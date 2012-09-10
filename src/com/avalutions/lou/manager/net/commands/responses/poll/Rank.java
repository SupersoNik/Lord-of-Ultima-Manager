package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rank {
    @JsonProperty("i")
    public int id;
    @JsonProperty("n")
    public String name;
}
