package com.example.jankovic.test.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jankovic.test.data.DatabaseManager;
import com.example.jankovic.test.data.model.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wurfl_v on 02/11/16.
 */

public class SessionRepo  {

    private Session session;
    private DatabaseManager dbHelper;

    public SessionRepo(){

        session=  new Session();

    }

    public static String createTable(){
        return "CREATE TABLE " + Session.TABLE  + "("
                + Session.KEY_SessionId  + " integer PRIMARY KEY AUTOINCREMENT,,"
                + Session.KEY_UserId  + " INTEGER,"
                + Session.KEY_Duration + " VARCHAR,"
                + Session.KEY_Location + " VARCHAR,"
                + Session.KEY_Date + " DATE,"
                + Session.KEY_Speed + " DOUBLE)";
    }


    public static List<Session> getAllSessions() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase(); //ON CREE L'INSTANCE

        List<Session> sessions = new ArrayList<Session>(); //ON CREE LA LISTE DE SESSIONS QU'ON VA RENVOYER

        Cursor cursor; //ON EXECUTE LA QUERY
        cursor = db.rawQuery("SELECT * FROM Session", null);


        cursor.moveToFirst();

        int ciduser=cursor.getColumnIndex(Session.KEY_UserId);
        int cidsession=cursor.getColumnIndex(Session.KEY_SessionId);
        int cduration=cursor.getColumnIndex(Session.KEY_Duration);
        int clocation=cursor.getColumnIndex(Session.KEY_Location);

        while (!cursor.isAfterLast()) {
            Session session = cursorToSession(cursor, cidsession, ciduser, cduration, clocation);
            sessions.add(session);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return sessions;
    }

    private static Session cursorToSession(Cursor cursor, int sess, int user, int dura, int loca) {
        Session session = new Session();
        session.setSessionId(cursor.getInt(sess));
        session.setUserId(cursor.getInt(user));
        session.setDuration(cursor.getString(dura));
        session.setLocation(cursor.getString(loca));

        return session;
    }

    public static int insert(Session session) {
        int sessionId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        //values.put(Session.KEY_SessionId, session.getSessionId());
        values.put(Session.KEY_UserId, session.getUserId());
        values.put(Session.KEY_Duration, session.getDuration());
        values.put(Session.KEY_Location, session.getLocation());
        values.put(Session.KEY_Date, String.valueOf(session.getDate()));
        values.put(Session.KEY_Speed, session.getSpeed());

        System.out.println("Les valeurs: " + values);

        // Inserting Row
        sessionId=(int)db.insert(Session.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return sessionId;
    }

    /*public void supprimer(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }*/


    public static void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Session.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

}
