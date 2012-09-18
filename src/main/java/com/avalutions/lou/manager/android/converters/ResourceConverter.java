package com.avalutions.lou.manager.android.converters;

/**
 * Copyright (c) 2012 All Right Reserved,
 * Developer: Benny Thompson
 * Date: 9/17/12
 * Summary:
 */
public class ResourceConverter implements Converter {
    @Override
    public float convert(float input) {
        return input * 60 * 60;
    }
}
