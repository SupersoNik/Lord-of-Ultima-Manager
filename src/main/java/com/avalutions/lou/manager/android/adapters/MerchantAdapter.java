package com.avalutions.lou.manager.android.adapters;

import android.view.LayoutInflater;
import com.avalutions.lou.manager.net.commands.responses.poll.Merchant;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

public class MerchantAdapter extends SingleTypeAdapter<Merchant> {
    public MerchantAdapter(LayoutInflater inflater, int layoutResourceId) {
        super(inflater, layoutResourceId);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void update(int position, Merchant item) {

    }
}
