package com.avalutions.lou.manager.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.android.adapters.Lookup;
import com.avalutions.lou.manager.android.adapters.UnitGroupAdapter;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.responses.poll.City;
import roboguice.fragment.RoboFragment;

public class CityTroops extends RoboFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_troops, container, false);

        ListView resourcesView = (ListView) view.findViewById(R.id.troop_list);

        City city = Session.getActive().world.getCurrentCity();

        UnitGroupAdapter resourceAdapter = new UnitGroupAdapter(inflater, R.layout.unit_group_item, city.units, new Lookup() {
            @Override
            public String lookup(int id) {
                return "Unknown";
            }
        });
        resourcesView.setAdapter(resourceAdapter);

        return view;
    }

}
