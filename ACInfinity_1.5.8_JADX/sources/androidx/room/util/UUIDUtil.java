package androidx.room.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class UUIDUtil {
    private UUIDUtil() {
    }

    public static UUID convertByteToUUID(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public static byte[] convertUUIDToByte(UUID uuid) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        return wrap.array();
    }
}
