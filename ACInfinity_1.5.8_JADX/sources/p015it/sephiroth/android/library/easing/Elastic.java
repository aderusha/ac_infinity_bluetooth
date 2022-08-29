package p015it.sephiroth.android.library.easing;

/* renamed from: it.sephiroth.android.library.easing.Elastic */
public class Elastic implements Easing {
    public double easeIn(double d, double d2, double d3, double d4) {
        return easeIn(d, d2, d3, d4, d2 + d3, d4);
    }

    public double easeIn(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        if (d == 0.0d) {
            return d2;
        }
        double d9 = d / d4;
        if (d9 == 1.0d) {
            return d2 + d3;
        }
        double d10 = d6 <= 0.0d ? 0.3d * d4 : d6;
        if (d5 <= 0.0d || d5 < Math.abs(d3)) {
            d7 = d10 / 4.0d;
            d8 = d3;
        } else {
            d7 = (d10 / 6.283185307179586d) * Math.asin(d3 / d5);
            d8 = d5;
        }
        double d11 = d9 - 1.0d;
        return (-(d8 * Math.pow(2.0d, 10.0d * d11) * Math.sin((((d11 * d4) - d7) * 6.283185307179586d) / d10))) + d2;
    }

    public double easeOut(double d, double d2, double d3, double d4) {
        return easeOut(d, d2, d3, d4, d2 + d3, d4);
    }

    public double easeOut(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        if (d == 0.0d) {
            return d2;
        }
        double d9 = d / d4;
        if (d9 == 1.0d) {
            return d2 + d3;
        }
        double d10 = d6 <= 0.0d ? 0.3d * d4 : d6;
        if (d5 <= 0.0d || d5 < Math.abs(d3)) {
            d7 = d10 / 4.0d;
            d8 = d3;
        } else {
            d7 = (d10 / 6.283185307179586d) * Math.asin(d3 / d5);
            d8 = d5;
        }
        return (d8 * Math.pow(2.0d, -10.0d * d9) * Math.sin((((d9 * d4) - d7) * 6.283185307179586d) / d10)) + d3 + d2;
    }

    public double easeInOut(double d, double d2, double d3, double d4) {
        return easeInOut(d, d2, d3, d4, d2 + d3, d4);
    }

    public double easeInOut(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        double pow;
        if (d == 0.0d) {
            return d2;
        }
        double d9 = d / (d4 / 2.0d);
        if (d9 == 2.0d) {
            return d2 + d3;
        }
        double d10 = d6 <= 0.0d ? 0.44999999999999996d * d4 : d6;
        if (d5 <= 0.0d || d5 < Math.abs(d3)) {
            d7 = d10 / 4.0d;
            d8 = d3;
        } else {
            d7 = (d10 / 6.283185307179586d) * Math.asin(d3 / d5);
            d8 = d5;
        }
        if (d9 < 1.0d) {
            double d11 = d9 - 1.0d;
            pow = d8 * Math.pow(2.0d, d11 * 10.0d) * Math.sin((((d11 * d4) - d7) * 6.283185307179586d) / d10) * -0.5d;
        } else {
            double d12 = d9 - 1.0d;
            pow = (d8 * Math.pow(2.0d, -10.0d * d12) * Math.sin((((d12 * d4) - d7) * 6.283185307179586d) / d10) * 0.5d) + d3;
        }
        return pow + d2;
    }
}
