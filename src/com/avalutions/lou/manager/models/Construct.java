package com.avalutions.lou.manager.models;

public class Construct {
	private int level;
	private ConstructType type;
	
	public Construct(int level, ConstructType type) {
		this.level = level;
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ConstructType getType() {
		return type;
	}

	public void setType(ConstructType type) {
		this.type = type;
	}
}
