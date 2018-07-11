package com.example.kunal.shophelper.Login.Presenter

import android.widget.Toast
import com.example.kunal.shophelper.DataManager
import com.example.kunal.shophelper.Login.View.LoginView

class LoginPresenter<V:LoginView>(dataManager: DataManager):LoginPresenterInterface<V> {
    override fun storedata(email: String, pass: String) {
        dataManager.saveEmailId(email)
        dataManager.savepassword(pass)
    }

    override fun signin(user:String, pass:String) {
        if (isvalid(user,pass)) {
            mvpView?.signin(user,pass)
        }
    }

    private fun isvalid(user: String, pass: String): Boolean {
        if (user == "") {
            mvpView?.showtoast("username field is empty")
            return false
        } else if (pass == "") {
            mvpView?.showtoast("password field is empty")
            return false
        } else {
            return true
        }
    }

    val dataManager=dataManager
    override fun checkprefs(){
        if (dataManager.getEmailId() != "0" && dataManager.getpassword() != "0"){
            mvpView?.signin(dataManager.getEmailId(),dataManager.getpassword())
        }
    }
    var mvpView: V? = null
    override fun onAttach(mvpView: V) {
        this.mvpView=mvpView
    }

    override fun onDetach() {

    }

}


