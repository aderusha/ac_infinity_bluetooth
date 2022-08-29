package p019no.nordicsemi.android.dfu.internal.manifest;

import com.google.gson.annotations.SerializedName;

/* renamed from: no.nordicsemi.android.dfu.internal.manifest.FileInfo */
public class FileInfo {
    @SerializedName("bin_file")
    private String binFile;
    @SerializedName("dat_file")
    private String datFile;

    public String getBinFileName() {
        return this.binFile;
    }

    public String getDatFileName() {
        return this.datFile;
    }
}
