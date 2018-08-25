package com.appemergencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttn = findViewById(R.id.button);
        bttn.setOnClickListener(this);

        /*
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello.setText("Presionado");
            }
        });
        */


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //hello.setText("Presionado");
                break;
        }
    }
}
