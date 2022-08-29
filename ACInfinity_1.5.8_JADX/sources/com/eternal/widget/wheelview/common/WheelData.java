package com.eternal.widget.wheelview.common;

import java.io.Serializable;

public class WheelData implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: id */
    private int f260id;
    private String name;

    public WheelData() {
    }

    public WheelData(int i, String str) {
        this.f260id = i;
        this.name = str;
    }

    public int getId() {
        return this.f260id;
    }

    public void setId(int i) {
        this.f260id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "WheelData{" + "id=" + this.f260id + ", name='" + this.name + '\'' + '}';
    }
}
