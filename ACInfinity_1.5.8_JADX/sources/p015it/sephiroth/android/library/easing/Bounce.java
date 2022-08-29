package p015it.sephiroth.android.library.easing;

/* renamed from: it.sephiroth.android.library.easing.Bounce */
public class Bounce implements Easing {
    public double easeOut(double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        double d7;
        double d8 = d / d4;
        if (d8 < 0.36363636363636365d) {
            d7 = 7.5625d * d8 * d8;
        } else {
            if (d8 < 0.7272727272727273d) {
                double d9 = d8 - 0.5454545454545454d;
                d5 = 7.5625d * d9 * d9;
                d6 = 0.75d;
            } else if (d8 < 0.9090909090909091d) {
                double d10 = d8 - 0.8181818181818182d;
                d5 = 7.5625d * d10 * d10;
                d6 = 0.9375d;
            } else {
                double d11 = d8 - 0.9545454545454546d;
                d5 = 7.5625d * d11 * d11;
                d6 = 0.984375d;
            }
            d7 = d5 + d6;
        }
        return (d3 * d7) + d2;
    }

    public double easeIn(double d, double d2, double d3, double d4) {
        return (d3 - easeOut(d4 - d, 0.0d, d3, d4)) + d2;
    }

    public double easeInOut(double d, double d2, double d3, double d4) {
        double easeOut;
        if (d < d4 / 2.0d) {
            easeOut = easeIn(d * 2.0d, 0.0d, d3, d4) * 0.5d;
        } else {
            easeOut = (easeOut((2.0d * d) - d4, 0.0d, d3, d4) * 0.5d) + (d3 * 0.5d);
        }
        return easeOut + d2;
    }
}
