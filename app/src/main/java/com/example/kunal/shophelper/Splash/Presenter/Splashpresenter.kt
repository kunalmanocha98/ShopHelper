package com.example.kunal.shophelper.Splash.Presenter

import android.content.Intent
import com.example.kunal.shophelper.Acitivites.LoginActivity
import com.example.kunal.shophelper.Acitivites.LoginPreview
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.Splash.View.SplashView
import java.util.logging.Handler

class Splashpresenter<V:SplashView>(dataManager: DataManager):SplashPresenterinterface<V> {

    var mvpView: V? = null
    override fun onattach(mvpview: V) {
        this.mvpView=mvpView
    }


}