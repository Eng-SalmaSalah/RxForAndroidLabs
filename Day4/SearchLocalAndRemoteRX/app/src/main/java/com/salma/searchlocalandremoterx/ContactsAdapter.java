package com.salma.searchlocalandremoterx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    List<Contact> contacts;
    Context context;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.custom_row, viewGroup, false);
        return new ContactsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.contactName.setText(contacts.get(i).getName());
        myViewHolder.contactNumber.setText(contacts.get(i).getPhone());
        Glide.with(context).load(contacts.get(i).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(myViewHolder.contactImage);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView contactName;
        TextView contactNumber;
        CircleImageView contactImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName=itemView.findViewById(R.id.contactNameTV);
            contactNumber=itemView.findViewById(R.id.contactNumTV);
            contactImage=itemView.findViewById(R.id.contactImage);

        }
    }


}
