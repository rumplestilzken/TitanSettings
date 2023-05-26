package com.rumplestilzken.gargoylesettings.provider;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.rumplestilzken.gargoylesettings.activity.SettingsActivity;

import java.lang.reflect.InvocationTargetException;

public class MiniModeProvider {

    static int displayWidth = 0;

    public static void setDisplayWidth(int width) {
        displayWidth = width;
    }

    public static int displayWidth() {
        return displayWidth;
    }

    public static void enableMiniMode() {
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
        double miniModeMultiplier = width/3;
        int newWidth = width - (int)miniModeMultiplier;

        RootProvider.RunAsRoot("wm size " + newWidth + "x" + screenHeight);
    }

    public static void disableMiniMode() {
        WindowManager wm = (WindowManager) SettingsActivity.getContext().getSystemService(Context.WINDOW_SERVICE);

        Point realSize = new Point();
        Display d = wm.getDefaultDisplay();
        int screenHeight;
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
        screenHeight = realSize.y;

        RootProvider.RunAsRoot("wm size " + displayWidth + "x" + screenHeight);
    }
}
