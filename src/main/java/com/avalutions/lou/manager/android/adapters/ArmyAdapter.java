package com.avalutions.lou.manager.android.adapters;

import android.view.LayoutInflater;
import com.avalutions.lou.manager.net.commands.responses.poll.Army;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/17/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArmyAdapter extends SingleTypeAdapter<Army> {
    public ArmyAdapter(LayoutInflater inflater, int layoutResourceId) {
        super(inflater, layoutResourceId);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void update(int position, Army item) {
        setText(0, item.type);
        setText(1, item.cityName);
    }
}
