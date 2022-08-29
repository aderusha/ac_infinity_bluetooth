package com.eternal.base.data;

import com.eternal.base.data.ble.BleController;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.data.ble.IBleControl;
import com.eternal.base.data.source.DeviceSource;
import com.eternal.base.data.source.HistorySource;
import com.eternal.base.data.source.LogSource;
import com.eternal.base.data.source.NotificationSource;

public class RepositoryInjection {
    private static IBleControl control;

    /* access modifiers changed from: private */
    public static IBleControl getBleControl() {
        if (control == null) {
            control = new BleController(BleServer.getInstance());
        }
        return control;
    }

    public static DeviceRepository providerDeviceRepository() {
        return DeviceContainer.repository;
    }

    public static NotificationRepository providerNotificationRepository() {
        return NotificationContainer.repository;
    }

    public static HistoryRepository providerHistoryRepository() {
        return HistoryContainer.repository;
    }

    public static LogRepository providerLogRepository() {
        return LogContainer.repository;
    }

    private static class DeviceContainer {
        /* access modifiers changed from: private */
        public static DeviceRepository repository = new DeviceRepository(new DeviceSource(), RepositoryInjection.getBleControl());

        private DeviceContainer() {
        }
    }

    private static class NotificationContainer {
        /* access modifiers changed from: private */
        public static NotificationRepository repository = new NotificationRepository(new NotificationSource(), RepositoryInjection.getBleControl());

        private NotificationContainer() {
        }
    }

    private static class HistoryContainer {
        /* access modifiers changed from: private */
        public static HistoryRepository repository = new HistoryRepository(new HistorySource(), RepositoryInjection.getBleControl());

        private HistoryContainer() {
        }
    }

    private static class LogContainer {
        /* access modifiers changed from: private */
        public static LogRepository repository = new LogRepository(new LogSource(), RepositoryInjection.getBleControl());

        private LogContainer() {
        }
    }
}
