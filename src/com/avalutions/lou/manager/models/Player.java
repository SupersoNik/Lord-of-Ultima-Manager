package com.avalutions.lou.manager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String CITY_ARRAY = "Cities";

	private String name;
	private int playerId;
	private int rank;
    private int score;
    private List<CityHeader> cities;

    public Player(int id, String name) {
        this.playerId = id;
        this.name = name;
        this.cities = new ArrayList<CityHeader>();
    }

	public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setScore(int score) {
        this.score = score;
    }
	
	public int getId() {
		return playerId;
	}
	
	public String getName() {
		return name;
	}

    public void addCity(CityHeader city) {
        cities.add(city);
    }

    public CityHeader[] getCities() {
        return cities != null ? cities.toArray(new CityHeader[cities.size()]) : null;
    }

    public static Player fromJSON(JSONObject object) throws JSONException {
        Player result = new Player(object.getInt(ID), object.getString(NAME));
        JSONArray cl = object.getJSONArray(CITY_ARRAY);
        for (int i = 0; i < cl.length(); i++) {
            JSONObject o = cl.getJSONObject(i);
            CityHeader city = CityHeader.fromJSON(o);
            result.addCity(city);
        }
        return result;
    }
}
