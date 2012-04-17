package com.avalutions.lou.manager.common;

import java.util.ArrayList;

public class CityResources extends ArrayList<CityResource> {
	/**
     * 
     */
    private static final long serialVersionUID = 6208316122233340345L;
	
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
}