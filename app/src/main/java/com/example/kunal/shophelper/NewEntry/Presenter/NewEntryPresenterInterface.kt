package com.example.kunal.shophelper.NewEntry.Presenter

import android.content.Intent
import android.support.design.widget.TextInputEditText
import com.example.kunal.shophelper.NewEntry.View.NewEntryView

interface NewEntryPresenterInterface<V:NewEntryView> {
    fun onattach(view:NewEntryView)
    fun showimageselectiondialog()
    fun onactivityresult(requestCode: Int, resultCode: Int, data: Intent?)
    fun addtextwatcher(textInputEditText: TextInputEditText)
    fun validateandsend(name:String,shopname:String,contact:String,address:String)
}