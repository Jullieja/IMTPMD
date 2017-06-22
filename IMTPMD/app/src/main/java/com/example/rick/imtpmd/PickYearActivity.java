package com.example.rick.imtpmd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PickYearActivity extends AppCompatActivity {
    String logged_in_username;
    String user_id;
//<<<<<<< HEAD
    String spec;

//=======
    ArrayList<String> userGegevens;
//>>>>>>> dbtestbranch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_year);

        //Informatie uit de bundle halen
        final Bundle b = getIntent().getExtras();
        if (b != null) {
//<<<<<<< HEAD
//            String username = b.getString("username");
//            logged_in_username = b.getString("username");
//            user_id = b.getString("user_id");
            spec = b.getString("spec");
//=======
            userGegevens = b.getStringArrayList("userGegevens");

            //String username = b.getString("username");
            logged_in_username = userGegevens.get(1);

            user_id = userGegevens.get(0);
//>>>>>>> dbtestbranch
            //Informatie weergeven dmv toast
            Context context = getApplicationContext();
            CharSequence text = "Je bent succesvol ingelogd als " + userGegevens.get(1);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        Button jaar1 = (Button) findViewById(R.id.jaar1);
        jaar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Knop year 1 ingedrukt", "Start YearOneActivity");
                Intent intent = new Intent(PickYearActivity.this, YearOneActivity.class);
                Bundle c = new Bundle();
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                //b.putString("user_id",user_id);

                intent.putExtras(c);
                startActivity(intent);
            }
        });

        Button jaar2 = (Button) findViewById(R.id.jaar2);
        jaar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Knop year 2 ingedrukt", "Start YearTwoActivity");
                Intent intent = new Intent(PickYearActivity.this, YearTwoActivity.class);
                Bundle c = new Bundle();
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                c.putString("spec", spec);

                intent.putExtras(c);
                startActivity(intent);
            }
        });

        Button jaar34 = (Button) findViewById(R.id.jaar34);
        jaar34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Knop year 34 ingedrukt", "Start YearThreeFourActivity");
                Intent intent = new Intent(PickYearActivity.this, YearThreeFourActivity.class);
                Bundle c = new Bundle();
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));

                intent.putExtras(c);
                startActivity(intent);
            }
        });

        Button keuzevak = (Button) findViewById(R.id.keuze);
        keuzevak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Knop keuzevak ingedrukt", "Start KeuzeVakActivity");
                Intent intent = new Intent(PickYearActivity.this, KeuzeVakActivity.class);
                Bundle c = new Bundle();
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));

                intent.putExtras(c);
                startActivity(intent);
            }
        });


    }

}
