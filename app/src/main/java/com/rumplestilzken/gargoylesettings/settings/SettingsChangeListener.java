package com.rumplestilzken.gargoylesettings.settings;

import android.content.SharedPreferences;
import android.util.Log;

import com.rumplestilzken.gargoylesettings.touchpad.TouchpadProcessor;

public class SettingsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final SharedPreferences.OnSharedPreferenceChangeListener INSTANCE = new SettingsChangeListener();

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals(Settings.getTouchpadScrolling())) {
            processTouchpadScrolling(sharedPreferences);
        }
        if(s.equals(Settings.getDoubleTapHomeButton()))
        {
            processDoubleTapHomeButton(sharedPreferences);
        }
    }

    private void processDoubleTapHomeButton(SharedPreferences sharedPreferences) {
        Log.d("SettingsActivity", "Double tap home button:" + (sharedPreferences.getBoolean(Settings.getDoubleTapHomeButton(), false) ? "Enabled" : "Disabled"));
//        if(sharedPreferences.getBoolean(Settings.getDoubleTapHomeButton(), false))
//        {
//
//        }
//        else {
//
//        }
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
