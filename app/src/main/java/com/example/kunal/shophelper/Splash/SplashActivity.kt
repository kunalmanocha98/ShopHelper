package com.example.kunal.shophelper.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.kunal.shophelper.Login.LoginActivity
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.ShopHelperApplication
import com.example.kunal.shophelper.Splash.Presenter.Splashpresenter
import com.example.kunal.shophelper.Splash.View.SplashView

class SplashActivity:AppCompatActivity(),SplashView {
    lateinit var mypresenter:Splashpresenter<SplashView>
    override fun startintent(intent: Intent) {
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var dataManager=(application as ShopHelperApplication).getDataManager()
        mypresenter= Splashpresenter(dataManager,this)
        mypresenter.onattach(this)
        mypresenter.runhandler(1000)
    }

}