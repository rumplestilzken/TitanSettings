package com.rumplestilzken.gargoylesettings;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.preference.Preference;

import com.rumplestilzken.gargoylesettings.touchpad.TouchpadProcessor;

public class SettingsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final SharedPreferences.OnSharedPreferenceChangeListener INSTANCE = new SettingsChangeListener();

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        processTouchpadScrolling(sharedPreferences);
    }

    private void processTouchpadScrolling(SharedPreferences sharedPreferences) {
        if(sharedPreferences.getBoolean(Settings.getTouchpadScrolling(), false))
        {
            TouchpadProcessor.enableTouchpadScrolling();
        }
        else {
            TouchpadProcessor.disableTouchpadScrolling();
        }
    }
}
