package com.example.blood_bank_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class data_ret extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Donate> list;
    MyAdapter adapter;
    Button btncal;
    TextView number;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ret);
        recyclerView=(RecyclerView)findViewById(R.id.myrecyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Donate>();




        reference= FirebaseDatabase.getInstance().getReference().child("Donate");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Donate d=dataSnapshot1.getValue(Donate.class);
                    list.add(d);
                }
                adapter=new MyAdapter(data_ret.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}