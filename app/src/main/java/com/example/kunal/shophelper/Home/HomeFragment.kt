package com.example.kunal.shophelper.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.Home.View.HomeView
import com.example.kunal.shophelper.R

class HomeFragment:Fragment() ,HomeView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v=inflater!!.inflate(R.layout.fragment_home,container,false)
        return  v
    }
}
