package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs4322.project.telemedicineapp.model.Patient;

public class PatientRegistration extends AppCompatActivity {

    private ImageView backBtn;
    private Button confirmBtn;
    private Patient patient;

    private EditText fullname;
    private EditText username;
    private EditText email;
    private EditText phone;
    private EditText gender;
    private EditText password;
    private EditText password2;
    private EditText birthdate;
    private DatabaseReference appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);
        appDatabase = FirebaseDatabase.getInstance().getReference();

        backBtn = (ImageView) findViewById(R.id.backBtn);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);

        fullname = (EditText) findViewById(R.id.fullname);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phoneNumber);
        gender = (EditText) findViewById(R.id.gender);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);
        birthdate = (EditText) findViewById(R.id.birthDate);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegistration.this, RoleSelection.class);
                startActivity(intent);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullnameStr = fullname.getText().toString();
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String phoneStr = phone.getText().toString();
                String genderStr = gender.getText().toString();
                String passwordStr = password.getText().toString();
                String password2Str = password2.getText().toString();
                String birthdateStr = birthdate.getText().toString();
                patient = new Patient (fullnameStr, emailStr, phoneStr, birthdateStr,genderStr, passwordStr);
                appDatabase.child("patients").child(usernameStr).setValue(patient);

                Intent intent = new Intent(PatientRegistration.this, SuccessulRegistration.class);
                startActivity(intent);
            }
        });
    }
}

/*appDatabase.child("patients").child("checking").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Patient pt = dataSnapshot.getValue(Patient.class);
        Toast.makeText(PatientRegistration.this, pt.toString(), Toast.LENGTH_SHORT).show();
        System.out.println(pt);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
    }
  });*/
