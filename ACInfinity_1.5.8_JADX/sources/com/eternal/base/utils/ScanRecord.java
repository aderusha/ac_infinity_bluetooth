package com.eternal.base.utils;

import android.os.ParcelUuid;
import android.util.ArrayMap;
import android.util.SparseArray;
import com.eternal.base.global.BluetoothKey;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ScanRecord {
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int DATA_TYPE_SERVICE_DATA_128_BIT = 33;
    private static final int DATA_TYPE_SERVICE_DATA_16_BIT = 22;
    private static final int DATA_TYPE_SERVICE_DATA_32_BIT = 32;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    public static final ParcelUuid GUQIANG_UUID = ParcelUuid.fromString(BluetoothKey.UUID_SERVICE);
    private static final String TAG = "ScanRecord";
    private final int mAdvertiseFlags;
    private final byte[] mBytes;
    private final String mDeviceName;
    private final SparseArray<byte[]> mManufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> mServiceData;
    private final List<ParcelUuid> mServiceUuids;
    private final int mTxPowerLevel;

    public int getAdvertiseFlags() {
        return this.mAdvertiseFlags;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.mServiceUuids;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.mManufacturerSpecificData;
    }

    public byte[] getManufacturerSpecificData(int i) {
        SparseArray<byte[]> sparseArray = this.mManufacturerSpecificData;
        if (sparseArray != null) {
            return sparseArray.get(i);
        }
        return null;
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.mServiceData;
    }

    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.mServiceData.get(parcelUuid);
    }

    public int getTxPowerLevel() {
        return this.mTxPowerLevel;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    private ScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.mServiceUuids = list;
        this.mManufacturerSpecificData = sparseArray;
        this.mServiceData = map;
        this.mDeviceName = str;
        this.mAdvertiseFlags = i;
        this.mTxPowerLevel = i2;
        this.mBytes = bArr;
    }

    public static ScanRecord parseFromBytes(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return null;
        }
        int i = 0;
        ArrayList arrayList = new ArrayList();
        SparseArray sparseArray = new SparseArray();
        ArrayMap arrayMap = new ArrayMap();
        String str = null;
        byte b = -1;
        byte b2 = -2147483648;
        while (true) {
            try {
                if (i < bArr2.length) {
                    int i2 = i + 1;
                    byte b3 = bArr2[i] & 255;
                    if (b3 != 0) {
                        int i3 = b3 - 1;
                        int i4 = i2 + 1;
                        byte b4 = bArr2[i2] & 255;
                        int i5 = 128;
                        if (b4 != 22) {
                            if (b4 != 255) {
                                if (!(b4 == 32 || b4 == 33)) {
                                    switch (b4) {
                                        case 1:
                                            b = bArr2[i4] & 255;
                                            break;
                                        case 2:
                                        case 3:
                                            parseServiceUuid(bArr2, i4, i3, 16, arrayList);
                                            break;
                                        case 4:
                                        case 5:
                                            parseServiceUuid(bArr2, i4, i3, 32, arrayList);
                                            break;
                                        case 6:
                                        case 7:
                                            parseServiceUuid(bArr2, i4, i3, 128, arrayList);
                                            break;
                                        case 8:
                                        case 9:
                                            str = new String(extractBytes(bArr2, i4, i3));
                                            break;
                                        case 10:
                                            b2 = bArr2[i4];
                                            break;
                                    }
                                }
                            } else {
                                byte[] bArr3 = (byte[]) sparseArray.get(BluetoothKey.FEATURES);
                                if (bArr3 != null) {
                                    sparseArray.put(BluetoothKey.FEATURES, ByteUtils.mergeBytes(bArr3, extractBytes(bArr2, i4, i3)));
                                } else {
                                    sparseArray.put(((bArr2[i4 + 1] & 255) << 8) + (bArr2[i4] & 255), extractBytes(bArr2, i4 + 2, i3 - 2));
                                }
                            }
                            i = i3 + i4;
                        }
                        if (b4 == 32) {
                            i5 = 32;
                        } else if (b4 != 33) {
                            i5 = 16;
                        }
                        arrayMap.put(parseUuidFrom(extractBytes(bArr2, i4, i5)), extractBytes(bArr2, i4 + i5, i3 - i5));
                        i = i3 + i4;
                    }
                }
            } catch (Exception unused) {
                return new ScanRecord((List<ParcelUuid>) null, (SparseArray<byte[]>) null, (Map<ParcelUuid, byte[]>) null, -1, Integer.MIN_VALUE, (String) null, bArr);
            }
        }
        return new ScanRecord(arrayList.isEmpty() ? null : arrayList, sparseArray, arrayMap, b, b2, str, bArr);
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.mAdvertiseFlags + ", mServiceUuids=" + this.mServiceUuids + ", mManufacturerSpecificData=" + this.mManufacturerSpecificData + ", mServiceData=" + this.mServiceData + ", mTxPowerLevel=" + this.mTxPowerLevel + ", mDeviceName=" + this.mDeviceName + "]";
    }

    private static int parseServiceUuid(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(parseUuidFrom(extractBytes(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] extractBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr != null) {
            int length = bArr.length;
            if (length != 16 && length != 32 && length != 128) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            } else if (length == 128) {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
            } else {
                if (length == 16) {
                    j = ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8));
                } else {
                    j = ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8)) + ((long) ((bArr[2] & 255) << Ascii.DLE)) + ((long) ((bArr[3] & 255) << Ascii.CAN));
                }
                ParcelUuid parcelUuid = BASE_UUID;
                return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
            }
        } else {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
    }
}
