package com.avalutions.lou.manager.android.adapters;

import android.view.LayoutInflater;
import com.avalutions.lou.manager.net.commands.responses.poll.BuildingQueueItem;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildingQueueAdapter extends SingleTypeAdapter<BuildingQueueItem> {
    public BuildingQueueAdapter(LayoutInflater inflater, int layoutResourceId, List<BuildingQueueItem> items) {
        super(inflater, layoutResourceId);

        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{};
    }

    @Override
    protected void update(int position, BuildingQueueItem item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
