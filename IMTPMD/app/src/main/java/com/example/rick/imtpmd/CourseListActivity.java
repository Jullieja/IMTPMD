package com.example.rick.imtpmd;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rick.imtpmd.Model.vakModel;
import com.example.rick.imtpmd.Model.vakkenAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private ListView mListView;
    private vakkenAdapter mAdapter;
    private List<vakModel> vakModels = new ArrayList<>();
    // WE MAY NEED A METHOD TO FILL THIS. WE COULD RETRIEVE THE DATA FROM AN ONLINE JSON SOURCE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        mListView = (ListView) findViewById(R.id.my_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     Toast t = Toast.makeText(CourseListActivity.this,"Click" + position,Toast.LENGTH_LONG);
                     t.show();
                 }
             }
        );
        vakModels.add(new vakModel("IKPMD", 3, 3.4, 0, null,null,false));
//        vakModels.add(new vakModel("IOPR1", "4", "100"));
//        vakModels.add(new vakModel("IPSEN", "6", "1000"));
//        vakModels.add(new vakModel("IKPMD", "3", "10"));
//        vakModels.add(new vakModel("IOPR1", "4", "10"));
//        vakModels.add(new vakModel("IPSEN", "6", "10"));
//        vakModels.add(new vakModel("IKPMD", "3", "10"));
//        vakModels.add(new vakModel("IOPR1", "4", "10"));
//        vakModels.add(new vakModel("IPSEN", "6", "10"));

        mAdapter = new vakkenAdapter(CourseListActivity.this, 0, vakModels);
        mListView.setAdapter(mAdapter);
    }
}