package com.avalutions.lou.manager.net.commands.requests;

/**
* Created with IntelliJ IDEA.
* User: Avalutions
* Date: 9/8/12
* Time: 3:44 PM
* To change this template use File | Settings | File Templates.
*/
public class PollRequest extends Request {
    public String requestid;
    public String requests;

    public PollRequest(String session, int sequence, String requests) {
        super(session);
        this.requestid = String.valueOf(sequence);
        this.requests = requests;
    }
}
