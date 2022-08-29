package p019no.nordicsemi.android.dfu.internal.manifest;

import com.google.gson.annotations.SerializedName;

/* renamed from: no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo */
public class SoftDeviceBootloaderFileInfo extends FileInfo {
    @SerializedName("bl_size")
    private int bootloaderSize;
    @SerializedName("sd_size")
    private int softdeviceSize;

    public int getSoftdeviceSize() {
        return this.softdeviceSize;
    }

    public int getBootloaderSize() {
        return this.bootloaderSize;
    }
}
