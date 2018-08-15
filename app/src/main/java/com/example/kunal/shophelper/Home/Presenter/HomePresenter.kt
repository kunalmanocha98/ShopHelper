package com.example.kunal.shophelper.Home.Presenter

import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.Home.View.HomeView

class HomePresenter<V:HomeView>(dataManager: DataManager):HomePresenterinterface<V> {

    var mvpview:V ?= null
    override fun onattach(mvpview: V) {
        this.mvpview=mvpview
    }

}

