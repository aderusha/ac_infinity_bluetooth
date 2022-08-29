package com.eternal.base.protocol;

import com.eternal.base.C1323R;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceModelTime;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.PortList;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.concat.WiFiDevice;
import com.eternal.base.database.entity.History;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.database.entity.NotificationMessage;
import com.eternal.base.utils.ByteUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.StringUtils;
import com.eternal.framework.utils.Utils;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;

public class EFamilialResolutionV4 extends BaseProtocol {
    public static byte formatBitForAdvance(boolean z, boolean z2, int i) {
        return (byte) (((byte) (((((byte) ((z | false ? 1 : 0) << true)) >> 6) | z2 ? 1 : 0) << true)) | i);
    }

    public static byte[] setDeviceMode(int i) {
        return new byte[]{8, 8, (byte) i, 1, 1};
    }

    public static byte[] setStaWifiConfirm(int i) {
        return new byte[]{12, 0, (byte) i, 0};
    }

    public static byte[] getE_WifiDeviceInfo(int i) {
        return addHead(new byte[]{0, -1, 0}, (byte) 1, i);
    }

    public static WiFiDevice parseE_WifiDeviceInfo(byte[] bArr) {
        byte[] check = check(bArr);
        WiFiDevice wiFiDevice = new WiFiDevice();
        if (check[0] != 0 || check.length < 16) {
            return null;
        }
        wiFiDevice.f140id = new String(check, 2, 5, StandardCharsets.US_ASCII);
        wiFiDevice.version = check[7];
        wiFiDevice.type = check[8];
        wiFiDevice.typeName = Utils.getString(C1323R.string.tip_name_name, ProtocolTransformer.getType(wiFiDevice.type), wiFiDevice.f140id);
        wiFiDevice.mac = StringUtils.getMacString(ByteUtils.subByte(check, 9, 6));
        return wiFiDevice;
    }

    public static byte[] setAPConfig(String str, String str2, byte[] bArr, int i, int i2) {
        byte[] bytesByString = ByteUtils.getBytesByString(str);
        byte[] bytesByString2 = ByteUtils.getBytesByString(str2);
        int length = bytesByString.length + bytesByString2.length + 8 + 2;
        byte[] bArr2 = new byte[(length + 2)];
        bArr2[0] = 8;
        bArr2[1] = (byte) (length - 2);
        bArr2[2] = (byte) bytesByString.length;
        System.arraycopy(bytesByString, 0, bArr2, 3, bytesByString.length);
        int length2 = bytesByString.length + 3;
        bArr2[length2] = (byte) bytesByString2.length;
        int i3 = length2 + 1;
        System.arraycopy(bytesByString2, 0, bArr2, i3, bytesByString2.length);
        int length3 = i3 + bytesByString2.length;
        System.arraycopy(bArr, 0, bArr2, length3, bArr.length);
        addInit(bArr2, length3 + bArr.length, i);
        bArr2[length] = -1;
        bArr2[length + 1] = 0;
        return addHead(bArr2, (byte) 3, i2);
    }

    public static byte[] setStaWifiSSID(String str, int i) {
        byte[] bytesByString = ByteUtils.getBytesByString(str);
        byte[] bArr = new byte[(bytesByString.length + 4)];
        bArr[0] = 9;
        bArr[1] = 0;
        bArr[2] = (byte) i;
        bArr[3] = (byte) bytesByString.length;
        System.arraycopy(bytesByString, 0, bArr, 4, bytesByString.length);
        return bArr;
    }

    public static byte[] setStaWifiPwd(String str, int i) {
        byte[] bytesByString = ByteUtils.getBytesByString(str);
        byte[] bArr = new byte[(bytesByString.length + 4)];
        bArr[0] = 13;
        bArr[1] = 0;
        bArr[2] = (byte) i;
        bArr[3] = (byte) bytesByString.length;
        System.arraycopy(bytesByString, 0, bArr, 4, bytesByString.length);
        return bArr;
    }

    public static byte[] setStaIpInfo(String str, int i) {
        byte[] bytesByString = ByteUtils.getBytesByString(str);
        byte[] bArr = new byte[(bytesByString.length + 4)];
        bArr[0] = 77;
        bArr[1] = 0;
        bArr[2] = (byte) i;
        bArr[3] = (byte) bytesByString.length;
        System.arraycopy(bytesByString, 0, bArr, 4, bytesByString.length);
        return bArr;
    }

    public static byte[] setTimeData(int i) {
        Calendar instance = Calendar.getInstance();
        return addHead(new byte[]{1, 7, (byte) (instance.get(1) % 100), (byte) (instance.get(2) + 1), (byte) instance.get(5), (byte) instance.get(7), (byte) instance.get(11), (byte) instance.get(12), (byte) instance.get(13), -1, 0}, (byte) 3, i);
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
        deviceModel.schedOn = (char) Math.round((float) ((check[42] * 60) + check[43]));
        deviceModel.schedOff = (char) Math.round((float) ((check[44] * 60) + check[45]));
        return deviceModel;
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
                    byte b2 = subByte[10];
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
            case 8:
                System.arraycopy(subByte, 2, deviceMinModel.values, 0, 3);
                return true;
            default:
                System.arraycopy(subByte, 2, deviceMinModel.values, 0, 4);
                if (subByte[6] == 24) {
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

    public static boolean parseMinModel(DeviceMinModel deviceMinModel, byte[] bArr, byte b) {
        byte[] check = check(bArr);
        if (check[0] == 16) {
            deviceMinModel.model = check[2];
            if (check[3] == 2) {
                deviceMinModel.tmpState = (byte) ByteUtils.getBits(check[5], 4, 2);
                deviceMinModel.humState = (byte) ByteUtils.getBits(check[5], 6, 2);
                deviceMinModel.tmp = ByteUtils.getShort(check, 6);
                deviceMinModel.hum = ByteUtils.getShort(check, 8);
                if (check[4] == 7) {
                    deviceMinModel.vpdState = (byte) ByteUtils.getBits(check[2], 2, 2);
                    deviceMinModel.vpd = ByteUtils.getShort(check, 10);
                    check = ByteUtils.subByte(check, 2, check.length - 2);
                }
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
                        if (check[20] == 4) {
                            deviceMinModel.choosePort = check[22];
                            if (check[27] == 7) {
                                int i = check[28] + Ascii.f273GS;
                                for (int i2 = 29; i2 < i; i2 += 7) {
                                    PortInfo portInfo = new PortInfo();
                                    portInfo.f138id = check[i2];
                                    int i3 = i2 + 1;
                                    portInfo.isPlug = check[i3] != -1;
                                    if (b < 2) {
                                        portInfo.fanType = check[i2 + 2];
                                    } else {
                                        portInfo.fanType = ByteUtils.getShort(check, i3);
                                    }
                                    int i4 = i2 + 3;
                                    portInfo.fanState = (byte) ByteUtils.getBits(check[i4], 0, 2);
                                    portInfo.fan = ByteUtils.getBits(check[i4], 4, 4);
                                    deviceMinModel.portList.add(portInfo);
                                }
                                return true;
                            }
                        }
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
        KLog.m65e("model err:" + ByteUtils.bytes2HexString(check));
        return false;
    }

    public static byte[] getTFPData(byte b, int i) {
        return addHead(new byte[]{2, 3, 32, 4, 6, 7, -1, 0}, b, i);
    }

    public static byte[] getF_TFPData(byte b, int i) {
        return addHead(new byte[]{2, 3, 32, 4, 7, -1, 0}, b, i);
    }

    public static DeviceTFP parseTFP(byte[] bArr, byte b) {
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
                if (check[14] == 32) {
                    deviceTFP.isDegree = !ByteUtils.getBit(check[16], 7);
                    if (check[17] == 4) {
                        deviceTFP.choosePort = check[19];
                        if (check[31] == 7) {
                            int i = check[32] + 33;
                            for (int i2 = 33; i2 < i; i2 += 7) {
                                PortInfo portInfo = new PortInfo();
                                portInfo.f138id = check[i2];
                                int i3 = i2 + 1;
                                portInfo.isPlug = check[i3] != -1;
                                if (b < 2) {
                                    portInfo.fanType = check[i2 + 2];
                                } else {
                                    portInfo.fanType = ByteUtils.getShort(check, i3);
                                }
                                int i4 = i2 + 3;
                                portInfo.fanState = (byte) ByteUtils.getBits(check[i4], 0, 2);
                                portInfo.fan = ByteUtils.getBits(check[i4], 4, 4);
                                deviceTFP.portList.add(portInfo);
                            }
                            return deviceTFP;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static PortList parsePortList(byte[] bArr, byte b) {
        PortList portList = new PortList();
        byte[] check = check(bArr);
        if (check[0] == 4) {
            portList.choosePort = check[2];
            if (check[7] == 7) {
                portList.portList = new ArrayList();
                int i = check[8] + 9;
                for (int i2 = 9; i2 < i; i2 += 7) {
                    PortSetting portSetting = new PortSetting();
                    portSetting.f138id = check[i2];
                    int i3 = i2 + 1;
                    portSetting.isPlug = check[i3] != -1;
                    if (b < 2) {
                        portSetting.fanType = check[i2 + 2];
                    } else {
                        portSetting.fanType = ByteUtils.getShort(check, i3);
                    }
                    int i4 = i2 + 3;
                    portSetting.fanState = (byte) ByteUtils.getBits(check[i4], 0, 2);
                    portSetting.fan = ByteUtils.getBits(check[i4], 4, 4);
                    portList.portList.add(portSetting);
                }
                return portList;
            }
        }
        return null;
    }

    public static DeviceTFP parseF_TFP(byte[] bArr, byte b) {
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
            }
            int i = check[1] + 0 + 2;
            if (check[i] == 3) {
                deviceTFP.fanType = check[i + 2];
                int i2 = i + 3;
                deviceTFP.fan = ByteUtils.getBits(check[i2], 4, 4);
                deviceTFP.fanState = (byte) ByteUtils.getBits(check[i2], 0, 2);
                int i3 = i + check[i + 1] + 2;
                if (check[i3] == 32) {
                    deviceTFP.isDegree = !ByteUtils.getBit(check[16], 7);
                    int i4 = i3 + check[i3 + 1] + 2;
                    if (check[i4] == 4) {
                        deviceTFP.choosePort = check[i4 + 2];
                        int i5 = i4 + check[i4 + 1] + 2;
                        if (check[i5] == 7) {
                            int i6 = check[i5 + 1] + i5 + 2;
                            for (int i7 = i5 + 2; i7 < i6; i7 += 7) {
                                PortInfo portInfo = new PortInfo();
                                portInfo.f138id = check[i7];
                                portInfo.isPlug = check[i7 + 1] != -1;
                                portInfo.fanType = check[i7 + 2];
                                int i8 = i7 + 3;
                                portInfo.fanState = (byte) ByteUtils.getBits(check[i8], 0, 2);
                                portInfo.fan = ByteUtils.getBits(check[i8], 4, 4);
                                deviceTFP.portList.add(portInfo);
                            }
                            return deviceTFP;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static Boolean parseTempUnit(byte[] bArr) {
        byte[] check = check(bArr);
        if (check[0] == 32) {
            return Boolean.valueOf(!ByteUtils.getBit(check[2], 7));
        }
        return false;
    }

    public static DeviceSetting parseSetting(byte[] bArr, byte b) {
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
                            if (check[22] == 4) {
                                deviceSetting.choosePort = check[24];
                                if (check[29] == 7) {
                                    deviceSetting.portList = new ArrayList();
                                    int i = check[30] + 31;
                                    for (int i2 = 31; i2 < i; i2 += 7) {
                                        PortSetting portSetting = new PortSetting();
                                        portSetting.f138id = check[i2];
                                        int i3 = i2 + 1;
                                        portSetting.isPlug = check[i3] != -1;
                                        if (b < 2) {
                                            portSetting.fanType = check[i2 + 2];
                                        } else {
                                            portSetting.fanType = ByteUtils.getShort(check, i3);
                                        }
                                        int i4 = i2 + 3;
                                        portSetting.fanState = (byte) ByteUtils.getBits(check[i4], 0, 2);
                                        portSetting.fan = ByteUtils.getBits(check[i4], 4, 4);
                                        deviceSetting.portList.add(portSetting);
                                    }
                                    if (check[i] == 37 && check[i + 1] == 2) {
                                        deviceSetting.leafTemperatureF = check[i + 2];
                                        deviceSetting.leafTemperatureC = check[i + 3];
                                    }
                                    return deviceSetting;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static byte[] getNotificationMessage(byte b, int i) {
        return addHead(new byte[]{32, 56, -1, b}, (byte) 1, i);
    }

    public static List<NotificationMessage> parseNotificationMessage(String str, byte[] bArr) {
        byte[] check = check(bArr);
        ArrayList arrayList = new ArrayList();
        if (check[0] == 32) {
            boolean z = !ByteUtils.getBit(check[2], 7);
            for (int i = 3; i < check.length; i += 21) {
                if (check[i] == 56 && check[i + 1] == 19) {
                    NotificationMessage notificationMessage = new NotificationMessage();
                    notificationMessage.isDegree = z;
                    int i2 = i + 6;
                    notificationMessage.type = (byte) ByteUtils.getBits(check[i2], 0, 2);
                    notificationMessage.notifyId = (byte) ByteUtils.getBits(check[i2], 2, 6);
                    notificationMessage.notifyType = check[i + 7];
                    notificationMessage.notifyId2 = check[i + 8];
                    notificationMessage.port = check[i + 9];
                    notificationMessage.notifyType2 = check[i + 10];
                    notificationMessage.mac = str;
                    System.arraycopy(check, i + 11, notificationMessage.values, 0, 10);
                    arrayList.add(notificationMessage);
                }
            }
            if (arrayList.size() == 0) {
                NotificationMessage notificationMessage2 = new NotificationMessage();
                notificationMessage2.isDegree = z;
                notificationMessage2.mac = str;
                notificationMessage2.type = -1;
                arrayList.add(notificationMessage2);
            }
        }
        return arrayList;
    }

    public static byte[] getTempUnitData(byte b, int i) {
        return addHead(new byte[]{32, -1, b}, (byte) 1, i);
    }

    public static byte[] getAllModelData(byte b, int i) {
        return addHead(new byte[]{Ascii.DLE, 17, 18, 19, 20, 21, 22, Ascii.ETB, -1, b}, (byte) 1, i);
    }

    public static byte[] setModelData(byte b, DeviceModel deviceModel, int i) {
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
                if (c == 65535) {
                    bArr[2] = -1;
                    bArr[3] = -1;
                } else {
                    if (c == 1440) {
                        c = 1439;
                    }
                    bArr[2] = (byte) (c / Typography.less);
                    bArr[3] = (byte) (c % Typography.less);
                }
                char c3 = deviceModel.schedOff;
                if (c3 != 65535) {
                    if (c3 != 1440) {
                        c2 = c3;
                    }
                    bArr[4] = (byte) (c2 / Typography.less);
                    bArr[5] = (byte) (c2 % Typography.less);
                    break;
                } else {
                    bArr[4] = -1;
                    bArr[5] = -1;
                    break;
                }
        }
        byte[] bArr2 = new byte[(bArr.length + 2)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = -1;
        bArr2[bArr.length + 1] = b;
        return addHead(bArr2, (byte) 3, i);
    }

    public static byte[] setModelData(byte b, byte b2, int i) {
        return addHead(new byte[]{Ascii.DLE, 1, b2, -1, b}, (byte) 3, i);
    }

    private static void setSecond(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >> 8) & 255);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public static byte[] setChoosePort(byte b, int i) {
        return addHead(new byte[]{4, 5, b, 0, 0, 0, 0}, (byte) 3, i);
    }

    public static byte[] getModelData(byte b, int i) {
        return addHead(new byte[]{Ascii.DLE, 2, 3, 32, 4, 7, -1, b}, (byte) 1, i);
    }

    public static byte[] getPortListData(byte b, int i) {
        return addHead(new byte[]{4, 7, -1, b}, (byte) 1, i);
    }

    public static byte[] getOffModelData(byte b, int i) {
        return addHead(new byte[]{17, 18, -1, b}, (byte) 1, i);
    }

    public static byte[] getOnModelData(byte b, int i) {
        return addHead(new byte[]{17, 18, -1, b}, (byte) 1, i);
    }

    public static byte[] getAutoModelData(byte b, int i, boolean z) {
        byte[] bArr = new byte[6];
        bArr[0] = 17;
        bArr[1] = 18;
        bArr[2] = 19;
        bArr[3] = (byte) (z ? 35 : 34);
        bArr[4] = -1;
        bArr[5] = b;
        return addHead(bArr, (byte) 1, i);
    }

    public static byte[] getTimeOnModelData(byte b, int i) {
        return addHead(new byte[]{17, 18, 20, Ascii.CAN, -1, b}, (byte) 1, i);
    }

    public static byte[] getTimeOffModelData(byte b, int i) {
        return addHead(new byte[]{17, 18, 21, Ascii.CAN, -1, b}, (byte) 1, i);
    }

    public static byte[] getCycleModelData(byte b, int i) {
        return addHead(new byte[]{17, 18, 22, Ascii.CAN, -1, b}, (byte) 1, i);
    }

    public static byte[] getSchedModelData(byte b, int i) {
        return addHead(new byte[]{17, 18, Ascii.ETB, Ascii.CAN, -1, b}, (byte) 1, i);
    }

    public static byte[] getLogStart(long j, long j2, int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        byte[] bArr = new byte[16];
        bArr[0] = (byte) (instance.get(1) - 2000);
        bArr[1] = (byte) (instance.get(2) + 1);
        bArr[2] = (byte) instance.get(5);
        bArr[3] = (byte) instance.get(11);
        bArr[4] = (byte) instance.get(12);
        bArr[5] = (byte) instance.get(13);
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(j2) - TimeUnit.MILLISECONDS.toSeconds(j));
        bArr[6] = (byte) ((seconds >> 24) & 255);
        bArr[7] = (byte) ((seconds >> 16) & 255);
        bArr[8] = (byte) ((seconds >> 8) & 255);
        bArr[9] = (byte) (seconds & 255);
        bArr[10] = (byte) ((i >> 24) & 255);
        bArr[11] = (byte) ((i >> 16) & 255);
        bArr[12] = (byte) ((i >> 8) & 255);
        bArr[13] = (byte) (i & 255);
        return addHead(bArr, (byte) 17, i2);
    }

    public static byte[] getLog(int i, int i2, int i3) {
        return addHead(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)}, (byte) 18, i3);
    }

    public static int parseLogSize(byte[] bArr) {
        byte[] check = check(bArr);
        return ((check[0] & 255) << Ascii.CAN) + 0 + ((check[1] & 255) << Ascii.DLE) + ((check[2] & 255) << 8) + (check[3] & 255);
    }

    public static Log parseLog(byte[] bArr, int i, long j) {
        if (i + 30 >= bArr.length) {
            return null;
        }
        Log log = new Log();
        boolean z = true;
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
        log.logType = bArr[i + 11];
        log.notifyId = bArr[i + 12];
        log.day = bArr[i + 13];
        log.start = (char) ((bArr[i + 14] * 60) + bArr[i + 15]);
        log.end = (char) ((bArr[i + 16] * 60) + bArr[i + 17]);
        log.port = bArr[i + 18];
        log.model = bArr[i + 19];
        switch (log.model) {
            case 1:
            case 2:
                log.fan = bArr[i + 20];
                break;
            case 3:
                log.tmpHum = (short) bArr[i + 20];
                log.hTmpF = bArr[i + 21];
                log.hTmpC = bArr[i + 22];
                log.lTmpF = bArr[i + 23];
                log.lTmpC = bArr[i + 24];
                log.hHum = bArr[i + 25];
                log.lHum = bArr[i + 26];
                log.fan = bArr[i + 27];
                break;
            case 4:
                log.fan = bArr[i + 24];
                log.f143on = (char) (((((bArr[i + 21] & 255) << Ascii.DLE) | ((bArr[i + 22] & 255) << 8)) | (bArr[i + 23] & 255)) / 60);
                break;
            case 5:
                log.fan = bArr[i + 24];
                log.off = (char) ((((bArr[i + 21] & 255) << Ascii.DLE) | (((bArr[i + 22] & 255) << 8) + (bArr[i + 23] & 255))) / 60);
                break;
            case 6:
                log.f143on = (char) (((((bArr[i + 21] & 255) << Ascii.DLE) + ((bArr[i + 22] & 255) << 8)) + (bArr[i + 23] & 255)) / 60);
                log.off = (char) (((((bArr[i + 25] & 255) << Ascii.DLE) + ((bArr[i + 26] & 255) << 8)) + (bArr[i + 27] & 255)) / 60);
                if (bArr[i + 28] == 0) {
                    z = false;
                }
                log.isStart = z;
                log.fan = bArr[i + 29];
                break;
            case 7:
                log.f143on = (char) ((bArr[i + 20] * 60) + bArr[i + 21]);
                log.off = (char) ((bArr[i + 22] * 60) + bArr[i + 23]);
                if (bArr[i + 24] != 0) {
                    z = false;
                }
                log.isStart = z;
                log.fan = bArr[i + 25];
                break;
            case 8:
            case 11:
                log.tmpHum = (short) bArr[i + 20];
                log.hVpd = bArr[i + 21];
                log.lVpd = bArr[i + 22];
                log.fan = bArr[i + 23];
                break;
            case 9:
                log.tmpHum = (short) bArr[i + 20];
                log.hTmpF = bArr[i + 21];
                log.hTmpC = bArr[i + 22];
                log.lTmpF = bArr[i + 23];
                log.lTmpC = bArr[i + 24];
                log.fan = bArr[i + 25];
                break;
            case 10:
                log.tmpHum = (short) bArr[i + 20];
                log.hHum = bArr[i + 21];
                log.lHum = bArr[i + 22];
                log.fan = bArr[i + 23];
                break;
        }
        return log;
    }

    public static byte[] getNotificationData(byte b, int i) {
        return addHead(new byte[]{54, 55, -1, b}, (byte) 1, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.eternal.base.database.entity.Notification> parseNotifications(byte[] r14) {
        /*
            byte[] r14 = check(r14)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = 0
        L_0x000b:
            int r3 = r14.length
            if (r2 >= r3) goto L_0x0209
            com.eternal.base.database.entity.Notification r3 = new com.eternal.base.database.entity.Notification
            r3.<init>()
            byte r4 = r14[r2]
            r5 = 54
            r6 = 3
            r7 = 5
            r8 = 6
            r9 = 1
            r10 = 4
            if (r4 != r5) goto L_0x0152
            int r4 = r14.length
            int r5 = r2 + 20
            if (r4 >= r5) goto L_0x0025
            goto L_0x0209
        L_0x0025:
            int r4 = r2 + 1
            byte r4 = r14[r4]
            if (r4 != 0) goto L_0x002d
            goto L_0x015f
        L_0x002d:
            int r4 = r2 + 2
            byte r11 = r14[r4]
            boolean r11 = com.eternal.base.utils.ByteUtils.getBit(r11, r1)
            r11 = r11 ^ r9
            r3.advActive = r11
            byte r4 = r14[r4]
            boolean r4 = com.eternal.base.utils.ByteUtils.getBit(r4, r9)
            r4 = r4 ^ r9
            r3.open = r4
            int r4 = r2 + 3
            byte r9 = r14[r4]
            int r9 = com.eternal.base.utils.ByteUtils.getBits(r9, r1, r10)
            r3.f144id = r9
            byte r4 = r14[r4]
            int r4 = com.eternal.base.utils.ByteUtils.getBits(r4, r10, r10)
            byte r4 = (byte) r4
            r3.groupIndex = r4
            int r4 = r2 + 4
            byte r9 = r14[r4]
            int r9 = com.eternal.base.utils.ByteUtils.getBits(r9, r1, r10)
            byte r9 = (byte) r9
            r3.childId = r9
            byte r4 = r14[r4]
            int r4 = com.eternal.base.utils.ByteUtils.getBits(r4, r10, r10)
            byte r4 = (byte) r4
            r3.childIndex = r4
            int r4 = r2 + 5
            byte r4 = r14[r4]
            r3.day = r4
            int r4 = r2 + 6
            byte r9 = r14[r4]
            r11 = 65535(0xffff, float:9.1834E-41)
            r12 = -1
            if (r9 == r12) goto L_0x008a
            int r9 = r2 + 7
            byte r13 = r14[r9]
            if (r13 != r12) goto L_0x007f
            goto L_0x008a
        L_0x007f:
            byte r4 = r14[r4]
            int r4 = r4 * 60
            byte r9 = r14[r9]
            int r4 = r4 + r9
            char r4 = (char) r4
            r3.start = r4
            goto L_0x008c
        L_0x008a:
            r3.start = r11
        L_0x008c:
            int r4 = r2 + 8
            byte r9 = r14[r4]
            if (r9 == r12) goto L_0x00a4
            int r9 = r2 + 9
            byte r13 = r14[r9]
            if (r13 != r12) goto L_0x0099
            goto L_0x00a4
        L_0x0099:
            byte r4 = r14[r4]
            int r4 = r4 * 60
            byte r9 = r14[r9]
            int r4 = r4 + r9
            char r4 = (char) r4
            r3.end = r4
            goto L_0x00a6
        L_0x00a4:
            r3.end = r11
        L_0x00a6:
            int r4 = r2 + 10
            byte r4 = r14[r4]
            r3.port = r4
            int r4 = r2 + 11
            byte r4 = r14[r4]
            r3.model = r4
            int r4 = r2 + 12
            byte r9 = r14[r4]
            int r9 = com.eternal.base.utils.ByteUtils.getBits(r9, r10, r10)
            r3.levelOff = r9
            byte r4 = r14[r4]
            int r4 = com.eternal.base.utils.ByteUtils.getBits(r4, r1, r10)
            r3.levelOn = r4
            byte r4 = r3.model
            if (r4 != r6) goto L_0x0104
            int r4 = r2 + 13
            byte r4 = r14[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 16
            int r6 = r2 + 14
            byte r6 = r14[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            int r4 = r4 + r6
            int r6 = r2 + 15
            byte r6 = r14[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r4 = r4 + r6
            int r4 = r4 / 60
            char r4 = (char) r4
            r3.cycleOn = r4
            int r4 = r2 + 16
            byte r4 = r14[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 16
            int r6 = r2 + 17
            byte r6 = r14[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            int r4 = r4 + r6
            int r2 = r2 + 18
            byte r2 = r14[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r4 = r4 + r2
            int r4 = r4 / 60
            char r2 = (char) r4
            r3.cycleOff = r2
            goto L_0x0203
        L_0x0104:
            byte r4 = r3.model
            if (r4 != r10) goto L_0x0134
            int r4 = r2 + 13
            byte r4 = r14[r4]
            r3.tmpHum = r4
            int r4 = r2 + 14
            byte r4 = r14[r4]
            r3.hTmpF = r4
            int r4 = r2 + 15
            byte r4 = r14[r4]
            r3.hTmpC = r4
            int r4 = r2 + 16
            byte r4 = r14[r4]
            r3.lTmpF = r4
            int r4 = r2 + 17
            byte r4 = r14[r4]
            r3.lTmpC = r4
            int r4 = r2 + 18
            byte r4 = r14[r4]
            r3.hHum = r4
            int r2 = r2 + 19
            byte r2 = r14[r2]
            r3.lHum = r2
            goto L_0x0203
        L_0x0134:
            byte r4 = r3.model
            if (r4 != r7) goto L_0x013a
            goto L_0x0203
        L_0x013a:
            byte r4 = r3.model
            if (r4 != r8) goto L_0x0203
            int r4 = r2 + 13
            byte r4 = r14[r4]
            r3.tmpHum = r4
            int r4 = r2 + 14
            byte r4 = r14[r4]
            r3.hVpd = r4
            int r2 = r2 + 15
            byte r2 = r14[r2]
            r3.lVpd = r2
            goto L_0x0203
        L_0x0152:
            int r4 = r14.length
            int r5 = r2 + 10
            if (r4 >= r5) goto L_0x0159
            goto L_0x0209
        L_0x0159:
            int r4 = r2 + 1
            byte r4 = r14[r4]
            if (r4 != 0) goto L_0x0163
        L_0x015f:
            int r2 = r2 + 2
            goto L_0x000b
        L_0x0163:
            int r4 = r2 + 2
            byte r11 = r14[r4]
            boolean r11 = com.eternal.base.utils.ByteUtils.getBit(r11, r9)
            r9 = r9 ^ r11
            r3.open = r9
            byte r4 = r14[r4]
            r9 = 2
            int r4 = com.eternal.base.utils.ByteUtils.getBits(r4, r9, r8)
            r3.f144id = r4
            int r4 = r3.f144id
            byte r4 = (byte) r4
            r3.groupIndex = r4
            int r4 = r2 + 3
            byte r4 = r14[r4]
            r3.model = r4
            byte r4 = r3.model
            boolean r4 = com.eternal.base.utils.ByteUtils.getBit(r4, r9)
            if (r4 != 0) goto L_0x01a9
            int r4 = r2 + 4
            byte r4 = r14[r4]
            r3.tmpHum = r4
            int r4 = r2 + 5
            byte r4 = r14[r4]
            r3.hTmpF = r4
            int r4 = r2 + 6
            byte r4 = r14[r4]
            r3.hTmpC = r4
            int r4 = r2 + 7
            byte r4 = r14[r4]
            r3.lTmpF = r4
            int r4 = r2 + 8
            byte r4 = r14[r4]
            r3.lTmpC = r4
            goto L_0x01fd
        L_0x01a9:
            byte r4 = r3.model
            boolean r4 = com.eternal.base.utils.ByteUtils.getBit(r4, r6)
            if (r4 != 0) goto L_0x01c4
            int r4 = r2 + 4
            byte r4 = r14[r4]
            r3.tmpHum = r4
            int r4 = r2 + 5
            byte r4 = r14[r4]
            r3.hHum = r4
            int r4 = r2 + 6
            byte r4 = r14[r4]
            r3.lHum = r4
            goto L_0x01fd
        L_0x01c4:
            byte r4 = r3.model
            boolean r4 = com.eternal.base.utils.ByteUtils.getBit(r4, r10)
            if (r4 != 0) goto L_0x01df
            int r4 = r2 + 4
            byte r4 = r14[r4]
            r3.tmpHum = r4
            int r4 = r2 + 5
            byte r4 = r14[r4]
            r3.hVpd = r4
            int r4 = r2 + 6
            byte r4 = r14[r4]
            r3.lVpd = r4
            goto L_0x01fd
        L_0x01df:
            byte r4 = r3.model
            boolean r4 = com.eternal.base.utils.ByteUtils.getBit(r4, r7)
            if (r4 != 0) goto L_0x01ee
            int r4 = r2 + 5
            byte r4 = r14[r4]
            r3.port = r4
            goto L_0x01fd
        L_0x01ee:
            byte r4 = r3.model
            boolean r4 = com.eternal.base.utils.ByteUtils.getBit(r4, r8)
            if (r4 != 0) goto L_0x01f7
            goto L_0x01fd
        L_0x01f7:
            byte r4 = r3.model
            r6 = 7
            com.eternal.base.utils.ByteUtils.getBit(r4, r6)
        L_0x01fd:
            int r2 = r2 + 9
            byte r2 = r14[r2]
            r3.beeps = r2
        L_0x0203:
            r2 = r5
            r0.add(r3)
            goto L_0x000b
        L_0x0209:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.base.protocol.EFamilialResolutionV4.parseNotifications(byte[]):java.util.List");
    }

    public static byte[] setNotificationData(Notification notification, boolean z, boolean z2, int i) {
        byte[] bArr;
        if (notification.type == 1) {
            bArr = new byte[20];
            bArr[0] = 54;
            bArr[1] = 18;
            bArr[2] = formatBitForAdvance(notification.advActive, z2, 0);
            bArr[3] = (byte) ((notification.f144id << 4) | notification.groupIndex);
            bArr[4] = (byte) ((notification.childId << 4) | notification.childIndex);
            bArr[5] = notification.day;
            bArr[6] = (byte) (notification.start / Typography.less);
            bArr[7] = (byte) (notification.start % Typography.less);
            bArr[8] = (byte) (notification.end / Typography.less);
            bArr[9] = (byte) (notification.end % Typography.less);
            bArr[10] = notification.port;
            bArr[11] = notification.model;
            bArr[12] = (byte) ((notification.levelOn << 4) | notification.levelOff);
            if (notification.model == 3) {
                int i2 = notification.cycleOn * Typography.less;
                bArr[13] = (byte) (i2 >> 16);
                bArr[14] = (byte) (i2 >> 8);
                bArr[15] = (byte) i2;
                int i3 = notification.cycleOff * Typography.less;
                bArr[16] = (byte) (i3 >> 16);
                bArr[17] = (byte) (i3 >> 8);
                bArr[18] = (byte) i3;
            } else if (notification.model == 4) {
                bArr[13] = notification.tmpHum;
                bArr[14] = notification.hTmpF;
                bArr[15] = notification.hTmpC;
                bArr[16] = notification.lTmpF;
                bArr[17] = notification.lTmpC;
                bArr[18] = notification.hHum;
                bArr[19] = notification.lHum;
            } else if (notification.model != 5 && notification.model == 6) {
                bArr[13] = notification.tmpHum;
                bArr[14] = notification.hVpd;
                bArr[15] = notification.lVpd;
            }
        } else {
            byte[] bArr2 = new byte[10];
            bArr2[0] = 55;
            bArr2[1] = 8;
            bArr2[2] = formatBitForAdvance(z, z2, notification.f144id);
            bArr2[3] = notification.model;
            if (!ByteUtils.getBit(notification.model, 2)) {
                bArr2[4] = notification.tmpHum;
                bArr2[5] = notification.hTmpF;
                bArr2[6] = notification.hTmpC;
                bArr2[7] = notification.lTmpF;
                bArr2[8] = notification.lTmpC;
            } else if (!ByteUtils.getBit(notification.model, 3)) {
                bArr2[4] = notification.tmpHum;
                bArr2[5] = notification.hHum;
                bArr2[6] = notification.lHum;
            } else if (!ByteUtils.getBit(notification.model, 4)) {
                bArr2[4] = notification.tmpHum;
                bArr2[5] = notification.hVpd;
                bArr2[6] = notification.lVpd;
            } else if (!ByteUtils.getBit(notification.model, 5)) {
                bArr2[5] = notification.port;
            } else if (ByteUtils.getBit(notification.model, 6)) {
                ByteUtils.getBit(notification.model, 7);
            }
            bArr2[9] = notification.beeps;
            bArr = bArr2;
        }
        return addHead(ByteUtils.mergeBytes(bArr, new byte[]{-1, 0}), (byte) 3, i);
    }

    public static byte[] setAutomationOperation(byte b, byte b2, boolean z, boolean z2, int i) {
        return addHead(new byte[]{52, 2, (byte) (((byte) ((z ? 1 : 0) << true)) | ((z2 ? 1 : 0) << true)), (byte) ((b << 4) | (b2 & 15)), -1, 0}, (byte) 3, i);
    }

    public static byte[] setAutomationOrder(List<Byte> list, byte b, boolean z, int i) {
        byte[] bArr = new byte[(list.size() + 3)];
        bArr[0] = 53;
        bArr[1] = (byte) (list.size() + 1);
        byte b2 = (byte) ((z ? 1 : 0) << true);
        if (!z) {
            b = 15;
        }
        bArr[2] = (byte) (b | b2);
        for (int i2 = 0; i2 < list.size(); i2++) {
            bArr[i2 + 3] = list.get(i2).byteValue();
        }
        return addHead(ByteUtils.mergeBytes(bArr, new byte[]{-1, 0}), (byte) 3, i);
    }

    public static byte[] getSettingData(byte b, byte b2, int i) {
        byte[] bArr;
        byte[] bArr2 = {32, 33, 34, 36, 17, 18, 4, 7};
        if (b2 >= 3) {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{37, -1, b});
        } else {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{-1, b});
        }
        return addHead(bArr, (byte) 1, i);
    }

    public static byte[] getFSettingData(byte b, byte b2, int i) {
        byte[] bArr;
        byte[] bArr2 = {32, 33, 35, 36, 17, 18, 4, 7};
        if (b2 >= 3) {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{37, -1, b});
        } else {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{-1, b});
        }
        return addHead(bArr, (byte) 1, i);
    }

    public static byte[] setSettingData(byte b, DeviceSetting deviceSetting, byte b2, int i) {
        byte[] bArr;
        DeviceSetting deviceSetting2 = deviceSetting;
        byte b3 = b2;
        byte[] bArr2 = new byte[17];
        bArr2[0] = 32;
        bArr2[1] = 1;
        bArr2[2] = deviceSetting2.isDegree ? (byte) 1 : 0;
        bArr2[3] = 33;
        bArr2[4] = 1;
        bArr2[5] = deviceSetting2.brightness;
        bArr2[6] = 36;
        bArr2[7] = 3;
        bArr2[8] = 0;
        bArr2[9] = 0;
        bArr2[10] = deviceSetting2.calibrationHumidity;
        if (deviceSetting2.isDegree) {
            bArr2[9] = deviceSetting2.calibrationTemperature;
        } else {
            bArr2[8] = deviceSetting2.calibrationTemperature;
        }
        bArr2[11] = 17;
        bArr2[12] = 1;
        bArr2[13] = deviceSetting2.typeOff;
        bArr2[14] = 18;
        bArr2[15] = 1;
        bArr2[16] = deviceSetting2.typeOn;
        if (b3 >= 4) {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{34, 4, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, deviceSetting2.transitionVpd});
        } else {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{34, 3, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity});
        }
        if (b3 >= 3) {
            bArr = ByteUtils.mergeBytes(bArr, new byte[]{37, 2, deviceSetting2.leafTemperatureF, deviceSetting2.leafTemperatureC});
        }
        return addHead(ByteUtils.mergeBytes(bArr, new byte[]{-1, b}), (byte) 3, i);
    }

    public static byte[] setFSettingData(byte b, DeviceSetting deviceSetting, byte b2, int i) {
        byte[] bArr;
        DeviceSetting deviceSetting2 = deviceSetting;
        byte[] bArr2 = {32, 1, deviceSetting2.isDegree ? (byte) 1 : 0, 33, 1, deviceSetting2.brightness, 36, 3, 0, 0, deviceSetting2.calibrationHumidity};
        if (deviceSetting2.isDegree) {
            bArr2[8] = (byte) Math.round(ProtocolTransformer.getFah((float) deviceSetting2.calibrationTemperature));
            bArr2[9] = deviceSetting2.calibrationTemperature;
        } else {
            bArr2[8] = deviceSetting2.calibrationTemperature;
            bArr2[9] = (byte) Math.round(ProtocolTransformer.getDegree((float) deviceSetting2.calibrationTemperature));
        }
        if (b2 >= 4) {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{35, 4, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, deviceSetting2.transitionVpd, -1, b});
        } else {
            bArr = ByteUtils.mergeBytes(bArr2, new byte[]{35, 3, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, -1, b});
        }
        return addHead(bArr, (byte) 3, i);
    }

    public static History parseHistory(byte[] bArr, int i, long j, byte b, byte b2) {
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
            int i2 = 0;
            if (b == 3) {
                if (bArr[i + 2] != 0) {
                    z = false;
                }
                history.off = z;
                int i3 = 0;
                while (i2 < bArr[i + 3]) {
                    i3 |= i2 << bArr[(i + 4) + i2];
                    i2++;
                }
                history.portState = i3;
            } else {
                history.hum = (char) (((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255));
                if (b2 >= 3) {
                    history.vpd = ByteUtils.getShort(bArr, i + 4);
                    int i4 = i + 6;
                    history.fan = (byte) ByteUtils.getBits(bArr[i4], 0, 4);
                    if (ByteUtils.getBits(bArr[i4], 4, 4) != 0) {
                        z = false;
                    }
                    history.off = z;
                    long j2 = 0;
                    int i5 = 0;
                    for (int i6 = 0; i6 < bArr[i + 7]; i6++) {
                        byte b3 = bArr[i + 8 + i6];
                        j2 |= ((long) ByteUtils.getBits(b3, 0, 4)) << (i6 * 4);
                        i5 |= ByteUtils.getBits(b3, 4, 4) << i6;
                    }
                    history.portState = i5;
                    history.portFan = j2;
                } else {
                    if (bArr[i + 4] != 0) {
                        z = false;
                    }
                    history.off = z;
                    int i7 = 0;
                    while (i2 < bArr[i + 5]) {
                        i7 |= bArr[(i + 6) + i2] << i2;
                        i2++;
                    }
                    history.portState = i7;
                }
            }
        }
        return history;
    }

    protected static byte[] check(byte[] bArr) {
        int length = (bArr.length - 10) - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 10, bArr2, 0, length);
        return bArr2;
    }

    public static boolean checkResult(byte[] bArr) {
        int length = (bArr.length - 10) - 4;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 10, bArr2, 0, length);
        for (int i = 1; i < length; i += 2) {
            if (bArr2[i] == 1) {
                return false;
            }
        }
        return true;
    }
}
