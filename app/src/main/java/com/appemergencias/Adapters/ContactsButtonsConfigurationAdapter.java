package com.appemergencias.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appemergencias.ConfigurationActivity;
import com.appemergencias.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsButtonsConfigurationAdapter extends RecyclerView.Adapter<ContactsButtonsConfigurationAdapter.ViewHolder> {

    ArrayList<String> listButtons;
    Context context;
    public ContactsButtonsConfigurationAdapter (ArrayList<String> listButtons, Context context) {
        this.listButtons = listButtons;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsButtonsConfigurationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_buttons_configuration_list_items,parent,false);
        return new ContactsButtonsConfigurationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsButtonsConfigurationAdapter.ViewHolder holder, final int position) {

        ConfigurationActivity.mUserReference.child("FacebookInfo").child("Contacts").child(listButtons.get(position)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    holder.selected = true;
                    holder.itemView.setBackgroundColor(Color.GREEN);
                    holder.del.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.contact.setText(listButtons.get(position));
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.selected){
                    holder.itemView.setBackgroundColor(Color.GREEN);
                    holder.selected = true;
                    holder.del.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp);
                    ConfigurationActivity.mUserReference.child("FacebookInfo").child("Contacts").child(listButtons.get(position)).setValue(ConfigurationActivity.friendUids.get(position));
                }
                else{
                    holder.itemView.setBackgroundColor(Color.WHITE);
                    holder.selected = false;
                    holder.del.setBackgroundResource(R.drawable.ic_add_circle_black_24dp);
                    ConfigurationActivity.mUserReference.child("FacebookInfo").child("Contacts").child(listButtons.get(position)).removeValue();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return listButtons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contact;
        ImageButton del;
        boolean selected = false;

        public ViewHolder(View itemView) {
            super(itemView);
            contact = itemView.findViewById(R.id.contact);
            del = itemView.findViewById(R.id.eliminate);
        }
    }
}
