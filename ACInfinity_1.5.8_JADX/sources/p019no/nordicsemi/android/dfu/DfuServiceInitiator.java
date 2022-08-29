package p019no.nordicsemi.android.dfu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelUuid;
import android.os.Parcelable;
import com.eternal.base.global.ActivityEvent;
import java.security.InvalidParameterException;
import java.util.UUID;

/* renamed from: no.nordicsemi.android.dfu.DfuServiceInitiator */
public final class DfuServiceInitiator {
    public static final int DEFAULT_MBR_SIZE = 4096;
    public static final int DEFAULT_PRN_VALUE = 12;
    public static final int SCOPE_APPLICATION = 2;
    public static final int SCOPE_SYSTEM_COMPONENTS = 1;
    private Parcelable[] buttonlessDfuWithBondSharingUuids;
    private Parcelable[] buttonlessDfuWithoutBondSharingUuids;
    private int currentMtu = 23;
    private long dataObjectDelay = 0;
    private final String deviceAddress;
    private String deviceName;
    private boolean disableNotification = false;
    private boolean disableResume = false;
    private boolean enableUnsafeExperimentalButtonlessDfu = false;
    private Parcelable[] experimentalButtonlessDfuUuids;
    private String filePath;
    private int fileResId;
    private int fileType = -1;
    private Uri fileUri;
    private boolean forceDfu = false;
    private boolean forceScanningForNewAddressInLegacyDfu = false;
    private String initFilePath;
    private int initFileResId;
    private Uri initFileUri;
    private boolean keepBond;
    private Parcelable[] legacyDfuUuids;
    private int mbrSize = 4096;
    private String mimeType;
    private int mtu = 517;
    private int numberOfPackets = 12;
    private int numberOfRetries = 0;
    private Boolean packetReceiptNotificationsEnabled;
    private boolean restoreBond;
    private Parcelable[] secureDfuUuids;
    private boolean startAsForegroundService = true;

    public DfuServiceInitiator(String str) {
        this.deviceAddress = str;
    }

    public DfuServiceInitiator setDeviceName(String str) {
        this.deviceName = str;
        return this;
    }

    public DfuServiceInitiator setDisableNotification(boolean z) {
        this.disableNotification = z;
        return this;
    }

    public DfuServiceInitiator setForeground(boolean z) {
        this.startAsForegroundService = z;
        return this;
    }

    public DfuServiceInitiator setKeepBond(boolean z) {
        this.keepBond = z;
        return this;
    }

    public DfuServiceInitiator setRestoreBond(boolean z) {
        this.restoreBond = z;
        return this;
    }

    public DfuServiceInitiator setPrepareDataObjectDelay(long j) {
        this.dataObjectDelay = j;
        return this;
    }

    public DfuServiceInitiator setPacketsReceiptNotificationsEnabled(boolean z) {
        this.packetReceiptNotificationsEnabled = Boolean.valueOf(z);
        return this;
    }

    public DfuServiceInitiator setPacketsReceiptNotificationsValue(int i) {
        if (i <= 0) {
            i = 12;
        }
        this.numberOfPackets = i;
        return this;
    }

    public DfuServiceInitiator setForceDfu(boolean z) {
        this.forceDfu = z;
        return this;
    }

    public DfuServiceInitiator setForceScanningForNewAddressInLegacyDfu(boolean z) {
        this.forceScanningForNewAddressInLegacyDfu = z;
        return this;
    }

    public DfuServiceInitiator disableResume() {
        this.disableResume = true;
        return this;
    }

    public DfuServiceInitiator setNumberOfRetries(int i) {
        this.numberOfRetries = i;
        return this;
    }

    public DfuServiceInitiator setMtu(int i) {
        this.mtu = i;
        return this;
    }

    public DfuServiceInitiator setCurrentMtu(int i) {
        this.currentMtu = i;
        return this;
    }

    public DfuServiceInitiator disableMtuRequest() {
        this.mtu = 0;
        return this;
    }

    public DfuServiceInitiator setScope(int i) {
        if (DfuBaseService.MIME_TYPE_ZIP.equals(this.mimeType)) {
            if (i == 2) {
                this.fileType = 4;
            } else if (i == 1) {
                this.fileType = 3;
            } else if (i == 3) {
                this.fileType = 0;
            } else {
                throw new UnsupportedOperationException("Unknown scope");
            }
            return this;
        }
        throw new UnsupportedOperationException("Scope can be set only for a ZIP file");
    }

    public DfuServiceInitiator setMbrSize(int i) {
        this.mbrSize = i;
        return this;
    }

    public DfuServiceInitiator setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(boolean z) {
        this.enableUnsafeExperimentalButtonlessDfu = z;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForLegacyDfu(UUID uuid, UUID uuid2, UUID uuid3, UUID uuid4) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[4];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        parcelUuidArr[1] = uuid2 != null ? new ParcelUuid(uuid2) : null;
        parcelUuidArr[2] = uuid3 != null ? new ParcelUuid(uuid3) : null;
        if (uuid4 != null) {
            parcelUuid = new ParcelUuid(uuid4);
        }
        parcelUuidArr[3] = parcelUuid;
        this.legacyDfuUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForSecureDfu(UUID uuid, UUID uuid2, UUID uuid3) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[3];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        parcelUuidArr[1] = uuid2 != null ? new ParcelUuid(uuid2) : null;
        if (uuid3 != null) {
            parcelUuid = new ParcelUuid(uuid3);
        }
        parcelUuidArr[2] = parcelUuid;
        this.secureDfuUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForExperimentalButtonlessDfu(UUID uuid, UUID uuid2) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[2];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        if (uuid2 != null) {
            parcelUuid = new ParcelUuid(uuid2);
        }
        parcelUuidArr[1] = parcelUuid;
        this.experimentalButtonlessDfuUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForButtonlessDfuWithBondSharing(UUID uuid, UUID uuid2) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[2];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        if (uuid2 != null) {
            parcelUuid = new ParcelUuid(uuid2);
        }
        parcelUuidArr[1] = parcelUuid;
        this.buttonlessDfuWithBondSharingUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForButtonlessDfuWithoutBondSharing(UUID uuid, UUID uuid2) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[2];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        if (uuid2 != null) {
            parcelUuid = new ParcelUuid(uuid2);
        }
        parcelUuidArr[1] = parcelUuid;
        this.buttonlessDfuWithoutBondSharingUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setZip(Uri uri) {
        return init(uri, (String) null, 0, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    public DfuServiceInitiator setZip(String str) {
        return init((Uri) null, str, 0, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    public DfuServiceInitiator setZip(int i) {
        return init((Uri) null, (String) null, i, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    public DfuServiceInitiator setZip(Uri uri, String str) {
        return init(uri, str, 0, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, Uri uri) {
        if (i != 0) {
            return init(uri, (String) null, 0, i, DfuBaseService.MIME_TYPE_OCTET_STREAM);
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, String str) {
        if (i != 0) {
            return init((Uri) null, str, 0, i, DfuBaseService.MIME_TYPE_OCTET_STREAM);
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, Uri uri, String str) {
        if (i != 0) {
            return init(uri, str, 0, i, DfuBaseService.MIME_TYPE_OCTET_STREAM);
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, int i2) {
        if (i != 0) {
            return init((Uri) null, (String) null, i2, i, DfuBaseService.MIME_TYPE_OCTET_STREAM);
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(Uri uri) {
        return init(uri, (String) null, 0);
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(String str) {
        return init((Uri) null, str, 0);
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(int i) {
        return init((Uri) null, (String) null, i);
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(Uri uri, String str) {
        return init(uri, str, 0);
    }

    public DfuServiceController start(Context context, Class<? extends DfuBaseService> cls) {
        if (this.fileType != -1) {
            Intent intent = new Intent(context, cls);
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, this.deviceAddress);
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_NAME, this.deviceName);
            intent.putExtra(DfuBaseService.EXTRA_DISABLE_NOTIFICATION, this.disableNotification);
            intent.putExtra(DfuBaseService.EXTRA_FOREGROUND_SERVICE, this.startAsForegroundService);
            intent.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, this.mimeType);
            intent.putExtra(DfuBaseService.EXTRA_FILE_TYPE, this.fileType);
            intent.putExtra(DfuBaseService.EXTRA_FILE_URI, this.fileUri);
            intent.putExtra(DfuBaseService.EXTRA_FILE_PATH, this.filePath);
            intent.putExtra(DfuBaseService.EXTRA_FILE_RES_ID, this.fileResId);
            intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_URI, this.initFileUri);
            intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_PATH, this.initFilePath);
            intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_RES_ID, this.initFileResId);
            intent.putExtra(DfuBaseService.EXTRA_KEEP_BOND, this.keepBond);
            intent.putExtra(DfuBaseService.EXTRA_RESTORE_BOND, this.restoreBond);
            intent.putExtra(DfuBaseService.EXTRA_FORCE_DFU, this.forceDfu);
            intent.putExtra(DfuBaseService.EXTRA_FORCE_SCANNING_FOR_BOOTLOADER_IN_LEGACY_DFU, this.forceScanningForNewAddressInLegacyDfu);
            intent.putExtra(DfuBaseService.EXTRA_DISABLE_RESUME, this.disableResume);
            intent.putExtra(DfuBaseService.EXTRA_MAX_DFU_ATTEMPTS, this.numberOfRetries);
            intent.putExtra(DfuBaseService.EXTRA_MBR_SIZE, this.mbrSize);
            intent.putExtra(DfuBaseService.EXTRA_DATA_OBJECT_DELAY, this.dataObjectDelay);
            int i = this.mtu;
            if (i > 0) {
                intent.putExtra(DfuBaseService.EXTRA_MTU, i);
            }
            intent.putExtra(DfuBaseService.EXTRA_CURRENT_MTU, this.currentMtu);
            intent.putExtra(DfuBaseService.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU, this.enableUnsafeExperimentalButtonlessDfu);
            Boolean bool = this.packetReceiptNotificationsEnabled;
            if (bool != null) {
                intent.putExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED, bool);
                intent.putExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE, this.numberOfPackets);
            }
            Parcelable[] parcelableArr = this.legacyDfuUuids;
            if (parcelableArr != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU, parcelableArr);
            }
            Parcelable[] parcelableArr2 = this.secureDfuUuids;
            if (parcelableArr2 != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU, parcelableArr2);
            }
            Parcelable[] parcelableArr3 = this.experimentalButtonlessDfuUuids;
            if (parcelableArr3 != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU, parcelableArr3);
            }
            Parcelable[] parcelableArr4 = this.buttonlessDfuWithoutBondSharingUuids;
            if (parcelableArr4 != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING, parcelableArr4);
            }
            Parcelable[] parcelableArr5 = this.buttonlessDfuWithBondSharingUuids;
            if (parcelableArr5 != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING, parcelableArr5);
            }
            if (Build.VERSION.SDK_INT < 26 || !this.startAsForegroundService) {
                context.startService(intent);
            } else {
                context.startForegroundService(intent);
            }
            return new DfuServiceController(context);
        }
        throw new UnsupportedOperationException("You must specify the firmware file before starting the service");
    }

    private DfuServiceInitiator init(Uri uri, String str, int i) {
        if (!DfuBaseService.MIME_TYPE_ZIP.equals(this.mimeType)) {
            this.initFileUri = uri;
            this.initFilePath = str;
            this.initFileResId = i;
            return this;
        }
        throw new InvalidParameterException("Init file must be located inside the ZIP");
    }

    private DfuServiceInitiator init(Uri uri, String str, int i, int i2, String str2) {
        this.fileUri = uri;
        this.filePath = str;
        this.fileResId = i;
        this.fileType = i2;
        this.mimeType = str2;
        if (DfuBaseService.MIME_TYPE_ZIP.equals(str2)) {
            this.initFileUri = null;
            this.initFilePath = null;
            this.initFileResId = 0;
        }
        return this;
    }

    public static void createDfuNotificationChannel(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(DfuBaseService.NOTIFICATION_CHANNEL_DFU, context.getString(C3953R.string.dfu_channel_name), 2);
        notificationChannel.setDescription(context.getString(C3953R.string.dfu_channel_description));
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(ActivityEvent.NOTIFICATION);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
