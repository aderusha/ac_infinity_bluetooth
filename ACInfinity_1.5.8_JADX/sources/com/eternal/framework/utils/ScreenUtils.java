package com.eternal.framework.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;

public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Point getDisplayDimensions(Activity activity) {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics currentWindowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            return new Point(currentWindowMetrics.getBounds().width(), currentWindowMetrics.getBounds().height());
        }
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) Utils.getContext().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) Utils.getContext().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    public static int getAppScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getAppScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }

    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int getScreenDensityDpi() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public static void setFullScreen(Activity activity) {
        activity.getWindow().addFlags(1024);
    }

    public static void setNonFullScreen(Activity activity) {
        activity.getWindow().clearFlags(1024);
    }

    public static void toggleFullScreen(Activity activity) {
        boolean isFullScreen = isFullScreen(activity);
        Window window = activity.getWindow();
        if (isFullScreen) {
            window.clearFlags(1024);
        } else {
            window.addFlags(1024);
        }
    }

    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 1024;
    }

    public static void setLandscape(Activity activity) {
        activity.setRequestedOrientation(-1);
    }

    public static void setPortrait(Activity activity) {
        activity.setRequestedOrientation(-1);
    }

    public static boolean isLandscape() {
        return Utils.getContext().getResources().getConfiguration().orientation == 2;
    }

    public static boolean isPortrait() {
        return Utils.getContext().getResources().getConfiguration().orientation == 1;
    }

    public static int getScreenRotation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    public static Bitmap screenShot(Activity activity) {
        return screenShot(activity, false);
    }

    public static Bitmap screenShot(Activity activity, boolean z) {
        Bitmap bitmap;
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.setWillNotCacheDrawing(false);
        Bitmap drawingCache = decorView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (z) {
            Resources resources = activity.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
            bitmap = Bitmap.createBitmap(drawingCache, 0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
        } else {
            bitmap = Bitmap.createBitmap(drawingCache, 0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        decorView.destroyDrawingCache();
        return bitmap;
    }

    public static boolean isScreenLock() {
        KeyguardManager keyguardManager = (KeyguardManager) Utils.getContext().getSystemService("keyguard");
        if (keyguardManager == null) {
            return false;
        }
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static void setSleepDuration(int i) {
        Settings.System.putInt(Utils.getContext().getContentResolver(), "screen_off_timeout", i);
    }

    public static int getSleepDuration() {
        try {
            return Settings.System.getInt(Utils.getContext().getContentResolver(), "screen_off_timeout");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }
}
