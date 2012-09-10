package com.avalutions.lou.manager.net.commands;

import android.util.Log;
import com.avalutions.lou.manager.net.commands.requests.PlayerInfoRequest;
import com.avalutions.lou.manager.net.commands.responses.PlayerInfoResponse;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Avalutions
 * Date: 9/9/12
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetPlayerInfo extends Command<PlayerInfoRequest, PlayerInfoResponse> {

    public GetPlayerInfo() {
        super(PlayerInfoRequest.class, PlayerInfoResponse.class);
    }

    @Override
    protected String getAction() {
        return GET_PLAYER_INFO;
    }

    @Override
    public PlayerInfoResponse run() {
        try {
            PlayerInfoRequest request = new PlayerInfoRequest(getSession().sessionId);
            return run(request);
        } catch (IOException e) {
            Log.e("Get Player Info", "Couldn't get player information", e);
            return null;
        }
    }
}
