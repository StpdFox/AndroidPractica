package com.example.surfacepro3.week3.Database;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surfacepro3.week3.R;

/**
 * Created by Surface Pro 3 on 15-5-2016.
 */
public class SearchContactActivity extends Activity {
    TextView Display_Month, Display_Day;
    EditText Search_Name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact_layout);
        Search_Name = (EditText)findViewById(R.id.search_name);
        Display_Month = (TextView) findViewById(R.id.display_month);
        Display_Day = (TextView)findViewById(R.id.display_day);
        Display_Month.setVisibility(View.GONE);
        Display_Day.setVisibility(View.GONE);
    }

    public void searchContact(View view){
        search_name = Search_Name.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(search_name,sqLiteDatabase);
        if(cursor.moveToFirst()){
            String DAY = cursor.getString(0);
            String MONTH = cursor.getString(1);
            Display_Day.setText(DAY);
            Display_Month.setText(MONTH);
            Display_Month.setVisibility(View.VISIBLE);
            Display_Day.setVisibility(View.VISIBLE);
        }
    }
    public void deleteContact(View view){
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        userDbHelper.deleteInformation(search_name,sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Contact Deleted", Toast.LENGTH_SHORT).show();
        Display_Month = (TextView) findViewById(R.id.display_month);
        Display_Day = (TextView)findViewById(R.id.display_day);
        Display_Month.setVisibility(View.GONE);
        Display_Day.setVisibility(View.GONE);
        Search_Name = (EditText)findViewById(R.id.search_name);
        Search_Name.setText("");
    }
}
