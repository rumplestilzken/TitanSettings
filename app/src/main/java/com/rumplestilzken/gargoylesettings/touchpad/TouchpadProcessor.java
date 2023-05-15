package com.rumplestilzken.gargoylesettings.touchpad;

import android.content.Context;
import android.util.Log;

import com.rumplestilzken.gargoylesettings.R;
import com.rumplestilzken.gargoylesettings.provider.RebootProvider;
import com.rumplestilzken.gargoylesettings.provider.RootProvider;
import com.rumplestilzken.gargoylesettings.service.ServiceProcessor;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TouchpadProcessor {
    private static Context context = null;
    public static void setContext(Context cnxt) {
        context = cnxt;
    }

    public static Context getContext() {
        return context;
    }

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
       InputStream in = context.getResources().openRawResource(R.raw.excluded_input_devices);
       FileOutputStream out = null;
       try {
           out = new FileOutputStream("/etc/excluded-input-devices.xml");
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }
       byte[] buff = new byte[1024];
       int read = 0;
       try {
           while ((read = in.read(buff)) > 0) {
               out.write(buff, 0, read);
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       } finally {
           try {
               in.close();
               out.close();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

       }
    }

}
