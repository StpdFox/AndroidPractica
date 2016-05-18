package com.example.surfacepro3.week3;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by Surface Pro 3 on 14-5-2016.
 */
public class ShowPopUp extends Activity {

    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    LinearLayout.LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click =true;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        popUp = new PopupWindow(this);
        layout = new LinearLayout(this);
        mainLayout =  new LinearLayout(this);
        tv = new TextView(this);
        but = new Button(this);
        but.setText("Click me");
        layout.setBackgroundColor(0xFFFFFFFF);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click){
                    popUp.showAtLocation(mainLayout, Gravity.BOTTOM,10,100);
                    ;
                    popUp.update(50,50,1000,200);
                    click=false;
                }else{
                    popUp.dismiss();
                    click=true;
                }
            }
        });
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        tv.setText("Pop pop");
        layout.addView(tv,params);
        popUp.setContentView(layout);
        mainLayout.addView(but,params);
        setContentView(mainLayout);
    }
}
