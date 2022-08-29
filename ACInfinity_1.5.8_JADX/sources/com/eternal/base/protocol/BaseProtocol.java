package com.eternal.base.protocol;

import com.clj.fastble.data.BleDevice;
import com.eternal.base.database.entity.Device;
import com.eternal.base.global.BluetoothKey;
import com.eternal.base.utils.ByteUtils;
import com.eternal.base.utils.CRCUtil;
import com.eternal.base.utils.ScanRecord;
import com.eternal.data.DataFragment;
import com.eternal.framework.utils.KLog;
import java.nio.charset.StandardCharsets;

public class BaseProtocol {
    private static final byte[] HEAD = {-91, 0};
    private static final int SCAN_RECORD_LENGTH = 27;

    public static void addInit(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 8) & 255);
        bArr[i + 1] = (byte) (i2 & 255);
    }

    protected static byte[] addHead(byte[] bArr, byte b, int i) {
        byte[] bArr2 = new byte[(bArr.length + 12)];
        byte[] bArr3 = HEAD;
        System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
        addInit(bArr2, 2, bArr.length);
        addInit(bArr2, 4, i);
        System.arraycopy(CRCUtil.crc16(bArr2, 0, 6), 0, bArr2, 6, 2);
        bArr2[8] = 0;
        bArr2[9] = b;
        System.arraycopy(bArr, 0, bArr2, 10, bArr.length);
        System.arraycopy(CRCUtil.crc16(bArr2, 8, bArr.length + 2), 0, bArr2, bArr.length + 10, 2);
        return bArr2;
    }

    protected static byte[] check(byte[] bArr) {
        int length = (bArr.length - 10) - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 10, bArr2, 0, length);
        return bArr2;
    }

    public static boolean checkResult(byte[] bArr) {
        byte[] check = check(bArr);
        for (int i = 1; i < check.length; i += 2) {
            if (check[i] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSequence(byte[] bArr, int i) {
        int i2 = ((bArr[4] & 255) << 8) + (bArr[5] & 255);
        KLog.m65e("check sequence:" + i2 + " sequence:" + i);
        return ((bArr[4] & 255) << 8) + (bArr[5] & 255) == i;
    }

    public static int parseScanRecordType(BleDevice bleDevice) {
        return ScanRecord.parseFromBytes(bleDevice.getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES)[12];
    }

    public static byte parseScanRecordVersion(BleDevice bleDevice) {
        return ScanRecord.parseFromBytes(bleDevice.getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES)[11];
    }

    public static int parseScanRecordPortCount(BleDevice bleDevice) {
        byte[] manufacturerSpecificData = ScanRecord.parseFromBytes(bleDevice.getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES);
        if (manufacturerSpecificData == null || manufacturerSpecificData.length <= 23) {
            return 0;
        }
        return manufacturerSpecificData[23];
    }

    public static Device parseScanRecordData(BleDevice bleDevice) {
        byte[] manufacturerSpecificData = ScanRecord.parseFromBytes(bleDevice.getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES);
        Device device = new Device(bleDevice.getMac());
        if (manufacturerSpecificData == null || 27 != manufacturerSpecificData.length) {
            return null;
        }
        device.typeName = new String(manufacturerSpecificData, 6, 5, StandardCharsets.US_ASCII);
        device.type = manufacturerSpecificData[12];
        byte b = device.type;
        if (b == 1 || b == 2) {
            resoleABDevice(device, manufacturerSpecificData);
        } else if (b == 4 || b == 5) {
            resoleCDevice(device, manufacturerSpecificData);
        }
        device.connectTime = System.currentTimeMillis();
        return device;
    }

    private static void resoleCDevice(Device device, byte[] bArr) {
        byte b = bArr[13];
        device.isDegree = !ByteUtils.getBit(b, 1);
        device.tmpState = (byte) ByteUtils.getBits(b, 4, 2);
        device.humState = (byte) ByteUtils.getBits(b, 6, 2);
        device.tmp = ByteUtils.getShort(bArr, 15) * 10;
        device.hum = (bArr[17] & 255) * DataFragment.TAG_FILTER_TEMPERATURE;
        byte b2 = bArr[14];
        device.autoHighTmpSwitch = !ByteUtils.getBit(b2, 4);
        device.autoLowTmpSwitch = !ByteUtils.getBit(b2, 5);
        device.autoHighHumSwitch = !ByteUtils.getBit(b2, 6);
        device.autoLowHumSwitch = !ByteUtils.getBit(b2, 7);
        if (device.isDegree) {
            device.autoHighTmp = bArr[20];
            device.autoLowTmp = bArr[21];
        } else {
            device.autoHighTmp = bArr[18];
            device.autoLowTmp = bArr[19];
        }
        device.autoHighHum = bArr[22];
        device.autoLowHum = bArr[23];
    }

    private static void resoleABDevice(Device device, byte[] bArr) {
        byte b = bArr[13];
        device.isDegree = true ^ ByteUtils.getBit(b, 1);
        device.fanState = (byte) ByteUtils.getBits(b, 2, 2);
        device.tmpState = (byte) ByteUtils.getBits(b, 4, 2);
        device.humState = (byte) ByteUtils.getBits(b, 6, 2);
        device.tmp = ByteUtils.getShort(bArr, 14);
        device.hum = ByteUtils.getShort(bArr, 16);
        device.fan = bArr[18];
    }
}
