package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorHomescreen extends AppCompatActivity {

    ImageView btnChat, btnAppt, btnHelp, btnPatients, btnProfile;
    TextView welcome;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homescreen);
        setTitle("Home");


        btnChat = (ImageView)findViewById(R.id.chat);
        btnAppt = (ImageView)findViewById(R.id.appointments);
        btnHelp = (ImageView)findViewById(R.id.help);
        btnPatients = (ImageView)findViewById(R.id.imageView);
        btnProfile = (ImageView)findViewById(R.id.profileBtn);

        btnLogout = (Button) findViewById(R.id.logoutBtn);



        // Chat Button Intent
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(DoctorHomescreen.this, DrChat.class);
                startActivity(chatIntent);
            }
        });

        // Appointment Button Intent
        btnAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apptIntent = new Intent(DoctorHomescreen.this, DoctorAppt.class);
                startActivity(apptIntent);
            }
        });


        // Help Button Intent
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(DoctorHomescreen.this, DoctorHelp.class);
                startActivity(helpIntent);
            }
        });

        // Logout Button Intent
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(DoctorHomescreen.this, Login.class);
                startActivity(logoutIntent);
            }
        });

        // Profile Button Intent
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(DoctorHomescreen.this, doctorProfile.class);
                startActivity(logoutIntent);
            }
        });


    }
}