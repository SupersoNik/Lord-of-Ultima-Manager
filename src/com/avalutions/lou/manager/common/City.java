package com.avalutions.lou.manager.common;

import java.util.List;

public class City extends MappedEntity implements ICity {
	private List<Construct> buildings;
	private Construct cityWall;
	private Construct townHall;
    private ConstrainedMeasurableUnit<TradeType> carts;
    private ConstrainedMeasurableUnit<TradeType> ships;
	private CityResources resources;
	private Army troops;
	private int cityId;
	private String name;
	
	public List<Caravan> getCaravans() {
        return caravans;
    }

    public void setCaravans(List<Caravan> caravans) {
        this.caravans = caravans;
    }

    private int score;
	private List<Caravan> caravans;
	
	public City(int id, String name) {
	    this.cityId = id;
	    this.name = name;
	}
	
	public City(int id, String name, int x, int y) {
	    this.cityId = id;
	    this.name = name;
	    this.x = x;
	    this.y = y;
	}
	public City(int id, String name, int x, int y, LouSession session) {
        this.cityId = id;
        this.name = name;
        this.x = x;
        this.y = y;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return cityId;
	}
	
	public String getName() {
		return name;
	}

    public List<Construct> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Construct> buildings) {
        this.buildings = buildings;
    }

    public CityResources getResources() {
        return resources;
    }

    public void setResources(CityResources resources) {
        this.resources = resources;
    }

    public Army getTroops() {
        return troops;
    }

    public void setTroops(Army troops) {
        this.troops = troops;
    }

    public Construct getCityWall() {
        return cityWall;
    }

    public void setCityWall(Construct cityWall) {
        this.cityWall = cityWall;
    }

    public Construct getTownHall() {
        return townHall;
    }

    public void setTownHall(Construct townHall) {
        this.townHall = townHall;
    }

    public ConstrainedMeasurableUnit<TradeType> getCarts() {
        return carts;
    }

    public void setCarts(ConstrainedMeasurableUnit<TradeType> carts) {
        this.carts = carts;
    }

    public ConstrainedMeasurableUnit<TradeType> getShips() {
        return ships;
    }

    public void setShips(ConstrainedMeasurableUnit<TradeType> ships) {
        this.ships = ships;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
