package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

class CamColor {
    private static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    private static final float DE_MAX = 1.0f;
    private static final float DL_MAX = 0.2f;
    private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;

    /* renamed from: mJ */
    private final float f26mJ;
    private final float mJstar;

    /* renamed from: mM */
    private final float f27mM;

    /* renamed from: mQ */
    private final float f28mQ;

    /* renamed from: mS */
    private final float f29mS;

    /* access modifiers changed from: package-private */
    public float getHue() {
        return this.mHue;
    }

    /* access modifiers changed from: package-private */
    public float getChroma() {
        return this.mChroma;
    }

    /* access modifiers changed from: package-private */
    public float getJ() {
        return this.f26mJ;
    }

    /* access modifiers changed from: package-private */
    public float getQ() {
        return this.f28mQ;
    }

    /* access modifiers changed from: package-private */
    public float getM() {
        return this.f27mM;
    }

    /* access modifiers changed from: package-private */
    public float getS() {
        return this.f29mS;
    }

    /* access modifiers changed from: package-private */
    public float getJStar() {
        return this.mJstar;
    }

    /* access modifiers changed from: package-private */
    public float getAStar() {
        return this.mAstar;
    }

    /* access modifiers changed from: package-private */
    public float getBStar() {
        return this.mBstar;
    }

    CamColor(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.mHue = f;
        this.mChroma = f2;
        this.f26mJ = f3;
        this.f28mQ = f4;
        this.f27mM = f5;
        this.f29mS = f6;
        this.mJstar = f7;
        this.mAstar = f8;
        this.mBstar = f9;
    }

    static int toColor(float f, float f2, float f3) {
        return toColor(f, f2, f3, ViewingConditions.DEFAULT);
    }

    static CamColor fromColor(int i) {
        return fromColorInViewingConditions(i, ViewingConditions.DEFAULT);
    }

    static CamColor fromColorInViewingConditions(int i, ViewingConditions viewingConditions) {
        float[] xyzFromInt = CamUtils.xyzFromInt(i);
        float[][] fArr = CamUtils.XYZ_TO_CAM16RGB;
        float f = (xyzFromInt[0] * fArr[0][0]) + (xyzFromInt[1] * fArr[0][1]) + (xyzFromInt[2] * fArr[0][2]);
        float f2 = (xyzFromInt[0] * fArr[1][0]) + (xyzFromInt[1] * fArr[1][1]) + (xyzFromInt[2] * fArr[1][2]);
        float f3 = (xyzFromInt[0] * fArr[2][0]) + (xyzFromInt[1] * fArr[2][1]) + (xyzFromInt[2] * fArr[2][2]);
        float f4 = viewingConditions.getRgbD()[0] * f;
        float f5 = viewingConditions.getRgbD()[1] * f2;
        float f6 = viewingConditions.getRgbD()[2] * f3;
        float pow = (float) Math.pow(((double) (viewingConditions.getFl() * Math.abs(f4))) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow(((double) (viewingConditions.getFl() * Math.abs(f5))) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow(((double) (viewingConditions.getFl() * Math.abs(f6))) / 100.0d, 0.42d);
        float signum = ((Math.signum(f4) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f5) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f6) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d = (double) signum3;
        float f7 = ((float) (((((double) signum) * 11.0d) + (((double) signum2) * -12.0d)) + d)) / 11.0f;
        float f8 = ((float) (((double) (signum + signum2)) - (d * 2.0d))) / 9.0f;
        float f9 = signum2 * 20.0f;
        float f10 = (((signum * 20.0f) + f9) + (21.0f * signum3)) / 20.0f;
        float f11 = (((signum * 40.0f) + f9) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2((double) f8, (double) f7)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f12 = atan2;
        float f13 = (3.1415927f * f12) / 180.0f;
        float pow4 = ((float) Math.pow((double) ((f11 * viewingConditions.getNbb()) / viewingConditions.getAw()), (double) (viewingConditions.getC() * viewingConditions.getZ()))) * 100.0f;
        float flRoot = viewingConditions.getFlRoot() * (4.0f / viewingConditions.getC()) * ((float) Math.sqrt((double) (pow4 / 100.0f))) * (viewingConditions.getAw() + 4.0f);
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d)) * ((float) Math.pow((double) ((((((((float) (Math.cos(((((double) (((double) f12) < 20.14d ? 360.0f + f12 : f12)) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.getNc()) * viewingConditions.getNcb()) * ((float) Math.sqrt((double) ((f7 * f7) + (f8 * f8))))) / (f10 + 0.305f)), 0.9d));
        float sqrt = pow5 * ((float) Math.sqrt(((double) pow4) / 100.0d));
        float flRoot2 = sqrt * viewingConditions.getFlRoot();
        float sqrt2 = ((float) Math.sqrt((double) ((pow5 * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float f14 = (1.7f * pow4) / ((0.007f * pow4) + DE_MAX);
        float log = ((float) Math.log((double) ((0.0228f * flRoot2) + DE_MAX))) * 43.85965f;
        double d2 = (double) f13;
        return new CamColor(f12, sqrt, pow4, flRoot, flRoot2, sqrt2, f14, log * ((float) Math.cos(d2)), log * ((float) Math.sin(d2)));
    }

    private static CamColor fromJch(float f, float f2, float f3) {
        return fromJchInFrame(f, f2, f3, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float f, float f2, float f3, ViewingConditions viewingConditions) {
        float f4 = f;
        double d = ((double) f4) / 100.0d;
        float c = (4.0f / viewingConditions.getC()) * ((float) Math.sqrt(d)) * (viewingConditions.getAw() + 4.0f) * viewingConditions.getFlRoot();
        float flRoot = f2 * viewingConditions.getFlRoot();
        float sqrt = ((float) Math.sqrt((double) (((f2 / ((float) Math.sqrt(d))) * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float f5 = (1.7f * f4) / ((0.007f * f4) + DE_MAX);
        float log = ((float) Math.log((((double) flRoot) * 0.0228d) + 1.0d)) * 43.85965f;
        double d2 = (double) ((3.1415927f * f3) / 180.0f);
        return new CamColor(f3, f2, f4, c, flRoot, sqrt, f5, log * ((float) Math.cos(d2)), log * ((float) Math.sin(d2)));
    }

    /* access modifiers changed from: package-private */
    public float distance(CamColor camColor) {
        float jStar = getJStar() - camColor.getJStar();
        float aStar = getAStar() - camColor.getAStar();
        float bStar = getBStar() - camColor.getBStar();
        return (float) (Math.pow(Math.sqrt((double) ((jStar * jStar) + (aStar * aStar) + (bStar * bStar))), 0.63d) * 1.41d);
    }

    /* access modifiers changed from: package-private */
    public int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    /* access modifiers changed from: package-private */
    public int viewed(ViewingConditions viewingConditions) {
        float pow = (float) Math.pow(((double) ((((double) getChroma()) == 0.0d || ((double) getJ()) == 0.0d) ? 0.0f : getChroma() / ((float) Math.sqrt(((double) getJ()) / 100.0d)))) / Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        double hue = (double) ((getHue() * 3.1415927f) / 180.0f);
        float aw = viewingConditions.getAw() * ((float) Math.pow(((double) getJ()) / 100.0d, (1.0d / ((double) viewingConditions.getC())) / ((double) viewingConditions.getZ())));
        float cos = ((float) (Math.cos(2.0d + hue) + 3.8d)) * 0.25f * 3846.1538f * viewingConditions.getNc() * viewingConditions.getNcb();
        float nbb = aw / viewingConditions.getNbb();
        float sin = (float) Math.sin(hue);
        float cos2 = (float) Math.cos(hue);
        float f = (((0.305f + nbb) * 23.0f) * pow) / (((cos * 23.0f) + ((11.0f * pow) * cos2)) + ((pow * 108.0f) * sin));
        float f2 = cos2 * f;
        float f3 = f * sin;
        float f4 = nbb * 460.0f;
        float f5 = (((451.0f * f2) + f4) + (288.0f * f3)) / 1403.0f;
        float f6 = ((f4 - (891.0f * f2)) - (261.0f * f3)) / 1403.0f;
        float f7 = ((f4 - (f2 * 220.0f)) - (f3 * 6300.0f)) / 1403.0f;
        float signum = Math.signum(f5) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) ((float) Math.max(0.0d, (((double) Math.abs(f5)) * 27.13d) / (400.0d - ((double) Math.abs(f5))))), 2.380952380952381d));
        float signum2 = Math.signum(f6) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) ((float) Math.max(0.0d, (((double) Math.abs(f6)) * 27.13d) / (400.0d - ((double) Math.abs(f6))))), 2.380952380952381d));
        float signum3 = Math.signum(f7) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) ((float) Math.max(0.0d, (((double) Math.abs(f7)) * 27.13d) / (400.0d - ((double) Math.abs(f7))))), 2.380952380952381d));
        float f8 = signum / viewingConditions.getRgbD()[0];
        float f9 = signum2 / viewingConditions.getRgbD()[1];
        float f10 = signum3 / viewingConditions.getRgbD()[2];
        float[][] fArr = CamUtils.CAM16RGB_TO_XYZ;
        return ColorUtils.XYZToColor((double) ((fArr[0][0] * f8) + (fArr[0][1] * f9) + (fArr[0][2] * f10)), (double) ((fArr[1][0] * f8) + (fArr[1][1] * f9) + (fArr[1][2] * f10)), (double) ((f8 * fArr[2][0]) + (f9 * fArr[2][1]) + (f10 * fArr[2][2])));
    }

    static int toColor(float f, float f2, float f3, ViewingConditions viewingConditions) {
        float f4;
        if (((double) f2) < 1.0d || ((double) Math.round(f3)) <= 0.0d || ((double) Math.round(f3)) >= 100.0d) {
            return CamUtils.intFromLStar(f3);
        }
        if (f < 0.0f) {
            f4 = 0.0f;
        } else {
            f4 = Math.min(360.0f, f);
        }
        float f5 = f2;
        CamColor camColor = null;
        float f6 = 0.0f;
        boolean z = true;
        while (Math.abs(f6 - f2) >= CHROMA_SEARCH_ENDPOINT) {
            CamColor findCamByJ = findCamByJ(f4, f5, f3);
            if (z) {
                if (findCamByJ != null) {
                    return findCamByJ.viewed(viewingConditions);
                }
                z = false;
            } else if (findCamByJ == null) {
                f2 = f5;
            } else {
                f6 = f5;
                camColor = findCamByJ;
            }
            f5 = ((f2 - f6) / 2.0f) + f6;
        }
        if (camColor == null) {
            return CamUtils.intFromLStar(f3);
        }
        return camColor.viewed(viewingConditions);
    }

    private static CamColor findCamByJ(float f, float f2, float f3) {
        float f4 = 1000.0f;
        CamColor camColor = null;
        float f5 = 1000.0f;
        float f6 = 100.0f;
        float f7 = 0.0f;
        while (Math.abs(f7 - f6) > LIGHTNESS_SEARCH_ENDPOINT) {
            float f8 = ((f6 - f7) / 2.0f) + f7;
            int viewedInSrgb = fromJch(f8, f2, f).viewedInSrgb();
            float lStarFromInt = CamUtils.lStarFromInt(viewedInSrgb);
            float abs = Math.abs(f3 - lStarFromInt);
            if (abs < DL_MAX) {
                CamColor fromColor = fromColor(viewedInSrgb);
                float distance = fromColor.distance(fromJch(fromColor.getJ(), fromColor.getChroma(), f));
                if (distance <= DE_MAX) {
                    camColor = fromColor;
                    f4 = abs;
                    f5 = distance;
                }
            }
            if (f4 == 0.0f && f5 == 0.0f) {
                break;
            } else if (lStarFromInt < f3) {
                f7 = f8;
            } else {
                f6 = f8;
            }
        }
        return camColor;
    }
}
