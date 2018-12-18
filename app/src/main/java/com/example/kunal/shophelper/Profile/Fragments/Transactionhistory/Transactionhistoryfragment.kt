package com.example.kunal.shophelper.Profile.Fragments.Transactionhistory

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.Profile.Fragments.Details.Detailsfragment
import com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Adapter.TransactionHistoryAdapter
import com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Models.*
import com.example.kunal.shophelper.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class Transactionhistoryfragment : Fragment() {

    companion object Instance {
        fun newInstance(userName: String): Transactionhistoryfragment {
            val f = Transactionhistoryfragment()
            val args = Bundle()
            args.putString(Constants.IntentExtras.USER_NAME, userName)
            f.arguments = args
            return f
        }
    }

    lateinit var recyclerview_transactions: RecyclerView
    lateinit var userName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_transaction_history, container, false)
        var args = arguments
        userName = args!!.getString(Constants.IntentExtras.USER_NAME)
        recyclerview_transactions = v.findViewById(R.id.rv_transaction_history)
        recyclerview_transactions.layoutManager = LinearLayoutManager(context)
        gettransactiondata()

        return v
    }

    private fun gettransactiondata() {
        FirebaseDatabase.getInstance().reference.child(Constants.FBRealTimeNodes.USERS)
                .child(userName)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var user = dataSnapshot.getValue(Demo::class.java)
                        if (user?.Transaction != null) {
                            var adapter = TransactionHistoryAdapter(context, user?.Transaction?.monthdata!!)
                            recyclerview_transactions.adapter = adapter
                        }
                    }

                    override fun onCancelled(dataError: DatabaseError) {

                    }
                })
    }

}