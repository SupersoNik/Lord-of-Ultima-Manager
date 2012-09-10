package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

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
