package com.avalutions.lou.manager.net.commands.requests;

/**
 * Created with IntelliJ IDEA.
 * User: Avalutions
 * Date: 9/8/12
 * Time: 7:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestResourcesRequest extends Request {
    public int cityid;
    public int resType;
    public long minResource;
    public long maxTime;

    public RequestResourcesRequest(String session, int cityId, int resourceType, long minimumResources, long maximumTime) {
        super(session);
        this.cityid = cityId;
        this.resType = resourceType;
        this.minResource = minimumResources;
        this.maxTime = maximumTime;
    }
}
