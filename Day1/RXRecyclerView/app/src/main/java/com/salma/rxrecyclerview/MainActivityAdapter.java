package com.salma.rxrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {
    private Context context;
    ArrayList<String>capital;
    ArrayList<String>small;
    public MainActivityAdapter(Context context, ArrayList<String>capital,
                               ArrayList<String>small) {
        this.context = context;
        this.capital=capital;
        this.small=small;
    }

    @NonNull
    @Override
    public MainActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainActivityAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_row, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.itemSmall.setText(small.get(i));
        myViewHolder.itemCapital.setText(capital.get(i));
    }

    @Override
    public int getItemCount() {
        return small.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemCapital;
        TextView itemSmall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCapital=itemView.findViewById(R.id.capital);
            itemSmall=itemView.findViewById(R.id.small);
        }
    }

}
