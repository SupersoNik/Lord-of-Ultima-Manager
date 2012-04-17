package com.avalutions.lou.manager.common;

public class TimeCheck {
    private long time;
    private int step = 1000;
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getStep() {
        return step;
    }
    public void setStep(int step) {
        this.step = step;
    }
    public void increment() {
        time += step;
    }
}