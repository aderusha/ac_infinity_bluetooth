package com.eternal.base.utils;

import android.util.Log;
import java.io.File;

public class FileUtil {
    public static boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            boolean z = true;
            for (File file2 : file.listFiles()) {
                if (!file2.isFile()) {
                    if (file2.isDirectory() && !(z = deleteDirectory(file2.getAbsolutePath()))) {
                        break;
                    }
                } else {
                    z = deleteSingleFile(file2.getAbsolutePath());
                    if (!z) {
                        break;
                    }
                }
            }
            if (z && file.delete()) {
                Log.e("--Method--", "Copy_Delete.deleteDirectory: 删除目录" + str + "成功！");
                return true;
            }
        }
        return false;
    }

    public static boolean delete(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return deleteSingleFile(str);
        }
        return deleteDirectory(str);
    }

    public static boolean deleteSingleFile(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile() || !file.delete()) {
            return false;
        }
        Log.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + str + "成功！");
        return true;
    }
}
