package com.appemergencias;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appemergencias.Adapters.MessageButtonMain;
import com.appemergencias.Adapters.MessageButtonsConfigurationAdapter;

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

        buttons.add("Estoy bien");
        buttons.add("No estoy bien");

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
        adapter = new MessageButtonMain(buttons);
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
                break;
        }
    }
}
