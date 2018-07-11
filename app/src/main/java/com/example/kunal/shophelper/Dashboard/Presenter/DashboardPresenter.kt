package com.example.kunal.shophelper.Dashboard.Presenter

import com.example.kunal.shophelper.Dashboard.View.DashboardView
import com.example.kunal.shophelper.DataManager

class DashboardPresenter<V:DashboardView>(dataManager: DataManager):DashboardpresenterInterface<V>{
    override fun onAttach(mvpView: V) {

    }

}