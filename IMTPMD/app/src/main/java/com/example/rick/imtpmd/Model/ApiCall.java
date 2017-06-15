package com.example.rick.imtpmd.Model;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import static com.example.dick.firstaidapp.R.id.progressBar;

/**
 * Created by rick on 9-6-2017.
 */

public class ApiCall extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private String url;


    public ApiCall(String url){
        this.url = url;
    }
    //protected void onPreExecute() {

    //}

    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL(this.url);
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
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        //Gson gson = new Gson();
        //User[] afspraken = gson.fromJson(response, User[].class);
        //Log.i( "INFO" , " : "+response );

    }
}