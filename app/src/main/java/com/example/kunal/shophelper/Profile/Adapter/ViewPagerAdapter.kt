package com.example.kunal.shophelper.Profile.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.kunal.shophelper.Profile.Fragments.Details.Detailsfragment
import com.example.kunal.shophelper.Profile.Fragments.Statistics.Statisticsfragment
import com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Transactionhistoryfragment

class ViewPagerAdapter(fm: FragmentManager, username: String) : FragmentPagerAdapter(fm) {
    var username=username


    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        if (position == 0) {
            fragment = Detailsfragment.newInstance(username)
        } else if (position == 1) {
            fragment = Transactionhistoryfragment.newInstance(username)
        } else if (position == 2) {
            fragment = Statisticsfragment.newInstance(username)
        }
        return fragment
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null

        if (position == 0) {
            title = "Details"
        } else if (position == 1) {
            title = "Transaction History"
        } else if (position == 2) {
            title = "Statistics"
        }
        return title
    }
}