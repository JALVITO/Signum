package com.appemergencias;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back;
    ScrollView numbers;
    RecyclerView recyclerView;
    RecyclerView.Adapter rva;
    RecyclerView.LayoutManager rvlm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        rvlm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlm);

        String[] myDataset = {"UNO", "DOS", "TRES"};
        rva = new MyAdapter(myDataset);



        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        numbers = findViewById(R.id.number_list);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.add:
                //numbers.addView();
                break;
        }
    }
}
