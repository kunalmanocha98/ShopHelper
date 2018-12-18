package com.example.kunal.shophelper.NewEntry.Presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.HelperClasses.Utils
import com.example.kunal.shophelper.NewEntry.View.NewEntryView
import com.example.kunal.shophelper.R
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.security.MessageDigest
import java.util.*
import kotlin.collections.HashMap


class NewEntryPresenter<V : NewEntryView>(dataManager: DataManager, context: Context) : NewEntryPresenterInterface<V> {

    val context = context
    lateinit var alertDialog: AlertDialog
    val REQUEST_OPEN_GALLERY_ID = 1
    val REQUEST_IMAGE_CAPTURE_ID = 2
    lateinit var mRef: StorageReference
    var imageUrl: String? = null
    override fun showimageselectiondialog() {

        val alertBuilder = AlertDialog.Builder(context)
        val alertView = LayoutInflater.from(context).inflate(R.layout.dialog_image_selection, null)
        alertBuilder.setView(alertView)
        alertDialog = alertBuilder.create()
        alertDialog.show()
        val openGalleryButton = alertView.findViewById<ImageButton>(R.id.button_select_from_gallery)
        openGalleryButton.setOnClickListener { opengallery() }
        val launchCameraButton = alertView.findViewById<ImageButton>(R.id.button_launch_camera)
        launchCameraButton.setOnClickListener { opencamera() }
    }

    private fun opencamera() {

        alertDialog.dismiss()
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
            mvpview?.opencamera(takePictureIntent, REQUEST_IMAGE_CAPTURE_ID)
        }
    }

    private fun opengallery() {
        alertDialog.dismiss()
        val openGalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        mvpview?.opengallery(openGalleryIntent, REQUEST_OPEN_GALLERY_ID)
    }


    var mvpview: NewEntryView? = null
    override fun onattach(view: NewEntryView) {
        this.mvpview = view
    }

    override fun onactivityresult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_OPEN_GALLERY_ID && resultCode == Activity.RESULT_OK && data != null) {
            var receiptBitmap: Bitmap? = null

            try {
                receiptBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, data.data)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            // Compress the bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            receiptBitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()

            val receiptBitmapCompressed = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            uploadprofilepic(receiptBitmapCompressed)
//            mvpview?.setProfilePic(receiptBitmapCompressed)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_ID && resultCode == Activity.RESULT_OK && data != null) {
            val extras = data.extras
            val imageBitmap = extras!!.get("data") as Bitmap
            // Compress the bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()

            val receiptBitmapCompressed = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//            mvpview?.setProfilePic(receiptBitmapCompressed)
            uploadprofilepic(receiptBitmapCompressed)
            alertDialog.dismiss()

        }
    }

    private fun uploadprofilepic(bitmap: Bitmap?) {
        mvpview?.showprogress(true)
        mRef = FirebaseStorage.getInstance().reference.child("Profile Images/" + Constants.getUUID() + ".jpg")
        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        mRef.putBytes(data).addOnSuccessListener { p0 ->
            mvpview?.showprogress(false)
            mvpview?.setProfilePic(bitmap!!)
            var task = p0?.metadata?.reference?.downloadUrl
            task?.addOnSuccessListener { uri ->
                imageUrl = uri.toString()
            }
        }
    }

    override fun addtextwatcher(textInputEditText: TextInputEditText) {
        textInputEditText.addTextChangedListener(MytextWatcher(textInputEditText))
    }

    class MytextWatcher(textInputEditText: TextInputEditText) : TextWatcher {
        var textInputEditText = textInputEditText
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    override fun validateandsend(name: String, shopname: String, contact: String, address: String) {
        if (name != "" && shopname != "" && contact != "" && address != "") {
            senddata(name, shopname, contact, address)
        }
    }

    private fun senddata(name: String, shopname: String, contact: String, address: String) {
        var mReference = FirebaseDatabase.getInstance().reference.child("Users").child(name).child("Details")

        var hash = HashMap<String, String>()
        hash.put("user_name", name)
        hash.put("user_shopname", shopname)
        hash.put("user_contact", contact)
        hash.put("user_address", address)
        hash.put("user_profile_pic_url", imageUrl.toString())
        mReference.setValue(hash).addOnCompleteListener {
            if (it.isSuccessful) {
                mvpview!!.refreshdata()
            } else {
                Utils.showtoast(context, "Some Error Occurred!")
                mvpview!!.refreshdata()
            }
        }
    }
}
