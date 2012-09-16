package com.avalutions.lou.manager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private static final String ARRAY = "t";
    private static final String TYPE = "t";
    private static final String AMOUNT = "c";
    private static final String MAX = "tc";

	private List<ConstrainedMeasurableUnit<TroopType>> troops = new ArrayList<ConstrainedMeasurableUnit<TroopType>>();
	
    public void add(TroopType unit, int amount, int capacity) {
    	troops.add(new ConstrainedMeasurableUnit<TroopType>(unit, amount, capacity));
    }

    public static Army fromJSON(JSONObject object) throws JSONException {
        Army army = new Army();
        JSONArray ta = object.getJSONArray(ARRAY);
        for (int i = 0; i < ta.length(); i++) {
            JSONObject o = ta.getJSONObject(i);
            army.add(
                    TroopType.values()[o.getInt(TYPE)],
                    o.getInt(AMOUNT),
                    o.getInt(MAX));
        }
        return army;
    }
}
