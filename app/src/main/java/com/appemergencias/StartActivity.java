package com.appemergencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        SharedPreferences settings = getSharedPreferences("com.appemergencias", MODE_PRIVATE);

        String userId = settings.getString("userId", null);
        if(userId != null){
            startActivity(new Intent(this, MainActivity.class));
        }else
            startActivity(new Intent(this, LoginActivity.class));

    }
}
