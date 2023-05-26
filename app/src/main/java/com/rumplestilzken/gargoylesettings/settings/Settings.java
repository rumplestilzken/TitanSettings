package com.rumplestilzken.gargoylesettings.settings;

public class Settings {
    private static final String touchpad_scrolling = "key_enable_touch_scrolling";
    private static final String mini_mode = "key_enable_mini_mode";

    public static final String getTouchpadScrolling(){
        return touchpad_scrolling;
    }

    public static final String getMiniMode() {
        return mini_mode;
    }
}
