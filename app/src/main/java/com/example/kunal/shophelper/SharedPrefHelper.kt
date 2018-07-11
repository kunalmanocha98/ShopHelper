package com.example.kunal.shophelper

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {


    var mSharedPreference: SharedPreferences

    init {
        mSharedPreference=context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
    }
    companion object {

        val MY_PREFS="PERB_MEMBER"
        val EMAIL_ID="EMAIL_ID"
        val PASSWORD="PASSWORD"
    }

    fun putEmail(email:String){
        mSharedPreference.edit().putString(EMAIL_ID,email).apply()
    }

    fun getEmail(): String {
        return mSharedPreference.getString(EMAIL_ID, "0")
    }

    fun putPassword(password:String){
        mSharedPreference.edit().putString(PASSWORD,password).apply()

    }

    fun getPassword(): String {
        return mSharedPreference.getString(PASSWORD, "0")
    }
}