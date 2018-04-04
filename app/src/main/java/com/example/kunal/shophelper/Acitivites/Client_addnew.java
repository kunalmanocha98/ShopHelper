package com.example.kunal.shophelper.Acitivites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Client_addnew extends AppCompatActivity {
    EditText name,shop_number,phone_number;
    Button btn_add;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref =database.getReference(Constants.ServiceType);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_addnew);
        name = findViewById(R.id.edttxt_name);
        shop_number = findViewById(R.id.edttxt_shopnumber);
        phone_number = findViewById(R.id.edttxt_phonenumber);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newname = name.getText().toString();
                String shopnumber = shop_number.getText().toString();
                String phonenumber = phone_number.getText().toString();
                ref.child(newname).child("personal information").child("shopnumber").setValue(shopnumber);
                ref.child(newname).child("personal information").child("phonenumber").setValue(phonenumber);
                ref.child(newname).child("balance").child("total").setValue("0");

            }
        });
    }
}
