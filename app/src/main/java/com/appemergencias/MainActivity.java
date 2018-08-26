package com.appemergencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appemergencias.Adapters.MessageButtonMain;
import com.appemergencias.Adapters.MessageButtonsConfigurationAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.appemergencias.R.drawable.baseline_settings_black_18dp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton conf;
    LinearLayout main_page, config_page;

    RecyclerView buttonsRV;
    MessageButtonMain adapter;

    Context context = this;
    public static ArrayList<String> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readNewLocallyMessages();

        //buttons.add("Estoy bien");
        //buttons.add("Estoy mal");
        //buttons.add("Nombre");

        main_page = findViewById(R.id.main_page);

        conf = findViewById(R.id.configuration);
        conf.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpButtonsRecyclerView();

    }

    private void setUpButtonsRecyclerView(){
        buttonsRV = findViewById(R.id.rvMain);
        adapter = new MessageButtonMain(buttons, context);
        buttonsRV.setLayoutManager(new LinearLayoutManager(this));
        buttonsRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.configuration:
                startActivity(new Intent(context, ConfigurationActivity.class));
                //main_page.setVisibility(View.GONE);
                //config_page.setVisibility(View.VISIBLE);
                writeNewLocallyMessages();
                break;
        }
    }


    public void writeNewLocallyMessages(){
        SharedPreferences settings = getSharedPreferences("com.appemergencias", MODE_PRIVATE);

        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        for(int i=0; i<buttons.size();i++)
            editor.putString(String.valueOf(i), buttons.get(i));

        editor.apply();
    }
    public void readNewLocallyMessages(){
        SharedPreferences settings = getSharedPreferences("com.appemergencias", MODE_PRIVATE);

        // Reading from SharedPreferences
        String s = null;
        int i=0;
        while(!(s=settings.getString(String.valueOf(i), "")).isEmpty()){
            buttons.add(s);
            i++;
        }
    }
}
