package com.rumplestilzken.gargoylesettings.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.rumplestilzken.gargoylesettings.settings.Settings;
import com.rumplestilzken.gargoylesettings.touchpad.TouchpadProcessor;

public class StartService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("StartService", "Booted");
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        if(settings.getBoolean(Settings.getTouchpadScrolling(), false))
        {
            TouchpadProcessor.enableTouchpadScrolling(true);
        }
        else {
            TouchpadProcessor.disableTouchpadScrolling(true);
        }
    }
}
