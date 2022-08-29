package com.eternal.base.protocol;

import com.clj.fastble.data.BleDevice;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceModelTime;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.database.entity.Device;
import com.eternal.base.database.entity.History;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.database.entity.NotificationMessage;
import com.eternal.base.global.BluetoothKey;
import com.eternal.base.utils.ByteUtils;
import com.eternal.base.utils.CRCUtil;
import com.eternal.base.utils.ScanRecord;
import com.eternal.framework.utils.KLog;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;

public class ProtocolResolution {
    private static final byte[] HEAD = {-91, 0};
    private static final int SCAN_RECORD_LENGTH = 27;
    private static final int SCAN_RECORD_LENGTH_G = 17;

    public static byte formatBitForAdvance(boolean z, boolean z2, int i) {
        return (byte) (((byte) (((((byte) ((z | false ? 1 : 0) << true)) >> 6) | z2 ? 1 : 0) << true)) | i);
    }

    public static byte[] getTempUnitData(int i) {
        return addHead(new byte[]{32}, (byte) 1, i);
    }

    public static byte[] getTFPData(byte b, int i) {
        return addHead(new byte[]{2, 3, 32}, b, i);
    }

    public static byte[] getSettingData(int i, byte b) {
        return addHead(new byte[]{32, 33, 34, 36, 17, 18, 2}, (byte) 1, i);
    }

    public static byte[] getBSettingData(int i) {
        return addHead(new byte[]{32, 33, 35, 36, 17, 18, 2}, (byte) 1, i);
    }

    public static byte[] getCSettingData(int i) {
        return addHead(new byte[]{32, 33, 36}, (byte) 1, i);
    }

    public static byte[] getModelData(int i) {
        return addHead(new byte[]{Ascii.DLE, 2, 3, 32}, (byte) 1, i);
    }

    public static byte[] getOffModelData(int i) {
        return addHead(new byte[]{17, 18}, (byte) 1, i);
    }

    public static byte[] getOnModelData(int i) {
        return addHead(new byte[]{17, 18}, (byte) 1, i);
    }

    public static byte[] getAutoModelData(int i, boolean z) {
        byte[] bArr = new byte[4];
        bArr[0] = 17;
        bArr[1] = 18;
        bArr[2] = 19;
        bArr[3] = (byte) (z ? 35 : 34);
        return addHead(bArr, (byte) 1, i);
    }

    public static byte[] getTimeOnModelData(int i) {
        return addHead(new byte[]{17, 18, 20, Ascii.CAN}, (byte) 1, i);
    }

    public static byte[] getTimeOffModelData(int i) {
        return addHead(new byte[]{17, 18, 21, Ascii.CAN}, (byte) 1, i);
    }

    public static byte[] getCycleModelData(int i) {
        return addHead(new byte[]{17, 18, 22, Ascii.CAN}, (byte) 1, i);
    }

    public static byte[] getSchedModelData(int i) {
        return addHead(new byte[]{17, 18, Ascii.ETB, Ascii.CAN}, (byte) 1, i);
    }

    public static byte[] getAllModelData(int i) {
        return addHead(new byte[]{Ascii.DLE, 17, 18, 19, 20, 21, 22, Ascii.ETB}, (byte) 1, i);
    }

    public static byte[] getNotificationData(int i) {
        return addHead(new byte[]{49, 50, 51}, (byte) 1, i);
    }

    public static byte[] getLogStart(long j, long j2, int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(j2) - TimeUnit.MILLISECONDS.toSeconds(j));
        return addHead(new byte[]{(byte) (instance.get(1) - 2000), (byte) (instance.get(2) + 1), (byte) instance.get(5), (byte) instance.get(11), (byte) instance.get(12), (byte) instance.get(13), (byte) ((seconds >> 24) & 255), (byte) ((seconds >> 16) & 255), (byte) ((seconds >> 8) & 255), (byte) (seconds & 255), (byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)}, (byte) 17, i2);
    }

    public static byte[] getHistoryStart(long j, long j2, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(j2) - TimeUnit.MILLISECONDS.toSeconds(j));
        return addHead(new byte[]{(byte) (instance.get(1) - 2000), (byte) (instance.get(2) + 1), (byte) instance.get(5), (byte) instance.get(11), (byte) instance.get(12), (byte) instance.get(13), (byte) ((seconds >> 24) & 255), (byte) ((seconds >> 16) & 255), (byte) ((seconds >> 8) & 255), (byte) (seconds & 255)}, (byte) 20, i);
    }

    public static byte[] getLog(int i, int i2, int i3) {
        return addHead(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)}, (byte) 18, i3);
    }

    public static byte[] getHistory(int i, int i2, int i3) {
        return addHead(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)}, (byte) 21, i3);
    }

    public static byte[] getLogEnd(int i) {
        return addHead(new byte[0], (byte) 19, i);
    }

    public static byte[] getHistoryEnd(int i) {
        return addHead(new byte[0], (byte) 22, i);
    }

    public static byte[] setSettingData(DeviceSetting deviceSetting, int i) {
        byte[] bArr = new byte[22];
        bArr[0] = 32;
        bArr[1] = 1;
        bArr[2] = deviceSetting.isDegree ? (byte) 1 : 0;
        bArr[3] = 33;
        bArr[4] = 1;
        bArr[5] = deviceSetting.brightness;
        bArr[6] = 34;
        bArr[7] = 3;
        bArr[8] = deviceSetting.transitionTemperature;
        bArr[9] = deviceSetting.transitionTemperatureC;
        bArr[10] = deviceSetting.transitionHumidity;
        bArr[11] = 36;
        bArr[12] = 3;
        bArr[13] = 0;
        bArr[14] = 0;
        bArr[15] = deviceSetting.calibrationHumidity;
        if (deviceSetting.isDegree) {
            bArr[14] = deviceSetting.calibrationTemperature;
        } else {
            bArr[13] = deviceSetting.calibrationTemperature;
        }
        bArr[16] = 17;
        bArr[17] = 1;
        bArr[18] = deviceSetting.typeOff;
        bArr[19] = 18;
        bArr[20] = 1;
        bArr[21] = deviceSetting.typeOn;
        return addHead(bArr, (byte) 3, i);
    }

    public static byte[] getNotificationMessage(int i) {
        return addHead(new byte[]{32, 48}, (byte) 1, i);
    }

    public static byte[] setBSettingData(DeviceSetting deviceSetting, int i) {
        byte[] bArr = {32, 1, deviceSetting.isDegree ? (byte) 1 : 0, 33, 1, deviceSetting.brightness, 35, 3, deviceSetting.transitionTemperature, deviceSetting.transitionTemperatureC, deviceSetting.transitionHumidity, 36, 3, 0, 0, deviceSetting.calibrationHumidity};
        if (deviceSetting.isDegree) {
            bArr[13] = (byte) Math.round(ProtocolTransformer.getFah((float) deviceSetting.calibrationTemperature));
            bArr[14] = deviceSetting.calibrationTemperature;
        } else {
            bArr[13] = deviceSetting.calibrationTemperature;
            bArr[14] = (byte) Math.round(ProtocolTransformer.getDegree((float) deviceSetting.calibrationTemperature));
        }
        return addHead(bArr, (byte) 3, i);
    }

    public static byte[] setCSettingData(DeviceSetting deviceSetting, int i) {
        byte[] bArr = new byte[11];
        bArr[0] = 32;
        bArr[1] = 1;
        bArr[2] = deviceSetting.isDegree ? (byte) 1 : 0;
        bArr[3] = 33;
        bArr[4] = 1;
        bArr[5] = deviceSetting.brightness;
        bArr[6] = 36;
        bArr[7] = 3;
        bArr[10] = deviceSetting.calibrationHumidity;
        if (deviceSetting.isDegree) {
            bArr[8] = (byte) Math.round(ProtocolTransformer.getFah((float) deviceSetting.calibrationTemperature));
            bArr[9] = deviceSetting.calibrationTemperature;
        } else {
            bArr[8] = deviceSetting.calibrationTemperature;
            bArr[9] = (byte) Math.round(ProtocolTransformer.getDegree((float) deviceSetting.calibrationTemperature));
        }
        return addHead(bArr, (byte) 3, i);
    }

    public static byte[] setModelData(DeviceModel deviceModel, int i) {
        byte[] bArr;
        switch (deviceModel.workType) {
            case 1:
                bArr = new byte[]{17, 1, deviceModel.typeOff};
                break;
            case 2:
                bArr = new byte[]{18, 1, deviceModel.typeOn};
                break;
            case 3:
                if (!deviceModel.isDegree) {
                    bArr = new byte[]{19, 7, ProtocolTransformer.getTmpHumSwitch(deviceModel), deviceModel.autoHighTmp, (byte) Math.round(ProtocolTransformer.getDegree((float) UnsignedBytes.toInt(deviceModel.autoHighTmp))), deviceModel.autoLowTmp, (byte) Math.round(ProtocolTransformer.getDegree((float) UnsignedBytes.toInt(deviceModel.autoLowTmp))), deviceModel.autoHighHum, deviceModel.autoLowHum};
                    break;
                } else {
                    bArr = new byte[]{19, 7, ProtocolTransformer.getTmpHumSwitch(deviceModel), (byte) Math.round(ProtocolTransformer.getFah((float) deviceModel.autoHighTmp)), deviceModel.autoHighTmp, (byte) Math.round(ProtocolTransformer.getFah((float) deviceModel.autoLowTmp)), deviceModel.autoLowTmp, deviceModel.autoHighHum, deviceModel.autoLowHum};
                    break;
                }
            case 4:
                bArr = new byte[6];
                bArr[0] = 20;
                bArr[1] = 4;
                setSecond(bArr, 2, deviceModel.timeOn * Typography.less);
                break;
            case 5:
                bArr = new byte[6];
                bArr[0] = 21;
                bArr[1] = 4;
                setSecond(bArr, 2, deviceModel.timeOff * Typography.less);
                break;
            case 6:
                bArr = new byte[10];
                bArr[0] = 22;
                bArr[1] = 8;
                setSecond(bArr, 2, deviceModel.cycleOn * Typography.less);
                setSecond(bArr, 6, deviceModel.cycleOff * Typography.less);
                break;
            default:
                bArr = new byte[6];
                bArr[0] = Ascii.ETB;
                bArr[1] = 4;
                char c = deviceModel.schedOn;
                char c2 = 1439;
                if (c == 1440) {
                    c = 1439;
                }
                bArr[2] = (byte) (c / Typography.less);
                bArr[3] = (byte) (c % Typography.less);
                char c3 = deviceModel.schedOff;
                if (c3 != 1440) {
                    c2 = c3;
                }
                bArr[4] = (byte) (c2 / Typography.less);
                bArr[5] = (byte) (c2 % Typography.less);
                break;
        }
        return addHead(bArr, (byte) 3, i);
    }

    public static byte[] setModelData(byte b, int i) {
        return addHead(new byte[]{Ascii.DLE, 1, b}, (byte) 3, i);
    }

    private static void setSecond(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >> 8) & 255);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public static byte[] setNotificationData(Notification notification, boolean z, boolean z2, int i) {
        byte[] bArr;
        Notification notification2 = notification;
        boolean z3 = z;
        boolean z4 = z2;
        if (notification2.type == 1) {
            bArr = new byte[24];
            bArr[0] = 49;
            bArr[1] = 22;
            bArr[2] = formatBitForAdvance(z3, z4, notification2.f144id);
            bArr[3] = (byte) notification2.f144id;
            bArr[4] = (byte) (notification2.start / Typography.less);
            bArr[5] = (byte) (notification2.start % Typography.less);
            bArr[6] = (byte) (notification2.end / Typography.less);
            bArr[7] = (byte) (notification2.end % Typography.less);
            bArr[8] = notification2.model;
            int i2 = notification2.cycleOn * Typography.less;
            bArr[9] = (byte) ((notification2.levelOn << 4) | notification2.levelOff);
            bArr[10] = (byte) (i2 >> 16);
            bArr[11] = (byte) (i2 >> 8);
            bArr[12] = (byte) i2;
            int i3 = notification2.cycleOff * Typography.less;
            bArr[13] = (byte) (i3 >> 24);
            bArr[14] = (byte) (i3 >> 16);
            bArr[15] = (byte) (i3 >> 8);
            bArr[16] = (byte) i3;
            bArr[17] = notification2.tmpHum;
            if (notification2.model == 5) {
                bArr[18] = notification2.hVpd;
                bArr[19] = notification2.lVpd;
            } else {
                bArr[18] = notification2.hTmpF;
                bArr[19] = notification2.hTmpC;
                bArr[20] = notification2.lTmpF;
                bArr[21] = notification2.lTmpC;
                bArr[22] = notification2.hHum;
                bArr[23] = notification2.lHum;
            }
        } else {
            bArr = new byte[13];
            if (notification2.type == 2) {
                bArr[0] = 50;
            } else {
                bArr[0] = 51;
            }
            bArr[1] = 11;
            bArr[2] = formatBitForAdvance(z3, z4, notification2.f144id);
            bArr[3] = notification2.model;
            bArr[4] = notification2.tmpHum;
            bArr[5] = notification2.hTmpF;
            bArr[6] = notification2.hTmpC;
            bArr[7] = notification2.lTmpF;
            bArr[8] = notification2.lTmpC;
            bArr[9] = notification2.hHum;
            bArr[10] = notification2.lHum;
            bArr[11] = notification2.hVpd;
            bArr[12] = notification2.lVpd;
        }
        return addHead(bArr, (byte) 3, i);
    }

    static byte[] addHead(byte[] bArr, byte b, int i) {
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

    private static List<byte[]> getListIntArray(byte[] bArr, int i) {
        int i2;
        int length = bArr.length / i;
        int length2 = bArr.length % i;
        if (length2 != 0) {
            length++;
        }
        ArrayList arrayList = new ArrayList(length);
        int i3 = 0;
        while (true) {
            i2 = length - 1;
            if (i3 >= i2) {
                break;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, i3 * i, bArr2, 0, i);
            arrayList.add(bArr2);
            i3++;
        }
        if (length2 != 0) {
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr, i2 * i, bArr3, 0, length2);
            arrayList.add(bArr3);
        }
        return arrayList;
    }

    private static void addInit(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 8) & 255);
        bArr[i + 1] = (byte) (i2 & 255);
    }

    public static byte[] setTimeData(int i) {
        Calendar instance = Calendar.getInstance();
        return addHead(new byte[]{1, 7, (byte) (instance.get(1) % 100), (byte) (instance.get(2) + 1), (byte) instance.get(5), (byte) instance.get(7), (byte) instance.get(11), (byte) instance.get(12), (byte) instance.get(13)}, (byte) 3, i);
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

    public static DeviceModel parseInfo(byte[] bArr) {
        DeviceModel deviceModel = new DeviceModel();
        if (bArr.length < bArr[5] + 6) {
            return deviceModel;
        }
        byte b = bArr[6];
        deviceModel.isDegree = !ByteUtils.getBit(b, 0);
        deviceModel.tmpState = (byte) ByteUtils.getBits(b, 1, 2);
        deviceModel.humState = (byte) ByteUtils.getBits(b, 3, 2);
        deviceModel.vpdState = (byte) ByteUtils.getBits(b, 5, 2);
        deviceModel.choosePort = (byte) ByteUtils.getBits(bArr[7], 4, 4);
        deviceModel.tmp = ByteUtils.getShort(bArr, 8);
        deviceModel.hum = ByteUtils.getShort(bArr, 10);
        deviceModel.vpd = ByteUtils.getShort(bArr, 12);
        deviceModel.fanType = ByteUtils.getShort(bArr, 14);
        deviceModel.fanState = (byte) ByteUtils.getBits(bArr[16], 0, 2);
        deviceModel.fan = ByteUtils.getBits(bArr[17], 0, 4);
        deviceModel.workType = (byte) ByteUtils.getBits(bArr[17], 4, 4);
        deviceModel.portList = new ArrayList();
        int i = 18;
        int i2 = 1;
        while (true) {
            int i3 = i + 3;
            if (i3 >= bArr.length) {
                return deviceModel;
            }
            PortInfo portInfo = new PortInfo();
            portInfo.f138id = (byte) i2;
            portInfo.isPlug = bArr[i] != -1;
            portInfo.fanType = ByteUtils.getShort(bArr, i);
            portInfo.fanState = (byte) ByteUtils.getBits(bArr[i + 2], 0, 2);
            portInfo.fan = ByteUtils.getBits(bArr[i3], 0, 4);
            portInfo.workType = (byte) ByteUtils.getBits(bArr[i3], 4, 4);
            int i4 = i2 + 3;
            if (i4 <= 7) {
                portInfo.isAdv = !ByteUtils.getBit(bArr[16], i4);
            }
            deviceModel.portList.add(portInfo);
            i += 4;
            i2++;
        }
    }

    public static Device parseScanRecordData(BleDevice bleDevice) {
        byte[] manufacturerSpecificData = ScanRecord.parseFromBytes(bleDevice.getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES);
        Device device = new Device(bleDevice.getMac());
        if (manufacturerSpecificData == null) {
            return null;
        }
        if (27 != manufacturerSpecificData.length && 17 != manufacturerSpecificData.length) {
            return null;
        }
        device.typeName = new String(manufacturerSpecificData, 6, 5, StandardCharsets.US_ASCII);
        device.version = manufacturerSpecificData[11];
        device.type = manufacturerSpecificData[12];
        byte b = manufacturerSpecificData[13];
        if (ByteUtils.getBit(b, 0)) {
            KLog.m68i("当前设备可以被添加， 设备类型码" + device.type);
        } else {
            KLog.m68i("当前设备不可以被添加");
        }
        device.isDegree = true ^ ByteUtils.getBit(b, 1);
        device.fanState = (byte) ByteUtils.getBits(b, 2, 2);
        device.tmpState = (byte) ByteUtils.getBits(b, 4, 2);
        device.humState = (byte) ByteUtils.getBits(b, 6, 2);
        device.tmp = ByteUtils.getShort(manufacturerSpecificData, 14);
        device.hum = ByteUtils.getShort(manufacturerSpecificData, 16);
        device.fan = manufacturerSpecificData[18];
        if (device.version >= 3 && (device.type == 11 || device.type == 7 || device.type == 9 || device.type == 12)) {
            device.choosePort = manufacturerSpecificData[19];
            device.vpdState = (byte) ByteUtils.getBits(manufacturerSpecificData[20], 0, 2);
            device.vpd = ByteUtils.getShort(manufacturerSpecificData, 21);
        }
        device.connectTime = System.currentTimeMillis();
        return device;
    }

    public static DeviceTFP parseTFP(byte[] bArr) {
        DeviceTFP deviceTFP = new DeviceTFP();
        byte[] check = check(bArr);
        if (check[0] == 2) {
            deviceTFP.tmpState = (byte) ByteUtils.getBits(check[2], 4, 2);
            deviceTFP.humState = (byte) ByteUtils.getBits(check[2], 6, 2);
            deviceTFP.tmp = ByteUtils.getShort(check, 3);
            deviceTFP.hum = ByteUtils.getShort(check, 5);
            if (check[1] == 7) {
                deviceTFP.vpdState = (byte) ByteUtils.getBits(check[2], 2, 2);
                deviceTFP.vpd = ByteUtils.getShort(check, 7);
                check = ByteUtils.subByte(check, 2, check.length - 2);
            }
            if (check[7] == 3) {
                if (check[8] == 5) {
                    deviceTFP.fanType = check[9];
                } else {
                    deviceTFP.fanType = ByteUtils.getShort(check, 9);
                    check = ByteUtils.subByte(check, 1, check.length - 1);
                }
                deviceTFP.fan = ByteUtils.getBits(check[10], 4, 4);
                deviceTFP.fanState = (byte) ByteUtils.getBits(check[10], 0, 2);
                if (check.length == 12) {
                    deviceTFP.isDegree = !ByteUtils.getBit(check[11], 7);
                } else if (check[14] == 32) {
                    deviceTFP.isDegree = !ByteUtils.getBit(check[16], 7);
                }
                return deviceTFP;
            }
        }
        return null;
    }

    public static List<NotificationMessage> parseNotificationMessage(String str, byte b, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        byte[] check = check(bArr);
        if (check[0] == 32) {
            boolean z = !ByteUtils.getBit(check[2], 7);
            for (int i = 3; i < check.length; i += 18) {
                if (check[i] == 48 && check[i + 1] == 16) {
                    NotificationMessage notificationMessage = new NotificationMessage();
                    notificationMessage.isDegree = z;
                    int i2 = i + 6;
                    notificationMessage.notifyId = (byte) ByteUtils.getBits(check[i2], 2, 6);
                    notificationMessage.type = (byte) ByteUtils.getBits(check[i2], 0, 2);
                    notificationMessage.notifyType = check[i + 7];
                    notificationMessage.mac = str;
                    notificationMessage.port = b;
                    System.arraycopy(check, i + 8, notificationMessage.values, 0, 10);
                    arrayList.add(notificationMessage);
                }
            }
            if (arrayList.size() == 0) {
                NotificationMessage notificationMessage2 = new NotificationMessage();
                notificationMessage2.isDegree = z;
                notificationMessage2.mac = str;
                notificationMessage2.port = b;
                notificationMessage2.type = -1;
                arrayList.add(notificationMessage2);
            }
        }
        return arrayList;
    }

    public static DeviceSetting parseCSetting(byte[] bArr) {
        DeviceSetting deviceSetting = new DeviceSetting();
        byte[] check = check(bArr);
        if (check[0] == 32) {
            deviceSetting.isDegree = !ByteUtils.getBit(check[2], 7);
            if (check[3] == 33) {
                deviceSetting.brightness = check[5];
                if (check[6] == 36) {
                    if (deviceSetting.isDegree) {
                        deviceSetting.calibrationTemperature = check[9];
                    } else {
                        deviceSetting.calibrationTemperature = check[8];
                    }
                    deviceSetting.calibrationHumidity = check[10];
                    return deviceSetting;
                }
            }
        }
        return null;
    }

    public static DeviceSetting parseSetting(byte[] bArr) {
        DeviceSetting deviceSetting = new DeviceSetting();
        byte[] check = check(bArr);
        if (check[0] == 32) {
            deviceSetting.isDegree = !ByteUtils.getBit(check[2], 7);
            if (check[3] == 33) {
                deviceSetting.brightness = check[5];
                if (check[6] != 34 && check[6] != 35) {
                    return null;
                }
                deviceSetting.transitionTemperature = check[8];
                deviceSetting.transitionTemperatureC = check[9];
                deviceSetting.transitionHumidity = check[10];
                if (check[7] == 4) {
                    deviceSetting.transitionVpd = check[11];
                    check = ByteUtils.subByte(check, 1, check.length - 1);
                }
                if (check[11] == 36) {
                    if (deviceSetting.isDegree) {
                        deviceSetting.calibrationTemperature = check[14];
                    } else {
                        deviceSetting.calibrationTemperature = check[13];
                    }
                    deviceSetting.calibrationHumidity = check[15];
                    if (check[16] == 17) {
                        deviceSetting.typeOff = check[18];
                        if (check[19] == 18) {
                            deviceSetting.typeOn = check[21];
                            if (check[22] == 2) {
                                deviceSetting.tmp = ByteUtils.getShort(check, 25);
                                deviceSetting.hum = ByteUtils.getShort(check, 27);
                                return deviceSetting;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static boolean parseMinModel(DeviceMinModel deviceMinModel, byte[] bArr) {
        byte[] check = check(bArr);
        if (check[0] == 16) {
            deviceMinModel.model = check[2];
            if (check[3] == 2) {
                deviceMinModel.tmpState = (byte) ByteUtils.getBits(check[5], 4, 2);
                deviceMinModel.humState = (byte) ByteUtils.getBits(check[5], 6, 2);
                deviceMinModel.tmp = ByteUtils.getShort(check, 6);
                deviceMinModel.hum = ByteUtils.getShort(check, 8);
                if (check[10] == 3) {
                    if (check[11] == 5) {
                        deviceMinModel.fanType = check[12];
                    } else {
                        deviceMinModel.fanType = ByteUtils.getShort(check, 12);
                        check = ByteUtils.subByte(check, 1, check.length - 1);
                    }
                    deviceMinModel.fan = ByteUtils.getBits(check[13], 4, 4);
                    deviceMinModel.fanState = (byte) ByteUtils.getBits(check[13], 0, 2);
                    if (check[17] == 32) {
                        deviceMinModel.isDegree = !ByteUtils.getBit(check[19], 7);
                        return true;
                    }
                    KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
                    return false;
                }
                KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
                return false;
            }
            KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
            return false;
        }
        KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
        return false;
    }

    public static boolean initDeviceMinModel(DeviceMinModel deviceMinModel, byte[] bArr, byte b) {
        byte[] check = check(bArr);
        deviceMinModel.typeOff = check[2];
        deviceMinModel.typeOn = check[5];
        byte[] subByte = ByteUtils.subByte(check, 6, check.length - 6);
        boolean z = false;
        switch (deviceMinModel.model) {
            case 1:
                deviceMinModel.values[0] = deviceMinModel.typeOff;
                return true;
            case 2:
                deviceMinModel.values[0] = deviceMinModel.typeOn;
                return true;
            case 3:
                System.arraycopy(subByte, 2, deviceMinModel.values, 0, 7);
                if (subByte[9] == 34 || subByte[9] == 35) {
                    deviceMinModel.transitionTemperature = subByte[11];
                    deviceMinModel.transitionTemperatureC = subByte[12];
                    deviceMinModel.transitionHumidity = subByte[13];
                }
                return true;
            case 4:
            case 5:
                System.arraycopy(subByte, 2, deviceMinModel.values, 0, 4);
                if (subByte[6] == 24) {
                    DeviceModelTime deviceModelTime = new DeviceModelTime();
                    deviceModelTime.time = (long) ((subByte[9] & 255) * Ascii.DLE);
                    deviceModelTime.time += (long) ((subByte[10] & 255) * 60);
                    deviceModelTime.time += (long) (subByte[11] & 255);
                    if (subByte[12] == 0) {
                        z = true;
                    }
                    deviceModelTime.isOff = z;
                    deviceMinModel.time = deviceModelTime;
                    return true;
                }
                break;
            case 6:
                System.arraycopy(subByte, 2, deviceMinModel.values, 0, 8);
                if (subByte[10] == 24) {
                    DeviceModelTime deviceModelTime2 = new DeviceModelTime();
                    deviceModelTime2.time = (long) ((subByte[13] & 255) * Ascii.DLE);
                    deviceModelTime2.time += (long) ((subByte[14] & 255) * 60);
                    deviceModelTime2.time += (long) (subByte[15] & 255);
                    if (subByte[16] == 0) {
                        z = true;
                    }
                    deviceModelTime2.isOff = z;
                    deviceMinModel.time = deviceModelTime2;
                    return true;
                }
                break;
            default:
                System.arraycopy(subByte, 2, deviceMinModel.values, 0, 4);
                if (subByte[6] == 24 && subByte[8] == 1) {
                    DeviceModelTime deviceModelTime3 = new DeviceModelTime();
                    deviceModelTime3.time = (long) ((subByte[9] & 255) * Ascii.DLE);
                    deviceModelTime3.time += (long) ((subByte[10] & 255) * 60);
                    deviceModelTime3.time += (long) (subByte[11] & 255);
                    if (deviceModelTime3.time == 0) {
                        if (b < 2) {
                            z = true;
                        }
                        deviceModelTime3.isOff = z;
                    } else {
                        if (subByte[12] == 0) {
                            z = true;
                        }
                        deviceModelTime3.isOff = z;
                    }
                    deviceMinModel.time = deviceModelTime3;
                }
                return true;
        }
        KLog.m65e("err packet:" + ByteUtils.bytes2HexString(subByte));
        return false;
    }

    public static DeviceModel parseDeviceModel(byte[] bArr) {
        byte[] check = check(bArr);
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.workType = check[2];
        deviceModel.typeOff = check[5];
        deviceModel.typeOn = check[8];
        deviceModel.autoHighTmpSwitch = !ByteUtils.getBit(check[11], 4);
        deviceModel.autoLowTmpSwitch = !ByteUtils.getBit(check[11], 5);
        deviceModel.autoHighHumSwitch = !ByteUtils.getBit(check[11], 6);
        deviceModel.autoLowHumSwitch = !ByteUtils.getBit(check[11], 7);
        deviceModel.autoHighTmp = check[13];
        deviceModel.autoLowTmp = check[15];
        deviceModel.autoHighHum = check[16];
        deviceModel.autoLowHum = check[17];
        deviceModel.timeOn = (char) (((((check[21] & 255) << Ascii.DLE) | ((check[22] & 255) << 8)) | (check[23] & 255)) / 60);
        deviceModel.timeOff = (char) (((((check[27] & 255) << Ascii.DLE) | ((check[28] & 255) << 8)) | (check[29] & 255)) / 60);
        deviceModel.cycleOn = (char) (((((check[33] & 255) << Ascii.DLE) | ((check[34] & 255) << 8)) | (check[35] & 255)) / 60);
        deviceModel.cycleOff = (char) (((((check[37] & 255) << Ascii.DLE) | ((check[38] & 255) << 8)) | (check[39] & 255)) / 60);
        if (check.length <= 42) {
            return deviceModel;
        }
        deviceModel.schedOn = (char) Math.round((float) ((check[42] * 60) + check[43]));
        deviceModel.schedOff = (char) Math.round((float) ((check[44] * 60) + check[45]));
        return deviceModel;
    }

    public static List<Notification> parseNotifications(byte[] bArr, boolean z) {
        int i;
        byte[] check = check(bArr);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < check.length && check.length >= (i = i2 + 4)) {
            if (check[i2 + 1] == 0) {
                i2 += 2;
            } else {
                Notification notification = new Notification();
                int i3 = i2 + 2;
                notification.f144id = ByteUtils.getBits(check[i3], 2, 6);
                notification.groupIndex = (byte) notification.f144id;
                notification.open = !ByteUtils.getBit(check[i3], 1);
                if (check[i2] == 49) {
                    int i4 = i2 + 24;
                    if (check.length < i4) {
                        break;
                    }
                    notification.type = 1;
                    notification.start = (char) ((check[i] * 60) + check[i2 + 5]);
                    notification.end = (char) ((check[i2 + 6] * 60) + check[i2 + 7]);
                    notification.model = (byte) ByteUtils.getBits(check[i2 + 8], 4, 4);
                    int i5 = i2 + 9;
                    notification.levelOff = ByteUtils.getBits(check[i5], 4, 4);
                    notification.levelOn = ByteUtils.getBits(check[i5], 0, 4);
                    notification.cycleOn = (char) (((((check[i2 + 10] & 255) << Ascii.DLE) + ((check[i2 + 11] & 255) << 8)) + (check[i2 + 12] & 255)) / 60);
                    notification.cycleOff = (char) (((((check[i2 + 14] & 255) << Ascii.DLE) + ((check[i2 + 15] & 255) << 8)) + (check[i2 + 16] & 255)) / 60);
                    notification.tmpHum = check[i2 + 17];
                    if (notification.model == 5) {
                        notification.hVpd = check[i2 + 18];
                        notification.lVpd = check[i2 + 19];
                    } else {
                        notification.hTmpF = check[i2 + 18];
                        notification.hTmpC = check[i2 + 19];
                        notification.lTmpF = check[i2 + 20];
                        notification.lTmpC = check[i2 + 21];
                        notification.hHum = check[i2 + 22];
                        notification.lHum = check[i2 + 23];
                    }
                    i2 = i4;
                } else {
                    int i6 = i2 + 13;
                    if (check.length < i6) {
                        break;
                    }
                    if (check[i2] == 50) {
                        notification.type = 2;
                    } else {
                        notification.type = 3;
                    }
                    notification.model = check[i2 + 3];
                    notification.tmpHum = check[i];
                    notification.hTmpF = check[i2 + 5];
                    notification.hTmpC = check[i2 + 6];
                    notification.lTmpF = check[i2 + 7];
                    notification.lTmpC = check[i2 + 8];
                    notification.hHum = check[i2 + 9];
                    notification.lHum = check[i2 + 10];
                    notification.hVpd = check[i2 + 11];
                    notification.lVpd = check[i2 + 12];
                    i2 = i6;
                }
                arrayList.add(notification);
            }
        }
        return arrayList;
    }

    public static int parseLogSize(byte[] bArr) {
        byte[] check = check(bArr);
        return ((check[0] & 255) << Ascii.CAN) + 0 + ((check[1] & 255) << Ascii.DLE) + ((check[2] & 255) << 8) + (check[3] & 255);
    }

    public static Log parseLog(byte[] bArr, int i, long j) {
        if (i + 28 > bArr.length) {
            return null;
        }
        Log log = new Log();
        boolean z = true;
        if (bArr[i] == 0) {
            Calendar instance = Calendar.getInstance();
            instance.set(bArr[i + 1] + 2000, bArr[i + 2] - 1, bArr[i + 3], bArr[i + 4], bArr[i + 5], bArr[i + 6]);
            log.time = (instance.getTimeInMillis() / 1000) * 1000;
        } else {
            log.time = (long) (((bArr[i + 1] & 255) << Ascii.CAN) | ((bArr[i + 2] & 255) << Ascii.DLE) | ((bArr[i + 3] & 255) << 8) | (bArr[i + 4] & 255));
            log.time = TimeUnit.MILLISECONDS.toSeconds(j) - log.time;
            log.time = (TimeUnit.SECONDS.toMillis(log.time) * 1000) / 1000;
        }
        log.f142id = ((bArr[i + 7] & 255) << Ascii.CAN) | ((bArr[i + 8] & 255) << Ascii.DLE) | ((bArr[i + 9] & 255) << 8) | (bArr[i + 10] & 255);
        log.logType = bArr[i + 11];
        log.notifyId = bArr[i + 12];
        log.start = (char) ((bArr[i + 13] * 60) + bArr[i + 14]);
        log.end = (char) ((bArr[i + 15] * 60) + bArr[i + 16]);
        log.model = bArr[i + 17];
        switch (log.model) {
            case 1:
            case 2:
                log.fan = bArr[i + 18];
                break;
            case 3:
            case 8:
                log.tmpHum = (short) bArr[i + 18];
                log.hTmpF = bArr[i + 19];
                log.hTmpC = bArr[i + 20];
                log.lTmpF = bArr[i + 21];
                log.lTmpC = bArr[i + 22];
                log.hHum = bArr[i + 23];
                log.lHum = bArr[i + 24];
                log.fan = bArr[i + 25];
                break;
            case 4:
                log.fan = bArr[i + 22];
                log.f143on = (char) (((((bArr[i + 19] & 255) << Ascii.DLE) | ((bArr[i + 20] & 255) << 8)) | (bArr[i + 21] & 255)) / 60);
                break;
            case 5:
                log.fan = bArr[i + 22];
                log.off = (char) ((((bArr[i + 19] & 255) << Ascii.DLE) | (((bArr[i + 20] & 255) << 8) + (bArr[i + 21] & 255))) / 60);
                break;
            case 6:
                log.f143on = (char) (((((bArr[i + 19] & 255) << Ascii.DLE) + ((bArr[i + 20] & 255) << 8)) + (bArr[i + 21] & 255)) / 60);
                log.off = (char) (((((bArr[i + 23] & 255) << Ascii.DLE) + ((bArr[i + 24] & 255) << 8)) + (bArr[i + 25] & 255)) / 60);
                if (bArr[i + 26] == 0) {
                    z = false;
                }
                log.isStart = z;
                log.fan = bArr[i + 27];
                break;
            case 7:
                log.f143on = (char) ((bArr[i + 18] * 60) + bArr[i + 19]);
                log.off = (char) ((bArr[i + 20] * 60) + bArr[i + 21]);
                if (bArr[i + 22] != 0) {
                    z = false;
                }
                log.isStart = z;
                log.fan = bArr[i + 23];
                break;
        }
        return log;
    }

    public static Log parseCLog(byte[] bArr, int i, long j) {
        if (i + 19 > bArr.length) {
            return null;
        }
        Log log = new Log();
        if (bArr[i] == 0) {
            Calendar instance = Calendar.getInstance();
            instance.set(bArr[i + 1] + 2000, bArr[i + 2] - 1, bArr[i + 3], bArr[i + 4], bArr[i + 5], bArr[i + 6]);
            log.time = instance.getTimeInMillis();
        } else {
            log.time = (long) (((bArr[i + 1] & 255) << Ascii.CAN) | ((bArr[i + 2] & 255) << Ascii.DLE) | ((bArr[i + 3] & 255) << 8) | (bArr[i + 4] & 255));
            log.time = TimeUnit.MILLISECONDS.toSeconds(j) - log.time;
            log.time = TimeUnit.SECONDS.toMillis(log.time);
        }
        log.f142id = ((bArr[i + 7] & 255) << Ascii.CAN) | ((bArr[i + 8] & 255) << Ascii.DLE) | ((bArr[i + 9] & 255) << 8) | (bArr[i + 10] & 255);
        log.model = bArr[i + 11];
        log.tmpHum = (short) bArr[i + 12];
        log.hTmpF = bArr[i + 13];
        log.hTmpC = bArr[i + 14];
        log.lTmpF = bArr[i + 15];
        log.lTmpC = bArr[i + 16];
        log.hHum = bArr[i + 17];
        log.lHum = bArr[i + 18];
        return log;
    }

    public static History parseHistory(byte[] bArr, int i, long j, boolean z) {
        History history = new History();
        history.time = j;
        history.tmp = ByteUtils.getShort(bArr, i);
        history.hum = (char) (((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255));
        if (!z) {
            history.off = bArr[i + 4] == 0;
        }
        return history;
    }

    public static History parseDHistory(byte[] bArr, int i, long j, byte b, byte b2) {
        History history = new History();
        history.time = j;
        history.tmp = ByteUtils.getShort(bArr, i);
        if (b == 2) {
            history.hum = (char) (((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255));
            if (b2 >= 3) {
                history.vpd = ByteUtils.getShort(bArr, i + 4);
            }
        } else {
            boolean z = true;
            if (b == 3) {
                int i2 = i + 2;
                history.fan = (byte) ByteUtils.getBits(bArr[i2], 0, 4);
                if (ByteUtils.getBits(bArr[i2], 4, 4) != 0) {
                    z = false;
                }
                history.off = z;
            } else {
                history.hum = (char) (((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255));
                if (b2 >= 3) {
                    history.vpd = ByteUtils.getShort(bArr, i + 4);
                    int i3 = i + 6;
                    history.fan = (byte) ByteUtils.getBits(bArr[i3], 0, 4);
                    if (ByteUtils.getBits(bArr[i3], 4, 4) != 0) {
                        z = false;
                    }
                    history.off = z;
                } else {
                    if (bArr[i + 4] != 0) {
                        z = false;
                    }
                    history.off = z;
                }
            }
        }
        return history;
    }

    static byte[] check(byte[] bArr) {
        int length = (bArr.length - 10) - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 10, bArr2, 0, length);
        return bArr2;
    }

    public static Boolean parseTempUnit(byte[] bArr) {
        byte[] check = check(bArr);
        if (check[0] == 32) {
            return Boolean.valueOf(!ByteUtils.getBit(check[2], 7));
        }
        return false;
    }
}
