package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecruitmentSpeed {
    @JsonProperty("t")
    public int type;
    @JsonProperty("p")
    public int percent;
}
