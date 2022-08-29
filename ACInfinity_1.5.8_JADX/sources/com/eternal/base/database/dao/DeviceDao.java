package com.eternal.base.database.dao;

import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceModelInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTarget;
import com.eternal.base.concat.PortCountAndLeafTmp;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.database.entity.Device;
import java.util.List;
import p014io.reactivex.Flowable;

public interface DeviceDao {
    DeviceInfo checkDB();

    int delete(String str);

    Flowable<ConnectInfo> getConnectInfo(String str);

    Flowable<Long> getConnectTime(String str);

    DeviceName getDeviceName(String str, byte b);

    String getSoftwareVersion(String str);

    void insert(Device device);

    boolean isDegree(String str);

    Flowable<List<DeviceInfo>> loadAllDeviceInfo();

    List<String> loadAllMac();

    List<DeviceName> loadAllName();

    long loadDeviceHistoryTime(String str);

    Flowable<DeviceInfo> loadDeviceInfo(String str);

    DeviceModel loadDeviceModel(String str, byte b);

    Flowable<List<DeviceName>> loadDeviceName(String str);

    Flowable<DeviceName> loadDeviceName(String str, byte b);

    byte loadDeviceType(String str);

    PortCountAndLeafTmp loadLeafTemperatureC(String str);

    long loadLogTime(String str);

    long loadLogTime(String str, byte b);

    DeviceModelInfo loadModelInfo(String str);

    List<PortInfo> loadPortInfo(String str);

    List<PortSetting> loadPortSetting(String str);

    DeviceSetting loadSettings(String str, byte b);

    DeviceTarget loadTarget(String str);

    void updateConnectType(String str, String str2, byte b);

    void updateControlTypeByHum(String str, byte b, boolean z);

    void updateDeviceHistoryTime(String str, long j);

    void updateDeviceInfo(String str, boolean z, int i, int i2, int i3, byte b, byte b2, byte b3, int i4, byte b4, String str2, String str3, String str4, byte b5, String str5, long j, int i5, byte b6, boolean z2, byte b7);

    void updateDeviceLogTime(String str, long j);

    void updateDeviceModel(String str, byte b, boolean z, int i, int i2, int i3, boolean z2, byte b2, byte b3, byte b4, char c, char c2, char c3, char c4, char c5, char c6, byte b5, byte b6, byte b7, byte b8, boolean z3, boolean z4, boolean z5, boolean z6, int i4, boolean z7, byte b9, boolean z8, byte b10, boolean z9, boolean z10);

    void updateDeviceName(String str, byte b, String str2);

    void updateLeafTemperature(String str, byte b, byte b2);

    void updateModel(String str, char c, char c2, char c3, char c4, char c5, char c6, byte b, byte b2, byte b3, byte b4, boolean z, boolean z2, boolean z3, boolean z4, byte b5, boolean z5, byte b6, boolean z6, byte b7, byte b8, boolean z7);

    void updatePortInfo(String str, byte b, byte b2, int i, int i2, boolean z, boolean z2);

    void updatePortSetting(String str, byte b, byte b2, byte b3, byte b4, byte b5, int i);

    void updatePortSetting(String str, byte b, String str2, byte b2, byte b3, byte b4, byte b5, int i);

    void updateSequence(String str, int i);

    void updateSetting(String str, byte b, byte b2, boolean z, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, int i, int i2);

    void updateSetting(String str, byte b, String str2, byte b2, boolean z, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, int i, int i2);

    void updateSoftwareVersion(String str, String str2);

    void updateTarget(String str, int i, int i2, int i3, int i4);

    void updateTime(String str, long j);
}
