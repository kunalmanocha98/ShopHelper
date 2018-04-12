package com.example.kunal.shophelper.Acitivites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.kunal.shophelper.Adapter.ChatAdapter;
import com.example.kunal.shophelper.HelperClasses.Constants;
import com.example.kunal.shophelper.Models.ChatdataValues;
import com.example.kunal.shophelper.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by kunal on 11/3/18.
 */

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
    ChatAdapter adapter;
    ImageButton btn_send;
    EditText edt_msg;
    DatabaseReference root;
    String temp_key,usrPhone,databasechild;
    List<ChatdataValues> list=new ArrayList<>();
    List<ChatdataValues> list2=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        databasechild=getIntent().getExtras().getString("child");
        recyclerView=findViewById(R.id.rv_chat);
        recyclerView.setLayoutManager(layoutManager);
        usrPhone= Constants.getstoredata(Constants.PhoneNumber,"default",this);
        root=FirebaseDatabase.getInstance().getReference("CHAT").child(databasechild);
        btn_send=findViewById(R.id.btn_send);
        edt_msg=findViewById(R.id.msg_input);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference message_root = root.child(temp_key);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("msg", edt_msg.getText().toString());
                map2.put("email",usrPhone);
                message_root.updateChildren(map2);
                edt_msg.setText("");
            }
        });
    root.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            append(dataSnapshot);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            append(dataSnapshot);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

    private void append(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        list2.clear();
        while (i.hasNext()) {
            String  chat_email = (String) ((DataSnapshot) i.next()).getValue();
            String  chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            ChatdataValues v=new ChatdataValues();
            v.setEmail(chat_email);
            v.setMessage(chat_msg);
            list.add(v);
        }
        list2.addAll(list);
        adapter=new ChatAdapter(this,list2,usrPhone);
        recyclerView.setAdapter(adapter);
        int position=recyclerView.getAdapter().getItemCount()-1;
        recyclerView.scrollToPosition(position);
//        rv.smoothScrollToPosition(position);
        adapter.notifyDataSetChanged();


    }
}
