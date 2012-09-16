package com.avalutions.lou.manager.android;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import com.avalutions.lou.manager.net.commands.responses.poll.City;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/14/12
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class CityPagerAdapter extends PagerAdapter {
    public CityPagerAdapter(Context context, City city, FragmentManager supportFragmentManager) {
    }

    @Override
    public int getCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
