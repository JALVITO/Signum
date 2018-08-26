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
import android.widget.TextView;


public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back;
    ScrollView id;
    //RecyclerView recyclerView;
    //RecyclerView.Adapter rva;
    //RecyclerView.LayoutManager rvlm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //recyclerView = (RecyclerView) findViewById(R.id.rv);
        //recyclerView.setHasFixedSize(true);
        //rvlm = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(rvlm);
        //String[] myDataset = {"UNO", "DOS", "TRES"};
        //rva = new MyAdapter(myDataset);



        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        id = findViewById(R.id.number_list);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.add:
                id.addView(createLinearLayout());
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
