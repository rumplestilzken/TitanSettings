package com.rumplestilzken.gargoylesettings.service;

import android.content.Intent;
import android.util.Log;

import com.rumplestilzken.gargoylesettings.provider.RootProvider;

import java.io.DataOutputStream;
import java.io.IOException;
import java.security.Provider;

public class ServiceProcessor {

    public static void startOrStop(boolean start, String service) {
        String com = start ? "start" : "stop";
//        String command = "setprop ctl." + com + " " + service;
        String command = com + " " + service;
        Log.d(ServiceProcessor.class.toString(), command);
        RootProvider.RunAsRoot(command);
    }
}
