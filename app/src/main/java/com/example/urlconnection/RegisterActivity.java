package com.example.urlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText account;
    private EditText password;
    private Button register;
    String responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String intAccount = account.getText().toString().trim();
                final String intPassword = password.getText().toString().trim();
                final String s = "http://39.107.143.247:18080/user/register?username=root&password=000000";
                final String address = "http://39.107.143.247:18080/user/register";
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        responseData = response.toString();

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                },intAccount, intPassword);
                if (intAccount.equals("root")&&intPassword.equals("root")) {
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("response", responseData);
                    startActivity(intent);
                }

            }
        });
    }
}