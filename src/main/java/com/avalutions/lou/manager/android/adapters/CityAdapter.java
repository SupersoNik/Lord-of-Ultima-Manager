package com.avalutions.lou.manager.android.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.commands.responses.poll.PlayerCity;

public class CityAdapter extends ArrayAdapter<PlayerCity> {
    private final Activity context;
    private final PlayerCity[] cities;

    public CityAdapter(Activity context, PlayerCity[] cities) {
        super(context, R.layout.world_list_item, cities);
        this.context = context;
        this.cities = cities;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.city_list_item, null, true);

        TextView textView = (TextView) rowView.findViewById(R.id.txtCityName);
        textView.setText(cities[position].name);

        return rowView;
    }
}
