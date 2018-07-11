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
lateinit var mypresenter:Splashpresenter<SplashView>
class SplashActivity:AppCompatActivity(),SplashView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var dataManager=(application as ShopHelperApplication).getDataManager()
        mypresenter= Splashpresenter(dataManager)
        mypresenter.onattach(this)
        runhandler()
    }

    private fun runhandler() {
        Handler().postDelayed({
            var i=Intent(this,LoginActivity::class.java)
            startActivity(i)
            finish()

        },1000)
    }
}