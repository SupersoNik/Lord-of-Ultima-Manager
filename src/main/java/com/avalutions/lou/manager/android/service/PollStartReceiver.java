package com.avalutions.lou.manager.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PollStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent pollService = new Intent(context, PollService.class);
        context.startService(pollService);
    }
}
