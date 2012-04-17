package com.avalutions.lou.manager.net;

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
        if(UA != null) {
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
