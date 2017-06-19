package com.example.rick.imtpmd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rick.imtpmd.Database.DatabaseHelper;
import com.example.rick.imtpmd.Database.DatabaseInfo;
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

                Bundle b = getIntent().getExtras();
                EditText cijfer = (EditText) findViewById(R.id.grade);
                CheckBox behaald = (CheckBox) findViewById(R.id.passed);
                //String txtelement = cijfer.getText().toString();
                Boolean isPassed = behaald.isEnabled();

                Log.e("user_id", b.getString("user_id"));
                Log.e("vak", b.getString("vak"));
                Log.e("cijfer", cijfer.getText().toString());
                Log.e("behaald", String.valueOf(isPassed));

                DatabaseHelper dbHelper = DatabaseHelper.getHelper(EditActivity.this);
                ContentValues values = new ContentValues();
                values.put(DatabaseInfo.CourseColumn.vak_name,b.getString("vak"));
                values.put(DatabaseInfo.CourseColumn.user_id,b.getString("user_id"));
                values.put(DatabaseInfo.CourseColumn.grade,cijfer.getText().toString());
                values.put(DatabaseInfo.CourseColumn.passed,String.valueOf(isPassed));
                dbHelper.insert(DatabaseInfo.CourseTables.user,null,values);

                Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.user,new String[]{"*"},null,null,null,null,null);
                rs.moveToFirst();
                Log.e("TESTd ","TEST");
                if (rs != null) {
                    do {
                        for (int i = 0; i < rs.getColumnCount(); i++) {
                            Log.e("veld "+i+" :", "" + rs.getString(i));
                        }
                    }while (rs.moveToNext());
                }


                Intent intent = new Intent(EditActivity.this, YearOneActivity.class);
                startActivity(intent);
            }
        });
    }

}
