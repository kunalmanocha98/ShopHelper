package com.example.kunal.shophelper

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class DataManager{

    var mSharedPrefHelper:SharedPrefHelper

    constructor(sharedPrefsHelper: SharedPrefHelper){
        mSharedPrefHelper=sharedPrefsHelper
    }

    fun saveEmailId(email:String){
        mSharedPrefHelper.putEmail(email)
    }

    fun getEmailId():String{
        return mSharedPrefHelper.getEmail()
    }

    fun savepassword(password:String){
        mSharedPrefHelper.putPassword(password)
    }

    fun getpassword():String{
        return mSharedPrefHelper.getPassword()
    }



}
