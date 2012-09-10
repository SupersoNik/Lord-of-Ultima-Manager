package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Merchant {
    @JsonProperty("i")
    public long id;
    @JsonProperty("t")
    public int type;
    @JsonProperty("ss")
    public long startSeconds;
    @JsonProperty("es")
    public long endSeconds;
    @JsonProperty("c")
    public int cityId;
    @JsonProperty("cn")
    public String cityName;
    @JsonProperty("p")
    public int playerid;
    @JsonProperty("pn")
    public String playerName;
    @JsonProperty("a")
    public int allianceId;
    @JsonProperty("an")
    public String allianceName;
    @JsonProperty("r")
    public List<UnitGroup> resources;
}
