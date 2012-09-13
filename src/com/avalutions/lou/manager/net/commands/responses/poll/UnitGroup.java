package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitGroup {
    @JsonProperty("t")
    public int type;
    @JsonProperty("c")
    public int count;
    @JsonProperty("tc")
    public int totalCount;
}
