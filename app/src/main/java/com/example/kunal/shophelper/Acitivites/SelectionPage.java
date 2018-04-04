package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kunal.shophelper.R;

public class SelectionPage extends AppCompatActivity implements View.OnClickListener{
    Button btn_client,btn_dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);
        btn_client=findViewById(R.id.btn_client);
        btn_dealer=findViewById(R.id.btn_dealer);
        btn_dealer.setOnClickListener(this);
        btn_client.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_client:{
//                Toast.makeText(this, "hello client", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SelectionPage.this,Client_MenuPage.class);
                startActivity(i);
                break;
            }
            case R.id.btn_dealer:{
//                Toast.makeText(this, "Hello dealer", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SelectionPage.this,DealerMenuActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
