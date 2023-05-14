package com.rumplestilzken.gargoylesettings.provider;

public class RebootProvider {
    private static boolean requiresReboot = false;
    public static void setRequiresReboot(boolean reboot)
    {
        requiresReboot = reboot;
        if(requiresReboot)
        {
            //TODO: Register PopupWindow Activity
        }
    }
    public static boolean requiresReboot() {
        return requiresReboot;
    }
}
