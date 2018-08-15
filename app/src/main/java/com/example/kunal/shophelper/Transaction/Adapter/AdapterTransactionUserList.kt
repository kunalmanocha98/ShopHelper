package com.example.kunal.shophelper.Transaction.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.Transaction.Dialog.CustomTransactionDialog

class AdapterTransactionUserList(context: Context) : RecyclerView.Adapter<AdapterTransactionUserList.ViewHolder>() {
    var mcontext=context

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn_credit.setOnClickListener {
            var dialog = CustomTransactionDialog(mcontext,R.style.MyDialog)
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)
            dialog.show()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 12
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var btn_credit=view.findViewById<TextView>(R.id.txt_btn_credit)
        var btn_dbit=view.findViewById<TextView>(R.id.txt_btn_debit)

    }

}