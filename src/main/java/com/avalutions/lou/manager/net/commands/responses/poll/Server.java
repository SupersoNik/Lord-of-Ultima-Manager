package com.avalutions.lou.manager.net.commands.responses.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Server {
    public int me;
    public List<Integer> ac;
    public String nm;
    public String ni;
    public String na;
    public int dt;
}
