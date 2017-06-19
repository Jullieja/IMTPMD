package com.example.rick.imtpmd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestMainActivity extends Activity {

    public static final String PREFS = "examplePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        final EditText et = (EditText)findViewById(R.id.editText1);
        Button nextAct = (Button)findViewById(R.id.button1);

        nextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = et.getText().toString();
                SharedPreferences examplePrefs = getSharedPreferences(PREFS, 0);
                SharedPreferences.Editor editor = examplePrefs.edit();
                editor.putString("userMessage", message);
                editor.commit();

                Intent i = new Intent(getApplicationContext(), TestSecondActivity.class);
                startActivity(i);

            }
        });
    }
}
