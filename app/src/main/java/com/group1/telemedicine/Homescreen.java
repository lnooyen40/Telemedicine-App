package com.group1.telemedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Homescreen extends AppCompatActivity {


    ImageView btnVisit, btnChat, btnAppt, btnBill, btnHelp;
    TextView welcome;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        setTitle("Home");

        btnVisit = (ImageView) findViewById(R.id.visitSummary);
        btnChat = (ImageView) findViewById(R.id.chat);
        btnAppt = (ImageView) findViewById(R.id.appointments);
        btnBill = (ImageView) findViewById(R.id.billing);
        btnHelp = (ImageView) findViewById(R.id.help);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        welcome = (TextView) findViewById(R.id.welcomeLabel);

        welcome.setText("Welcome, " + getIntent().getExtras().getString("username") + "!");

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Visit = new Intent (Homescreen.this, Visitmanagement.class);
                startActivity(Visit);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Chat = new Intent (Homescreen.this, Chat.class);
                startActivity(Chat);
            }
        });

        btnAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Appt = new Intent (Homescreen.this, Appointments.class);
                startActivity(Appt);
            }
        });

        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Bill = new Intent ( Homescreen.this, Billing.class);
                startActivity(Bill);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Help = new Intent (Homescreen.this, Help.class);
                startActivity(Help);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Logout = new Intent ( Homescreen.this, Login.class);
                startActivity(Logout);
            }
        });

    }

}
