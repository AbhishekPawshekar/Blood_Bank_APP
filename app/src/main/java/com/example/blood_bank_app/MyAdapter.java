package com.example.blood_bank_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import  androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Donate> donate;
    String s;

    public MyAdapter(Context c, ArrayList<Donate> d) {
        context = c;
        donate = d;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.user_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.name.setText(donate.get(position).getName());
        holder.bloodtype.setText(donate.get(position).getBlood_type());
        long mob = donate.get(position).getMob();
        holder.number.setText("" + mob);

        holder.btncal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + donate.get(holder.getAdapterPosition()).getMob()));

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return donate.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder

    {
        TextView name,bloodtype,number;
        Button btncal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            bloodtype=(TextView)itemView.findViewById(R.id.bloodtype);
            btncal=(Button)itemView.findViewById(R.id.bttncall);
            number=(TextView)itemView.findViewById(R.id.num);

        }
    }
}

