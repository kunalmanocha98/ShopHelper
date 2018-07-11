package com.example.kunal.shophelper.Splash.Presenter

import com.example.kunal.shophelper.Splash.View.SplashView

interface SplashPresenterinterface<in V:SplashView> {

    fun onattach(mvpview:V)

}