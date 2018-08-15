package com.example.kunal.shophelper.Splash.Presenter

import android.content.Context
import android.content.Intent
import android.os.Handler
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.Login.LoginActivity
import com.example.kunal.shophelper.Splash.View.SplashView

class Splashpresenter<V:SplashView>(dataManager: DataManager,context: Context):SplashPresenterinterface<V> {
    var context=context
    var mvpView:V?= null
    override fun runhandler(time: Long) {
        var i=Intent(context,LoginActivity::class.java)
        Handler().postDelayed({
            mvpView!!.startintent(i)
        },time)
    }

    override fun onattach(mvpview: V) {
        this.mvpView=mvpView
    }


}