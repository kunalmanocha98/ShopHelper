package com.example.kunal.shophelper.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by kunal on 28/3/18.
 */

public class Constants {
    public static String ServiceType;
    public static String Transactiontype;
    public static String PhoneNumber="PhoneNumber";
    public static String MY_SHARED_PREF="MySharedPrefrences";
    public static String USERNAME="username";
    public static String PASSWORD="password";
    public static String USER_NAME="def";

    public static class FBRealTimeNodes{
        public static String USERS="Users";
        public static String DETAILS="Details";
        public static String TRANSACTION="Transactions";
        public static String CREDIT="credit";
        public static String DEBIT="debit";
        public static String TOTAL_BALANCE="total_balance";
    }

    public static class IntentExtras{
        public static String USER_NAME="username";
        public static String USER_SHOPNAME="shopname";
        public static String USER_PIC_URL="pic_url";
    }


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


    public static String getUUID(){
        MessageDigest salt = null;
        try {
            salt = MessageDigest.getInstance("SHA-1");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytesToHex(salt.digest());
    }

    private static String bytesToHex(byte[] digest) {
            StringBuilder result = new StringBuilder();
            char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
            for (int idx = 0; idx < digest.length; ++idx) {
                byte b = digest[idx];
                result.append(digits[ (b&0xf0) >> 4 ]);
                result.append(digits[ b&0x0f]);
            }
            return result.toString();

    }


}
