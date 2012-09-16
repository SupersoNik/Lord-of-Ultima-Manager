package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Army {
    @JsonProperty("i")
    public int id;
    @JsonProperty("t")
    public int type;
    @JsonProperty("c")
    public int cityId;
    @JsonProperty("n")
    public String cityName;
    @JsonProperty("x")
    public int x;
    @JsonProperty("y")
    public int y;
    @JsonProperty("u")
    public List<UnitGroup> units;
}
