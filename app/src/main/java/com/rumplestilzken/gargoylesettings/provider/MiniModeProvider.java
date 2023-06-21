package com.rumplestilzken.gargoylesettings.provider;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.rumplestilzken.gargoylesettings.activity.SettingsActivity;

import java.lang.reflect.InvocationTargetException;

public class MiniModeProvider implements SensorEventListener {



    public enum orientation {Portrait, Portrait_Flip, Landscape_Left, Landscape_Right};

    orientation lastOrientation = MiniModeProvider.orientation.Portrait;

    static int displayWidth = 0;
    static int displayHeight = 0;

    static boolean isEnabled = false;

    public static void setDisplayHeight(int height) {
        displayHeight = height;
    }

    public static int displayHeight() {
        return displayHeight;
    }

    public static void setDisplayWidth(int width) {
        displayWidth = width;
    }

    public static int displayWidth() {
        return displayWidth;
    }

    public static void enableMiniMode(orientation orientation) {
        isEnabled = true;
        WindowManager wm = (WindowManager) SettingsActivity.getContext().getSystemService(Context.WINDOW_SERVICE);

        Point realSize = new Point();
        Display d = wm.getDefaultDisplay();
        int screenWidth, screenHeight;
        try {
            Display.class.getMethod("getRealSize", Point.class).invoke(
                    d, realSize);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        screenWidth = realSize.x;
        screenHeight = realSize.y;

        int width = screenWidth;
        double miniModeMultiplier = displayWidth/3.3;
        int newWidth;

        if(orientation == MiniModeProvider.orientation.Landscape_Left ||
        orientation == MiniModeProvider.orientation.Landscape_Right)
        {
            newWidth = displayWidth;
            screenHeight = displayHeight - (int)miniModeMultiplier;
        }
        else {
            screenHeight = displayHeight;
            newWidth = displayWidth - (int)miniModeMultiplier;
        }

        RootProvider.RunAsRoot("wm size " + newWidth + "x" + screenHeight);
    }

    public static void disableMiniMode() {
        isEnabled = false;
        WindowManager wm = (WindowManager) SettingsActivity.getContext().getSystemService(Context.WINDOW_SERVICE);
        RootProvider.RunAsRoot("wm size " + displayWidth + "x" + displayHeight);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!isEnabled)
        {
            return;
        }

        orientation orientation = MiniModeProvider.orientation.Portrait;

        // Vertical
        if (Math.abs(sensorEvent.values[1]) > Math.abs(sensorEvent.values[0]) &&
                Math.abs(sensorEvent.values[1]) > Math.abs(sensorEvent.values[2]))
            if (sensorEvent.values[1] > 0)
                orientation = MiniModeProvider.orientation.Portrait; // Head Up
            else
                orientation = MiniModeProvider.orientation.Portrait_Flip; // Head Down

            // Horizontal
        else if (Math.abs(sensorEvent.values[0]) > Math.abs(sensorEvent.values[1]) &&
                Math.abs(sensorEvent.values[0]) > Math.abs(sensorEvent.values[2]))
            if (sensorEvent.values[0] > 0)
                orientation = MiniModeProvider.orientation.Landscape_Left; // Left
            else
                orientation = MiniModeProvider.orientation.Landscape_Right; // Right

        if(lastOrientation != orientation) {
            lastOrientation = orientation;
            enableMiniMode(orientation);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
