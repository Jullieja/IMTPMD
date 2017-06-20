package com.example.rick.imtpmd;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rick.imtpmd.Database.DatabaseInfo;
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
import com.example.rick.imtpmd.Database.DatabaseHelper;
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
        private List<Vak> vakkenlijst = new ArrayList<>();


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
            String spec = "";
            ArrayList<String> userGegevens;

            final User logginuser = new User(99,"test","test","test" );

            final Bundle b = getIntent().getExtras();
            if (b != null) {
<<<<<<< HEAD
              user_id = b.getString("user_id");
=======

>>>>>>> 340c44be58d6cc18329e17b646fede2ff4f1f263
                spec = b.getString("spec");

                userGegevens = b.getStringArrayList("userGegevens");
                username = userGegevens.get(1);
                user_id = userGegevens.get(0);


                logginuser.setId(Integer.parseInt(user_id));
            }

            Log.i("INFO", " : " + username + " " + user_id + " " + spec);

            DatabaseHelper dbHelper = DatabaseHelper.getHelper(YearOneActivity.this);
            Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.user,new String[]{"*"/*"vak_name = "+b.getString("vak")*/},"user_id = '"+b.getStringArrayList("userGegevens").get(0)+"'" ,null,null,null,null);
            int rijenteller = rs.getCount();
            rs.moveToFirst();


            int j=0;
            if (rs != null) {
                do {
                    //Log.e("RIJ: " , j+ " #########################################");
                    j+=1;
                    Vak vak = new Vak("","","","",null);
                    for (int i = 0; i < rs.getColumnCount(); i++) {
                        //Log.e("veld "+i+" :", "" + rs.getString(i));
                        if(i == 1){//naam
                            vak.setName(rs.getString(i));
                        }
                        if(i == 2){//id
                            vak.setUser_id(rs.getString(i));
                        }
                        if(i == 3){//grade
                            vak.setGrade(rs.getString(i));
                        }
                        if(i == 4){//passed
                            vak.setPassed(rs.getString(i));
                        }
                    }
                    vakkenlijst.add(vak);
                }while (rs.moveToNext());
            }

            Gson gson = new Gson();
            Vak[] vakken = gson.fromJson(response, Vak[].class);
            for (Vak vak : vakken) {
                if(vak.getYear().equals("1")){
                    for(Vak vak_add : vakkenlijst){
                        if(vak.getName().equals(vak_add.getName())){
                            //Log.e("MATCH FOUND: ",vak.getName()+vak_add.getName());
                            vak.setUser_id(vak_add.getUser_id());
                            vak.setGrade(vak_add.getGrade());
                            vak.setPassed(vak_add.getPassed());
                        }
                    }

                    vakModels.add(vak);
                }
            }

            mListView = (ListView) findViewById(R.id.my_list_view);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     Intent intent = new Intent(YearOneActivity.this, EditActivity.class);

<<<<<<< HEAD
=======
//                     Bundle b = new Bundle();
//                     b.putString("user_id",String.valueOf(logginuser.getId()));
////                     b.putString("spec", String.valueOf(spec)));
//                     b.putString("vak",vakModels.get(position).getName());
//                     intent.putExtras(b);

>>>>>>> 340c44be58d6cc18329e17b646fede2ff4f1f263
                     Bundle c = new Bundle();
                     c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                     //b.putString("user_id",String.valueOf(logginuser.getId()));
                     c.putString("vak",vakModels.get(position).getName());
                     intent.putExtras(c);
<<<<<<< HEAD
=======

>>>>>>> 340c44be58d6cc18329e17b646fede2ff4f1f263
                     startActivity(intent);

                 }
             }
            );

            mAdapter = new vakkenAdapter(YearOneActivity.this, 0, vakModels);
            mListView.setAdapter(mAdapter);


        }

    }

}
