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
    ArrayList<String> userGegevens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_year);

        //Informatie uit de bundle halen
        final Bundle b = getIntent().getExtras();
        if (b != null) {
            userGegevens = b.getStringArrayList("userGegevens");

            //String username = b.getString("username");
            logged_in_username = userGegevens.get(1);

            user_id = userGegevens.get(0);
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


    }

}
