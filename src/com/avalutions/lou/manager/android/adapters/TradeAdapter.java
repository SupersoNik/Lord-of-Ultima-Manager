package com.avalutions.lou.manager.android.adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.UltimaClient;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TradeAdapter extends ArrayAdapter<JSONObject> {
	
    private final Activity context;
    private final JSONObject[] trades;

    public TradeAdapter(Activity context, JSONArray trades) throws JSONException {
        super(context, R.layout.city_trade_item, UltimaClient.getJSONObjectArray(trades));
        this.context = context;
        this.trades = UltimaClient.getJSONObjectArray(trades);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.city_trade_item, null, true);
        TextView textView = (TextView) rowView.findViewById(R.id.city_trade_item_type);
        try {
            textView.setText(trades[position].getString(context.getString(R.string.player_poll_city_name)));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rowView;
    }

}
