package com.appemergencias.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appemergencias.MainActivity;
import com.appemergencias.R;

import java.util.ArrayList;

/**
 * Created by rober on 25/08/2018.
 */

public class MessageButtonsConfigurationAdapter extends RecyclerView.Adapter<MessageButtonsConfigurationAdapter.ViewHolder> {

    ArrayList<String> listButtons;
    Context context;
    public MessageButtonsConfigurationAdapter (ArrayList<String> listButtons, Context context) {
        this.listButtons = listButtons;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_buttons_configuration_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageButtonsConfigurationAdapter.ViewHolder holder, final int position) {
        if(position == 0){
            holder.mensaje.setBackgroundTintList(context.getResources().getColorStateList(R.color.estoyBien));
            holder.del.setVisibility(View.GONE);
        }
        if(position == 1){
            holder.mensaje.setBackgroundTintList(context.getResources().getColorStateList(R.color.estoyMal));
            holder.del.setVisibility(View.GONE);
        }
        holder.mensaje.setText(listButtons.get(position));
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
        TextView mensaje;
        ImageButton del;

        public ViewHolder(View itemView) {
            super(itemView);
            mensaje = itemView.findViewById(R.id.mensaje);
            del = itemView.findViewById(R.id.eliminate);
        }
    }
}

