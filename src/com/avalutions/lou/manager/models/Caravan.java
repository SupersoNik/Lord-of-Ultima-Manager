package com.avalutions.lou.manager.models;

import java.util.ArrayList;
import java.util.List;

public class Caravan {
    private CityHeader fromCity;
    private Player fromPlayer;
    private List<MeasurableUnit<Resource>> resources = new ArrayList<MeasurableUnit<Resource>>();
    
    public Caravan(CityHeader fromCity, Player fromPlayer) {
        this.fromCity = fromCity;
        this.fromPlayer = fromPlayer;
    }
    
    public void addResource(Resource unit, int amount) {
        resources.add(new MeasurableUnit<Resource>(unit, amount));
    }

    public CityHeader getFromCity() {
        return fromCity;
    }

    public Player getFromPlayer() {
        return fromPlayer;
    }

    public List<MeasurableUnit<Resource>> getResources() {
        return resources;
    }
}
