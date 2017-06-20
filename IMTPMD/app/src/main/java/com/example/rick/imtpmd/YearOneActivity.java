package com.example.rick.imtpmd;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
            String username="";
            String user_id="";
//<<<<<<< HEAD
            String spec = "";
//=======
            ArrayList<String> userGegevens;
//>>>>>>> dbtestbranch
            final User logginuser = new User(99,"test","test","test" );

            final Bundle b = getIntent().getExtras();
            if (b != null) {
//<<<<<<< HEAD
//                username = b.getString("username");
//                user_id = b.getString("user_id");
                spec = b.getString("spec");
//=======
                userGegevens = b.getStringArrayList("userGegevens");
                username = userGegevens.get(1);
                user_id = userGegevens.get(0);

//>>>>>>> dbtestbranch
                logginuser.setId(Integer.parseInt(user_id));
            }

            Log.i("INFO", " : " + username + " " + user_id + " " + spec);
            Gson gson = new Gson();


            Vak[] vakken = gson.fromJson(response, Vak[].class);
            for (Vak vak : vakken) {
                //vak.setUser_id(b.getString("user_id"));
                vak.setGrade("10");
                if(vak.getYear().equals("1")){
                    vakModels.add(vak);
                }

            }

            mListView = (ListView) findViewById(R.id.my_list_view);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     Intent intent = new Intent(YearOneActivity.this, EditActivity.class);
//<<<<<<< HEAD
//                     Bundle b = new Bundle();
//                     b.putString("user_id",String.valueOf(logginuser.getId()));
////                     b.putString("spec", String.valueOf(spec)));
//                     b.putString("vak",vakModels.get(position).getName());
//                     intent.putExtras(b);
//=======
                     Bundle c = new Bundle();
                     c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                     //b.putString("user_id",String.valueOf(logginuser.getId()));
                     c.putString("vak",vakModels.get(position).getName());
                     intent.putExtras(c);
//>>>>>>> dbtestbranch
                     startActivity(intent);

                     //Toast t = Toast.makeText(YearOneActivity.this,"Click " + vakModels.get(position).getName(),Toast.LENGTH_SHORT);
                     //t.show();
                 }
             }
            );
            //boolean found = false;


            mAdapter = new vakkenAdapter(YearOneActivity.this, 0, vakModels);
            mListView.setAdapter(mAdapter);


        }

    }

}
