package com.eternal.base.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.p005db.SupportSQLiteStatement;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import p014io.reactivex.Flowable;

public final class DeviceDao_Impl implements DeviceDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<Device> __insertionAdapterOfDevice;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfUpdateConnectType;
    private final SharedSQLiteStatement __preparedStmtOfUpdateControlTypeByHum;
    private final SharedSQLiteStatement __preparedStmtOfUpdateDeviceHistoryTime;
    private final SharedSQLiteStatement __preparedStmtOfUpdateDeviceInfo;
    private final SharedSQLiteStatement __preparedStmtOfUpdateDeviceLogTime;
    private final SharedSQLiteStatement __preparedStmtOfUpdateDeviceModel;
    private final SharedSQLiteStatement __preparedStmtOfUpdateDeviceName;
    private final SharedSQLiteStatement __preparedStmtOfUpdateLeafTemperature;
    private final SharedSQLiteStatement __preparedStmtOfUpdateModel;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePortInfo;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePortSetting;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePortSetting_1;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSequence;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSetting;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSetting_1;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSoftwareVersion;
    private final SharedSQLiteStatement __preparedStmtOfUpdateTarget;
    private final SharedSQLiteStatement __preparedStmtOfUpdateTime;

    public DeviceDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfDevice = new EntityInsertionAdapter<Device>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `Device` (`mac`,`deviceId`,`isShare`,`name`,`type`,`version`,`firmwareVersion`,`hardwareVersion`,`softwareVersion`,`shareEmail`,`connectTime`,`triggerNotifyTime`,`typeName`,`tmp`,`isDegree`,`tmpState`,`hum`,`humState`,`vpdState`,`vpd`,`fan`,`fanState`,`brightness`,`transitionTemperature`,`transitionTemperatureC`,`transitionHumidity`,`transitionVpd`,`bufferTemp`,`bufferTempC`,`bufferHum`,`bufferVpd`,`calibrationTemperature`,`calibrationHumidity`,`historyTime`,`logTime`,`isControlTypeByHum`,`workType`,`typeOn`,`typeOff`,`timeOn`,`timeOff`,`cycleOn`,`cycleOff`,`schedOn`,`schedOff`,`autoHighHum`,`autoLowHum`,`autoHighTmp`,`autoLowTmp`,`autoHighTmpSwitch`,`autoLowTmpSwitch`,`autoHighHumSwitch`,`autoLowHumSwitch`,`currentTypeResidueTime`,`currentTypeResidueOn`,`fanType`,`portType`,`port`,`choosePort`,`isPlug`,`sequence`,`leafTemperatureC`,`leafTemperatureF`,`targetTmpC`,`targetTmpF`,`targetHum`,`targetVpd`,`highVpdSwitch`,`lowVpdSwitch`,`highVpd`,`lowVpd`,`isAdv`,`connectType`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Device device) {
                if (device.mac == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, device.mac);
                }
                if (device.deviceId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, device.deviceId);
                }
                supportSQLiteStatement.bindLong(3, (long) device.isShare);
                if (device.name == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, device.name);
                }
                supportSQLiteStatement.bindLong(5, (long) device.type);
                supportSQLiteStatement.bindLong(6, (long) device.version);
                if (device.firmwareVersion == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, device.firmwareVersion);
                }
                if (device.hardwareVersion == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, device.hardwareVersion);
                }
                if (device.softwareVersion == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, device.softwareVersion);
                }
                if (device.shareEmail == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, device.shareEmail);
                }
                supportSQLiteStatement.bindLong(11, device.connectTime);
                supportSQLiteStatement.bindLong(12, device.triggerNotifyTime);
                if (device.typeName == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, device.typeName);
                }
                supportSQLiteStatement.bindLong(14, (long) device.tmp);
                supportSQLiteStatement.bindLong(15, device.isDegree ? 1 : 0);
                supportSQLiteStatement.bindLong(16, (long) device.tmpState);
                supportSQLiteStatement.bindLong(17, (long) device.hum);
                supportSQLiteStatement.bindLong(18, (long) device.humState);
                supportSQLiteStatement.bindLong(19, (long) device.vpdState);
                supportSQLiteStatement.bindLong(20, (long) device.vpd);
                supportSQLiteStatement.bindLong(21, (long) device.fan);
                supportSQLiteStatement.bindLong(22, (long) device.fanState);
                supportSQLiteStatement.bindLong(23, (long) device.brightness);
                supportSQLiteStatement.bindLong(24, (long) device.transitionTemperature);
                supportSQLiteStatement.bindLong(25, (long) device.transitionTemperatureC);
                supportSQLiteStatement.bindLong(26, (long) device.transitionHumidity);
                supportSQLiteStatement.bindLong(27, (long) device.transitionVpd);
                supportSQLiteStatement.bindLong(28, (long) device.bufferTemp);
                supportSQLiteStatement.bindLong(29, (long) device.bufferTempC);
                supportSQLiteStatement.bindLong(30, (long) device.bufferHum);
                supportSQLiteStatement.bindLong(31, (long) device.bufferVpd);
                supportSQLiteStatement.bindLong(32, (long) device.calibrationTemperature);
                supportSQLiteStatement.bindLong(33, (long) device.calibrationHumidity);
                supportSQLiteStatement.bindLong(34, device.historyTime);
                supportSQLiteStatement.bindLong(35, device.logTime);
                supportSQLiteStatement.bindLong(36, device.isControlTypeByHum ? 1 : 0);
                supportSQLiteStatement.bindLong(37, (long) device.workType);
                supportSQLiteStatement.bindLong(38, (long) device.typeOn);
                supportSQLiteStatement.bindLong(39, (long) device.typeOff);
                supportSQLiteStatement.bindLong(40, (long) device.timeOn);
                supportSQLiteStatement.bindLong(41, (long) device.timeOff);
                supportSQLiteStatement.bindLong(42, (long) device.cycleOn);
                supportSQLiteStatement.bindLong(43, (long) device.cycleOff);
                supportSQLiteStatement.bindLong(44, (long) device.schedOn);
                supportSQLiteStatement.bindLong(45, (long) device.schedOff);
                supportSQLiteStatement.bindLong(46, (long) device.autoHighHum);
                supportSQLiteStatement.bindLong(47, (long) device.autoLowHum);
                supportSQLiteStatement.bindLong(48, (long) device.autoHighTmp);
                supportSQLiteStatement.bindLong(49, (long) device.autoLowTmp);
                supportSQLiteStatement.bindLong(50, device.autoHighTmpSwitch ? 1 : 0);
                supportSQLiteStatement.bindLong(51, device.autoLowTmpSwitch ? 1 : 0);
                supportSQLiteStatement.bindLong(52, device.autoHighHumSwitch ? 1 : 0);
                supportSQLiteStatement.bindLong(53, device.autoLowHumSwitch ? 1 : 0);
                supportSQLiteStatement.bindLong(54, (long) device.currentTypeResidueTime);
                supportSQLiteStatement.bindLong(55, device.currentTypeResidueOn ? 1 : 0);
                supportSQLiteStatement.bindLong(56, (long) device.fanType);
                supportSQLiteStatement.bindLong(57, (long) device.portType);
                supportSQLiteStatement.bindLong(58, (long) device.port);
                supportSQLiteStatement.bindLong(59, (long) device.choosePort);
                supportSQLiteStatement.bindLong(60, device.isPlug ? 1 : 0);
                supportSQLiteStatement.bindLong(61, device.sequence ? 1 : 0);
                supportSQLiteStatement.bindLong(62, (long) device.leafTemperatureC);
                supportSQLiteStatement.bindLong(63, (long) device.leafTemperatureF);
                supportSQLiteStatement.bindLong(64, (long) device.targetTmpC);
                supportSQLiteStatement.bindLong(65, (long) device.targetTmpF);
                supportSQLiteStatement.bindLong(66, (long) device.targetHum);
                supportSQLiteStatement.bindLong(67, (long) device.targetVpd);
                supportSQLiteStatement.bindLong(68, device.highVpdSwitch ? 1 : 0);
                supportSQLiteStatement.bindLong(69, device.lowVpdSwitch ? 1 : 0);
                supportSQLiteStatement.bindLong(70, (long) device.highVpd);
                supportSQLiteStatement.bindLong(71, (long) device.lowVpd);
                supportSQLiteStatement.bindLong(72, device.isAdv ? 1 : 0);
                supportSQLiteStatement.bindLong(73, (long) device.connectType);
            }
        };
        this.__preparedStmtOfUpdateTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set connectTime=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateSequence = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set sequence=? where mac=?";
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from device where mac=?";
            }
        };
        this.__preparedStmtOfUpdateSetting = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set name=?,brightness=?, isDegree=?,transitionTemperature=?,transitionTemperatureC=?,transitionHumidity=?,calibrationTemperature=?, calibrationHumidity=?,typeOff=?,typeOn=?,tmp=?,hum=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdateSetting_1 = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set brightness=?, isDegree=?,transitionTemperature=?,transitionTemperatureC=?,transitionHumidity=?,calibrationTemperature=?, calibrationHumidity=?, typeOff=?,typeOn=?, tmp=?,hum=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdatePortSetting = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set name=?,transitionTemperature=?,transitionTemperatureC=?,transitionHumidity=?,transitionVpd=?,portType=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdatePortSetting_1 = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set transitionTemperature=?,transitionTemperatureC=?,transitionHumidity=?,transitionVpd=?,portType=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdateLeafTemperature = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set leafTemperatureC=?,leafTemperatureF=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateTarget = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set targetTmpF=?,targetTmpC=?, targetHum=?,targetVpd=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateDeviceName = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set name=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdateConnectType = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set connectType=?, deviceId=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateSoftwareVersion = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set softwareVersion=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateDeviceLogTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set logTime=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateDeviceHistoryTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set historyTime=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdateDeviceModel = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set isDegree=?,tmp=?,hum=?,fan=?,isControlTypeByHum=?,workType=?,typeOn=?,typeOff=?,timeOn=?,timeOff=?,cycleOn=?,cycleOff=?,schedOn=?,schedOff=?,autoHighHum=?,autoLowHum=?,autoHighTmp=?,autoLowTmp=?,autoHighTmpSwitch=?,autoLowTmpSwitch=?,autoHighHumSwitch=?,autoLowHumSwitch=?,currentTypeResidueTime=?,currentTypeResidueOn=?,highVpd=?,lowVpd=?,highVpdSwitch=?,lowVpdSwitch=?,isAdv=?  where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdateModel = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set timeOn=?, timeOff=?, cycleOn=?, cycleOff=?, schedOn=?, schedOff=?,autoHighHum=?, autoLowHum=?, autoHighTmp=?, autoLowTmp=?,autoHighTmpSwitch=?, autoLowTmpSwitch=?,autoHighHumSwitch=?,autoLowHumSwitch=?,choosePort=?,highVpd=?,lowVpd=?,highVpdSwitch=?,lowVpdSwitch=?,isAdv=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdateDeviceInfo = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set isDegree=?,tmp=?,hum=?,fan=?,tmpState=?, humState=?, fanType=?,choosePort=?, version=?,softwareVersion=?,hardwareVersion=?,firmwareVersion=?,isShare=?,shareEmail=?,triggerNotifyTime=?,vpd=?,vpdState=?,isAdv=?,connectType=? where mac=?";
            }
        };
        this.__preparedStmtOfUpdatePortInfo = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set fan=?, fanState=?, fanType=?, fan=?, isPlug=?,isAdv=? where mac=? and port=?";
            }
        };
        this.__preparedStmtOfUpdateControlTypeByHum = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update device set isControlTypeByHum=? where mac=? and port=?";
            }
        };
    }

    public void insert(Device device) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDevice.insert(device);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void updateTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateTime.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateTime.release(acquire);
        }
    }

    public void updateSequence(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateSequence.acquire();
        acquire.bindLong(1, (long) i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSequence.release(acquire);
        }
    }

    public int delete(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(acquire);
        }
    }

    public void updateSetting(String str, byte b, String str2, byte b2, boolean z, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, int i, int i2) {
        String str3 = str;
        String str4 = str2;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateSetting.acquire();
        if (str4 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        acquire.bindLong(2, (long) b2);
        acquire.bindLong(3, z ? 1 : 0);
        acquire.bindLong(4, (long) b3);
        acquire.bindLong(5, (long) b4);
        acquire.bindLong(6, (long) b5);
        acquire.bindLong(7, (long) b6);
        acquire.bindLong(8, (long) b7);
        acquire.bindLong(9, (long) b8);
        acquire.bindLong(10, (long) b9);
        acquire.bindLong(11, (long) i);
        acquire.bindLong(12, (long) i2);
        if (str3 == null) {
            acquire.bindNull(13);
        } else {
            acquire.bindString(13, str);
        }
        acquire.bindLong(14, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSetting.release(acquire);
        }
    }

    public void updateSetting(String str, byte b, byte b2, boolean z, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, int i, int i2) {
        String str2 = str;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateSetting_1.acquire();
        acquire.bindLong(1, (long) b2);
        acquire.bindLong(2, z ? 1 : 0);
        acquire.bindLong(3, (long) b3);
        acquire.bindLong(4, (long) b4);
        acquire.bindLong(5, (long) b5);
        acquire.bindLong(6, (long) b6);
        acquire.bindLong(7, (long) b7);
        acquire.bindLong(8, (long) b8);
        acquire.bindLong(9, (long) b9);
        acquire.bindLong(10, (long) i);
        acquire.bindLong(11, (long) i2);
        if (str2 == null) {
            acquire.bindNull(12);
        } else {
            acquire.bindString(12, str);
        }
        acquire.bindLong(13, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSetting_1.release(acquire);
        }
    }

    public void updatePortSetting(String str, byte b, String str2, byte b2, byte b3, byte b4, byte b5, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdatePortSetting.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        acquire.bindLong(2, (long) b2);
        acquire.bindLong(3, (long) b3);
        acquire.bindLong(4, (long) b4);
        acquire.bindLong(5, (long) b5);
        acquire.bindLong(6, (long) i);
        if (str == null) {
            acquire.bindNull(7);
        } else {
            acquire.bindString(7, str);
        }
        acquire.bindLong(8, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdatePortSetting.release(acquire);
        }
    }

    public void updatePortSetting(String str, byte b, byte b2, byte b3, byte b4, byte b5, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdatePortSetting_1.acquire();
        acquire.bindLong(1, (long) b2);
        acquire.bindLong(2, (long) b3);
        acquire.bindLong(3, (long) b4);
        acquire.bindLong(4, (long) b5);
        acquire.bindLong(5, (long) i);
        if (str == null) {
            acquire.bindNull(6);
        } else {
            acquire.bindString(6, str);
        }
        acquire.bindLong(7, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdatePortSetting_1.release(acquire);
        }
    }

    public void updateLeafTemperature(String str, byte b, byte b2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateLeafTemperature.acquire();
        acquire.bindLong(1, (long) b);
        acquire.bindLong(2, (long) b2);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateLeafTemperature.release(acquire);
        }
    }

    public void updateTarget(String str, int i, int i2, int i3, int i4) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateTarget.acquire();
        acquire.bindLong(1, (long) i);
        acquire.bindLong(2, (long) i2);
        acquire.bindLong(3, (long) i3);
        acquire.bindLong(4, (long) i4);
        if (str == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateTarget.release(acquire);
        }
    }

    public void updateDeviceName(String str, byte b, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateDeviceName.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateDeviceName.release(acquire);
        }
    }

    public void updateConnectType(String str, String str2, byte b) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateConnectType.acquire();
        acquire.bindLong(1, (long) b);
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateConnectType.release(acquire);
        }
    }

    public void updateSoftwareVersion(String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateSoftwareVersion.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSoftwareVersion.release(acquire);
        }
    }

    public void updateDeviceLogTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateDeviceLogTime.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateDeviceLogTime.release(acquire);
        }
    }

    public void updateDeviceHistoryTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateDeviceHistoryTime.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateDeviceHistoryTime.release(acquire);
        }
    }

    public void updateDeviceModel(String str, byte b, boolean z, int i, int i2, int i3, boolean z2, byte b2, byte b3, byte b4, char c, char c2, char c3, char c4, char c5, char c6, byte b5, byte b6, byte b7, byte b8, boolean z3, boolean z4, boolean z5, boolean z6, int i4, boolean z7, byte b9, boolean z8, byte b10, boolean z9, boolean z10) {
        String str2 = str;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateDeviceModel.acquire();
        acquire.bindLong(1, z ? 1 : 0);
        acquire.bindLong(2, (long) i);
        acquire.bindLong(3, (long) i2);
        acquire.bindLong(4, (long) i3);
        acquire.bindLong(5, z2 ? 1 : 0);
        acquire.bindLong(6, (long) b2);
        acquire.bindLong(7, (long) b3);
        acquire.bindLong(8, (long) b4);
        acquire.bindLong(9, (long) c);
        acquire.bindLong(10, (long) c2);
        acquire.bindLong(11, (long) c3);
        acquire.bindLong(12, (long) c4);
        acquire.bindLong(13, (long) c5);
        acquire.bindLong(14, (long) c6);
        acquire.bindLong(15, (long) b5);
        acquire.bindLong(16, (long) b6);
        acquire.bindLong(17, (long) b7);
        acquire.bindLong(18, (long) b8);
        acquire.bindLong(19, z3 ? 1 : 0);
        acquire.bindLong(20, z4 ? 1 : 0);
        acquire.bindLong(21, z5 ? 1 : 0);
        acquire.bindLong(22, z6 ? 1 : 0);
        acquire.bindLong(23, (long) i4);
        acquire.bindLong(24, z7 ? 1 : 0);
        acquire.bindLong(25, (long) b9);
        acquire.bindLong(26, (long) b10);
        acquire.bindLong(27, z8 ? 1 : 0);
        acquire.bindLong(28, z9 ? 1 : 0);
        acquire.bindLong(29, z10 ? 1 : 0);
        if (str2 == null) {
            acquire.bindNull(30);
        } else {
            acquire.bindString(30, str);
        }
        acquire.bindLong(31, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateDeviceModel.release(acquire);
        }
    }

    public void updateModel(String str, char c, char c2, char c3, char c4, char c5, char c6, byte b, byte b2, byte b3, byte b4, boolean z, boolean z2, boolean z3, boolean z4, byte b5, boolean z5, byte b6, boolean z6, byte b7, byte b8, boolean z7) {
        String str2 = str;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateModel.acquire();
        acquire.bindLong(1, (long) c);
        acquire.bindLong(2, (long) c2);
        acquire.bindLong(3, (long) c3);
        acquire.bindLong(4, (long) c4);
        acquire.bindLong(5, (long) c5);
        acquire.bindLong(6, (long) c6);
        acquire.bindLong(7, (long) b);
        acquire.bindLong(8, (long) b2);
        acquire.bindLong(9, (long) b3);
        acquire.bindLong(10, (long) b4);
        acquire.bindLong(11, z ? 1 : 0);
        acquire.bindLong(12, z2 ? 1 : 0);
        acquire.bindLong(13, z3 ? 1 : 0);
        acquire.bindLong(14, z4 ? 1 : 0);
        acquire.bindLong(15, (long) b8);
        acquire.bindLong(16, (long) b5);
        acquire.bindLong(17, (long) b6);
        acquire.bindLong(18, z5 ? 1 : 0);
        acquire.bindLong(19, z6 ? 1 : 0);
        acquire.bindLong(20, z7 ? 1 : 0);
        if (str2 == null) {
            acquire.bindNull(21);
        } else {
            acquire.bindString(21, str);
        }
        acquire.bindLong(22, (long) b7);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateModel.release(acquire);
        }
    }

    public void updateDeviceInfo(String str, boolean z, int i, int i2, int i3, byte b, byte b2, byte b3, int i4, byte b4, String str2, String str3, String str4, byte b5, String str5, long j, int i5, byte b6, boolean z2, byte b7) {
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        String str9 = str4;
        String str10 = str5;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateDeviceInfo.acquire();
        acquire.bindLong(1, z ? 1 : 0);
        acquire.bindLong(2, (long) i);
        acquire.bindLong(3, (long) i2);
        acquire.bindLong(4, (long) i3);
        acquire.bindLong(5, (long) b);
        acquire.bindLong(6, (long) b2);
        acquire.bindLong(7, (long) i4);
        acquire.bindLong(8, (long) b3);
        acquire.bindLong(9, (long) b4);
        if (str7 == null) {
            acquire.bindNull(10);
        } else {
            acquire.bindString(10, str7);
        }
        if (str8 == null) {
            acquire.bindNull(11);
        } else {
            acquire.bindString(11, str8);
        }
        if (str9 == null) {
            acquire.bindNull(12);
        } else {
            acquire.bindString(12, str9);
        }
        acquire.bindLong(13, (long) b5);
        if (str10 == null) {
            acquire.bindNull(14);
        } else {
            acquire.bindString(14, str10);
        }
        acquire.bindLong(15, j);
        acquire.bindLong(16, (long) i5);
        acquire.bindLong(17, (long) b6);
        acquire.bindLong(18, z2 ? 1 : 0);
        acquire.bindLong(19, (long) b7);
        if (str6 == null) {
            acquire.bindNull(20);
        } else {
            acquire.bindString(20, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateDeviceInfo.release(acquire);
        }
    }

    public void updatePortInfo(String str, byte b, byte b2, int i, int i2, boolean z, boolean z2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdatePortInfo.acquire();
        long j = (long) i;
        acquire.bindLong(1, j);
        acquire.bindLong(2, (long) b2);
        acquire.bindLong(3, (long) i2);
        acquire.bindLong(4, j);
        acquire.bindLong(5, z ? 1 : 0);
        acquire.bindLong(6, z2 ? 1 : 0);
        if (str == null) {
            acquire.bindNull(7);
        } else {
            acquire.bindString(7, str);
        }
        acquire.bindLong(8, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdatePortInfo.release(acquire);
        }
    }

    public void updateControlTypeByHum(String str, byte b, boolean z) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateControlTypeByHum.acquire();
        acquire.bindLong(1, z ? 1 : 0);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateControlTypeByHum.release(acquire);
        }
    }

    public Flowable<List<DeviceInfo>> loadAllDeviceInfo() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac,name, typeName, type, connectTime, tmp, hum, fan,isDegree, tmpState, humState,fanState,fanType, port,choosePort, isPlug,version,softwareVersion,hardwareVersion,firmwareVersion,deviceId,isShare,shareEmail,triggerNotifyTime,leafTemperatureC,leafTemperatureF,vpd,vpdState,workType,isAdv,portType,connectType from Device order by sequence asc", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{com.telink.p010lt.ble.Device.TAG}, new Callable<List<DeviceInfo>>() {
            public List<DeviceInfo> call() throws Exception {
                Cursor query = DBUtil.query(DeviceDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        DeviceInfo deviceInfo = new DeviceInfo();
                        if (query.isNull(0)) {
                            deviceInfo.mac = null;
                        } else {
                            deviceInfo.mac = query.getString(0);
                        }
                        boolean z = true;
                        if (query.isNull(1)) {
                            deviceInfo.name = null;
                        } else {
                            deviceInfo.name = query.getString(1);
                        }
                        if (query.isNull(2)) {
                            deviceInfo.typeName = null;
                        } else {
                            deviceInfo.typeName = query.getString(2);
                        }
                        deviceInfo.type = (byte) query.getShort(3);
                        deviceInfo.connectTime = query.getLong(4);
                        deviceInfo.tmp = query.getInt(5);
                        deviceInfo.hum = query.getInt(6);
                        deviceInfo.fan = query.getInt(7);
                        deviceInfo.isDegree = query.getInt(8) != 0;
                        deviceInfo.tmpState = (byte) query.getShort(9);
                        deviceInfo.humState = (byte) query.getShort(10);
                        deviceInfo.fanState = (byte) query.getShort(11);
                        deviceInfo.fanType = query.getInt(12);
                        deviceInfo.port = (byte) query.getShort(13);
                        deviceInfo.choosePort = (byte) query.getShort(14);
                        deviceInfo.isPlug = query.getInt(15) != 0;
                        deviceInfo.version = (byte) query.getShort(16);
                        if (query.isNull(17)) {
                            deviceInfo.softwareVersion = null;
                        } else {
                            deviceInfo.softwareVersion = query.getString(17);
                        }
                        if (query.isNull(18)) {
                            deviceInfo.hardwareVersion = null;
                        } else {
                            deviceInfo.hardwareVersion = query.getString(18);
                        }
                        if (query.isNull(19)) {
                            deviceInfo.firmwareVersion = null;
                        } else {
                            deviceInfo.firmwareVersion = query.getString(19);
                        }
                        if (query.isNull(20)) {
                            deviceInfo.deviceId = null;
                        } else {
                            deviceInfo.deviceId = query.getString(20);
                        }
                        deviceInfo.isShare = (byte) query.getShort(21);
                        if (query.isNull(22)) {
                            deviceInfo.shareEmail = null;
                        } else {
                            deviceInfo.shareEmail = query.getString(22);
                        }
                        deviceInfo.triggerNotifyTime = query.getLong(23);
                        deviceInfo.leafTemperatureC = (byte) query.getShort(24);
                        deviceInfo.leafTemperatureF = (byte) query.getShort(25);
                        deviceInfo.vpd = query.getInt(26);
                        deviceInfo.vpdState = (byte) query.getShort(27);
                        deviceInfo.workType = (byte) query.getShort(28);
                        if (query.getInt(29) == 0) {
                            z = false;
                        }
                        deviceInfo.isAdv = z;
                        deviceInfo.portType = query.getInt(30);
                        deviceInfo.connectType = (byte) query.getShort(31);
                        arrayList.add(deviceInfo);
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public DeviceInfo checkDB() {
        boolean z = false;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac,name, typeName, type, connectTime, tmp, hum, fan,isDegree, tmpState, humState,fanState,fanType, port,choosePort, isPlug,version,softwareVersion,hardwareVersion,firmwareVersion,deviceId,isShare,shareEmail,triggerNotifyTime,leafTemperatureC,leafTemperatureF,vpd,vpdState,workType,isAdv,portType,connectType from Device order by sequence asc", 0);
        this.__db.assertNotSuspendingTransaction();
        DeviceInfo deviceInfo = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                DeviceInfo deviceInfo2 = new DeviceInfo();
                if (query.isNull(0)) {
                    deviceInfo2.mac = null;
                } else {
                    deviceInfo2.mac = query.getString(0);
                }
                if (query.isNull(1)) {
                    deviceInfo2.name = null;
                } else {
                    deviceInfo2.name = query.getString(1);
                }
                if (query.isNull(2)) {
                    deviceInfo2.typeName = null;
                } else {
                    deviceInfo2.typeName = query.getString(2);
                }
                deviceInfo2.type = (byte) query.getShort(3);
                deviceInfo2.connectTime = query.getLong(4);
                deviceInfo2.tmp = query.getInt(5);
                deviceInfo2.hum = query.getInt(6);
                deviceInfo2.fan = query.getInt(7);
                deviceInfo2.isDegree = query.getInt(8) != 0;
                deviceInfo2.tmpState = (byte) query.getShort(9);
                deviceInfo2.humState = (byte) query.getShort(10);
                deviceInfo2.fanState = (byte) query.getShort(11);
                deviceInfo2.fanType = query.getInt(12);
                deviceInfo2.port = (byte) query.getShort(13);
                deviceInfo2.choosePort = (byte) query.getShort(14);
                deviceInfo2.isPlug = query.getInt(15) != 0;
                deviceInfo2.version = (byte) query.getShort(16);
                if (query.isNull(17)) {
                    deviceInfo2.softwareVersion = null;
                } else {
                    deviceInfo2.softwareVersion = query.getString(17);
                }
                if (query.isNull(18)) {
                    deviceInfo2.hardwareVersion = null;
                } else {
                    deviceInfo2.hardwareVersion = query.getString(18);
                }
                if (query.isNull(19)) {
                    deviceInfo2.firmwareVersion = null;
                } else {
                    deviceInfo2.firmwareVersion = query.getString(19);
                }
                if (query.isNull(20)) {
                    deviceInfo2.deviceId = null;
                } else {
                    deviceInfo2.deviceId = query.getString(20);
                }
                deviceInfo2.isShare = (byte) query.getShort(21);
                if (query.isNull(22)) {
                    deviceInfo2.shareEmail = null;
                } else {
                    deviceInfo2.shareEmail = query.getString(22);
                }
                deviceInfo2.triggerNotifyTime = query.getLong(23);
                deviceInfo2.leafTemperatureC = (byte) query.getShort(24);
                deviceInfo2.leafTemperatureF = (byte) query.getShort(25);
                deviceInfo2.vpd = query.getInt(26);
                deviceInfo2.vpdState = (byte) query.getShort(27);
                deviceInfo2.workType = (byte) query.getShort(28);
                if (query.getInt(29) != 0) {
                    z = true;
                }
                deviceInfo2.isAdv = z;
                deviceInfo2.portType = query.getInt(30);
                deviceInfo2.connectType = (byte) query.getShort(31);
                deviceInfo = deviceInfo2;
            }
            return deviceInfo;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Flowable<DeviceInfo> loadDeviceInfo(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac,name, typeName, type, connectTime, tmp, hum, fan,isDegree, tmpState, humState,fanState, fanType, port,choosePort, isPlug,version,softwareVersion,hardwareVersion,firmwareVersion,deviceId,isShare,shareEmail,triggerNotifyTime,leafTemperatureC,leafTemperatureF,vpd,vpdState,workType,isAdv,portType,connectType from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"device"}, new Callable<DeviceInfo>() {
            public DeviceInfo call() throws Exception {
                boolean z = false;
                DeviceInfo deviceInfo = null;
                Cursor query = DBUtil.query(DeviceDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    if (query.moveToFirst()) {
                        DeviceInfo deviceInfo2 = new DeviceInfo();
                        if (query.isNull(0)) {
                            deviceInfo2.mac = null;
                        } else {
                            deviceInfo2.mac = query.getString(0);
                        }
                        if (query.isNull(1)) {
                            deviceInfo2.name = null;
                        } else {
                            deviceInfo2.name = query.getString(1);
                        }
                        if (query.isNull(2)) {
                            deviceInfo2.typeName = null;
                        } else {
                            deviceInfo2.typeName = query.getString(2);
                        }
                        deviceInfo2.type = (byte) query.getShort(3);
                        deviceInfo2.connectTime = query.getLong(4);
                        deviceInfo2.tmp = query.getInt(5);
                        deviceInfo2.hum = query.getInt(6);
                        deviceInfo2.fan = query.getInt(7);
                        deviceInfo2.isDegree = query.getInt(8) != 0;
                        deviceInfo2.tmpState = (byte) query.getShort(9);
                        deviceInfo2.humState = (byte) query.getShort(10);
                        deviceInfo2.fanState = (byte) query.getShort(11);
                        deviceInfo2.fanType = query.getInt(12);
                        deviceInfo2.port = (byte) query.getShort(13);
                        deviceInfo2.choosePort = (byte) query.getShort(14);
                        deviceInfo2.isPlug = query.getInt(15) != 0;
                        deviceInfo2.version = (byte) query.getShort(16);
                        if (query.isNull(17)) {
                            deviceInfo2.softwareVersion = null;
                        } else {
                            deviceInfo2.softwareVersion = query.getString(17);
                        }
                        if (query.isNull(18)) {
                            deviceInfo2.hardwareVersion = null;
                        } else {
                            deviceInfo2.hardwareVersion = query.getString(18);
                        }
                        if (query.isNull(19)) {
                            deviceInfo2.firmwareVersion = null;
                        } else {
                            deviceInfo2.firmwareVersion = query.getString(19);
                        }
                        if (query.isNull(20)) {
                            deviceInfo2.deviceId = null;
                        } else {
                            deviceInfo2.deviceId = query.getString(20);
                        }
                        deviceInfo2.isShare = (byte) query.getShort(21);
                        if (query.isNull(22)) {
                            deviceInfo2.shareEmail = null;
                        } else {
                            deviceInfo2.shareEmail = query.getString(22);
                        }
                        deviceInfo2.triggerNotifyTime = query.getLong(23);
                        deviceInfo2.leafTemperatureC = (byte) query.getShort(24);
                        deviceInfo2.leafTemperatureF = (byte) query.getShort(25);
                        deviceInfo2.vpd = query.getInt(26);
                        deviceInfo2.vpdState = (byte) query.getShort(27);
                        deviceInfo2.workType = (byte) query.getShort(28);
                        if (query.getInt(29) != 0) {
                            z = true;
                        }
                        deviceInfo2.isAdv = z;
                        deviceInfo2.portType = query.getInt(30);
                        deviceInfo2.connectType = (byte) query.getShort(31);
                        deviceInfo = deviceInfo2;
                    }
                    return deviceInfo;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public byte loadDeviceType(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select type from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        byte b = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                b = (byte) query.getShort(0);
            }
            return b;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<DeviceName> loadAllName() {
        String str;
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac, name, port,portType from device where port=0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    str = null;
                } else {
                    str = query.getString(0);
                }
                if (query.isNull(1)) {
                    str2 = null;
                } else {
                    str2 = query.getString(1);
                }
                arrayList.add(new DeviceName(str, str2, (byte) query.getShort(2), query.getInt(3)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<String> loadAllMac() {
        String str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac from device", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    str = null;
                } else {
                    str = query.getString(0);
                }
                arrayList.add(str);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public DeviceSetting loadSettings(String str, byte b) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select name, isDegree, brightness, transitionTemperature,transitionTemperatureC, transitionHumidity,calibrationTemperature, calibrationHumidity,typeOn,typeOff,tmp,hum,isShare,shareEmail,leafTemperatureC,leafTemperatureF,choosePort,portType,transitionVpd from device where mac=? and port=?", 2);
        boolean z = true;
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        this.__db.assertNotSuspendingTransaction();
        DeviceSetting deviceSetting = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                DeviceSetting deviceSetting2 = new DeviceSetting();
                if (query.isNull(0)) {
                    deviceSetting2.name = null;
                } else {
                    deviceSetting2.name = query.getString(0);
                }
                if (query.getInt(1) == 0) {
                    z = false;
                }
                deviceSetting2.isDegree = z;
                deviceSetting2.brightness = (byte) query.getShort(2);
                deviceSetting2.transitionTemperature = (byte) query.getShort(3);
                deviceSetting2.transitionTemperatureC = (byte) query.getShort(4);
                deviceSetting2.transitionHumidity = (byte) query.getShort(5);
                deviceSetting2.calibrationTemperature = (byte) query.getShort(6);
                deviceSetting2.calibrationHumidity = (byte) query.getShort(7);
                deviceSetting2.typeOn = (byte) query.getShort(8);
                deviceSetting2.typeOff = (byte) query.getShort(9);
                deviceSetting2.tmp = query.getInt(10);
                deviceSetting2.hum = query.getInt(11);
                deviceSetting2.isShare = (byte) query.getShort(12);
                if (query.isNull(13)) {
                    deviceSetting2.shareEmail = null;
                } else {
                    deviceSetting2.shareEmail = query.getString(13);
                }
                deviceSetting2.leafTemperatureC = (byte) query.getShort(14);
                deviceSetting2.leafTemperatureF = (byte) query.getShort(15);
                deviceSetting2.choosePort = (byte) query.getShort(16);
                deviceSetting2.portType = query.getInt(17);
                deviceSetting2.transitionVpd = (byte) query.getShort(18);
                deviceSetting = deviceSetting2;
            }
            return deviceSetting;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public PortCountAndLeafTmp loadLeafTemperatureC(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select leafTemperatureC,COUNT(*) AS portCount  from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        PortCountAndLeafTmp portCountAndLeafTmp = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                portCountAndLeafTmp = new PortCountAndLeafTmp();
                portCountAndLeafTmp.leafTemperatureC = (byte) query.getShort(0);
                portCountAndLeafTmp.portCount = query.getInt(1);
            }
            return portCountAndLeafTmp;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public DeviceTarget loadTarget(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select targetTmpC, targetTmpF, targetHum, targetVpd from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        DeviceTarget deviceTarget = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                deviceTarget = new DeviceTarget();
                deviceTarget.targetTmpC = query.getInt(0);
                deviceTarget.targetTmpF = query.getInt(1);
                deviceTarget.targetHum = query.getInt(2);
                deviceTarget.targetVpd = query.getInt(3);
            }
            return deviceTarget;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public DeviceModel loadDeviceModel(String str, byte b) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select isControlTypeByHum, workType, typeOff, typeOn, timeOff, timeOn, cycleOff, cycleOn, schedOff, schedOn, autoHighHum, autoHighTmpSwitch, autoLowHum, autoLowHumSwitch, autoHighTmp, autoHighTmpSwitch, autoLowTmp, autoLowTmpSwitch, autoHighHumSwitch, currentTypeResidueOn, currentTypeResidueTime, isDegree, fan, hum, tmp,fanState, humState,tmpState,fanType, port,choosePort,leafTemperatureC,vpd,vpdState,highVpd,highVpdSwitch,lowVpd,lowVpdSwitch,name,isPlug,isAdv,transitionTemperature,transitionTemperatureC,transitionHumidity from device where mac=? and port=?", 2);
        boolean z = true;
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        this.__db.assertNotSuspendingTransaction();
        DeviceModel deviceModel = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                DeviceModel deviceModel2 = new DeviceModel();
                deviceModel2.isControlTypeByHum = query.getInt(0) != 0;
                deviceModel2.workType = (byte) query.getShort(1);
                deviceModel2.typeOff = (byte) query.getShort(2);
                deviceModel2.typeOn = (byte) query.getShort(3);
                deviceModel2.timeOff = (char) query.getInt(4);
                deviceModel2.timeOn = (char) query.getInt(5);
                deviceModel2.cycleOff = (char) query.getInt(6);
                deviceModel2.cycleOn = (char) query.getInt(7);
                deviceModel2.schedOff = (char) query.getInt(8);
                deviceModel2.schedOn = (char) query.getInt(9);
                deviceModel2.autoHighHum = (byte) query.getShort(10);
                deviceModel2.autoHighTmpSwitch = query.getInt(11) != 0;
                deviceModel2.autoLowHum = (byte) query.getShort(12);
                deviceModel2.autoLowHumSwitch = query.getInt(13) != 0;
                deviceModel2.autoHighTmp = (byte) query.getShort(14);
                deviceModel2.autoHighTmpSwitch = query.getInt(11) != 0;
                deviceModel2.autoLowTmp = (byte) query.getShort(16);
                deviceModel2.autoLowTmpSwitch = query.getInt(17) != 0;
                deviceModel2.autoHighHumSwitch = query.getInt(18) != 0;
                deviceModel2.currentTypeResidueOn = query.getInt(19) != 0;
                deviceModel2.currentTypeResidueTime = query.getInt(20);
                deviceModel2.isDegree = query.getInt(21) != 0;
                deviceModel2.fan = query.getInt(22);
                deviceModel2.hum = query.getInt(23);
                deviceModel2.tmp = query.getInt(24);
                deviceModel2.fanState = (byte) query.getShort(25);
                deviceModel2.humState = (byte) query.getShort(26);
                deviceModel2.tmpState = (byte) query.getShort(27);
                deviceModel2.fanType = query.getInt(28);
                deviceModel2.port = (byte) query.getShort(29);
                deviceModel2.choosePort = (byte) query.getShort(30);
                deviceModel2.leafTemperatureC = (byte) query.getShort(31);
                deviceModel2.vpd = query.getInt(32);
                deviceModel2.vpdState = (byte) query.getShort(33);
                deviceModel2.highVpd = query.getInt(34);
                deviceModel2.highVpdSwitch = query.getInt(35) != 0;
                deviceModel2.lowVpd = query.getInt(36);
                deviceModel2.lowVpdSwitch = query.getInt(37) != 0;
                if (query.isNull(38)) {
                    deviceModel2.name = null;
                } else {
                    deviceModel2.name = query.getString(38);
                }
                deviceModel2.isPlug = query.getInt(39) != 0;
                if (query.getInt(40) == 0) {
                    z = false;
                }
                deviceModel2.isAdv = z;
                deviceModel2.transitionTemperature = (byte) query.getShort(41);
                deviceModel2.transitionTemperatureC = (byte) query.getShort(42);
                deviceModel2.transitionHumidity = (byte) query.getShort(43);
                deviceModel = deviceModel2;
            }
            return deviceModel;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<PortInfo> loadPortInfo(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select fan,fanState,fanType, port,name,isPlug,workType,isAdv,portType from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PortInfo portInfo = new PortInfo();
                portInfo.fan = query.getInt(0);
                portInfo.fanState = (byte) query.getShort(1);
                portInfo.fanType = query.getInt(2);
                portInfo.f138id = (byte) query.getShort(3);
                if (query.isNull(4)) {
                    portInfo.name = null;
                } else {
                    portInfo.name = query.getString(4);
                }
                portInfo.isPlug = query.getInt(5) != 0;
                portInfo.workType = (byte) query.getShort(6);
                portInfo.isAdv = query.getInt(7) != 0;
                portInfo.portType = query.getInt(8);
                arrayList.add(portInfo);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<PortSetting> loadPortSetting(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select fan,fanState,fanType, port,name,isPlug,workType,transitionTemperature,transitionTemperatureC,transitionHumidity,transitionVpd,isAdv,typeOn,typeOff,portType from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PortSetting portSetting = new PortSetting();
                portSetting.fan = query.getInt(0);
                portSetting.fanState = (byte) query.getShort(1);
                portSetting.fanType = query.getInt(2);
                portSetting.f138id = (byte) query.getShort(3);
                if (query.isNull(4)) {
                    portSetting.name = null;
                } else {
                    portSetting.name = query.getString(4);
                }
                portSetting.isPlug = query.getInt(5) != 0;
                portSetting.workType = (byte) query.getShort(6);
                portSetting.transitionTemperature = (byte) query.getShort(7);
                portSetting.transitionTemperatureC = (byte) query.getShort(8);
                portSetting.transitionHumidity = (byte) query.getShort(9);
                portSetting.transitionVpd = (byte) query.getShort(10);
                portSetting.isAdv = query.getInt(11) != 0;
                portSetting.typeOn = (byte) query.getShort(12);
                portSetting.typeOff = (byte) query.getShort(13);
                portSetting.portType = query.getInt(14);
                arrayList.add(portSetting);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Flowable<DeviceName> loadDeviceName(String str, byte b) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac, name, port,portType from device where mac=? and port=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        return RxRoom.createFlowable(this.__db, false, new String[]{"device"}, new Callable<DeviceName>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.eternal.base.concat.DeviceName} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
            /* JADX WARNING: type inference failed for: r3v0 */
            /* JADX WARNING: type inference failed for: r3v3 */
            /* JADX WARNING: type inference failed for: r3v5 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.eternal.base.concat.DeviceName call() throws java.lang.Exception {
                /*
                    r6 = this;
                    com.eternal.base.database.dao.DeviceDao_Impl r0 = com.eternal.base.database.dao.DeviceDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r1 = r0
                    r2 = 0
                    r3 = 0
                    android.database.Cursor r0 = androidx.room.util.DBUtil.query(r0, r1, r2, r3)
                    boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0041 }
                    if (r1 == 0) goto L_0x003d
                    boolean r1 = r0.isNull(r2)     // Catch:{ all -> 0x0041 }
                    if (r1 == 0) goto L_0x001c
                    r1 = r3
                    goto L_0x0020
                L_0x001c:
                    java.lang.String r1 = r0.getString(r2)     // Catch:{ all -> 0x0041 }
                L_0x0020:
                    r2 = 1
                    boolean r4 = r0.isNull(r2)     // Catch:{ all -> 0x0041 }
                    if (r4 == 0) goto L_0x0028
                    goto L_0x002c
                L_0x0028:
                    java.lang.String r3 = r0.getString(r2)     // Catch:{ all -> 0x0041 }
                L_0x002c:
                    r2 = 2
                    short r2 = r0.getShort(r2)     // Catch:{ all -> 0x0041 }
                    byte r2 = (byte) r2     // Catch:{ all -> 0x0041 }
                    r4 = 3
                    int r4 = r0.getInt(r4)     // Catch:{ all -> 0x0041 }
                    com.eternal.base.concat.DeviceName r5 = new com.eternal.base.concat.DeviceName     // Catch:{ all -> 0x0041 }
                    r5.<init>(r1, r3, r2, r4)     // Catch:{ all -> 0x0041 }
                    r3 = r5
                L_0x003d:
                    r0.close()
                    return r3
                L_0x0041:
                    r1 = move-exception
                    r0.close()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.eternal.base.database.dao.DeviceDao_Impl.C162523.call():com.eternal.base.concat.DeviceName");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<DeviceName>> loadDeviceName(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mac, name, port,portType from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"device"}, new Callable<List<DeviceName>>() {
            public List<DeviceName> call() throws Exception {
                String str;
                String str2;
                Cursor query = DBUtil.query(DeviceDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(0)) {
                            str = null;
                        } else {
                            str = query.getString(0);
                        }
                        if (query.isNull(1)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(1);
                        }
                        arrayList.add(new DeviceName(str, str2, (byte) query.getShort(2), query.getInt(3)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.eternal.base.concat.DeviceName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.eternal.base.concat.DeviceName getDeviceName(java.lang.String r6, byte r7) {
        /*
            r5 = this;
            java.lang.String r0 = "select mac, name, port,portType from device where mac=? and port=?"
            r1 = 2
            androidx.room.RoomSQLiteQuery r0 = androidx.room.RoomSQLiteQuery.acquire(r0, r1)
            r2 = 1
            if (r6 != 0) goto L_0x000e
            r0.bindNull(r2)
            goto L_0x0011
        L_0x000e:
            r0.bindString(r2, r6)
        L_0x0011:
            long r6 = (long) r7
            r0.bindLong(r1, r6)
            androidx.room.RoomDatabase r6 = r5.__db
            r6.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r6 = r5.__db
            r7 = 0
            r3 = 0
            android.database.Cursor r6 = androidx.room.util.DBUtil.query(r6, r0, r7, r3)
            boolean r4 = r6.moveToFirst()     // Catch:{ all -> 0x0056 }
            if (r4 == 0) goto L_0x004f
            boolean r4 = r6.isNull(r7)     // Catch:{ all -> 0x0056 }
            if (r4 == 0) goto L_0x0030
            r7 = r3
            goto L_0x0034
        L_0x0030:
            java.lang.String r7 = r6.getString(r7)     // Catch:{ all -> 0x0056 }
        L_0x0034:
            boolean r4 = r6.isNull(r2)     // Catch:{ all -> 0x0056 }
            if (r4 == 0) goto L_0x003b
            goto L_0x003f
        L_0x003b:
            java.lang.String r3 = r6.getString(r2)     // Catch:{ all -> 0x0056 }
        L_0x003f:
            short r1 = r6.getShort(r1)     // Catch:{ all -> 0x0056 }
            byte r1 = (byte) r1     // Catch:{ all -> 0x0056 }
            r2 = 3
            int r2 = r6.getInt(r2)     // Catch:{ all -> 0x0056 }
            com.eternal.base.concat.DeviceName r4 = new com.eternal.base.concat.DeviceName     // Catch:{ all -> 0x0056 }
            r4.<init>(r7, r3, r1, r2)     // Catch:{ all -> 0x0056 }
            r3 = r4
        L_0x004f:
            r6.close()
            r0.release()
            return r3
        L_0x0056:
            r7 = move-exception
            r6.close()
            r0.release()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.base.database.dao.DeviceDao_Impl.getDeviceName(java.lang.String, byte):com.eternal.base.concat.DeviceName");
    }

    public String getSoftwareVersion(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select softwareVersion from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        String str2 = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    str2 = query.getString(0);
                }
            }
            return str2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public DeviceModelInfo loadModelInfo(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select isDegree, schedOff, schedOn, cycleOn, cycleOff, timeOn, timeOff, autoHighHum, autoHighTmp, autoLowHum, autoLowTmp , autoHighTmpSwitch, autoLowTmpSwitch,autoHighHumSwitch, autoLowHumSwitch from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        DeviceModelInfo deviceModelInfo = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                deviceModelInfo = new DeviceModelInfo();
                deviceModelInfo.isDegree = query.getInt(0) != 0;
                deviceModelInfo.schedOff = (byte) query.getShort(1);
                deviceModelInfo.schedOn = (byte) query.getShort(2);
                deviceModelInfo.cycleOn = (char) query.getInt(3);
                deviceModelInfo.cycleOff = (char) query.getInt(4);
                deviceModelInfo.timeOn = (char) query.getInt(5);
                deviceModelInfo.timeOff = (char) query.getInt(6);
                deviceModelInfo.autoHighHum = (byte) query.getShort(7);
                deviceModelInfo.autoHighTmp = (byte) query.getShort(8);
                deviceModelInfo.autoLowHum = (byte) query.getShort(9);
                deviceModelInfo.autoLowTmp = (byte) query.getShort(10);
                deviceModelInfo.autoHighTmpSwitch = query.getInt(11) != 0;
                deviceModelInfo.autoLowTmpSwitch = query.getInt(12) != 0;
                deviceModelInfo.autoHighHumSwitch = query.getInt(13) != 0;
                if (query.getInt(14) == 0) {
                    z = false;
                }
                deviceModelInfo.autoLowHumSwitch = z;
            }
            return deviceModelInfo;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public long loadLogTime(String str, byte b) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select logTime from device where mac=? and port=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public long loadLogTime(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select logTime from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public long loadDeviceHistoryTime(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select historyTime from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public boolean isDegree(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select isDegree from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        boolean z2 = false;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                if (query.getInt(0) == 0) {
                    z = false;
                }
                z2 = z;
            }
            return z2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Flowable<Long> getConnectTime(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select connectTime from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"device"}, new Callable<Long>() {
            public Long call() throws Exception {
                Long l = null;
                Cursor query = DBUtil.query(DeviceDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    if (query.moveToFirst()) {
                        if (!query.isNull(0)) {
                            l = Long.valueOf(query.getLong(0));
                        }
                    }
                    return l;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<ConnectInfo> getConnectInfo(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select connectTime,connectType,deviceId from device where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"device"}, new Callable<ConnectInfo>() {
            public ConnectInfo call() throws Exception {
                ConnectInfo connectInfo = null;
                Cursor query = DBUtil.query(DeviceDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    if (query.moveToFirst()) {
                        ConnectInfo connectInfo2 = new ConnectInfo();
                        connectInfo2.connectTime = query.getLong(0);
                        connectInfo2.connectType = (byte) query.getShort(1);
                        if (query.isNull(2)) {
                            connectInfo2.deviceId = null;
                        } else {
                            connectInfo2.deviceId = query.getString(2);
                        }
                        connectInfo = connectInfo2;
                    }
                    return connectInfo;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
