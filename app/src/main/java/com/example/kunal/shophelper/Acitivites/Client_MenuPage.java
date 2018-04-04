package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;

public class Client_MenuPage extends AppCompatActivity implements View.OnClickListener {
Button btn_transactionmenu,btn_balance,btn_addclient,btn_calendar,btn_chat;
    private String databasechild="ClientChat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_menupage);
        btn_transactionmenu =findViewById(R.id.btn_transaction);
        btn_balance=findViewById(R.id.btn_balance);
        btn_addclient=findViewById(R.id.btn_addclient);
        btn_calendar=findViewById(R.id.btn_calendar);
        btn_chat=findViewById(R.id.btn_chat);
        btn_addclient.setOnClickListener(this);
        btn_transactionmenu.setOnClickListener(this);
        btn_balance.setOnClickListener(this);
        btn_calendar.setOnClickListener(this);
        btn_chat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_transaction:{
                Intent i=new Intent(Client_MenuPage.this,Transaction_Menupage.class);
                Constants.ServiceType="CLIENTS";
//                i.putExtra("switcher",true);
                startActivity(i);
                break;
            }
            case R.id.btn_balance:{
                Intent i=new Intent(Client_MenuPage.this,Client_Totalbalance.class);
                startActivity(i);
                break;
            }
            case R.id.btn_addclient:{
                Intent i=new Intent(Client_MenuPage.this,Client_addnew.class);
                startActivity(i);
                break;
            }
            case R.id.btn_calendar:{
                Intent i=new Intent(Client_MenuPage.this,ClientCalendarPage.class);
                startActivity(i);
//                Toast.makeText(this, "clendar is not available", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_chat:{
                Intent i=new Intent(Client_MenuPage.this,ChatActivity.class);
                i.putExtra("child",databasechild);
                startActivity(i);
                break;
            }
        }
    }
}
