package com.avalutions.lou.manager.common;

import java.io.Serializable;

public class LouSession implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 5020043974517897726L;
    
    private String sessionId;
	private String game;
	private String instance;
	private String world;
	private String region;
	private ICity currentCity;

    public ICity getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(ICity currentCity) {
        this.currentCity = currentCity;
    }

    public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

	public LouSession(String sessionId)
	{
		this.sessionId = sessionId;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	@Override
	public boolean equals(Object o) {
	    if(o instanceof LouSession) {
	        return this.getSessionId().equals(((LouSession) o).getSessionId());
	    } else {
	        return false;
	    }
	}
	
	@Override
	public int hashCode() {
	    return getSessionId().hashCode();
	}
}
