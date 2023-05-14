package com.rumplestilzken.gargoylesettings.provider;

import java.io.DataOutputStream;
import java.io.IOException;

public class RootProvider {
    public static void EnableRoot()
    {
        RunAsRoot("exit");
    }

    public static void RunAsRoot(String command)
    {
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());

            os.writeBytes(command + "\n");

            os.writeBytes("exit\n");

            os.flush();
            os.close();
            try { p.waitFor(); } catch (InterruptedException e) {}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
