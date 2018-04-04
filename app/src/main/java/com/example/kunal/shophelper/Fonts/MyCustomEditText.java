package com.example.kunal.shophelper.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyCustomEditText extends android.support.v7.widget.AppCompatEditText {

    public MyCustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Ubuntu-Light.ttf"));
    }
}
