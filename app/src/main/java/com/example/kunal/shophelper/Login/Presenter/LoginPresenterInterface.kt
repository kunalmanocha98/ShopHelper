package com.example.kunal.shophelper.Login.Presenter

import com.example.kunal.shophelper.Login.View.LoginView

interface LoginPresenterInterface<in V:LoginView> {
    fun onAttach(mvpView: V)

    fun onDetach()
    fun checkprefs()
    fun signin(user:String,pass:String)
    fun storedata(email: String, pass: String)
}