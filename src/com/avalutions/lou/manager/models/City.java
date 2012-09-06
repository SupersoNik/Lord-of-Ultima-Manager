package com.avalutions.lou.manager.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class City extends CityHeader {
    private static final String IS_CITY = "c";
    private static final String ARRAY = "u";
    private static final String LEVEL = "l";
    private static final String TYPE = "t";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String TRADE_ARRAY = "t";
    private static final String TRADE_TYPE = "t";
    private static final String TRADE_COUNT = "c";
    private static final String TRADE_TOTAL = "tc";
    private static final String EXCHANGE_ARRAY = "ti";
    private static final String EXCHANGE_ID = "i";
    private static final String EXCHANGE_CITY_ID = "c";
    private static final String EXCHANGE_CITY_NAME = "cn";
    private static final String EXCHANGE_PLAYER_ID = "p";
    private static final String EXCHANGE_PLAYER_NAME = "pn";
    private static final String EXCHANGE_ALLIANCE_ID = "a";
    private static final String EXCHANGE_ALLIANCE_NAME = "an";
    private static final String EXCHANGE_RESOURCE_ARRAY = "r";
    private static final String EXCHANGE_RESOURCE_TYPE = "t";
    private static final String EXCHANGE_RESOURCE_COUNT = "c";

	private List<Construct> buildings;
	private Construct cityWall;
	private Construct townHall;
    private ConstrainedMeasurableUnit<TradeType> carts;
    private ConstrainedMeasurableUnit<TradeType> ships;
	private CityResources resources;
	private Army troops;
	private int cityId;
	private String name;
	
	public List<Caravan> getCaravans() {
        return caravans;
    }

    public void setCaravans(List<Caravan> caravans) {
        this.caravans = caravans;
    }

    private int score;
	private List<Caravan> caravans;

    public City() {

    }
	
	public City(int id, String name) {
	    this.cityId = id;
	    this.name = name;
	}
	
	public City(int id, String name, int x, int y) {
	    this.cityId = id;
	    this.name = name;
	    setX(x);
	    setY(y);
	}
	public City(int id, String name, int x, int y, World session) {
        this.cityId = id;
        this.name = name;
        setX(x);
        setY(y);
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return cityId;
	}
	
	public String getName() {
		return name;
	}

    public List<Construct> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Construct> buildings) {
        this.buildings = buildings;
    }

    public CityResources getResources() {
        return resources;
    }

    public void setResources(CityResources resources) {
        this.resources = resources;
    }

    public Army getTroops() {
        return troops;
    }

    public void setTroops(Army troops) {
        this.troops = troops;
    }

    public Construct getCityWall() {
        return cityWall;
    }

    public void setCityWall(Construct cityWall) {
        this.cityWall = cityWall;
    }

    public Construct getTownHall() {
        return townHall;
    }

    public void setTownHall(Construct townHall) {
        this.townHall = townHall;
    }

    public ConstrainedMeasurableUnit<TradeType> getCarts() {
        return carts;
    }

    public void setCarts(ConstrainedMeasurableUnit<TradeType> carts) {
        this.carts = carts;
    }

    public ConstrainedMeasurableUnit<TradeType> getShips() {
        return ships;
    }

    public void setShips(ConstrainedMeasurableUnit<TradeType> ships) {
        this.ships = ships;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void buildingsFromJSON(JSONObject object) throws JSONException {
        JSONArray cl = object.getJSONArray(ARRAY);
        for (int i = 0; i < cl.length(); i++) {
            JSONObject o = cl.getJSONObject(i);
            buildings.add(new Construct(o.getInt(LEVEL), ConstructType.values()[o.getInt(TYPE)]));
        }
    }

    public static City fromJSON(JSONObject object) throws JSONException {
        City city = new City();

        CityResources resources = CityResources.fromJSON(object);
        city.setResources(resources);

        Army army = Army.fromJSON(object);
        city.setTroops(army);

        JSONArray ta = object.getJSONArray(TRADE_ARRAY);
        for (int i = 0; i < ta.length(); i++) {
            JSONObject o = ta.getJSONObject(i);
            ConstrainedMeasurableUnit<TradeType> t = new ConstrainedMeasurableUnit<TradeType>(
                    TradeType.values()[o.getInt(TRADE_TYPE)],
                    o.getInt(TRADE_COUNT),
                    o.getInt(TRADE_TOTAL));
            if (t.getUnit().equals(TradeType.Cart)) {
                city.setCarts(t);
            } else {
                city.setShips(t);
            }
        }

        ta = object.getJSONArray(EXCHANGE_ARRAY);
        List<Caravan> caravans = new ArrayList<Caravan>();
        for (int i = 0; i < ta.length(); i++) {
            JSONObject o = ta.getJSONObject(i);

            CityHeader cityHeader = new CityHeader();
            cityHeader.setId(object.getInt(EXCHANGE_CITY_ID));
            cityHeader.setName(object.getString(EXCHANGE_CITY_NAME));

            Alliance alliance = new Alliance(o.getInt(EXCHANGE_ALLIANCE_ID), o.getString(EXCHANGE_ALLIANCE_NAME));
            Player xplayer = new Player(o.getInt(EXCHANGE_PLAYER_ID), o.getString(EXCHANGE_PLAYER_NAME));
            Caravan result = new Caravan(cityHeader, xplayer);
            JSONArray ira = o.getJSONArray(EXCHANGE_RESOURCE_ARRAY);
            for (int j = 0; j < ira.length(); j++) {
                JSONObject io = ira.getJSONObject(j);
                result.addResource(
                        Resource.values()[io.getInt(EXCHANGE_RESOURCE_TYPE)],
                        io.getInt(EXCHANGE_RESOURCE_COUNT));
            }
            city.setCaravans(caravans);
        }
        return city;
    }
}
