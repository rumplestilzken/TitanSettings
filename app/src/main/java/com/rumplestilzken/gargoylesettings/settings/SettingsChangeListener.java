package com.rumplestilzken.gargoylesettings.settings;

import android.content.SharedPreferences;
import android.util.Log;

import com.rumplestilzken.gargoylesettings.provider.MiniModeProvider;
import com.rumplestilzken.gargoylesettings.touchpad.TouchpadProcessor;

public class SettingsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final SharedPreferences.OnSharedPreferenceChangeListener INSTANCE = new SettingsChangeListener();

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals(Settings.getTouchpadScrolling())) {
            processTouchpadScrolling(sharedPreferences);
        }
        if(s.equals(Settings.getMiniMode()))
        {
            processMiniMode(sharedPreferences);
        }
    }

    private void processMiniMode(SharedPreferences sharedPreferences) {
        if(sharedPreferences.getBoolean(Settings.getMiniMode(), false))
        {
            MiniModeProvider.enableMiniMode();
        }
        else {
            MiniModeProvider.disableMiniMode();
        }
    }

    private void processTouchpadScrolling(SharedPreferences sharedPreferences) {
        if(sharedPreferences.getBoolean(Settings.getTouchpadScrolling(), false))
        {
            TouchpadProcessor.enableTouchpadScrolling(false);
        }
        else {
            TouchpadProcessor.disableTouchpadScrolling(false);
        }
    }
}
