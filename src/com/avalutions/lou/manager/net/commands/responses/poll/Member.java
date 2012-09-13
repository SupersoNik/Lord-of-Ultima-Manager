package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    @JsonProperty("i")
    public int id;
    @JsonProperty("n")
    public String name;
    @JsonProperty("r")
    public long rankId;
    @JsonProperty("ra")
    public int worldRank;
    @JsonProperty("p")
    public long score;
    @JsonProperty("c")
    public int cities;
    @JsonProperty("l")
    public Date joined;
}
