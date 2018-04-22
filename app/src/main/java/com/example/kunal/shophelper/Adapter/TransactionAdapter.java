package com.example.kunal.shophelper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kunal.shophelper.Acitivites.Transaction;
import com.example.kunal.shophelper.Acitivites.Transaction_listofnames;
import com.example.kunal.shophelper.Fonts.MyCustonTextView;
import com.example.kunal.shophelper.Models.Transactiondata;
import com.example.kunal.shophelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunal on 12/2/18.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> implements Filterable{
    List<Transactiondata> data;
    List<Transactiondata> filterdata;
    Context c;
//    Boolean creditdebitswitcher;
    public TransactionAdapter(Transaction_listofnames transactionlistofnames, List<Transactiondata> data) {
    this.data=data;
    this.c= transactionlistofnames;
    this.filterdata=data;
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
        final Transactiondata filterdata2 = filterdata.get(position);
        holder.client_name.setText(filterdata2.getName());
        holder.client_shop.setText(filterdata2.getShopnumber());
        holder.client_phone.setText(filterdata2.getPhonenumber());
        holder.client_balance.setText(filterdata2.getTotalbalance());
        int balance=Integer.parseInt(filterdata2.getTotalbalance());
        if(balance<1000){
            holder.client_item_card.setCardBackgroundColor(Color.GREEN);
        }else if (balance>=1000 && balance<5000){
            holder.client_item_card.setCardBackgroundColor(Color.YELLOW);
        }else {
            holder.client_item_card.setCardBackgroundColor(Color.RED);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,Transaction.class);
                i.putExtra("name",filterdata2.getName().toString());
//                i.putExtra("switch",creditdebitswitcher);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterdata.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterdata=data;
                } else {
                    List<Transactiondata> filteredList = new ArrayList<>();
                    for (Transactiondata i: data) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (i.getName().toLowerCase().contains(charString.toLowerCase()) || i.getPhonenumber().contains(charString.toLowerCase())) {
                            filteredList.add(i);
                        }
                    }

                    filterdata = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdata;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterdata = (ArrayList<Transactiondata>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
