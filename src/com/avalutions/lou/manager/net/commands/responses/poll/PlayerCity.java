package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerCity {
    @JsonProperty("i")
    public long id;
    @JsonProperty("n")
    public String name;
    public int x;
    public int y;
}
