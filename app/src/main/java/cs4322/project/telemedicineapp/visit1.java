package cs4322.project.telemedicineapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.TokenClientParam;

public class visit1 extends AppCompatActivity {


    ImageView backBtn;

    TextView vdoctor, vdate, vdiagnosis, vbp, vtemp, vheight, vweight, vpulse, vresp, vmeds, vinstruct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit1);

        vdoctor = (TextView) findViewById(R.id.tvDocName);          vdate = (TextView) findViewById(R.id.tvVisitDate);
        vdiagnosis = (TextView) findViewById(R.id.tvDiagnosis);     vbp =  (TextView) findViewById(R.id.tvBP);
        vtemp = (TextView) findViewById(R.id.tvTemp);               vheight = (TextView) findViewById(R.id.tvHeight);
        vweight = (TextView) findViewById(R.id.tvWeight);           vpulse = (TextView) findViewById(R.id.tvPulse) ;
        vresp = (TextView) findViewById(R.id.tvResp) ;              vmeds = (TextView) findViewById(R.id.tvMedication);
        vinstruct = (TextView) findViewById(R.id.tvInstrct);






        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(visit1.this, Visitmanagement.class);
                startActivity(intent);
            }
        });
    }
}
