package cs4322.project.telemedicineapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class DoctorHomescreen extends AppCompatActivity {

    ImageView btnChat, btnAppt, btnHelp, btnVideo, btnProfile;
    TextView welcome;
    Button btnLogout;

    String roomName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homescreen);
        setTitle("Home");


        btnChat = (ImageView)findViewById(R.id.chat);
        btnAppt = (ImageView)findViewById(R.id.appointments);
        btnHelp = (ImageView)findViewById(R.id.help);
        btnVideo = (ImageView)findViewById(R.id.imageView);
        btnProfile = (ImageView)findViewById(R.id.profileBtn);

        btnLogout = (Button) findViewById(R.id.logoutBtn);


        // Video Button Intent
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorHomescreen.this);
                builder.setTitle("Room Name");

                // Get view
                final EditText roomNameInput = new EditText(DoctorHomescreen.this);
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
                            Intent chatIntent = new Intent(DoctorHomescreen.this, VideoChatViewActivity.class);
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

        // Chat Button Intent
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(DoctorHomescreen.this, chatList.class);
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