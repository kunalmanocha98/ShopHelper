package com.example.kunal.shophelper.Transaction.Presenter

import com.example.kunal.shophelper.Transaction.View.Transactionview

interface TransactionPresenterInterface<in V:Transactionview>{
    fun onattach(mvpview:V)
    fun getuserdata()
}