package com.avalutions.lou.manager.net.requests;

import org.json.JSONException;

import com.avalutions.lou.manager.common.LouSession;

public class Poll extends Session {
    public Poll(LouSession session, int id, String requests) {
        super(session.getSessionId());
        try {
            super.put("requests", requests);
            super.put("requestId", id);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
