package com.avalutions.lou.manager.android.adapters;

import java.util.List;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.TradeRequestItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TradeRequestAdapter extends ArrayAdapter<TradeRequestItem> {
    private final Activity context;
    private final TradeRequestItem[] items;

    public TradeRequestAdapter(Activity context, List<TradeRequestItem> items) {
        super(context, R.layout.request_resources, items);
        this.context = context;
        this.items = items.toArray(items.toArray(new TradeRequestItem[items.size()]));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.request_resources_item, null, true);
        
        TradeRequestItem item = items[position];
        TextView textView = (TextView) rowView.findViewById(R.id.request_resources_item_city);
        textView.setText(item.getCity().getName());
        textView = (TextView) rowView.findViewById(R.id.request_resources_item_total);
        textView.setText(Integer.toString(item.getTotalAvailable().getAmount()));
        textView = (TextView) rowView.findViewById(R.id.request_resources_item_land);
        textView.setText(Integer.toString(item.getCartAvailable().getAmount()));
        textView = (TextView) rowView.findViewById(R.id.request_resources_item_water);
        textView.setText(Integer.toString(item.getShipAvailable().getAmount()));

        return rowView;
    }

}
