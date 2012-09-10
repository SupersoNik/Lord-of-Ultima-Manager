package com.avalutions.lou.manager.net.commands;

import android.util.Log;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.requests.PollRequest;
import com.avalutions.lou.manager.net.commands.responses.PollResponse;

import java.io.IOException;

public class Poll extends Command<PollRequest, PollResponse> {
    private int sequence;
    private Requests requests;

    public Poll(int sequence) {
        super(PollRequest.class, PollResponse.class);
        this.sequence = sequence;
        this.requests = new Requests();
    }

    @Override
    protected String getAction() {
        return POLL;
    }

    @Override
    public PollResponse run() {
        try {
            Session session = getSession();
            if(session.world.getCurrentCity() != null) {
                requests.CITY = String.valueOf(session.world.currentCityId);
            }
            requests.CAT = sequence == 1 ? "0" : "1";
            requests.WC = sequence == 1 ? "A" : "";
            PollRequest request = new PollRequest(getSession().sessionId, sequence, new Requests().toString());
            return run(request);
        } catch (IOException e) {
            Log.e("Reset", "Couldn't reset session", e);
            return null;
        }
    }

    public class Requests {
        public String TM = "0,0,";
        public String CAT = "";
        public String SERVER = "";
        public String ALLIANCE = "";
        public String QUEST = "";
        public String TE = "";
        public String FW = "";
        public String PLAYER = "";
        public String CITY = "";
        public String WC = "";
        public String WORLD = "";
        public String UFP = "";
        public String REPORT = "";
        public String MAIL = "";
        public String FRIENDINV = "";
        public String CHAT = "";
        public String SUBSTITUTION = "";
        public String EC = "";
        public String INV = "";
        public String AI = "";
        public String MAT = "";

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("TM:").append(TM).append("\f");
            builder.append("CAT:").append(CAT).append("\f");
            builder.append("TIME:").append(System.currentTimeMillis()).append("\f");
            builder.append("SERVER:").append(SERVER).append("\f");
            builder.append("ALLIANCE:").append(ALLIANCE).append("\f");
            builder.append("QUEST:").append(QUEST).append("\f");
            builder.append("TE:").append(TE).append("\f");
            builder.append("FW:").append(FW).append("\f");
            builder.append("PLAYER:").append(PLAYER).append("\f");
            builder.append("CITY:").append(CITY).append("\f");
            builder.append("WC:").append(WC).append("\f");
            builder.append("WORLD:").append(WORLD).append("\f");
            builder.append("VIS:").append(String.format("c:%s:0:-714:-416:1516:1008", CITY)).append("\f");
            builder.append("UFP:").append(UFP).append("\f");
            builder.append("REPORT:").append(REPORT).append("\f");
            builder.append("MAIL:").append(MAIL).append("\f");
            builder.append("FRIENDINV:").append(FRIENDINV).append("\f");
            builder.append("CHAT:").append(CHAT).append("\f");
            builder.append("SUBSTITUTION:").append(SUBSTITUTION).append("\f");
            builder.append("EC:").append(EC).append("\f");
            builder.append("INV:").append(INV).append("\f");
            builder.append("AI:").append(AI).append("\f");
            builder.append("MAT:").append(CITY).append("\f");
            return builder.toString();
        }
    }
}
