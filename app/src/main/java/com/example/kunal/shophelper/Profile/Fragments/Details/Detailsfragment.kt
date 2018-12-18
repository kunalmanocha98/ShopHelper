package com.example.kunal.shophelper.Profile.Fragments.Details

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.Profile.Fragments.Details.Model.ProfileUserDetails
import com.example.kunal.shophelper.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_details.view.*

class Detailsfragment : Fragment(), View.OnClickListener {

    companion object Instance{
       fun newInstance(userName:String):Detailsfragment{
           val f = Detailsfragment()
           val args=Bundle()
           args.putString(Constants.IntentExtras.USER_NAME,userName)
           f.arguments=args
           return f
       }
    }
    lateinit var txt_company_name: TextView
    lateinit var txt_address: TextView
    lateinit var txt_phone: TextView
    lateinit var userName: String
    lateinit var btn_signout:TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v=inflater.inflate(R.layout.fragment_details, container, false)
        var args=arguments
        userName=args!!.getString(Constants.IntentExtras.USER_NAME).toString()
        txt_address=v.txt_address_name
        txt_company_name=v.txt_company_name
        txt_phone=v.txt_phone_number
        btn_signout=v.txt_delete_account
        initializevalues()
        return v
    }

    override fun onResume() {
        super.onResume()
        btn_signout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
            when(view!!.id){
                R.id.txt_delete_account->{
                    deletedialog()
                }
            }
    }

    private fun deletedialog() {
        var builder=AlertDialog.Builder(context!!)
        builder.setTitle("Delete Account")
                .setMessage("Are you sure you want to delete this account?")
                .setPositiveButton(android.R.string.yes) { p0, p1 ->
                    deleteaccount()
                }
                .setNegativeButton(android.R.string.no) { p0, p1 ->
                    p0.dismiss()
                }
                .show()
    }

    private fun deleteaccount() {
        FirebaseDatabase.getInstance().reference.child("Users").child(userName).removeValue().addOnCompleteListener {
            if (it.isSuccessful){
                activity!!.finish()
            }else{

            }
        }
    }

    private fun initializevalues() {
        FirebaseDatabase.getInstance().reference.child("Users")
                .child(userName)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var user=dataSnapshot.getValue(ProfileUserDetails::class.java)
                        setdata(user)
                    }
                    override fun onCancelled(dataError: DatabaseError) {

                    }
                })
    }

    private fun setdata(user: ProfileUserDetails?) {
        txt_company_name.text=user!!.Details!!.user_shopname
        txt_address.text= user.Details!!.user_address
        txt_phone.text= user.Details!!.user_contact
    }
}