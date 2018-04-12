package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DealerMenuActivity extends AppCompatActivity {
    ConstraintLayout btn_dealer_chat, btn_calendar,btn_dealer_transaction,addnew,balance;
    String databasechild="DealerChat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_menu);
        btn_dealer_chat=findViewById(R.id.chatlayout_dealer);
        btn_calendar=findViewById(R.id.calendarlayout_dealer);
        btn_dealer_transaction=findViewById(R.id.transactionlayout_dealer);
        addnew=findViewById(R.id.addnewlayout_dealer);
        setTitle("Dealer");
        balance=findViewById(R.id.balancelayout_dealer);
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DealerMenuActivity.this,Client_Totalbalance.class);
                Constants.ServiceType="DEALERS";
                startActivity(i);
            }
        });
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:{
                Constants.clearalldata(this);
                Intent i = new Intent(this, SplashActivity.class);
                startActivity(i);
                break;
            }

        }
        return true;
    }

}
