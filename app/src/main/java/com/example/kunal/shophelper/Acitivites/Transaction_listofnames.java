package com.example.kunal.shophelper.Acitivites;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    List<Transactiondata> list = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference(Constants.ServiceType);
//    boolean switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_transaction_listofnames);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("LIST OF "+Constants.ServiceType);

        clientcredit_rv = findViewById(R.id.clientcredit_rv);
        layoutManager = new LinearLayoutManager(this);
        clientcredit_rv.setLayoutManager(layoutManager);
//        setTitle("LIST OF " + Constants.ServiceType);
//        Bundle bundle=getIntent().getExtras();
//        switcher=bundle.getBoolean("switcher");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> arr = dataSnapshot.getChildren();
                list.clear();
                while (arr.iterator().hasNext()) {
                    String name = (arr.iterator().next().getKey());
                    String shopnumber = (String) dataSnapshot.child(name).child("personal information").child("shopnumber").getValue();
                    String phonenumber = (String) dataSnapshot.child(name).child("personal information").child("phonenumber").getValue();
                    String totalbalance = (String) dataSnapshot.child(name).child("balance").child("total").getValue();
                    Transactiondata data = new Transactiondata(name, shopnumber, phonenumber, totalbalance);
                    list.add(data);
                }
                recyclerview();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusearch, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
//                Toast.makeText(Transaction_listofnames.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
//                Toast.makeText(Transaction_listofnames.this, newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void recyclerview() {
        adapter = new TransactionAdapter(this, list);
        clientcredit_rv.setAdapter(adapter);
    }

}
