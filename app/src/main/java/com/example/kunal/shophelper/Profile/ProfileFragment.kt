package com.example.kunal.shophelper.Profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.Profile.View.ProfileView
import com.example.kunal.shophelper.R

class ProfileFragment: Fragment(),ProfileView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v=inflater?.inflate(R.layout.fragment_profile,container,false)
        return v
    }
}