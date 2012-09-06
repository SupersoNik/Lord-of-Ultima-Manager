package com.avalutions.lou.manager.models;

public class MappedEntity {
	
	protected int x;
	protected int y;
	
	public String getLocation() {
		return String.valueOf(x) + ":" + String.valueOf(y);
	}
	
}
