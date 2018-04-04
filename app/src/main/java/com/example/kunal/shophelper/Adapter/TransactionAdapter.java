package com.example.kunal.shophelper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kunal.shophelper.Acitivites.Transaction;
import com.example.kunal.shophelper.Acitivites.Transaction_listofnames;
import com.example.kunal.shophelper.R;

import java.util.ArrayList;

/**
 * Created by kunal on 12/2/18.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    ArrayList<String> data;
    Context c;
//    Boolean creditdebitswitcher;
    public TransactionAdapter(Transaction_listofnames transactionlistofnames, ArrayList<String> data) {
    this.data=data;
    this.c= transactionlistofnames;
//    creditdebitswitcher=switcher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.clientname_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txt_name.setText(data.get(position));
        holder.rl_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,Transaction.class);
                i.putExtra("name",data.get(position).toString());
//                i.putExtra("switch",creditdebitswitcher);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name;
        RelativeLayout rl_clickable;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            rl_clickable=itemView.findViewById(R.id.rl_clickable);
        }
    }
}
