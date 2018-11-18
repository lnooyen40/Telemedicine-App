package cs4322.project.telemedicineapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Help extends AppCompatActivity {

    ImageView backBtn;
    Button visit, lab, rx, chat, appointments, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        backBtn = (ImageView)findViewById(R.id.backBtn);

        visit = (Button) findViewById(R.id.vsLabel);
        lab = (Button) findViewById(R.id.labResultsLabel);
        rx = (Button) findViewById(R.id.prescriptionsLabel);
        chat = (Button) findViewById(R.id.Chat);
        appointments = (Button) findViewById(R.id.apptLabel);
        profile = (Button) findViewById(R.id.profile);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, Homescreen.class);
                startActivity(intent);
            }
        });


    }

    public void onClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog d;
        switch(view.getId()){
            case R.id.vsLabel:

                builder.setMessage(R.string.visitHelp);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Visit Summary");
                d.show();
                break;
            case R.id.labResultsLabel:
                builder.setMessage(R.string.labResultsHelp);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Lab Results");
                d.show();
                break;
            case R.id.prescriptionsLabel:
                builder.setMessage(R.string.rxHelp);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                d = builder.create();
                d.setTitle("Prescriptions");
                d.show();
                break;
            case R.id.Chat:
                builder.setMessage(R.string.chatHelp);
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
                builder.setMessage(R.string.ProfileHelp);
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
                builder.setMessage(R.string.apptHelp);
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
