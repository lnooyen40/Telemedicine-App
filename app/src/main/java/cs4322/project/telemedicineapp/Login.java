package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs4322.project.telemedicineapp.model.Patient;

public class Login extends AppCompatActivity {

    private Button registerBtn;
    private Button loginBtn;
    private EditText username;
    private EditText password;


    private DatabaseReference appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appDatabase = FirebaseDatabase.getInstance().getReference();

        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RoleSelection.class);
                startActivity(intent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usernameStr = username.getText().toString();
                final String passwordStr = password.getText().toString();

                if (!usernameStr.equals("") && !passwordStr.equals("")) {
                    appDatabase.child("patients").child(usernameStr).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Patient pt = dataSnapshot.getValue(Patient.class);
                            //Toast.makeText(LoginActivity.this, pt.toString(), Toast.LENGTH_SHORT).show();
                            //System.out.println(pt);

                            if (pt != null && pt.getPassword().equals(passwordStr)) {
                                Intent intent = new Intent(Login.this, Homescreen.class);
                                intent.putExtra("username", usernameStr);

                                startActivity(intent);
                                //String usernameStr = username.getText().toString();

                            } else {
                                Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                            Toast.makeText(Login.this, "Invalid information", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(Login.this, "Please enter your information", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
