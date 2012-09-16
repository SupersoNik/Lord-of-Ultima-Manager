package com.avalutions.lou.manager.net.commands;

import android.util.Log;
import com.avalutions.lou.manager.net.Session;
import com.avalutions.lou.manager.net.commands.requests.ResetRequest;
import com.avalutions.lou.manager.net.commands.responses.ResetResponse;

import java.io.IOException;

public class Reset extends Command<ResetRequest, ResetResponse> {
    private Session sessionToReset;

    public Reset(Session sessionToReset) {
        super(ResetRequest.class, ResetResponse.class);
        this.sessionToReset = sessionToReset;
    }

    @Override
    protected Session getSession() {
        return sessionToReset;
    }

    @Override
    public ResetResponse run() {
        try {
            ResetRequest request = new ResetRequest(sessionToReset.sessionId, true);
            return run(request);
        } catch (IOException e) {
            Log.e("Reset", "Couldn't reset session", e);
            return null;
        }
    }

    @Override
    protected String getAction() {
        return OPEN_SESSION;
    }
}
