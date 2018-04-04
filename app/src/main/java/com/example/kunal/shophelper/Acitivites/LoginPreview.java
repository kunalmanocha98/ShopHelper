package com.example.kunal.shophelper.Acitivites;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunal.shophelper.R;

public class LoginPreview extends AppCompatActivity implements View.OnClickListener{
TextView txt_entermobile,txt_mobilecode;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_preview);
        txt_entermobile=findViewById(R.id.txt_entermobile);
        txt_mobilecode=findViewById(R.id.txt_mobile_code);
        img=findViewById(R.id.img_loginpreview);
        txt_mobilecode.setOnClickListener(this);
        txt_entermobile.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_entermobile:{
                starttransition();

                break;
            }
            case R.id.txt_mobile_code:{
//                Intent i=new Intent(LoginPreview.this,GetPhoneNumber.class);
//                startActivity(i);
                starttransition();
                break;
            }
            case R.id.img_loginpreview:{
                starttransition();
                break;
            }
        }
    }

    private void starttransition() {

        Intent i=new Intent(LoginPreview.this,GetPhoneNumber.class);
//                View sharedview1=img;
//                View sharedview2=txt_entermobile;
//                String transitionname=getString(R.string.transitionname);
//                String transitiontext=getString(R.string.transitiontext);
//                Pair.create((View)img,getString(R.string.transitiontext));
        Pair<View,String> p1=Pair.create((View) img,getString(R.string.transitionname));
        Pair<View,String> p2=Pair.create((View)txt_entermobile,getString(R.string.transitiontext));
//        ChangeBounds changeBounds=new ChangeBounds();
//        changeBounds.setDuration(10000);
//        getWindow().setSharedElementEnterTransition(changeBounds);
        ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation(LoginPreview.this,p1,p2);
        startActivity(i,activityOptions.toBundle());
    }

}
