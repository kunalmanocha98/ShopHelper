package com.example.kunal.shophelper.Transaction.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.View
import android.widget.*
import com.example.kunal.shophelper.HelperClasses.Utils
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.Transaction.Model.UserResult
import com.google.firebase.database.*

class CustomCreditTransactionDialog(context: Context, resid: Int, user: UserResult) : Dialog(context, resid), View.OnClickListener {

    var user = user
    lateinit var txt_amount: TextView
    lateinit var txt_shopname: TextView
    lateinit var txt_name: TextView
    lateinit var addpayment: CardView
    lateinit var cancelpayment: CardView
    lateinit var amount: EditText
    lateinit var modeofpayment: EditText
    lateinit var subref: DatabaseReference
    lateinit var mReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.credit_dialog_transaction)
        txt_amount = findViewById(R.id.txt_amount)
        txt_name = findViewById(R.id.txt_name)
        txt_shopname = findViewById(R.id.txt_shopname)
        addpayment = findViewById(R.id.btn_add_payment)
        cancelpayment = findViewById(R.id.btn_cancel_payment)
        amount = findViewById(R.id.edt_enter_amount)
        modeofpayment = findViewById(R.id.edt_enter_mode_of_payment)
        setdata()
    }

    private fun setdata() {
        txt_name.text = user.Details?.user_name
        txt_shopname.text = user.Details?.user_shopname
        txt_amount.text = "₹" + user.Transaction?.total_balance
        addpayment.setOnClickListener(this)
        cancelpayment.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_add_payment -> {
                adddata()
            }
            R.id.btn_cancel_payment -> {
                dismiss()
            }
        }
    }

    private fun adddata() {
        mReference= FirebaseDatabase.getInstance().reference.child("Users").child(user.Details?.user_name!!).child("Transaction")
        subref=mReference.child("monthdata").child(Utils.getcurrentmonth()+","+Utils.getcurrentyear()).child(Utils.getcurrentdateinformat("dd-MM-YYYY"))
        subref.addListenerForSingleValueEvent(eventlistener)

    }

    private fun setbalance(){
        mReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(dS: DataSnapshot) {
                var previous_total:Int
                if(dS.child("total_balance").exists()){
                    previous_total=Integer.parseInt(dS.child("total_balance").value.toString())
                }else{
                    previous_total=0
                }
                subref.child("balance").setValue((previous_total-Integer.parseInt(amount.text.toString())).toString())
                mReference.child("total_balance").setValue((previous_total-Integer.parseInt(amount.text.toString())).toString())
            }
            override fun onCancelled(dE: DatabaseError) {
            }
        })
    }
    private var eventlistener=object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if(dataSnapshot.exists()){
                if(dataSnapshot.child("credit").value.toString().equals("0")){
                    editdata(dataSnapshot)
                }else{
                    Toast.makeText(context,"Record already existed",Toast.LENGTH_SHORT).show()
                }
            }else{
                addnewdata()
            }
        }
        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

    private fun addnewdata() {
        subref.child("credit").setValue(amount.text.toString())
        subref.child("debit").setValue("0")
        setbalance()
        dismiss()
    }
    private fun editdata(dataSnapshot: DataSnapshot) {
        subref.child("credit").setValue(amount.text.toString())
        subref.child("debit").setValue(dataSnapshot.child("debit").value.toString())
        setbalance()
        dismiss()
    }


}