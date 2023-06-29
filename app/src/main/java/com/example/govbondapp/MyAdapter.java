package com.example.govbondapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList email_id, password_id, name_id;

    public MyAdapter(Context context, ArrayList name_id,
                     ArrayList email_id, ArrayList password_id) {
        this.mContext = context;
        this.email_id = email_id;
        this.password_id = password_id;
        this.name_id = name_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.password_id.setText(String.valueOf(password_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email_id, name_id, password_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_id = itemView.findViewById(R.id.txtName);
            email_id = itemView.findViewById(R.id.txtEmail);
            password_id = itemView.findViewById(R.id.txtPassword);
        }
    }
}
