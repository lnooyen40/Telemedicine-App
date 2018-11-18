package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RoleSelection extends Activity {

    private ImageView backBtn;
    private ImageView patientBtn, doctorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        backBtn = (ImageView) findViewById(R.id.back);
        patientBtn = (ImageView) findViewById(R.id.patientBtn);
        doctorBtn = (ImageView) findViewById(R.id.doctorBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleSelection.this, Login.class);
                startActivity(intent);
            }
        });

        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleSelection.this, PatientRegistration.class);
                startActivity(intent);
            }
        });

        doctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleSelection.this, DoctorRegistration.class);
                startActivity(intent);
            }
        });
    }

}
