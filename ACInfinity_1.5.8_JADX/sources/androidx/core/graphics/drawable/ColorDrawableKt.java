package androidx.core.graphics.drawable;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\b¨\u0006\u0004"}, mo27512d2 = {"toDrawable", "Landroid/graphics/drawable/ColorDrawable;", "Landroid/graphics/Color;", "", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: ColorDrawable.kt */
public final class ColorDrawableKt {
    public static final ColorDrawable toDrawable(int i) {
        return new ColorDrawable(i);
    }

    public static final ColorDrawable toDrawable(Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return new ColorDrawable(color.toArgb());
    }
}
