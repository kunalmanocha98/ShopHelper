package com.example.kunal.shophelper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kunal.shophelper.Acitivites.Client_Totalbalance;
import com.example.kunal.shophelper.Acitivites.Transaction;
import com.example.kunal.shophelper.Fonts.MyCustonTextView;
import com.example.kunal.shophelper.Models.TotalBalanceData;
import com.example.kunal.shophelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunal on 19/2/18.
 */

public class TotalBalanceAdapter extends RecyclerView.Adapter<TotalBalanceAdapter.ViewHolder> {
    Context c;
    List<TotalBalanceData> data;
    public TotalBalanceAdapter(Client_Totalbalance client_totalbalance,List<TotalBalanceData> list) {
        this.c=client_totalbalance;
        this.data=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.clientname_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.client_name.setText(data.get(position).getName());
        holder.client_shop.setText(data.get(position).getShopnumber());
        holder.client_phone.setText(data.get(position).getPhonenumber());
        holder.client_balance.setText(data.get(position).getTotalbalance());
        int balance=Integer.parseInt(data.get(position).getTotalbalance());
        if(balance<1000){
            holder.client_item_card.setCardBackgroundColor(Color.GREEN);
        }else if (balance>=1000 && balance<5000){
            holder.client_item_card.setCardBackgroundColor(Color.YELLOW);
        }else {
            holder.client_item_card.setCardBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyCustonTextView client_name,client_shop,client_phone,client_balance;
        CardView client_item_card;
        public ViewHolder(View itemView) {
            super(itemView);
            client_name=itemView.findViewById(R.id.clientname);
            client_shop=itemView.findViewById(R.id.clientshopnumber);
            client_phone=itemView.findViewById(R.id.clientphonenumber);
            client_balance=itemView.findViewById(R.id.clientbalance);
            client_item_card=itemView.findViewById(R.id.clientitemcard);

        }
    }
}
