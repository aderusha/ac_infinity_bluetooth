package p018me.leolin.shortcutbadger.util;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;

/* renamed from: me.leolin.shortcutbadger.util.CloseHelper */
public class CloseHelper {
    public static void close(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
