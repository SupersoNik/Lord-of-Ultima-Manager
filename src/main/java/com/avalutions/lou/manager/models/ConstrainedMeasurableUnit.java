package com.avalutions.lou.manager.models;

public class ConstrainedMeasurableUnit<T> extends MeasurableUnit<T> {
	private int capacity;
	
	public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ConstrainedMeasurableUnit(T unit, int amount, int capacity) {
	    super(unit, amount);
	    this.capacity = capacity;
	}
}
