package com.avalutions.lou.manager.net.requests;

import android.os.AsyncTask;
import android.util.Log;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.UltimaClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

abstract class Request extends AsyncTask<Void, Void, String> {
    @Override
    protected abstract void onPostExecute(String jsonObject);
    protected abstract String getAction();
    protected Map<String, String> getRequestData() {
        return null;
    }

    @Override
    protected final String doInBackground(Void... voids) {
        JSONObject request = new JSONObject();
        Session session = Session.getActive();
        try {
            Map<String, String> data = getRequestData();
            if(data != null) {
                for(Map.Entry<String, String> entry : data.entrySet()) {
                    request.put(entry.getKey(), entry.getValue());
                }
            }
            request.put("session", session.getSessionId());
            String response = UltimaClient.getInstance().post(session, getAction(), request);
            Log.v("JSON Requests", response);
            return response;
        } catch (JSONException e) {
            Log.e("Request", "Error parsing response.", e);
        }
        return null;
    }
}
