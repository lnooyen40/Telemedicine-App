package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs4322.project.telemedicineapp.model.Doctor;
import cs4322.project.telemedicineapp.model.Patient;

public class DoctorRegistration extends AppCompatActivity {

    ImageView backBtn;

    private Button confirmBtn;
    private Doctor doctor;

    private EditText fullname;
    private EditText username;
    private EditText email;
    private EditText credentials;
    private EditText password;
    private EditText password3;
    private EditText facility;
    private DatabaseReference appDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        appDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        backBtn = (ImageView)findViewById(R.id.backBtn);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);

        fullname = (EditText) findViewById(R.id.fullname);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        credentials = (EditText) findViewById(R.id.credentials);
        facility = (EditText) findViewById(R.id.facility);
        password = (EditText) findViewById(R.id.password);
        password3 = (EditText) findViewById(R.id.password3);
        //birthdate = (EditText) findViewById(R.id.credentials);

        /*confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullnameStr = fullname.getText().toString();
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String credentialsStr = credentials.getText().toString();
                String passwordStr = password.getText().toString();
                String password3Str = password3.getText().toString();
                String facilityStr = facility.getText().toString();
                doctor = new Doctor(fullnameStr, emailStr, facilityStr, credentialsStr, passwordStr);
                appDatabase.child("Doctors").child(usernameStr).setValue(doctor);
            });*/

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullnameStr = fullname.getText().toString();
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String credentialsStr = credentials.getText().toString();
                String passwordStr = password.getText().toString();
                //String password3Str = password3.getText().toString();
                String facilityStr = facility.getText().toString();
                doctor = new Doctor(usernameStr, fullnameStr, emailStr, facilityStr, credentialsStr);
                //appDatabase.child("Doctors").child(usernameStr).setValue(doctor);

                mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    /*Patient user = new Patient(
                                            name,
                                            email,
                                            phone
                                    );*/

                                    FirebaseDatabase.getInstance().getReference("Doctors")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            //progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                //Toast.makeText(Pat.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(DoctorRegistration.this, SuccessulRegistration.class);
                                                startActivity(intent);
                                            } else {
                                                //display a failure message
                                            }
                                        }
                                    });

                                } else {
                                    Toast.makeText(DoctorRegistration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorRegistration.this, RoleSelection.class);
                startActivity(intent);
            }
        });
    }
}
