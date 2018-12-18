package com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Models.DemoTransactionData
import com.example.kunal.shophelper.R
import kotlin.collections.HashMap

class TransactionHistoryAdapter(context: Context?, monthdata: HashMap<String, HashMap<String, DemoTransactionData>>) : RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {
var monthdata=monthdata
    var context=context
    var list=ArrayList<String>(monthdata.keys)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_transaction_history_month,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_month_name.text=list[position]
        holder.rv_month_wise_data.layoutManager=LinearLayoutManager(context)
        holder.rv_month_wise_data.adapter=TransactionHistorySubAdapter(context, monthdata[list[position]])
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val txt_month_name=itemView.findViewById<TextView>(R.id.txt_month_name)
        var rv_month_wise_data=itemView.findViewById<RecyclerView>(R.id.recycler_view_month_data)
    }
}