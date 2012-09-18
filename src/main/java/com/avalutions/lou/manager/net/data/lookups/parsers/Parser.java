package com.avalutions.lou.manager.net.data.lookups.parsers;

import com.avalutions.lou.manager.net.data.lookups.Lookup;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Copyright (c) 2012 All Right Reserved,
 * Developer: Benny Thompson
 * Date: 9/18/12
 * Summary:
 */
public interface Parser<T extends Lookup> {
    public String getName();

    public T parse(JsonNode node);
}
