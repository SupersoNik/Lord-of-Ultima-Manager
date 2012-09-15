package com.avalutions.lou.manager.android;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.actionbarsherlock.view.MenuItem;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.responses.poll.City;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.viewpagerindicator.TitlePageIndicator;
import roboguice.inject.InjectView;

public class CityDetails extends RoboSherlockFragmentActivity {

    @InjectView(R.id.tpi_header)
    private TitlePageIndicator indicator;

    @InjectView(R.id.vp_pages)
    private ViewPager pager;

    private City city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.citydetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        city = Session.getActive().world.getCurrentCity();

        setTitle(city.name);

        pager.setAdapter(new CityPagerAdapter(getResources(), city, getSupportFragmentManager()));

        indicator.setViewPager(pager);
        pager.setCurrentItem(1);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, CityListing.class);
                homeIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        tv.setText(city.name);
	}
	
	private void updatePollDetails() {
        City city = Session.getActive().world.getCurrentCity();
        TextView tv = (TextView)findViewById(R.id.city_details_city_wall);
        tv.setText(String.valueOf(city.wallLevel));
        tv = (TextView)findViewById(R.id.city_details_town_hall);
        tv.setText(String.valueOf(city.townHallLevel));
	}
}
