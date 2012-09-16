package com.avalutions.lou.manager.net.commands;

import android.util.Log;
import com.avalutions.lou.manager.net.commands.requests.RequestResourcesRequest;
import com.avalutions.lou.manager.net.commands.responses.RequestResourcesResponse;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/2/12
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestResources extends Command<RequestResourcesRequest, RequestResourcesResponse> {
    private int cityId;
    private int resourceType;
    private long minimumResources;
    private long maximumDistance;

    public RequestResources(int cityId, int resourceType, long minimumResources, long maximumDistance) {
        super(RequestResourcesRequest.class, RequestResourcesResponse.class);
        this.cityId = cityId;
        this.resourceType = resourceType;
        this.minimumResources = minimumResources;
        this.maximumDistance = maximumDistance;
    }

    @Override
    protected String getAction() {
        return REQUEST_RESOURCES;
    }

    @Override
    public RequestResourcesResponse run() {
        try {
            RequestResourcesRequest request = new RequestResourcesRequest(getSession().sessionId, cityId, resourceType, minimumResources, maximumDistance);
            return run(request);
        } catch (IOException e) {
            Log.e("Reset", "Couldn't reset session", e);
            return null;
        }
    }
}
