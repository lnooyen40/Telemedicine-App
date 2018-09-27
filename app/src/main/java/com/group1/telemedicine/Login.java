package com.group1.telemedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private EditText userName, password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        Login    = (Button)   findViewById(R.id.btnLogin);


        // Login currently hard coded to Admin / 1234
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String uName = userName.getText().toString();
                String uPassword = password.getText().toString();

                if (uName.equals("Admin") && uPassword.equals("1234")) {
                    Intent Valid = new Intent (Login.this, Homescreen.class);
                   // Valid.putExtra(uName , PatientID)      << Placeholder for later
                    startActivity(Valid);
                }
                // Toast telling the patient that their login is incorrect
                else {

                    Toast toast = Toast.makeText(getApplicationContext(), "Incorrect Login. Please try again.", Toast.LENGTH_SHORT);
                    toast.show();

                }

            }
        });


    }
}
