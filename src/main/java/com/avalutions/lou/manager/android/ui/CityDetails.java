package com.avalutions.lou.manager.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.view.MenuItem;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.responses.poll.City;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.viewpagerindicator.TitlePageIndicator;
import roboguice.inject.InjectView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

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
        setContentView(R.layout.city_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        city = Session.getActive().world.getCurrentCity();

        setTitle(city.name);

        pager.setAdapter(new CityPagerAdapter(getSupportFragmentManager()));

        indicator.setViewPager(pager);
        pager.setCurrentItem(0);
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
}
