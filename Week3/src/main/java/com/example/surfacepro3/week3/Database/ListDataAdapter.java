package com.example.surfacepro3.week3.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.surfacepro3.week3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surface Pro 3 on 14-5-2016.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class LayoutHandler{
        TextView NAME, DAY, MONTH;

    }
    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME=(TextView)row.findViewById(R.id.text_user_name);
            layoutHandler.DAY =(TextView)row.findViewById(R.id.text_user_day);
            layoutHandler.MONTH =(TextView)row.findViewById(R.id.text_user_month);
            row.setTag(layoutHandler);

        }
        else{
            layoutHandler=(LayoutHandler)row.getTag();

        }
        Dataprovider dataprovider = (Dataprovider)this.getItem(position);
        layoutHandler.NAME.setText(dataprovider.getName());
        layoutHandler.DAY.setText(dataprovider.getDay());
        layoutHandler.MONTH.setText(dataprovider.getMonth());

        return row;
    }
}
