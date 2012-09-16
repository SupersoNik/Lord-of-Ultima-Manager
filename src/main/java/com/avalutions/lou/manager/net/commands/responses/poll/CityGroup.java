package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityGroup {
    @JsonProperty("i")
    public int id;
    @JsonProperty("n")
    public String name;
    @JsonProperty("c")
    public List<Integer> cities;
}
