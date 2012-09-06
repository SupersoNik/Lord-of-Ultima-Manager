package com.avalutions.lou.manager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Alliance {
    private static final String ID = "id";
    private static final String NAME = "n";
    private static final String MEMBER_ARRAY = "m";
    private static final String MEMBER_ID = "i";
    private static final String MEMBER_NAME = "n";
    private static final String MEMBER_RANK = "ra";

	private String name;
	private int allianceId;
	private List<Player> players = new ArrayList<Player>();
	
	public Alliance(int id, String name) {
	    this.allianceId = id;
	    this.name = name;
	}
	
	public void add(Player player) {
	    players.add(player);
	}
    
    public void add(int id, String name, int rank) {
        Player player = new Player(id, name);
        player.setRank(rank);
        players.add(player);
    }
	
	public List<Player> getMembers() {
		return null;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return allianceId;
	}
	
	public String getName() {
		return name;
	}

    public static Alliance fromJSON(JSONObject object) throws JSONException {
        Alliance result = new Alliance(object.getInt(ID), object.getString(NAME));
        JSONArray cl = object.getJSONArray(MEMBER_ARRAY);
        for (int i = 0; i < cl.length(); i++) {
            JSONObject o = cl.getJSONObject(i);
            result.add(o.getInt(MEMBER_ID), o.getString(MEMBER_NAME), o.getInt(MEMBER_RANK));
        }
        return result;
    }
}
