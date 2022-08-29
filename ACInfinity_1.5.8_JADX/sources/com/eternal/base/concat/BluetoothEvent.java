package com.eternal.base.concat;

public class BluetoothEvent {
    public static final byte AUTOMATION_ACTIVE = 6;
    public static final byte CONFIG_NETWORKING = 11;
    public static final byte CONNECT = 2;
    public static final byte CONNECT_TYPE = 10;
    public static final byte DISCONNECT = 0;
    public static final byte GPS_SWITCH = 9;
    public static final byte RECONNECTED = 12;
    public static final byte REFRESH_BADGE = 1;
    public static final byte REFRESH_TFP = 7;
    public static final byte STATE_OFF = 3;
    public static final byte STATE_ON = 4;
    public static final byte TEMP_UNIT = 5;
    public static final byte TIME_ZONE = 8;

    /* renamed from: b1 */
    public final byte f134b1;

    /* renamed from: i1 */
    public final int f135i1;
    public final String mac;
    public final Object obj;
    public final byte what;

    public BluetoothEvent(byte b, String str, int i) {
        this(b, str, i, (byte) 0);
    }

    public BluetoothEvent(byte b, String str, int i, byte b2) {
        this(b, str, i, b2, (Object) null);
    }

    public BluetoothEvent(byte b, String str, int i, byte b2, Object obj2) {
        this.what = b;
        this.mac = str;
        this.f135i1 = i;
        this.f134b1 = b2;
        this.obj = obj2;
    }

    public BluetoothEvent(byte b, String str, Object obj2) {
        this(b, str, 0, (byte) 0, obj2);
    }

    public BluetoothEvent(byte b, String str) {
        this(b, str, 0);
    }

    public BluetoothEvent(byte b, int i) {
        this(b, (String) null, i);
    }
}
