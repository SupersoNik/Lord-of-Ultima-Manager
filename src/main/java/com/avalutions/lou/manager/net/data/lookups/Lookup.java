package com.avalutions.lou.manager.net.data.lookups;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Lookup<T> {
    private Map<String, T> items;

    protected Lookup(Map<String, T> items) {
        this.items = items;
    }

    public T lookup(int id) {
        return lookup(String.valueOf(id));
    }

    public T lookup(String id) {
        return items.get(id);
    }
}
