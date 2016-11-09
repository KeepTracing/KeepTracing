package com.example.jankovic.test.data.model;

/**
 * Created by wurfl_v on 01/11/16.
 */

public class Jump {

    public static final String TAG = Jump.class.getSimpleName();
    public static final String TABLE = "Jump";
    // Labels Table Columns names
    public static final String KEY_JumpId = "jumpId";
    public static final String KEY_SessionId = "sessionId";
    public static final String KEY_Air = "air";


    private int jumpId;
    private int sessionId;
    private int air;

    public int getJumpId() {
        return jumpId;
    }

    public void setJumpId(int jumpId) {
        this.jumpId = jumpId;
    }


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }


    public int getAir() {
        return air;
    }

    public void setAir(int air) {
        this.air = air;
    }

}

