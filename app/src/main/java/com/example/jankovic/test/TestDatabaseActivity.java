package com.example.jankovic.test;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


import java.util.List;
import java.util.Random;


/**
 * Created by wurfl_v on 11/10/16.
 */

public class TestDatabaseActivity extends ListActivity {

    private SessionsDataSource datasource; //TEST SQL

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new SessionsDataSource(this);
        datasource.open();

        List<Session> values = datasource.getAllSessions();

        // utilisez SimpleCursorAdapter pour afficher les
        // éléments dans une ListView
        ArrayAdapter<Session> adapter = new ArrayAdapter<Session>(this,
                android.R.layout.simple_list_item_1, values);
        System.out.println("Session fuckg: " + values);

        setListAdapter(adapter);
    }

    // Sera appelée par l'attribut onClick
    // des boutons déclarés dans main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Session> adapter = (ArrayAdapter<Session>) getListAdapter();
        Session session = null;
        switch (view.getId()) {
            case R.id.add:
                String[] sessions = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // enregistrer le nouveau commentaire dans la base de données
                session = datasource.createSession("Test");
                adapter.add(session);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    session = (Session) getListAdapter().getItem(0);
                    datasource.deleteSession(session);
                    adapter.remove(session);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
