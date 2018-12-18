package com.example.kunal.shophelper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kunal.shophelper.Acitivites.ChatActivity;
import com.example.kunal.shophelper.Models.ChatdataValues;
import com.example.kunal.shophelper.R;

import java.util.List;

/**
 * Created by kunal on 11/3/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    Context c;
    List<ChatdataValues> list;
    String usr;
    private int right=0;
    private int left=1;

    public ChatAdapter(ChatActivity chatActivity, List<ChatdataValues> list2, String usr_email) {
        this.c=chatActivity;
        this.list=list2;
        this.usr=usr_email;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==left){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layoutleft, parent, false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layoutright, parent, false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_email.setText(list.get(position).getEmail());
        holder.txtmsg.setText(list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        String usrname=list.get(position).getEmail();
        if(usrname.equals(usr)){
            return right;
        }else{
            return left;
        }
        
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmsg,txt_email;
        public ViewHolder(View v) {
            super(v);
            txt_email=v.findViewById(R.id.txt_email);
            txtmsg=v.findViewById(R.id.txt_msg);
        }
    }

}
