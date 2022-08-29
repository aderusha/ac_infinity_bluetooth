package com.telink.p010lt.ble;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.telink.lt.ble.PropertyResolver */
public class PropertyResolver {
    public static final String INDICATE = "indicate";
    public static final String NOTIFY = "notify";
    public static final String READ = "read";
    public static final String WRITE = "write";
    public static final String WRITE_NO_RESPONSE = "write_no_response";
    private int mProp;
    private Map<String, Boolean> properties;

    public PropertyResolver(int i) {
        this.mProp = i;
        HashMap hashMap = new HashMap();
        this.properties = hashMap;
        boolean z = true;
        hashMap.put(READ, Boolean.valueOf((i & 2) != 0));
        this.properties.put(WRITE, Boolean.valueOf((i & 8) != 0));
        this.properties.put(NOTIFY, Boolean.valueOf((i & 16) != 0));
        this.properties.put(INDICATE, Boolean.valueOf((i & 32) != 0));
        this.properties.put(WRITE_NO_RESPONSE, Boolean.valueOf((i & 4) == 0 ? false : z));
    }

    public boolean contains(String str) {
        return this.properties.containsKey(str) && this.properties.get(str).booleanValue();
    }

    public String getGattCharacteristicPropDesc() {
        String str = " ";
        if ((this.mProp & 2) != 0) {
            str = str + "read ";
        }
        if ((this.mProp & 8) != 0) {
            str = str + "write ";
        }
        if ((this.mProp & 16) != 0) {
            str = str + "notify ";
        }
        if ((this.mProp & 32) != 0) {
            str = str + "indicate ";
        }
        if ((this.mProp & 4) == 0) {
            return str;
        }
        return str + "write_no_response ";
    }
}
