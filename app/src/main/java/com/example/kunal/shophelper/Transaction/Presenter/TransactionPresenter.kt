package com.example.kunal.shophelper.Transaction.Presenter

import android.support.v4.app.FragmentActivity
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.Transaction.View.Transactionview

class TransactionPresenter<V:Transactionview>(dataManager: DataManager, activity: FragmentActivity):TransactionPresenterInterface<V>{
    var context=activity
    var mvpview:V?=null
    override fun onattach(mvpview: V) {
    this.mvpview=mvpview
    }

}