package com.avalutions.lou.manager.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.Converter;
import com.avalutions.lou.manager.android.adapters.Lookup;
import com.avalutions.lou.manager.android.adapters.UnitAdapter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.responses.poll.City;
import roboguice.fragment.RoboFragment;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 1:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class CityOverview extends RoboFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_overview, container, false);

        TextView townHallView = (TextView) view.findViewById(R.id.town_hall_level);
        ListView resourcesView = (ListView) view.findViewById(R.id.resource_list);
        TextView cityWallView = (TextView) view.findViewById(R.id.city_wall_level);

        City city = Session.getActive().world.getCurrentCity();
        townHallView.setText(String.valueOf(city.townHallLevel));
        cityWallView.setText(String.valueOf(city.wallLevel));

        UnitAdapter resourceAdapter = new UnitAdapter(inflater, R.layout.unit_item, city.resources, new Converter() {
            @Override
            public float convert(float input) {
                return input * 60 * 60;
            }
        }, new Lookup() {
            @Override
            public String lookup(int id) {
                switch (id) {
                    case 1:
                        return "Wood";
                    case 2:
                        return "Stone";
                    case 3:
                        return "Iron";
                    case 4:
                        return "Food";
                }
                return null;
            }
        }
        );
        resourcesView.setAdapter(resourceAdapter);

        return view;
    }
}
