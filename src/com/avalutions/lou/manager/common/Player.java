package com.avalutions.lou.manager.common;

import java.util.ArrayList;
import java.util.List;

public class Player implements IIdentifiable {
	private Alliance alliance;
	private String name;
	private int playerId;
	private List<City> cities;
	private int rank;
	public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

	public Player(int id, String name) {
	    this(id, name, null);
	}
	
	public Player(int id, String name, Alliance alliance) {
	    this.playerId = id;
	    this.name = name;
	    this.alliance = alliance;
	    this.cities = new ArrayList<City>();
	}
	
	public void AddCity(City city) {
	    cities.add(city);
	}
	
	public List<City> getCities() {
	    return cities;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return playerId;
	}
	
	public String getName() {
		return name;
	}

}
