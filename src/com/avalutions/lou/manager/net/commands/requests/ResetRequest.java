package com.avalutions.lou.manager.net.commands.requests;

/**
 * Created with IntelliJ IDEA.
 * User: Avalutions
 * Date: 9/8/12
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResetRequest extends Request {
    public boolean reset;

    public ResetRequest(String session) {
        super(session);
    }
}
