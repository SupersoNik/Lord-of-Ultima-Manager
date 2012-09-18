package com.avalutions.lou.manager.net.data;

import com.avalutions.lou.manager.net.commands.Poll;
import com.avalutions.lou.manager.net.commands.responses.PollResponse;
import com.avalutions.lou.manager.net.commands.responses.poll.*;
import com.avalutions.lou.manager.net.data.lookups.Lookup;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/2/12
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class World {
    public static final String BUILDINGS = "buildings";
    public static final String DUNGEONS = "dungeons";
    public static final String ITEMS = "items";
    public static final String PLAYER_TITLES = "playerTitles";
    public static final String QUEST_GROUPS = "questGroups";
    public static final String QUESTS = "quests";
    public static final String REPORTS = "reports";
    public static final String RESOURCES = "resources";
    public static final String UNIT_TYPES = "unitTypes";
    public static final String UNITS = "units";

    private String id;
    private Player player;
    private Alliance alliance;
    private Long currentCityId;
    private City currentCity;
    private QuestProgress quests;
    private Mail mailbox;
    private Map<String, Lookup> lookups;

    public void changeCity(long cityId) {
        this.currentCityId = cityId;
        update();
    }

    public void setLookups(Map<String, Lookup> lookups) {
        this.lookups = lookups;
    }

    public Long getCurrentCityId() {
        return currentCityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public QuestProgress getQuests() {
        return quests;
    }

    public void setQuests(QuestProgress quests) {
        this.quests = quests;
    }

    public Mail getMailbox() {
        return mailbox;
    }

    public void setMailbox(Mail mailbox) {
        this.mailbox = mailbox;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public synchronized void update() {
        Poll poll = new Poll();
        PollResponse response = poll.run();

        if (response.player != null) {
            setPlayer(response.player);
        }
        if (response.alliance != null) {
            setAlliance(response.alliance);
        }
        if (response.chats != null) {
            setMailbox(response.mail);
        }
        if (response.city != null) {
            setCurrentCity(response.city);
        }
        if (response.mail != null) {
            setMailbox(response.mail);
        }
        if (response.quest != null) {
            setQuests(response.quest);
        }
    }

    public Lookup getLookup(String name) {
        return lookups.get(name);
    }
}
