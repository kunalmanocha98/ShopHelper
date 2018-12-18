package com.example.kunal.shophelper.Home

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.Home.Presenter.HomePresenter
import com.example.kunal.shophelper.Home.View.HomeView
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.ShopHelperApplication

class HomeFragment:Fragment() ,HomeView {

    lateinit var fab_new_entry:FloatingActionButton
    lateinit var mypresenter:HomePresenter<HomeView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v=inflater!!.inflate(R.layout.fragment_home,container,false)
        init()
        fab_new_entry=v.findViewById(R.id.fab_new_entry)
        return  v
    }

    private fun init() {
        var dataManager=(activity?.application as ShopHelperApplication).getDataManager()
        mypresenter= HomePresenter(dataManager, this.requireContext())
        mypresenter.onattach(this)
    }

    override fun onResume() {
        super.onResume()
        fab_new_entry.setOnClickListener { mypresenter.opennewentrypage() }
    }
}
