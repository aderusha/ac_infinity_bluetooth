package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.core.util.Preconditions;

class GnssStatusWrapper extends GnssStatusCompat {
    private final GnssStatus mWrapped;

    GnssStatusWrapper(GnssStatus gnssStatus) {
        this.mWrapped = (GnssStatus) Preconditions.checkNotNull(gnssStatus);
    }

    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    public int getConstellationType(int i) {
        return this.mWrapped.getConstellationType(i);
    }

    public int getSvid(int i) {
        return this.mWrapped.getSvid(i);
    }

    public float getCn0DbHz(int i) {
        return this.mWrapped.getCn0DbHz(i);
    }

    public float getElevationDegrees(int i) {
        return this.mWrapped.getElevationDegrees(i);
    }

    public float getAzimuthDegrees(int i) {
        return this.mWrapped.getAzimuthDegrees(i);
    }

    public boolean hasEphemerisData(int i) {
        return this.mWrapped.hasEphemerisData(i);
    }

    public boolean hasAlmanacData(int i) {
        return this.mWrapped.hasAlmanacData(i);
    }

    public boolean usedInFix(int i) {
        return this.mWrapped.usedInFix(i);
    }

    public boolean hasCarrierFrequencyHz(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mWrapped.hasCarrierFrequencyHz(i);
        }
        return false;
    }

    public float getCarrierFrequencyHz(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mWrapped.getCarrierFrequencyHz(i);
        }
        throw new UnsupportedOperationException();
    }

    public boolean hasBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.mWrapped.hasBasebandCn0DbHz(i);
        }
        return false;
    }

    public float getBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.mWrapped.getBasebandCn0DbHz(i);
        }
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GnssStatusWrapper)) {
            return false;
        }
        return this.mWrapped.equals(((GnssStatusWrapper) obj).mWrapped);
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }
}
