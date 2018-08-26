package com.appemergencias;

import android.content.Context;
import android.content.Intent;
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

import com.appemergencias.Adapters.MessageButtonsConfigurationAdapter;


public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back, add;
    RecyclerView buttons;
    MessageButtonsConfigurationAdapter adapter;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        back = findViewById(R.id.back);

        add = findViewById(R.id.add);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpButtonsRecyclerView();

    }


    private void setUpButtonsRecyclerView(){
        buttons = findViewById(R.id.rv);
        adapter = new MessageButtonsConfigurationAdapter(MainActivity.buttons);
        buttons.setLayoutManager(new LinearLayoutManager(this));
        buttons.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
        }
    }

    public LinearLayout createLinearLayout(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView tv = new TextView(this);
        tv.setText("Hola");
        layout.addView(tv);
        return layout;
    }
}