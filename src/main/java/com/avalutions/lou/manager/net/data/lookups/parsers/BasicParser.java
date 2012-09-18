package com.avalutions.lou.manager.net.data.lookups.parsers;

import com.avalutions.lou.manager.net.data.lookups.BasicLookup;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Copyright (c) 2012 All Right Reserved,
 * Developer: Benny Thompson
 * Date: 9/18/12
 * Summary:
 */
public class BasicParser implements Parser<BasicLookup> {
    private String name;

    public BasicParser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BasicLookup parse(JsonNode node) {
        Map<String, String> result = new HashMap<String, String>();
        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            if (entry.getValue().has("dn")) {
                result.put(entry.getKey(), entry.getValue().get("dn").asText());
            } else {
                result.put(entry.getKey(), entry.getValue().get("n").asText());
            }
        }
        return new BasicLookup(result);
    }
}
