package com.avalutions.lou.manager.net.commands;

import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.UltimaClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Avalutions
 * Date: 9/8/12
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class Command<TRequest, TResponse> {

    protected static final String OPEN_SESSION = "OpenSession";
    protected static final String GET_PLAYER_INFO = "GetPlayerInfo";
    protected static final String TRADE_DIRECT = "TradeDirect";
    protected static final String GET_DISTANCE = "GetDistance";
    protected static final String CREATE_AUCTION = "TradeAuctionOffer";
    protected static final String TRADE_START_SEARCH = "TradeStartSearch";
    protected static final String REQUEST_RESOURCES = "TradeSearchResources";
    protected static final String GET_PUBLIC_PLAYER_INFO = "GetPublicPlayerInfo";
    protected static final String GET_PUBLIC_CITY_INFO = "GetPublicCityInfo";
    protected static final String GET_UNIT_PRODUCTION_INFO = "GetUnitProductionInfo";
    protected static final String START_UNIT_PRODUCTION = "StartUnitProduction";
    protected static final String ORDER_UNITS = "OrderUnits";
    protected static final String GET_ORDER_TARGET_INFO = "GetOrderTargetInfo";
    protected static final String POLL = "poll";

    protected abstract String getAction();
    protected final  ObjectMapper mapper = new ObjectMapper();
    private Class<TResponse> responseType;
    private Class<TRequest> requestType;

    protected Command(Class<TRequest> requestType, Class<TResponse> responseType) {
        this.requestType = requestType;
        this.responseType = responseType;
        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        mapper.setDateFormat(df);
    }

    protected TResponse handleResponse(String response) throws IOException {
        return mapper.readValue(response, responseType);
    }

    protected String prepareRequest(TRequest request) throws IOException {
        return mapper.writeValueAsString(request);
    }

    protected Session getSession() {
        return Session.getActive();
    }

    public abstract TResponse run();

    protected TResponse run(TRequest request) throws IOException {
        String requestString = prepareRequest(request);
        String responseString = UltimaClient.getInstance().post(getSession(), getAction(), requestString);
        TResponse response = handleResponse(responseString);
        return response;
    }
}
