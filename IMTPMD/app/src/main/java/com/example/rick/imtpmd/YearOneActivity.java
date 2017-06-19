package com.example.rick.imtpmd;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.rick.imtpmd.Model.User;
import com.example.rick.imtpmd.Model.Vak;
import com.example.rick.imtpmd.Model.vakModel;
import com.example.rick.imtpmd.Model.vakkenAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class YearOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_one);

        new VakkenTask().execute();
    }

    public class VakkenTask extends AsyncTask<Void, Void, String> {

        private ListView mListView;
        private vakkenAdapter mAdapter;
        private List<Vak> vakModels = new ArrayList<>();


        VakkenTask() {


        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                URL url = new URL("http://www.rickvanmegen.nl/vakken.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(final String response) {
            Log.i("INFO", " : " + response);
            Gson gson = new Gson();

            Vak[] vakken = gson.fromJson(response, Vak[].class);
            mListView = (ListView) findViewById(R.id.my_list_view);

            boolean found = false;
            for (Vak vak : vakken) {
                    vak.setUser_id("1");
                    vak.setGrade("10");
                if(vak.getYear().equals("1")){
                    vakModels.add(vak);
                }

            }

            mAdapter = new vakkenAdapter(YearOneActivity.this, 0, vakModels);
            mListView.setAdapter(mAdapter);


        }

    }

}
