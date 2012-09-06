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

    public interface PlayerUpdatedHandler {
        public void onPlayerUpdated();
    }

    public interface AllianceUpdatedHandler {
        public void onAllianceUpdated();
    }

    public interface CityChangedHandler {
        public void onCityChanged();
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

    public QuestLog getQuests() {
        return quests;
    }

    public void setQuests(QuestLog quests) {
        this.quests = quests;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Mailbox getMailbox() {
        return mailbox;
    }

    public void setMailbox(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }
}
