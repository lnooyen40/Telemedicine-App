package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth auth;
    public static String USER_PROFILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appDatabase = FirebaseDatabase.getInstance().getReference();

        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        auth = FirebaseAuth.getInstance();


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

                Log.i("Login", "Clicked");

                String usernameStr = username.getText().toString();
                final String passwordStr = password.getText().toString();

                if (!usernameStr.equals("") && !passwordStr.equals("")) {
                    /*appDatabase.child("patients").child(usernameStr).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Patient pt = dataSnapshot.getValue(Patient.class);
                            //Toast.makeText(LoginActivity.this, pt.toString(), Toast.LENGTH_SHORT).show();
                            //System.out.println(pt);

                            if (pt != null && pt.getPassword().equals(passwordStr)) {
                                Intent intent = new Intent(Login.this, Homescreen.class);
                                startActivity(intent);
                                //String usernameStr = username.getText().toString();
                                //intent.putExtra("name")
                            } else {
                                Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                            Toast.makeText(Login.this, "Invalid information", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                    //authenticate user
                    auth.signInWithEmailAndPassword(usernameStr, passwordStr)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            //inputPassword.setError(getString(R.string.minimum_password));
                                            Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        //Intent intent;
                                        /*if ( appDatabase.child("patients").child(user.getUid()) != null)
                                            intent = new Intent(Login.this, Homescreen.class);
                                        else
                                            intent = new Intent(Login.this, DoctorHomescreen.class);*/
                                        appDatabase.child("patients").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                //Patient pt = dataSnapshot.getValue(Patient.class);
                                                //Toast.makeText(LoginActivity.this, pt.toString(), Toast.LENGTH_SHORT).show();
                                                //System.out.println(pt);

                                                if (dataSnapshot.hasChild(user.getUid())) {
                                                    Intent intent = new Intent(Login.this, Homescreen.class);
                                                    startActivity(intent);
                                                    USER_PROFILE = "p";
                                                    //String usernameStr = username.getText().toString();
                                                    //intent.putExtra("name")
                                                } else {
                                                    Intent intent = new Intent(Login.this, DoctorHomescreen.class);
                                                    startActivity(intent);
                                                    USER_PROFILE = "d";
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                System.out.println("The read failed: " + databaseError.getCode());
                                                Toast.makeText(Login.this, "Invalid information", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        //startActivity(intent);
                                        finish();
                                    }
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
