package com.group1.telemedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import helpers.InputValidation;
import sql.Database;


public class Login extends AppCompatActivity {

    private final AppCompatActivity activity = Login.this;
    private EditText userName, password;
    private Button loginBtn;
    private Button newUserBtn;
    private InputValidation inputValidation = new InputValidation(activity);
    Database database = new Database(activity);
    private static final String TAG = "LOGIN.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        loginBtn    = (Button)   findViewById(R.id.btnLogin);
        newUserBtn = (Button) findViewById(R.id.btnNewUser);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });

        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, NewPatient.class);
                startActivity(intent);
            }
        });
    }

    private void verifyFromSQLite()
    {
        if (!inputValidation.isInputEditTextFilled(userName, "USERNAME NOT FILLED IN")){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password, "PASSWORD NOT FILLED IN")){
            return;
        }
        if (database.checkUserExist(userName.getText().toString().trim(), password.getText().toString().trim())){
            Intent intent = new Intent(Login.this, Homescreen.class);
            intent.putExtra("USERNAME", userName.getText().toString().trim());
            intent.putExtra("PASSWORD", password.getText().toString().trim());
            emptyInputEditText();
            startActivity(intent);
        }else
        {
            Log.i(TAG, "SOMETHING WENT WRONG");
        }
    }

    private void emptyInputEditText()
    {
        userName.setText(null);
        password.setText(null);
    }
}
