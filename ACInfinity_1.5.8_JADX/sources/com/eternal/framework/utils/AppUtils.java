package com.eternal.framework.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public final class AppUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static boolean isAppForeground() {
        return false;
    }

    public static boolean isAppRoot() {
        return false;
    }

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void openGooglePlay(Context context) {
        try {
            if (context.getPackageName() != null) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
                intent.setPackage("com.android.vending");
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName()));
            intent2.addFlags(268435456);
            context.startActivity(intent2);
        }
    }

    public static void installApp(String str) {
        installApp(getFileByPath(str));
    }

    public static void installApp(File file) {
        if (isFileExists(file)) {
            Utils.getContext().startActivity(getInstallAppIntent(file, true));
        }
    }

    public static void installApp(Activity activity, String str, int i) {
        installApp(activity, getFileByPath(str), i);
    }

    public static void installApp(Activity activity, File file, int i) {
        if (isFileExists(file)) {
            activity.startActivityForResult(getInstallAppIntent(file), i);
        }
    }

    public static void uninstallApp(String str) {
        if (!isSpace(str)) {
            Utils.getContext().startActivity(getUninstallAppIntent(str, true));
        }
    }

    public static void uninstallApp(Activity activity, String str, int i) {
        if (!isSpace(str)) {
            activity.startActivityForResult(getUninstallAppIntent(str), i);
        }
    }

    public static boolean isAppInstalled(String str) {
        try {
            if (Utils.getContext().getPackageManager().getApplicationInfo(str, 0) != null) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppDebug() {
        return isAppDebug(Utils.getContext().getPackageName());
    }

    public static boolean isAppDebug(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null || (applicationInfo.flags & 2) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppSystem() {
        return isAppSystem(Utils.getContext().getPackageName());
    }

    public static boolean isAppSystem(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null || (applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppForeground(String str) {
        return !isSpace(str) && str.equals(getForegroundProcessName());
    }

    public static boolean isAppRunning(String str) {
        try {
            ApplicationInfo applicationInfo = Utils.getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                return false;
            }
            int i = applicationInfo.uid;
            ActivityManager activityManager = (ActivityManager) Utils.getContext().getSystemService("activity");
            if (activityManager != null) {
                List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                if (runningTasks != null && runningTasks.size() > 0) {
                    for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                        if (str.equals(runningTaskInfo.baseActivity.getPackageName())) {
                            return true;
                        }
                    }
                }
                List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                        if (i == runningServiceInfo.uid) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void launchApp(String str) {
        if (!isSpace(str)) {
            Intent launchAppIntent = getLaunchAppIntent(str, true);
            if (launchAppIntent == null) {
                Log.e("AppUtils", "Didn't exist launcher activity.");
            } else {
                Utils.getContext().startActivity(launchAppIntent);
            }
        }
    }

    public static void launchApp(Activity activity, String str, int i) {
        if (!isSpace(str)) {
            Intent launchAppIntent = getLaunchAppIntent(str);
            if (launchAppIntent == null) {
                Log.e("AppUtils", "Didn't exist launcher activity.");
            } else {
                activity.startActivityForResult(launchAppIntent, i);
            }
        }
    }

    public static void relaunchApp() {
        relaunchApp(false);
    }

    public static void relaunchApp(boolean z) {
        Intent launchAppIntent = getLaunchAppIntent(Utils.getContext().getPackageName(), true);
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        launchAppIntent.addFlags(335577088);
        Utils.getContext().startActivity(launchAppIntent);
        if (z) {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    public static void launchAppDetailsSettings() {
        launchAppDetailsSettings(Utils.getContext().getPackageName());
    }

    public static void launchAppDetailsSettings(String str) {
        if (!isSpace(str)) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + str));
            Utils.getContext().startActivity(intent.addFlags(268435456));
        }
    }

    public static Drawable getAppIcon() {
        return getAppIcon(Utils.getContext().getPackageName());
    }

    public static Drawable getAppIcon(String str) {
        if (isSpace(str)) {
            return null;
        }
        try {
            PackageManager packageManager = Utils.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppPackageName() {
        return Utils.getContext().getPackageName();
    }

    public static String getAppName() {
        return getAppName(Utils.getContext().getPackageName());
    }

    public static String getAppName(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageManager packageManager = Utils.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppPath() {
        return getAppPath(Utils.getContext().getPackageName());
    }

    public static String getAppPath(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppVersionName() {
        return getAppVersionName(Utils.getContext().getPackageName());
    }

    public static String getAppVersionName(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppVersionCode() {
        return getAppVersionCode(Utils.getContext().getPackageName());
    }

    public static int getAppVersionCode(String str) {
        if (isSpace(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = Utils.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Signature[] getAppSignature() {
        return getAppSignature(Utils.getContext().getPackageName());
    }

    public static Signature[] getAppSignature(String str) {
        if (isSpace(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = Utils.getContext().getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppSignatureSHA1() {
        return getAppSignatureSHA1(Utils.getContext().getPackageName());
    }

    public static String getAppSignatureSHA1(String str) {
        return getAppSignatureHash(str, "SHA1");
    }

    public static String getAppSignatureSHA256() {
        return getAppSignatureSHA256(Utils.getContext().getPackageName());
    }

    public static String getAppSignatureSHA256(String str) {
        return getAppSignatureHash(str, "SHA256");
    }

    public static String getAppSignatureMD5() {
        return getAppSignatureMD5(Utils.getContext().getPackageName());
    }

    public static String getAppSignatureMD5(String str) {
        return getAppSignatureHash(str, "MD5");
    }

    public static int getAppUid() {
        return getAppUid(Utils.getContext().getPackageName());
    }

    public static int getAppUid(String str) {
        try {
            ApplicationInfo applicationInfo = Utils.getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String getAppSignatureHash(String str, String str2) {
        Signature[] appSignature;
        if (!isSpace(str) && (appSignature = getAppSignature(str)) != null && appSignature.length > 0) {
            return bytes2HexString(hashTemplate(appSignature[0].toByteArray(), str2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
        }
        return "";
    }

    public static AppInfo getAppInfo() {
        return getAppInfo(Utils.getContext().getPackageName());
    }

    public static AppInfo getAppInfo(String str) {
        try {
            PackageManager packageManager = Utils.getContext().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            return getBean(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<AppInfo> getAppsInfo() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = Utils.getContext().getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        for (PackageInfo bean : packageManager.getInstalledPackages(0)) {
            AppInfo bean2 = getBean(packageManager, bean);
            if (bean2 != null) {
                arrayList.add(bean2);
            }
        }
        return arrayList;
    }

    public static AppInfo getApkInfo(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return null;
        }
        return getApkInfo(file.getAbsolutePath());
    }

    public static AppInfo getApkInfo(String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (isSpace(str) || (packageManager = Utils.getContext().getPackageManager()) == null || (packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 0)) == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        return getBean(packageManager, packageArchiveInfo);
    }

    private static AppInfo getBean(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return new AppInfo(packageInfo.packageName, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager), applicationInfo.sourceDir, packageInfo.versionName, packageInfo.versionCode, (applicationInfo.flags & 1) != 0);
    }

    public static class AppInfo {
        private Drawable icon;
        private boolean isSystem;
        private String name;
        private String packageName;
        private String packagePath;
        private int versionCode;
        private String versionName;

        public Drawable getIcon() {
            return this.icon;
        }

        public void setIcon(Drawable drawable) {
            this.icon = drawable;
        }

        public boolean isSystem() {
            return this.isSystem;
        }

        public void setSystem(boolean z) {
            this.isSystem = z;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPackagePath() {
            return this.packagePath;
        }

        public void setPackagePath(String str) {
            this.packagePath = str;
        }

        public int getVersionCode() {
            return this.versionCode;
        }

        public void setVersionCode(int i) {
            this.versionCode = i;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }

        public AppInfo(String str, String str2, Drawable drawable, String str3, String str4, int i, boolean z) {
            setName(str2);
            setIcon(drawable);
            setPackageName(str);
            setPackagePath(str3);
            setVersionName(str4);
            setVersionCode(i);
            setSystem(z);
        }

        public String toString() {
            return "{\n  pkg name: " + getPackageName() + "\n  app icon: " + getIcon() + "\n  app name: " + getName() + "\n  app path: " + getPackagePath() + "\n  app v name: " + getVersionName() + "\n  app v code: " + getVersionCode() + "\n  is system: " + isSystem() + "}";
        }
    }

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static byte[] hashTemplate(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                instance.update(bArr);
                return instance.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    private static Intent getInstallAppIntent(File file) {
        return getInstallAppIntent(file, false);
    }

    private static Intent getInstallAppIntent(File file, boolean z) {
        Uri uri;
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        } else {
            uri = FileProvider.getUriForFile(Utils.getContext(), Utils.getContext().getPackageName() + ".utilcode.provider", file);
            intent.setFlags(1);
        }
        Utils.getContext().grantUriPermission(Utils.getContext().getPackageName(), uri, 1);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return z ? intent.addFlags(268435456) : intent;
    }

    private static Intent getUninstallAppIntent(String str) {
        return getUninstallAppIntent(str, false);
    }

    private static Intent getUninstallAppIntent(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        return z ? intent.addFlags(268435456) : intent;
    }

    private static Intent getLaunchAppIntent(String str) {
        return getLaunchAppIntent(str, false);
    }

    private static Intent getLaunchAppIntent(String str, boolean z) {
        String launcherActivity = getLauncherActivity(str);
        if (launcherActivity.isEmpty()) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str, launcherActivity));
        return z ? intent.addFlags(268435456) : intent;
    }

    private static String getLauncherActivity(String str) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = Utils.getContext().getPackageManager().queryIntentActivities(intent, 0);
        int size = queryIntentActivities.size();
        if (size == 0) {
            return "";
        }
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = queryIntentActivities.get(i);
            if (resolveInfo.activityInfo.processName.equals(str)) {
                return resolveInfo.activityInfo.name;
            }
        }
        return queryIntentActivities.get(0).activityInfo.name;
    }

    private static String getForegroundProcessName() {
        List<UsageStats> list;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.getContext().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.importance == 100) {
                    return next.processName;
                }
            }
        }
        if (Build.VERSION.SDK_INT > 21) {
            PackageManager packageManager = Utils.getContext().getPackageManager();
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            Log.i("ProcessUtils", queryIntentActivities.toString());
            if (queryIntentActivities.size() <= 0) {
                Log.i("ProcessUtils", "getForegroundProcessName: noun of access to usage information.");
                return "";
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(Utils.getContext().getPackageName(), 0);
                AppOpsManager appOpsManager = (AppOpsManager) Utils.getContext().getSystemService("appops");
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    intent.addFlags(268435456);
                    Utils.getContext().startActivity(intent);
                }
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    Log.i("ProcessUtils", "getForegroundProcessName: refuse to device usage stats.");
                    return "";
                }
                UsageStatsManager usageStatsManager = (UsageStatsManager) Utils.getContext().getSystemService("usagestats");
                if (usageStatsManager != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    list = usageStatsManager.queryUsageStats(4, currentTimeMillis - 604800000, currentTimeMillis);
                } else {
                    list = null;
                }
                if (list == null) {
                    return null;
                }
                if (list.isEmpty()) {
                    return null;
                }
                UsageStats usageStats = null;
                for (UsageStats next2 : list) {
                    if (usageStats == null || next2.getLastTimeUsed() > usageStats.getLastTimeUsed()) {
                        usageStats = next2;
                    }
                }
                if (usageStats == null) {
                    return null;
                }
                return usageStats.getPackageName();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void startLocationPermissionActivity(Activity activity, int i) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, i);
    }

    public static void startLocationSourceActivity(Activity activity, int i) {
        activity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), i);
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.isLocationEnabled();
        }
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    public static boolean isNeedTurnOnLocationService(Context context) {
        return Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 31 && !isLocationEnabled(context);
    }

    private static boolean lacksPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == -1;
    }

    public static boolean lacksPermissions(Context context, String[] strArr) {
        for (String lacksPermission : strArr) {
            if (lacksPermission(context, lacksPermission)) {
                return false;
            }
        }
        return true;
    }
}
