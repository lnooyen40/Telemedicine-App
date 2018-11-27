package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

import cs4322.project.telemedicineapp.model.Patient;


public class Profile extends AppCompatActivity {
    TextView Username;
    EditText Name2, Email, Phone, Password;
    ImageView backBtn;
    private DatabaseReference appDatabase;
    Button UpdateBTN;

    String LoginName= ""; // Used to pass login name to other views


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Username = (TextView)findViewById(R.id.tvName);

        Name2        = (EditText) findViewById(R.id.tvNAME2);
        Email       = (EditText) findViewById(R.id.tvEMAIL2);
        Phone       = (EditText) findViewById(R.id.tvPHONE2);
        Password    = (EditText) findViewById(R.id.tvPASSWORD2);

        UpdateBTN = (Button)findViewById(R.id.updateInfo);

        LoginName = getIntent().getExtras().getString("username");   // getting the currently logged in user
        appDatabase = FirebaseDatabase.getInstance().getReference();

        backBtn = (ImageView)findViewById(R.id.backBtn);
/*
        final String userName2 = appDatabase.child("patients").child(LoginName).child("fullname").toString();
        String email = appDatabase.child("patients").child(LoginName).child("email").getKey();
        String DOB = appDatabase.child("patients").child(LoginName).child("birthDate").getKey();
        String phone = appDatabase.child("patients").child(LoginName).child("phoneNumber").getKey();
        //Log.i ("This person", userName2);
        // Log.i ("This email", email);
        // Log.i ("This birthday", DOB);
        // Log.i ("This phone#", phone);

*/
        String userName2 = "";
        String email = "";
        String phone = "";

        Name2.setText(userName2);
        Email.setText(email);
        Phone.setText(phone);


        Username.setText(LoginName);

        UpdateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String fullnameStr = Name2.getText().toString();
                String usernameStr = LoginName;
                String emailStr = Email.getText().toString();
                String phoneStr = Phone.getText().toString();
                String genderStr = "";   // this doesnt need to change
                String passwordStr = Password.getText().toString();
                String birthdateStr = "";   // doesnt need to change
                patient = new Patient(fullnameStr, emailStr, phoneStr, birthdateStr, genderStr, passwordStr);
                appDatabase.child("patients").child(usernameStr).setValue(patient);
            */
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Homescreen.class);
                startActivity(intent);
            }
        });

    }
}