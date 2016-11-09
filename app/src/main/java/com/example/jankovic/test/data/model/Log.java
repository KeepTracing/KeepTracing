package com.example.jankovic.test.data.model;

import java.util.Date;

/**
 * Created by wurfl_v on 01/11/16.
 */

public class Log {

    public static final String TAG = Log.class.getSimpleName();
    public static final String TABLE = "Log";
    // Labels Table Columns names
    public static final String KEY_logId = "logId";
    public static final String KEY_Date = "date";
    public static final String KEY_Value = "value";


    private int logId;
    private Date date;
    private String value;

    public int getlogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


