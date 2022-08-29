package androidx.room.util;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Build;
import android.util.Log;
import com.alibaba.android.arouter.utils.Consts;
import java.util.Arrays;

public class CursorUtil {
    public static Cursor copyAndClose(Cursor cursor) {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames(), cursor.getCount());
            while (cursor.moveToNext()) {
                Object[] objArr = new Object[cursor.getColumnCount()];
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    int type = cursor.getType(i);
                    if (type == 0) {
                        objArr[i] = null;
                    } else if (type == 1) {
                        objArr[i] = Long.valueOf(cursor.getLong(i));
                    } else if (type == 2) {
                        objArr[i] = Double.valueOf(cursor.getDouble(i));
                    } else if (type == 3) {
                        objArr[i] = cursor.getString(i);
                    } else if (type == 4) {
                        objArr[i] = cursor.getBlob(i);
                    } else {
                        throw new IllegalStateException();
                    }
                }
                matrixCursor.addRow(objArr);
            }
            return matrixCursor;
        } finally {
            cursor.close();
        }
    }

    public static int getColumnIndex(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex("`" + str + "`");
        if (columnIndex2 >= 0) {
            return columnIndex2;
        }
        return findColumnIndexBySuffix(cursor, str);
    }

    public static int getColumnIndexOrThrow(Cursor cursor, String str) {
        String str2;
        int columnIndex = getColumnIndex(cursor, str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            str2 = Arrays.toString(cursor.getColumnNames());
        } catch (Exception e) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e);
            str2 = "";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + str2);
    }

    private static int findColumnIndexBySuffix(Cursor cursor, String str) {
        if (Build.VERSION.SDK_INT <= 25 && str.length() != 0) {
            return findColumnIndexBySuffix(cursor.getColumnNames(), str);
        }
        return -1;
    }

    static int findColumnIndexBySuffix(String[] strArr, String str) {
        String str2 = Consts.DOT + str;
        String str3 = Consts.DOT + str + "`";
        for (int i = 0; i < strArr.length; i++) {
            String str4 = strArr[i];
            if (str4.length() >= str.length() + 2) {
                if (str4.endsWith(str2)) {
                    return i;
                }
                if (str4.charAt(0) == '`' && str4.endsWith(str3)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private CursorUtil() {
    }
}
