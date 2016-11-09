package com.example.jankovic.test.data.model;

/**
 * Created by wurfl_v on 01/11/16.
 */

public class Accelero {

    public static final String TAG = Accelero.class.getSimpleName();
    public static final String TABLE = "Accelero";
    // Labels Table Columns names
    public static final String KEY_AcceleroId = "acceleroId";
    public static final String KEY_JumpId = "jumpId";
    public static final String KEY_X = "x_value";
    public static final String KEY_Y = "y_value";
    public static final String KEY_Z = "z_value";


    private int acceleroId;
    private int jumpId;
    private int x_value;
    private int y_value;
    private int z_value;


    public int getAcceleroId() {
        return acceleroId;
    }

    public void setAcceleroId (int acceleroId) {
        this.acceleroId = acceleroId;
    }

    public int getJumpId() {
        return jumpId;
    }

    public void setJumpId(int jumpId) {
        this.jumpId = jumpId;
    }


    public int getX() {
        return x_value;
    }

    public void setX(int x_value) {
        this.x_value = x_value;
    }


    public int getY() {
        return y_value;
    }

    public void setY(int y_value) {
        this.y_value = y_value;
    }


    public int getZ() {
        return z_value;
    }

    public void setZ(int z_value) {
        this.z_value = z_value;
    }

}
