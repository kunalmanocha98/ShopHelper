package com.example.kunal.shophelper.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustonTextView extends android.support.v7.widget.AppCompatTextView {

    public MyCustonTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Ubuntu-Light.ttf"));
    }
}
