package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitGroup {
    @JsonProperty("t")
    public int type;
    @JsonProperty("c")
    public int count;
    @JsonProperty("tc")
    public int totalCount;
}
