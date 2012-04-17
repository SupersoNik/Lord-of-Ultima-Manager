package com.avalutions.lou.manager.common;

import java.util.ArrayList;
import java.util.List;

import com.avalutions.lou.manager.common.MeasurableUnit;

public class Caravan {
    private ICity fromCity;
    private Player fromPlayer;
    private List<MeasurableUnit<Resource>> resources = new ArrayList<MeasurableUnit<Resource>>();
    
    public Caravan(ICity fromCity, Player fromPlayer) {
        this.fromCity = fromCity;
        this.fromPlayer = fromPlayer;
    }
    
    public void addResource(Resource unit, int amount) {
        resources.add(new MeasurableUnit<Resource>(unit, amount));
    }

    public ICity getFromCity() {
        return fromCity;
    }

    public Player getFromPlayer() {
        return fromPlayer;
    }

    public List<MeasurableUnit<Resource>> getResources() {
        return resources;
    }
}
