package com.appemergencias;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.appemergencias.R.drawable.baseline_settings_black_18dp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bttn, bttn2;
    ImageButton conf;
    LinearLayout main_page, config_page;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_page = findViewById(R.id.main_page);

        conf = findViewById(R.id.configuration);
        conf.setOnClickListener(this);

        bttn = findViewById(R.id.button);
        bttn.setOnClickListener(this);

        bttn2 = findViewById(R.id.button2);
        bttn2.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //Ok
                break;
            case R.id.button2:
                //Not ok

                break;
            case R.id.configuration:
                startActivity(new Intent(context, ConfigurationActivity.class));
                //main_page.setVisibility(View.GONE);
                //config_page.setVisibility(View.VISIBLE);
                break;
        }
    }
}
