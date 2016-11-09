package com.example.jankovic.test.data.model;

import java.util.Date;

/**
 * Created by wurfl_v on 25/10/16.
 */

public class Session {

    public static final String TAG = Session.class.getSimpleName();
    public static final String TABLE = "Session";
    // Labels Table Columns names
    public static final String KEY_SessionId = "sessionId";
    public static final String KEY_UserId = "userId";
    public static final String KEY_Duration = "duration";
    public static final String KEY_Location = "location";
    public static final String KEY_Date = "date";
    public static final String KEY_Speed = "speed";


    private int sessionId;
    private int userId;
    private String duration;
    private String location;
    private Date date;
    private double speed;


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
