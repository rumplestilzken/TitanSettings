package com.rumplestilzken.gargoylesettings.provider;

import android.util.Log;

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
//            DataInputStream is = new DataInputStream(p.getInputStream());

            os.writeBytes(command + "\n");

            os.writeBytes("exit\n");

            os.flush();
            os.close();

//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
//                String output = new String(is.readAllBytes(), StandardCharsets.UTF_8);
//                Log.d(RootProvider.class.toString(), output);
//            }
//            is.close();

            try { p.waitFor(); } catch (InterruptedException e) { Log.d("EXCEPTION", e.toString());}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Remount() {
        RunAsRoot("remount");
    }
}
