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

public class vakkenAdapter extends ArrayAdapter<vakModel> {

    public vakkenAdapter(Context context, int resource, List<vakModel> objects){
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
            vh.cijfer = (TextView) convertView.findViewById(R.id.cijfer);
            vh.behaald = (CheckBox) convertView.findViewById(R.id.behaald);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //koppeling van viewholder aan model
        vakModel vak = getItem(position);
        vh.naam.setText((CharSequence) vak.naam);
        vh.ects.setText((CharSequence) vak.getEcts());
        vh.cijfer.setText((CharSequence) vak.getCijfer());
        vh.behaald.setChecked((boolean) vak.behaald);
        return convertView;
    }

    //declaratie van elementen
    private static class ViewHolder {
        //TextView name;
        //TextView ects;
        //TextView grade;

        TextView naam;
        TextView ects;
        TextView cijfer;
        CheckBox behaald;
        //public int jaar;
        //public String soort;
        //public String spec;

    }
}
