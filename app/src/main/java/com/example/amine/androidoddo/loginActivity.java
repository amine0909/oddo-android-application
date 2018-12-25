package com.example.amine.androidoddo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.amine.androidoddo.Controllers.LoginController;
import com.example.amine.androidoddo.config.Config;


public class loginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Tasks List");

    }

    public void login(View view) {

        EditText username = findViewById(R.id.etLoginUsername);
        EditText password = findViewById(R.id.etLoginPassword);


        LoginController loginController = new LoginController(this,username.getText().toString(),password.getText().toString(),
                loginActivity.this);

        //Intent intent = new Intent(this,ProjectActivity.class);
        //startActivity(intent);







    }
}
