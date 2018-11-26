package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Appointments extends AppCompatActivity {


    CalendarView    Calendar;
    TextView        selected;
    Spinner         spin;
    Button          request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        Calendar = (CalendarView)   findViewById(R.id.cvCalendar);
        selected = (TextView)       findViewById(R.id.tvSelected);
        spin     = (Spinner)        findViewById(R.id.spinOptions);
        request  = (Button)         findViewById(R.id.btnRequest);

        Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selected.setText( month+1 + "/" + dayOfMonth + "/" + year);
            }
        });

        String[] Times = new String[]{"Morning", "Afternoon"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, Times);
        spin.setAdapter(adapter);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = "Alex";
                String date = (String)selected.getText();
                String time = spin.getSelectedItem().toString();
                String visittype = "";
                String message = user + " has requested an appointment on " + date + " in the " + time ;
                Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG).show();
                //Intent visitIntent = new Intent(Appointments.this, Chat.class);
                //startActivity(visitIntent);
            }
        });



    }
}
