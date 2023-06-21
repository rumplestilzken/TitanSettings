package com.rumplestilzken.gargoylesettings.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.rumplestilzken.gargoylesettings.R;
import com.rumplestilzken.gargoylesettings.provider.MiniModeProvider;
import com.rumplestilzken.gargoylesettings.settings.SettingsChangeListener;
import com.rumplestilzken.gargoylesettings.provider.RootProvider;
import com.rumplestilzken.gargoylesettings.touchpad.TouchpadProcessor;

public class SettingsActivity extends AppCompatActivity {
    SensorManager sm;
    Sensor accelorometer;

    MiniModeProvider miniModeProvider = new MiniModeProvider();

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(miniModeProvider, accelorometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(miniModeProvider);
    }

    private static final SharedPreferences.OnSharedPreferenceChangeListener preferenceBinder = SettingsChangeListener.INSTANCE;

    static Context context = null;

    public static Context getContext(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

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

        //Store the original dispaly width, one time, before ever having a chance to change it.
        int width = settings.getInt("original_display_width", -1);
        if(width == -1)
        {
            SharedPreferences.Editor editor = settings.edit();
            width = Resources.getSystem().getDisplayMetrics().widthPixels;
            editor.putInt("original_display_width", width);
            editor.apply();
        }
        MiniModeProvider.setDisplayWidth(width);

        int height = settings.getInt("original_display_height", -1);
        if(height == -1)
        {
            SharedPreferences.Editor editor = settings.edit();
            height = Resources.getSystem().getDisplayMetrics().heightPixels;
            editor.putInt("original_display_height", height);
            editor.apply();
        }
        MiniModeProvider.setDisplayHeight(height);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelorometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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