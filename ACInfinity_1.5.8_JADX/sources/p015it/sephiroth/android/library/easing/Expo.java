package p015it.sephiroth.android.library.easing;

/* renamed from: it.sephiroth.android.library.easing.Expo */
public class Expo implements Easing {
    public double easeOut(double d, double d2, double d3, double d4) {
        return d == d4 ? d2 + d3 : d2 + (d3 * ((-Math.pow(2.0d, (d * -10.0d) / d4)) + 1.0d));
    }

    public double easeIn(double d, double d2, double d3, double d4) {
        return d == 0.0d ? d2 : d2 + (d3 * Math.pow(2.0d, ((d / d4) - 1.0d) * 10.0d));
    }

    public double easeInOut(double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        if (d == 0.0d) {
            return d2;
        }
        if (d == d4) {
            return d2 + d3;
        }
        double d7 = d / (d4 / 2.0d);
        if (d7 < 1.0d) {
            d5 = d3 / 2.0d;
            d6 = Math.pow(2.0d, (d7 - 1.0d) * 10.0d);
        } else {
            d5 = d3 / 2.0d;
            d6 = (-Math.pow(2.0d, (d7 - 1.0d) * -10.0d)) + 2.0d;
        }
        return (d5 * d6) + d2;
    }
}
