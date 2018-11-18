package cs4322.project.telemedicineapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DoctorHelp extends AppCompatActivity {

    ImageView backBtn;
    Button patients, chat, appointments, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        backBtn = (ImageView)findViewById(R.id.backBtn);

        patients = (Button) findViewById(R.id.patientsLabel2);
        chat = (Button) findViewById(R.id.Chat);
        appointments = (Button) findViewById(R.id.apptLabel);
        profile = (Button) findViewById(R.id.profile);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHelp.this, Homescreen.class);
                startActivity(intent);
            }
        });


    }

    public void onClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog d;
        switch(view.getId()){
            case R.id.patientsLabel2:

                builder.setMessage(R.string.patientsHelp);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Patients");
                d.show();
                break;
            case R.id.Chat:
                builder.setMessage(R.string.chatHelpDr);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Chat");
                d.show();
                break;

            case R.id.profile:
                builder.setMessage(R.string.ProfileHelpDr);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Profile");
                d.show();
                break;

            case R.id.apptLabel:
                builder.setMessage(R.string.apptHelpDr);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Appointments");
                d.show();
                break;
        }
    }
}
