package com.avalutions.lou.manager.android.adapters;

import android.view.LayoutInflater;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.commands.responses.poll.UnitGroup;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnitGroupAdapter extends SingleTypeAdapter<UnitGroup> {
    private final Lookup lookup;

    public UnitGroupAdapter(LayoutInflater inflater, int layoutResourceId, List<UnitGroup> items, Lookup lookup) {
        super(inflater, layoutResourceId);
        this.lookup = lookup;

        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.group_type, R.id.group_avail, R.id.group_total};
    }

    @Override
    protected void update(int position, UnitGroup item) {
        setText(0, lookup.lookup(item.type));
        setNumber(1, item.count);
        setNumber(2, item.totalCount);
    }
}
