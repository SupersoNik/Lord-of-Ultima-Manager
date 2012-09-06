package com.avalutions.lou.manager.models;

public class MeasurableUnit<TUnit> {
	private TUnit unit;
	private int amount;
	
	public TUnit getUnit() {
        return unit;
    }

    public void setUnit(TUnit unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MeasurableUnit(TUnit unit, int amount) {
	    this.unit = unit;
	    this.amount = amount;
	}
}
