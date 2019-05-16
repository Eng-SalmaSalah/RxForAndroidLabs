package com.salma.filterusersrx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder>  {
    private Context context;
    private ArrayList<String>femalesList;
    public UsersAdapter(Context context, ArrayList<String> femalesList) {
        this.context=context;
        this.femalesList=femalesList;
    }

    @NonNull
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UsersAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder myViewHolder, int i) {
        Log.i("name",femalesList.get(i));
        myViewHolder.user.setText(femalesList.get(i));
    }

    @Override
    public int getItemCount() {
        return femalesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView user;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user=itemView.findViewById(R.id.user);
        }
    }
}
