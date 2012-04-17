package com.avalutions.lou.manager.android;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.City;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.net.IPollHandler;
import com.avalutions.lou.manager.net.SessionManager;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class CityDetails extends TabActivity implements IPollHandler {
	private TabHost mTabHost;
	private LouSession session;
	private SessionManager world;
    private final ProgressDialog dialog = new ProgressDialog(CityDetails.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.citydetails);

		Intent intent = this.getIntent();
		session = (LouSession) intent.getSerializableExtra("session");
		world = SessionManager.getInstance(session);

		world.setPollHandler(this);
		world.setContext(this);
		
		if(((City)session.getCurrentCity()).getResources() == null) {
            dialog.setMessage("Loading city...");
            dialog.show();
		}
		updateDetails();
	}

	private void setupTab(Class<?> cls, final String tag) {
		Intent intent = new Intent().setClass(this, cls);
        intent.putExtra("session", this.session);

		View tabview = createTabView(mTabHost.getContext(), tag);
		TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview)
				.setContent(intent);
		mTabHost.addTab(setContent);
	}

	private View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
	
	private void updateDetails() {
        City city = (City)session.getCurrentCity();
        TextView tv = (TextView)findViewById(R.id.city_details_city_name);
        tv.setText(city.getName());
	}
	
	private void updatePollDetails() {
        City city = (City)session.getCurrentCity();
        TextView tv = (TextView)findViewById(R.id.city_details_city_wall);
        tv.setText(String.valueOf(city.getCityWall().getLevel()));
        tv = (TextView)findViewById(R.id.city_details_town_hall);
        tv.setText(String.valueOf(city.getTownHall().getLevel()));

        if(mTabHost == null) {
            mTabHost = (TabHost) findViewById(android.R.id.tabhost);
            setupTab(CityTrade.class, "Trade");
            setupTab(CityTroops.class, "Troops");
        }
	}

    public void BucketChanged(String bucket) {
        if(bucket == getString(R.string.bucket_city_poll)) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            
            updatePollDetails();
        }
    }
}
