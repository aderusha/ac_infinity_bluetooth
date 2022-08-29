package com.eternal.base.data.source;

import android.database.sqlite.SQLiteConstraintException;
import android.text.TextUtils;
import com.eternal.base.C1323R;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceIndex;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceModelInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTarget;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.concat.PortCountAndLeafTmp;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.database.BaseDatabase;
import com.eternal.base.database.dao.DeviceDao;
import com.eternal.base.database.dao.NotificationDao;
import com.eternal.base.database.entity.Device;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p014io.reactivex.Flowable;

public class DeviceSource implements IDeviceSource {
    private static final String DEFAULT_ALARMS = "Alarms";
    private static final String DEFAULT_ALERTS = "Alerts";
    private static final String DEFAULT_AUTOMATIC_NAME = "Automations";
    private static final String DEFAULT_NAME = "New Device";
    private static final String DEFAULT_NOTIFICATIONS = "Notifications";
    private DeviceDao dDao = BaseDatabase.getInstance().deviceDao();
    private NotificationDao nDao = BaseDatabase.getInstance().notificationDao();

    public DeviceModelInfo getModelInfo(String str) {
        return null;
    }

    public List<String> getAllMac() {
        return this.dDao.loadAllMac();
    }

    public byte getType(String str) {
        return this.dDao.loadDeviceType(str);
    }

    public Flowable<List<DeviceInfo>> getInfo() {
        return this.dDao.loadAllDeviceInfo();
    }

    public List<PortInfo> getPortInfo(String str) {
        return this.dDao.loadPortInfo(str);
    }

    public List<PortSetting> getPortSetting(String str) {
        return this.dDao.loadPortSetting(str);
    }

    public void deleteDevice(String str) {
        this.dDao.delete(str);
    }

    public void addDevice(Device device) {
        this.dDao.insert(device);
    }

    public void setSettings(String str, byte b, DeviceSetting deviceSetting) {
        DeviceSetting deviceSetting2 = deviceSetting;
        if (deviceSetting2.name == null) {
            this.dDao.updateSetting(str, b, deviceSetting2.brightness, deviceSetting2.isDegree, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, deviceSetting2.calibrationTemperature, deviceSetting2.calibrationHumidity, deviceSetting2.typeOff, deviceSetting2.typeOn, deviceSetting2.tmp, deviceSetting2.hum);
            return;
        }
        this.dDao.updateSetting(str, b, deviceSetting2.name, deviceSetting2.brightness, deviceSetting2.isDegree, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, deviceSetting2.calibrationTemperature, deviceSetting2.calibrationHumidity, deviceSetting2.typeOff, deviceSetting2.typeOn, deviceSetting2.tmp, deviceSetting2.hum);
    }

    public void setPortSettings(String str, PortSetting portSetting) {
        PortSetting portSetting2 = portSetting;
        if (portSetting2.name == null) {
            this.dDao.updatePortSetting(str, portSetting2.f138id, portSetting2.transitionTemperature, portSetting2.transitionTemperatureC, portSetting2.transitionHumidity, portSetting2.transitionVpd, portSetting2.portType);
            return;
        }
        this.dDao.updatePortSetting(str, portSetting2.f138id, portSetting2.name, portSetting2.transitionTemperature, portSetting2.transitionTemperatureC, portSetting2.transitionHumidity, portSetting2.transitionVpd, portSetting2.portType);
    }

    public void setPortSettings(String str, String str2, byte b, byte b2, byte b3, byte b4, byte b5, int i) {
        if (str2 == null) {
            this.dDao.updatePortSetting(str, b, b2, b3, b4, b5, i);
        } else {
            this.dDao.updatePortSetting(str, b, str2, b2, b3, b4, b5, i);
        }
    }

    public void setLeafTemperature(String str, byte b, byte b2) {
        this.dDao.updateLeafTemperature(str, b, b2);
    }

    public PortCountAndLeafTmp getLeafTemperatureC(String str) {
        return this.dDao.loadLeafTemperatureC(str);
    }

    public void setTarget(String str, int i, int i2, int i3, int i4) {
        this.dDao.updateTarget(str, i, i2, i3, i4);
    }

    public DeviceTarget getTarget(String str) {
        return this.dDao.loadTarget(str);
    }

    public DeviceSetting getSetting(String str, byte b) {
        return this.dDao.loadSettings(str, b);
    }

    public void setTime(String str, long j) {
        this.dDao.updateTime(str, j);
    }

    public Flowable<Long> getConnectTime(String str) {
        return this.dDao.getConnectTime(str);
    }

    public Flowable<ConnectInfo> getConnectInfo(String str) {
        return this.dDao.getConnectInfo(str);
    }

    public void setSequence(String str, int i) {
        this.dDao.updateSequence(str, i);
    }

    public void setControlTypeByHum(String str, byte b, boolean z) {
        this.dDao.updateControlTypeByHum(str, b, z);
    }

    public boolean isDegree(String str) {
        return BaseDatabase.getInstance().deviceDao().isDegree(str);
    }

    public DeviceModel getModel(String str, byte b) {
        DeviceModel loadDeviceModel = this.dDao.loadDeviceModel(str, b);
        loadDeviceModel.portList = this.dDao.loadPortInfo(str);
        return loadDeviceModel;
    }

    public Flowable<DeviceName> getDeviceName(String str, byte b) {
        return this.dDao.loadDeviceName(str, b);
    }

    public Flowable<List<DeviceName>> getDeviceName(String str) {
        return this.dDao.loadDeviceName(str);
    }

    public DeviceName getName(String str, byte b) {
        return this.dDao.getDeviceName(str, b);
    }

    public void setDeviceName(String str, byte b, String str2) {
        this.dDao.updateDeviceName(str, b, str2);
    }

    public void setConnectType(String str, String str2, byte b) {
        this.dDao.updateConnectType(str, str2, b);
    }

    public void setSoftwareVersion(String str, String str2) {
        this.dDao.updateSoftwareVersion(str, str2);
    }

    public String getSoftwareVersion(String str) {
        return this.dDao.getSoftwareVersion(str);
    }

    public void setDeviceModel(String str, byte b, DeviceModel deviceModel) {
        DeviceModel deviceModel2 = deviceModel;
        this.dDao.updateDeviceModel(str, b, deviceModel2.isDegree, deviceModel2.tmp, deviceModel2.hum, deviceModel2.fan, deviceModel2.isControlTypeByHum, deviceModel2.workType, deviceModel2.typeOn, deviceModel2.typeOff, deviceModel2.timeOn, deviceModel2.timeOff, deviceModel2.cycleOn, deviceModel2.cycleOff, deviceModel2.schedOn, deviceModel2.schedOff, deviceModel2.autoHighHum, deviceModel2.autoLowHum, deviceModel2.autoHighTmp, deviceModel2.autoLowTmp, deviceModel2.autoHighTmpSwitch, deviceModel2.autoLowTmpSwitch, deviceModel2.autoHighHumSwitch, deviceModel2.autoLowHumSwitch, deviceModel2.currentTypeResidueTime, deviceModel2.currentTypeResidueOn, (byte) deviceModel2.highVpd, deviceModel2.highVpdSwitch, (byte) deviceModel2.lowVpd, deviceModel2.lowVpdSwitch, deviceModel2.isAdv);
    }

    public void setModelData(String str, DeviceModel deviceModel) {
        DeviceModel deviceModel2 = deviceModel;
        this.dDao.updateModel(str, deviceModel2.timeOn, deviceModel2.timeOff, deviceModel2.cycleOn, deviceModel2.cycleOff, deviceModel2.schedOn, deviceModel2.schedOff, deviceModel2.autoHighHum, deviceModel2.autoLowHum, deviceModel2.autoHighTmp, deviceModel2.autoLowTmp, deviceModel2.autoHighTmpSwitch, deviceModel2.autoLowTmpSwitch, deviceModel2.autoHighHumSwitch, deviceModel2.autoLowHumSwitch, (byte) deviceModel2.highVpd, deviceModel2.highVpdSwitch, (byte) deviceModel2.lowVpd, deviceModel2.lowVpdSwitch, deviceModel2.port, deviceModel2.choosePort, deviceModel2.isAdv);
    }

    public void setDeviceInfo(String str, DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2 = deviceInfo;
        String str2 = str;
        this.dDao.updateDeviceInfo(str2, deviceInfo2.isDegree, deviceInfo2.tmp, deviceInfo2.hum, deviceInfo2.fan, deviceInfo2.tmpState, deviceInfo2.humState, deviceInfo2.choosePort, deviceInfo2.fanType, deviceInfo2.version, deviceInfo2.softwareVersion, deviceInfo2.hardwareVersion, deviceInfo2.firmwareVersion, deviceInfo2.isShare, deviceInfo2.shareEmail, deviceInfo2.triggerNotifyTime, deviceInfo2.vpd, deviceInfo2.vpdState, deviceInfo2.isAdv, deviceInfo2.connectType);
    }

    public void setPortInfo(String str, PortInfo portInfo) {
        this.dDao.updatePortInfo(str, portInfo.f138id, portInfo.fanState, portInfo.fan, portInfo.fanType, portInfo.isPlug, portInfo.isAdv);
    }

    public void initNotification(String str, byte b, List<Notification> list) {
        String str2;
        String str3;
        List<NotificationName> loadMacAllName = this.nDao.loadMacAllName(str, b);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (NotificationName next : loadMacAllName) {
            if (next.type == 1) {
                getNumberZero(arrayList, DEFAULT_AUTOMATIC_NAME, next.name);
            } else {
                getNumberZero(arrayList2, DEFAULT_ALERTS, next.name);
            }
        }
        for (Notification next2 : list) {
            next2.mac = str;
            next2.port = b;
            boolean z = false;
            Iterator<NotificationName> it = loadMacAllName.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NotificationName next3 = it.next();
                if (next3.f137id == next2.f144id && next3.type == next2.type) {
                    it.remove();
                    next2.name = next3.name;
                    z = true;
                    break;
                }
            }
            if (z) {
                this.nDao.update(next2);
            } else {
                if (next2.type == 1) {
                    int minNumberZero = minNumberZero(arrayList);
                    if (minNumberZero == 0) {
                        str3 = DEFAULT_AUTOMATIC_NAME;
                    } else {
                        str3 = "Automations " + minNumberZero;
                    }
                    next2.name = str3;
                    arrayList.add(Integer.valueOf(minNumberZero));
                } else {
                    int minNumberZero2 = minNumberZero(arrayList2);
                    if (minNumberZero2 == 0) {
                        str2 = DEFAULT_ALERTS;
                    } else {
                        str2 = "Alerts " + minNumberZero2;
                    }
                    next2.name = str2;
                    arrayList2.add(Integer.valueOf(minNumberZero2));
                }
                this.nDao.insertNotification(next2);
            }
        }
        for (NotificationName next4 : loadMacAllName) {
            this.nDao.deleteNotification(str, b, next4.f137id, next4.type);
        }
    }

    public void initNotificationV4(String str, List<Notification> list) {
        String str2;
        String str3;
        List<NotificationName> loadMacAllName = this.nDao.loadMacAllName(str);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (NotificationName next : loadMacAllName) {
            if (next.type == 1) {
                getNumberZero(arrayList, DEFAULT_AUTOMATIC_NAME, next.name);
            } else {
                getNumberZero(arrayList2, DEFAULT_ALERTS, next.name);
            }
        }
        for (Notification next2 : list) {
            next2.mac = str;
            boolean z = false;
            Iterator<NotificationName> it = loadMacAllName.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NotificationName next3 = it.next();
                if (next3.f137id == next2.f144id && next3.type == next2.type && next3.childId == next2.childId) {
                    it.remove();
                    next2.name = next3.name;
                    z = true;
                    break;
                }
            }
            if (z) {
                this.nDao.update(next2);
            } else {
                if (next2.type == 1) {
                    int minNumberZero = minNumberZero(arrayList);
                    if (minNumberZero == 0) {
                        str3 = DEFAULT_AUTOMATIC_NAME;
                    } else {
                        str3 = "Automations " + minNumberZero;
                    }
                    next2.name = str3;
                    arrayList.add(Integer.valueOf(minNumberZero));
                } else {
                    int minNumberZero2 = minNumberZero(arrayList2);
                    if (minNumberZero2 == 0) {
                        str2 = DEFAULT_ALERTS;
                    } else {
                        str2 = "Alerts " + minNumberZero2;
                    }
                    next2.name = str2;
                    arrayList2.add(Integer.valueOf(minNumberZero2));
                }
                this.nDao.insertNotification(next2);
            }
        }
        for (NotificationName next4 : loadMacAllName) {
            this.nDao.deleteNotification(str, next4.f137id, next4.type, next4.childId);
        }
    }

    public void initNetNotification(String str, byte b, List<Notification> list) {
        List<NotificationName> loadMacAllName = this.nDao.loadMacAllName(str, b);
        for (Notification next : list) {
            next.mac = str;
            next.port = b;
            boolean z = false;
            Iterator<NotificationName> it = loadMacAllName.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NotificationName next2 = it.next();
                if (next2.f137id == next.f144id && next2.type == next.type) {
                    z = true;
                    it.remove();
                    break;
                }
            }
            if (z) {
                this.nDao.update(next);
            } else {
                try {
                    this.nDao.insertNotification(next);
                } catch (SQLiteConstraintException e) {
                    KLog.m65e("添加通知失败，id：" + next.f144id + " ,port: " + next.port);
                    e.printStackTrace();
                }
            }
        }
        for (NotificationName next3 : loadMacAllName) {
            this.nDao.deleteNotification(str, b, next3.f137id, next3.type);
        }
    }

    public DeviceIndex connect(Device device, boolean z) {
        String str;
        DeviceIndex deviceIndex;
        List<DeviceName> loadAllName = this.dDao.loadAllName();
        ArrayList arrayList = new ArrayList(loadAllName.size());
        int i = 0;
        while (true) {
            int size = loadAllName.size();
            str = DEFAULT_NAME;
            if (i >= size) {
                deviceIndex = null;
                break;
            }
            DeviceName deviceName = loadAllName.get(i);
            if (deviceName.mac.equals(device.mac)) {
                deviceIndex = new DeviceIndex(i, deviceName.name);
                break;
            }
            if (!TextUtils.isEmpty(deviceName.name) && deviceName.name.startsWith(str)) {
                if (deviceName.name.length() == 10) {
                    arrayList.add(1);
                } else {
                    try {
                        arrayList.add(Integer.valueOf(deviceName.name.substring(11)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            i++;
        }
        if (deviceIndex == null) {
            int minNumber = minNumber(arrayList);
            if (minNumber != 1) {
                str = "New Device " + minNumber;
            }
            device.name = str;
            if (z) {
                this.dDao.insert(device);
            }
            KLog.m68i("save device:" + device.mac);
            deviceIndex = new DeviceIndex(-1, device.name);
        }
        deviceIndex.typeName = Utils.getString(C1323R.string.tip_name_name, ProtocolTransformer.getType(device.type), device.typeName);
        deviceIndex.mac = device.mac;
        deviceIndex.isSaved = z;
        return deviceIndex;
    }

    private int minNumber(List<Integer> list) {
        int size;
        if (list.isEmpty()) {
            return 1;
        }
        if (list.size() != 1) {
            int i = 0;
            for (int i2 = 1; i2 < list.size(); i2++) {
                if (list.get(i).intValue() > list.get(i2).intValue()) {
                    i = i2;
                }
            }
            if (list.get(i).intValue() != 1) {
                return 1;
            }
            Collections.swap(list, i, 0);
            int i3 = 1;
            while (true) {
                if (i3 >= list.size()) {
                    size = list.size();
                    break;
                }
                int i4 = i3 + 1;
                int i5 = i3;
                for (int i6 = i4; i6 < list.size(); i6++) {
                    if (list.get(i5).intValue() > list.get(i6).intValue()) {
                        i5 = i6;
                    }
                }
                Collections.swap(list, i3, i5);
                int i7 = i3 - 1;
                if (list.get(i3).intValue() - list.get(i7).intValue() > 1) {
                    size = list.get(i7).intValue();
                    break;
                }
                i3 = i4;
            }
            return size + 1;
        } else if (list.get(0).intValue() == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    private int minNumberZero(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        if (list.size() != 1) {
            int i = 0;
            for (int i2 = 1; i2 < list.size(); i2++) {
                if (list.get(i).intValue() > list.get(i2).intValue()) {
                    i = i2;
                }
            }
            if (list.get(i).intValue() != 0) {
                return 0;
            }
            Collections.swap(list, i, 0);
            int i3 = 1;
            while (i3 < list.size()) {
                int i4 = i3 + 1;
                int i5 = i3;
                for (int i6 = i4; i6 < list.size(); i6++) {
                    if (list.get(i5).intValue() > list.get(i6).intValue()) {
                        i5 = i6;
                    }
                }
                Collections.swap(list, i3, i5);
                int i7 = i3 - 1;
                if (list.get(i3).intValue() - list.get(i7).intValue() > 1) {
                    return list.get(i7).intValue() + 1;
                }
                i3 = i4;
            }
            return list.size();
        } else if (list.get(0).intValue() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private void getNumberZero(List<Integer> list, String str, String str2) {
        if (!str2.startsWith(str)) {
            return;
        }
        if (str2.length() == str.length()) {
            list.add(0);
            return;
        }
        try {
            list.add(Integer.valueOf(str2.substring(str.length() + 1)));
        } catch (NumberFormatException unused) {
        }
    }
}
