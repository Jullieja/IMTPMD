package com.example.rick.imtpmd.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.rick.imtpmd.R;

import java.util.List;

public class vakkenAdapter extends ArrayAdapter<Vak> {

    public vakkenAdapter(Context context, int resource, List<Vak> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null ) {
            vh = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.view_content_row, parent, false);

            //koppeling van viewholder aan de view
            vh.naam = (TextView) convertView.findViewById(R.id.name);
            vh.ects = (TextView) convertView.findViewById(R.id.ects);
//            vh.cijfer = (TextView) convertView.findViewById(R.id.grade);
//            vh.behaald = (CheckBox) convertView.findViewById(R.id.passed);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //koppeling van viewholder aan model
        Vak vak = getItem(position);
        vh.naam.setText((CharSequence) vak.getName());
        vh.ects.setText((CharSequence) vak.getEcts());
//        vh.cijfer.setText((CharSequence) vak.getGrade());
//        vh.behaald.setChecked((boolean) vak.passed);
        return convertView;
    }

    //declaratie van elementen
    private static class ViewHolder {
        //TextView name;
        //TextView ects;
        //TextView grade;

        TextView naam;
        TextView ects;
        //TextView cijfer;
        //CheckBox behaald;
        //public int year;
        //public String soort;
        //public String spec;

    }
}
