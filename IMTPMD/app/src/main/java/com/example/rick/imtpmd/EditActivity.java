package com.example.rick.imtpmd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rick.imtpmd.Database.DatabaseHelper;
import com.example.rick.imtpmd.Database.DatabaseInfo;
import com.example.rick.imtpmd.R;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ArrayList<String> userGegevens;
        Button save = (Button) findViewById(R.id.save);
        TextView vaknaam = (TextView) findViewById(R.id.vaknaam);
        final EditText cijfer = (EditText) findViewById(R.id.grade);

        final Bundle b = getIntent().getExtras();
        if (b != null) {
            userGegevens = b.getStringArrayList("userGegevens");
            String vak = b.getString("vak");
            String user_id = userGegevens.get(0);
            //Log.d("editacititytest: "+vak+" ",user_id);
            Toast t = Toast.makeText(EditActivity.this,"Click " + vak+user_id,Toast.LENGTH_SHORT);
            t.show();
        }


        /*ACTIONBARCREATE*/

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
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                intent.putExtras(c);
                startActivity(intent);
            }
        });

        /*//ACTIONBARCREATE*/

        vaknaam.setText(b.getString("vak"));
        cijfer.setText(b.getString("cijfer"));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getIntent().getExtras();
                //CheckBox behaald = (CheckBox) findViewById(R.id.passed);
                //String txtelement = cijfer.getText().toString();
                //Boolean isPassed = behaald.isChecked();

                Log.e("user_id", b.getStringArrayList("userGegevens").get(0));
                Log.e("vak", b.getString("vak"));
                Log.e("cijfer", cijfer.getText().toString());
                //Log.e("behaald", String.valueOf(isPassed));

                DatabaseHelper dbHelper = DatabaseHelper.getHelper(EditActivity.this);

                Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.user,new String[]{"*"/*"vak_name = "+b.getString("vak")*/},"vak_name = '"+b.getString("vak")+"' AND user_id = '"+b.getStringArrayList("userGegevens").get(0)+"'" ,null,null,null,null);
                int rijenteller = rs.getCount();
                Log.e("er is aantal rijen: ", String.valueOf(rijenteller));

                if(rs!=null && rs.getCount()>0){ // als de database gevuld is.
                    ContentValues values = new ContentValues();
                    values.put(DatabaseInfo.CourseColumn.vak_name,b.getString("vak"));
                    values.put(DatabaseInfo.CourseColumn.user_id,b.getStringArrayList("userGegevens").get(0));
                    if(!(cijfer.getText().toString().equals(""))) {
                        String cijfer_str_toint = cijfer.getText().toString();
                        float cijfer_int = Float.parseFloat(cijfer_str_toint);
                        if ((cijfer_int <= 10)) {
                            values.put(DatabaseInfo.CourseColumn.grade, cijfer.getText().toString());
                            if (cijfer_int >= 5.5) {
                                values.put(DatabaseInfo.CourseColumn.passed, String.valueOf(true));
                            } else {
                                values.put(DatabaseInfo.CourseColumn.passed, String.valueOf(false));
                            }
                        }
                    }
                    //dbHelper.update(DatabaseInfo.CourseTables.user, values ,"user_id = '"+b.getStringArrayList("userGegevens").get(0)+"'",null);
                    dbHelper.update(DatabaseInfo.CourseTables.user, values ,"user_id = ? AND vak_name = ? ",new String[]{b.getStringArrayList("userGegevens").get(0),b.getString("vak")});

                    //rs.moveToFirst();
                    Log.e("##", "HIERHIERHIER##HIERHIERHIER##HIERHIERHIER##HIERHIERHIER UPDATE");

                } else {                        // als de database leeg is.
                    Log.e("##", "HIERHIERHIER##HIERHIERHIER##HIERHIERHIER##HIERHIERHIER INSERT");
                    ContentValues values = new ContentValues();
                    values.put(DatabaseInfo.CourseColumn.vak_name,b.getString("vak"));
                    values.put(DatabaseInfo.CourseColumn.user_id,b.getStringArrayList("userGegevens").get(0));
                    if(!(cijfer.getText().toString().equals(""))) {
                        String cijfer_str_toint = cijfer.getText().toString();
                        float cijfer_int = Float.parseFloat(cijfer_str_toint);
                        if ((cijfer_int <= 10)) {
                            values.put(DatabaseInfo.CourseColumn.grade, cijfer.getText().toString());
                            if (cijfer_int >= 5.5) {
                                values.put(DatabaseInfo.CourseColumn.passed, String.valueOf(true));
                            } else {
                                values.put(DatabaseInfo.CourseColumn.passed, String.valueOf(false));
                            }
                        }
                    }

                    dbHelper.insert(DatabaseInfo.CourseTables.user,null,values);
                }


                Intent intent = new Intent(EditActivity.this, PickYearActivity.class);
                switch(b.getString("jaar")){
                    case "1":
                         intent = new Intent(EditActivity.this, YearOneActivity.class);
                        break;
                    case "2":
                         intent = new Intent(EditActivity.this, YearTwoActivity.class);
                        break;
                    case "34":
                        intent = new Intent(EditActivity.this, YearThreeFourActivity.class);
                        break;
                    case "keuzevak":
                        intent = new Intent(EditActivity.this, KeuzeVakActivity.class);
                        break;
                }


                Bundle c = new Bundle();
                c.putStringArrayList("userGegevens", b.getStringArrayList("userGegevens"));
                c.putString("jaar",b.getString("jaar"));
                //Log.e("######################",b.getString("spec"));
                if(b.getString("spec")!=null){
                    c.putString("spec",b.getString("spec"));
                    Log.e("######################",b.getString("spec"));
                }
                intent.putExtras(c);
                startActivity(intent);

            }
        });
    }


}
