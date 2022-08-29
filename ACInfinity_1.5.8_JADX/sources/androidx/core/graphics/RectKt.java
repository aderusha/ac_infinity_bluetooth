package androidx.core.graphics;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\f\u001a\r\u0010\u0004\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\u0004\u001a\u00020\u0006*\u00020\u0003H\n\u001a\r\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\u0007\u001a\u00020\u0006*\u00020\u0003H\n\u001a\r\u0010\b\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\b\u001a\u00020\u0006*\u00020\u0003H\n\u001a\r\u0010\t\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\t\u001a\u00020\u0006*\u00020\u0003H\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u000eH\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\n\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\f\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\n\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0005H\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006H\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\n\u001a\r\u0010\u0016\u001a\u00020\u0001*\u00020\u0003H\b\u001a\r\u0010\u0017\u001a\u00020\u0003*\u00020\u0001H\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0001H\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0003H\b\u001a\u0015\u0010\u0019\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\f¨\u0006\u001d"}, mo27512d2 = {"and", "Landroid/graphics/Rect;", "r", "Landroid/graphics/RectF;", "component1", "", "", "component2", "component3", "component4", "contains", "", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "minus", "xy", "Landroid/graphics/Region;", "or", "plus", "times", "factor", "toRect", "toRectF", "toRegion", "transform", "m", "Landroid/graphics/Matrix;", "xor", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Rect.kt */
public final class RectKt {
    public static final int component1(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return rect.left;
    }

    public static final int component2(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return rect.top;
    }

    public static final int component3(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return rect.right;
    }

    public static final int component4(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return rect.bottom;
    }

    public static final float component1(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        return rectF.left;
    }

    public static final float component2(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        return rectF.top;
    }

    public static final float component3(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        return rectF.right;
    }

    public static final float component4(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        return rectF.bottom;
    }

    public static final Rect plus(Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "r");
        Rect rect3 = new Rect(rect);
        rect3.union(rect2);
        return rect3;
    }

    public static final RectF plus(RectF rectF, RectF rectF2) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(rectF2, "r");
        RectF rectF3 = new RectF(rectF);
        rectF3.union(rectF2);
        return rectF3;
    }

    public static final Rect plus(Rect rect, int i) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Rect rect2 = new Rect(rect);
        rect2.offset(i, i);
        return rect2;
    }

    public static final RectF plus(RectF rectF, float f) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        RectF rectF2 = new RectF(rectF);
        rectF2.offset(f, f);
        return rectF2;
    }

    public static final Rect plus(Rect rect, Point point) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(point, "xy");
        Rect rect2 = new Rect(rect);
        rect2.offset(point.x, point.y);
        return rect2;
    }

    public static final RectF plus(RectF rectF, PointF pointF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(pointF, "xy");
        RectF rectF2 = new RectF(rectF);
        rectF2.offset(pointF.x, pointF.y);
        return rectF2;
    }

    public static final Region minus(Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "r");
        Region region = new Region(rect);
        region.op(rect2, Region.Op.DIFFERENCE);
        return region;
    }

    public static final Region minus(RectF rectF, RectF rectF2) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(rectF2, "r");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        Region region = new Region(rect);
        Rect rect2 = new Rect();
        rectF2.roundOut(rect2);
        region.op(rect2, Region.Op.DIFFERENCE);
        return region;
    }

    public static final Rect minus(Rect rect, int i) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Rect rect2 = new Rect(rect);
        int i2 = -i;
        rect2.offset(i2, i2);
        return rect2;
    }

    public static final RectF minus(RectF rectF, float f) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        RectF rectF2 = new RectF(rectF);
        float f2 = -f;
        rectF2.offset(f2, f2);
        return rectF2;
    }

    public static final Rect minus(Rect rect, Point point) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(point, "xy");
        Rect rect2 = new Rect(rect);
        rect2.offset(-point.x, -point.y);
        return rect2;
    }

    public static final RectF minus(RectF rectF, PointF pointF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(pointF, "xy");
        RectF rectF2 = new RectF(rectF);
        rectF2.offset(-pointF.x, -pointF.y);
        return rectF2;
    }

    public static final Rect times(Rect rect, int i) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Rect rect2 = new Rect(rect);
        rect2.top *= i;
        rect2.left *= i;
        rect2.right *= i;
        rect2.bottom *= i;
        return rect2;
    }

    public static final RectF times(RectF rectF, float f) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        RectF rectF2 = new RectF(rectF);
        rectF2.top *= f;
        rectF2.left *= f;
        rectF2.right *= f;
        rectF2.bottom *= f;
        return rectF2;
    }

    public static final Rect and(Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "r");
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        return rect3;
    }

    public static final RectF and(RectF rectF, RectF rectF2) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(rectF2, "r");
        RectF rectF3 = new RectF(rectF);
        rectF3.intersect(rectF2);
        return rectF3;
    }

    public static final Region xor(Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "r");
        Region region = new Region(rect);
        region.op(rect2, Region.Op.XOR);
        return region;
    }

    public static final Region xor(RectF rectF, RectF rectF2) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(rectF2, "r");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        Region region = new Region(rect);
        Rect rect2 = new Rect();
        rectF2.roundOut(rect2);
        region.op(rect2, Region.Op.XOR);
        return region;
    }

    public static final boolean contains(Rect rect, Point point) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(point, "p");
        return rect.contains(point.x, point.y);
    }

    public static final boolean contains(RectF rectF, PointF pointF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(pointF, "p");
        return rectF.contains(pointF.x, pointF.y);
    }

    public static final RectF toRectF(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return new RectF(rect);
    }

    public static final Rect toRect(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        return rect;
    }

    public static final Region toRegion(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return new Region(rect);
    }

    public static final Region toRegion(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        return new Region(rect);
    }

    public static final RectF transform(RectF rectF, Matrix matrix) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(matrix, "m");
        matrix.mapRect(rectF);
        return rectF;
    }

    public static final RectF times(RectF rectF, int i) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        float f = (float) i;
        RectF rectF2 = new RectF(rectF);
        rectF2.top *= f;
        rectF2.left *= f;
        rectF2.right *= f;
        rectF2.bottom *= f;
        return rectF2;
    }

    /* renamed from: or */
    public static final Rect m7or(Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "r");
        Rect rect3 = new Rect(rect);
        rect3.union(rect2);
        return rect3;
    }

    /* renamed from: or */
    public static final RectF m8or(RectF rectF, RectF rectF2) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        Intrinsics.checkNotNullParameter(rectF2, "r");
        RectF rectF3 = new RectF(rectF);
        rectF3.union(rectF2);
        return rectF3;
    }
}
