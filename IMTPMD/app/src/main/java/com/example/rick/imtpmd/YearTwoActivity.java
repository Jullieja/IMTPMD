package com.example.rick.imtpmd;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rick.imtpmd.Model.User;
import com.example.rick.imtpmd.Model.Vak;
import com.example.rick.imtpmd.Model.vakkenAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class YearTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_two);

        new YearTwoActivity.VakkenTask().execute();
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
            String username = "";
            String user_id = "";
            String spec = "";
            final User logginuser = new User(99,"test","test","test" );

            Bundle b = getIntent().getExtras();
            if (b != null) {
                username = b.getString("username");
                user_id = b.getString("user_id");
                spec = b.getString("spec");
                logginuser.setId(Integer.parseInt(user_id));
            }

            Log.i("INFO", " : " + username + " " + user_id + " " + spec);
            Gson gson = new Gson();


            Vak[] vakken = gson.fromJson(response, Vak[].class);
            for (Vak vak : vakken) {
                if (vak.getSpec().equals(spec)){
                    vakModels.add(vak);
                }

               // vak.setSpec(b.getString("spec"));
                //vak.setGrade("10");
//                if(vak.getYear().equals("2") & vak.getSpec().equals("fict")){
//                        vakModels.add(vak);
//                }

//                if(vak.getSpec() == "medt"){
//                    if(vak.getYear().equals("2")){
//                        vakModels.add(vak);
//                    }
//                }


            }

            mListView = (ListView) findViewById(R.id.my_list_view);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent(YearTwoActivity.this, EditActivity.class);
                    Bundle b = new Bundle();
                    b.putString("user_id",String.valueOf(logginuser.getId()));
                    b.putString("spec", String.valueOf(logginuser.getSpec()));
                    b.putString("vak",vakModels.get(position).getName());
                    intent.putExtras(b);
                    startActivity(intent);

                    //Toast t = Toast.makeText(YearOneActivity.this,"Click " + vakModels.get(position).getName(),Toast.LENGTH_SHORT);
                    //t.show();
                                                 }
                                             }
            );
            //boolean found = false;

            mAdapter = new vakkenAdapter(YearTwoActivity.this, 0, vakModels);
            mListView.setAdapter(mAdapter);

        }

    }

}

