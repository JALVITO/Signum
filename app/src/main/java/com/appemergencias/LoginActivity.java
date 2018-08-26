package com.appemergencias;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appemergencias.util.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {


    Context context = this;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.email_sign_in_button:
                        String formatedUser = mEmailView.getText().toString().replace('.', '_');
                        LoginServer loginServer = new LoginServer(HttpRequest.REQUEST_TYPES.GET, "/createUser/" + formatedUser + "/" + mPasswordView.getText().toString(), "");
                        loginServer.execute();
                        break;
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }



    private class LoginServer extends HttpRequest {

        public LoginServer(REQUEST_TYPES request_type, String url_route, String parameters) {
            super(request_type, url_route, parameters);
        }

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected void onPostExecute(String server_response) {
            Log.d("serverRespone", server_response);
            SharedPreferences settings = getSharedPreferences("com.appemergencias", MODE_PRIVATE);

            // Writing data to SharedPreferences
            SharedPreferences.Editor editor = settings.edit();
            String formatedUser = mEmailView.getText().toString().replace('.', '_');
            editor.putString("userId", formatedUser);
            editor.putString("0", "Hola, me encuentro bien actualmente, me pondre en contacto contigo pronto.");
            editor.putString("1", "Hola, me encuentro mal, ayuda.");
            editor.apply();
            startActivity(new Intent(context, MainActivity.class));
        }
    }

}

