package com.rumplestilzken.gargoylesettings.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.rumplestilzken.gargoylesettings.R;
import com.rumplestilzken.gargoylesettings.settings.SettingsChangeListener;
import com.rumplestilzken.gargoylesettings.provider.RootProvider;
import com.rumplestilzken.gargoylesettings.touchpad.TouchpadProcessor;

public class SettingsActivity extends AppCompatActivity {

    private static final SharedPreferences.OnSharedPreferenceChangeListener preferenceBinder = SettingsChangeListener.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        settings.registerOnSharedPreferenceChangeListener(preferenceBinder);

        RootProvider.EnableRoot();
        TouchpadProcessor.setContext(getApplicationContext());


//        Button save_button = (Button)findViewById(R.id.save_button);
//        save_button.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
////                PreferenceManager.setDefaultValues(view.getContext(), R.xml.root_preferences, false);
//
//                Log.d("SettingsActivity", "Touchpad Scrolling:" + (settings.getBoolean(Settings.getTouchpadScrolling(), false) ? "Enabled" : "Disabled"));
//            }
//        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }


}