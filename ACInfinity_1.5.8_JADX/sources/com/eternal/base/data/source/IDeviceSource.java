package com.eternal.base.data.source;

import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceIndex;
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
import com.eternal.base.database.entity.Notification;
import java.util.List;
import p014io.reactivex.Flowable;

public interface IDeviceSource {
    void addDevice(Device device);

    DeviceIndex connect(Device device, boolean z);

    void deleteDevice(String str);

    List<String> getAllMac();

    Flowable<ConnectInfo> getConnectInfo(String str);

    Flowable<Long> getConnectTime(String str);

    Flowable<List<DeviceName>> getDeviceName(String str);

    Flowable<DeviceName> getDeviceName(String str, byte b);

    Flowable<List<DeviceInfo>> getInfo();

    PortCountAndLeafTmp getLeafTemperatureC(String str);

    DeviceModel getModel(String str, byte b);

    DeviceModelInfo getModelInfo(String str);

    DeviceName getName(String str, byte b);

    List<PortInfo> getPortInfo(String str);

    List<PortSetting> getPortSetting(String str);

    DeviceSetting getSetting(String str, byte b);

    String getSoftwareVersion(String str);

    DeviceTarget getTarget(String str);

    byte getType(String str);

    void initNetNotification(String str, byte b, List<Notification> list);

    void initNotification(String str, byte b, List<Notification> list);

    void initNotificationV4(String str, List<Notification> list);

    boolean isDegree(String str);

    void setConnectType(String str, String str2, byte b);

    void setControlTypeByHum(String str, byte b, boolean z);

    void setDeviceInfo(String str, DeviceInfo deviceInfo);

    void setDeviceModel(String str, byte b, DeviceModel deviceModel);

    void setDeviceName(String str, byte b, String str2);

    void setLeafTemperature(String str, byte b, byte b2);

    void setModelData(String str, DeviceModel deviceModel);

    void setPortInfo(String str, PortInfo portInfo);

    void setPortSettings(String str, PortSetting portSetting);

    void setPortSettings(String str, String str2, byte b, byte b2, byte b3, byte b4, byte b5, int i);

    void setSequence(String str, int i);

    void setSettings(String str, byte b, DeviceSetting deviceSetting);

    void setSoftwareVersion(String str, String str2);

    void setTarget(String str, int i, int i2, int i3, int i4);

    void setTime(String str, long j);
}
