package com.example.kunal.shophelper.Acitivites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kunal.shophelper.R;
import com.example.kunal.shophelper.Adapter.TotalBalanceAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Client_Totalbalance extends AppCompatActivity {
    RecyclerView rv_totalbalance;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    TotalBalanceAdapter adapter;
    ArrayList<String> name, balance;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("CLIENTS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_totalbalance);
        rv_totalbalance = findViewById(R.id.rv_totalbalance);
        rv_totalbalance.setLayoutManager(layoutManager);
        name = new ArrayList<>();
        balance = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> arr = dataSnapshot.getChildren();
                while (arr.iterator().hasNext()) {
                    String namevalue = arr.iterator().next().getKey();
                    name.add(namevalue);
                    balance.add(dataSnapshot.child(namevalue).child("balance").getValue().toString());
                }
                recyclerview();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void recyclerview() {
        adapter = new TotalBalanceAdapter(this, name, balance);
        rv_totalbalance.setAdapter(adapter);
    }
}
