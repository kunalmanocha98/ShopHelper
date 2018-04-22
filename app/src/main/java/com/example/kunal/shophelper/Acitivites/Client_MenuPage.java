package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;

public class Client_MenuPage extends AppCompatActivity implements View.OnClickListener {
ConstraintLayout btn_transactionmenu,btn_balance,btn_addclient,btn_calendar,btn_chat;
    private String databasechild="ClientChat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_menupage);
        btn_transactionmenu =findViewById(R.id.transactionlayout_dealer);
        btn_balance=findViewById(R.id.balancelayout_dealer);
        btn_addclient=findViewById(R.id.addnewlayout_dealer);
        btn_calendar=findViewById(R.id.calendarlayout_dealer);
        btn_chat=findViewById(R.id.chatlayout_dealer);
        btn_addclient.setOnClickListener(this);
        btn_transactionmenu.setOnClickListener(this);
        btn_balance.setOnClickListener(this);
        btn_calendar.setOnClickListener(this);
        btn_chat.setOnClickListener(this);
        setTitle("Client");
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
            case R.id.help:{
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "captainamerica98999@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                startActivity(Intent.createChooser(emailIntent, null));
                break;
            }

        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.transactionlayout_dealer:{
                Intent i=new Intent(Client_MenuPage.this,Transaction_Menupage.class);
                Constants.ServiceType="CLIENTS";
//                i.putExtra("switcher",true);
                startActivity(i);
                break;
            }
            case R.id.balancelayout_dealer:{
                Intent i=new Intent(Client_MenuPage.this,Client_Totalbalance.class);
                startActivity(i);
                Constants.ServiceType="CLIENTS";
                break;
            }
            case R.id.addnewlayout_dealer:{
                Intent i=new Intent(Client_MenuPage.this,Client_addnew.class);
                startActivity(i);
                Constants.ServiceType="CLIENTS";
                break;
            }
            case R.id.calendarlayout_dealer:{
                Intent i=new Intent(Client_MenuPage.this,ClientCalendarPage.class);
                startActivity(i);
                Constants.ServiceType="CLIENTS";
//                Toast.makeText(this, "clendar is not available", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.chatlayout_dealer:{
                Intent i=new Intent(Client_MenuPage.this,ChatActivity.class);
                i.putExtra("child",databasechild);
                startActivity(i);
                break;
            }
        }
    }
}
