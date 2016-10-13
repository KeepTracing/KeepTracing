package com.example.jankovic.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wurfl_v on 11/10/16.
 */

public class SessionsDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.SESSION_KEY,
            DatabaseHandler.SESSION_KEY};

    public SessionsDataSource(Context context) {
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Session createSession(String session) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.SESSION_TIME, session);
        long insertId = database.insert(DatabaseHandler.SESSION_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DatabaseHandler.SESSION_TABLE_NAME,
                allColumns, DatabaseHandler.SESSION_KEY + " = " + insertId, null,
                null, null, null);
        System.out.println("Session created with string: " + session);

        cursor.moveToFirst();
        Session newSession = cursorToSession(cursor);
        cursor.close();
        return newSession;
    }

    public void deleteSession(Session session) {
        long id = session.getId();
        System.out.println("Session deleted with id: " + id);
        database.delete(DatabaseHandler.SESSION_TABLE_NAME, DatabaseHandler.SESSION_KEY
                + " = " + id, null);
    }

    public List<Session> getAllSessions() {
        List<Session> sessions = new ArrayList<Session>();

        Cursor cursor = database.query(DatabaseHandler.SESSION_TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Session session = cursorToSession(cursor);
            sessions.add(session);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return sessions;
    }

    private Session cursorToSession(Cursor cursor) {
        Session session = new Session(0, null);
        session.setId(cursor.getLong(0));
        session.setTime(cursor.getString(1));
        return session;
    }
}