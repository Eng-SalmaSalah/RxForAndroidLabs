package com.salma.searchrx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {

    List<String> words;
    Context context;

    public WordAdapter(Context context,List<String> words) {
        this.words = words;
        this.context = context;
    }

    @NonNull
    @Override
    public WordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.custom_row, viewGroup, false);
        return new WordAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(words.get(i));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.word);
        }
    }


}
