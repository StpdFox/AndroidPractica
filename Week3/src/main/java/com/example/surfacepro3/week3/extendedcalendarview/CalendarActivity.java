package com.example.surfacepro3.week3.extendedcalendarview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.surfacepro3.week3.Database.DataListActivity;
import com.example.surfacepro3.week3.MainActivity;
import com.example.surfacepro3.week3.R;

public class CalendarActivity extends Activity {
    PopupWindow popUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        popUp = new PopupWindow(this);
        ExtendedCalendarView extendedCalendarView =(ExtendedCalendarView)findViewById(R.id.calendar);
        extendedCalendarView.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener() {
            @Override
            public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {
                Intent intent = new Intent(getApplicationContext(), DataListActivity.class);
                startActivity(intent);
            }
        });
    }
}
