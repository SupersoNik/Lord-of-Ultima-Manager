package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Avalutions
 * Date: 9/8/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty("n")
    public String name;
    @JsonProperty("uc")
    public int unitCount;
    @JsonProperty("ul")
    public int unitLimit;
    @JsonProperty("tl")
    public int townHallLevel;
    @JsonProperty("wl")
    public int wallLevel;
    @JsonProperty("g")
    public float goldIncrease;
    @JsonProperty("r")
    public List<Unit> resources;
    @JsonProperty("q")
    public List<BuildingQueueItem> buildingQueue;
    @JsonProperty("uq")
    public List<TroopQueueItem> unitQueue;
    @JsonProperty("uo")
    public List<Army> outgoingUnits;
    @JsonProperty("u")
    public List<UnitGroup> units;
    @JsonProperty("t")
    public List<UnitGroup> tradeUnits;
    @JsonProperty("to")
    public List<Merchant> outgoingTrades;
    @JsonProperty("ti")
    public List<Merchant> incomingTrades;
    @JsonProperty("rs")
    public List<RecruitmentSpeed> rs;
}
