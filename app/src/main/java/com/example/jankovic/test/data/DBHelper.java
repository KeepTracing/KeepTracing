package com.example.jankovic.test.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jankovic.test.MainActivity;
import com.example.jankovic.test.data.model.User;
import com.example.jankovic.test.data.model.Accelero;
import com.example.jankovic.test.data.model.Jump;
import com.example.jankovic.test.data.model.Session;
import com.example.jankovic.test.data.model.Log;
import com.example.jankovic.test.data.repo.AcceleroRepo;
import com.example.jankovic.test.data.repo.JumpRepo;
import com.example.jankovic.test.data.repo.LogRepo;
import com.example.jankovic.test.data.repo.SessionRepo;
import com.example.jankovic.test.data.repo.UserRepo;


/**
 * Created by wurfl_v on 01/11/16.
 */

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =8;
    // Database Name
    private static final String DATABASE_NAME = "KeepTracing.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper( ) {
        super(MainActivity.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(SessionRepo.createTable());
       /* db.execSQL(UserRepo.createTable());
        db.execSQL(JumpRepo.createTable());
        db.execSQL(AcceleroRepo.createTable());
        db.execSQL(LogRepo.createTable());
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Session.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Jump.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Log.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Accelero.TABLE);
        onCreate(db);
    }

}
