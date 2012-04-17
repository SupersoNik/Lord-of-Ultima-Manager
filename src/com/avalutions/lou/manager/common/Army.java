package com.avalutions.lou.manager.common;

import java.util.ArrayList;
import java.util.List;

public class Army {

	private List<ConstrainedMeasurableUnit<TroopType>> troops = new ArrayList<ConstrainedMeasurableUnit<TroopType>>();
	
    public void add(TroopType unit, int amount, int capacity) {
    	troops.add(new ConstrainedMeasurableUnit<TroopType>(unit, amount, capacity));
    }

}
