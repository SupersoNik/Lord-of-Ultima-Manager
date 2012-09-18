package com.avalutions.lou.manager.android.ui;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.SessionAdapter;
import com.avalutions.lou.manager.net.Session;

public class SessionListing extends ListActivity {
    private class ActivationTask extends AsyncTask<Session, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SessionListing.this, "", "Activating...");
        }

        @Override
        protected Void doInBackground(Session... sessions) {
            sessions[0].activate();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            Intent intent = new Intent(SessionListing.this, CityListing.class);
            SessionListing.this.startActivity(intent);
        }
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_list);

        setTitle("My Games");

        SessionAdapter adapter = new SessionAdapter(this, Session.getSessions());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Session session = (Session) this.getListAdapter().getItem(position);
        new ActivationTask().execute(session);
    }

}
