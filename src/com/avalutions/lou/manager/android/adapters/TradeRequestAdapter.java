package com.avalutions.lou.manager.android.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.commands.responses.RequestResourcesResponse;

import java.util.List;

public class TradeRequestAdapter extends ArrayAdapter<RequestResourcesResponse> {
    private final Activity context;
    private final RequestResourcesResponse[] items;

    public TradeRequestAdapter(Activity context, List<RequestResourcesResponse> items) {
        super(context, R.layout.request_resources, items);
        this.context = context;
        this.items = items.toArray(items.toArray(new RequestResourcesResponse[items.size()]));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.request_resources_item, null, true);
        
//        TradeRequestItem item = items[position];
//        TextView textView = (TextView) rowView.findViewById(R.id.request_resources_item_city);
//        textView.setText(item.getCity().getName());
//        textView = (TextView) rowView.findViewById(R.id.request_resources_item_total);
//        textView.setText(Integer.toString(item.getTotalAvailable().getAmount()));
//        textView = (TextView) rowView.findViewById(R.id.request_resources_item_land);
//        textView.setText(Integer.toString(item.getCartAvailable().getAmount()));
//        textView = (TextView) rowView.findViewById(R.id.request_resources_item_water);
//        textView.setText(Integer.toString(item.getShipAvailable().getAmount()));

        return rowView;
    }

}
