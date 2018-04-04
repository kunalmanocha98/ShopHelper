package com.example.kunal.shophelper.Acitivites;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kunal.shophelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
EditText iptxt_user,iptxt_pass;
Button btn_signin;
FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iptxt_user=findViewById(R.id.iptxt_user);
        iptxt_pass=findViewById(R.id.iptxt_pass);
        btn_signin=findViewById(R.id.btn_signin);
        Auth=FirebaseAuth.getInstance();
        btn_signin.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==btn_signin.getId()){
            signin();
        }
    }
    private void signin() {
        String user=iptxt_user.getText().toString();
        String pass=iptxt_pass.getText().toString();
        Auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication successfull",
                                    Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this,SelectionPage.class);
                            startActivity(i);
                            FirebaseUser user = Auth.getCurrentUser();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this,SelectionPage.class);
                            startActivity(i);
                        }

                    }
                });


    }
}
