package com.avalutions.lou.manager.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/4/12
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityHeader {
    private static final String ID = "i";
    private static final String NAME = "n";
    private static final String X = "x";
    private static final String Y = "y";

    private int id;
    private String name;
    private int x;
    private int y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static CityHeader fromJSON(JSONObject object) throws JSONException {
        CityHeader city = new CityHeader();
        city.setId(object.getInt(ID));
        city.setName(object.getString(NAME));
        city.setX(object.getInt(X));
        city.setY(object.getInt(Y));

        return city;
    }
}
