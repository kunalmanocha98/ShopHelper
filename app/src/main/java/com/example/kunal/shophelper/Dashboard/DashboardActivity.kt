package com.example.kunal.shophelper.Dashboard


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.kunal.shophelper.Dashboard.Presenter.DashboardPresenter
import com.example.kunal.shophelper.Dashboard.View.DashboardView
import com.example.kunal.shophelper.Home.HomeFragment
import com.example.kunal.shophelper.Profile.ProfileFragment
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.Sales.SalesFragment
import com.example.kunal.shophelper.ShopHelperApplication
import com.example.kunal.shophelper.Transaction.Transaction_Fragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import android.support.v4.app.Fragment


lateinit var mypresenter:DashboardPresenter<DashboardView>

class DashboardActivity: AppCompatActivity(),DashboardView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val dataManager = (application as ShopHelperApplication).getDataManager()
        mypresenter=DashboardPresenter(dataManager)
        mypresenter.onAttach(this)
        loadfragment(HomeFragment())
    }

    override fun onResume() {
        super.onResume()
        bn_view.setOnNavigationItemSelectedListener(onnavigationitemselection)
    }

    private var onnavigationitemselection=object :BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            var fragment:Fragment
            when(item.itemId){
                R.id.bn_home -> {
                    fragment=HomeFragment()
                    loadfragment(fragment)
                    return true
                }
                R.id.bn_tranaction ->{
                    fragment=Transaction_Fragment()
                    loadfragment(fragment)
                    return true
                }
                R.id.bn_sales ->{
                    fragment=SalesFragment()
                    loadfragment(fragment)
                    return true
                }
                R.id.bn_chat ->{
                    return true
                }
                R.id.bn_profile ->{
                    fragment=ProfileFragment()
                    loadfragment(fragment)
                    return true
                }
                else ->{
                    return false
                }
            }
        }


    }

    private fun loadfragment(fragment: Fragment) {

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


