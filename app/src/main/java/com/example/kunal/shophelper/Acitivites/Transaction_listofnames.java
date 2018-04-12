package com.example.kunal.shophelper.Acitivites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.Models.Transactiondata;
import com.example.kunal.shophelper.R;
import com.example.kunal.shophelper.Adapter.TransactionAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Transaction_listofnames extends AppCompatActivity {
    RecyclerView clientcredit_rv;
    TransactionAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Transactiondata> list=new ArrayList<>();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference(Constants.ServiceType);
//    boolean switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_transaction_listofnames);
        clientcredit_rv=findViewById(R.id.clientcredit_rv);
        layoutManager= new LinearLayoutManager(this);
        clientcredit_rv.setLayoutManager(layoutManager);
        setTitle("LIST OF "+Constants.ServiceType);
//        Bundle bundle=getIntent().getExtras();
//        switcher=bundle.getBoolean("switcher");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> arr=dataSnapshot.getChildren();
                list.clear();
                while(arr.iterator().hasNext()){
                    String name=(arr.iterator().next().getKey());
                    String shopnumber= (String) dataSnapshot.child(name).child("personal information").child("shopnumber").getValue();
                    String phonenumber=(String) dataSnapshot.child(name).child("personal information").child("phonenumber").getValue();
                    String totalbalance=(String) dataSnapshot.child(name).child("balance").child("total").getValue();
                    Transactiondata data=new Transactiondata(name,shopnumber,phonenumber,totalbalance);
                     list.add(data);
                }
                recyclerview();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void recyclerview() {
        adapter=new TransactionAdapter(this,list);
        clientcredit_rv.setAdapter(adapter);
    }

}
