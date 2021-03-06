package cs4322.project.telemedicineapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class Homescreen extends Activity {

    ImageView btnVisit, btnChat, btnAppt, btnVideo, btnHelp, btnRx, btnLab, btnProfile;
    TextView welcome;
    Button btnLogout;
    String roomName = ""; // Used for video chatting


    String Username= ""; // Used to pass login name to other views


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        setTitle("Home");

        //Username = getIntent().getExtras().getString("username");


        btnVisit = (ImageView)findViewById(R.id.visitSummary);
        btnChat = (ImageView)findViewById(R.id.chat);
        btnAppt = (ImageView)findViewById(R.id.appointments);
        btnVideo = (ImageView)findViewById(R.id.videoChatBtn);
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
           //     visitIntent.putExtra("username", Username);
                startActivity(visitIntent);
            }
        });

        // Chat Button Intent
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(Homescreen.this, chatList.class);
                //         billIntent.putExtra("username", Username);
                startActivity(chatIntent);
            }
        });

        // Appointment Button Intent
        btnAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apptIntent = new Intent(Homescreen.this, Appointments.class);
                apptIntent.putExtra("username", Username);
                startActivity(apptIntent);
            }
        });

        // Video Button Intent
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                builder.setTitle("Room Name");

                // Get view
                final EditText roomNameInput = new EditText(Homescreen.this);
                roomNameInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                builder.setView(roomNameInput);

                // Add button to alert
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        roomName = roomNameInput.getText().toString();
                        if(roomName.isEmpty()){
                            dialogInterface.cancel();
                            Toast.makeText(getApplicationContext(), "Room Name Cannot be Empty", Toast.LENGTH_LONG).show();
                        } else {
                            Intent chatIntent = new Intent(Homescreen.this, VideoChatViewActivity.class);
                            chatIntent.putExtra("RoomName", roomName);
                            startActivity(chatIntent);
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        //Intent returnHome = new Intent(getApplicationContext(), Homescreen.class);
                        //startActivity(returnHome);
                    }
                });

                builder.show();
            }
        });

        //Lab Results Intent
        btnLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent labIntent = new Intent(Homescreen.this, LabResults.class);
         //       labIntent.putExtra("username", Username);
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
                Intent profileIntent = new Intent(Homescreen.this, Profile.class);
                profileIntent.putExtra("username", Username);    // Send username into profile
                startActivity(profileIntent);
            }
        });


    }
}
