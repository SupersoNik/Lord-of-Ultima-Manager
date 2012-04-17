package com.avalutions.lou.manager.android;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.net.IPollHandler;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TradeRequest extends Activity implements IPollHandler {
    private LouSession session;
    private SessionManager world;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worlddetails);

        Intent intent = this.getIntent();
        this.session = (LouSession) intent.getSerializableExtra("session");
        this.world = SessionManager.getInstance(session);

        world.setPollHandler(this);
        world.setContext(this);
    }

    public void BucketChanged(String bucket) {
    }
}
