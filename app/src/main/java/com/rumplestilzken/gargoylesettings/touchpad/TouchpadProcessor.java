package com.rumplestilzken.gargoylesettings.touchpad;

import android.util.Log;

import com.rumplestilzken.gargoylesettings.R;
import com.rumplestilzken.gargoylesettings.provider.RebootProvider;
import com.rumplestilzken.gargoylesettings.provider.RootProvider;
import com.rumplestilzken.gargoylesettings.service.ServiceProcessor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TouchpadProcessor {
    public static void enableTouchpadScrolling() {
        Log.d("TouchpadProcessor", "Starting uinput_titan");
        ServiceProcessor.startOrStop(true, "uninput_titan");
        RebootProvider.setRequiresReboot(true);
        RootProvider.Remount();
        RootProvider.RunAsRoot("rm /etc/excluded-input-devices.xml");
    }

    public static void disableTouchpadScrolling() {
       Log.d("TouchpadProcessor", "Stopping uinput_titan");
       ServiceProcessor.startOrStop(false, "uninput_titan");
       RebootProvider.setRequiresReboot(true);
       RootProvider.Remount();
       RootProvider.RunAsRoot("touch /etc/excluded-input-devices.xml; echo \"<devices><device name=\"mtk-pad\"/></devices>\n\" >> /etc/excluded-input-devices.xml");
    }

}
