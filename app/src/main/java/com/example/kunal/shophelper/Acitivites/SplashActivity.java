package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runhandler();
    }

    private void runhandler() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                isphonenumbersaved();
                if (!isphonenumbersaved()) {
                    Intent i = new Intent(SplashActivity.this, LoginPreview.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);
    }

    private boolean isphonenumbersaved() {
        if (!Constants.getstoredata(Constants.PhoneNumber, "0", this).equals("0")) {
//            Toast.makeText(this, ""+Constants.getstoredata(Constants.PhoneNumber,"0",this), Toast.LENGTH_SHORT).show();
            return true;
        } else {
//            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        runhandler();
    }
}
