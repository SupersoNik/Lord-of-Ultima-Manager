package com.avalutions.lou.manager.android.adapters;

import android.view.LayoutInflater;
import com.avalutions.lou.manager.net.commands.responses.poll.TroopQueueItem;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

/**
 * Copyright (c) 2012 All Right Reserved,
 * Developer: Benny Thompson
 * Date: 9/17/12
 * Summary:
 */
public class RecruitmentAdapter extends SingleTypeAdapter<TroopQueueItem> {
    public RecruitmentAdapter(LayoutInflater inflater, int layoutResourceId) {
        super(inflater, layoutResourceId);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void update(int position, TroopQueueItem item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
