package com.watchdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Listener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.SECRET_CODE".equals(intent.getAction())) {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.setClass(context, SplashScreen.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}