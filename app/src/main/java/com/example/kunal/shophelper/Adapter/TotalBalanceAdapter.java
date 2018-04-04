package com.example.kunal.shophelper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kunal.shophelper.Acitivites.Client_Totalbalance;
import com.example.kunal.shophelper.R;

import java.util.ArrayList;

/**
 * Created by kunal on 19/2/18.
 */

public class TotalBalanceAdapter extends RecyclerView.Adapter<TotalBalanceAdapter.ViewHolder> {
    Context c;
    ArrayList<String> name,balance;
    public TotalBalanceAdapter(Client_Totalbalance client_totalbalance, ArrayList<String> name, ArrayList<String> balance) {
        this.c=client_totalbalance;
        this.name=name;
        this.balance=balance;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.totalbalance_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(name.get(position));
        holder.tv_balance.setText(balance.get(position));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_balance;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_totalbalanceclientname);
            tv_balance=itemView.findViewById(R.id.tv_totalbalanceclientbalance);
        }
    }
}
