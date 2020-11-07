package com.example.urlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private Button register;
    private CheckBox rememberPass;
    String responseData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        boolean isRemember = pref.getBoolean("remember", false);
        if (isRemember) {
            String account = pref.getString("account","");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String account = accountEdit.getText().toString().trim();
                 String password = passwordEdit.getText().toString().trim();
                final String s = "http://39.107.143.247:18080/user/login?username=root&password=000000";
                final String address = "http://39.107.143.247:18080/user/login";
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                         responseData = response.toString();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                },account, password);
                if (account.equals("root")&&password.equals("root")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember",true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    intent.putExtra("response", responseData);
                    startActivity(intent);
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}