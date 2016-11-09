package com.example.jankovic.test;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.ListActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jankovic.test.data.DBHelper;
import com.example.jankovic.test.data.DatabaseManager;
import com.example.jankovic.test.data.model.Session;
import com.example.jankovic.test.data.repo.SessionRepo;

import org.w3c.dom.Comment;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button btnStart, btnPause, btnResume, btnStop;
    TextView txtTimer, txtAccelero;
    Handler customHandler = new Handler();
    LinearLayout container;

    SensorManager sensorManager;

    float x, y, z;
    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;

    Runnable updateTimerTheard = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            int hours = mins / 60;
            secs %= 60;
            mins %= 60;
            txtTimer.setText("" + String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }
    };

    public void validate(View view)
    {
        //Toast.makeText(this.getApplicationContext(), "YEAH !", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), TestDatabaseActivity.class));
    }

    private static String formatInterval(final long millis) {
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
    }

    private static Context context;
    private static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);

        final Session session = new Session();
        SessionRepo sessionRepo = new SessionRepo();

        btnStart = (Button)findViewById(R.id.btnStart);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnResume = (Button)findViewById(R.id.btnResume);
        btnStop = (Button)findViewById(R.id.btnStop);
        txtTimer = (TextView) findViewById(R.id.timerValue);
        container = (LinearLayout) findViewById(R.id.container);
        txtAccelero = (TextView) findViewById(R.id.acceleroValue);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerTheard, 0);
                btnStart.setVisibility(v.GONE);
                btnPause.setVisibility(v.VISIBLE);
                btnStop.setVisibility(v.VISIBLE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerTheard);
                btnPause.setVisibility(v.GONE);
                btnResume.setVisibility(v.VISIBLE);
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerTheard, 0);
                btnResume.setVisibility(v.GONE);
                btnPause.setVisibility(v.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Le temps: " + formatInterval(startTime));
                System.out.println("Le x: " + x);

                startTime = 0L;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updateTime = 0L;
                customHandler.removeCallbacks(updateTimerTheard);
                btnStart.setVisibility(v.VISIBLE);
                btnPause.setVisibility(v.GONE);
                btnResume.setVisibility(v.GONE);
                btnStop.setVisibility(v.GONE);
            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    public static Context getContext(){
        return context;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Movement
             x = event.values[0];
             y = event.values[1];
             z = event.values[2];
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
