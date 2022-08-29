package okhttp3.internal.http2;

import okhttp3.internal.Util;
import okio.ByteString;

/* renamed from: okhttp3.internal.http2.Header */
public final class C3987Header {
    public static final ByteString PSEUDO_PREFIX = ByteString.encodeUtf8(":");
    public static final ByteString RESPONSE_STATUS = ByteString.encodeUtf8(":status");
    public static final ByteString TARGET_AUTHORITY = ByteString.encodeUtf8(":authority");
    public static final ByteString TARGET_METHOD = ByteString.encodeUtf8(":method");
    public static final ByteString TARGET_PATH = ByteString.encodeUtf8(":path");
    public static final ByteString TARGET_SCHEME = ByteString.encodeUtf8(":scheme");
    final int hpackSize;
    public final ByteString name;
    public final ByteString value;

    public C3987Header(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public C3987Header(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public C3987Header(ByteString byteString, ByteString byteString2) {
        this.name = byteString;
        this.value = byteString2;
        this.hpackSize = byteString.size() + 32 + byteString2.size();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C3987Header)) {
            return false;
        }
        C3987Header header = (C3987Header) obj;
        if (!this.name.equals(header.name) || !this.value.equals(header.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((527 + this.name.hashCode()) * 31) + this.value.hashCode();
    }

    public String toString() {
        return Util.format("%s: %s", this.name.utf8(), this.value.utf8());
    }
}
