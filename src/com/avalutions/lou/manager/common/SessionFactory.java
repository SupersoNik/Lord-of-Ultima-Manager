package com.avalutions.lou.manager.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avalutions.lou.manager.R;

import android.app.Activity;

public class SessionFactory {
    public static void updateCityFromPoll(Activity context, JSONObject object,
            City city) {
        try {

            CityResources resources = new CityResources();
            JSONArray ra = getArray(object, context,
                    R.string.city_resources_array);
            for (int i = 0; i < ra.length(); i++) {
                JSONObject o = ra.getJSONObject(i);
                resources.add(
                        Resource.values()[getInt(o, context,
                                R.string.city_resources_type)],
                        getInt(o, context, R.string.city_resources_amount),
                        getInt(o, context, R.string.city_resources_max),
                        getDouble(o, context, R.string.city_resources_per));
            }
            city.setResources(resources);

            Army army = new Army();
            JSONArray ta = getArray(object, context, R.string.city_troops_array);
            for (int i = 0; i < ta.length(); i++) {
                JSONObject o = ta.getJSONObject(i);
                army.add(
                        TroopType.values()[getInt(o, context,
                                R.string.city_troops_type)],
                        getInt(o, context, R.string.city_troops_amount),
                        getInt(o, context, R.string.city_troops_max));
            }
            city.setTroops(army);

            ta = getArray(object, context, R.string.city_trade_array);
            for (int i = 0; i < ta.length(); i++) {
                JSONObject o = ta.getJSONObject(i);
                ConstrainedMeasurableUnit<TradeType> t = new ConstrainedMeasurableUnit<TradeType>(
                        TradeType.values()[getInt(o, context,
                                R.string.city_trade_type)], getInt(o, context,
                                R.string.city_trade_count), getInt(o, context,
                                R.string.city_trade_total));
                if (t.getUnit().equals(TradeType.Cart)) {
                    city.setCarts(t);
                } else {
                    city.setShips(t);
                }
            }

            ta = getArray(object, context, R.string.city_exchange_array);
            List<Caravan> caravans = new ArrayList<Caravan>();
            for (int i = 0; i < ta.length(); i++) {
                JSONObject o = ta.getJSONObject(i);
                ICity xcity = new City(getInt(o, context,
                        R.string.city_exchange_city_id), getString(o, context,
                        R.string.city_exchange_city_name));
                Alliance alliance = new Alliance(getInt(o, context,
                        R.string.city_exchange_alliance_id), getString(o,
                        context, R.string.city_exchange_alliance_name));
                Player xplayer = new Player(getInt(o, context,
                        R.string.city_exchange_player_id), getString(o,
                        context, R.string.city_exchange_player_name), alliance);
                Caravan result = new Caravan(xcity, xplayer);
                JSONArray ira = o.getJSONArray(getString(o, context,
                        R.string.city_exchange_resource_array));
                for (int j = 0; j < ira.length(); j++) {
                    JSONObject io = ra.getJSONObject(j);
                    result.addResource(
                            Resource.values()[getInt(io, context,
                                    R.string.city_exchange_resource_type)],
                            getInt(o, context,
                                    R.string.city_exchange_resource_count));
                }
                city.setCaravans(caravans);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Player buildPlayer(Activity context, JSONObject object) {
        Player result = null;
        try {
            result = new Player(getInt(object, context,
                    R.string.player_player_id), getString(object, context,
                    R.string.player_player_name));
            JSONArray cl = getArray(object, context, R.string.player_city_array);
            for (int i = 0; i < cl.length(); i++) {
                JSONObject o = cl.getJSONObject(i);
                result.AddCity(new City(getInt(o, context,
                        R.string.player_city_id), getString(o, context,
                        R.string.player_city_name), getInt(o, context,
                        R.string.player_city_x), getInt(o, context,
                        R.string.player_city_y)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Alliance buildAlliance(Activity context, JSONObject object) {
        Alliance result = null;
        try {
            result = new Alliance(
                    getInt(object, context, R.string.alliance_id), getString(
                            object, context, R.string.alliance_name));
            JSONArray cl = getArray(object, context,
                    R.string.alliance_member_array);
            for (int i = 0; i < cl.length(); i++) {
                JSONObject o = cl.getJSONObject(i);
                result.add(getInt(o, context, R.string.alliance_member_id),
                        getString(o, context, R.string.alliance_member_name),
                        getInt(o, context, R.string.alliance_member_rank));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Construct> buildBuildings(Activity context, JSONObject object) {
        List<Construct> result = new ArrayList<Construct>();
        try {
            JSONArray cl = getArray(object, context, R.string.visual_array);
            for (int i = 0; i < cl.length(); i++) {
                JSONObject o = cl.getJSONObject(i);
                result.add(new Construct(getInt(o, context, R.string.visual_level), ConstructType.values()[getInt(o, context, R.string.visual_type)]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void updatePlayerFromPoll(Activity context,
            JSONObject object, Player player) {
        try {
            player.setScore(getInt(object, context, R.string.player_poll_score));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray getArray(JSONObject o, Activity context, int id)
            throws JSONException {
        return o.getJSONArray(context.getString(id));
    }

    private static int getInt(JSONObject o, Activity context, int id)
            throws JSONException {
        return o.getInt(context.getString(id));
    }

    private static double getDouble(JSONObject o, Activity context, int id)
            throws JSONException {
        return o.getDouble(context.getString(id));
    }

    private static String getString(JSONObject o, Activity context, int id)
            throws JSONException {
        return o.getString(context.getString(id));
    }
}
