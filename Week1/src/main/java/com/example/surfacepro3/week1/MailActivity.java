package com.example.surfacepro3.week1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MailActivity extends AppCompatActivity {
Hitcount main = new Hitcount();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
    }
    public void Send(View v){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+main.i);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
