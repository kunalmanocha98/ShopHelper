package com.example.kunal.shophelper.NewEntry

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.kunal.shophelper.CustomViews.CircleImageView
import com.example.kunal.shophelper.NewEntry.Presenter.NewEntryPresenter
import com.example.kunal.shophelper.NewEntry.View.NewEntryView
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.ShopHelperApplication
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_newentry.*
import org.w3c.dom.Text
import android.R.attr.bitmap
import android.support.design.widget.AppBarLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import com.example.kunal.shophelper.HelperClasses.Constants
import java.io.ByteArrayOutputStream


class NewEntryActivity : AppCompatActivity(), NewEntryView {

    lateinit var mypresenter: NewEntryPresenter<NewEntryView>
    lateinit var imgv_profile: CircleImageView
    lateinit var imgv_profile_edit: ImageView
    lateinit var input_name: TextInputEditText
    lateinit var input_shopname: TextInputEditText
    lateinit var input_contact: TextInputEditText
    lateinit var input_address: TextInputEditText
    lateinit var progress: ProgressBar
    lateinit var toolbar: Toolbar
    lateinit var appBarLayout: AppBarLayout
    lateinit var actionBar: ActionBar
    lateinit var btn_add: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newentry)
        init()
    }

    override fun onResume() {
        super.onResume()
        imgv_profile_edit.setOnClickListener { mypresenter.showimageselectiondialog() }
        mypresenter.addtextwatcher(input_name)
        mypresenter.addtextwatcher(input_shopname)
        mypresenter.addtextwatcher(input_contact)
        mypresenter.addtextwatcher(input_address)
        setSupportActionBar(toolbar)
        actionBar = this!!.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.title = "Add New"
        btn_add.setOnClickListener {
            validatedata()
        }
    }

    private fun validatedata() {
        mypresenter.validateandsend(input_name.text.toString(),
                input_shopname.text.toString(), input_contact.text.toString(),
                input_address.text.toString())
    }

    fun init() {
        var dataManager = (application as ShopHelperApplication).getDataManager()
        mypresenter = NewEntryPresenter(dataManager, this)
        mypresenter.onattach(this)
        imgv_profile = findViewById(R.id.imgv_profile)
        imgv_profile.setImageResource(R.drawable.profile_placeholder)
        imgv_profile_edit = findViewById(R.id.imgv_edit)
//        inputs
        input_name = findViewById(R.id.input_name)
        input_shopname = findViewById(R.id.input_shopname)
        input_contact = findViewById(R.id.input_phone)
        input_address = findViewById(R.id.input_address)
//        progress bar
        progress = findViewById(R.id.progressbar)
        progress.visibility = View.GONE
//        toolbar
        toolbar = findViewById(R.id.toolbar)
//        appbarlayout
        appBarLayout = findViewById(R.id.appbar_layout)
//        Button add
        btn_add = findViewById(R.id.btn_add_new_entry)
    }

    override fun opencamera(intent: Intent, request_id: Int) {
        startActivityForResult(intent, request_id)
    }

    override fun opengallery(intent: Intent, request_id: Int) {
        startActivityForResult(intent, request_id)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mypresenter.onactivityresult(requestCode, resultCode, data)
    }

    override fun setProfilePic(bitmap: Bitmap) {
        imgv_profile.setImageBitmap(bitmap)
    }

    override fun showprogress(boolean: Boolean) {
        if (boolean) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    override fun refreshdata() {
        input_name.setText("")
        input_address.setText("")
        input_contact.setText("")
        input_shopname.setText("")
        imgv_profile.setImageResource(R.drawable.profile_placeholder)
    }

}