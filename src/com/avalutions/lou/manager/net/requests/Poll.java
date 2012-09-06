package com.avalutions.lou.manager.net.requests;

import android.util.Log;
import com.avalutions.lou.manager.models.Alliance;
import com.avalutions.lou.manager.models.City;
import com.avalutions.lou.manager.models.Player;
import com.avalutions.lou.manager.net.ClientActions;
import com.avalutions.lou.manager.net.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Poll extends Request {
    private final int sequence;

    public Poll(int sequence) {
        this.sequence = sequence;
    }

    @Override
    protected Map<String, String> getRequestData() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("requests", new Requests().toString());
        result.put("requestid", String.valueOf(sequence));
        return result;
    }

    @Override
    protected void onPostExecute(String jsonObject) {
        try {
            JSONArray result = new JSONArray(jsonObject);
            for (int i = 0; i < result.length(); i++) {
                JSONObject pair = result.getJSONObject(i);
                String key = pair.getString("C");
                JSONObject value = pair.getJSONObject("D");

                if (key == "PLAYER") {
                    Player player = Player.fromJSON(value);
                    Session.getActive().getWorld().setPlayer(player);
                } else if (key == "CITY") {
                    City city = City.fromJSON(value);
                    Session.getActive().getWorld().setCurrentCity(city);
                } else if (key == "ALLIANCE") {
                    Alliance alliance = Alliance.fromJSON(value);
                    Session.getActive().getWorld().setAlliance(alliance);
                } else if (key == "VIS") {

                }
            }
        } catch (JSONException e) {
            Log.e("Poll", "Problem with poll JSON", e);
        }
    }

    @Override
    protected String getAction() {
        return ClientActions.POLL;
    }

    public class Requests {
        public String UA = "";
        public String TM = "";
        public String CAT = "";
        public String TIME = "";
        public String SERVER = "";
        public String ALLIANCE = "";
        public String QUEST = "";
        public String TE = "";
        public String FW = "";
        public String PLAYER = "";
        public String CITY = "";
        public String WC = "";
        public String WORLD = "";
        public String VIS = "c:22413544:0:-714:-416:1516:1008";
        public String UFP = "";
        public String REPORT = "";
        public String MAIL = "";
        public String FRIENDINV = "";
        public String CHAT = "";
        public String SUBSTITUTION = "";
        public String EC = "";
        public String INV = "";
        public String AI = "";
        public String MAT = "22413544";

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (UA != null) {
                builder.append("UA" + UA + "\f");
            }
            builder.append("TM:" + TM + "\f");
            builder.append("CAT:" + CAT + "\f");
            builder.append("TIME:" + TIME + "\f");
            builder.append("SERVER:" + SERVER + "\f");
            builder.append("ALLIANCE:" + ALLIANCE + "\f");
            builder.append("QUEST:" + QUEST + "\f");
            builder.append("TE:" + TE + "\f");
            builder.append("FW:" + FW + "\f");
            builder.append("PLAYER:" + PLAYER + "\f");
            builder.append("CITY:" + CITY + "\f");
            builder.append("WC:" + WC + "\f");
            builder.append("WORLD:" + WORLD + "\f");
            builder.append("VIS:" + VIS + "\f");
            builder.append("UFP:" + UFP + "\f");
            builder.append("REPORT:" + REPORT + "\f");
            builder.append("MAIL:" + MAIL + "\f");
            builder.append("FRIENDINV:" + FRIENDINV + "\f");
            builder.append("CHAT:" + CHAT + "\f");
            builder.append("SUBSTITUTION:" + SUBSTITUTION + "\f");
            builder.append("EC:" + EC + "\f");
            builder.append("INV:" + INV + "\f");
            builder.append("AI:" + AI + "\f");
            builder.append("MAT:" + MAT + "\f");
            return builder.toString();
        }
    }
}
