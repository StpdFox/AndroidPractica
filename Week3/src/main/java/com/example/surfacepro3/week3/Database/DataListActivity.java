package com.example.surfacepro3.week3.Database;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.surfacepro3.week3.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DataListActivity extends Activity {
ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        listView = (ListView)findViewById(R.id.list_view);
        listDataAdapter=new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
       // String selection = UserContract.NewUserInfo.USER_DOB+" LIKE ?";
        //String[] selection_args = {"1"};
        cursor = userDbHelper.getInformations(sqLiteDatabase);
        if(cursor.moveToFirst()){

            do {
                    String name, day, month;
                    name = cursor.getString(0);
                    day = cursor.getString(1);
                    month = cursor.getString(2);
                    Dataprovider dataprovider = new Dataprovider(name, day, month);
                    listDataAdapter.add(dataprovider);
        }while(cursor.moveToNext());

    }}

    public static class NewContactActivity extends Activity {
        EditText ContactName, ContactDay, ContactMonth;
        Context context = this;
        UserDbHelper userDbHelper;
        SQLiteDatabase sqLiteDatabase;
        private EditText DateEtxt;
        private DatePickerDialog datePickerDialog;
        private SimpleDateFormat dateFormatter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_contact);

            ContactName = (EditText)findViewById(R.id.ContactName);
            ContactMonth = (EditText)findViewById(R.id.ContactMonth);
            ContactDay = (EditText)findViewById(R.id.ContactDay);
            dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
//            findViewsById();
//            setDateTimeField();
        }


        public void save(View view){
            String name = ContactName.getText().toString();
            String day = ContactDay.getText().toString();
            String month = ContactMonth.getText().toString();
            userDbHelper = new UserDbHelper(context);
            sqLiteDatabase = userDbHelper.getWritableDatabase();
            userDbHelper.addInformations(name,day,month,sqLiteDatabase);
            Toast.makeText(NewContactActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
            userDbHelper.close();


        }
//        private void findViewsById(){
//            DateEtxt = (EditText) findViewById(R.id.etxt_date);
//            DateEtxt.setInputType(InputType.TYPE_NULL);
//            DateEtxt.requestFocus();
//        }
//        private void setDateTimeField(){
//
//            Calendar newCalendar = Calendar.getInstance();
//            datePickerDialog = new DatePickerDialog(this, new android.app.DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                    Calendar newDate = Calendar.getInstance();
//                    newDate.set(year,monthOfYear,dayOfMonth);
//                    DateEtxt.setText(dateFormatter.format(newDate.getTime()));
//                }
//            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    }

