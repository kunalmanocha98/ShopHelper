package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DealerMenuActivity extends AppCompatActivity {
    Button btn_dealer_chat, btn_calendar,btn_dealer_transaction,addnew;
    String databasechild="DealerChat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_menu);
        btn_dealer_chat=findViewById(R.id.btn_dealer_chat);
        btn_calendar=findViewById(R.id.btn_calendar);
        btn_dealer_transaction=findViewById(R.id.btn_dealer_transaction);
        addnew=findViewById(R.id.btn_dealer_addnew);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DealerMenuActivity.this,Client_addnew.class);
                Constants.ServiceType="DEALERS";
                startActivity(i);
            }
        });
        btn_dealer_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DealerMenuActivity.this,ChatActivity.class);
                i.putExtra("child",databasechild);
                startActivity(i);
            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DealerMenuActivity.this,ClientCalendarPage.class);
                startActivity(i);
            }
        });
        btn_dealer_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DealerMenuActivity.this,Transaction_Menupage.class);
                Constants.ServiceType="DEALERS";
                startActivity(i);

            }
        });

    }
}
