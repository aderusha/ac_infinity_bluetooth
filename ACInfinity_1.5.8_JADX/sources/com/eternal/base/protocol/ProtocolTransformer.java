package com.eternal.base.protocol;

import android.text.TextUtils;
import com.alibaba.android.arouter.utils.Consts;
import com.eternal.base.C1323R;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.NetMessageInfo;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.NotificationMessage;
import com.eternal.base.utils.ByteUtils;
import com.eternal.base.utils.GuQiangUtil;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.StringUtils;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;

public class ProtocolTransformer {
    public static final String DEGREE = "℃";
    public static final String FAH = "℉";

    public static boolean checkTmpAndHum(int i, int i2) {
        float f = ((float) i2) / 100.0f;
        return f <= 100.0f && f >= 0.0f && i != -32768;
    }

    public static boolean checkTmpHum(byte b) {
        return (b & 15) != 0;
    }

    public static boolean checkVpd(byte b) {
        return (b & 48) != 0;
    }

    public static String getModeString(byte b) {
        switch (b) {
            case 1:
                return "OFF";
            case 2:
                return "ON";
            case 3:
                return "AUTO";
            case 4:
                return "TIMER ON";
            case 5:
                return "TIMER OFF";
            case 6:
                return "CYCLE";
            case 7:
                return "SCHED";
            case 8:
                return "VPD";
            case 9:
                return "TEMPERATURE PARAM";
            case 10:
                return "HUMIDITY PARAM";
            case 11:
                return "ADVANCE";
            case 12:
                return "AI";
            default:
                return "";
        }
    }

    public static int getPortTypeByResistance(int i) {
        if (i >= 380 && i <= 420) {
            return 3;
        }
        if (i >= 3135 && i <= 3465) {
            return 3;
        }
        if (i >= 4845 && i <= 10500) {
            return 2;
        }
        if (i >= 11400 && i <= 12600) {
            return 6;
        }
        if (i >= 13015 && i <= 14385) {
            return 7;
        }
        if (i >= 15010 && i <= 16590) {
            return 4;
        }
        if (i < 17100 || i > 18900) {
            return (i < 19475 || i > 21525) ? 0 : 5;
        }
        return 8;
    }

    public static String getType(int i) {
        switch (i) {
            case 2:
                return "B";
            case 3:
            case 4:
            case 5:
            case 14:
            case 15:
                return "C";
            case 6:
                return "D";
            case 7:
            case 8:
                return "E";
            case 9:
            case 12:
                return "F";
            case 11:
                return "G";
            default:
                return "A";
        }
    }

    public static boolean isDeviceC(int i) {
        return i == 4 || i == 5 || i == 14 || i == 15;
    }

    public static boolean isDeviceMultiPort(int i) {
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOutletDevice(int i) {
        return i == 2 || i == 9 || i == 12;
    }

    public static boolean isWorkWiFi(int i, byte b) {
        if (i == 8) {
            return true;
        }
        return i == 11 && b != 0;
    }

    public static byte setLogVpd(boolean z, boolean z2, boolean z3, boolean z4) {
        byte b = z4 ? (byte) 1 : 0;
        if (z3) {
            b = (byte) (b | 2);
        }
        if (z2) {
            b = (byte) (b | 4);
        }
        return z ? (byte) (b | 8) : b;
    }

    public static byte setModelType(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        byte b = z6 ? (byte) 1 : 0;
        if (z5) {
            b = (byte) (b | 2);
        }
        if (z3) {
            b = (byte) (b | 4);
        }
        if (z4) {
            b = (byte) (b | 8);
        }
        if (z2) {
            b = (byte) (b | Ascii.DLE);
        }
        if (z) {
            b = (byte) (b | 32);
        }
        if (z7) {
            b = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
        }
        return z8 ? (byte) (b | 128) : b;
    }

    public static byte setTmpHum(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        byte b = z8 ? (byte) 1 : 0;
        if (z7) {
            b = (byte) (b | 2);
        }
        if (z6) {
            b = (byte) (b | 4);
        }
        if (z5) {
            b = (byte) (b | 8);
        }
        if (z4) {
            b = (byte) (b | Ascii.DLE);
        }
        if (z3) {
            b = (byte) (b | 32);
        }
        if (z2) {
            b = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
        }
        return z ? (byte) (b | 128) : b;
    }

    public static byte setVpd(boolean z, boolean z2) {
        byte b = z2 ? (byte) 1 : 0;
        return z ? (byte) (b | 2) : b;
    }

    public static byte setVpd(boolean z, boolean z2, boolean z3, boolean z4) {
        byte b = z4 ? (byte) 1 : 0;
        if (z3) {
            b = (byte) (b | 2);
        }
        if (z2) {
            b = (byte) (b | 4);
        }
        return z ? (byte) (b | 8) : b;
    }

    public static byte setVpd(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        byte b = z4 ? (byte) 1 : 0;
        if (z3) {
            b = (byte) (b | 2);
        }
        if (z2) {
            b = (byte) (b | 4);
        }
        if (z) {
            b = (byte) (b | 8);
        }
        if (z6) {
            b = (byte) (b | Ascii.DLE);
        }
        return z5 ? (byte) (b | 32) : b;
    }

    public static int getPlugIcon(int i, int i2, boolean z, byte b) {
        int portTypeByResistance = getPortTypeByResistance(i2);
        boolean z2 = (portTypeByResistance == 4 && b == 11) || isOutletDevice(b);
        if (i == 0) {
            i = portTypeByResistance;
        }
        return i == 3 ? z2 ? z ? C1323R.mipmap.light_nor_small : C1323R.mipmap.light_dis_small : z ? C1323R.mipmap.light_nor_small_circle : C1323R.mipmap.light_dis_small_circle : i == 2 ? z2 ? z ? C1323R.mipmap.fan_nor_small : C1323R.mipmap.fan_dis_small : z ? C1323R.mipmap.fan_nor_small_circle : C1323R.mipmap.fan_dis_small_circle : i == 6 ? z2 ? z ? C1323R.mipmap.hum_nor_small : C1323R.mipmap.hum_dis_small : z ? C1323R.mipmap.hum_nor_small_circle : C1323R.mipmap.hum_dis_small_circle : i == 7 ? z2 ? z ? C1323R.mipmap.dehum_nor_small : C1323R.mipmap.dehum_dis_small : z ? C1323R.mipmap.dehum_nor_small_circle : C1323R.mipmap.dehum_dis_small_circle : i == 4 ? z2 ? z ? C1323R.mipmap.plug_nor_small : C1323R.mipmap.plug_dis_small : z ? C1323R.mipmap.plug_nor_small_circle : C1323R.mipmap.plug_dis_small_circle : i == 8 ? z2 ? z ? C1323R.mipmap.heater_nor_small : C1323R.mipmap.heater_dis_small : z ? C1323R.mipmap.heater_nor_small_circle : C1323R.mipmap.heater_dis_small_circle : i == 5 ? z2 ? z ? C1323R.mipmap.ac_nor_small : C1323R.mipmap.ac_dis_small : z ? C1323R.mipmap.ac_nor_small_circle : C1323R.mipmap.ac_dis_small_circle : z2 ? z ? C1323R.mipmap.plug_nor_small : C1323R.mipmap.plug_dis_small : z ? C1323R.mipmap.default_nor_small_circle : C1323R.mipmap.default_dis_small_circle;
    }

    public static int getPortType(int i, int i2) {
        return i == 0 ? getPortTypeByResistance(i2) : i;
    }

    public static boolean isOutletDevice(int i, int i2) {
        if (isOutletDevice(i)) {
            return true;
        }
        if (i == 11 && getPortTypeByResistance(i2) == 4) {
            return true;
        }
        return false;
    }

    public static boolean isSupportFirmwareUpdate(String str, byte b) {
        if (!TextUtils.isEmpty(str)) {
            if (isDeviceC(b) || b == 6 || b == 2 || b == 8 || b == 11 || b == 9 || b == 12) {
                return true;
            }
            if (b != 7 || !RegexUtils.isDeviceSoftwareVersion(str) || StringUtils.compareVersion(str, "1.0.9") < 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static String getDisplayPortName(byte b, byte b2, String str) {
        if (!isDeviceMultiPort(b2) || b == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((b2 == 9 || b2 == 12) ? "Outlet " : "Port ");
        sb.append(b);
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(str) || sb2.equals(str)) {
            return sb2;
        }
        return String.format(Locale.ENGLISH, "%d: %s", new Object[]{Byte.valueOf(b), str}).replace(" ", " ");
    }

    public static String getLogPortName(byte b, byte b2, String str) {
        if (!isDeviceMultiPort(b2)) {
            return "";
        }
        if (b == 0) {
            return "All - ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((b2 == 9 || b2 == 12) ? "Outlet " : "Port ");
        sb.append(b);
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(str) && !sb2.equals(str)) {
            sb2 = b + ": " + str;
        }
        return sb2 + " - ";
    }

    public static String getString4Degree(int i, boolean z, DecimalFormat decimalFormat) {
        if (i == -32768) {
            return "--";
        }
        if (z) {
            float f = ((float) i) / 100.0f;
            int round = Math.round(f);
            if (round < -19 || round > 100) {
                return "--";
            }
            if (round < -9) {
                return "LO";
            }
            if (round > 90) {
                return "HI";
            }
            return decimalFormat == null ? String.valueOf(round) : decimalFormat.format((double) f);
        }
        float f2 = (((((float) i) / 100.0f) * 9.0f) / 5.0f) + 32.0f;
        int round2 = Math.round(f2);
        if (round2 <= 0 || round2 >= 212) {
            return "--";
        }
        if (round2 < 15) {
            return "LO";
        }
        if (round2 > 194) {
            return "HI";
        }
        return decimalFormat == null ? String.valueOf(round2) : decimalFormat.format((double) f2);
    }

    public static int getC_Tmp(int i) {
        if (i == -32768) {
            return 0;
        }
        return Math.round(((float) i) / 100.0f);
    }

    public static String getC_TmpString(int i, boolean z) {
        return getC_TmpString(i, z, (DecimalFormat) null);
    }

    public static String getC_TmpString(int i, boolean z, DecimalFormat decimalFormat) {
        if (i == -32768) {
            return "--";
        }
        float f = ((float) i) / 100.0f;
        int round = Math.round(f);
        if (z) {
            if (round < -19 || round > 100) {
                return "--";
            }
            if (round < -9) {
                return "LO";
            }
            if (round > 90) {
                return "HI";
            }
        } else if (round < 0 || round > 212) {
            return "--";
        } else {
            if (round < 15) {
                return "LO";
            }
            if (round > 194) {
                return "HI";
            }
        }
        return decimalFormat == null ? String.valueOf(Math.round(f)) : decimalFormat.format((double) f);
    }

    public static String getVPDString(int i, DecimalFormat decimalFormat) {
        if (i == -32768) {
            return "--";
        }
        if (i < 0) {
            i = 0;
        }
        float f = ((float) i) / 100.0f;
        return decimalFormat == null ? String.valueOf(f) : decimalFormat.format((double) f);
    }

    public static String getVPDString(int i, int i2, int i3, boolean z, DecimalFormat decimalFormat) {
        if (!checkTmpAndHum(i, i2)) {
            return "--";
        }
        float vpd = getVPD(i, i2, i3, z);
        return decimalFormat == null ? String.valueOf(vpd) : decimalFormat.format((double) vpd);
    }

    public static float getVPD(int i, int i2, int i3, boolean z) {
        return getVPD(i, i2, i3, z, true);
    }

    public static float getVPD(int i, int i2, int i3, boolean z, boolean z2) {
        if (!checkTmpAndHum(i, i2)) {
            return 0.0f;
        }
        float f = ((float) i2) / 100.0f;
        float f2 = ((float) i) / 100.0f;
        if (!z) {
            f2 = getDegree(f2);
        }
        double d = (double) f2;
        double d2 = (double) (((float) i3) + f2);
        double exp = (Math.exp((17.2694d * d2) / (d2 + 238.3d)) * 0.61078d) - (((Math.exp((d * 17.2694d) / (d + 238.3d)) * 0.61078d) * ((double) f)) / 100.0d);
        if (z2 && exp < 0.0d) {
            exp = 0.0d;
        }
        return (float) exp;
    }

    public static float getFah(float f) {
        return ((float) Math.round((((f * 9.0f) / 5.0f) + 32.0f) * 100.0f)) / 100.0f;
    }

    public static float getDegree(float f) {
        return ((float) Math.round((((f - 32.0f) * 5.0f) / 9.0f) * 100.0f)) / 100.0f;
    }

    public static int getTmp(int i, boolean z) {
        if (i == -32768) {
            return 0;
        }
        if (z) {
            return Math.round(((float) i) / 100.0f);
        }
        return Math.round((((((float) i) / 100.0f) * 9.0f) / 5.0f) + 32.0f);
    }

    public static String getPer(int i, DecimalFormat decimalFormat) {
        float f;
        int round;
        if (i != -32768 && (round = Math.round(f)) <= 100 && round >= 0) {
            return decimalFormat == null ? String.valueOf(round) : decimalFormat.format((double) (f = ((float) i) / 100.0f));
        }
        return "--";
    }

    public static String getPer(int i) {
        return getPer(i, (DecimalFormat) null);
    }

    public static int getHum(int i) {
        if (i == -32768) {
            return 0;
        }
        int round = Math.round(((float) i) / 100.0f);
        if (round > 100) {
            return 100;
        }
        if (round < 0) {
            return 0;
        }
        return round;
    }

    public static byte getTmpHumSwitch(DeviceModel deviceModel) {
        byte b = deviceModel.autoHighTmpSwitch ? (byte) 8 : 0;
        if (deviceModel.autoLowTmpSwitch) {
            b = (byte) (b | 4);
        }
        if (deviceModel.autoHighHumSwitch) {
            b = (byte) (b | 2);
        }
        return deviceModel.autoLowHumSwitch ? (byte) (b | 1) : b;
    }

    public static byte getTmpSwitch(DeviceModel deviceModel) {
        byte b = deviceModel.autoHighTmpSwitch ? (byte) 8 : 0;
        return deviceModel.autoLowTmpSwitch ? (byte) (b | 4) : b;
    }

    public static byte getVpdSwitch(DeviceModel deviceModel) {
        byte b = deviceModel.highVpdSwitch ? (byte) 2 : 0;
        return deviceModel.lowVpdSwitch ? (byte) (b | 1) : b;
    }

    public static String getTime(long j) {
        return new SimpleDateFormat("MMM dd, yyyy, h:mm aa", Locale.ENGLISH).format(new Date(j)).toUpperCase();
    }

    public static String getTime(long j, String str) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm aa", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str));
        return simpleDateFormat.format(date).toUpperCase();
    }

    public static String getTimeWithAMPM(long j) {
        return new SimpleDateFormat("h:mm aa", Locale.ENGLISH).format(new Date(j)).toUpperCase();
    }

    public static boolean vpdModel(byte b) {
        return !ByteUtils.getBit(b, 0);
    }

    public static boolean vpdParam(byte b) {
        return !ByteUtils.getBit(b, 1);
    }

    public static boolean vpdParamV4(byte b) {
        return !ByteUtils.getBit(b, 4);
    }

    public static boolean scheduleModel(byte b) {
        return !ByteUtils.getBit(b, 2);
    }

    public static boolean cycleModel(byte b) {
        return !ByteUtils.getBit(b, 3);
    }

    public static boolean offModel(byte b) {
        return !ByteUtils.getBit(b, 4);
    }

    public static boolean onModel(byte b) {
        return !ByteUtils.getBit(b, 5);
    }

    public static boolean paramModel(byte b) {
        return !ByteUtils.getBit(b, 7);
    }

    public static boolean tempParamModelV4(byte b) {
        return !ByteUtils.getBit(b, 2);
    }

    public static boolean humParamModelV4(byte b) {
        return !ByteUtils.getBit(b, 3);
    }

    public static boolean autoModel(byte b) {
        return !ByteUtils.getBit(b, 6);
    }

    public static boolean allModel(byte b) {
        return autoModel(b) && onModel(b) && offModel(b) && cycleModel(b) && scheduleModel(b);
    }

    public static boolean isModelV4(byte b) {
        return !ByteUtils.getBit(b, 5);
    }

    public static boolean isModel(byte b) {
        return autoModel(b) || onModel(b) || offModel(b) || cycleModel(b) || scheduleModel(b) || vpdModel(b);
    }

    public static String getLogCString(Log log, boolean z) {
        StringBuilder sb = new StringBuilder();
        switch (log.model) {
            case 1:
                sb.append("Temperature Alarm (HIGH ");
                int i = UnsignedBytes.toInt(log.hTmpF);
                if (z) {
                    i = Math.round(getDegree((float) i));
                }
                sb.append(i);
                sb.append("°) activated");
                break;
            case 2:
                sb.append("Temperature Alarm (LOW ");
                int i2 = UnsignedBytes.toInt(log.lTmpF);
                if (z) {
                    i2 = Math.round(getDegree((float) i2));
                }
                sb.append(i2);
                sb.append("°) activated");
                break;
            case 3:
                sb.append("Temperature Alarm (HIGH ");
                int i3 = UnsignedBytes.toInt(log.hTmpF);
                if (z) {
                    i3 = Math.round(getDegree((float) i3));
                }
                sb.append(i3);
                sb.append("°,LOW ");
                int i4 = UnsignedBytes.toInt(log.lTmpF);
                if (z) {
                    i4 = Math.round(getDegree((float) i4));
                }
                sb.append(i4);
                sb.append("°) activated");
                break;
            case 4:
                sb.append("Humidity Alarm (HIGH ");
                sb.append(log.hHum);
                sb.append("%) activated");
                break;
            case 5:
                sb.append("Humidity Alarm (LOW ");
                sb.append(log.lHum);
                sb.append("%) activated");
                break;
            case 6:
                sb.append("Humidity Alarm (HIGH ");
                sb.append(log.hHum);
                sb.append("%,LOW ");
                sb.append(log.lHum);
                sb.append("%) activated");
                break;
            default:
                KLog.m65e("unknown c log type:" + log.model);
                break;
        }
        sb.append(Consts.DOT);
        return sb.toString();
    }

    public static String getLogString(Log log, String str, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        byte b = log.logType;
        if (b == 0) {
            initLog(sb, log, z);
        } else if (b != 1) {
            sb.append("ALERTS ");
            if (str != null) {
                sb.append("\"");
                sb.append(str);
                sb.append("\" ");
            }
            initNotify(sb, log, z);
        } else {
            sb.append("AUTOMATION ");
            if (str != null) {
                sb.append("\"");
                sb.append(str);
                sb.append("\" ");
            }
            initAuto(sb, log, z, z2);
        }
        sb.append(Consts.DOT);
        return sb.toString();
    }

    public static String getNotifyString(DeviceModel deviceModel, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("ALARM:");
        sb.append(str);
        if (deviceModel.autoHighTmpTrigger || deviceModel.autoLowTmpTrigger) {
            sb.append(" Temperature Alarm (");
        } else {
            sb.append(" Humidity Alarm (");
        }
        if (initTmpHum(sb, deviceModel)) {
            sb.append(")");
        }
        sb.append(" has been reached");
        sb.append(Consts.DOT);
        return sb.toString();
    }

    public static String getNotifyString(NotificationMessage notificationMessage, String str, byte b, boolean z) {
        NotificationMessage notificationMessage2 = notificationMessage;
        boolean z2 = z;
        byte[] bArr = notificationMessage2.values;
        StringBuilder sb = new StringBuilder();
        sb.append("ALERTS:\"");
        sb.append(str);
        sb.append("\"");
        String str2 = "ON";
        switch (notificationMessage2.notifyType) {
            case 2:
                sb.append(" AUTO Mode (");
                if (initTmpHum(sb, bArr, z2)) {
                    sb.append(")");
                }
                sb.append(" has set device to ");
                if (bArr[7] != 1) {
                    str2 = "OFF";
                }
                sb.append(str2);
                break;
            case 3:
                sb.append(" TIMER TO ON Mode (");
                sb.append(GuQiangUtil.stringForTimeByCycleSpace(((((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255)) / 60));
                sb.append(") has set device to ");
                if (bArr[4] != 1) {
                    str2 = "OFF";
                }
                sb.append(str2);
                break;
            case 4:
                sb.append(" TIMER TO OFF Mode (");
                sb.append(GuQiangUtil.stringForTimeByCycleSpace(((((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255)) / 60));
                sb.append(") has set device to ");
                if (bArr[4] != 1) {
                    str2 = "OFF";
                }
                sb.append(str2);
                break;
            case 5:
                sb.append(" CYCLE Mode (");
                if (bArr[8] == 0) {
                    sb.append("ON ");
                    sb.append(GuQiangUtil.stringForTimeByCycleSpace(((((bArr[2] & 255) << 8) | ((bArr[1] & 255) << Ascii.DLE)) | (bArr[3] & 255)) / 60));
                    sb.append(") has set device to ");
                } else {
                    sb.append("OFF ");
                    sb.append(GuQiangUtil.stringForTimeByCycleSpace(((((bArr[5] & 255) << Ascii.DLE) | ((bArr[6] & 255) << 8)) | (bArr[7] & 255)) / 60));
                    sb.append(") has set device to ");
                }
                if (bArr[9] == 0) {
                    str2 = "OFF";
                }
                sb.append(str2);
                break;
            case 6:
                sb.append(" SCHEDULE Mode (");
                if (bArr[4] != 0) {
                    sb.append("ON ");
                    sb.append(GuQiangUtil.stringForTimeBySched((bArr[0] * 60) + bArr[1]));
                } else {
                    sb.append("OFF ");
                    sb.append(GuQiangUtil.stringForTimeBySched((bArr[2] * 60) + bArr[3]));
                }
                sb.append(") has set device to ");
                if (bArr[5] != 1) {
                    str2 = "OFF";
                }
                sb.append(str2);
                break;
            case 7:
                sb.append(" VPD Mode (");
                initVpd(sb, bArr);
                sb.append(") has set device to ");
                if (bArr[3] != 1) {
                    str2 = "OFF";
                }
                sb.append(str2);
                break;
            case 8:
                sb.append(" PARAMETER Point (");
                initVpd(sb, bArr);
                sb.append(") has been reached");
                break;
            default:
                sb.append(" PARAMETER Point (");
                if (initTmpHum(sb, bArr, z2)) {
                    sb.append(")");
                }
                sb.append(" has been reached");
                break;
        }
        sb.append(Consts.DOT);
        return sb.toString();
    }

    public static String getNotifyString(NetMessageInfo netMessageInfo, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("ALERTS:\"");
        sb.append(netMessageInfo.notificationName);
        sb.append("\"");
        String str = "ON";
        switch (netMessageInfo.currentMode) {
            case 2:
                sb.append(" AUTO Mode (");
                if (initTmpHum(sb, netMessageInfo, z)) {
                    sb.append(")");
                }
                sb.append(" has set device to ");
                if (netMessageInfo.currentStatus != 1) {
                    str = "OFF";
                }
                sb.append(str);
                break;
            case 3:
                sb.append(" TIMER TO ON Mode (");
                sb.append(GuQiangUtil.stringForTimeByCycleSpace(netMessageInfo.timerToOn / 60));
                sb.append(") has set device to ");
                if (netMessageInfo.currentStatus != 1) {
                    str = "OFF";
                }
                sb.append(str);
                break;
            case 4:
                sb.append(" TIMER TO OFF Mode (");
                sb.append(GuQiangUtil.stringForTimeByCycleSpace(netMessageInfo.timerToOff / 60));
                sb.append(") has set device to ");
                if (netMessageInfo.currentStatus != 1) {
                    str = "OFF";
                }
                sb.append(str);
                break;
            case 5:
                sb.append(" CYCLE Mode (");
                if (netMessageInfo.isCycleOn) {
                    sb.append("OFF ");
                    sb.append(GuQiangUtil.stringForTimeByCycleSpace(netMessageInfo.cycleOff / 60));
                    sb.append(") has set device to ");
                } else {
                    sb.append("ON ");
                    sb.append(GuQiangUtil.stringForTimeByCycleSpace(netMessageInfo.cycleOn / 60));
                    sb.append(") has set device to ");
                }
                if (netMessageInfo.currentStatus != 1) {
                    str = "OFF";
                }
                sb.append(str);
                break;
            case 6:
                sb.append(" SCHEDULE Mode (");
                if (netMessageInfo.isSchedOn) {
                    sb.append("ON ");
                    sb.append(GuQiangUtil.stringForTimeBySched(netMessageInfo.schedOn));
                } else {
                    sb.append("OFF ");
                    sb.append(GuQiangUtil.stringForTimeBySched(netMessageInfo.schedOff));
                }
                sb.append(") has set device to ");
                if (netMessageInfo.currentStatus != 1) {
                    str = "OFF";
                }
                sb.append(str);
                break;
            case 7:
                sb.append(" VPD Mode (");
                if (initVpd(sb, netMessageInfo)) {
                    sb.append(")");
                }
                sb.append(" has set device to ");
                if (netMessageInfo.currentStatus != 1) {
                    str = "OFF";
                }
                sb.append(str);
                break;
            case 8:
                sb.append(" PARAMETER Point (");
                if (initVpd(sb, netMessageInfo)) {
                    sb.append(")");
                }
                sb.append(" has been reached");
                break;
            default:
                sb.append(" PARAMETER Point (");
                if (initTmpHum(sb, netMessageInfo, z)) {
                    sb.append(")");
                }
                sb.append(" has been reached");
                break;
        }
        sb.append(Consts.DOT);
        return sb.toString();
    }

    private static void initLog(StringBuilder sb, Log log, boolean z) {
        String str = "ON";
        switch (log.model) {
            case 1:
                sb.append("OFF Mode");
                return;
            case 2:
                sb.append("ON Mode");
                return;
            case 3:
                sb.append("AUTO Mode (");
                if (initTmpHum(sb, log, z)) {
                    sb.append(")");
                }
                sb.append(" set device to ");
                if (log.fan != 1) {
                    str = "OFF";
                }
                sb.append(str);
                return;
            case 4:
                sb.append("TIMER TO ON Mode (");
                sb.append(GuQiangUtil.stringForTimeByCycleSpace(log.f143on));
                sb.append(") set device to ");
                if (log.fan != 1) {
                    str = "OFF";
                }
                sb.append(str);
                return;
            case 5:
                sb.append("TIMER TO OFF Mode (");
                sb.append(GuQiangUtil.stringForTimeByCycleSpace(log.off));
                sb.append(") set device to ");
                if (log.fan != 1) {
                    str = "OFF";
                }
                sb.append(str);
                return;
            case 6:
                sb.append("CYCLE Mode (");
                if (!log.isStart) {
                    sb.append("OFF ");
                    sb.append(GuQiangUtil.stringForTimeByCycleSpace(log.off));
                } else {
                    sb.append("ON ");
                    sb.append(GuQiangUtil.stringForTimeByCycleSpace(log.f143on));
                }
                sb.append(") set device to ");
                if (log.fan != 0) {
                    str = "OFF";
                }
                sb.append(str);
                return;
            case 9:
            case 10:
                sb.append("VPD Mode (");
                if (initVpd(sb, log)) {
                    sb.append(")");
                }
                sb.append(" set device to ");
                if (log.fan != 1) {
                    str = "OFF";
                }
                sb.append(str);
                return;
            default:
                sb.append("SCHEDULE Mode (");
                if (!log.isStart) {
                    sb.append("ON ");
                    formatAmPm(sb, log.f143on);
                } else {
                    sb.append("OFF ");
                    formatAmPm(sb, log.off);
                }
                sb.append(") set device to ");
                if (log.fan != 1) {
                    str = "OFF";
                }
                sb.append(str);
                return;
        }
    }

    private static void formatAmPm(StringBuilder sb, char c) {
        boolean z;
        int i = c / Typography.less;
        int i2 = c % Typography.less;
        if (i > 12) {
            i -= 12;
            z = false;
        } else {
            z = true;
        }
        sb.append(i);
        sb.append(":");
        if (i2 < 10) {
            sb.append("0");
        }
        sb.append(i2);
        sb.append(z ? " AM" : " PM");
    }

    private static void initAuto(StringBuilder sb, Log log, boolean z, boolean z2) {
        sb.append("(");
        if (!z2) {
            sb.append(GuQiangUtil.stringForTimeBySched(log.start));
            sb.append(" to ");
            sb.append(GuQiangUtil.stringForTimeBySched(log.end));
            sb.append(", ");
        }
        byte b = log.model;
        if (b == 1) {
            sb.append("OFF) ");
        } else if (b == 2) {
            sb.append("ON) ");
        } else if (b == 6) {
            sb.append("CYCLE ");
            if (log.isStart) {
                sb.append("ON " + GuQiangUtil.stringForTimeByCycleSpace(log.f143on));
            } else {
                sb.append("OFF " + GuQiangUtil.stringForTimeByCycleSpace(log.off));
            }
            sb.append(") ");
        } else if (b == 9) {
            sb.append("VPD ");
            if (log.isStart) {
                sb.append("ON " + GuQiangUtil.stringForTimeByCycleSpace(log.f143on));
            } else {
                sb.append("OFF " + GuQiangUtil.stringForTimeByCycleSpace(log.off));
            }
            sb.append(") ");
        } else if (b != 10) {
            if (initTmpHum(sb, log, z)) {
                sb.append(")");
            }
            sb.append(" ");
        } else {
            if (initVpd(sb, log)) {
                sb.append(")");
            }
            sb.append(" ");
        }
        sb.append("activated");
    }

    private static void initNotify(StringBuilder sb, Log log, boolean z) {
        switch (log.model) {
            case 3:
                sb.append(" (AUTO) ");
                break;
            case 4:
                sb.append(" (TIMER TO ON) ");
                break;
            case 5:
                sb.append(" (TIMER TO OFF) ");
                break;
            case 6:
                sb.append(" (CYCLE) ");
                break;
            case 7:
                sb.append(" (SCHEDULE) ");
                break;
            case 9:
                sb.append(" (VPD) ");
                break;
            case 10:
                sb.append(" (");
                initVpd(sb, log);
                sb.append(")");
                break;
            default:
                sb.append(" (");
                initTmpHum(sb, log, z);
                sb.append(")");
                break;
        }
        sb.append(" activated");
    }

    private static boolean initTmpHum(StringBuilder sb, Log log, boolean z) {
        boolean z2;
        boolean z3 = true;
        if (((log.tmpHum >> 5) & 1) != 0 || (log.fan == 0 && ((log.tmpHum >> 1) & 1) != 0)) {
            sb.append("HIGH ");
            sb.append(log.hHum);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        } else {
            z2 = false;
        }
        if (((log.tmpHum >> 4) & 1) != 0 || (log.fan == 0 && (log.tmpHum & 1) != 0)) {
            sb.append("LOW ");
            sb.append(log.lHum);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        }
        if (((log.tmpHum >> 7) & 1) != 0 || (log.fan == 0 && ((log.tmpHum >> 3) & 1) != 0)) {
            sb.append("HIGH ");
            if (z) {
                sb.append(log.hTmpC);
                sb.append("°");
            } else {
                sb.append(UnsignedBytes.toInt(log.hTmpF));
                sb.append("°");
            }
            sb.append(", ");
            z2 = true;
        }
        if (((log.tmpHum >> 6) & 1) != 0 || (log.fan == 0 && ((log.tmpHum >> 2) & 1) != 0)) {
            sb.append("LOW ");
            if (z) {
                sb.append(log.lTmpC);
                sb.append("°");
            } else {
                sb.append(UnsignedBytes.toInt(log.lTmpF));
                sb.append("°");
            }
            sb.append(", ");
        } else {
            z3 = z2;
        }
        sb.setLength(sb.length() - 2);
        return z3;
    }

    private static boolean initTmp(StringBuilder sb, Log log, boolean z) {
        boolean z2;
        boolean z3 = true;
        if (((log.tmpHum >> 5) & 1) != 0 || (log.fan == 0 && ((log.tmpHum >> 1) & 1) != 0)) {
            sb.append("HIGH ");
            sb.append(log.hHum);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        } else {
            z2 = false;
        }
        if (((log.tmpHum >> 4) & 1) != 0 || (log.fan == 0 && (log.tmpHum & 1) != 0)) {
            sb.append("LOW ");
            sb.append(log.lHum);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        }
        if (((log.tmpHum >> 7) & 1) != 0 || (log.fan == 0 && ((log.tmpHum >> 3) & 1) != 0)) {
            sb.append("HIGH ");
            if (z) {
                sb.append(log.hTmpC);
                sb.append("°");
            } else {
                sb.append(UnsignedBytes.toInt(log.hTmpF));
                sb.append("°");
            }
            sb.append(", ");
            z2 = true;
        }
        if (((log.tmpHum >> 6) & 1) != 0 || (log.fan == 0 && ((log.tmpHum >> 2) & 1) != 0)) {
            sb.append("LOW ");
            if (z) {
                sb.append(log.lTmpC);
                sb.append("°");
            } else {
                sb.append(UnsignedBytes.toInt(log.lTmpF));
                sb.append("°");
            }
            sb.append(", ");
        } else {
            z3 = z2;
        }
        sb.setLength(sb.length() - 2);
        return z3;
    }

    private static boolean initVpd(StringBuilder sb, Log log) {
        boolean z;
        boolean z2 = true;
        if (((log.tmpHum >> 3) & 1) == 0 || ((log.tmpHum >> 1) & 1) == 0) {
            z = false;
        } else {
            sb.append("HIGH ");
            sb.append(String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) log.hVpd) / 10.0f)}));
            sb.append("kPa");
            sb.append(", ");
            z = true;
        }
        if (((log.tmpHum >> 2) & 1) == 0 || (log.tmpHum & 1) == 0) {
            z2 = z;
        } else {
            sb.append("LOW ");
            sb.append(String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) log.lVpd) / 10.0f)}));
            sb.append("kPa");
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return z2;
    }

    private static void initVpd(StringBuilder sb, byte[] bArr) {
        if (((bArr[0] >> 3) & 1) != 0) {
            sb.append("HIGH ");
            sb.append(String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) bArr[1]) / 10.0f)}));
            sb.append("kPa");
            sb.append(", ");
        }
        if (((bArr[0] >> 2) & 1) != 0) {
            sb.append("LOW ");
            sb.append(String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) bArr[2]) / 10.0f)}));
            sb.append("kPa");
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
    }

    private static boolean initTmpHum(StringBuilder sb, byte[] bArr, boolean z) {
        boolean z2;
        boolean z3 = true;
        if (((bArr[0] >> 5) & 1) != 0) {
            sb.append("HIGH ");
            sb.append(bArr[5]);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        } else {
            z2 = false;
        }
        if (((bArr[0] >> 4) & 1) != 0) {
            sb.append("LOW ");
            sb.append(bArr[6]);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        }
        if (((bArr[0] >> 7) & 1) != 0) {
            sb.append("HIGH ");
            if (z) {
                sb.append(bArr[2]);
                sb.append("°");
            } else {
                sb.append(UnsignedBytes.toInt(bArr[1]));
                sb.append("°");
            }
            sb.append(", ");
            z2 = true;
        }
        if (((bArr[0] >> 6) & 1) != 0) {
            sb.append("LOW ");
            if (z) {
                sb.append(bArr[4]);
                sb.append("°");
            } else {
                sb.append(UnsignedBytes.toInt(bArr[3]));
                sb.append("°");
            }
            sb.append(", ");
        } else {
            z3 = z2;
        }
        sb.setLength(sb.length() - 2);
        return z3;
    }

    private static boolean initTmpHum(StringBuilder sb, DeviceModel deviceModel) {
        boolean z = false;
        if (deviceModel.autoHighTmpTrigger || deviceModel.autoLowTmpTrigger) {
            if (deviceModel.autoHighTmpTrigger) {
                sb.append("HIGH ");
                sb.append(UnsignedBytes.toInt(deviceModel.autoHighTmp));
                sb.append("°");
                sb.append(" ");
                z = true;
            }
            if (deviceModel.autoLowTmpTrigger) {
                sb.append("LOW ");
                sb.append(UnsignedBytes.toInt(deviceModel.autoLowTmp));
                sb.append("°");
                sb.append(" ");
            }
            sb.setLength(sb.length() - 1);
            return z;
        }
        if (deviceModel.autoHighHumTrigger) {
            sb.append("HIGH ");
            sb.append(deviceModel.autoHighHum);
            sb.append("%");
            sb.append(" ");
            z = true;
        }
        if (deviceModel.autoLowHumTrigger) {
            sb.append("LOW ");
            sb.append(deviceModel.autoLowHum);
            sb.append("%");
            sb.append(" ");
        }
        sb.setLength(sb.length() - 1);
        return z;
        z = true;
        sb.setLength(sb.length() - 1);
        return z;
    }

    private static boolean initVpd(StringBuilder sb, NetMessageInfo netMessageInfo) {
        boolean z;
        boolean z2 = true;
        if (netMessageInfo.activeHtVpd == 0 || netMessageInfo.activeHtVpdParam == 0) {
            z = false;
        } else {
            sb.append("HIGH ");
            sb.append(((float) netMessageInfo.htVpdNums) / 10.0f);
            sb.append("kPa");
            sb.append(", ");
            z = true;
        }
        if (netMessageInfo.activeLtVpd == 0 || netMessageInfo.activeLtVpdParam == 0) {
            z2 = z;
        } else {
            sb.append("LOW ");
            sb.append(((float) netMessageInfo.ltVpdNums) / 10.0f);
            sb.append("kPa");
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return z2;
    }

    private static boolean initTmpHum(StringBuilder sb, NetMessageInfo netMessageInfo, boolean z) {
        boolean z2;
        boolean z3 = true;
        if (netMessageInfo.isHighHumi) {
            sb.append("HIGH ");
            sb.append(netMessageInfo.highHumi);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        } else {
            z2 = false;
        }
        if (netMessageInfo.isLowHumi) {
            sb.append("LOW ");
            sb.append(netMessageInfo.lowHumi);
            sb.append("%");
            sb.append(", ");
            z2 = true;
        }
        if (netMessageInfo.isHighTemp) {
            sb.append("HIGH ");
            if (z) {
                sb.append(netMessageInfo.highTempC);
                sb.append("°");
            } else {
                sb.append(netMessageInfo.highTempF);
                sb.append("°");
            }
            sb.append(", ");
            z2 = true;
        }
        if (netMessageInfo.isLowTemp) {
            sb.append("LOW ");
            if (z) {
                sb.append(netMessageInfo.lowTempC);
                sb.append("°");
            } else {
                sb.append(netMessageInfo.lowTempF);
                sb.append("°");
            }
            sb.append(", ");
        } else {
            z3 = z2;
        }
        sb.setLength(sb.length() - 2);
        return z3;
    }
}
