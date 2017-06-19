package com.example.rick.imtpmd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rick.imtpmd.R;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button save = (Button) findViewById(R.id.save);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            String vak = b.getString("vak");
            String user_id = b.getString("user_id");
            //Log.d("editacititytest: "+vak+" ",user_id);
            Toast t = Toast.makeText(EditActivity.this,"Click " + vak+user_id,Toast.LENGTH_SHORT);
           t.show();
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Opslaan knop ingedrukt", "Terug naar activity_courselist");

                Intent intent = new Intent(EditActivity.this, YearOneActivity.class);
                startActivity(intent);
            }
        });
    }

}
