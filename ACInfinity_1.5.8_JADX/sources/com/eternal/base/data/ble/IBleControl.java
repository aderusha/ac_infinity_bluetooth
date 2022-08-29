package com.eternal.base.data.ble;

import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.PortList;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.database.entity.History;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.database.entity.NotificationMessage;
import java.util.List;
import p014io.reactivex.Completable;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;
import p014io.reactivex.Single;

public interface IBleControl {
    void clean();

    void closeScan();

    Single<BleStatue> connect(BleDevice bleDevice, BleServer.DisconnectListener disconnectListener);

    void disConnect(BleStatue bleStatue);

    List<BleDevice> disableBle();

    Single<DeviceSetting> getBSetting(BleStatue bleStatue);

    Flowable<DeviceModel> getBroadcast(BleStatue bleStatue);

    Observable<History> getCHistory(BleStatue bleStatue, long j);

    Observable<Log> getCLog(BleStatue bleStatue, long j);

    Single<DeviceMinModel> getCModel(BleStatue bleStatue);

    Single<DeviceSetting> getCSetting(BleStatue bleStatue);

    BleStatue getConnect(String str);

    Observable<History> getEHistory(BleStatue bleStatue, long j);

    Observable<Log> getELog(BleStatue bleStatue, byte b, long j, int i);

    Single<DeviceSetting> getESetting(BleStatue bleStatue, byte b);

    Single<DeviceSetting> getFSetting(BleStatue bleStatue, byte b);

    Observable<History> getHistory(BleStatue bleStatue, long j);

    Observable<Log> getLog(BleStatue bleStatue, long j, int i);

    Single<DeviceMinModel> getModel(BleStatue bleStatue, byte b);

    Single<DeviceMinModel> getModel(BleStatue bleStatue, byte b, byte b2);

    Single<List<NotificationMessage>> getNotificationMessage(BleStatue bleStatue, byte b);

    Single<List<Notification>> getNotifications(BleStatue bleStatue, byte b);

    Single<PortList> getPortList(BleStatue bleStatue);

    Single<DeviceSetting> getSetting(BleStatue bleStatue);

    Single<DeviceTFP> getTFP(String str, byte b);

    Single<Boolean> getTempUnit(BleStatue bleStatue);

    Single<DeviceModel> initMode(BleStatue bleStatue, byte b);

    boolean isConnect(String str);

    boolean isScanning();

    Single<Boolean> resetDeviceToFactory(String str);

    Single<List<BleDevice>> scan(BleScanRuleConfig bleScanRuleConfig);

    Observable<List<BleDevice>> scanCycle(BleScanRuleConfig bleScanRuleConfig, boolean z);

    Single<Boolean> setAutomationOrder(BleStatue bleStatue, List<Byte> list, byte b, boolean z);

    Single<Boolean> setBSetting(String str, DeviceSetting deviceSetting);

    Single<Boolean> setCModel(BleStatue bleStatue, DeviceModel deviceModel);

    Single<Boolean> setCSetting(String str, DeviceSetting deviceSetting);

    Single<Boolean> setChoosePort(BleStatue bleStatue, byte b);

    Single<Boolean> setConfigSta(BleStatue bleStatue, String str, String str2, String str3);

    Single<Boolean> setESetting(String str, byte b, DeviceSetting deviceSetting);

    Single<Boolean> setFSetting(String str, byte b, DeviceSetting deviceSetting);

    Single<Boolean> setModel(BleStatue bleStatue, byte b, byte b2);

    Single<Boolean> setModel(BleStatue bleStatue, byte b, DeviceModel deviceModel);

    Single<Boolean> setNotification(BleStatue bleStatue, Notification notification, boolean z, boolean z2, boolean z3, boolean z4);

    Single<Boolean> setSetting(String str, DeviceSetting deviceSetting);

    void sleep(long j);

    Single<Boolean> syncCTime(BleStatue bleStatue);

    Completable syncConnectedTime();
}
