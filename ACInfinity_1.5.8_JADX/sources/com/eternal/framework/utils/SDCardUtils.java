package com.eternal.framework.utils;

import android.os.Environment;
import android.os.StatFs;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public final class SDCardUtils {
    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getSDCardPath() {
        BufferedReader bufferedReader = null;
        if (!isSDCardEnable()) {
            return null;
        }
        try {
            Process exec = Runtime.getRuntime().exec("cat /proc/mounts");
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(exec.getInputStream())));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        if (readLine.contains("sdcard") && readLine.contains(".android_secure")) {
                            String[] split = readLine.split(" ");
                            if (split.length >= 5) {
                                String str = split[1].replace("/.android_secure", "") + File.separator;
                                CloseUtils.closeIO(bufferedReader2);
                                return str;
                            }
                        }
                        if (exec.waitFor() != 0 && exec.exitValue() == 1) {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    e = e;
                    bufferedReader = bufferedReader2;
                    try {
                        e.printStackTrace();
                        CloseUtils.closeIO(bufferedReader);
                        return Environment.getExternalStorageDirectory().getPath() + File.separator;
                    } catch (Throwable th) {
                        th = th;
                        CloseUtils.closeIO(bufferedReader);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    CloseUtils.closeIO(bufferedReader);
                    throw th;
                }
            }
            CloseUtils.closeIO(bufferedReader2);
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            CloseUtils.closeIO(bufferedReader);
            return Environment.getExternalStorageDirectory().getPath() + File.separator;
        }
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }

    public static String getDataPath() {
        if (!isSDCardEnable()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "data" + File.separator;
    }

    public static String getFreeSpace() {
        if (!isSDCardEnable()) {
            return null;
        }
        StatFs statFs = new StatFs(getSDCardPath());
        return ConvertUtils.byte2FitMemorySize(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
    }

    public static String getSDCardInfo() {
        if (!isSDCardEnable()) {
            return null;
        }
        SDCardInfo sDCardInfo = new SDCardInfo();
        sDCardInfo.isExist = true;
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        sDCardInfo.totalBlocks = statFs.getBlockCountLong();
        sDCardInfo.blockByteSize = statFs.getBlockSizeLong();
        sDCardInfo.availableBlocks = statFs.getAvailableBlocksLong();
        sDCardInfo.availableBytes = statFs.getAvailableBytes();
        sDCardInfo.freeBlocks = statFs.getFreeBlocksLong();
        sDCardInfo.freeBytes = statFs.getFreeBytes();
        sDCardInfo.totalBytes = statFs.getTotalBytes();
        return sDCardInfo.toString();
    }

    public static class SDCardInfo {
        long availableBlocks;
        long availableBytes;
        long blockByteSize;
        long freeBlocks;
        long freeBytes;
        boolean isExist;
        long totalBlocks;
        long totalBytes;

        public String toString() {
            return "isExist=" + this.isExist + "\ntotalBlocks=" + this.totalBlocks + "\nfreeBlocks=" + this.freeBlocks + "\navailableBlocks=" + this.availableBlocks + "\nblockByteSize=" + this.blockByteSize + "\ntotalBytes=" + this.totalBytes + "\nfreeBytes=" + this.freeBytes + "\navailableBytes=" + this.availableBytes;
        }
    }
}
