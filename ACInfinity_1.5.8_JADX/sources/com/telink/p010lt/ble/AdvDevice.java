package com.telink.p010lt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.telink.lt.ble.AdvDevice */
public class AdvDevice implements Parcelable {
    public static final Parcelable.Creator<AdvDevice> CREATOR = new Parcelable.Creator<AdvDevice>() {
        public AdvDevice createFromParcel(Parcel parcel) {
            return new AdvDevice(parcel);
        }

        public AdvDevice[] newArray(int i) {
            return new AdvDevice[i];
        }
    };
    public BluetoothDevice device;
    public int rssi;
    public byte[] scanRecord;

    public int describeContents() {
        return 0;
    }

    public AdvDevice(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.device = bluetoothDevice;
        this.rssi = i;
        this.scanRecord = bArr;
    }

    public AdvDevice(Parcel parcel) {
        this.device = (BluetoothDevice) parcel.readParcelable(getClass().getClassLoader());
        this.rssi = parcel.readInt();
        byte[] bArr = new byte[parcel.readInt()];
        this.scanRecord = bArr;
        parcel.readByteArray(bArr);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.device, 0);
        parcel.writeInt(this.rssi);
        parcel.writeInt(this.scanRecord.length);
        parcel.writeByteArray(this.scanRecord);
    }
}
