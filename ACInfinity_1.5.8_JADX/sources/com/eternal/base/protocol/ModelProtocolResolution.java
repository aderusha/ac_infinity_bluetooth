package com.eternal.base.protocol;

import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.utils.ByteUtils;
import com.eternal.framework.utils.KLog;
import com.google.common.base.Ascii;

public class ModelProtocolResolution extends ProtocolResolution {
    public static byte[] getCModelAlarmData(int i) {
        return addHead(new byte[]{2, 32, Ascii.f270EM}, (byte) 1, i);
    }

    public static DeviceMinModel parseCMinModel(byte[] bArr) {
        byte[] check = check(bArr);
        DeviceMinModel deviceMinModel = new DeviceMinModel();
        if (check[0] == 2) {
            deviceMinModel.tmpState = (byte) ByteUtils.getBits(check[2], 4, 2);
            deviceMinModel.humState = (byte) ByteUtils.getBits(check[2], 6, 2);
            deviceMinModel.tmp = ByteUtils.getShort(check, 3);
            deviceMinModel.hum = ByteUtils.getShort(check, 5);
            if (check[7] == 32) {
                deviceMinModel.isDegree = !ByteUtils.getBit(check[9], 7);
                if (check[10] == 25) {
                    System.arraycopy(check, 12, deviceMinModel.values, 0, 7);
                } else {
                    KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
                }
                return deviceMinModel;
            }
            KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
            return deviceMinModel;
        }
        KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
        return deviceMinModel;
    }
}
