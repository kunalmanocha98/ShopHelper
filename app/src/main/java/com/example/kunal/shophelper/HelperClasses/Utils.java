package com.example.kunal.shophelper.HelperClasses;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static class MyLogs{
        public static void e(String tag,String message){
            Log.e(tag,message);
        }
        public static void d(String tag,String message){
            Log.d(tag,message);
        }
        public static void v(String tag,String message){
            Log.v(tag,message);
        }
        public static void i(String tag,String message){
            Log.i(tag,message);
        }

    }

    public static void showtoast(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static String getcurrentmonth() {
        Date date = getcurrentdate();
        return converttodateformat("MMM", date);
    }

    public static String getcurrentyear() {
        Date date = getcurrentdate();
        return converttodateformat("YYYY", date);
    }

    public static Date getcurrentdate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String converttodateformat(String format, Date date) {
        SimpleDateFormat spf = new SimpleDateFormat(format);
        return spf.format(date);
    }

    public static String getcurrentdateinformat(String format) {
        SimpleDateFormat spf = new SimpleDateFormat(format);
        return spf.format(Calendar.getInstance().getTime());
    }
}
