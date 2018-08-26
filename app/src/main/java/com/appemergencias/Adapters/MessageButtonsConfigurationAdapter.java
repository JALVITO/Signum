package com.appemergencias.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appemergencias.ConfigurationActivity;
import com.appemergencias.MainActivity;
import com.appemergencias.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
    public void onBindViewHolder(@NonNull final MessageButtonsConfigurationAdapter.ViewHolder holder, final int position) {
        if(position == 0){
            holder.mensaje.setBackgroundTintList(context.getResources().getColorStateList(R.color.estoyBien));
            holder.del.setVisibility(View.GONE);
        }
        else if(position == 1){
            holder.mensaje.setBackgroundTintList(context.getResources().getColorStateList(R.color.estoyMal));
            holder.del.setVisibility(View.GONE);
        }
        else{
            holder.mensaje.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("position", position+ "");
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Cambiar Mensaje");
                    alert.setMessage("Cambiara el mensaje predefinido por el escrito en el cuadro de texto.");

                    final EditText input = new EditText(context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    input.setLayoutParams(params);
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    alert.setView(input);
                    alert.setPositiveButton("Cambiar", null);
                    alert.setNegativeButton("Cancelar", null);

                    final AlertDialog dialog = alert.create();
                    dialog.show();

                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listButtons.set(position, input.getText().toString());
                            holder.mensaje.setText(input.getText().toString());
                            ConfigurationActivity.mUserReference.child("Messages").child(String.valueOf(position)).setValue(listButtons.get(position));
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
        holder.mensaje.setText(listButtons.get(position));
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listButtons.remove(position);
                updateFirebaseMessages();
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

    private void updateFirebaseMessages(){
        int len = listButtons.size();
        for(int i = 0; i < len; i++){
            ConfigurationActivity.mUserReference.child("Messages").child(String.valueOf(i)).setValue(listButtons.get(i));
        }
        ConfigurationActivity.mUserReference.child("Messages").child(String.valueOf(len)).removeValue();
    }
}

