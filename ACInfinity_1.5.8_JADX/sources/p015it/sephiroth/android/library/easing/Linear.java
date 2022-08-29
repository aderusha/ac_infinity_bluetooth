package p015it.sephiroth.android.library.easing;

/* renamed from: it.sephiroth.android.library.easing.Linear */
public class Linear implements Easing {
    public double easeIn(double d, double d2, double d3, double d4) {
        return ((d3 * d) / d4) + d2;
    }

    public double easeInOut(double d, double d2, double d3, double d4) {
        return ((d3 * d) / d4) + d2;
    }

    public double easeNone(double d, double d2, double d3, double d4) {
        return ((d3 * d) / d4) + d2;
    }

    public double easeOut(double d, double d2, double d3, double d4) {
        return ((d3 * d) / d4) + d2;
    }
}
