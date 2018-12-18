package com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Models.DemoTransactionData
import com.example.kunal.shophelper.R
import kotlinx.android.synthetic.main.fragment_item_transaction_history_date.view.*
import java.util.HashMap

class TransactionHistorySubAdapter(context: Context?, datedata: HashMap<String, DemoTransactionData>?) : RecyclerView.Adapter<TransactionHistorySubAdapter.ViewHolder>() {
    var context=context
    var datedata=datedata
    var list=ArrayList<String>(datedata!!.keys)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_transaction_date.text=list[position]
        holder.txt_balance_amount.text= "₹"+datedata!![list[position]]!!.balance
        holder.txt_credit_amount.text= "₹"+datedata!![list[position]]!!.credit
        holder.txt_debit_amount.text= "₹"+datedata!![list[position]]!!.debit

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistorySubAdapter.ViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_transaction_history_date,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        var txt_transaction_date=itemView.txt_transaction_date
        var txt_balance_amount=itemView.txt_balance_amount
        var txt_credit_amount=itemView.txt_credit_amount
        var txt_debit_amount=itemView.txt_debit_amount
    }

}