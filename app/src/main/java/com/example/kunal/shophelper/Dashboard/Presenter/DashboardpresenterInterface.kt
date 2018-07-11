package com.example.kunal.shophelper.Dashboard.Presenter

import com.example.kunal.shophelper.Dashboard.View.DashboardView

interface DashboardpresenterInterface<in V:DashboardView> {
    fun onAttach(mvpView: V)
}