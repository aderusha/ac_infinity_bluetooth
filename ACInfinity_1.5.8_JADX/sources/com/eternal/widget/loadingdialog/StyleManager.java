package com.eternal.widget.loadingdialog;

import com.eternal.widget.loadingdialog.LoadingDialog;

public class StyleManager {
    private int contentSize = -1;
    private String failedText = "加载失败";
    private boolean interceptBack = true;
    private int loadStyle = 0;
    private String loadText = "加载中...";
    private boolean openAnim = true;
    private int repeatTime;
    private long showTime = -1;
    private LoadingDialog.Speed speed = LoadingDialog.Speed.SPEED_TWO;
    private String successText = "加载成功";
    private int textSize = -1;

    public static StyleManager getDefault() {
        return new StyleManager(true, 0, LoadingDialog.Speed.SPEED_TWO, -1, -1, 1000, true, "Loading...", "Success", "Failure");
    }

    public StyleManager() {
    }

    public StyleManager(boolean z, int i, LoadingDialog.Speed speed2, int i2, int i3, long j, boolean z2, String str, String str2, String str3) {
        this.openAnim = z;
        this.repeatTime = i;
        this.speed = speed2;
        this.contentSize = i2;
        this.textSize = i3;
        this.showTime = j;
        this.interceptBack = z2;
        this.loadText = str;
        this.successText = str2;
        this.failedText = str3;
    }

    public StyleManager(boolean z, int i, LoadingDialog.Speed speed2, int i2, int i3, long j, boolean z2, String str, String str2, String str3, int i4) {
        this.openAnim = z;
        this.repeatTime = i;
        this.speed = speed2;
        this.contentSize = i2;
        this.textSize = i3;
        this.showTime = j;
        this.interceptBack = z2;
        this.loadText = str;
        this.successText = str2;
        this.failedText = str3;
        this.loadStyle = i4;
    }

    public StyleManager setLoadStyle(int i) {
        this.loadStyle = i;
        return this;
    }

    public int getLoadStyle() {
        return this.loadStyle;
    }

    public boolean isOpenAnim() {
        return this.openAnim;
    }

    public String getFailedText() {
        return this.failedText;
    }

    public String getSuccessText() {
        return this.successText;
    }

    public String getLoadText() {
        return this.loadText;
    }

    public boolean isInterceptBack() {
        return this.interceptBack;
    }

    public long getShowTime() {
        return this.showTime;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public int getContentSize() {
        return this.contentSize;
    }

    public LoadingDialog.Speed getSpeed() {
        return this.speed;
    }

    public int getRepeatTime() {
        return this.repeatTime;
    }

    public StyleManager Anim(boolean z) {
        this.openAnim = z;
        return this;
    }

    public StyleManager repeatTime(int i) {
        this.repeatTime = i;
        return this;
    }

    public StyleManager speed(LoadingDialog.Speed speed2) {
        this.speed = speed2;
        return this;
    }

    public StyleManager contentSize(int i) {
        this.contentSize = i;
        return this;
    }

    public StyleManager textSize(int i) {
        this.textSize = i;
        return this;
    }

    public StyleManager showTime(long j) {
        this.showTime = j;
        return this;
    }

    public StyleManager intercept(boolean z) {
        this.interceptBack = z;
        return this;
    }

    public StyleManager loadText(String str) {
        this.loadText = str;
        return this;
    }

    public StyleManager successText(String str) {
        this.successText = str;
        return this;
    }

    public StyleManager failedText(String str) {
        this.failedText = str;
        return this;
    }
}
