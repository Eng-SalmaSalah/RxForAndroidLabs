package com.salma.evenindecesrx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder> {
    ArrayList<Integer> receivedList;
    Context context;

    public NumbersAdapter(Context context, ArrayList<Integer> receivedList) {
        this.context=context;
        this.receivedList=receivedList;
    }


    @NonNull
    @Override
    public NumbersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NumbersAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.number.setText(receivedList.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return receivedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.evenNum);
        }
    }
}
