package com.avalutions.lou.manager.android;

import com.avalutions.lou.manager.android.adapters.WorldAdapter;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class WorldListing extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LouSession[] sessions = SessionManager.getSessions();

		WorldAdapter adapter = new WorldAdapter(this, sessions);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);

	    LouSession session = (LouSession)this.getListAdapter().getItem(position);

        Intent intent = new Intent(getBaseContext(), CityListing.class);
        intent.putExtra("session", session);
        startActivity(intent);
	    
	}

}
