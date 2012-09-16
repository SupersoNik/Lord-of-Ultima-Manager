package com.avalutions.lou.manager.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/14/12
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class CityPagerAdapter extends FragmentPagerAdapter {

    public CityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Overview";
            case 1:
                return "Troops";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CityOverview();
            case 1:
                return new CityTroops();
        }
        return null;
    }
}
