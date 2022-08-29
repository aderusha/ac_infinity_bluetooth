package androidx.core.location;

import android.location.Location;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\n¨\u0006\u0004"}, mo27512d2 = {"component1", "", "Landroid/location/Location;", "component2", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Location.kt */
public final class LocationKt {
    public static final double component1(Location location) {
        Intrinsics.checkNotNullParameter(location, "<this>");
        return location.getLatitude();
    }

    public static final double component2(Location location) {
        Intrinsics.checkNotNullParameter(location, "<this>");
        return location.getLongitude();
    }
}
