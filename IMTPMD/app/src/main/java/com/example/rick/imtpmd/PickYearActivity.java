package com.example.rick.imtpmd;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PickYearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_year);

        //Informatie uit de bundle halen
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String username = b.getString("username");

            //Informatie weergeven dmv toast
            Context context = getApplicationContext();
            CharSequence text = "Je bent succesvol ingelogd als " + username;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }

}
