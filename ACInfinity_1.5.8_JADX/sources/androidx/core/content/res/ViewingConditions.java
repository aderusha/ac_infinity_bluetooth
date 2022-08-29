package androidx.core.content.res;

final class ViewingConditions {
    static final ViewingConditions DEFAULT = make(CamUtils.WHITE_POINT_D65, (float) ((((double) CamUtils.yFromLStar(50.0f)) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);
    private final float mAw;

    /* renamed from: mC */
    private final float f30mC;
    private final float mFl;
    private final float mFlRoot;

    /* renamed from: mN */
    private final float f31mN;
    private final float mNbb;
    private final float mNc;
    private final float mNcb;
    private final float[] mRgbD;

    /* renamed from: mZ */
    private final float f32mZ;

    /* access modifiers changed from: package-private */
    public float getAw() {
        return this.mAw;
    }

    /* access modifiers changed from: package-private */
    public float getN() {
        return this.f31mN;
    }

    /* access modifiers changed from: package-private */
    public float getNbb() {
        return this.mNbb;
    }

    /* access modifiers changed from: package-private */
    public float getNcb() {
        return this.mNcb;
    }

    /* access modifiers changed from: package-private */
    public float getC() {
        return this.f30mC;
    }

    /* access modifiers changed from: package-private */
    public float getNc() {
        return this.mNc;
    }

    /* access modifiers changed from: package-private */
    public float[] getRgbD() {
        return this.mRgbD;
    }

    /* access modifiers changed from: package-private */
    public float getFl() {
        return this.mFl;
    }

    /* access modifiers changed from: package-private */
    public float getFlRoot() {
        return this.mFlRoot;
    }

    /* access modifiers changed from: package-private */
    public float getZ() {
        return this.f32mZ;
    }

    private ViewingConditions(float f, float f2, float f3, float f4, float f5, float f6, float[] fArr, float f7, float f8, float f9) {
        this.f31mN = f;
        this.mAw = f2;
        this.mNbb = f3;
        this.mNcb = f4;
        this.f30mC = f5;
        this.mNc = f6;
        this.mRgbD = fArr;
        this.mFl = f7;
        this.mFlRoot = f8;
        this.f32mZ = f9;
    }

    static ViewingConditions make(float[] fArr, float f, float f2, float f3, boolean z) {
        float f4;
        float f5 = f;
        float[][] fArr2 = CamUtils.XYZ_TO_CAM16RGB;
        float f6 = (fArr[0] * fArr2[0][0]) + (fArr[1] * fArr2[0][1]) + (fArr[2] * fArr2[0][2]);
        float f7 = (fArr[0] * fArr2[1][0]) + (fArr[1] * fArr2[1][1]) + (fArr[2] * fArr2[1][2]);
        float f8 = (fArr[0] * fArr2[2][0]) + (fArr[1] * fArr2[2][1]) + (fArr[2] * fArr2[2][2]);
        float f9 = (f3 / 10.0f) + 0.8f;
        float lerp = ((double) f9) >= 0.9d ? CamUtils.lerp(0.59f, 0.69f, (f9 - 0.9f) * 10.0f) : CamUtils.lerp(0.525f, 0.59f, (f9 - 0.8f) * 10.0f);
        if (z) {
            f4 = 1.0f;
        } else {
            f4 = (1.0f - (((float) Math.exp((double) (((-f5) - 42.0f) / 92.0f))) * 0.2777778f)) * f9;
        }
        double d = (double) f4;
        if (d > 1.0d) {
            f4 = 1.0f;
        } else if (d < 0.0d) {
            f4 = 0.0f;
        }
        float[] fArr3 = {(((100.0f / f6) * f4) + 1.0f) - f4, (((100.0f / f7) * f4) + 1.0f) - f4, (((100.0f / f8) * f4) + 1.0f) - f4};
        float f10 = 1.0f / ((5.0f * f5) + 1.0f);
        float f11 = f10 * f10 * f10 * f10;
        float f12 = 1.0f - f11;
        float cbrt = (f11 * f5) + (0.1f * f12 * f12 * ((float) Math.cbrt(((double) f5) * 5.0d)));
        float yFromLStar = CamUtils.yFromLStar(f2) / fArr[1];
        double d2 = (double) yFromLStar;
        float sqrt = ((float) Math.sqrt(d2)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d2, 0.2d));
        float[] fArr4 = {(float) Math.pow(((double) ((fArr3[0] * cbrt) * f6)) / 100.0d, 0.42d), (float) Math.pow(((double) ((fArr3[1] * cbrt) * f7)) / 100.0d, 0.42d), (float) Math.pow(((double) ((fArr3[2] * cbrt) * f8)) / 100.0d, 0.42d)};
        float[] fArr5 = {(fArr4[0] * 400.0f) / (fArr4[0] + 27.13f), (fArr4[1] * 400.0f) / (fArr4[1] + 27.13f), (fArr4[2] * 400.0f) / (fArr4[2] + 27.13f)};
        return new ViewingConditions(yFromLStar, ((fArr5[0] * 2.0f) + fArr5[1] + (fArr5[2] * 0.05f)) * pow, pow, pow, lerp, f9, fArr3, cbrt, (float) Math.pow((double) cbrt, 0.25d), sqrt);
    }
}
