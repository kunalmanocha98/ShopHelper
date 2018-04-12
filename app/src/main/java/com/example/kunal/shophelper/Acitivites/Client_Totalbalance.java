package com.example.kunal.shophelper.Acitivites;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.Models.TotalBalanceData;
import com.example.kunal.shophelper.Models.Transactiondata;
import com.example.kunal.shophelper.R;
import com.example.kunal.shophelper.Adapter.TotalBalanceAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Client_Totalbalance extends AppCompatActivity {
    RecyclerView rv_totalbalance;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    TotalBalanceAdapter adapter;
    List<TotalBalanceData> list=new ArrayList<>();
    ArrayList<PieEntry> piedata=new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference(Constants.ServiceType);
    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_totalbalance);
        rv_totalbalance = findViewById(R.id.rv_totalbalance);
        rv_totalbalance.setLayoutManager(layoutManager);
        piechart=findViewById(R.id.piechart);
        setTitle("Total Balance");
        setuppiechart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> arr = dataSnapshot.getChildren();
                while (arr.iterator().hasNext()) {
                    String namevalue = arr.iterator().next().getKey();
                    String name=(arr.iterator().next().getKey());
                    String shopnumber= (String) dataSnapshot.child(name).child("personal information").child("shopnumber").getValue();
                    String phonenumber=(String) dataSnapshot.child(name).child("personal information").child("phonenumber").getValue();
                    String totalbalance=(String) dataSnapshot.child(name).child("balance").child("total").getValue();
                    piedata.add(new PieEntry(Float.parseFloat(totalbalance),name));
                    TotalBalanceData data=new TotalBalanceData(name,shopnumber,phonenumber,totalbalance);
                    list.add(data);
                }
                recyclerview();
                setpiechart();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void setpiechart() {

        PieDataSet dataSet=new PieDataSet(piedata,Constants.ServiceType);
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(3f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data=new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.CYAN);
         piechart.setData(data);

    }

    private void setuppiechart() {
        piechart.getDescription().setEnabled(false);
        piechart.setUsePercentValues(true);
        piechart.setExtraOffsets(5,10,5,5);
        piechart.setDragDecelerationFrictionCoef(0.99f);
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);
        piechart.setTransparentCircleRadius(61f);

    }

    private void recyclerview() {
        adapter = new TotalBalanceAdapter(this,list);
        rv_totalbalance.setAdapter(adapter);
    }
}
