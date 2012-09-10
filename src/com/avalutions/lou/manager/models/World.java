package com.avalutions.lou.manager.models;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/2/12
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class World {
    private String id;
    private Player player;
    private Alliance alliance;
    private City currentCity;
    private QuestLog quests;
    private Journal journal;
    private Mailbox mailbox;


    public World() {
    }

    public enum WorldChange { Player, City, Quest, Journal, Alliance, Mailbox, Chat }
    private void onWorldChanged(WorldChange whatChanged) {
        if(handler != null) {
            handler.onWorldChanged(whatChanged);
        }
    }
    public void setWorldChangedHandler(WorldChangedHandler handler) {
        this.handler = handler;
    }
    private WorldChangedHandler handler;
    public interface WorldChangedHandler {
        public void onWorldChanged(WorldChange whatChanged);
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
        onWorldChanged(WorldChange.Player);
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
        onWorldChanged(WorldChange.Alliance);
    }

    public QuestLog getQuests() {
        return quests;
    }

    public void setQuests(QuestLog quests) {
        this.quests = quests;
        onWorldChanged(WorldChange.Quest);
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
        onWorldChanged(WorldChange.Journal);
    }

    public Mailbox getMailbox() {
        return mailbox;
    }

    public void setMailbox(Mailbox mailbox) {
        this.mailbox = mailbox;
        onWorldChanged(WorldChange.Mailbox);
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
        onWorldChanged(WorldChange.City);
    }
}
