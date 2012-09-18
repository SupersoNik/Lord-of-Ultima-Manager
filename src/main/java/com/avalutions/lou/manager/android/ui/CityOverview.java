package com.avalutions.lou.manager.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.UnitAdapter;
import com.avalutions.lou.manager.android.converters.ResourceConverter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.responses.poll.City;
import com.avalutions.lou.manager.net.data.World;
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
        View view = inflater.inflate(R.layout.city_page_overview, container, false);

        TextView townHallView = (TextView) view.findViewById(R.id.town_hall_level);
        ListView resourcesView = (ListView) view.findViewById(R.id.resource_list);
        TextView cityWallView = (TextView) view.findViewById(R.id.city_wall_level);

        City city = Session.getActive().world.getCurrentCity();
        townHallView.setText(String.valueOf(city.townHallLevel));
        cityWallView.setText(String.valueOf(city.wallLevel));

        UnitAdapter resourceAdapter = new UnitAdapter(inflater, R.layout.unit_item, city.resources, new ResourceConverter(), Session.getActive().world.getLookup(World.RESOURCES));
        resourcesView.setAdapter(resourceAdapter);

        return view;
    }
}
