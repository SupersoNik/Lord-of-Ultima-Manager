package com.avalutions.lou.manager.common;

public class Time {

    private long time;

    public Time(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return Long.toString(time);
    }
}
