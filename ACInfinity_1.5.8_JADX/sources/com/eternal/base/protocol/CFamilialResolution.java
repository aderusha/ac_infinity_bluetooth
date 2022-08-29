package com.eternal.base.protocol;

import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.HistoryInfo;
import com.eternal.base.database.entity.History;
import com.eternal.base.database.entity.Log;
import com.eternal.base.utils.ByteUtils;
import com.eternal.data.DataFragment;
import com.eternal.framework.utils.KLog;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CFamilialResolution extends BaseProtocol {
    public static byte[] getSettingData(int i) {
        return addHead(new byte[]{32, 36}, (byte) 1, i);
    }

    public static byte[] setResetDeviceToFactory(int i) {
        return addHead(new byte[]{61}, (byte) 3, i);
    }

    public static DeviceModel parseInfo(byte[] bArr) {
        DeviceModel deviceModel = new DeviceModel();
        byte b = bArr[17];
        deviceModel.isDegree = !ByteUtils.getBit(b, 1);
        deviceModel.alert = (byte) ByteUtils.getBits(b, 2, 2);
        deviceModel.tmpState = (byte) ByteUtils.getBits(b, 4, 2);
        deviceModel.humState = (byte) ByteUtils.getBits(b, 6, 2);
        deviceModel.tmp = ByteUtils.getShort(bArr, 19);
        if (deviceModel.tmp != -32768) {
            deviceModel.tmp *= 10;
        }
        deviceModel.hum = (bArr[21] & 255) * DataFragment.TAG_FILTER_TEMPERATURE;
        byte b2 = bArr[18];
        deviceModel.autoHighTmpTrigger = !ByteUtils.getBit(b2, 0);
        deviceModel.autoLowTmpTrigger = !ByteUtils.getBit(b2, 1);
        deviceModel.autoHighHumTrigger = !ByteUtils.getBit(b2, 2);
        deviceModel.autoLowHumTrigger = !ByteUtils.getBit(b2, 3);
        deviceModel.autoHighTmpSwitch = !ByteUtils.getBit(b2, 4);
        deviceModel.autoLowTmpSwitch = !ByteUtils.getBit(b2, 5);
        deviceModel.autoHighHumSwitch = !ByteUtils.getBit(b2, 6);
        deviceModel.autoLowHumSwitch = !ByteUtils.getBit(b2, 7);
        if (deviceModel.isDegree) {
            deviceModel.autoHighTmp = bArr[24];
            deviceModel.autoLowTmp = bArr[25];
        } else {
            deviceModel.autoHighTmp = bArr[22];
            deviceModel.autoLowTmp = bArr[23];
        }
        deviceModel.autoHighHum = bArr[26];
        deviceModel.autoLowHum = bArr[27];
        deviceModel.time = (long) (((bArr[28] & 255) << Ascii.DLE) + ((bArr[29] & 255) << 8) + (bArr[30] & 255));
        return deviceModel;
    }

    public static DeviceSetting parseSetting(byte[] bArr) {
        DeviceSetting deviceSetting = new DeviceSetting();
        byte[] check = check(bArr);
        if (check[0] == 32) {
            deviceSetting.isDegree = !ByteUtils.getBit(check[2], 7);
            if (check[3] == 36) {
                if (deviceSetting.isDegree) {
                    deviceSetting.calibrationTemperature = check[6];
                } else {
                    deviceSetting.calibrationTemperature = check[5];
                }
                deviceSetting.calibrationHumidity = check[7];
                return deviceSetting;
            }
        }
        return null;
    }

    public static byte[] getModelAlarmData(int i) {
        return addHead(new byte[]{2, 32, Ascii.f270EM}, (byte) 1, i);
    }

    public static byte[] setSettingData(DeviceSetting deviceSetting, int i) {
        byte[] bArr = new byte[8];
        bArr[0] = 32;
        bArr[1] = 1;
        bArr[2] = deviceSetting.isDegree ? (byte) 1 : 0;
        bArr[3] = 36;
        bArr[4] = 3;
        bArr[7] = deviceSetting.calibrationHumidity;
        if (deviceSetting.isDegree) {
            bArr[5] = (byte) Math.round(ProtocolTransformer.getFah((float) deviceSetting.calibrationTemperature));
            bArr[6] = deviceSetting.calibrationTemperature;
        } else {
            bArr[5] = deviceSetting.calibrationTemperature;
            bArr[6] = (byte) Math.round(ProtocolTransformer.getDegree((float) deviceSetting.calibrationTemperature));
        }
        return addHead(bArr, (byte) 3, i);
    }

    public static byte[] setModelData(DeviceModel deviceModel, int i) {
        DeviceModel deviceModel2 = deviceModel;
        return addHead(deviceModel2.isDegree ? new byte[]{50, 11, 0, 0, ProtocolTransformer.getTmpHumSwitch(deviceModel), (byte) Math.round(ProtocolTransformer.getFah((float) deviceModel2.autoHighTmp)), (byte) Math.round(ProtocolTransformer.getFah((float) deviceModel2.autoLowTmp)), deviceModel2.autoHighTmp, deviceModel2.autoLowTmp, deviceModel2.autoHighHum, deviceModel2.autoLowHum, 0, 0} : new byte[]{50, 11, 0, 0, ProtocolTransformer.getTmpHumSwitch(deviceModel), deviceModel2.autoHighTmp, deviceModel2.autoLowTmp, (byte) Math.round(ProtocolTransformer.getDegree((float) UnsignedBytes.toInt(deviceModel2.autoHighTmp))), (byte) Math.round(ProtocolTransformer.getDegree((float) UnsignedBytes.toInt(deviceModel2.autoLowTmp))), deviceModel2.autoHighHum, deviceModel2.autoLowHum, 0, 0}, (byte) 3, i);
    }

    public static byte[] getInfo(int i) {
        return addHead(new byte[]{0}, (byte) 1, i);
    }

    public static byte[] setTime(int i) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(simpleDateFormat.format(simpleDateFormat.parse("2020-1-1 00:00:00")));
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        if (date2 != null) {
            instance.setTime(date2);
        }
        long timeInMillis = instance.getTimeInMillis();
        int i2 = instance.get(16);
        if (date != null) {
            instance.setTime(date);
        }
        long timeInMillis2 = instance.getTimeInMillis();
        long j = ((((timeInMillis2 - timeInMillis) - ((long) i2)) + ((long) instance.get(16))) / 1000) / 60;
        KLog.m62d("时间间隔" + j + "--now==" + timeInMillis2 + "---2--" + timeInMillis);
        return addHead(new byte[]{1, 3, (byte) ((int) (j >> 16)), (byte) ((int) (j >> 8)), (byte) ((int) j)}, (byte) 3, i);
    }

    public static byte[] getHistory(int i, int i2, int i3) {
        return addHead(new byte[]{(byte) (i >> 16), (byte) (i >> 8), (byte) i, (byte) i2}, (byte) 20, i3);
    }

    public static int parseLogSize(byte[] bArr) {
        byte[] check = check(bArr);
        if (check[0] == 0) {
            return ((check[10] & 255) << 8) + (check[11] & 255);
        }
        return 0;
    }

    public static List<HistoryInfo> parseHistoryInfo(byte[] bArr) {
        byte[] check = check(bArr);
        ArrayList arrayList = new ArrayList();
        if (check[0] == 0) {
            int i = 12;
            while (i < check.length) {
                HistoryInfo historyInfo = new HistoryInfo();
                historyInfo.startId = ((check[i] & 255) << Ascii.DLE) + ((check[i + 1] & 255) << 8) + (check[i + 2] & 255);
                int i2 = i + 3;
                historyInfo.calibrated = ((check[i2] & 255) << Ascii.DLE) + ((check[i2 + 1] & 255) << 8) + (check[i2 + 2] & 255);
                int i3 = i2 + 3;
                historyInfo.length = ((check[i3] & 255) << Ascii.DLE) + ((check[i3 + 1] & 255) << 8) + (check[i3 + 2] & 255);
                arrayList.add(historyInfo);
                i = i3 + 3;
            }
        }
        int size = arrayList.size() - 1;
        HistoryInfo historyInfo2 = (HistoryInfo) arrayList.get(size);
        if (historyInfo2.calibrated == 16777215) {
            historyInfo2.calibrated = (int) ((((System.currentTimeMillis() / 1000) - 1577808000) / 60) - ((long) historyInfo2.length));
        }
        int i4 = historyInfo2.calibrated;
        for (int i5 = size - 1; i5 >= 0; i5--) {
            HistoryInfo historyInfo3 = (HistoryInfo) arrayList.get(i5);
            if (historyInfo3.calibrated == 16777215) {
                i4 -= historyInfo3.length + 1;
                historyInfo3.calibrated = i4;
            } else {
                i4 = historyInfo3.calibrated;
            }
        }
        return arrayList;
    }

    public static byte[] getLogId(long j, int i) {
        return addHead(new byte[]{(byte) ((int) (j >> 16)), (byte) ((int) (j >> 8)), (byte) ((int) j)}, (byte) 19, i);
    }

    public static int parseLogId(byte[] bArr) {
        byte[] check = check(bArr);
        if (check.length == 3) {
            return ((check[0] & 255) << Ascii.DLE) + ((check[1] & 255) << 8) + (check[2] & 255);
        }
        return 0;
    }

    public static byte[] getLog(int i, int i2, int i3) {
        return addHead(new byte[]{(byte) (i >> 16), (byte) (i >> 8), (byte) i, (byte) i2}, (byte) 18, i3);
    }

    public static Log parseLog(byte[] bArr, int i) {
        Log log = new Log();
        if (bArr[i] == -1 && bArr[i + 1] == -1 && bArr[i + 2] == -1) {
            return log;
        }
        log.time = (long) (((bArr[i] & 255) << Ascii.DLE) + ((bArr[i + 1] & 255) << 8) + (bArr[i + 2] & 255));
        log.model = (byte) ((bArr[i + 3] & 240) >> 4);
        switch (log.model) {
            case 1:
                log.hTmpF = bArr[i + 4];
                break;
            case 2:
                log.lTmpF = bArr[i + 5];
                break;
            case 3:
                log.hTmpF = bArr[i + 4];
                log.lTmpF = bArr[i + 5];
                break;
            case 4:
                log.hHum = bArr[i + 4];
                break;
            case 5:
                log.lHum = bArr[i + 5];
                break;
            case 6:
                log.hHum = bArr[i + 4];
                log.lHum = bArr[i + 5];
                break;
        }
        return log;
    }

    public static History parseHistory(byte[] bArr, int i, int i2) {
        History history = new History();
        history.time = (long) i2;
        history.off = false;
        history.tmp = ByteUtils.getShort(bArr, i) * 10;
        history.hum = (char) ((((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255)) * 10);
        return history;
    }
}
