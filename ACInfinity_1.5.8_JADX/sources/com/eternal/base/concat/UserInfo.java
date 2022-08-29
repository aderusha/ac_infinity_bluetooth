package com.eternal.base.concat;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("appEmail")
    private String email;
    @SerializedName("appIsanalytics")
    private int isAnalytics;
    @SerializedName("appIsbugreport")
    private int isBugReportOpen;
    @SerializedName("appIsemailrepost")
    private int isEmailRepost;
    @SerializedName("appUsable")
    private int isForbiden;
    @SerializedName("appPasswordl")
    private String password;
    @SerializedName("appId")
    private String token;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public int getIsBugReportOpen() {
        return this.isBugReportOpen;
    }

    public void setIsBugReportOpen(int i) {
        this.isBugReportOpen = i;
    }

    public int getIsEmailRepost() {
        return this.isEmailRepost;
    }

    public void setIsEmailRepost(int i) {
        this.isEmailRepost = i;
    }

    public int getIsAnalytics() {
        return this.isAnalytics;
    }

    public void setIsAnalytics(int i) {
        this.isAnalytics = i;
    }

    public int getIsForbiden() {
        return this.isForbiden;
    }

    public void setIsForbiden(int i) {
        this.isForbiden = i;
    }
}
