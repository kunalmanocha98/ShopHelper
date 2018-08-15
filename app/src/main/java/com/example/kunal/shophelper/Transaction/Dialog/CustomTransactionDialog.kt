package com.example.kunal.shophelper.Transaction.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.kunal.shophelper.R

class CustomTransactionDialog(context: Context,resid:Int) : Dialog(context,resid) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_transaction)
    }
}