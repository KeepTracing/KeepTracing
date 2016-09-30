package com.example.jankovic.test;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button btnStart,btnPause;
    TextView txtTimer, txtAccelero;
    Handler customHandler = new Handler();
    LinearLayout container;

    SensorManager sensorManager;

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
        txtAccelero = (TextView) findViewById(R.id.acceleroValue);

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

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Movement
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            txtAccelero.setText(" x : " + x + "\n y : " + y + "\n z : " + z);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
