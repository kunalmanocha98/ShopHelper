package com.example.kunal.shophelper.Login

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.kunal.shophelper.Acitivites.SelectionPage
import com.example.kunal.shophelper.Dashboard.DashboardActivity
import com.example.kunal.shophelper.Login.Presenter.LoginPresenter
import com.example.kunal.shophelper.Login.View.LoginView
import com.example.kunal.shophelper.R
import com.example.kunal.shophelper.ShopHelperApplication
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginView{
    override fun showtoast(stringtoshow: String) {
        Toast.makeText(this,stringtoshow,Toast.LENGTH_SHORT).show()
    }

    override fun signin(email:String,pass:String) {
        val auth= FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, object :OnCompleteListener<AuthResult>{
            override fun onComplete(task: Task<AuthResult>) {
                if (task.isSuccessful){
                    mypresenter.storedata(email,pass)
                    val i=Intent(this@LoginActivity,DashboardActivity::class.java)
                    startActivity(i)
                    finish()
                }else{

                    showtoast("authentication failed")
                }
            }

        })
    }

    lateinit var mypresenter:LoginPresenter<LoginView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val dataManager = (application as ShopHelperApplication).getDataManager()
        mypresenter= LoginPresenter(dataManager)
        mypresenter.onAttach(this)
        mypresenter.checkprefs()
    }

    override fun onResume() {
        super.onResume()
        btn_signin.setOnClickListener{
           mypresenter.signin(iptxt_user.text.toString(),iptxt_pass.text.toString())
        }
    }
}