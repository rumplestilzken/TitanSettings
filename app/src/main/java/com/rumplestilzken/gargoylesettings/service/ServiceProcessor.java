package com.rumplestilzken.gargoylesettings.service;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServiceProcessor {

    public static void startOrStop(boolean start, String service) {
        String command = start ? "start" : "stop";
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());

            os.writeBytes(command + " " + service + " \n");

            os.writeBytes("exit\n");

            os.flush();
            os.close();
            try { p.waitFor(); } catch (InterruptedException e) {}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
