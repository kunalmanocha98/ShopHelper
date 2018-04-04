package com.example.kunal.shophelper.Acitivites;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;

public class GetPhoneNumber extends AppCompatActivity implements View.OnClickListener {
ImageView img;
TextView txt_entermobile;
EditText edt_mobilenumber;
FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone_number);
        img=findViewById(R.id.flagicon);
        txt_entermobile=findViewById(R.id.txt_entermobile);
        edt_mobilenumber=findViewById(R.id.edt_mobilenumber);
        fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(this);
        edt_mobilenumber.setOnClickListener(this);
//        edt_mobilenumber.setFocusable(true);
//        edt_mobilenumber.setFocusableInTouchMode(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edt_mobilenumber:{
                break;
            }
            case R.id.floatingActionButton:{
                Constants.PhoneNumber=edt_mobilenumber.getText().toString();
                Intent i=new Intent(GetPhoneNumber.this,LoginActivity.class);
                startActivity(i);
                break;
            }
        }

    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
//        Intent i=new Intent(GetPhoneNumber.this,LoginPreview.class);
//        ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation(GetPhoneNumber.this,
//                Pair.create((View)img,getString(R.string.transitionname)),
//                Pair.create((View)txt_entermobile,getString(R.string.transitiontext)));
//        startActivity(i,activityOptions.toBundle());
    }
}
