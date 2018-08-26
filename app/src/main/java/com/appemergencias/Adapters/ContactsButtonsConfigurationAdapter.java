package com.appemergencias.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appemergencias.R;

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
    public void onBindViewHolder(@NonNull ContactsButtonsConfigurationAdapter.ViewHolder holder, final int position) {
        holder.contact.setText(listButtons.get(position));
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtons.remove(position);
                notifyDataSetChanged();
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

        public ViewHolder(View itemView) {
            super(itemView);
            contact = itemView.findViewById(R.id.contact);
            del = itemView.findViewById(R.id.eliminate);
        }
    }
}
