package com.eternal.base.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import com.eternal.base.data.source.BooleanConverters;
import com.eternal.base.database.dao.DeviceDao;
import com.eternal.base.database.dao.HistoryDao;
import com.eternal.base.database.dao.LogDao;
import com.eternal.base.database.dao.NotificationDao;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import java.util.List;
import kotlinx.coroutines.DebugKt;

public abstract class BaseDatabase extends RoomDatabase {
    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `targetTmpC` INTEGER NOT NULL DEFAULT -32768");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `targetTmpF` INTEGER NOT NULL DEFAULT -32768");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `targetHum` INTEGER NOT NULL DEFAULT -32768");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `targetVpd` INTEGER NOT NULL DEFAULT -32768");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `highVpdSwitch` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `lowVpdSwitch` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `highVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `lowVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Log_backup` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `time` INTEGER NOT NULL, `logType` INTEGER NOT NULL, `notifyId` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `model` INTEGER NOT NULL, `fan` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hVpd` INTEGER NOT NULL DEFAULT 0, `lVpd` INTEGER NOT NULL DEFAULT 0, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `on` INTEGER NOT NULL, `off` INTEGER NOT NULL, `isStart` INTEGER NOT NULL, `isDelete` INTEGER NOT NULL DEFAULT 0, `isRead` INTEGER NOT NULL DEFAULT 0, `port` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `id`, `model`, `port`, `logType`))");
            supportSQLiteDatabase.execSQL("INSERT INTO `Log_backup`(`mac`,`id`,`time`,`logType`,`notifyId`,`start`,`end`,`model`,`fan`,`tmpHum`,`hTmpF`,`lTmpF`,`hTmpC`,`lTmpC`,`hVpd`,`lVpd`,`hHum`,`lHum`,`on`,`off`,`isStart`,`isDelete`,`isRead`,`port`) SELECT * from `Log`");
            supportSQLiteDatabase.execSQL("DROP TABLE `Log`");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log_backup` RENAME TO Log");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `transitionTemperatureC` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `isAdv` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log` ADD COLUMN `netId` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `portType` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `bufferTemp` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `bufferTempC` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `bufferHum` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `bufferVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `transitionVpd` INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_11_12 = new Migration(11, 12) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Notification_backup` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `name` TEXT, `open` INTEGER NOT NULL, `type` INTEGER NOT NULL, `model` INTEGER NOT NULL, `levelOn` INTEGER NOT NULL, `levelOff` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `cycleOn` INTEGER NOT NULL, `cycleOff` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `hVpd` INTEGER NOT NULL DEFAULT 0, `lVpd` INTEGER NOT NULL DEFAULT 0, `port` INTEGER NOT NULL DEFAULT 0, `advId` TEXT, `devId` TEXT, `devCode` TEXT, `nameCode` INTEGER NOT NULL, `codeAddress` INTEGER NOT NULL, `advActive` INTEGER NOT NULL DEFAULT 0,`groupIndex` INTEGER NOT NULL DEFAULT 0,`childId` INTEGER NOT NULL DEFAULT 0,`childIndex` INTEGER NOT NULL DEFAULT 0,`day` INTEGER NOT NULL DEFAULT 0,`beeps` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `id`, `type`, `port`, `childId`))");
            supportSQLiteDatabase.execSQL("INSERT INTO `Notification_backup`(`mac`,`id`,`name`,`open`,`type`,`model`,`levelOn`,`levelOff`,`tmpHum`,`start`,`end`,`cycleOn`,`cycleOff`,`hTmpC`,`lTmpC`,`hTmpF`,`lTmpF`,`hHum`,`lHum`,`hVpd`,`lVpd`,`port`,`advId`,`devId`,`devCode`,`nameCode`,`codeAddress`,`groupIndex`) SELECT `mac`,`id`,`name`,`open`,`type`,`model`,`levelOn`,`levelOff`,`tmpHum`,`start`,`end`,`cycleOn`,`cycleOff`,`hTmpC`,`lTmpC`,`hTmpF`,`lTmpF`,`hHum`,`lHum`,`hVpd`,`lVpd`,`port`,`advId`,`devId`,`devCode`,`nameCode`,`codeAddress`,`id` as `groupIndex` from `Notification`");
            supportSQLiteDatabase.execSQL("DROP TABLE `Notification`");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification_backup` RENAME TO Notification");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log` ADD COLUMN `day` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `connectType` INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Log_backup` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `time` INTEGER NOT NULL, `logType` INTEGER NOT NULL, `notifyId` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `model` INTEGER NOT NULL, `fan` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `on` INTEGER NOT NULL, `off` INTEGER NOT NULL, `isStart` INTEGER NOT NULL, PRIMARY KEY(`mac`, `id`, `model`))");
            supportSQLiteDatabase.execSQL("INSERT INTO `Log_backup`(`mac`,`id`,`time`,`logType`,`notifyId`,`start`,`end`,`model`,`fan`,`tmpHum`,`hTmpF`,`lTmpF`,`hTmpC`,`lTmpC`,`hHum`,`lHum`,`on`,`off`,`isStart`) SELECT * from `Log`");
            supportSQLiteDatabase.execSQL("DROP TABLE `Log`");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log_backup` RENAME TO Log");
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `History` ADD COLUMN `portStateList` TEXT");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Device_backup` (`mac` TEXT NOT NULL, `name` TEXT, `type` INTEGER NOT NULL, `connectTime` INTEGER NOT NULL, `typeName` TEXT, `tmp` INTEGER NOT NULL, `isDegree` INTEGER NOT NULL, `tmpState` INTEGER NOT NULL, `hum` INTEGER NOT NULL, `humState` INTEGER NOT NULL, `fan` INTEGER NOT NULL, `fanState` INTEGER NOT NULL, `brightness` INTEGER NOT NULL, `transitionTemperature` INTEGER NOT NULL, `transitionHumidity` INTEGER NOT NULL, `calibrationTemperature` INTEGER NOT NULL, `calibrationHumidity` INTEGER NOT NULL, `historyTime` INTEGER NOT NULL, `logTime` INTEGER NOT NULL, `isControlTypeByHum` INTEGER NOT NULL, `workType` INTEGER NOT NULL, `typeOn` INTEGER NOT NULL, `typeOff` INTEGER NOT NULL, `timeOn` INTEGER NOT NULL, `timeOff` INTEGER NOT NULL, `cycleOn` INTEGER NOT NULL, `cycleOff` INTEGER NOT NULL, `schedOn` INTEGER NOT NULL, `schedOff` INTEGER NOT NULL, `autoHighHum` INTEGER NOT NULL, `autoLowHum` INTEGER NOT NULL, `autoHighTmp` INTEGER NOT NULL, `autoLowTmp` INTEGER NOT NULL, `autoHighTmpSwitch` INTEGER NOT NULL, `autoLowTmpSwitch` INTEGER NOT NULL, `autoHighHumSwitch` INTEGER NOT NULL, `autoLowHumSwitch` INTEGER NOT NULL, `currentTypeResidueTime` INTEGER NOT NULL, `currentTypeResidueOn` INTEGER NOT NULL, `fanType` INTEGER NOT NULL DEFAULT 0, `port` INTEGER NOT NULL DEFAULT 0, `choosePort` INTEGER NOT NULL DEFAULT 0, `isPlug` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `port`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Notification_backup` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `name` TEXT, `open` INTEGER NOT NULL, `type` INTEGER NOT NULL, `model` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `cycleOn` INTEGER NOT NULL, `cycleOff` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `port` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `id`, `type`, `port`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Log_backup` (`mac` TEXT NOT NULL, `id` INTEGER NOT NULL, `time` INTEGER NOT NULL, `logType` INTEGER NOT NULL, `notifyId` INTEGER NOT NULL, `start` INTEGER NOT NULL, `end` INTEGER NOT NULL, `model` INTEGER NOT NULL, `fan` INTEGER NOT NULL, `tmpHum` INTEGER NOT NULL, `hTmpF` INTEGER NOT NULL, `lTmpF` INTEGER NOT NULL, `hTmpC` INTEGER NOT NULL, `lTmpC` INTEGER NOT NULL, `hHum` INTEGER NOT NULL, `lHum` INTEGER NOT NULL, `on` INTEGER NOT NULL, `off` INTEGER NOT NULL, `isStart` INTEGER NOT NULL, `port` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`mac`, `id`, `model`, `port`))");
            supportSQLiteDatabase.execSQL("INSERT INTO `Device_backup`(`mac`,`name`,`type`,`connectTime`,`typeName`,`tmp`,`isDegree`,`tmpState`,`hum`,`humState`,`fan`,`fanState`,`brightness`,`transitionTemperature`,`transitionHumidity`,`calibrationTemperature`,`calibrationHumidity`,`historyTime`,`logTime`,`isControlTypeByHum`,`workType`,`typeOn`,`typeOff`,`timeOn`,`timeOff`,`cycleOn`,`cycleOff`,`schedOn`,`schedOff`,`autoHighHum`,`autoLowHum`,`autoHighTmp`,`autoLowTmp`,`autoHighTmpSwitch`,`autoLowTmpSwitch`,`autoHighHumSwitch`,`autoLowHumSwitch`,`currentTypeResidueTime`,`currentTypeResidueOn`) SELECT * from `Device`");
            supportSQLiteDatabase.execSQL("INSERT INTO `Notification_backup`(`mac`,`id`,`name`,`open`,`type`,`model`,`tmpHum`,`start`,`end`,`cycleOn`,`cycleOff`,`hTmpC`,`lTmpC`,`hTmpF`,`lTmpF`,`hHum`,`lHum`) SELECT * from `Notification`");
            supportSQLiteDatabase.execSQL("INSERT INTO `Log_backup`(`mac`,`id`,`time`,`logType`,`notifyId`,`start`,`end`,`model`,`fan`,`tmpHum`,`hTmpF`,`lTmpF`,`hTmpC`,`lTmpC`,`hHum`,`lHum`,`on`,`off`,`isStart`) SELECT * from `Log`");
            supportSQLiteDatabase.execSQL("DROP TABLE `Device`");
            supportSQLiteDatabase.execSQL("DROP TABLE `Notification`");
            supportSQLiteDatabase.execSQL("DROP TABLE `Log`");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device_backup` RENAME TO Device");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification_backup` RENAME TO Notification");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log_backup` RENAME TO Log");
        }
    };
    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `version` INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `deviceId` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `isShare` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `sequence` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log` ADD COLUMN `isDelete` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log` ADD COLUMN `isRead` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `History` ADD COLUMN `isDelete` INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `softwareVersion` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `hardwareVersion` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `firmwareVersion` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `levelOn` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `levelOff` INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            List<Boolean> stringToBooleanList;
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `devId` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `advId` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `devCode` TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `nameCode` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `codeAddress` INTEGER NOT NULL DEFAULT 0");
            boolean inTransaction = supportSQLiteDatabase.inTransaction();
            KLog.m62d("Performing database migration from SQLiteHelper to Room");
            if (!inTransaction) {
                supportSQLiteDatabase.beginTransaction();
            }
            Cursor query = supportSQLiteDatabase.query("SELECT * FROM History");
            DatabaseUtils.dumpCursor(query);
            query.close();
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `History_backup` (`time` INTEGER NOT NULL, `mac` TEXT NOT NULL, `tmp` INTEGER NOT NULL, `hum` INTEGER NOT NULL, `off` INTEGER NOT NULL, `isDelete` INTEGER NOT NULL DEFAULT 0, `portState` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`time`, `mac`))");
            Cursor query2 = supportSQLiteDatabase.query("SELECT * FROM `History`");
            DatabaseUtils.dumpCursor(query2);
            ContentValues contentValues = new ContentValues();
            while (query2.moveToNext()) {
                contentValues.clear();
                contentValues.put("time", Long.valueOf(query2.getLong(query2.getColumnIndex("time"))));
                contentValues.put("mac", query2.getString(query2.getColumnIndex("mac")));
                contentValues.put("tmp", Long.valueOf(query2.getLong(query2.getColumnIndex("tmp"))));
                contentValues.put("hum", Long.valueOf(query2.getLong(query2.getColumnIndex("hum"))));
                contentValues.put(DebugKt.DEBUG_PROPERTY_VALUE_OFF, Integer.valueOf(query2.getInt(query2.getColumnIndex(DebugKt.DEBUG_PROPERTY_VALUE_OFF))));
                contentValues.put("isDelete", Integer.valueOf(query2.getInt(query2.getColumnIndex("isDelete"))));
                String string = query2.getString(query2.getColumnIndex("portStateList"));
                int i = 0;
                if (!(string == null || (stringToBooleanList = BooleanConverters.stringToBooleanList(string)) == null || stringToBooleanList.size() <= 0)) {
                    int i2 = 0;
                    while (i < stringToBooleanList.size()) {
                        if (stringToBooleanList.get(i) == Boolean.TRUE) {
                            i2 |= 1 << i;
                        }
                        i++;
                    }
                    i = i2;
                }
                contentValues.put("portState", Integer.valueOf(i));
                supportSQLiteDatabase.insert("`History_backup`", 5, contentValues);
            }
            query2.close();
            supportSQLiteDatabase.execSQL("DROP TABLE `History`");
            supportSQLiteDatabase.execSQL("ALTER TABLE `History_backup` RENAME TO History");
            if (!inTransaction) {
                supportSQLiteDatabase.setTransactionSuccessful();
                supportSQLiteDatabase.endTransaction();
            }
        }
    };
    static final Migration MIGRATION_7_8 = new Migration(7, 8) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `shareEmail` TEXT");
        }
    };
    static final Migration MIGRATION_8_9 = new Migration(8, 9) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `triggerNotifyTime` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `leafTemperatureC` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `leafTemperatureF` INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_9_10 = new Migration(9, 10) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `hVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Notification` ADD COLUMN `lVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log` ADD COLUMN `hVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Log` ADD COLUMN `lVpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `vpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `Device` ADD COLUMN `vpdState` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `History` ADD COLUMN `vpd` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `History` ADD COLUMN `portFan` INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("ALTER TABLE `History` ADD COLUMN `fan` INTEGER NOT NULL DEFAULT 0");
        }
    };

    public abstract DeviceDao deviceDao();

    public abstract HistoryDao historyDao();

    public abstract LogDao logDao();

    public abstract NotificationDao notificationDao();

    public static BaseDatabase getInstance() {
        return SingleContainer.INSTANCE;
    }

    public static void resetDB() {
        Utils.getContext().deleteDatabase("information_room.db");
        AppUtils.relaunchApp();
    }

    private static class SingleContainer {
        /* access modifiers changed from: private */
        public static BaseDatabase INSTANCE = Room.databaseBuilder(Utils.getContext(), BaseDatabase.class, "information_room.db").addMigrations(BaseDatabase.MIGRATION_1_2, BaseDatabase.MIGRATION_2_3, BaseDatabase.MIGRATION_3_4, BaseDatabase.MIGRATION_4_5, BaseDatabase.MIGRATION_5_6, BaseDatabase.MIGRATION_6_7, BaseDatabase.MIGRATION_7_8, BaseDatabase.MIGRATION_8_9, BaseDatabase.MIGRATION_9_10, BaseDatabase.MIGRATION_10_11, BaseDatabase.MIGRATION_11_12).fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }
        }).build();

        private SingleContainer() {
        }
    }
}
