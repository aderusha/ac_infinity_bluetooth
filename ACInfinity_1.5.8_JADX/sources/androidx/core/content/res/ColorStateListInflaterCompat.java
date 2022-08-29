package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ColorStateListInflaterCompat {
    private static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();

    private ColorStateListInflaterCompat() {
    }

    public static ColorStateList inflate(Resources resources, int i, Resources.Theme theme) {
        try {
            return createFromXml(resources, resources.getXml(i), theme);
        } catch (Exception e) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList createFromXml(android.content.res.Resources r4, org.xmlpull.v1.XmlPullParser r5, android.content.res.Resources.Theme r6) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)
        L_0x0004:
            int r1 = r5.next()
            r2 = 2
            if (r1 == r2) goto L_0x000f
            r3 = 1
            if (r1 == r3) goto L_0x000f
            goto L_0x0004
        L_0x000f:
            if (r1 != r2) goto L_0x0016
            android.content.res.ColorStateList r4 = createFromXmlInner(r4, r5, r0, r6)
            return r4
        L_0x0016:
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r5 = "No start tag found"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ColorStateListInflaterCompat.createFromXml(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    public static ColorStateList createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("selector")) {
            return inflate(resources, xmlPullParser, attributeSet, theme);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList inflate(android.content.res.Resources r17, org.xmlpull.v1.XmlPullParser r18, android.util.AttributeSet r19, android.content.res.Resources.Theme r20) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r0 = r17
            r1 = r19
            r2 = r20
            int r3 = r18.getDepth()
            r4 = 1
            int r3 = r3 + r4
            r5 = 20
            int[][] r6 = new int[r5][]
            int[] r5 = new int[r5]
            r8 = 0
        L_0x0013:
            int r9 = r18.next()
            if (r9 == r4) goto L_0x00f4
            int r10 = r18.getDepth()
            if (r10 >= r3) goto L_0x0022
            r11 = 3
            if (r9 == r11) goto L_0x00f4
        L_0x0022:
            r11 = 2
            if (r9 != r11) goto L_0x00ef
            if (r10 > r3) goto L_0x00ef
            java.lang.String r9 = r18.getName()
            java.lang.String r10 = "item"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0035
            goto L_0x00ef
        L_0x0035:
            int[] r9 = androidx.core.C0167R.styleable.ColorStateListItem
            android.content.res.TypedArray r9 = obtainAttributes(r0, r2, r1, r9)
            int r10 = androidx.core.C0167R.styleable.ColorStateListItem_android_color
            r11 = -1
            int r10 = r9.getResourceId(r10, r11)
            r12 = -65281(0xffffffffffff00ff, float:NaN)
            if (r10 == r11) goto L_0x0061
            boolean r11 = isColorInt(r0, r10)
            if (r11 != 0) goto L_0x0061
            android.content.res.XmlResourceParser r10 = r0.getXml(r10)     // Catch:{ Exception -> 0x005a }
            android.content.res.ColorStateList r10 = createFromXml(r0, r10, r2)     // Catch:{ Exception -> 0x005a }
            int r10 = r10.getDefaultColor()     // Catch:{ Exception -> 0x005a }
            goto L_0x0067
        L_0x005a:
            int r10 = androidx.core.C0167R.styleable.ColorStateListItem_android_color
            int r10 = r9.getColor(r10, r12)
            goto L_0x0067
        L_0x0061:
            int r10 = androidx.core.C0167R.styleable.ColorStateListItem_android_color
            int r10 = r9.getColor(r10, r12)
        L_0x0067:
            r11 = 1065353216(0x3f800000, float:1.0)
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_android_alpha
            boolean r12 = r9.hasValue(r12)
            if (r12 == 0) goto L_0x0078
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_android_alpha
            float r11 = r9.getFloat(r12, r11)
            goto L_0x0086
        L_0x0078:
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_alpha
            boolean r12 = r9.hasValue(r12)
            if (r12 == 0) goto L_0x0086
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_alpha
            float r11 = r9.getFloat(r12, r11)
        L_0x0086:
            boolean r12 = androidx.core.p003os.BuildCompat.isAtLeastS()
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r12 == 0) goto L_0x009d
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_android_lStar
            boolean r12 = r9.hasValue(r12)
            if (r12 == 0) goto L_0x009d
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_android_lStar
            float r12 = r9.getFloat(r12, r13)
            goto L_0x00a3
        L_0x009d:
            int r12 = androidx.core.C0167R.styleable.ColorStateListItem_lStar
            float r12 = r9.getFloat(r12, r13)
        L_0x00a3:
            r9.recycle()
            int r9 = r19.getAttributeCount()
            int[] r13 = new int[r9]
            r14 = 0
            r15 = 0
        L_0x00ae:
            if (r14 >= r9) goto L_0x00da
            int r4 = r1.getAttributeNameResource(r14)
            r7 = 16843173(0x10101a5, float:2.3694738E-38)
            if (r4 == r7) goto L_0x00d4
            r7 = 16843551(0x101031f, float:2.3695797E-38)
            if (r4 == r7) goto L_0x00d4
            int r7 = androidx.core.C0167R.attr.alpha
            if (r4 == r7) goto L_0x00d4
            int r7 = androidx.core.C0167R.attr.lStar
            if (r4 == r7) goto L_0x00d4
            int r7 = r15 + 1
            r0 = 0
            boolean r16 = r1.getAttributeBooleanValue(r14, r0)
            if (r16 == 0) goto L_0x00d0
            goto L_0x00d1
        L_0x00d0:
            int r4 = -r4
        L_0x00d1:
            r13[r15] = r4
            r15 = r7
        L_0x00d4:
            int r14 = r14 + 1
            r4 = 1
            r0 = r17
            goto L_0x00ae
        L_0x00da:
            int[] r0 = android.util.StateSet.trimStateSet(r13, r15)
            int r4 = modulateColorAlpha(r10, r11, r12)
            int[] r5 = androidx.core.content.res.GrowingArrayUtils.append((int[]) r5, (int) r8, (int) r4)
            java.lang.Object[] r0 = androidx.core.content.res.GrowingArrayUtils.append((T[]) r6, (int) r8, r0)
            r6 = r0
            int[][] r6 = (int[][]) r6
            int r8 = r8 + 1
        L_0x00ef:
            r4 = 1
            r0 = r17
            goto L_0x0013
        L_0x00f4:
            int[] r0 = new int[r8]
            int[][] r1 = new int[r8][]
            r2 = 0
            java.lang.System.arraycopy(r5, r2, r0, r2, r8)
            java.lang.System.arraycopy(r6, r2, r1, r2, r8)
            android.content.res.ColorStateList r2 = new android.content.res.ColorStateList
            r2.<init>(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ColorStateListInflaterCompat.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    private static boolean isColorInt(Resources resources, int i) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.type < 28 || typedValue.type > 31) {
            return false;
        }
        return true;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = sTempTypedValue;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    private static int modulateColorAlpha(int i, float f, float f2) {
        boolean z = f2 >= 0.0f && f2 <= 100.0f;
        if (f == 1.0f && !z) {
            return i;
        }
        int clamp = MathUtils.clamp((int) ((((float) Color.alpha(i)) * f) + 0.5f), 0, 255);
        if (z) {
            CamColor fromColor = CamColor.fromColor(i);
            i = CamColor.toColor(fromColor.getHue(), fromColor.getChroma(), f2);
        }
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (clamp << 24);
    }
}
