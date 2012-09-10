package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Player {
    @JsonProperty("a")
    public int a;
    @JsonProperty("g")
    public Unit gold;
    @JsonProperty("m")
    public Unit mana;
    @JsonProperty("c")
    public List<PlayerCity> cities;
    @JsonProperty("cg")
    public List<CityGroup> cityGroups;
}
