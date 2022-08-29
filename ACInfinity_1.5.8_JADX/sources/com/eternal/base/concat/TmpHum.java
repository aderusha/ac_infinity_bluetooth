package com.eternal.base.concat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TmpHum implements Cloneable {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
    public byte fan;
    public char hum;
    public boolean off;
    public long portFan;
    public int portState;
    public long time;
    public int tmp;
    public int vpd;

    private int castInt(String str) {
        try {
            return (int) (format.parse(str).getTime() / 1000);
        } catch (ParseException unused) {
            return 0;
        }
    }

    public TmpHum clone() {
        try {
            TmpHum tmpHum = (TmpHum) super.clone();
            tmpHum.time = this.time;
            tmpHum.tmp = this.tmp;
            tmpHum.hum = this.hum;
            tmpHum.off = this.off;
            tmpHum.portState = this.portState;
            tmpHum.vpd = this.vpd;
            tmpHum.fan = this.fan;
            tmpHum.portFan = this.portFan;
            return tmpHum;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTmpValue() {
        return this.tmp;
    }

    public String toString() {
        return "TmpHum{time=" + format.format(Long.valueOf(this.time * 1000)) + ", tmp=" + this.tmp + ", hum=" + this.hum + ", off=" + this.off + ", fan=" + this.fan + ", vpd=" + this.vpd + ", portFan=" + this.portFan + ", portState=" + this.portState + '}';
    }
}
