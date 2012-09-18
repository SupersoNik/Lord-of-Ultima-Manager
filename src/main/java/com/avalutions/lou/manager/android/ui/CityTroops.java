package com.avalutions.lou.manager.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.UnitGroupAdapter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.responses.poll.City;
import com.avalutions.lou.manager.net.data.World;
import roboguice.fragment.RoboFragment;

public class CityTroops extends RoboFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_page_troops, container, false);

        ListView resourcesView = (ListView) view.findViewById(R.id.troop_list);

        City city = Session.getActive().world.getCurrentCity();

        UnitGroupAdapter resourceAdapter = new UnitGroupAdapter(inflater, R.layout.unit_group_item, city.units, Session.getActive().world.getLookup(World.UNITS));
        resourcesView.setAdapter(resourceAdapter);

        return view;
    }

}
