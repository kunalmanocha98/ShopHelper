package com.example.kunal.shophelper.Transaction.Presenter

import android.support.v4.app.FragmentActivity
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.HelperClasses.Constants
import com.example.kunal.shophelper.Transaction.Model.UserResult
import com.example.kunal.shophelper.Transaction.View.Transactionview
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TransactionPresenter<V : Transactionview>(dataManager: DataManager, activity: FragmentActivity) : TransactionPresenterInterface<V> {
    var context = activity
    var mvpview: V? = null
    var mRef= FirebaseDatabase.getInstance().reference
    var list= ArrayList<UserResult>()



    override fun onattach(mvpview: V) {
        this.mvpview = mvpview
    }

    override fun getuserdata() {

        mRef.child(Constants.FBRealTimeNodes.USERS).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot){
                list.clear()
                var iterator=datasnapshot.children.iterator()
                while (iterator.hasNext()){
                    var iter=iterator.next()
                    var userDetails=iter.getValue(UserResult::class.java)
                    list.add(userDetails!!)
                }
                mvpview?.setrecyclerview(list)
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}