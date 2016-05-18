package com.example.surfacepro3.week1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hitcount extends AppCompatActivity {
int i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("Times Pressed: "+i);
        Button btn = (Button)findViewById(R.id.button);
        Button mail = (Button)findViewById(R.id.mailbtn);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("MyInt", i);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("MyInt");
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("Times Pressed: "+i);
    }
    public void Click(View v){
        i++;
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("Times Pressed: "+i);

    }
    public void Mail(View v){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Ik heb "+i+"x op een simpel knopje gedrukt");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

    }
}
