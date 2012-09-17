package com.avalutions.lou.manager.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.avalutions.lou.manager.net.Session;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class PollService extends Service {
    private final IBinder binder = new PollBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Session.getActive().world.update();

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class PollBinder extends Binder {
        PollService getService() {
            return PollService.this;
        }
    }
}
