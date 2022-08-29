package p019no.nordicsemi.android.dfu.internal.scanner;

/* renamed from: no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner */
public interface BootloaderScanner {
    public static final int ADDRESS_DIFF = 1;
    public static final long TIMEOUT = 5000;

    String searchFor(String str);
}
