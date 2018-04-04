package com.example.kunal.shophelper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kunal.shophelper.Acitivites.Transaction;
import com.example.kunal.shophelper.Models.TransactionDateGetSet;
import com.example.kunal.shophelper.R;

import java.util.List;

/**
 * Created by kunal on 27/2/18.
 */

public class DatewiseListAdapter extends RecyclerView.Adapter<DatewiseListAdapter.ViewHolder> {

    Context c;
    List<TransactionDateGetSet> list;
    public DatewiseListAdapter(Transaction transaction, List<TransactionDateGetSet> data) {
        this.c=transaction;
        this.list=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_datewiselistlayout, parent, false);
        return new DatewiseListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int position) {
        h.txt_date.setText(list.get(position).getDate());
        h.txt_credit.setText(list.get(position).getCredit());
        h.txt_debit.setText(list.get(position).getDebit());
        h.txt_balance.setText(list.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date,txt_credit,txt_debit,txt_balance;
        public ViewHolder(View v) {
            super(v);
            txt_date=v.findViewById(R.id.txt_date);
            txt_credit=v.findViewById(R.id.txt_credit);
            txt_debit=v.findViewById(R.id.txt_debit);
            txt_balance=v.findViewById(R.id.txt_balance);
        }
    }
}
