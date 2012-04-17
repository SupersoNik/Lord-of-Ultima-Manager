package com.avalutions.lou.manager.net;

import java.util.Date;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.avalutions.lou.manager.common.LouSession;

public class PollTask extends TimerTask {
    private int requestId = 0;
    private LouSession session;
    private long time;
    
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public PollTask(LouSession session) {
        this.session = session;
        this.time = new Date().getTime();
    }

    @Override
    public void run() {
        JSONObject request = new JSONObject();
        try {
            Requests r = buildRequests(requestId);
            request.put("requests", r.toString());
            request.put("requestid", requestId);
            request.put("session", session.getSessionId());
            SessionManager.getInstance(session).poll(request);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        requestId++;
        time += 1000;
    }
    
    private Requests buildRequests(int requestId) {
        Requests result = new Requests();
        
        if(requestId == 0) { 
            result.UA = null;
            result.TM = "0,0,";
            result.CAT = "0"; 
        } else { 
            result.TM = "91,2,";
            result.CAT = "1"; 
        }
        result.TIME = String.valueOf(time);
        result.CITY = String.valueOf(session.getCurrentCity().getId());
        
        return result;
    }

}
