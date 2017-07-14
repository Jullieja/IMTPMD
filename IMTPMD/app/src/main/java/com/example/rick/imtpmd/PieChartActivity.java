package com.example.rick.imtpmd;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.rick.imtpmd.Database.DatabaseHelper;
import com.example.rick.imtpmd.Database.DatabaseInfo;
import com.example.rick.imtpmd.Model.User;
import com.example.rick.imtpmd.Model.Vak;
import com.example.rick.imtpmd.Model.vakkenAdapter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.google.gson.Gson;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {
    private PieChart mChart;
    public static final int MAX_ECTS = 60;
    public static int currentEcts = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        new VakkenTask().execute();



        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        View view =getSupportActionBar().getCustomView();
        ImageButton imageButton= (ImageButton)view.findViewById(R.id.go_b_button);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), PickYearActivity.class);
                Bundle c = new Bundle();
                final Bundle b = getIntent().getExtras();
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                intent.putExtras(c);
               startActivity(intent);
            }
        });


        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription(" description ");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        //setData(0);

//        Button fab = (Button) findViewById(R.id.plusTweeTest);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentEcts < MAX_ECTS) {
//                    setData(currentEcts += 2);
//                } else {
//                     setData(currentEcts = 0);
//                }
//            }
//        });
    }

    public class VakkenTask extends AsyncTask<Void, Void, String> {

        //private ListView mListView;
        //private vakkenAdapter mAdapter;
        private List<Vak> vakModels = new ArrayList<>();
        private List<Vak> vakkenlijst = new ArrayList<>();

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

                spec = b.getString("spec");

                userGegevens = b.getStringArrayList("userGegevens");
                username = userGegevens.get(1);
                user_id = userGegevens.get(0);

                logginuser.setId(Integer.parseInt(user_id));
            }




            Log.i("INFO", " : " + username + " " + user_id + " " + spec);

            DatabaseHelper dbHelper = DatabaseHelper.getHelper(PieChartActivity.this);
            Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.user,new String[]{"*"/*"vak_name = "+b.getString("vak")*/},"user_id = '"+b.getStringArrayList("userGegevens").get(0)+"'" ,null,null,null,null);

            int j=0;
            if (rs != null && rs.moveToFirst()) {

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
            int ect_cnt_1 = 0;
            int ect_cnt_2 = 0;
            int ect_cnt_34 = 0;
            int ect_cnt_234 = 0;
            final Bundle d = getIntent().getExtras();

            for (Vak vak : vakken) {
                    for(Vak vak_add : vakkenlijst){
                        if(vak.getName().equals(vak_add.getName())){
                            //Log.e("MATCH FOUND: ",vak.getName()+vak_add.getName());
                            vak.setUser_id(vak_add.getUser_id());
                            vak.setGrade(vak_add.getGrade());
                            vak.setPassed(vak_add.getPassed());
                        }
                    }
                    if(vak.getPassed() != null) {
                        if (vak.getPassed().equals("true")) {
//                            Log.e(vak.getPassed(), vak.getEcts() + " Behaald met ects");
//                            int punt = Integer.valueOf(vak.getEcts());
//                            Log.e(String.valueOf(punt), "ect");//
//                            Log.e(String.valueOf(puntenteller), "totaal");
                            //Log.e("vak year:",vak.getYear()+" vak ects"+vak.getEcts()+" "+vak.getName());
                            //Log.e("",vak.getYear());

                            switch(vak.getYear()){
                                case "1":
                                    ect_cnt_1 += Integer.valueOf(vak.getEcts());
                                    //og.e("1",String.valueOf(ect_cnt_1));
                                    break;
                                case "2":
                                    if (vak.getSpec().equals(spec)) {
                                        ect_cnt_2 += Integer.valueOf(vak.getEcts());
                                    }
                                    Log.e("2",vak.getName()+ect_cnt_2+vak.getSpec()+spec);
                                    break;
                                case "34":
                                    ect_cnt_34 += Integer.valueOf(vak.getEcts());
                                    //Log.e("23",String.valueOf(ect_cnt_34));
                                    break;
                                case "234":
                                    ect_cnt_234 += Integer.valueOf(vak.getEcts());
                                    //Log.e("234",String.valueOf(ect_cnt_234));
                                    break;
                                default:
                                    //Log.e("def",vak.getYear());
                            }

                        }
                    }

                    vakModels.add(vak);
            }
            if(ect_cnt_234 > 12){
                ect_cnt_234 = 12;
            }
            int niet_behaalde_ec = 240;
            niet_behaalde_ec -= ect_cnt_1;
            niet_behaalde_ec -= ect_cnt_2;
            niet_behaalde_ec -= ect_cnt_34;
            niet_behaalde_ec -= ect_cnt_234;

            Log.e("not:"+niet_behaalde_ec, "1:"+ect_cnt_1+" 2:"+ect_cnt_2+" 34:"+ect_cnt_34+" 234:"+ect_cnt_234);
            //TextView totaalpunten = (TextView) findViewById(R.id.aantal_p);
            //totaalpunten.setText(String.valueOf(puntenteller));
            setData(niet_behaalde_ec,ect_cnt_1,ect_cnt_2,ect_cnt_34,ect_cnt_234);



        }



    }

    private void setData(int onbehaald,int jr1,int jr2,int jr34,int jr234) {
        //currentEcts = aantal;
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        yValues.add(new Entry(onbehaald, 1));
        xValues.add("Niet behaalde Ec");

        yValues.add(new Entry(jr1, 1));
        xValues.add("Behaalde Jaar 1");

        yValues.add(new Entry(jr2, 1));
        xValues.add("Behaalde Jaar 2");

        yValues.add(new Entry(jr34, 1));
        xValues.add("Behaalde Jaar 3 en 4");

        yValues.add(new Entry(jr234, 1));
        xValues.add("Behaalde keuzevakken");

        //  http://www.materialui.co/colors
           ArrayList<Integer> colors = new ArrayList<>();
             //if (currentEcts <10) {
                 colors.add(Color.rgb(244,81,30));
             //} else if (currentEcts < 40){
                 colors.add(Color.rgb(235,0,0));
             //} else if  (currentEcts < 50) {
                 colors.add(Color.rgb(253,216,53));
             //} else {
                colors.add(Color.rgb(67,160,71));
            // }
             colors.add(Color.rgb(255,0,0));
            colors.add(Color.rgb(0,160,71));
             PieDataSet dataSet = new PieDataSet(yValues, "ECTS");
             dataSet.setColors(colors);

             PieData data = new PieData(xValues, dataSet);
             mChart.setData(data); // bind dataset aan chart.
             mChart.invalidate();  // Aanroepen van een redraw
             Log.d("aantal =", ""+currentEcts);

         }

    }







