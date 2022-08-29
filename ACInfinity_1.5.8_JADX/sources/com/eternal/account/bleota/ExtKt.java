package com.eternal.account.bleota;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, mo27512d2 = {"UUID_NOTIFY_DESCRIPTOR", "Ljava/util/UUID;", "getUUID_NOTIFY_DESCRIPTOR", "()Ljava/util/UUID;", "bleUUID", "uuid", "", "module-account_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Ext.kt */
public final class ExtKt {
    private static final UUID UUID_NOTIFY_DESCRIPTOR;

    static {
        UUID fromString = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(\"00002902-0000-1000-8000-00805f9b34fb\")");
        UUID_NOTIFY_DESCRIPTOR = fromString;
    }

    public static final UUID getUUID_NOTIFY_DESCRIPTOR() {
        return UUID_NOTIFY_DESCRIPTOR;
    }

    public static final UUID bleUUID(String str) {
        Intrinsics.checkNotNullParameter(str, "uuid");
        UUID fromString = UUID.fromString("0000" + str + "-0000-1000-8000-00805f9b34fb");
        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(\"0000$uuid-00…-1000-8000-00805f9b34fb\")");
        return fromString;
    }
}
