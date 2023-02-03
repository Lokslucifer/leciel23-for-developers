package com.barebrains.leciel19;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private ListView registerList;
    private DatabaseReference dbRef;
    private RegisterListItem listItem;
    private RegisterListAdapter listAdapter;
    private ArrayList<RegisterListItem> arrayList;
    private Button button;

    public RegisterActivity() {
        //empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        button = findViewById(R.id.backbutton);
        registerList = findViewById(R.id.reg_listview);
        dbRef = FirebaseDatabase.getInstance().getReference().child("registration");
        arrayList = new ArrayList<RegisterListItem>();
        listAdapter = new RegisterListAdapter(R.layout.reg_item,arrayList,this);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot dSnap : dataSnapshot.getChildren()) {
                    try {
                        listItem = new RegisterListItem(dSnap.child("title").getValue().toString(), dSnap.child("about").getValue().toString(), dSnap.child("link").getValue().toString());
                        arrayList.add(listItem);
                    } catch(Exception e) {}
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        registerList.setAdapter(listAdapter);
        registerList.setDivider(null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
