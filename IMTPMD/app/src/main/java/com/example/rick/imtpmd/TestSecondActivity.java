package com.example.rick.imtpmd;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestSecondActivity extends AppCompatActivity {

    public static final String PREFS = "examplePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_second);

        TextView tv = (TextView)findViewById(R.id.textView1);

        SharedPreferences example = getSharedPreferences(PREFS, 0);
        String userString = example.getString("userMessage", "Nothing Found");
        tv.setText(userString);

    }
}
