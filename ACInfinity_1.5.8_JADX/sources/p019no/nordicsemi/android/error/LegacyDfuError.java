package p019no.nordicsemi.android.error;

import com.eternal.widget.wheelview.common.WheelConstants;

/* renamed from: no.nordicsemi.android.error.LegacyDfuError */
public final class LegacyDfuError {
    public static final int CRC_ERROR = 5;
    public static final int DATA_SIZE_EXCEEDS_LIMIT = 4;
    public static final int INVALID_STATE = 2;
    public static final int NOT_SUPPORTED = 3;
    public static final int OPERATION_FAILED = 6;

    public static String parse(int i) {
        switch (i) {
            case WheelConstants.WHEEL_SCROLL_START_WHAT:
                return "INVALID STATE";
            case 259:
                return "NOT SUPPORTED";
            case 260:
                return "DATA SIZE EXCEEDS LIMIT";
            case 261:
                return "INVALID CRC ERROR";
            case 262:
                return "OPERATION FAILED";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }
}
