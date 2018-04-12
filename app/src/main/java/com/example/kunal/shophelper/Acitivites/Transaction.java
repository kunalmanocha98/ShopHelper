package com.example.kunal.shophelper.Acitivites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.Models.TransactionDateGetSet;
import com.example.kunal.shophelper.Adapter.DatewiseListAdapter;
import com.example.kunal.shophelper.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Transaction extends AppCompatActivity implements View.OnClickListener {
    String name;
    TextView tv, clientname;
    EditText edttxt;
    Button submit;
    ImageView callbutton, locationbutton;
    //    boolean creditdebitswitcher;
    int balance;
    String lat, lng, phone;
    Boolean islocationavail = false;
    List<TransactionDateGetSet> list;
    RecyclerView rv_datewisebalance;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    DatewiseListAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference(Constants.ServiceType);

    public static final int LOCATIONCODE = 2;
    public static final int PHONECODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_transactiopage);
        clientname = findViewById(R.id.txt_client_name);
        rv_datewisebalance = findViewById(R.id.rv_datewisebalancelist);
        callbutton = findViewById(R.id.callicon);
        callbutton.setOnClickListener(this);
        locationbutton = findViewById(R.id.locationicon);
        locationbutton.setOnClickListener(this);
        rv_datewisebalance.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        clientname.setText(name);
        tv = findViewById(R.id.txt_balance);
        ref.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                balance = Integer.parseInt(dataSnapshot.child("balance").child("total").getValue().toString());
                phone = dataSnapshot.child("personal information").child("phonenumber").getValue().toString();
                try {
                    lat = dataSnapshot.child("personal information").child("location").child("lat").getValue().toString();
                    lng = dataSnapshot.child("personal information").child("location").child("lng").getValue().toString();
                    islocationavail = true;

                } catch (Exception e) {
                }
                tv.setText("" + balance);
                Iterable<DataSnapshot> arr = dataSnapshot.child("balance").child("datewise").getChildren();
                try {
                    while (arr.iterator().hasNext()) {
                        String date = arr.iterator().next().getKey();
                        String credit = dataSnapshot.child("balance").child("datewise").child(date).child("credit").getValue().toString();
                        String debit = dataSnapshot.child("balance").child("datewise").child(date).child("debit").getValue().toString();
                        String balance = dataSnapshot.child("balance").child("datewise").child(date).child("balance").getValue().toString();
                        TransactionDateGetSet getset = new TransactionDateGetSet(date, credit, debit, balance);
                        list.add(getset);
                    }
                    recyclerview();
                } catch (Exception e) {
                    Log.e("Exception", "" + e);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        edttxt = findViewById(R.id.edttxt_amount);
        submit = findViewById(R.id.btn_submitamount);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String amount = edttxt.getText().toString();
                if (Constants.Transactiontype.equals("CREDIT")) {
                    ref.child(name).child("balance").child("datewise").child(date).child("credit").setValue("" + amount);
                    int amnt = Integer.parseInt(amount);
                    balance = balance + amnt;
                    ref.child(name).child("balance").child("datewise").child(date).child("balance").setValue("" + balance);
                    ref.child(name).child("balance").child("total").setValue("" + balance);
                    tv.setText("" + balance);
                    checkbalance(date);
                } else {
                    ref.child(name).child("balance").child("datewise").child(date).child("debit").setValue("" + amount);
                    int amnt = Integer.parseInt(amount);
                    balance = balance - amnt;
                    ref.child(name).child("balance").child("datewise").child(date).child("balance").setValue("" + balance);
                    ref.child(name).child("balance").child("total").setValue("" + balance);
                    tv.setText("" + balance);
                    checkbalance(date);
                }
            }
        });
    }

    private void checkbalance(final String date) {
        if (Constants.Transactiontype.equals("CREDIT")) {
            ref.child(name).child("balance").child("datewise").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        String debit=dataSnapshot.child("debit").getValue().toString();
                    } catch (Exception e) {
                        ref.child(name).child("balance").child("datewise").child(date).child("debit").setValue("0");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else if (Constants.Transactiontype.equals("DEBIT")) {
            ref.child(name).child("balance").child("datewise").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        String credit=dataSnapshot.child("credit").getValue().toString();
                    } catch (Exception e) {
                        ref.child(name).child("balance").child("datewise").child(date).child("credit").setValue("0");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void recyclerview() {
        adapter = new DatewiseListAdapter(this, list);
        rv_datewisebalance.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callicon: {
                call();
                break;
            }
            case R.id.locationicon: {
                if (islocationavail) {
                    getusertolocation();
                } else {
                    Toast.makeText(this, "Location doesn't exists for this member", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void getusertolocation() {
        String uri = "http://maps.google.com/maps?daddr=" + lat + "," + lng;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void checkpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONECODE);
        } else {
            call();
        }
    }

    private void call() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            checkpermission();
            return;
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONECODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                }
            }
        }
    }
}
