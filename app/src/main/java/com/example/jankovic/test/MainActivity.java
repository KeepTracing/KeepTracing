package com.example.jankovic.test;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnPause;
    TextView txtTimer;
    Handler customHandler = new Handler();
    LinearLayout container;

    long startTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L,updateTime=0L;

    Runnable updateTimerTheard = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff+timeInMilliseconds;
            int secs=(int)(updateTime/1000);
            int mins=secs/60;
            secs%=60;
            int miliseconds = (int)(updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%2d", secs)+":"
                                        +String.format("%3d", miliseconds));
            customHandler.postDelayed(this,0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnPause = (Button)findViewById(R.id.btnPause);
        txtTimer = (TextView) findViewById(R.id.timerValue);
        container = (LinearLayout)findViewById(R.id.container);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerTheard, 0);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerTheard);
            }
        });
    }
}
