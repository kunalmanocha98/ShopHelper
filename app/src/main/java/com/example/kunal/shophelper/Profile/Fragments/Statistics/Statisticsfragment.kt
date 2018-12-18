package com.example.kunal.shophelper.Profile.Fragments.Statistics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.Profile.Fragments.Details.Detailsfragment
import com.example.kunal.shophelper.R

class Statisticsfragment : Fragment() {

    companion object Instance{
        fun newInstance(userName:String): Statisticsfragment {
            val f = Statisticsfragment()
            val args=Bundle()
            args.putString(Constants.IntentExtras.USER_NAME,userName)
            f.arguments=args
            return f
        }

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }
}