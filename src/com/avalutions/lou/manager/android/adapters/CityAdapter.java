package com.avalutions.lou.manager.android.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.models.CityHeader;

public class CityAdapter extends ArrayAdapter<CityHeader> {
    private final Activity context;
    private final CityHeader[] cities;

    public CityAdapter(Activity context, CityHeader[] cities) {
        super(context, R.layout.worlditem, cities);
        this.context = context;
        this.cities = cities;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.cityitem, null, true);
        
        TextView textView = (TextView) rowView.findViewById(R.id.txtCityName);
        textView.setText(cities[position].getName());

        return rowView;
    }
}
