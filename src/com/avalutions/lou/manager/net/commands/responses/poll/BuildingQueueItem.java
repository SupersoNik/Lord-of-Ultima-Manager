package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildingQueueItem {
    @JsonProperty("i")
    public int id;
    @JsonProperty("b")
    public int type;
}
