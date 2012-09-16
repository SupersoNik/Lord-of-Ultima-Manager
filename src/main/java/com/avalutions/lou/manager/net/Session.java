package com.avalutions.lou.manager.net;

import com.avalutions.lou.manager.models.World;
import com.avalutions.lou.manager.net.commands.Reset;
import com.avalutions.lou.manager.net.commands.responses.ResetResponse;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/2/12
 * Time: 2:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Session {

    private static Session[] sessions;
    private static Session activeSession;

    public static Session[] getSessions() {
        return sessions;
    }
    public static Session getActive() {
        return activeSession;
    }
    public static boolean login(String username, String password) {
        //authentication block:
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("mail", "bennyandlinds@gmail.com"));
        nvps.add(new BasicNameValuePair("password", "RunS0meLaps"));

        UltimaClient.getInstance().post("https://www.lordofultima.com/en/user/login", nvps);

        String html = UltimaClient.getInstance().get("http://www.lordofultima.com/en/game/index");

        Document doc = Jsoup.parse(html);
        Elements forms = doc.select("form");
        Pattern pattern = Pattern.compile("http://prodgame(\\d+)\\.lordofultima.com/(\\d+)/index.aspx");       //<form.*action=\"http://prodgame(\\d+)\\.lordofultima\\.com/(\\d+)/index\\.aspx\">[\\n\\s]*<input type=\"hidden\" name=\"sessionId\" id=\"sessionId\" value=\"(.*)\" />[\\n\\s]*.*[\\n\\s]*.*World (\\d+) \\((.*)\\).*[\\n\\s]*.*[\\n\\s]*</form>
        Pattern worldPattern = Pattern.compile("World (\\d+) \\((.*)\\)");
        Matcher matcher, worldMatcher;
        List<Session> sessionList = new ArrayList<Session>();
        for(Element element : forms)
        {
            matcher = pattern.matcher(element.attr("action"));
            if(matcher.matches())
            {
                Element input = element.select("input[type=hidden]").first();
                String sessionId = input.attr("value");
                Session session = new Session(sessionId);
                session.game = matcher.group(1);
                session.instance = matcher.group(2);

                String button = element.select("input[type=submit]").first().attr("value");
                worldMatcher = worldPattern.matcher(button);
                worldMatcher.matches();
                session.worldId = worldMatcher.group(1);
                session.region = worldMatcher.group(2);

                sessionList.add(session);
            }
        }
        sessions = sessionList.toArray(new Session[sessionList.size()]);
        return sessionList.size() > 0;
    }

    public String sessionId;
    public String game;
    public String instance;
    public String worldId;
    public String region;
    public World world;
    private final Timer timer = new Timer();

    private Session(String sessionId) {
        this.sessionId = sessionId;
        world = new World();
    }

    public void activate() {
        Reset reset = new Reset(this);
        ResetResponse response = reset.run();
        sessionId = response.i;

        if(activeSession != null) {
            activeSession.deactivate();
        }
        activeSession = this;
    }

    private void deactivate() {

    }
}
