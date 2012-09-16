package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Alliance {
    public int v;
    public int id;
    @JsonProperty("n")
    public String name;
    @JsonProperty("t")
    public String abbreviation;
    @JsonProperty("a")
    public String messageOfTheDay;
    @JsonProperty("d")
    public String description;
    @JsonProperty("r")
    public List<Rank> ranks;
    @JsonProperty("m")
    public List<Member> members;
    @JsonProperty("ia")
    public int incomingAttacks;
    @JsonProperty("oa")
    public int outgoingAttacks;
}
