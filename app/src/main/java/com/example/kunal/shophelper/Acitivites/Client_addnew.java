package com.example.kunal.shophelper.Acitivites;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Client_addnew extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    EditText name, shop_number, phone_number, edt_location;
    Button btn_add;
    ImageView locationbutton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference(Constants.ServiceType);
    GoogleApiClient googleApiClient;
    Location location;
    LocationRequest locationRequest;
    PendingResult<LocationSettingsResult> result;
    final static int REQUEST_LOCATION = 199;
    ProgressDialog dialog;
    private static  final int LOCATION_PERMISSION=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_addnew);
        name = findViewById(R.id.edt_name);
        shop_number = findViewById(R.id.edt_shopno);
        phone_number = findViewById(R.id.edt_phone);
        locationbutton = findViewById(R.id.locationbutton);
        btn_add = findViewById(R.id.btn_add);
        edt_location = findViewById(R.id.edt_location);
        edt_location.setCursorVisible(false);
        edt_location.setFocusable(false);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newname = name.getText().toString();
                String shopnumber = shop_number.getText().toString();
                String phonenumber = phone_number.getText().toString();
                String locationtext = edt_location.getText().toString();
                if (isvalid(newname, shopnumber, phonenumber)) {
                    ref.child(newname).child("personal information").child("shopnumber").setValue(shopnumber);
                    ref.child(newname).child("personal information").child("phonenumber").setValue(phonenumber);
                    if (location != null) {
                        ref.child(newname).child("personal information").child("location").child("lat").setValue("" + location.getLatitude());
                        ref.child(newname).child("personal information").child("location").child("lng").setValue("" + location.getLongitude());
                    }
                    ref.child(newname).child("balance").child("total").setValue("0");
                    setvaluestozero();
                } else {
                    Toast.makeText(Client_addnew.this, "Invalid deatils!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setvaluestozero() {
        name.setText("");
        shop_number.setText("");
        phone_number.setText("");
        edt_location.setText("");
        Toast.makeText(this, "data entry successful", Toast.LENGTH_SHORT).show();
    }

    private Boolean isvalid(String newname, String shopnumber, String phonenumber) {
        if (newname.equals("") || shopnumber.equals("") || phonenumber.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void getlocation() {

        new Locationtask().execute();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnongps();
            }
        });
    }

    private void turnongps() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS: {
                        getlocation();
                        break;
                    }
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED: {
//                        Toast.makeText(Client_addnew.this, "Gps required!!", Toast.LENGTH_SHORT).show();
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    Client_addnew.this,
                                    REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    }
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        getlocation();
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        Toast.makeText(this, "Gps required!!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        googleApiClient.connect();
    }

    private class Locationtask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Client_addnew.this);
            dialog.setMessage("Fetching Location");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean bln) {
            super.onPostExecute(bln);
            dialog.dismiss();
            if (bln) {
                edt_location.setText("lat: " + location.getLatitude() + " | long:" + location.getLongitude());
            } else {
                promptpermissions();
            }


        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (ActivityCompat.checkSelfPermission(Client_addnew.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Client_addnew.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            return true;
        }
    }

    private void promptpermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_PERMISSION:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getlocation();
                }

                break;
            }
        }
    }
}
