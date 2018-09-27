package com.group1.telemedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homescreen extends AppCompatActivity {


    Button btnVisit, btnChat, btnAppt, btnBill, btnHelp, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        btnVisit = (Button) findViewById(R.id.btnVisit);
        btnChat = (Button) findViewById(R.id.btnChat);
        btnAppt = (Button) findViewById(R.id.btnAppt2);
        btnBill = (Button) findViewById(R.id.btnBill);
        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnLogout = (Button) findViewById(R.id.btnLogout);


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
