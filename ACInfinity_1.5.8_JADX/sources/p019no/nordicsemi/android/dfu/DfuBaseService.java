package p019no.nordicsemi.android.dfu;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.eternal.base.global.ActivityEvent;
import com.eternal.control.ControlCFragment;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import p019no.nordicsemi.android.dfu.DfuProgressInfo;
import p019no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import p019no.nordicsemi.android.dfu.internal.HexInputStream;

/* renamed from: no.nordicsemi.android.dfu.DfuBaseService */
public abstract class DfuBaseService extends IntentService implements DfuProgressInfo.ProgressListener {
    public static final int ACTION_ABORT = 2;
    public static final int ACTION_PAUSE = 0;
    public static final int ACTION_RESUME = 1;
    public static final String BROADCAST_ACTION = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION";
    public static final String BROADCAST_ERROR = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR";
    public static final String BROADCAST_LOG = "no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG";
    public static final String BROADCAST_PROGRESS = "no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS";
    static boolean DEBUG = false;
    public static final int ERROR_BLUETOOTH_DISABLED = 4106;
    public static final int ERROR_CONNECTION_MASK = 16384;
    public static final int ERROR_CONNECTION_STATE_MASK = 32768;
    public static final int ERROR_CRC_ERROR = 4109;
    public static final int ERROR_DEVICE_DISCONNECTED = 4096;
    public static final int ERROR_DEVICE_NOT_BONDED = 4110;
    public static final int ERROR_FILE_ERROR = 4098;
    public static final int ERROR_FILE_INVALID = 4099;
    public static final int ERROR_FILE_IO_EXCEPTION = 4100;
    public static final int ERROR_FILE_NOT_FOUND = 4097;
    public static final int ERROR_FILE_SIZE_INVALID = 4108;
    public static final int ERROR_FILE_TYPE_UNSUPPORTED = 4105;
    public static final int ERROR_INIT_PACKET_REQUIRED = 4107;
    public static final int ERROR_INVALID_RESPONSE = 4104;
    public static final int ERROR_MASK = 4096;
    public static final int ERROR_PROGRESS_LOST = 4111;
    public static final int ERROR_REMOTE_MASK = 8192;
    public static final int ERROR_REMOTE_TYPE_LEGACY = 256;
    public static final int ERROR_REMOTE_TYPE_SECURE = 512;
    public static final int ERROR_REMOTE_TYPE_SECURE_BUTTONLESS = 2048;
    public static final int ERROR_REMOTE_TYPE_SECURE_EXTENDED = 1024;
    public static final int ERROR_SERVICE_DISCOVERY_NOT_STARTED = 4101;
    public static final int ERROR_SERVICE_NOT_FOUND = 4102;
    public static final int ERROR_TYPE_COMMUNICATION = 2;
    public static final int ERROR_TYPE_COMMUNICATION_STATE = 1;
    public static final int ERROR_TYPE_DFU_REMOTE = 3;
    public static final int ERROR_TYPE_OTHER = 0;
    public static final String EXTRA_ACTION = "no.nordicsemi.android.dfu.extra.EXTRA_ACTION";
    public static final String EXTRA_AVG_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS";
    public static final String EXTRA_CURRENT_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU";
    public static final String EXTRA_DATA = "no.nordicsemi.android.dfu.extra.EXTRA_DATA";
    public static final String EXTRA_DATA_OBJECT_DELAY = "no.nordicsemi.android.dfu.extra.EXTRA_DATA_OBJECT_DELAY";
    public static final String EXTRA_DEVICE_ADDRESS = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS";
    public static final String EXTRA_DEVICE_NAME = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME";
    static final String EXTRA_DFU_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_DFU_ATTEMPT";
    public static final String EXTRA_DISABLE_NOTIFICATION = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION";
    public static final String EXTRA_DISABLE_RESUME = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME";
    public static final String EXTRA_ERROR_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE";
    public static final String EXTRA_FILE_MIME_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE";
    public static final String EXTRA_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH";
    public static final String EXTRA_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID";
    public static final String EXTRA_FILE_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE";
    public static final String EXTRA_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI";
    public static final String EXTRA_FORCE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU";
    public static final String EXTRA_FORCE_SCANNING_FOR_BOOTLOADER_IN_LEGACY_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_FORCE_SCANNING_FOR_BOOTLOADER_IN_LEGACY_DFU";
    public static final String EXTRA_FOREGROUND_SERVICE = "no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE";
    public static final String EXTRA_INIT_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH";
    public static final String EXTRA_INIT_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID";
    public static final String EXTRA_INIT_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI";
    public static final String EXTRA_KEEP_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND";
    public static final String EXTRA_LOG_LEVEL = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL";
    public static final String EXTRA_LOG_MESSAGE = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO";
    public static final String EXTRA_MAX_DFU_ATTEMPTS = "no.nordicsemi.android.dfu.extra.EXTRA_MAX_DFU_ATTEMPTS";
    public static final String EXTRA_MBR_SIZE = "no.nordicsemi.android.dfu.extra.EXTRA_MBR_SIZE";
    public static final String EXTRA_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_MTU";
    public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED";
    public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE";
    public static final String EXTRA_PARTS_TOTAL = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL";
    public static final String EXTRA_PART_CURRENT = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT";
    public static final String EXTRA_PROGRESS = "no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS";
    private static final String EXTRA_RECONNECTION_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_RECONNECTION_ATTEMPT";
    public static final String EXTRA_RESTORE_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND";
    public static final String EXTRA_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS";
    public static final String EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU";
    public static final int LOG_LEVEL_APPLICATION = 10;
    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_ERROR = 20;
    public static final int LOG_LEVEL_INFO = 5;
    public static final int LOG_LEVEL_VERBOSE = 1;
    public static final int LOG_LEVEL_WARNING = 15;
    public static final String MIME_TYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIME_TYPE_ZIP = "application/zip";
    public static final String NOTIFICATION_CHANNEL_DFU = "dfu";
    public static final int NOTIFICATION_ID = 283;
    public static final int PROGRESS_ABORTED = -7;
    public static final int PROGRESS_COMPLETED = -6;
    public static final int PROGRESS_CONNECTING = -1;
    public static final int PROGRESS_DISCONNECTING = -5;
    public static final int PROGRESS_ENABLING_DFU_MODE = -3;
    public static final int PROGRESS_STARTING = -2;
    public static final int PROGRESS_VALIDATING = -4;
    protected static final int STATE_CLOSED = -5;
    protected static final int STATE_CONNECTED = -2;
    protected static final int STATE_CONNECTED_AND_READY = -3;
    protected static final int STATE_CONNECTING = -1;
    protected static final int STATE_DISCONNECTED = 0;
    protected static final int STATE_DISCONNECTING = -4;
    private static final String TAG = "DfuBaseService";
    public static final int TYPE_APPLICATION = 4;
    public static final int TYPE_AUTO = 0;
    public static final int TYPE_BOOTLOADER = 2;
    public static final int TYPE_SOFT_DEVICE = 1;
    /* access modifiers changed from: private */
    public boolean mAborted;
    private BluetoothAdapter mBluetoothAdapter;
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 12);
            DfuBaseService dfuBaseService = DfuBaseService.this;
            dfuBaseService.logw("Action received: android.bluetooth.adapter.action.STATE_CHANGED [state: " + intExtra + ", previous state: " + intExtra2 + "]");
            if (intExtra2 != 12) {
                return;
            }
            if (intExtra == 13 || intExtra == 10) {
                DfuBaseService.this.sendLogBroadcast(15, "Bluetooth adapter disabled");
                DfuBaseService.this.mConnectionState = 0;
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                }
                synchronized (DfuBaseService.this.mLock) {
                    DfuBaseService.this.mLock.notifyAll();
                }
            }
        }
    };
    private final BroadcastReceiver mBondStateBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice != null && bluetoothDevice.getAddress().equals(DfuBaseService.this.mDeviceAddress) && (intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1)) != 11 && DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.onBondStateChanged(intExtra);
            }
        }
    };
    protected int mConnectionState;
    private final BroadcastReceiver mConnectionStateBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice != null && bluetoothDevice.getAddress().equals(DfuBaseService.this.mDeviceAddress)) {
                String action = intent.getAction();
                DfuBaseService dfuBaseService = DfuBaseService.this;
                dfuBaseService.logi("Action received: " + action);
                DfuBaseService dfuBaseService2 = DfuBaseService.this;
                dfuBaseService2.sendLogBroadcast(0, "[Broadcast] Action received: " + action);
            }
        }
    };
    /* access modifiers changed from: private */
    public String mDeviceAddress;
    private String mDeviceName;
    private final BroadcastReceiver mDfuActionReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_ACTION, 0);
            DfuBaseService dfuBaseService = DfuBaseService.this;
            dfuBaseService.logi("User action received: " + intExtra);
            if (intExtra == 0) {
                DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Pause action received");
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.pause();
                }
            } else if (intExtra == 1) {
                DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Resume action received");
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.resume();
                }
            } else if (intExtra == 2) {
                DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Abort action received");
                boolean unused = DfuBaseService.this.mAborted = true;
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.abort();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public DfuCallback mDfuServiceImpl;
    private boolean mDisableNotification;
    /* access modifiers changed from: private */
    public int mError;
    private InputStream mFirmwareInputStream;
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                if (i == 8 || i == 19) {
                    DfuBaseService dfuBaseService = DfuBaseService.this;
                    dfuBaseService.logw("Target device disconnected with status: " + i);
                } else {
                    DfuBaseService dfuBaseService2 = DfuBaseService.this;
                    dfuBaseService2.loge("Connection state change error: " + i + " newState: " + i2);
                }
                int unused = DfuBaseService.this.mError = i | 32768;
                if (i2 == 0) {
                    DfuBaseService.this.mConnectionState = 0;
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                    }
                }
            } else if (i2 == 2) {
                DfuBaseService.this.logi("Connected to GATT server");
                DfuBaseService dfuBaseService3 = DfuBaseService.this;
                dfuBaseService3.sendLogBroadcast(5, "Connected to " + DfuBaseService.this.mDeviceAddress);
                DfuBaseService.this.mConnectionState = -2;
                if (bluetoothGatt.getDevice().getBondState() == 12) {
                    DfuBaseService.this.logi("Waiting 1600 ms for a possible Service Changed indication...");
                    DfuBaseService.this.waitFor(1600);
                }
                DfuBaseService.this.sendLogBroadcast(1, "Discovering services...");
                DfuBaseService.this.sendLogBroadcast(0, "gatt.discoverServices()");
                boolean discoverServices = bluetoothGatt.discoverServices();
                DfuBaseService dfuBaseService4 = DfuBaseService.this;
                StringBuilder sb = new StringBuilder();
                sb.append("Attempting to start service discovery... ");
                sb.append(discoverServices ? "succeed" : "failed");
                dfuBaseService4.logi(sb.toString());
                if (!discoverServices) {
                    int unused2 = DfuBaseService.this.mError = DfuBaseService.ERROR_SERVICE_DISCOVERY_NOT_STARTED;
                } else {
                    return;
                }
            } else if (i2 == 0) {
                DfuBaseService.this.logi("Disconnected from GATT server");
                DfuBaseService.this.mConnectionState = 0;
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                }
            }
            synchronized (DfuBaseService.this.mLock) {
                DfuBaseService.this.mLock.notifyAll();
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                DfuBaseService.this.logi("Services discovered");
                DfuBaseService.this.mConnectionState = -3;
            } else {
                DfuBaseService dfuBaseService = DfuBaseService.this;
                dfuBaseService.loge("Service discovery error: " + i);
                int unused = DfuBaseService.this.mError = i | 16384;
            }
            synchronized (DfuBaseService.this.mLock) {
                DfuBaseService.this.mLock.notifyAll();
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            }
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onMtuChanged(bluetoothGatt, i, i2);
            }
        }

        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onPhyUpdate(bluetoothGatt, i, i2, i3);
            }
        }
    };
    private InputStream mInitFileInputStream;
    private long mLastNotificationTime;
    private int mLastProgress = -1;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    DfuProgressInfo mProgressInfo;

    /* access modifiers changed from: protected */
    public abstract Class<? extends Activity> getNotificationTarget();

    /* access modifiers changed from: protected */
    public boolean isDebug() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void updateErrorNotification(NotificationCompat.Builder builder) {
    }

    /* access modifiers changed from: protected */
    public void updateForegroundNotification(NotificationCompat.Builder builder) {
    }

    public DfuBaseService() {
        super(TAG);
    }

    private static IntentFilter makeDfuActionIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        return intentFilter;
    }

    public void onCreate() {
        super.onCreate();
        DEBUG = isDebug();
        logi("DFU service created. Version: 2.0.3");
        initialize();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        IntentFilter makeDfuActionIntentFilter = makeDfuActionIntentFilter();
        instance.registerReceiver(this.mDfuActionReceiver, makeDfuActionIntentFilter);
        registerReceiver(this.mDfuActionReceiver, makeDfuActionIntentFilter);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        registerReceiver(this.mConnectionStateBroadcastReceiver, intentFilter);
        registerReceiver(this.mBondStateBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(ActivityEvent.NOTIFICATION);
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
        stopSelf();
    }

    public void onDestroy() {
        super.onDestroy();
        DfuCallback dfuCallback = this.mDfuServiceImpl;
        if (dfuCallback != null) {
            dfuCallback.abort();
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mConnectionStateBroadcastReceiver);
        unregisterReceiver(this.mBondStateBroadcastReceiver);
        unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        try {
            InputStream inputStream = this.mFirmwareInputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            InputStream inputStream2 = this.mInitFileInputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.mFirmwareInputStream = null;
            this.mInitFileInputStream = null;
            throw th;
        }
        this.mFirmwareInputStream = null;
        this.mInitFileInputStream = null;
        logi("DFU service destroyed");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0444, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0446, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0447, code lost:
        r3 = r0;
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x044b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x044c, code lost:
        r3 = r0;
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x044f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0450, code lost:
        r15 = 4096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0452, code lost:
        r3 = r0;
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0456, code lost:
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0469, code lost:
        r4 = r4 & -32769;
        sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Error (0x%02X): %s", new java.lang.Object[]{java.lang.Integer.valueOf(r4), p019no.nordicsemi.android.error.GattError.parseConnectionError(r4)}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x048c, code lost:
        r4 = r4 & -16385;
        sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Error (0x%02X): %s", new java.lang.Object[]{java.lang.Integer.valueOf(r4), p019no.nordicsemi.android.error.GattError.parse(r4)}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x04ba, code lost:
        if (r2 != null) goto L_0x04bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:?, code lost:
        r2.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x04e5, code lost:
        r5 = new java.lang.StringBuilder();
        r5.append("Restarting the service (");
        r3 = r3 + 1;
        r5.append(r3);
        r5.append(" /");
        r5.append(r4);
        r5.append(")");
        logi(r5.toString());
        r4 = new android.content.Intent();
        r4.fillIn(r8, 24);
        r4.putExtra(EXTRA_DFU_ATTEMPT, r3);
        startService(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x051a, code lost:
        if (r2 != null) goto L_0x051c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:?, code lost:
        r2.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x051f, code lost:
        if (r12 != false) goto L_0x0521;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0521, code lost:
        stopForeground(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:?, code lost:
        report(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x0528, code lost:
        if (r2 != null) goto L_0x052a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x053c, code lost:
        if (r2 != null) goto L_0x04bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:?, code lost:
        r2.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0102, code lost:
        if (r4 < 0) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (r4 < 0) goto L_0x0110;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0217 A[Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181, all -> 0x017d }] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0249 A[SYNTHETIC, Splitter:B:143:0x0249] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0446 A[ExcHandler: all (r0v6 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:216:0x0405] */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x044b A[ExcHandler: DfuException (r0v5 'e' no.nordicsemi.android.dfu.internal.exception.DfuException A[CUSTOM_DECLARE]), Splitter:B:216:0x0405] */
    /* JADX WARNING: Removed duplicated region for block: B:246:? A[ExcHandler: UploadAbortedException (unused no.nordicsemi.android.dfu.internal.exception.UploadAbortedException), SYNTHETIC, Splitter:B:216:0x0405] */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x0469 A[Catch:{ all -> 0x0459 }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x048c A[Catch:{ all -> 0x0459 }] */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x04e5 A[Catch:{ all -> 0x0459 }] */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0525 A[SYNTHETIC, Splitter:B:272:0x0525] */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0542  */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x0548 A[SYNTHETIC, Splitter:B:282:0x0548] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05e3  */
    /* JADX WARNING: Removed duplicated region for block: B:332:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r25) {
        /*
            r24 = this;
            r1 = r24
            r8 = r25
            java.lang.String r2 = "Bluetooth adapter disabled"
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS"
            java.lang.String r3 = r8.getStringExtra(r3)
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME"
            java.lang.String r4 = r8.getStringExtra(r4)
            java.lang.String r5 = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION"
            r9 = 0
            boolean r10 = r8.getBooleanExtra(r5, r9)
            java.lang.String r5 = "no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE"
            r11 = 1
            boolean r12 = r8.getBooleanExtra(r5, r11)
            java.lang.String r5 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH"
            java.lang.String r5 = r8.getStringExtra(r5)
            java.lang.String r6 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI"
            android.os.Parcelable r6 = r8.getParcelableExtra(r6)
            android.net.Uri r6 = (android.net.Uri) r6
            java.lang.String r7 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID"
            int r7 = r8.getIntExtra(r7, r9)
            java.lang.String r13 = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH"
            java.lang.String r13 = r8.getStringExtra(r13)
            java.lang.String r14 = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI"
            android.os.Parcelable r14 = r8.getParcelableExtra(r14)
            android.net.Uri r14 = (android.net.Uri) r14
            java.lang.String r15 = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID"
            int r15 = r8.getIntExtra(r15, r9)
            java.lang.String r11 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE"
            int r11 = r8.getIntExtra(r11, r9)
            if (r5 == 0) goto L_0x0063
            if (r11 != 0) goto L_0x0063
            java.util.Locale r11 = java.util.Locale.US
            java.lang.String r11 = r5.toLowerCase(r11)
            java.lang.String r9 = "zip"
            boolean r9 = r11.endsWith(r9)
            if (r9 == 0) goto L_0x0062
            r11 = 0
            goto L_0x0063
        L_0x0062:
            r11 = 4
        L_0x0063:
            java.lang.String r9 = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE"
            java.lang.String r9 = r8.getStringExtra(r9)
            r16 = r2
            java.lang.String r2 = "application/zip"
            r17 = r15
            java.lang.String r15 = "application/octet-stream"
            if (r9 == 0) goto L_0x0074
            goto L_0x0079
        L_0x0074:
            if (r11 != 0) goto L_0x0078
            r9 = r2
            goto L_0x0079
        L_0x0078:
            r9 = r15
        L_0x0079:
            if (r3 == 0) goto L_0x05f7
            if (r5 != 0) goto L_0x0083
            if (r6 != 0) goto L_0x0083
            if (r7 != 0) goto L_0x0083
            goto L_0x05f7
        L_0x0083:
            r18 = r11 & -8
            r19 = r13
            if (r18 > 0) goto L_0x05e7
            boolean r18 = r2.equals(r9)
            if (r18 != 0) goto L_0x0097
            boolean r18 = r15.equals(r9)
            if (r18 != 0) goto L_0x0097
            goto L_0x05e7
        L_0x0097:
            boolean r15 = r15.equals(r9)
            r13 = 2
            if (r15 == 0) goto L_0x00b6
            r15 = 1
            if (r11 == r15) goto L_0x00b6
            if (r11 == r13) goto L_0x00b6
            r15 = 4
            if (r11 == r15) goto L_0x00b6
            java.lang.String r2 = "Unable to determine file type"
            r1.logw(r2)
            r3 = 15
            r1.sendLogBroadcast(r3, r2)
            r2 = 4105(0x1009, float:5.752E-42)
            r1.report(r2)
            return
        L_0x00b6:
            if (r10 != 0) goto L_0x00c1
            java.lang.Class r15 = r24.getNotificationTarget()
            java.lang.String r13 = "getNotificationTarget() must not return null if notifications are enabled"
            java.util.Objects.requireNonNull(r15, r13)
        L_0x00c1:
            if (r12 != 0) goto L_0x00ce
            int r13 = android.os.Build.VERSION.SDK_INT
            r15 = 26
            if (r13 < r15) goto L_0x00ce
            java.lang.String r13 = "Foreground service disabled. Android Oreo or newer may kill a background service few moments after user closes the application.\nConsider enabling foreground service using DfuServiceInitiator#setForeground(boolean)"
            r1.logw(r13)
        L_0x00ce:
            p019no.nordicsemi.android.dfu.UuidHelper.assignCustomUuids(r25)
            if (r12 == 0) goto L_0x00db
            java.lang.String r13 = "Starting DFU service in foreground"
            r1.logi(r13)
            r24.startForeground()
        L_0x00db:
            r1.mDeviceAddress = r3
            r1.mDeviceName = r4
            r1.mDisableNotification = r10
            r4 = 0
            r1.mConnectionState = r4
            r1.mError = r4
            android.content.SharedPreferences r4 = android.preference.PreferenceManager.getDefaultSharedPreferences(r24)
            java.lang.String r13 = "settings_mbr_size"
            boolean r15 = r4.contains(r13)
            r20 = r3
            r3 = 4096(0x1000, float:5.74E-42)
            if (r15 == 0) goto L_0x0108
            java.lang.String r15 = java.lang.String.valueOf(r3)
            java.lang.String r4 = r4.getString(r13, r15)
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x0105 }
            if (r4 >= 0) goto L_0x0111
            goto L_0x0110
        L_0x0105:
            r4 = 4096(0x1000, float:5.74E-42)
            goto L_0x0111
        L_0x0108:
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_MBR_SIZE"
            int r4 = r8.getIntExtra(r4, r3)
            if (r4 >= 0) goto L_0x0111
        L_0x0110:
            r4 = 0
        L_0x0111:
            java.lang.String r13 = "DFU service started"
            r15 = 1
            r1.sendLogBroadcast(r15, r13)
            java.io.InputStream r13 = r1.mFirmwareInputStream
            java.io.InputStream r15 = r1.mInitFileInputStream
            if (r13 != 0) goto L_0x0120
            r21 = 1
            goto L_0x0122
        L_0x0120:
            r21 = 0
        L_0x0122:
            java.lang.String r3 = "Opening file failed: "
            r22 = r13
            if (r21 == 0) goto L_0x0195
            java.lang.String r13 = "Opening file..."
            r23 = r15
            r15 = 1
            r1.sendLogBroadcast(r15, r13)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r6 == 0) goto L_0x0137
            java.io.InputStream r13 = r1.openInputStream((android.net.Uri) r6, (java.lang.String) r9, (int) r4, (int) r11)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x0147
        L_0x0137:
            if (r5 == 0) goto L_0x013e
            java.io.InputStream r13 = r1.openInputStream((java.lang.String) r5, (java.lang.String) r9, (int) r4, (int) r11)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x0147
        L_0x013e:
            if (r7 <= 0) goto L_0x0145
            java.io.InputStream r13 = r1.openInputStream((int) r7, (java.lang.String) r9, (int) r4, (int) r11)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x0147
        L_0x0145:
            r13 = r22
        L_0x0147:
            if (r14 == 0) goto L_0x0152
            android.content.ContentResolver r4 = r24.getContentResolver()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            java.io.InputStream r15 = r4.openInputStream(r14)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x016b
        L_0x0152:
            if (r19 == 0) goto L_0x015c
            java.io.FileInputStream r15 = new java.io.FileInputStream     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r4 = r19
            r15.<init>(r4)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x016b
        L_0x015c:
            if (r17 <= 0) goto L_0x0169
            android.content.res.Resources r4 = r24.getResources()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r5 = r17
            java.io.InputStream r15 = r4.openRawResource(r5)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x016b
        L_0x0169:
            r15 = r23
        L_0x016b:
            int r4 = r13.available()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r5 = 4
            int r4 = r4 % r5
            if (r4 != 0) goto L_0x0175
            r6 = r13
            goto L_0x0199
        L_0x0175:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            java.lang.String r4 = "The new firmware is not word-aligned."
            r2.<init>(r4)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            throw r2     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x017d:
            r0 = move-exception
        L_0x017e:
            r2 = r0
            goto L_0x05e1
        L_0x0181:
            r0 = move-exception
            r2 = r0
            goto L_0x054c
        L_0x0185:
            r0 = move-exception
            r2 = r0
            goto L_0x0574
        L_0x0189:
            r0 = move-exception
            r2 = r0
            goto L_0x059c
        L_0x018d:
            r0 = move-exception
            r2 = r0
            goto L_0x05b3
        L_0x0191:
            r0 = move-exception
            r2 = r0
            goto L_0x05ca
        L_0x0195:
            r23 = r15
            r6 = r22
        L_0x0199:
            boolean r2 = r2.equals(r9)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r2 == 0) goto L_0x0213
            r2 = r6
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r2 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r2     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r11 != 0) goto L_0x01a9
            int r4 = r2.getContentType()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x01ad
        L_0x01a9:
            int r4 = r2.setContentType(r11)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x01ad:
            r5 = r4 & 4
            if (r5 <= 0) goto L_0x01c2
            int r5 = r2.applicationImageSize()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r7 = 4
            int r5 = r5 % r7
            if (r5 != 0) goto L_0x01ba
            goto L_0x01c2
        L_0x01ba:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            java.lang.String r4 = "Application firmware is not word-aligned."
            r2.<init>(r4)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            throw r2     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x01c2:
            r5 = r4 & 2
            if (r5 <= 0) goto L_0x01d7
            int r5 = r2.bootloaderImageSize()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r7 = 4
            int r5 = r5 % r7
            if (r5 != 0) goto L_0x01cf
            goto L_0x01d7
        L_0x01cf:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            java.lang.String r4 = "Bootloader firmware is not word-aligned."
            r2.<init>(r4)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            throw r2     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x01d7:
            r5 = r4 & 1
            if (r5 <= 0) goto L_0x01ec
            int r5 = r2.softDeviceImageSize()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r7 = 4
            int r5 = r5 % r7
            if (r5 != 0) goto L_0x01e4
            goto L_0x01ec
        L_0x01e4:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            java.lang.String r4 = "Soft Device firmware is not word-aligned."
            r2.<init>(r4)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            throw r2     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x01ec:
            r5 = 4
            if (r4 != r5) goto L_0x01ff
            byte[] r5 = r2.getApplicationInit()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r5 == 0) goto L_0x0211
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            byte[] r2 = r2.getApplicationInit()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r5.<init>(r2)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            goto L_0x020e
        L_0x01ff:
            byte[] r5 = r2.getSystemInit()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r5 == 0) goto L_0x0211
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            byte[] r2 = r2.getSystemInit()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r5.<init>(r2)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x020e:
            r7 = r5
            r5 = r4
            goto L_0x0215
        L_0x0211:
            r5 = r4
            goto L_0x0214
        L_0x0213:
            r5 = r11
        L_0x0214:
            r7 = r15
        L_0x0215:
            if (r21 == 0) goto L_0x0227
            int r2 = r6.available()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r6.mark(r2)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r7 == 0) goto L_0x0227
            int r2 = r7.available()     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r7.mark(r2)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
        L_0x0227:
            r1.mFirmwareInputStream = r6     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            r1.mInitFileInputStream = r7     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            java.lang.String r2 = "Firmware file opened successfully"
            r4 = 5
            r1.sendLogBroadcast(r4, r2)     // Catch:{ SecurityException -> 0x0191, FileNotFoundException -> 0x018d, SizeValidationException -> 0x0189, IOException -> 0x0185, Exception -> 0x0181 }
            if (r21 != 0) goto L_0x023b
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.waitFor(r2)     // Catch:{ all -> 0x017d }
            r1.waitFor(r2)     // Catch:{ all -> 0x017d }
        L_0x023b:
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = new no.nordicsemi.android.dfu.DfuProgressInfo     // Catch:{ all -> 0x017d }
            r2.<init>(r1)     // Catch:{ all -> 0x017d }
            r1.mProgressInfo = r2     // Catch:{ all -> 0x017d }
            boolean r2 = r1.mAborted     // Catch:{ all -> 0x017d }
            r9 = -7
            java.lang.String r11 = "Upload aborted"
            if (r2 == 0) goto L_0x025c
            r1.logw(r11)     // Catch:{ all -> 0x017d }
            r2 = 15
            r1.sendLogBroadcast(r2, r11)     // Catch:{ all -> 0x017d }
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo     // Catch:{ all -> 0x017d }
            r2.setProgress(r9)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x025b
            r1.stopForeground(r10)
        L_0x025b:
            return
        L_0x025c:
            java.lang.String r2 = "Connecting to DFU target..."
            r3 = 1
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo     // Catch:{ all -> 0x017d }
            r3 = -1
            r2.setProgress(r3)     // Catch:{ all -> 0x017d }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x017d }
            r13 = r20
            android.bluetooth.BluetoothGatt r14 = r1.connect(r13)     // Catch:{ all -> 0x017d }
            long r20 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x017d }
            if (r14 != 0) goto L_0x028d
            r2 = r16
            r1.loge(r2)     // Catch:{ all -> 0x017d }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r2 = 4106(0x100a, float:5.754E-42)
            r1.report(r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x028c
            r1.stopForeground(r10)
        L_0x028c:
            return
        L_0x028d:
            int r15 = r1.mError     // Catch:{ all -> 0x017d }
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_RECONNECTION_ATTEMPT"
            if (r15 <= 0) goto L_0x03bc
            r5 = 32768(0x8000, float:4.5918E-41)
            r5 = r5 & r15
            if (r5 <= 0) goto L_0x0324
            r5 = -32769(0xffffffffffff7fff, float:NaN)
            r5 = r5 & r15
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x017d }
            r6.<init>()     // Catch:{ all -> 0x017d }
            java.lang.String r7 = "Connection error after: "
            r6.append(r7)     // Catch:{ all -> 0x017d }
            r16 = r10
            long r9 = r20 - r2
            r6.append(r9)     // Catch:{ all -> 0x031e }
            java.lang.String r7 = " ms"
            r6.append(r7)     // Catch:{ all -> 0x031e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x031e }
            r1.logi(r6)     // Catch:{ all -> 0x031e }
            r6 = 133(0x85, float:1.86E-43)
            if (r5 != r6) goto L_0x02c7
            r6 = 25000(0x61a8, double:1.23516E-319)
            long r2 = r2 + r6
            int r6 = (r20 > r2 ? 1 : (r20 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x02c7
            r2 = 1
            goto L_0x02c8
        L_0x02c7:
            r2 = 0
        L_0x02c8:
            if (r2 == 0) goto L_0x02eb
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x031e }
            r2.<init>()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "Device not reachable. Check if the device with address "
            r2.append(r3)     // Catch:{ all -> 0x031e }
            r2.append(r13)     // Catch:{ all -> 0x031e }
            java.lang.String r3 = " is in range, is advertising and is connectable"
            r2.append(r3)     // Catch:{ all -> 0x031e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x031e }
            r1.loge(r2)     // Catch:{ all -> 0x031e }
            java.lang.String r2 = "Error 133: Connection timeout"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x031e }
            goto L_0x035a
        L_0x02eb:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x031e }
            r2.<init>()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "An error occurred while connecting to the device:"
            r2.append(r3)     // Catch:{ all -> 0x031e }
            r2.append(r5)     // Catch:{ all -> 0x031e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x031e }
            r1.loge(r2)     // Catch:{ all -> 0x031e }
            java.util.Locale r2 = java.util.Locale.US     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "Connection failed (0x%02X): %s"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x031e }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x031e }
            r9 = 0
            r7[r9] = r6     // Catch:{ all -> 0x031e }
            java.lang.String r5 = p019no.nordicsemi.android.error.GattError.parseConnectionError(r5)     // Catch:{ all -> 0x031e }
            r6 = 1
            r7[r6] = r5     // Catch:{ all -> 0x031e }
            java.lang.String r2 = java.lang.String.format(r2, r3, r7)     // Catch:{ all -> 0x031e }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x031e }
            goto L_0x035a
        L_0x031e:
            r0 = move-exception
            r2 = r0
            r10 = r16
            goto L_0x05e1
        L_0x0324:
            r16 = r10
            r2 = r15 & -16385(0xffffffffffffbfff, float:NaN)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b7 }
            r3.<init>()     // Catch:{ all -> 0x03b7 }
            java.lang.String r5 = "An error occurred during discovering services:"
            r3.append(r5)     // Catch:{ all -> 0x03b7 }
            r3.append(r2)     // Catch:{ all -> 0x03b7 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x03b7 }
            r1.loge(r3)     // Catch:{ all -> 0x03b7 }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ all -> 0x03b7 }
            java.lang.String r5 = "Connection failed (0x%02X): %s"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x03b7 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x03b7 }
            r9 = 0
            r7[r9] = r6     // Catch:{ all -> 0x03b7 }
            java.lang.String r2 = p019no.nordicsemi.android.error.GattError.parse(r2)     // Catch:{ all -> 0x03b7 }
            r6 = 1
            r7[r6] = r2     // Catch:{ all -> 0x03b7 }
            java.lang.String r2 = java.lang.String.format(r3, r5, r7)     // Catch:{ all -> 0x03b7 }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x03b7 }
        L_0x035a:
            r2 = 0
            int r2 = r8.getIntExtra(r4, r2)     // Catch:{ all -> 0x03b7 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x03b7 }
            r3.<init>()     // Catch:{ all -> 0x03b7 }
            java.lang.String r5 = "Attempt: "
            r3.append(r5)     // Catch:{ all -> 0x03b7 }
            int r5 = r2 + 1
            r3.append(r5)     // Catch:{ all -> 0x03b7 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x03b7 }
            r1.logi(r3)     // Catch:{ all -> 0x03b7 }
            r3 = 2
            if (r2 >= r3) goto L_0x03aa
            java.lang.String r2 = "Retrying..."
            r3 = 15
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x03b7 }
            int r2 = r1.mConnectionState     // Catch:{ all -> 0x03b7 }
            if (r2 == 0) goto L_0x0386
            r1.disconnect(r14)     // Catch:{ all -> 0x031e }
        L_0x0386:
            r2 = 1
            r1.refreshDeviceCache(r14, r2)     // Catch:{ all -> 0x03b7 }
            r1.close(r14)     // Catch:{ all -> 0x03b7 }
            java.lang.String r2 = "Restarting the service"
            r1.logi(r2)     // Catch:{ all -> 0x03b7 }
            android.content.Intent r2 = new android.content.Intent     // Catch:{ all -> 0x03b7 }
            r2.<init>()     // Catch:{ all -> 0x03b7 }
            r3 = 24
            r2.fillIn(r8, r3)     // Catch:{ all -> 0x03b7 }
            r2.putExtra(r4, r5)     // Catch:{ all -> 0x03b7 }
            r1.startService(r2)     // Catch:{ all -> 0x03b7 }
            if (r12 == 0) goto L_0x03a9
            r10 = r16
            r1.stopForeground(r10)
        L_0x03a9:
            return
        L_0x03aa:
            r10 = r16
            int r2 = r1.mError     // Catch:{ all -> 0x017d }
            r1.terminateConnection(r14, r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x03b6
            r1.stopForeground(r10)
        L_0x03b6:
            return
        L_0x03b7:
            r0 = move-exception
            r10 = r16
            goto L_0x017e
        L_0x03bc:
            int r2 = r1.mConnectionState     // Catch:{ all -> 0x017d }
            if (r2 != 0) goto L_0x03d2
            java.lang.String r2 = "Disconnected"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r3 = 4096(0x1000, float:5.74E-42)
            r1.terminateConnection(r14, r3)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x03d1
            r1.stopForeground(r10)
        L_0x03d1:
            return
        L_0x03d2:
            r3 = 4096(0x1000, float:5.74E-42)
            boolean r2 = r1.mAborted     // Catch:{ all -> 0x017d }
            if (r2 == 0) goto L_0x03ef
            r1.logw(r11)     // Catch:{ all -> 0x017d }
            r2 = 15
            r1.sendLogBroadcast(r2, r11)     // Catch:{ all -> 0x017d }
            r2 = 0
            r1.terminateConnection(r14, r2)     // Catch:{ all -> 0x017d }
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo     // Catch:{ all -> 0x017d }
            r2.setProgress(r9)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x03ee
            r1.stopForeground(r10)
        L_0x03ee:
            return
        L_0x03ef:
            java.lang.String r2 = "Services discovered"
            r13 = 5
            r1.sendLogBroadcast(r13, r2)     // Catch:{ all -> 0x017d }
            r2 = 0
            r8.putExtra(r4, r2)     // Catch:{ all -> 0x017d }
            r2 = 0
            no.nordicsemi.android.dfu.DfuServiceProvider r4 = new no.nordicsemi.android.dfu.DfuServiceProvider     // Catch:{ UploadAbortedException -> 0x052b, DeviceDisconnectedException -> 0x04c1, DfuException -> 0x045d }
            r4.<init>()     // Catch:{ UploadAbortedException -> 0x052b, DeviceDisconnectedException -> 0x04c1, DfuException -> 0x045d }
            r1.mDfuServiceImpl = r4     // Catch:{ UploadAbortedException -> 0x052b, DeviceDisconnectedException -> 0x04c1, DfuException -> 0x045d }
            no.nordicsemi.android.dfu.DfuService r13 = r4.getServiceImpl(r8, r1, r14)     // Catch:{ UploadAbortedException -> 0x052b, DeviceDisconnectedException -> 0x04c1, DfuException -> 0x045d }
            r1.mDfuServiceImpl = r13     // Catch:{ UploadAbortedException -> 0x0456, DeviceDisconnectedException -> 0x044f, DfuException -> 0x044b, all -> 0x0446 }
            if (r13 != 0) goto L_0x042e
            java.lang.String r2 = "DfuBaseService"
            java.lang.String r4 = "DFU Service not found."
            android.util.Log.w(r2, r4)     // Catch:{ UploadAbortedException -> 0x0456, DeviceDisconnectedException -> 0x0427, DfuException -> 0x044b, all -> 0x0446 }
            java.lang.String r2 = "DFU Service not found"
            r4 = 15
            r1.sendLogBroadcast(r4, r2)     // Catch:{ UploadAbortedException -> 0x0456, DeviceDisconnectedException -> 0x0427, DfuException -> 0x044b, all -> 0x0446 }
            r2 = 4102(0x1006, float:5.748E-42)
            r1.terminateConnection(r14, r2)     // Catch:{ UploadAbortedException -> 0x0456, DeviceDisconnectedException -> 0x0427, DfuException -> 0x044b, all -> 0x0446 }
            if (r13 == 0) goto L_0x0421
            r13.release()     // Catch:{ all -> 0x017d }
        L_0x0421:
            if (r12 == 0) goto L_0x0426
            r1.stopForeground(r10)
        L_0x0426:
            return
        L_0x0427:
            r0 = move-exception
            r3 = r0
            r2 = r13
            r15 = 4096(0x1000, float:5.74E-42)
            goto L_0x04c5
        L_0x042e:
            r2 = r13
            r15 = 4096(0x1000, float:5.74E-42)
            r3 = r25
            r4 = r14
            boolean r2 = r2.initialize(r3, r4, r5, r6, r7)     // Catch:{ UploadAbortedException -> 0x0456, DeviceDisconnectedException -> 0x0444, DfuException -> 0x044b, all -> 0x0446 }
            if (r2 == 0) goto L_0x043d
            r13.performDfu(r8)     // Catch:{ UploadAbortedException -> 0x0456, DeviceDisconnectedException -> 0x0444, DfuException -> 0x044b, all -> 0x0446 }
        L_0x043d:
            if (r13 == 0) goto L_0x0540
            r13.release()     // Catch:{ all -> 0x017d }
            goto L_0x0540
        L_0x0444:
            r0 = move-exception
            goto L_0x0452
        L_0x0446:
            r0 = move-exception
            r3 = r0
            r2 = r13
            goto L_0x0546
        L_0x044b:
            r0 = move-exception
            r3 = r0
            r2 = r13
            goto L_0x045f
        L_0x044f:
            r0 = move-exception
            r15 = 4096(0x1000, float:5.74E-42)
        L_0x0452:
            r3 = r0
            r2 = r13
            goto L_0x04c5
        L_0x0456:
            r2 = r13
            goto L_0x052b
        L_0x0459:
            r0 = move-exception
            r3 = r0
            goto L_0x0546
        L_0x045d:
            r0 = move-exception
            r3 = r0
        L_0x045f:
            int r4 = r3.getErrorNumber()     // Catch:{ all -> 0x0459 }
            r5 = 32768(0x8000, float:4.5918E-41)
            r5 = r5 & r4
            if (r5 <= 0) goto L_0x048c
            r5 = -32769(0xffffffffffff7fff, float:NaN)
            r4 = r4 & r5
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ all -> 0x0459 }
            java.lang.String r6 = "Error (0x%02X): %s"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0459 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0459 }
            r9 = 0
            r7[r9] = r8     // Catch:{ all -> 0x0459 }
            java.lang.String r4 = p019no.nordicsemi.android.error.GattError.parseConnectionError(r4)     // Catch:{ all -> 0x0459 }
            r8 = 1
            r7[r8] = r4     // Catch:{ all -> 0x0459 }
            java.lang.String r4 = java.lang.String.format(r5, r6, r7)     // Catch:{ all -> 0x0459 }
            r5 = 20
            r1.sendLogBroadcast(r5, r4)     // Catch:{ all -> 0x0459 }
            goto L_0x04ac
        L_0x048c:
            r4 = r4 & -16385(0xffffffffffffbfff, float:NaN)
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ all -> 0x0459 }
            java.lang.String r6 = "Error (0x%02X): %s"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0459 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0459 }
            r9 = 0
            r7[r9] = r8     // Catch:{ all -> 0x0459 }
            java.lang.String r4 = p019no.nordicsemi.android.error.GattError.parse(r4)     // Catch:{ all -> 0x0459 }
            r8 = 1
            r7[r8] = r4     // Catch:{ all -> 0x0459 }
            java.lang.String r4 = java.lang.String.format(r5, r6, r7)     // Catch:{ all -> 0x0459 }
            r5 = 20
            r1.sendLogBroadcast(r5, r4)     // Catch:{ all -> 0x0459 }
        L_0x04ac:
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x0459 }
            r1.loge(r4)     // Catch:{ all -> 0x0459 }
            int r3 = r3.getErrorNumber()     // Catch:{ all -> 0x0459 }
            r1.terminateConnection(r14, r3)     // Catch:{ all -> 0x0459 }
            if (r2 == 0) goto L_0x0540
        L_0x04bc:
            r2.release()     // Catch:{ all -> 0x017d }
            goto L_0x0540
        L_0x04c1:
            r0 = move-exception
            r15 = 4096(0x1000, float:5.74E-42)
            r3 = r0
        L_0x04c5:
            java.lang.String r4 = "Device has disconnected"
            r5 = 20
            r1.sendLogBroadcast(r5, r4)     // Catch:{ all -> 0x0459 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0459 }
            r1.loge(r3)     // Catch:{ all -> 0x0459 }
            r1.close(r14)     // Catch:{ all -> 0x0459 }
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_DFU_ATTEMPT"
            r4 = 0
            int r3 = r8.getIntExtra(r3, r4)     // Catch:{ all -> 0x0459 }
            java.lang.String r5 = "no.nordicsemi.android.dfu.extra.EXTRA_MAX_DFU_ATTEMPTS"
            int r4 = r8.getIntExtra(r5, r4)     // Catch:{ all -> 0x0459 }
            if (r3 >= r4) goto L_0x0525
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0459 }
            r5.<init>()     // Catch:{ all -> 0x0459 }
            java.lang.String r6 = "Restarting the service ("
            r5.append(r6)     // Catch:{ all -> 0x0459 }
            r6 = 1
            int r3 = r3 + r6
            r5.append(r3)     // Catch:{ all -> 0x0459 }
            java.lang.String r6 = " /"
            r5.append(r6)     // Catch:{ all -> 0x0459 }
            r5.append(r4)     // Catch:{ all -> 0x0459 }
            java.lang.String r4 = ")"
            r5.append(r4)     // Catch:{ all -> 0x0459 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0459 }
            r1.logi(r4)     // Catch:{ all -> 0x0459 }
            android.content.Intent r4 = new android.content.Intent     // Catch:{ all -> 0x0459 }
            r4.<init>()     // Catch:{ all -> 0x0459 }
            r5 = 24
            r4.fillIn(r8, r5)     // Catch:{ all -> 0x0459 }
            java.lang.String r5 = "no.nordicsemi.android.dfu.extra.EXTRA_DFU_ATTEMPT"
            r4.putExtra(r5, r3)     // Catch:{ all -> 0x0459 }
            r1.startService(r4)     // Catch:{ all -> 0x0459 }
            if (r2 == 0) goto L_0x051f
            r2.release()     // Catch:{ all -> 0x017d }
        L_0x051f:
            if (r12 == 0) goto L_0x0524
            r1.stopForeground(r10)
        L_0x0524:
            return
        L_0x0525:
            r1.report(r15)     // Catch:{ all -> 0x0459 }
            if (r2 == 0) goto L_0x0540
            goto L_0x04bc
        L_0x052b:
            r1.logw(r11)     // Catch:{ all -> 0x0459 }
            r3 = 15
            r1.sendLogBroadcast(r3, r11)     // Catch:{ all -> 0x0459 }
            r3 = 0
            r1.terminateConnection(r14, r3)     // Catch:{ all -> 0x0459 }
            no.nordicsemi.android.dfu.DfuProgressInfo r3 = r1.mProgressInfo     // Catch:{ all -> 0x0459 }
            r3.setProgress(r9)     // Catch:{ all -> 0x0459 }
            if (r2 == 0) goto L_0x0540
            goto L_0x04bc
        L_0x0540:
            if (r12 == 0) goto L_0x0545
            r1.stopForeground(r10)
        L_0x0545:
            return
        L_0x0546:
            if (r2 == 0) goto L_0x054b
            r2.release()     // Catch:{ all -> 0x017d }
        L_0x054b:
            throw r3     // Catch:{ all -> 0x017d }
        L_0x054c:
            java.lang.String r4 = "An exception occurred while opening files. Did you set the firmware file?"
            r1.loge(r4, r2)     // Catch:{ all -> 0x017d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x017d }
            r4.<init>()     // Catch:{ all -> 0x017d }
            r4.append(r3)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x017d }
            r4.append(r2)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x017d }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r2 = 4098(0x1002, float:5.743E-42)
            r1.report(r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x0573
            r1.stopForeground(r10)
        L_0x0573:
            return
        L_0x0574:
            java.lang.String r4 = "An exception occurred while calculating file size"
            r1.loge(r4, r2)     // Catch:{ all -> 0x017d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x017d }
            r4.<init>()     // Catch:{ all -> 0x017d }
            r4.append(r3)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x017d }
            r4.append(r2)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x017d }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r2 = 4098(0x1002, float:5.743E-42)
            r1.report(r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x059b
            r1.stopForeground(r10)
        L_0x059b:
            return
        L_0x059c:
            java.lang.String r3 = "Firmware not word-aligned"
            r1.loge(r3, r2)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = "Opening file failed: Firmware size must be word-aligned"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r2 = 4108(0x100c, float:5.757E-42)
            r1.report(r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x05b2
            r1.stopForeground(r10)
        L_0x05b2:
            return
        L_0x05b3:
            java.lang.String r3 = "An exception occurred while opening file"
            r1.loge(r3, r2)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = "Opening file failed: File not found"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r2 = 4097(0x1001, float:5.741E-42)
            r1.report(r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x05c9
            r1.stopForeground(r10)
        L_0x05c9:
            return
        L_0x05ca:
            java.lang.String r3 = "A security exception occurred while opening file"
            r1.loge(r3, r2)     // Catch:{ all -> 0x017d }
            java.lang.String r2 = "Opening file failed: Permission required"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x017d }
            r2 = 4097(0x1001, float:5.741E-42)
            r1.report(r2)     // Catch:{ all -> 0x017d }
            if (r12 == 0) goto L_0x05e0
            r1.stopForeground(r10)
        L_0x05e0:
            return
        L_0x05e1:
            if (r12 == 0) goto L_0x05e6
            r1.stopForeground(r10)
        L_0x05e6:
            throw r2
        L_0x05e7:
            java.lang.String r2 = "File type or file mime-type not supported"
            r1.logw(r2)
            r3 = 15
            r1.sendLogBroadcast(r3, r2)
            r2 = 4105(0x1009, float:5.752E-42)
            r1.report(r2)
            return
        L_0x05f7:
            java.lang.String r2 = "Device Address of firmware location are empty. Hint: use DfuServiceInitiator to start DFU"
            r1.loge(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.DfuBaseService.onHandleIntent(android.content.Intent):void");
    }

    private InputStream openInputStream(String str, String str2, int i, int i2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        if (MIME_TYPE_ZIP.equals(str2)) {
            return new ArchiveInputStream(fileInputStream, i, i2);
        }
        return str.toLowerCase(Locale.US).endsWith("hex") ? new HexInputStream((InputStream) fileInputStream, i) : fileInputStream;
    }

    private InputStream openInputStream(Uri uri, String str, int i, int i2) throws IOException {
        InputStream openInputStream = getContentResolver().openInputStream(uri);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(openInputStream, i, i2);
        }
        Cursor query = getContentResolver().query(uri, new String[]{"_display_name"}, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToNext() && query.getString(0).toLowerCase(Locale.US).endsWith("hex")) {
                    return new HexInputStream(openInputStream, i);
                }
            } finally {
                query.close();
            }
        }
        query.close();
        return openInputStream;
    }

    private InputStream openInputStream(int i, String str, int i2, int i3) throws IOException {
        InputStream openRawResource = getResources().openRawResource(i);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(openRawResource, i2, i3);
        }
        openRawResource.mark(2);
        int read = openRawResource.read();
        openRawResource.reset();
        return read == 58 ? new HexInputStream(openRawResource, i2) : openRawResource;
    }

    /* access modifiers changed from: protected */
    public BluetoothGatt connect(String str) {
        BluetoothGatt bluetoothGatt;
        if (!this.mBluetoothAdapter.isEnabled()) {
            return null;
        }
        this.mConnectionState = -1;
        logi("Connecting to the device...");
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        if (Build.VERSION.SDK_INT >= 26) {
            sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, preferredPhy = LE_1M | LE_2M)");
            bluetoothGatt = remoteDevice.connectGatt(this, false, this.mGattCallback, 2, 3);
        } else if (Build.VERSION.SDK_INT >= 23) {
            sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
            bluetoothGatt = remoteDevice.connectGatt(this, false, this.mGattCallback, 2);
        } else {
            sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false)");
            bluetoothGatt = remoteDevice.connectGatt(this, false, this.mGattCallback);
        }
        try {
            synchronized (this.mLock) {
                while (true) {
                    int i = this.mConnectionState;
                    if ((i == -1 || i == -2) && this.mError == 0 && !this.mAborted) {
                        this.mLock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        return bluetoothGatt;
    }

    /* access modifiers changed from: protected */
    public void terminateConnection(BluetoothGatt bluetoothGatt, int i) {
        if (this.mConnectionState != 0) {
            disconnect(bluetoothGatt);
        }
        refreshDeviceCache(bluetoothGatt, false);
        close(bluetoothGatt);
        waitFor(600);
        if (i != 0) {
            report(i);
        }
    }

    /* access modifiers changed from: protected */
    public void disconnect(BluetoothGatt bluetoothGatt) {
        if (this.mConnectionState != 0) {
            sendLogBroadcast(1, "Disconnecting...");
            this.mProgressInfo.setProgress(-5);
            this.mConnectionState = -4;
            logi("Disconnecting from the device...");
            sendLogBroadcast(0, "gatt.disconnect()");
            bluetoothGatt.disconnect();
            waitUntilDisconnected();
            sendLogBroadcast(5, "Disconnected");
        }
    }

    /* access modifiers changed from: protected */
    public void waitUntilDisconnected() {
        try {
            synchronized (this.mLock) {
                while (this.mConnectionState != 0 && this.mError == 0) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    /* access modifiers changed from: protected */
    public void waitFor(long j) {
        synchronized (this.mLock) {
            try {
                sendLogBroadcast(0, "wait(" + j + ")");
                this.mLock.wait(j);
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void close(BluetoothGatt bluetoothGatt) {
        logi("Cleaning up...");
        sendLogBroadcast(0, "gatt.disconnect()");
        bluetoothGatt.disconnect();
        sendLogBroadcast(0, "gatt.close()");
        bluetoothGatt.close();
        this.mConnectionState = -5;
    }

    /* access modifiers changed from: protected */
    public void refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            sendLogBroadcast(0, "gatt.refresh() (hidden)");
            try {
                boolean booleanValue = ((Boolean) bluetoothGatt.getClass().getMethod(ControlCFragment.REFRESH, new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
                logi("Refreshing result: " + booleanValue);
            } catch (Exception e) {
                loge("An exception occurred while refreshing device", e);
                sendLogBroadcast(15, "Refreshing failed");
            }
        }
    }

    public void updateProgressNotification() {
        String str;
        DfuProgressInfo dfuProgressInfo = this.mProgressInfo;
        int progress = dfuProgressInfo.getProgress();
        if (this.mLastProgress != progress) {
            this.mLastProgress = progress;
            sendProgressBroadcast(dfuProgressInfo);
            if (!this.mDisableNotification) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime - this.mLastNotificationTime >= 250 || -6 == progress || -7 == progress) {
                    this.mLastNotificationTime = elapsedRealtime;
                    String str2 = this.mDeviceAddress;
                    String str3 = this.mDeviceName;
                    if (str3 == null) {
                        str3 = getString(C3953R.string.dfu_unknown_name);
                    }
                    NotificationCompat.Builder onlyAlertOnce = new NotificationCompat.Builder((Context) this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setOnlyAlertOnce(true);
                    onlyAlertOnce.setColor(-7829368);
                    switch (progress) {
                        case -7:
                            onlyAlertOnce.setOngoing(false).setContentTitle(getString(C3953R.string.dfu_status_aborted)).setSmallIcon(17301641).setContentText(getString(C3953R.string.dfu_status_aborted_msg)).setAutoCancel(true);
                            break;
                        case -6:
                            onlyAlertOnce.setOngoing(false).setContentTitle(getString(C3953R.string.dfu_status_completed)).setSmallIcon(17301641).setContentText(getString(C3953R.string.dfu_status_completed_msg)).setAutoCancel(true).setColor(-16730086);
                            break;
                        case -5:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(C3953R.string.dfu_status_disconnecting)).setContentText(getString(C3953R.string.dfu_status_disconnecting_msg, new Object[]{str3})).setProgress(100, 0, true);
                            break;
                        case -4:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(C3953R.string.dfu_status_validating)).setContentText(getString(C3953R.string.dfu_status_validating_msg)).setProgress(100, 0, true);
                            break;
                        case -3:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(C3953R.string.dfu_status_switching_to_dfu)).setContentText(getString(C3953R.string.dfu_status_switching_to_dfu_msg)).setProgress(100, 0, true);
                            break;
                        case -2:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(C3953R.string.dfu_status_starting)).setContentText(getString(C3953R.string.dfu_status_starting_msg)).setProgress(100, 0, true);
                            break;
                        case -1:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(C3953R.string.dfu_status_connecting)).setContentText(getString(C3953R.string.dfu_status_connecting_msg, new Object[]{str3})).setProgress(100, 0, true);
                            break;
                        default:
                            if (dfuProgressInfo.getTotalParts() == 1) {
                                str = getString(C3953R.string.dfu_status_uploading);
                            } else {
                                str = getString(C3953R.string.dfu_status_uploading_part, new Object[]{Integer.valueOf(dfuProgressInfo.getCurrentPart()), Integer.valueOf(dfuProgressInfo.getTotalParts())});
                            }
                            onlyAlertOnce.setOngoing(true).setContentTitle(str).setContentText(getString(C3953R.string.dfu_status_uploading_msg, new Object[]{str3})).setProgress(100, progress, false);
                            break;
                    }
                    Intent intent = new Intent(this, getNotificationTarget());
                    intent.addFlags(268435456);
                    intent.putExtra(EXTRA_DEVICE_ADDRESS, str2);
                    intent.putExtra(EXTRA_DEVICE_NAME, str3);
                    intent.putExtra(EXTRA_PROGRESS, progress);
                    int i = 134217728;
                    if (Build.VERSION.SDK_INT >= 23) {
                        i = 201326592;
                    }
                    onlyAlertOnce.setContentIntent(PendingIntent.getActivity(this, 0, intent, i));
                    updateProgressNotification(onlyAlertOnce, progress);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(ActivityEvent.NOTIFICATION);
                    if (notificationManager != null) {
                        notificationManager.notify(NOTIFICATION_ID, onlyAlertOnce.build());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateProgressNotification(NotificationCompat.Builder builder, int i) {
        if (i != -7 && i != -6) {
            Intent intent = new Intent(BROADCAST_ACTION);
            intent.putExtra(EXTRA_ACTION, 2);
            int i2 = 134217728;
            if (Build.VERSION.SDK_INT >= 23) {
                i2 = 201326592;
            }
            builder.addAction(C3953R.C3955drawable.ic_action_notify_cancel, getString(C3953R.string.dfu_action_abort), PendingIntent.getBroadcast(this, 1, intent, i2));
        }
    }

    private void report(int i) {
        sendErrorBroadcast(i);
        if (!this.mDisableNotification) {
            String str = this.mDeviceAddress;
            String str2 = this.mDeviceName;
            if (str2 == null) {
                str2 = getString(C3953R.string.dfu_unknown_name);
            }
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder((Context) this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setOnlyAlertOnce(true).setColor(SupportMenu.CATEGORY_MASK).setOngoing(false).setContentTitle(getString(C3953R.string.dfu_status_error)).setSmallIcon(17301641).setContentText(getString(C3953R.string.dfu_status_error_msg)).setAutoCancel(true);
            Intent intent = new Intent(this, getNotificationTarget());
            intent.addFlags(268435456);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, str);
            intent.putExtra(EXTRA_DEVICE_NAME, str2);
            intent.putExtra(EXTRA_PROGRESS, i);
            int i2 = 134217728;
            if (Build.VERSION.SDK_INT >= 23) {
                i2 = 201326592;
            }
            autoCancel.setContentIntent(PendingIntent.getActivity(this, 0, intent, i2));
            updateErrorNotification(autoCancel);
            NotificationManager notificationManager = (NotificationManager) getSystemService(ActivityEvent.NOTIFICATION);
            if (notificationManager != null) {
                notificationManager.notify(NOTIFICATION_ID, autoCancel.build());
            }
        }
    }

    private void startForeground() {
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder((Context) this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setContentTitle(getString(C3953R.string.dfu_status_foreground_title)).setContentText(getString(C3953R.string.dfu_status_foreground_content)).setColor(-7829368).setPriority(-1).setOngoing(true);
        Class<? extends Activity> notificationTarget = getNotificationTarget();
        if (notificationTarget != null) {
            Intent intent = new Intent(this, notificationTarget);
            intent.addFlags(268435456);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
            intent.putExtra(EXTRA_DEVICE_NAME, this.mDeviceName);
            int i = 134217728;
            if (Build.VERSION.SDK_INT >= 23) {
                i = 201326592;
            }
            ongoing.setContentIntent(PendingIntent.getActivity(this, 0, intent, i));
        } else {
            logw("getNotificationTarget() should not return null if the service is to be started as a foreground service");
        }
        updateForegroundNotification(ongoing);
        startForeground(NOTIFICATION_ID, ongoing.build());
    }

    private void sendProgressBroadcast(DfuProgressInfo dfuProgressInfo) {
        Intent intent = new Intent(BROADCAST_PROGRESS);
        intent.putExtra(EXTRA_DATA, dfuProgressInfo.getProgress());
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        intent.putExtra(EXTRA_PART_CURRENT, dfuProgressInfo.getCurrentPart());
        intent.putExtra(EXTRA_PARTS_TOTAL, dfuProgressInfo.getTotalParts());
        intent.putExtra(EXTRA_SPEED_B_PER_MS, dfuProgressInfo.getSpeed());
        intent.putExtra(EXTRA_AVG_SPEED_B_PER_MS, dfuProgressInfo.getAverageSpeed());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sendErrorBroadcast(int i) {
        Intent intent = new Intent(BROADCAST_ERROR);
        if ((i & 16384) > 0) {
            intent.putExtra(EXTRA_DATA, i & -16385);
            intent.putExtra(EXTRA_ERROR_TYPE, 2);
        } else if ((32768 & i) > 0) {
            intent.putExtra(EXTRA_DATA, i & -32769);
            intent.putExtra(EXTRA_ERROR_TYPE, 1);
        } else if ((i & 8192) > 0) {
            intent.putExtra(EXTRA_DATA, i & -8193);
            intent.putExtra(EXTRA_ERROR_TYPE, 3);
        } else {
            intent.putExtra(EXTRA_DATA, i);
            intent.putExtra(EXTRA_ERROR_TYPE, 0);
        }
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void sendLogBroadcast(int i, String str) {
        Intent intent = new Intent(BROADCAST_LOG);
        intent.putExtra(EXTRA_LOG_MESSAGE, "[DFU] " + str);
        intent.putExtra(EXTRA_LOG_LEVEL, i);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private boolean initialize() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            loge("Unable to initialize BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.mBluetoothAdapter = adapter;
        if (adapter != null) {
            return true;
        }
        loge("Unable to obtain a BluetoothAdapter.");
        return false;
    }

    /* access modifiers changed from: private */
    public void loge(String str) {
        Log.e(TAG, str);
    }

    private void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    /* access modifiers changed from: private */
    public void logw(String str) {
        if (DEBUG) {
            Log.w(TAG, str);
        }
    }

    /* access modifiers changed from: private */
    public void logi(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }
}
