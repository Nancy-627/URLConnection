package com.example.urlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acticity);
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        textView = (TextView) findViewById(R.id.text);
        textView.setText(response);

    }
}