package com.example.jankovic.test;

import java.sql.Time;

/**
 * Created by wurfl_v on 11/10/16.
 */

public class Session {

    private long id;
    private String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String comment) {
        this.time = time;
    }

    // Sera utilisée par ArrayAdapter dans la ListView
    @Override
    public String toString() {
        return time;
    }
}