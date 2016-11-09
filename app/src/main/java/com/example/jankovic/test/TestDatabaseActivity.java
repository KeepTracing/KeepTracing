package com.example.jankovic.test;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jankovic.test.data.model.Session;
import com.example.jankovic.test.data.repo.SessionRepo;

import java.util.List;
import java.util.Random;


/**
 * Created by wurfl_v on 11/10/16.
 */

public class TestDatabaseActivity extends ListActivity {

    TextView txtSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //SessionRepo sessionRepo = new SessionRepo();

        List<Session> values = SessionRepo.getAllSessions();

        for (int i = 0 ; i < values.size() ; i++){
            System.out.print("Location - " +values.get(i).getLocation() +
                    "\nDate - "+values.get(i).getDate() +
                    "\nduree - "+values.get(i).getDuration() +
                    "\nLocation - "+values.get(i).getLocation());
        }

        // utilisez SimpleCursorAdapter pour afficher les
        // éléments dans une ListView
        ArrayAdapter<Session> adapter = new ArrayAdapter<Session>(this,
                android.R.layout.simple_list_item_1, values);
        System.out.println("Session fuckg: " + values);

        setListAdapter(adapter);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }


    // Sera appelée par l'attribut onClick
    // des boutons déclarés dans main.xml
   /* public void onClick(View view) {
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
    }*/

}
