package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Visitmanagement extends AppCompatActivity {

    ImageView backBtn;
    TextView visit1, visit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitmanagement);


        backBtn = (ImageView)findViewById(R.id.backBtn);
        visit1 = (TextView)findViewById(R.id.visit);
        visit2 = (TextView)findViewById(R.id.visit2);

        visit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Visitmanagement.this, visit1.class);
                startActivity(intent);
            }
        });

        visit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Visitmanagement.this, visit2.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Visitmanagement.this, Homescreen.class);
                startActivity(intent);
            }
        });


    }
}
