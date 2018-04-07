package com.example.kunal.shophelper.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kunal on 28/3/18.
 */

public class Constants {
    public static String ServiceType;
    public static String Transactiontype;
    public static  String PhoneNumber="PhoneNumber";
    public static  String MY_SHARED_PREF="MySharedPrefrences";
    public static  String USERNAME="username";
    public static  String PASSWORD="password";


    public static void storeinprefrences(String key, String value, Context context){
        SharedPreferences preferences= context.getSharedPreferences(MY_SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getstoredata(String key,String defval,Context context){
        SharedPreferences preferences= context.getSharedPreferences(MY_SHARED_PREF,Context.MODE_PRIVATE);
        return preferences.getString(key,defval);
    }

    public static void clearalldata(Context context){
        SharedPreferences preferences= context.getSharedPreferences(MY_SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();
    }



}
