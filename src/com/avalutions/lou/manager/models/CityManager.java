package com.avalutions.lou.manager.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/2/12
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityManager {
    private Map<String, City> cities = new HashMap<String, City>();
    private City current;
    private List<CityChangedHandler> handlers = new ArrayList<CityChangedHandler>();

    public interface CityChangedHandler {
        public void CityChanged(City city);
    }

    public void setCity(String cityId) {
        current = cities.get(cityId);
        onCityChanged();
    }

    private void onCityChanged() {
        for(CityChangedHandler handler : handlers) {
            handler.CityChanged(current);
        }
    }

    public City[] getCities() {
        return cities.values().toArray(new City[cities.size()]);
    }
}
