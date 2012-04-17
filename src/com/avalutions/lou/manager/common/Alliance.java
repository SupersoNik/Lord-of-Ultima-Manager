package com.avalutions.lou.manager.common;

import java.util.ArrayList;
import java.util.List;

public class Alliance implements IIdentifiable {
	private String name;
	private int allianceId;
	private List<IIdentifiable> players = new ArrayList<IIdentifiable>();
	
	public Alliance(int id, String name) {
	    this.allianceId = id;
	    this.name = name;
	}
	
	public void add(IIdentifiable player) {
	    players.add(player);
	}
    
    public void add(int id, String name, int rank) {
        Player player = new Player(id, name);
        player.setRank(rank);
        players.add(player);
    }
	
	public List<IIdentifiable> getMembers() {
		return null;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return allianceId;
	}
	
	public String getName() {
		return name;
	}

}
