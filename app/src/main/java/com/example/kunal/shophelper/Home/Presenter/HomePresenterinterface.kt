package com.example.kunal.shophelper.Home.Presenter

import com.example.kunal.shophelper.Home.View.HomeView

interface HomePresenterinterface<in V:HomeView> {
    fun onattach(mvpview:V)
}