package com.eternal.base.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import com.eternal.base.database.dao.DeviceDao;
import com.eternal.base.database.dao.DeviceDao_Impl;
import com.eternal.base.database.dao.HistoryDao;
import com.eternal.base.database.dao.HistoryDao_Impl;
import com.eternal.base.database.dao.LogDao;
import com.eternal.base.database.dao.LogDao_Impl;
import com.eternal.base.database.dao.NotificationDao;
import com.eternal.base.database.dao.NotificationDao_Impl;
import com.eternal.framework.http.cookie.SerializableCookie;
import com.telink.p010lt.ble.Device;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.coroutines.DebugKt;

public final class BaseDatabase_Impl extends BaseDatabase {
    private volatile DeviceDao _deviceDao;
    private volatile HistoryDao _historyDao;
    private volatile LogDao _logDao;
    private volatile NotificationDao _notificationDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(12) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Device` (`mac` TEXT NOT NULL, `deviceId` TEXT, `isShare` INTEGER NOT NULL, `name` TEXT, `type` INTEGER NOT NULL, `version` INTEGER NOT NULL, `firmwareVersion` TEXT, `hardwareVersion` TEXT, `softwareVersion` TEXT, `shareEmail` TEXT, `connectTime` INTEGER NOT NULL, `triggerNotifyTime` INTEGER NOT NULL DEFAULT 0, `typeName` TEXT, `tmp` INTEGER NOT NULL, `isDegree` INTEGER NOT NULL, `tmpState` INTEGER NOT NULL, `hum` INTEGER NOT NULL, `humState` INTEGER NOT NULL, `vpdState` INTEGER NOT NULL DEFAULT 0, `vpd` INTEGER NOT NULL DEFAULT 0, `fan` INTEGER NOT NULL, `fanState` INTEGER NOT NULL, `brightness` INTEGER NOT NULL, `transitionTemperature` INTEGER NOT NULL, `transitionTemperatureC` INTEGER NOT NULL DEFAULT 0, `transitionHumidity` INTEGER NOT NULL, `transitionVpd` INTEGER NOT NULL DEFAULT 0, `bufferTemp` INTEGER NOT NULL DEFAULT 0, `bufferTempC` INTEGER NOT NULL DEFAULT 0, `bufferHum` INTEGER NOT NULL DEFAULT 0, `bufferVpd` INTEGER NOT NULL DEFAULT 0, `calibrationTemperature` INTEGER NOT NULL, `calibrationHumidity` INTEGER NOT NULL, `historyTime` INTEGER NOT NULL, `logTime` INTEGER NOT NULL, `isControlTypeByHum` INTEGER NOT NULL, `workType` INTEGER NOT NULL, `typeOn` INTEGER NOT NULL, `typeOff` INTEGER NOT NULL, `timeOn` INTEGER NOT NULL, `timeOff` INTEGER NOT NULL, `cycleOn` INTEGER NOT NULL, `cycleOff` INTEGER NOT NULL, `schedOn` INTEGER NOT NULL, `schedOff` INTEGER NOT NULL, `autoHighHum` INTEGER NOT NULL, `autoLowHum` INTEGER NOT NULL, `autoHighTmp` INTEGER NOT NULL, `autoLowTmp` INTEGER NOT NULL, `autoHighTmpSwitch` INTEGER NOT NULL, `autoLowTmpSwitch` INTEGER NOT NULL, `autoHighHumSwitch` INTEGER NOT NULL, `autoLowHumSwitch` INTEGER NOT NULL, `currentTypeResidueTime` INTEGER NOT NULL, `currentTypeResidueOn` INTEGER NOT NULL, `fanType` INTEGER NOT NULL DEFAULT 0, `portType` INTEGER NOT NULL DEFAULT 0, `port` INTEGER NOT NULL DEFAULT 0, `choosePort` INTEGER NOT NULL DEFAULT 0, `isPlug` INTEGER NOT NULL DEFAULT 0, `sequence` INTEGER NOT NULL DEFAULT 0, `leafTemperatureC` INTEGER NOT NULL DEFAULT 0, `leafTemperatureF` INTEGER NOT NULL DEFAULT 0, `targetTmpC` INTEGER NOT NULL DEFAULT -32768, `targetTmpF` INTEGER NOT NULL DEFAULT -32768, `targetHum` INTEGER NOT NULL DEFAULT -32768, `targetVpd` INTEGER NOT NULL DEFAULT -32768, `highVpdSwitch` INTEGER NOT NULL DEFAULT 0, `lowVpdSwitch` INTEGER NOT NULL DEFAULT 0, `highVpd` INTEGER NOT NULL DEFAULT 0, `lowVpd` INTEGER NOT NULL DEFAULT 0, `isAdv` INTEGER NOT NULL DEFAULT 0, `connectType` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `port`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Notification` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `name` TEXT, `open` INTEGER NOT NULL, `type` INTEGER NOT NULL, `model` INTEGER NOT NULL, `levelOn` INTEGER NOT NULL, `levelOff` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `cycleOn` INTEGER NOT NULL, `cycleOff` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `hVpd` INTEGER NOT NULL DEFAULT 0, `lVpd` INTEGER NOT NULL DEFAULT 0, `port` INTEGER NOT NULL DEFAULT 0, `advId` TEXT, `devId` TEXT, `devCode` TEXT, `nameCode` INTEGER NOT NULL, `codeAddress` INTEGER NOT NULL, `advActive` INTEGER NOT NULL DEFAULT 0, `groupIndex` INTEGER NOT NULL DEFAULT 0, `childId` INTEGER NOT NULL DEFAULT 0, `childIndex` INTEGER NOT NULL DEFAULT 0, `day` INTEGER NOT NULL DEFAULT 0, `beeps` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `id`, `type`, `port`, `childId`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Log` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `time` INTEGER NOT NULL, `logType` INTEGER NOT NULL, `notifyId` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `day` INTEGER NOT NULL DEFAULT 0, `model` INTEGER NOT NULL, `fan` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hVpd` INTEGER NOT NULL DEFAULT 0, `lVpd` INTEGER NOT NULL DEFAULT 0, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `on` INTEGER NOT NULL, `off` INTEGER NOT NULL, `isStart` INTEGER NOT NULL, `isDelete` INTEGER NOT NULL DEFAULT 0, `isRead` INTEGER NOT NULL DEFAULT 0, `port` INTEGER NOT NULL DEFAULT 0, `netId` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `id`, `model`, `port`, `logType`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `History` (`time` INTEGER NOT NULL, `mac` TEXT NOT NULL, `tmp` INTEGER NOT NULL, `hum` INTEGER NOT NULL, `off` INTEGER NOT NULL, `isDelete` INTEGER NOT NULL DEFAULT 0, `portState` INTEGER NOT NULL DEFAULT 0, `portFan` INTEGER NOT NULL DEFAULT 0, `fan` INTEGER NOT NULL DEFAULT 0, `vpd` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`time`, `mac`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '213057c6a6e21b41cbdf0276cfbcf293')");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Device`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Notification`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Log`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `History`");
                if (BaseDatabase_Impl.this.mCallbacks != null) {
                    int size = BaseDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) BaseDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (BaseDatabase_Impl.this.mCallbacks != null) {
                    int size = BaseDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) BaseDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = BaseDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                BaseDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (BaseDatabase_Impl.this.mCallbacks != null) {
                    int size = BaseDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) BaseDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            /* access modifiers changed from: protected */
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(73);
                hashMap.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, (String) null, 1));
                hashMap.put("deviceId", new TableInfo.Column("deviceId", "TEXT", false, 0, (String) null, 1));
                hashMap.put("isShare", new TableInfo.Column("isShare", "INTEGER", true, 0, (String) null, 1));
                hashMap.put(SerializableCookie.NAME, new TableInfo.Column(SerializableCookie.NAME, "TEXT", false, 0, (String) null, 1));
                hashMap.put("type", new TableInfo.Column("type", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("version", new TableInfo.Column("version", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("firmwareVersion", new TableInfo.Column("firmwareVersion", "TEXT", false, 0, (String) null, 1));
                hashMap.put("hardwareVersion", new TableInfo.Column("hardwareVersion", "TEXT", false, 0, (String) null, 1));
                hashMap.put("softwareVersion", new TableInfo.Column("softwareVersion", "TEXT", false, 0, (String) null, 1));
                hashMap.put("shareEmail", new TableInfo.Column("shareEmail", "TEXT", false, 0, (String) null, 1));
                hashMap.put("connectTime", new TableInfo.Column("connectTime", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("triggerNotifyTime", new TableInfo.Column("triggerNotifyTime", "INTEGER", true, 0, "0", 1));
                hashMap.put("typeName", new TableInfo.Column("typeName", "TEXT", false, 0, (String) null, 1));
                hashMap.put("tmp", new TableInfo.Column("tmp", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("isDegree", new TableInfo.Column("isDegree", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("tmpState", new TableInfo.Column("tmpState", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("hum", new TableInfo.Column("hum", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("humState", new TableInfo.Column("humState", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("vpdState", new TableInfo.Column("vpdState", "INTEGER", true, 0, "0", 1));
                hashMap.put("vpd", new TableInfo.Column("vpd", "INTEGER", true, 0, "0", 1));
                hashMap.put("fan", new TableInfo.Column("fan", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("fanState", new TableInfo.Column("fanState", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("brightness", new TableInfo.Column("brightness", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("transitionTemperature", new TableInfo.Column("transitionTemperature", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("transitionTemperatureC", new TableInfo.Column("transitionTemperatureC", "INTEGER", true, 0, "0", 1));
                hashMap.put("transitionHumidity", new TableInfo.Column("transitionHumidity", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("transitionVpd", new TableInfo.Column("transitionVpd", "INTEGER", true, 0, "0", 1));
                hashMap.put("bufferTemp", new TableInfo.Column("bufferTemp", "INTEGER", true, 0, "0", 1));
                hashMap.put("bufferTempC", new TableInfo.Column("bufferTempC", "INTEGER", true, 0, "0", 1));
                hashMap.put("bufferHum", new TableInfo.Column("bufferHum", "INTEGER", true, 0, "0", 1));
                hashMap.put("bufferVpd", new TableInfo.Column("bufferVpd", "INTEGER", true, 0, "0", 1));
                hashMap.put("calibrationTemperature", new TableInfo.Column("calibrationTemperature", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("calibrationHumidity", new TableInfo.Column("calibrationHumidity", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("historyTime", new TableInfo.Column("historyTime", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("logTime", new TableInfo.Column("logTime", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("isControlTypeByHum", new TableInfo.Column("isControlTypeByHum", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("workType", new TableInfo.Column("workType", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("typeOn", new TableInfo.Column("typeOn", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("typeOff", new TableInfo.Column("typeOff", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("timeOn", new TableInfo.Column("timeOn", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("timeOff", new TableInfo.Column("timeOff", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("cycleOn", new TableInfo.Column("cycleOn", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("cycleOff", new TableInfo.Column("cycleOff", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("schedOn", new TableInfo.Column("schedOn", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("schedOff", new TableInfo.Column("schedOff", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoHighHum", new TableInfo.Column("autoHighHum", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoLowHum", new TableInfo.Column("autoLowHum", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoHighTmp", new TableInfo.Column("autoHighTmp", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoLowTmp", new TableInfo.Column("autoLowTmp", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoHighTmpSwitch", new TableInfo.Column("autoHighTmpSwitch", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoLowTmpSwitch", new TableInfo.Column("autoLowTmpSwitch", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoHighHumSwitch", new TableInfo.Column("autoHighHumSwitch", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("autoLowHumSwitch", new TableInfo.Column("autoLowHumSwitch", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("currentTypeResidueTime", new TableInfo.Column("currentTypeResidueTime", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("currentTypeResidueOn", new TableInfo.Column("currentTypeResidueOn", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("fanType", new TableInfo.Column("fanType", "INTEGER", true, 0, "0", 1));
                hashMap.put("portType", new TableInfo.Column("portType", "INTEGER", true, 0, "0", 1));
                hashMap.put("port", new TableInfo.Column("port", "INTEGER", true, 2, "0", 1));
                hashMap.put("choosePort", new TableInfo.Column("choosePort", "INTEGER", true, 0, "0", 1));
                hashMap.put("isPlug", new TableInfo.Column("isPlug", "INTEGER", true, 0, "0", 1));
                hashMap.put("sequence", new TableInfo.Column("sequence", "INTEGER", true, 0, "0", 1));
                hashMap.put("leafTemperatureC", new TableInfo.Column("leafTemperatureC", "INTEGER", true, 0, "0", 1));
                hashMap.put("leafTemperatureF", new TableInfo.Column("leafTemperatureF", "INTEGER", true, 0, "0", 1));
                hashMap.put("targetTmpC", new TableInfo.Column("targetTmpC", "INTEGER", true, 0, "-32768", 1));
                hashMap.put("targetTmpF", new TableInfo.Column("targetTmpF", "INTEGER", true, 0, "-32768", 1));
                hashMap.put("targetHum", new TableInfo.Column("targetHum", "INTEGER", true, 0, "-32768", 1));
                hashMap.put("targetVpd", new TableInfo.Column("targetVpd", "INTEGER", true, 0, "-32768", 1));
                hashMap.put("highVpdSwitch", new TableInfo.Column("highVpdSwitch", "INTEGER", true, 0, "0", 1));
                hashMap.put("lowVpdSwitch", new TableInfo.Column("lowVpdSwitch", "INTEGER", true, 0, "0", 1));
                hashMap.put("highVpd", new TableInfo.Column("highVpd", "INTEGER", true, 0, "0", 1));
                hashMap.put("lowVpd", new TableInfo.Column("lowVpd", "INTEGER", true, 0, "0", 1));
                hashMap.put("isAdv", new TableInfo.Column("isAdv", "INTEGER", true, 0, "0", 1));
                hashMap.put("connectType", new TableInfo.Column("connectType", "INTEGER", true, 0, "0", 1));
                TableInfo tableInfo = new TableInfo(Device.TAG, hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, Device.TAG);
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "Device(com.eternal.base.database.entity.Device).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(33);
                hashMap2.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, (String) null, 1));
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 2, (String) null, 1));
                hashMap2.put(SerializableCookie.NAME, new TableInfo.Column(SerializableCookie.NAME, "TEXT", false, 0, (String) null, 1));
                hashMap2.put("open", new TableInfo.Column("open", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("type", new TableInfo.Column("type", "INTEGER", true, 3, (String) null, 1));
                hashMap2.put("model", new TableInfo.Column("model", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("levelOn", new TableInfo.Column("levelOn", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("levelOff", new TableInfo.Column("levelOff", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("tmpHum", new TableInfo.Column("tmpHum", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("start", new TableInfo.Column("start", "INTEGER", true, 0, (String) null, 1));
                Object obj = "vpd";
                hashMap2.put("end", new TableInfo.Column("end", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("cycleOn", new TableInfo.Column("cycleOn", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("cycleOff", new TableInfo.Column("cycleOff", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("hTmpC", new TableInfo.Column("hTmpC", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("lTmpC", new TableInfo.Column("lTmpC", "INTEGER", true, 0, (String) null, 1));
                Object obj2 = "hum";
                hashMap2.put("hTmpF", new TableInfo.Column("hTmpF", "INTEGER", true, 0, (String) null, 1));
                Object obj3 = "tmp";
                hashMap2.put("lTmpF", new TableInfo.Column("lTmpF", "INTEGER", true, 0, (String) null, 1));
                Object obj4 = "lTmpC";
                hashMap2.put("hHum", new TableInfo.Column("hHum", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("lHum", new TableInfo.Column("lHum", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("hVpd", new TableInfo.Column("hVpd", "INTEGER", true, 0, "0", 1));
                hashMap2.put("lVpd", new TableInfo.Column("lVpd", "INTEGER", true, 0, "0", 1));
                hashMap2.put("port", new TableInfo.Column("port", "INTEGER", true, 4, "0", 1));
                hashMap2.put("advId", new TableInfo.Column("advId", "TEXT", false, 0, (String) null, 1));
                hashMap2.put("devId", new TableInfo.Column("devId", "TEXT", false, 0, (String) null, 1));
                hashMap2.put("devCode", new TableInfo.Column("devCode", "TEXT", false, 0, (String) null, 1));
                hashMap2.put("nameCode", new TableInfo.Column("nameCode", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("codeAddress", new TableInfo.Column("codeAddress", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("advActive", new TableInfo.Column("advActive", "INTEGER", true, 0, "0", 1));
                hashMap2.put("groupIndex", new TableInfo.Column("groupIndex", "INTEGER", true, 0, "0", 1));
                hashMap2.put("childId", new TableInfo.Column("childId", "INTEGER", true, 5, "0", 1));
                hashMap2.put("childIndex", new TableInfo.Column("childIndex", "INTEGER", true, 0, "0", 1));
                hashMap2.put("day", new TableInfo.Column("day", "INTEGER", true, 0, "0", 1));
                hashMap2.put("beeps", new TableInfo.Column("beeps", "INTEGER", true, 0, "0", 1));
                Object obj5 = "port";
                Object obj6 = "hTmpC";
                TableInfo tableInfo2 = new TableInfo("Notification", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "Notification");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "Notification(com.eternal.base.database.entity.Notification).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(26);
                hashMap3.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, (String) null, 1));
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 2, (String) null, 1));
                hashMap3.put("time", new TableInfo.Column("time", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("logType", new TableInfo.Column("logType", "INTEGER", true, 5, (String) null, 1));
                hashMap3.put("notifyId", new TableInfo.Column("notifyId", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("start", new TableInfo.Column("start", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("end", new TableInfo.Column("end", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("day", new TableInfo.Column("day", "INTEGER", true, 0, "0", 1));
                hashMap3.put("model", new TableInfo.Column("model", "INTEGER", true, 3, (String) null, 1));
                hashMap3.put("fan", new TableInfo.Column("fan", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("tmpHum", new TableInfo.Column("tmpHum", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("hTmpF", new TableInfo.Column("hTmpF", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("lTmpF", new TableInfo.Column("lTmpF", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put(obj6, new TableInfo.Column("hTmpC", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put(obj4, new TableInfo.Column("lTmpC", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("hVpd", new TableInfo.Column("hVpd", "INTEGER", true, 0, "0", 1));
                hashMap3.put("lVpd", new TableInfo.Column("lVpd", "INTEGER", true, 0, "0", 1));
                hashMap3.put("hHum", new TableInfo.Column("hHum", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("lHum", new TableInfo.Column("lHum", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put(DebugKt.DEBUG_PROPERTY_VALUE_ON, new TableInfo.Column(DebugKt.DEBUG_PROPERTY_VALUE_ON, "INTEGER", true, 0, (String) null, 1));
                hashMap3.put(DebugKt.DEBUG_PROPERTY_VALUE_OFF, new TableInfo.Column(DebugKt.DEBUG_PROPERTY_VALUE_OFF, "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("isStart", new TableInfo.Column("isStart", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("isDelete", new TableInfo.Column("isDelete", "INTEGER", true, 0, "0", 1));
                hashMap3.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, "0", 1));
                hashMap3.put(obj5, new TableInfo.Column("port", "INTEGER", true, 4, "0", 1));
                hashMap3.put("netId", new TableInfo.Column("netId", "INTEGER", true, 0, "0", 1));
                TableInfo tableInfo3 = new TableInfo("Log", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "Log");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "Log(com.eternal.base.database.entity.Log).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(10);
                hashMap4.put("time", new TableInfo.Column("time", "INTEGER", true, 1, (String) null, 1));
                hashMap4.put("mac", new TableInfo.Column("mac", "TEXT", true, 2, (String) null, 1));
                hashMap4.put(obj3, new TableInfo.Column("tmp", "INTEGER", true, 0, (String) null, 1));
                hashMap4.put(obj2, new TableInfo.Column("hum", "INTEGER", true, 0, (String) null, 1));
                hashMap4.put(DebugKt.DEBUG_PROPERTY_VALUE_OFF, new TableInfo.Column(DebugKt.DEBUG_PROPERTY_VALUE_OFF, "INTEGER", true, 0, (String) null, 1));
                hashMap4.put("isDelete", new TableInfo.Column("isDelete", "INTEGER", true, 0, "0", 1));
                hashMap4.put("portState", new TableInfo.Column("portState", "INTEGER", true, 0, "0", 1));
                hashMap4.put("portFan", new TableInfo.Column("portFan", "INTEGER", true, 0, "0", 1));
                hashMap4.put("fan", new TableInfo.Column("fan", "INTEGER", true, 0, "0", 1));
                hashMap4.put(obj, new TableInfo.Column("vpd", "INTEGER", true, 0, "0", 1));
                TableInfo tableInfo4 = new TableInfo("History", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "History");
                if (tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(true, (String) null);
                }
                return new RoomOpenHelper.ValidationResult(false, "History(com.eternal.base.database.entity.History).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
        }, "213057c6a6e21b41cbdf0276cfbcf293", "6916e5112b8987178afaddc03a09e341")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), Device.TAG, "Notification", "Log", "History");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Device`");
            writableDatabase.execSQL("DELETE FROM `Notification`");
            writableDatabase.execSQL("DELETE FROM `Log`");
            writableDatabase.execSQL("DELETE FROM `History`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceDao.class, DeviceDao_Impl.getRequiredConverters());
        hashMap.put(LogDao.class, LogDao_Impl.getRequiredConverters());
        hashMap.put(HistoryDao.class, HistoryDao_Impl.getRequiredConverters());
        hashMap.put(NotificationDao.class, NotificationDao_Impl.getRequiredConverters());
        return hashMap;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    public DeviceDao deviceDao() {
        DeviceDao deviceDao;
        if (this._deviceDao != null) {
            return this._deviceDao;
        }
        synchronized (this) {
            if (this._deviceDao == null) {
                this._deviceDao = new DeviceDao_Impl(this);
            }
            deviceDao = this._deviceDao;
        }
        return deviceDao;
    }

    public LogDao logDao() {
        LogDao logDao;
        if (this._logDao != null) {
            return this._logDao;
        }
        synchronized (this) {
            if (this._logDao == null) {
                this._logDao = new LogDao_Impl(this);
            }
            logDao = this._logDao;
        }
        return logDao;
    }

    public HistoryDao historyDao() {
        HistoryDao historyDao;
        if (this._historyDao != null) {
            return this._historyDao;
        }
        synchronized (this) {
            if (this._historyDao == null) {
                this._historyDao = new HistoryDao_Impl(this);
            }
            historyDao = this._historyDao;
        }
        return historyDao;
    }

    public NotificationDao notificationDao() {
        NotificationDao notificationDao;
        if (this._notificationDao != null) {
            return this._notificationDao;
        }
        synchronized (this) {
            if (this._notificationDao == null) {
                this._notificationDao = new NotificationDao_Impl(this);
            }
            notificationDao = this._notificationDao;
        }
        return notificationDao;
    }
}
