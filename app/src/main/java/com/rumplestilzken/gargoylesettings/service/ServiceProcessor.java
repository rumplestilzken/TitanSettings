package com.rumplestilzken.gargoylesettings.service;

import com.rumplestilzken.gargoylesettings.provider.RootProvider;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServiceProcessor {

    public static void startOrStop(boolean start, String service) {
        String command = start ? "start" : "stop";
        RootProvider.RunAsRoot(command + " " + service);
    }
}
