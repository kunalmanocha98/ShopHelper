package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;

public class Transaction_Menupage extends AppCompatActivity implements View.OnClickListener {
    Button credittransaction,debittrtansaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_transactionmenu);
        credittransaction=findViewById(R.id.btn_credittransaction);
        debittrtansaction=findViewById(R.id.btn_debittransaction);
        credittransaction.setOnClickListener(this);
        debittrtansaction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_credittransaction :{
                Intent i=new Intent(Transaction_Menupage.this,Transaction_listofnames.class);
                Constants.Transactiontype="CREDIT";
//                i.putExtra("switcher",true);
                startActivity(i);
                break;
            }
            case R.id.btn_debittransaction:{
                Intent i=new Intent(Transaction_Menupage.this,Transaction_listofnames.class);
                Constants.Transactiontype="DEBIT";
//                i.putExtra("switcher",false);
                startActivity(i);
                break;
            }
        }
    }
}
