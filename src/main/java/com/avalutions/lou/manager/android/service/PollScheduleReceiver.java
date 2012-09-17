package com.avalutions.lou.manager.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.inject.Inject;
import roboguice.receiver.RoboBroadcastReceiver;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/16/12
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class PollScheduleReceiver extends RoboBroadcastReceiver {

    private static final int POLL_FREQ_SEC = 30;

    @Inject
    private AlarmManager alarmManager;
    @Inject
    private Calendar calendar;

    @Override
    protected void handleReceive(Context context, Intent intent) {
        Intent schedulerIntent = new Intent(context, PollStartReceiver.class);
        PendingIntent pendingSchedulerIntent = PendingIntent.getBroadcast(context, 0, schedulerIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        calendar.add(Calendar.SECOND, POLL_FREQ_SEC);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), POLL_FREQ_SEC * 1000, pendingSchedulerIntent);
    }
}
