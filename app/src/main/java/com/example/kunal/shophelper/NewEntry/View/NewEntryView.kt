package com.example.kunal.shophelper.NewEntry.View

import android.content.Intent
import android.graphics.Bitmap

interface NewEntryView {
    fun opencamera(intent: Intent, request_id: Int)
    fun opengallery(intent: Intent, request_id: Int)
    fun setProfilePic(bitmap: Bitmap)
    fun showprogress(boolean: Boolean)
    fun refreshdata()
}