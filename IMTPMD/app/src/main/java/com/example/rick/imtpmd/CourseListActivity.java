package com.example.rick.imtpmd;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rick.imtpmd.Model.CourseModel;
import com.example.rick.imtpmd.Model.CourseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private ListView mListView;
    private CourseListAdapter mAdapter;
    private List<CourseModel> courseModels = new ArrayList<>();
    // WE MAY NEED A METHOD TO FILL THIS. WE COULD RETRIEVE THE DATA FROM AN ONLINE JSON SOURCE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        mListView = (ListView) findViewById(R.id.my_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     startActivity(new Intent(CourseListActivity.this, EditActivity.class));
                     Toast t = Toast.makeText(CourseListActivity.this,"Click" + position,Toast.LENGTH_LONG);
                     t.show();
                 }
             }
        );
        courseModels.add(new CourseModel("IKPMD", "3", "100"));
        courseModels.add(new CourseModel("IOPR1", "4", "100"));
        courseModels.add(new CourseModel("IPSEN", "6", "1000"));
        courseModels.add(new CourseModel("IKPMD", "3", "10"));
        courseModels.add(new CourseModel("IOPR1", "4", "10"));
        courseModels.add(new CourseModel("IPSEN", "6", "10"));
        courseModels.add(new CourseModel("IKPMD", "3", "10"));
        courseModels.add(new CourseModel("IOPR1", "4", "10"));
        courseModels.add(new CourseModel("IPSEN", "6", "10"));

        mAdapter = new CourseListAdapter(CourseListActivity.this, 0, courseModels);
        mListView.setAdapter(mAdapter);
    }
}