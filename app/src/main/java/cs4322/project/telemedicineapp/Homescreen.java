package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Homescreen extends Activity {

    ImageView btnVisit, btnChat, btnAppt, btnBill, btnHelp, btnRx, btnLab, btnProfile;
    TextView welcome;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        setTitle("Home");

        btnVisit = (ImageView)findViewById(R.id.visitSummary);
        btnChat = (ImageView)findViewById(R.id.chat);
        btnAppt = (ImageView)findViewById(R.id.appointments);
        btnBill = (ImageView)findViewById(R.id.billing);
        btnHelp = (ImageView)findViewById(R.id.help);
        btnRx = (ImageView)findViewById(R.id.prescriptions);
        btnLab = (ImageView)findViewById(R.id.labResults);
        btnProfile = (ImageView)findViewById(R.id.profileBtn);

        //welcome = (TextView)findViewById(R.id.btnLogout);
        btnLogout = (Button) findViewById(R.id.logoutBtn);

        // Button Visit Intent
        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visitIntent = new Intent(Homescreen.this, Visitmanagement.class);
                startActivity(visitIntent);
            }
        });

        // Chat Button Intent
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(Homescreen.this, Chat.class);
                startActivity(chatIntent);
            }
        });

        // Appointment Button Intent
        btnAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apptIntent = new Intent(Homescreen.this, Appointments.class);
                startActivity(apptIntent);
            }
        });

        // Bill Button Intent
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent billIntent = new Intent(Homescreen.this, Billing.class);
                startActivity(billIntent);
            }
        });

        //Lab Results Intent
        btnLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent labIntent = new Intent(Homescreen.this, LabResults.class);
                startActivity(labIntent);
            }
        });

        //Prescription History Intent
        btnRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rxIntent = new Intent(Homescreen.this, PrescriptionHistory.class);
                startActivity(rxIntent);
            }
        });

        // Help Button Intent
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(Homescreen.this, Help.class);
                startActivity(helpIntent);
            }
        });

        // Logout Button Intent
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(Homescreen.this, Login.class);
                startActivity(logoutIntent);
            }
        });

        // Profile Button Intent
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(Homescreen.this, Profile.class);
                startActivity(logoutIntent);
            }
        });


    }
}
