package com.example.kunal.shophelper.Acitivites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText iptxt_user, iptxt_pass;
    Button btn_signin, signout;
    FirebaseAuth Auth;
    String user, pass;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iptxt_user = findViewById(R.id.iptxt_user);
        iptxt_pass = findViewById(R.id.iptxt_pass);
        btn_signin = findViewById(R.id.btn_signin);
        signout = findViewById(R.id.logout);
        signout.setOnClickListener(this);
        Auth = FirebaseAuth.getInstance();
        btn_signin.setOnClickListener(this);
        checkprefs();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btn_signin.getId()) {
            user = iptxt_user.getText().toString();
            pass = iptxt_pass.getText().toString();
            if (isvalid(user, pass)) {
                showdialog();
                signin();
            }
        } else if (view.getId() == signout.getId()) {
            Constants.clearalldata(this);
            Intent i = new Intent(this, SplashActivity.class);
            startActivity(i);
        }
    }

    private void showdialog() {
        dialog=new ProgressDialog(this);
        dialog.setMessage("Logging in!");
        dialog.show();
    }

    private void signin() {
        Auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeprefs(user, pass);
//                            Toast.makeText(LoginActivity.this, "Authentication successfull", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, SelectionPage.class);
                            startActivity(i);
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                        } else {
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, SelectionPage.class);
                            startActivity(i);
                        }

                    }
                });


    }

    private Boolean isvalid(String user, String pass) {
        if (user.equals("")) {
            Toast.makeText(this, "Username or Password field empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if(pass.equals("")) {
            Toast.makeText(this, "Password field empty", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private void checkprefs() {
        user = Constants.getstoredata(Constants.USERNAME, "0", this);
        pass = Constants.getstoredata(Constants.PASSWORD, "0", this);
        if (!user.equals("0") && !pass.equals("0")) {
            signin();
            showdialog();
        }
        return;
    }

    private void storeprefs(String user, String pass) {
        Constants.storeinprefrences(Constants.USERNAME, user, this);
        Constants.storeinprefrences(Constants.PASSWORD, pass, this);
    }
}
