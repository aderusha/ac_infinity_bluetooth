package com.eternal.base.concat;

public class Version {
    public boolean isForce;
    public String showVersion;
    public String update;
    public String version;

    public Version(String str, boolean z) {
        this.showVersion = str;
        this.isForce = z;
    }
}
