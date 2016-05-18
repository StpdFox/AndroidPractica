package com.example.surfacepro3.week4;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

public class MainActivity extends Activity implements View.OnClickListener {
TextView tv;
    String ret ="";
    EditText inputValue=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputValue = (EditText)findViewById(R.id.input);

        tv = (TextView) findViewById(R.id.textview1);
        Button b=(Button)findViewById(R.id.button1);
        b.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(getResources().getString(R.string.url));
                            URLConnection connection = url.openConnection();

                            String inputString = inputValue.getText().toString();

                            Log.d("intputString", inputString);

                            connection.setDoOutput(true);
                            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                            out.write(inputString);
                            out.close();

                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                            String returnstring = "";

                            while ((returnstring = in.readLine()) != null) {
                                ret = returnstring;
                            }
                            in.close();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(ret.toString());
                                }
                            });
                        } catch (IOException ioe) {
                            Log.e("HitCount", ioe.toString());

                        }

                    }
                }).start();
            }}