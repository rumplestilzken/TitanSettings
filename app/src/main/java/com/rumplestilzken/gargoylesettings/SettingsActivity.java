package com.rumplestilzken.gargoylesettings;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    private final void setupActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
