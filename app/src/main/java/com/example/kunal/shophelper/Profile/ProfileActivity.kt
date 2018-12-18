package com.example.kunal.shophelper.Profile

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kunal.shophelper.CustomViews.CircleImageView
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.HelperClasses.Utils
import com.example.kunal.shophelper.Profile.Adapter.ViewPagerAdapter
import com.example.kunal.shophelper.Profile.View.ProfileView
import com.example.kunal.shophelper.R
import kotlinx.android.synthetic.main.activity_profile.*
import java.lang.Exception


class ProfileActivity : AppCompatActivity(), ProfileView {


    private var username: String? = null
    private var user_shopname: String? = null
    private var user_pic_url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var tab_layout = findViewById<TabLayout>(R.id.tab_layout)
        var view_pager = findViewById<ViewPager>(R.id.viewpager)
        var txt_shop_name=findViewById<TextView>(R.id.txt_firm_name_header)
        var txt_person_name=findViewById<TextView>(R.id.txt_person_name)
        var img_profile=findViewById<CircleImageView>(R.id.imgv_profile)

        try {
            username = intent.getStringExtra(Constants.IntentExtras.USER_NAME)
            user_shopname=intent.getStringExtra(Constants.IntentExtras.USER_SHOPNAME)
            user_pic_url=intent.getStringExtra(Constants.IntentExtras.USER_PIC_URL)
        } catch (e: Exception) {
            Utils.MyLogs.e("Exception", e.localizedMessage.toString())
        }

        if (username.equals(null) || user_shopname.equals(null)) {
            Utils.showtoast(this, "SomeError")
            finish()
        } else {
            Glide
                    .with(this)
                    .load(user_pic_url)
                    .asBitmap()
                    .into(img_profile)
            txt_person_name.text=username
            txt_shop_name.text=user_shopname

            var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, username!!)
            view_pager.adapter = viewPagerAdapter
            tab_layout.setupWithViewPager(view_pager)
        }
    }
}