package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerCity {
    @JsonProperty("i")
    public long id;
    @JsonProperty("n")
    public String name;
    public int x;
    public int y;
}
