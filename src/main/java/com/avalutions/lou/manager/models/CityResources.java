package com.avalutions.lou.manager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityResources extends ArrayList<CityResource> {
    private static final String ARRAY = "r";
    private static final String TYPE = "i";
    private static final String MAX = "m";
    private static final String AMOUNT = "b";
    private static final String PER = "d";
	
    public void add(Resource unit, int amount, int capacity, double increase) {
        CityResource resource = new CityResource(unit, amount, capacity, increase);
        add(resource);
    }
    
    public ConstrainedMeasurableUnit<Resource> getResource(Resource resource) {
        ConstrainedMeasurableUnit<Resource> result = null;
        for(ConstrainedMeasurableUnit<Resource> res : this) {
            if(res.getUnit().equals(resource)) {
                result = res;
            }
        }
        return result;
    }

    public static CityResources fromJSON(JSONObject object) throws JSONException {
        CityResources resources = new CityResources();
        JSONArray ra = object.getJSONArray(ARRAY);
        for (int i = 0; i < ra.length(); i++) {
            JSONObject o = ra.getJSONObject(i);
            resources.add(
                    Resource.values()[o.getInt(TYPE)],
                    o.getInt(AMOUNT),
                    o.getInt(MAX),
                    o.getDouble(PER));
        }
        return resources;
    }
}