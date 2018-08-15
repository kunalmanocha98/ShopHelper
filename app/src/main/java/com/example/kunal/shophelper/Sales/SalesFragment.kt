package com.example.kunal.shophelper.Sales

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.Sales.View.SalesView

class SalesFragment: Fragment(),SalesView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v= inflater?.inflate(R.layout.fragment_sales,container,false)
        return v
    }
}