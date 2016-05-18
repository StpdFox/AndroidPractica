package com.example.surfacepro3.week2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String sex = "Male";

private static final String TAG = "MainActivity";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDatepicker();
    }
    public void Submit(View v){
        DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        EditText nameText = (EditText) findViewById(R.id.name1);
        EditText emailText = (EditText) findViewById(R.id.month);
        EditText phoneText = (EditText) findViewById(R.id.phone);


            if (TextUtils.isEmpty(nameText.getText()))
                Toast.makeText(MainActivity.this, "Fill in a Name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(phoneText.getText()))
                Toast.makeText(MainActivity.this, "Fill in a Phone Number", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(emailText.getText()))
                Toast.makeText(MainActivity.this, "Fill in an Email Adress", Toast.LENGTH_SHORT).show();
            else if (radioGroup.getCheckedRadioButtonId() == -1)
                Toast.makeText(MainActivity.this, "Select a Gender", Toast.LENGTH_SHORT).show();
            else if (datePicker.getDayOfMonth()==1&&datePicker.getMonth()==1&&datePicker.getYear()==1914)
                Toast.makeText(MainActivity.this,"Please select your Date of Birth", Toast.LENGTH_SHORT).show();
            else{

           radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup group, int checkedId) {

                   if(checkedId==R.id.femalebtn)sex="Female";
                   else if(checkedId==R.id.malebtn)sex ="Male";
               }
           });
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth()+1;
                int year = datePicker.getYear();
           Log.w(TAG, "Name: "+nameText.getText()+"\nEmail: "+emailText.getText()+"\nPhone: "+ phoneText.getText()+"\nSex: "+ sex + "\nDate of Birth: "+day+"/"+month+"/"+year);}
}
public void setDatepicker(){
    DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);

    datePicker.updateDate(1914,0,1);
    int day = datePicker.getDayOfMonth();
    int month = datePicker.getMonth();
    int year = datePicker.getYear();
}

}
