package com.alibaba.android.arouter.utils;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.android.arouter.facade.template.ILogger;

public class DefaultLogger implements ILogger {
    private static boolean isMonitorMode = false;
    private static boolean isShowLog = false;
    private static boolean isShowStackTrace = false;
    private String defaultTag = Consts.SDK_NAME;

    public void showLog(boolean z) {
        isShowLog = z;
    }

    public void showStackTrace(boolean z) {
        isShowStackTrace = z;
    }

    public void showMonitor(boolean z) {
        isMonitorMode = z;
    }

    public DefaultLogger() {
    }

    public DefaultLogger(String str) {
        this.defaultTag = str;
    }

    public void debug(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.d(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    public void info(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.i(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    public void warning(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.w(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    public void error(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.e(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    public void monitor(String str) {
        if (isShowLog && isMonitorMode()) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(this.defaultTag + "::monitor", str + getExtInfo(stackTraceElement));
        }
    }

    public boolean isMonitorMode() {
        return isMonitorMode;
    }

    public String getDefaultTag() {
        return this.defaultTag;
    }

    public static String getExtInfo(StackTraceElement stackTraceElement) {
        StringBuilder sb = new StringBuilder("[");
        if (isShowStackTrace) {
            String name = Thread.currentThread().getName();
            String fileName = stackTraceElement.getFileName();
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            long id = Thread.currentThread().getId();
            int lineNumber = stackTraceElement.getLineNumber();
            sb.append("ThreadId=");
            sb.append(id);
            sb.append(" & ");
            sb.append("ThreadName=");
            sb.append(name);
            sb.append(" & ");
            sb.append("FileName=");
            sb.append(fileName);
            sb.append(" & ");
            sb.append("ClassName=");
            sb.append(className);
            sb.append(" & ");
            sb.append("MethodName=");
            sb.append(methodName);
            sb.append(" & ");
            sb.append("LineNumber=");
            sb.append(lineNumber);
        }
        sb.append(" ] ");
        return sb.toString();
    }
}
