package com.example.rick.imtpmd;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rick.imtpmd.Database.DatabaseHelper;
import com.example.rick.imtpmd.Database.DatabaseInfo;

public class VullerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuller);


        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

        ContentValues vak1 = new ContentValues();
        vak1.put(DatabaseInfo.CourseColumn.name, "IARCH");
        vak1.put(DatabaseInfo.CourseColumn.grade, " ");

        dbHelper.insert(DatabaseInfo.CourseTables.course, null, vak1);




    }
}
