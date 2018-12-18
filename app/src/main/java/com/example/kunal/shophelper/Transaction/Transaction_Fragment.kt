package com.example.kunal.shophelper.Transaction

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.ShopHelperApplication
import com.example.kunal.shophelper.Transaction.Adapter.AdapterTransactionUserList
import com.example.kunal.shophelper.Transaction.Model.UserResult
import com.example.kunal.shophelper.Transaction.Presenter.TransactionPresenter
import com.example.kunal.shophelper.Transaction.View.Transactionview

class Transaction_Fragment: Fragment(),Transactionview {


    lateinit var rv_userlist:RecyclerView
    lateinit var mypresenter:TransactionPresenter<Transactionview>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v= inflater.inflate(R.layout.fragment_transaction,container,false)
        var dataManager=(activity!!.application as ShopHelperApplication).getDataManager()

        mypresenter= TransactionPresenter(dataManager, activity!!)
        mypresenter.onattach(this)

        rv_userlist=v.findViewById(R.id.rv_transaction_user_list)
        rv_userlist.layoutManager=LinearLayoutManager(activity)
        mypresenter.getuserdata()

        return v
    }
    override fun setrecyclerview(list: List<UserResult>) {
        var adapter=AdapterTransactionUserList(activity!!,list)
        rv_userlist.adapter=adapter
    }

}