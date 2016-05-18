package com.example.surfacepro3.week3;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.surfacepro3.week3.Database.DataListActivity;
import com.example.surfacepro3.week3.Database.SearchContactActivity;
import com.example.surfacepro3.week3.Database.UserContract;
import com.example.surfacepro3.week3.Database.UserDbHelper;
import com.example.surfacepro3.week3.extendedcalendarview.CalendarActivity;
import com.example.surfacepro3.week3.extendedcalendarview.CalendarFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void add(View v){
        Intent intent = new Intent(this, DataListActivity.NewContactActivity.class);
        startActivity(intent);
    }

    public void viewContent(View v){
        Intent intent = new Intent(this, DataListActivity.class);
        startActivity(intent);
    }
    public void searchContact(View v){
        Intent intent = new Intent(this, SearchContactActivity.class);
        startActivity(intent);
    }
    public void viewCalendar(View v){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
    public void popuptest(){

    }}
