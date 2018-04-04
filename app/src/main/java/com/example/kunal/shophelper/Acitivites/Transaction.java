package com.example.kunal.shophelper.Acitivites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.Models.TransactionDateGetSet;
import com.example.kunal.shophelper.Adapter.DatewiseListAdapter;
import com.example.kunal.shophelper.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Transaction extends AppCompatActivity {
    String name;
    TextView tv;
    EditText edttxt;
    Button submit;
//    boolean creditdebitswitcher;
    int balance;
    List<TransactionDateGetSet> list;
    RecyclerView rv_datewisebalance;
    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
    DatewiseListAdapter adapter;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference(Constants.ServiceType);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_transactiopage);
        rv_datewisebalance =findViewById(R.id.rv_datewisebalancelist);
        rv_datewisebalance.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
//        creditdebitswitcher=bundle.getBoolean("switch");
        tv=findViewById(R.id.txt_balance);
        ref.child(name).child("balance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                balance=Integer.parseInt(dataSnapshot.child("total").getValue().toString());
                tv.setText(""+balance);
                Iterable<DataSnapshot> arr=dataSnapshot.child("datewise").getChildren();
                try {
                    while (arr.iterator().hasNext()) {
                        String date = arr.iterator().next().getKey();
                        String credit = dataSnapshot.child("datewise").child(date).child("credit").getValue().toString();
                        String debit = dataSnapshot.child("datewise").child(date).child("debit").getValue().toString();
                        String balance = dataSnapshot.child("datewise").child(date).child("balance").getValue().toString();
                        TransactionDateGetSet getset = new TransactionDateGetSet(date, credit, debit, balance);
                        list.add(getset);
                    }
                    recyclerview();
                }catch (Exception e){
                    Log.e("Exception",""+e);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        edttxt=findViewById(R.id.edttxt_amount);
        submit=findViewById(R.id.btn_submitamount);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String amount=edttxt.getText().toString();
                if(Constants.Transactiontype.equals("CREDIT")) {
                    ref.child(name).child("balance").child("datewise").child(date).child("credit").setValue("" + amount);
                    int amnt = Integer.parseInt(amount);
                    balance = balance + amnt;
                    ref.child(name).child("balance").child("datewise").child(date).child("balance").setValue("" + balance);
                    ref.child(name).child("balance").child("total").setValue(""+balance);
                    tv.setText("" + balance);
                }
                else{
                    ref.child(name).child("balance").child("datewise").child(date).child("debit").setValue("" + amount);
                    int amnt = Integer.parseInt(amount);
                    balance = balance - amnt;
                    ref.child(name).child("balance").child("datewise").child(date).child("balance").setValue("" + balance);
                    ref.child(name).child("balance").child("total").setValue(""+balance);
                    tv.setText("" + balance);
                }
            }
        });
    }
    private void recyclerview() {
        adapter=new DatewiseListAdapter(this,list);
        rv_datewisebalance.setAdapter(adapter);
    }
}
