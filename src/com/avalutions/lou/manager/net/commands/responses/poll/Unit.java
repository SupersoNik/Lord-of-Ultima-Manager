package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Unit {
    @JsonProperty("i")
    public int type;
    @JsonProperty("b")
    public long amount;
    @JsonProperty("d")
    public float increase;
    @JsonProperty("m")
    public long maximum;
}
