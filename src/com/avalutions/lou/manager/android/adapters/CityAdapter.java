package com.avalutions.lou.manager.android.adapters;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.City;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CityAdapter extends ArrayAdapter<City> {
    private final Activity context;
    private final City[] cities;

    public CityAdapter(Activity context, City[] cities) {
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
