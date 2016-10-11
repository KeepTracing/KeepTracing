package com.example.jankovic.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wurfl_v on 10/10/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

        public static final String SESSION_KEY = "id";
        public static final String SESSION_TIME = "time";
        public static final String SESSION_DATE = "date";

        public static final String SESSION_TABLE_NAME = "Session";
        public static final String SESSION_TABLE_CREATE =
                "CREATE TABLE " + SESSION_TABLE_NAME + " (" +
                        SESSION_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        SESSION_TIME + " text not null, " +
                        SESSION_DATE + " DATE);";

        public DatabaseHandler(Context context){
            super(context, SESSION_TABLE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SESSION_TABLE_CREATE);
        }

    public static final String SESSION_TABLE_DROP = "DROP TABLE IF EXISTS " + SESSION_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SESSION_TABLE_DROP);
        onCreate(db);
    }

}