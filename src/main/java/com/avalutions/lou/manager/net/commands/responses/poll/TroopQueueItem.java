package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TroopQueueItem {
    @JsonProperty("i")
    public int id;
    @JsonProperty("t")
    public int type;
    @JsonProperty("c")
    public int cityId;
    @JsonProperty("l")
    public int total;
    @JsonProperty("o")
    public int originalTotal;
    @JsonProperty("ss")
    public int startSeconds;
    @JsonProperty("es")
    public int endSeconds;
    @JsonProperty("be")
    public int batchEnd;
    @JsonProperty("m")
    public boolean ministerAssigned;
}
