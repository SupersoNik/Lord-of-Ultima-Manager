package com.avalutions.lou.manager.android.adapters;

import android.view.LayoutInflater;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.commands.responses.poll.Unit;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 1:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class UnitAdapter extends SingleTypeAdapter<Unit> {

    private final Converter converter;
    private final Lookup lookup;

    public UnitAdapter(LayoutInflater inflater, int resourceId, List<Unit> objects, Converter converter, Lookup lookup) {
        super(inflater, resourceId);

        setItems(objects);

        this.converter = converter;
        this.lookup = lookup;
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.type, R.id.capacity, R.id.increase, R.id.current};
    }

    @Override
    protected void update(int position, Unit item) {
        setText(0, lookup.lookup(item.type));
        setNumber(1, item.maximum);
        setNumber(2, (long) converter.convert(item.increase));
        setNumber(3, item.amount);
    }
}
