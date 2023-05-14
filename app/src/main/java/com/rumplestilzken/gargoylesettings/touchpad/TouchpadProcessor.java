package com.rumplestilzken.gargoylesettings.touchpad;

import android.util.Log;

import com.rumplestilzken.gargoylesettings.provider.RebootProvider;
import com.rumplestilzken.gargoylesettings.service.ServiceProcessor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TouchpadProcessor {
    public static void enableTouchpadScrolling() {
        Log.d("TouchpadProcessor", "Starting uinput-titan");
        ServiceProcessor.startOrStop(true, "uninput-titan");
        RebootProvider.setRequiresReboot(true);
        //TODO: Remove /etc/excluded-input-devices.xml
    }

    public static void disableTouchpadScrolling() {
       Log.d("TouchpadProcessor", "Stopping uinput-titan");
       ServiceProcessor.startOrStop(false, "uninput-titan");
       RebootProvider.setRequiresReboot(true);
        //TODO: Replace /etc/excluded-input-devices.xml
    }

}
