package com.avalutions.lou.manager.android;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.models.City;
import com.avalutions.lou.manager.net.Session;

public class CityDetails extends TabActivity {
	private TabHost mTabHost;
    private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.citydetails);

        dialog = new ProgressDialog(this);
		
		if((Session.getActive().world.getCurrentCity()).getResources() == null) {
            dialog.setMessage("Loading city...");
            dialog.show();
		}
		updateDetails();
	}

	private void setupTab(Class<?> cls, final String tag) {
		Intent intent = new Intent().setClass(this, cls);

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
        City city = Session.getActive().world.getCurrentCity();
        TextView tv = (TextView)findViewById(R.id.city_details_city_name);
        tv.setText(city.getName());
	}
	
	private void updatePollDetails() {
        City city = Session.getActive().world.getCurrentCity();
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
}
