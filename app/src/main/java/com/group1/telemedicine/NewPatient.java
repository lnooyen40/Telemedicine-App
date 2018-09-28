package com.group1.telemedicine;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helpers.InputValidation;
import sql.Database;

public class NewPatient extends AppCompatActivity{

    private final AppCompatActivity activity = NewPatient.this;

    EditText getUsername;
    EditText getEmail;
    EditText getPassword;
    EditText getConfirmPassword;
    //EditText getSSN;
    Button createUserBtn;
    EditText getAge;
    private Patient patient = new Patient();
    Database database = new Database(activity);
    private InputValidation inputValidation = new InputValidation(activity);
    // Missing any??

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        getUsername = (EditText)findViewById(R.id.ptUsername);
        getEmail = (EditText)findViewById(R.id.ptEmail);
        getPassword = (EditText)findViewById(R.id.ptPassword);
        getConfirmPassword = (EditText)findViewById(R.id.etReEnter);
        getAge = (EditText)findViewById(R.id.ptAge);
        createUserBtn = (Button)findViewById(R.id.createUserBtn);

        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToSQLite();

                Intent intent = new Intent(NewPatient.this, Homescreen.class);
                startActivity(intent);
            }
        });
    }

    private void postDataToSQLite()
    {
        if (!inputValidation.isInputEditTextFilled(getUsername, "USERNAME NOT FILLED IN")){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(getEmail, "EMAIL NOT FILLED IN")){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(getPassword, "PASSWORD NOT FILLED IN")){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(getAge, "AGE NOT SET")){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(getConfirmPassword, "CONFIRM PASSWORD NOT SET")){
            return;
        }
        if (!inputValidation.isInputTextAnEmail(getEmail, "THIS IS NOT A REAL EMAIL ADDRESS")){
            return;
        }
        if (!inputValidation.isInputEditTextMatch(getPassword, getConfirmPassword,"YOUR FIRST AND SECOND PASSWORDS DO NOT MATCH")){
            return;
        }
        if (!database.checkUserExist(getEmail.getText().toString().trim())){
            patient.setPtName(getUsername.getText().toString());
            patient.setPtEmail(getEmail.getText().toString());
            patient.setPtPassword(getPassword.getText().toString());
            patient.setPtAge(Integer.parseInt(getAge.getText().toString()));

            database.addPatient(patient);
            Toast toast = Toast.makeText(getApplicationContext(),"SUCCESSFULLY ADDED PATIENT", Toast.LENGTH_SHORT);
            toast.show();
            emptyInputsEditText();
        }
    }

    private void emptyInputsEditText()
    {
        getUsername.setText(null);
        getEmail.setText(null);
        getPassword.setText(null);
        getConfirmPassword.setText(null);
        getAge.setText(null);
    }
}
