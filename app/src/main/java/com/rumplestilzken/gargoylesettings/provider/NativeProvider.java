package com.rumplestilzken.gargoylesettings.provider;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class NativeProvider {

    Activity activity  = null;
    public NativeProvider(Activity activity) {
        this.activity = activity;
        setupNativeBindings();
    }

    private native void setupNativeBindings();

    private boolean IsTextViewFocused() {
        View v = activity.getCurrentFocus();
        return v instanceof TextView;
    }
}
