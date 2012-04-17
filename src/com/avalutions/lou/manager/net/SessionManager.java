package com.avalutions.lou.manager.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import android.app.Activity;

import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.common.Alliance;
import com.avalutions.lou.manager.common.City;
import com.avalutions.lou.manager.common.Construct;
import com.avalutions.lou.manager.common.ICity;
import com.avalutions.lou.manager.common.IIdentifiable;
import com.avalutions.lou.manager.common.LouSession;
import com.avalutions.lou.manager.common.MeasurableUnit;
import com.avalutions.lou.manager.common.Player;
import com.avalutions.lou.manager.common.Resource;
import com.avalutions.lou.manager.common.SessionFactory;
import com.avalutions.lou.manager.common.TradeType;

public class SessionManager {
    
    private static Map<LouSession, SessionManager> _sessions = new HashMap<LouSession, SessionManager>();
    public static SessionManager getInstance(LouSession session) {
        return _sessions.get(session);
    }
    
    public static LouSession[] getSessions() {
        return _sessions.keySet().toArray(new LouSession[_sessions.size()]);
    }
	
	public static LouSession[] LogIn(String username, String password)
	{
		//authentication block:
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("mail", username));
		nvps.add(new BasicNameValuePair("password", password));
		
		UltimaClient.getInstance().post("https://www.lordofultima.com/en/user/login", nvps);
		
		return Initialize();
	}
	
	private static LouSession[] Initialize()
	{
		String html = UltimaClient.getInstance().get("http://www.lordofultima.com/en/game/index");

		Document doc = Jsoup.parse(html);
        Elements forms = doc.select("form");
        Pattern pattern = Pattern.compile("http://prodgame(\\d+)\\.lordofultima.com/(\\d+)/index.aspx");
        Pattern worldPattern = Pattern.compile("World (\\d+) \\((.*)\\)");
        Matcher matcher = null;
        Matcher worldMatcher = null;
        for(Element element : forms)
        {
        	matcher = pattern.matcher(element.attr("action"));
        	if(matcher.matches())
        	{
        		Element input = element.select("input[type=hidden]").first();
        		LouSession session = new LouSession(input.attr("value"));
        		session.setGame(matcher.group(1));
        		session.setInstance(matcher.group(2));
        		
        		String button = element.select("input[type=submit]").first().attr("value");
        		worldMatcher = worldPattern.matcher(button);
        		worldMatcher.matches();
        		session.setWorld(worldMatcher.group(1));
        		session.setRegion(worldMatcher.group(2));

        		Reset(session);
        		
        		_sessions.put(session, new SessionManager(session));
        	}
        }
        return _sessions == null ? null : _sessions.keySet().toArray(new LouSession[_sessions.size()]);
	}
    
    private static void Reset(LouSession session)
    {
        JSONObject o = new JSONObject();
        try {
            o.put("reset", true);
            o.put("session", session.getSessionId());
            String response = UltimaClient.getInstance().post(session, ClientActions.OPEN_SESSION, o);
            JSONObject data = new JSONObject(response);
            session.setSessionId(data.getString("i"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private LouSession session;
    private Timer timer;
    private PollTask task;
    private Player player;
    private Activity context;
    private IPollHandler pollHandler;
    
    
    public SessionManager(LouSession session) {
        this.session = session;
        
        this.getPlayerDetails();
        
        this.task = new PollTask(session);
        
        this.timer = new Timer();
        this.timer.schedule(this.task, 0, 1000);
    }
    
    public void setCurrentCity(ICity id) {
        
    }
    
    public void setPollHandler(IPollHandler handler) {
        this.pollHandler = handler;
    }
    
    public void setContext(Activity context) {
        this.context = context;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void poll(JSONObject request) {
        String result = UltimaClient.getInstance().post(session, ClientActions.POLL, request);
        handlePoll(result);
    }

    public void getPlayerDetails() {
        try {
            JSONObject request = new JSONObject();
            request.put("session", session.getSessionId());
            String result = UltimaClient.getInstance().post(session, ClientActions.GET_PLAYER_INFO, request);
            player = SessionFactory.buildPlayer(context, new JSONObject(result));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void handlePoll(String json) {
        if(json != null) {
            try {
                JSONArray value = new JSONArray(json);
                for(int i = 0; i < value.length(); i++) {
                    try {
                        JSONObject pair = value.getJSONObject(i);
                        updateBucket(pair.getString("C"), pair.getJSONObject("D"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void purify(JSONArray request) {
        
    }
    
    public void changeCity(ICity cityId) {
    	session.setCurrentCity(cityId);
    }
    
    public void sendResources(ICity from, TradeType tradeType, IIdentifiable playerTo, ICity cityTo, List<MeasurableUnit<Resource>> resources) {
        sendResources(from, tradeType, playerTo, cityTo, resources, false);
    }
    
    public void sendResources(ICity from, TradeType tradeType, IIdentifiable playerTo, ICity cityTo, List<MeasurableUnit<Resource>> resources, boolean palaceSupport) {

    }
    
    public JSONArray requestResources(int city, int resource, int minimumResources, int maxTime) {
        JSONObject request = new JSONObject();
        try {
            request.put("maxTime", Integer.toString(maxTime));
            request.put("minResource", Integer.toString(minimumResources));
            request.put("resType", Integer.toString(resource));
            request.put("cityid", Integer.toString(city));
            request.put("session", session.getSessionId());

            String result = UltimaClient.getInstance().post(session, ClientActions.REQUEST_RESOURCES, request);
            return new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void updateBucket(String key, JSONObject value) {
        if(value != null && !value.equals(org.json.JSONObject.NULL)) {
            if(key.equals("PLAYER")) {
                SessionFactory.updatePlayerFromPoll(context, value, player);
            } else if (key.equals("CITY")) {
                SessionFactory.updateCityFromPoll(context, value, (City)session.getCurrentCity());
            } else if (key.equals("ALLIANCE")) {
                Alliance alliance = SessionFactory.buildAlliance(context, value);
                player.setAlliance(alliance);
            } else if (key.equals("VIS")) {
                try {
                    if(value.getBoolean(context.getString(R.string.visual_is_city))) {
                        List<Construct> buildings = SessionFactory.buildBuildings(context, value);
                        ((City)session.getCurrentCity()).setBuildings(buildings);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(pollHandler != null) {
                pollHandler.BucketChanged(key);
            }
        }
    }
}
