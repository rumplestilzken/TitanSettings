package com.rumplestilzken.gargoylesettings.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityWindowInfo;

public class TouchpadOnScreenKeyboardService extends AccessibilityService {

    static boolean previousKeyboardState = false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        boolean keyboardOnscreen = false;
        for(AccessibilityWindowInfo windowInfo : getWindows())
        {
            if(windowInfo.getType() == AccessibilityWindowInfo.TYPE_INPUT_METHOD)
            {
                keyboardOnscreen = true;
            }
        }

        if(keyboardOnscreen && !previousKeyboardState)
        {
            previousKeyboardState = true;
            Log.i(TouchpadOnScreenKeyboardService.class.toString(), "keyboard is opened!");
            ServiceProcessor.startOrStop(false, "uinput_titan");
        }
        else if(!keyboardOnscreen && previousKeyboardState) {
            previousKeyboardState = false;
            Log.i(TouchpadOnScreenKeyboardService.class.toString(), "keyboard is opened!");
            ServiceProcessor.startOrStop(true, "uinput_titan");
        }
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        ServiceProcessor.startOrStop(true, "uinput_titan");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ServiceProcessor.startOrStop(true, "uinput_titan");
        return super.onUnbind(intent);
    }

    @Override
    public void onInterrupt() {

    }
}
