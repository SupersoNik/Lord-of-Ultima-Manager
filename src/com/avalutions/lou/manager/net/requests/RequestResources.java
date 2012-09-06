package com.avalutions.lou.manager.net.requests;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/2/12
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestResources extends Request {
    private String city;
    private String resource;
    private String minimumResources;
    private String maxTime;

    public RequestResources(int city, int resource, int minimumResources, int maxTime) {
        this.city = String.valueOf(city);
        this.resource = String.valueOf(resource);
        this.minimumResources = String.valueOf(minimumResources);
        this.maxTime = String.valueOf(maxTime);
    }

    @Override
    protected Map<String, String> getRequestData() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("maxTime", maxTime);
        result.put("minResource", minimumResources);
        result.put("resType", resource);
        result.put("cityid", city);
        return result;
    }

    @Override
    protected void onPostExecute(String jsonObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String getAction() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
