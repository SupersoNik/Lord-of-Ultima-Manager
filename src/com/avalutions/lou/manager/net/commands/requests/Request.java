package com.avalutions.lou.manager.net.commands.requests;

abstract class Request {
    public String session;

    protected Request(String session) {
        this.session = session;
    }
}
