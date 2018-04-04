package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

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
                Intent i=new Intent(SplashActivity.this,LoginPreview.class);
                startActivity(i);
                finish();
            }
        },3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        runhandler();
    }
}
