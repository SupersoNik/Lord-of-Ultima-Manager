package com.avalutions.lou.manager.net.commands.responses;

import com.avalutions.lou.manager.net.commands.responses.poll.PlayerCity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Avalutions
 * Date: 9/9/12
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerInfoResponse {
    @JsonProperty("AllianceId")
    public long allianceId;
    @JsonProperty("AllianceName")
    public String allianceName;
    @JsonProperty("Cities")
    public PlayerCity[] cities;
    @JsonProperty("Id")
    public long id;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("p")
    public long score;
}
