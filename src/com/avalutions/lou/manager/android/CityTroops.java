package com.avalutions.lou.manager.android;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.net.IPollHandler;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

public class CityTroops extends ListActivity implements IPollHandler {
    private SessionManager world;
    private LouSession session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.city_troops);

        Intent intent = this.getIntent();
        this.session = (LouSession) intent.getSerializableExtra("session");
        this.world = SessionManager.getInstance(session);

        world.setPollHandler(this);
        world.setContext(this);
	}

    public void BucketChanged(String bucket) {
    }

}
