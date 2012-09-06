package com.avalutions.lou.manager.net.requests;

import com.avalutions.lou.manager.net.ClientActions;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/4/12
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetPlayerDetails extends Request {

    @Override
    protected void onPostExecute(String jsonObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String getAction() {
        return ClientActions.GET_PLAYER_INFO;
    }
}
