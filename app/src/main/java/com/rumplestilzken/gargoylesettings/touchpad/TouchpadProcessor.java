package com.rumplestilzken.gargoylesettings.touchpad;

import com.rumplestilzken.gargoylesettings.service.ServiceProcessor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TouchpadProcessor {
    public static void enableTouchpadScrolling() {
        ServiceProcessor.startOrStop(true, "uninput-titan");
    }

    public static void disableTouchpadScrolling() {
       ServiceProcessor.startOrStop(false, "uninput-titan");
    }

}
