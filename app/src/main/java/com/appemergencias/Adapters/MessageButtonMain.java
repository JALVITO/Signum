package com.appemergencias.Adapters;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appemergencias.MainActivity;
import com.appemergencias.MyService;
import com.appemergencias.R;

import java.util.ArrayList;

/**
 * Created by rober on 25/08/2018.
 */

public class MessageButtonMain extends RecyclerView.Adapter<MessageButtonMain.ViewHolder> {

    ArrayList<String> listButtons;
    Context context;
    public MessageButtonMain(ArrayList<String> listButtons, Context context) {
        this.listButtons = listButtons;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_buttons_main_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageButtonMain.ViewHolder holder, final int position) {
        holder.mensaje.setText(listButtons.get(position));
        if(position == 0){
            holder.mensaje.setBackgroundTintList(context.getResources().getColorStateList(R.color.estoyBien));
        }
        if(position == 1){
            holder.mensaje.setBackgroundTintList(context.getResources().getColorStateList(R.color.estoyMal));
        }
        holder.mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.numberOfEmergencyMessage = position;
                context.startService(new Intent(context, MyService.class));
            }
        });
    }
    @Override
    public int getItemCount() {
        return listButtons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mensaje;

        public ViewHolder(View itemView) {
            super(itemView);
            mensaje = itemView.findViewById(R.id.mensaje);
        }
    }
}

