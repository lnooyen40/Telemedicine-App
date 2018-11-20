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

    // Create a client (only needed once)
    FhirContext ctx = new FhirContext();
    IGenericClient client = ctx.newRestfulGenericClient("https://api-v5-dstu2.hspconsortium.org/PaCon/open");

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

        // Invoke the client
        ca.uhn.fhir.model.dstu2.resource.Bundle bundle = client.search().forResource(Observation.class)
                .where(new TokenClientParam("_id").exactly().code("SMART-7777701"))
                .prettyPrint()
                .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
                .execute();


        /*
         String visitdoc = bundle.getString("key");

          All of this information is pulled from the Observation.class of hAPI
     Codes for these different values:

     Obs codes :       BP               8480-6   (S) /  8462-4   (D)
                       Temp             8310-5
                       Weight           3141-9
                       Height           8302-2
                       pulse            8867-4
                       resp             9279-1

        id  Date then code
         */






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
