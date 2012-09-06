package com.avalutions.lou.manager.net.requests;

import com.avalutions.lou.manager.net.ClientActions;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Reset extends Request {
    private ResetCompleteHandler handler;

    public interface ResetCompleteHandler {
        void onResetComplete(boolean result);
    }

    public void setResetCompleteHandler(ResetCompleteHandler handler) {
        this.handler = handler;
    }

    @Override
    protected Map<String, String> getRequestData() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("reset", "true");
        return result;
    }

    @Override
    protected void onPostExecute(String jsonObject) {
        if(handler != null) {
            boolean result = false;
            try {
                JSONObject o = new JSONObject(jsonObject);
                result = o != null && o.get("r") == "1";
            } catch (JSONException e) {
            }
            handler.onResetComplete(result);
        }
    }

    @Override
    protected String getAction() {
        return ClientActions.OPEN_SESSION;
    }
}
