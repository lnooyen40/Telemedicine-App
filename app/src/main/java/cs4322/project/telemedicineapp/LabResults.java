package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LabResults extends AppCompatActivity {

    ImageView backBtn, questionBtn;
    TextView labResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_results);

        backBtn = (ImageView)findViewById(R.id.backBtn);
        labResult = (TextView)findViewById(R.id.lab1);
        questionBtn = (ImageView)findViewById(R.id.questionBtn);

        labResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visitIntent = new Intent(LabResults.this, labexample.class);
                startActivity(visitIntent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabResults.this, Homescreen.class);
                startActivity(intent);
            }
        });

        questionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabResults.this, chatList.class);
                startActivity(intent);
            }
        });
    }
}
