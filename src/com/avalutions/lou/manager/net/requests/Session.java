package com.avalutions.lou.manager.net.requests;

import org.json.JSONException;
import org.json.JSONObject;

public class Session extends JSONObject {
    private String session;
    
    public Session(String session) {
        setSession(session);
    }
    
    public String getSession() {
        return session;
    }
    
    public void setSession(String session) {
        this.session = session;
        try {
            super.put("session", session);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
