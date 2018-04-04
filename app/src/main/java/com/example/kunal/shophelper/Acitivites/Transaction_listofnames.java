package com.example.kunal.shophelper.Acitivites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.R;
import com.example.kunal.shophelper.Adapter.TransactionAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Transaction_listofnames extends AppCompatActivity {
    RecyclerView clientcredit_rv;
    TransactionAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> data=new ArrayList<>();
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
//        Bundle bundle=getIntent().getExtras();
//        switcher=bundle.getBoolean("switcher");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> arr=dataSnapshot.getChildren();
                data.clear();
                while(arr.iterator().hasNext()){
                    data.add(arr.iterator().next().getKey());
                }
                recyclerview();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void recyclerview() {
        adapter=new TransactionAdapter(this,data);
        clientcredit_rv.setAdapter(adapter);
    }

}
