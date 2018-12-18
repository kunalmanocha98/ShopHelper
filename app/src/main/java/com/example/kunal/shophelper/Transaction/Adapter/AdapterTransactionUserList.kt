package com.example.kunal.shophelper.Transaction.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.Profile.ProfileActivity
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.Transaction.Dialog.CustomCreditTransactionDialog
import com.example.kunal.shophelper.Transaction.Dialog.CustomDebitTransactionDialog
import com.example.kunal.shophelper.Transaction.Model.UserDetails
import com.example.kunal.shophelper.Transaction.Model.UserResult

class AdapterTransactionUserList(context: Context,list:List<UserResult>) : RecyclerView.Adapter<AdapterTransactionUserList.ViewHolder>() {
    var mcontext=context
    var list=list

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firm_name.text= list[position].Details?.user_shopname
        holder.client_name.text=list[position].Details?.user_name
        if (list[position].Transaction?.total_balance == null){
            holder.amount.text="₹0"
        }else{
            holder.amount.text="₹"+list[position].Transaction?.total_balance
        }
        holder.btn_credit.setOnClickListener {
            var dialog = CustomCreditTransactionDialog(mcontext,R.style.MyDialog,list[position])
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)
            dialog.show()
        }
        holder.btn_debit.setOnClickListener {
            var dialog = CustomDebitTransactionDialog(mcontext,R.style.MyDialog,list[position])
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)
            dialog.show()
        }
        holder.indicator_wrapper.setOnClickListener {
            openprofile(list[position].Details)
        }
    }

    private fun openprofile(user: UserDetails?) {
        val i =Intent(mcontext,ProfileActivity::class.java)
        i.putExtra(Constants.IntentExtras.USER_NAME,user?.user_name)
        i.putExtra(Constants.IntentExtras.USER_SHOPNAME,user?.user_shopname)
        i.putExtra(Constants.IntentExtras.USER_PIC_URL,user?.user_profile_pic_url)
        mcontext.startActivity(i)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var btn_credit=view.findViewById<TextView>(R.id.txt_btn_credit)
        var btn_debit=view.findViewById<TextView>(R.id.txt_btn_debit)
        var indicator_wrapper=view.findViewById<RelativeLayout>(R.id.wrapper_txt_indicator)
        var firm_name=view.findViewById<TextView>(R.id.txt_shop_name)
        var client_name=view.findViewById<TextView>(R.id.txt_client_name)
        var amount=view.findViewById<TextView>(R.id.txt_amount_credit_list)
    }

}