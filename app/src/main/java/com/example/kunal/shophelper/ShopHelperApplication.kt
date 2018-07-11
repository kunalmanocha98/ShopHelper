package com.example.kunal.shophelper

import android.app.Application

class ShopHelperApplication: Application() {
lateinit var dataManger:DataManager
    override fun onCreate() {
        super.onCreate()
        val sharedPrefHelper: SharedPrefHelper = SharedPrefHelper(applicationContext)
        dataManger= DataManager(sharedPrefHelper)
    }
    fun getDataManager():DataManager{

        return dataManger
    }
}