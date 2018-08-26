package com.appemergencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.appemergencias.Adapters.ContactsButtonsConfigurationAdapter;
import com.appemergencias.Adapters.MessageButtonsConfigurationAdapter;

import java.util.ArrayList;


public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back, add, add2;
    RecyclerView buttons, buttons2;
    MessageButtonsConfigurationAdapter adapter;
    ContactsButtonsConfigurationAdapter adapter2;
    Context context = this;
    public static ArrayList<String> buttonsArraylist2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        buttonsArraylist2.add("Juan");
        buttonsArraylist2.add("Pedro");
        buttonsArraylist2.add("Roberto");

        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        add2 = findViewById(R.id.add2);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
        add2.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpButtonsRecyclerView();

    }


    private void setUpButtonsRecyclerView() {
        //Mensajes
        buttons = findViewById(R.id.rv);
        adapter = new MessageButtonsConfigurationAdapter(MainActivity.buttons, this);
        buttons.setLayoutManager(new LinearLayoutManager(this));
        buttons.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //Contactos
        buttons2 = findViewById(R.id.rv2);
        adapter2 = new ContactsButtonsConfigurationAdapter(buttonsArraylist2, this);
        buttons2.setLayoutManager(new LinearLayoutManager(this));
        buttons2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.add:
                if(MainActivity.buttons.size() < 10){
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Agregar mensaje");
                    alert.setMessage("Este mensaje sera parte de sus mensajes por default al estar en una emergencia.");

                    final EditText input = new EditText(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    input.setLayoutParams(params);
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    alert.setView(input);
                    alert.setPositiveButton("Agregar", null);
                    alert.setNegativeButton("Cancelar", null);

                    final AlertDialog dialog = alert.create();
                    dialog.show();

                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainActivity.buttons.add(input.getText().toString());
                            writeNewLocallyMessages();
                            dialog.dismiss();
                        }
                    });
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Agregar mensaje");
                    alert.setMessage("Lo sentimos, solo puede tener 10 mensajes.");
                    alert.setPositiveButton("Okay", null);
                }

                break;
            case R.id.add2:
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Agregar contacto");
                alert.setMessage("Este contacto sera alertado al estar en una emergencia.");

                final EditText input = new EditText(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(params);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                alert.setView(input);
                alert.setPositiveButton("Agregar", null);
                alert.setNegativeButton("Cancelar", null);

                final AlertDialog dialog = alert.create();
                dialog.show();

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonsArraylist2.add(input.getText().toString());
                        dialog.dismiss();
                    }
                });
                break;
        }
    }
    public void writeNewLocallyMessages(){
        SharedPreferences settings = getSharedPreferences("com.appemergencias", MODE_PRIVATE);

        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        for(int i=0; i<MainActivity.buttons.size();i++)
            editor.putString(String.valueOf(i), MainActivity.buttons.get(i));

        editor.apply();
    }
}