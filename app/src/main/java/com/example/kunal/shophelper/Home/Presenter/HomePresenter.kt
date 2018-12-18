package com.example.kunal.shophelper.Home.Presenter

import android.content.Context
import android.content.Intent
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.Home.View.HomeView
import com.example.kunal.shophelper.NewEntry.NewEntryActivity

class HomePresenter<V:HomeView>(dataManager: DataManager,context: Context):HomePresenterinterface<V> {

    var context=context
    var mvpview:V ?= null

    override fun onattach(mvpview: V) {
        this.mvpview=mvpview
    }
    override fun opennewentrypage() {
        val i=Intent(context,NewEntryActivity::class.java)
        context.startActivity(i)
    }

}

