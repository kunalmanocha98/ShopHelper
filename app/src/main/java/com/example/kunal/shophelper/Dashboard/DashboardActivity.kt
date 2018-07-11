package com.example.kunal.shophelper.Dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kunal.shophelper.Dashboard.Presenter.DashboardPresenter
import com.example.kunal.shophelper.Dashboard.View.DashboardView
import com.example.kunal.shophelper.Login.Presenter.LoginPresenter
import com.example.kunal.shophelper.Login.View.LoginView
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.ShopHelperApplication
lateinit var mypresenter:DashboardPresenter<DashboardView>
class DashboardActivity: AppCompatActivity(),DashboardView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val dataManager = (application as ShopHelperApplication).getDataManager()
        mypresenter=DashboardPresenter(dataManager)
        mypresenter.onAttach(this)
    }
}