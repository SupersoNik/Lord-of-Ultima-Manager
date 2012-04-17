package com.avalutions.lou.manager.common;

import java.text.NumberFormat;

public class CityResource extends ConstrainedMeasurableUnit<Resource> {
    private double increase;

    public CityResource(Resource unit, int amount, int capacity, double increase) {
        super(unit, amount, capacity);
        this.increase = increase;
    }
    
    public double getIncrease() {
        return increase;
    }
    
    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        double per = increase * 60 * 60;
        return formatter.format(per) + "/hr";
    }
}
