package com.rumplestilzken.gargoylesettings.settings;

public class Settings {
    private static final String touchpad_scrolling = "key_enable_touch_scrolling";
    private static final String double_tap_home_button = "key_enable_double_tap_home_button";

    public static final String getTouchpadScrolling(){
        return touchpad_scrolling;
    }

    public static final String getDoubleTapHomeButton() {
        return double_tap_home_button;
    }
}
