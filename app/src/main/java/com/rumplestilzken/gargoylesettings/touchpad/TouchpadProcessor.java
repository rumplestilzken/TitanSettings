package com.rumplestilzken.gargoylesettings.touchpad;

import android.content.Context;
import android.util.Log;

import com.rumplestilzken.gargoylesettings.R;
import com.rumplestilzken.gargoylesettings.provider.RebootProvider;
import com.rumplestilzken.gargoylesettings.provider.RootProvider;
import com.rumplestilzken.gargoylesettings.service.ServiceProcessor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import android.util.Base64;

public class TouchpadProcessor {
    private static Context context = null;
    public static void setContext(Context cnxt) {
        context = cnxt;
    }

    public static Context getContext() {
        return context;
    }

    public static void enableTouchpadScrolling(boolean service) {
        Log.d("TouchpadProcessor", "Starting uinput_titan");
        ServiceProcessor.startOrStop(true, "uinput_titan");
        if(!service)
        {
//            RebootProvider.setRequiresReboot(true);
            RootProvider.Remount();
            RootProvider.RunAsRoot("rm /etc/excluded-input-devices.xml");
        }
    }

    public static void disableTouchpadScrolling(boolean service) {
       Log.d("TouchpadProcessor", "Stopping uinput_titan");
       ServiceProcessor.startOrStop(false, "uinput_titan");
       if(!service) {
//           RebootProvider.setRequiresReboot(true);
           RootProvider.Remount();
           RootProvider.RunAsRoot("touch /etc/excluded-input-devices.xml");
           try {
               //excluded-input-devices.xml is needed to fix a bug where UI elements are selected
               //when the keyboard is touched.
               if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                   byte[] bytes = context.getResources().openRawResource(R.raw.excluded_input_devices).readAllBytes();
                   String string = new String(bytes, StandardCharsets.UTF_8);
                   RootProvider.RunAsRoot("echo '" + string + "' > /etc/excluded-input-devices.xml");
               }
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
    }

}
