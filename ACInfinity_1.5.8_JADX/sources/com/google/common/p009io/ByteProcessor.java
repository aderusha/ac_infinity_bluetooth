package com.google.common.p009io;

import java.io.IOException;

/* renamed from: com.google.common.io.ByteProcessor */
public interface ByteProcessor<T> {
    T getResult();

    boolean processBytes(byte[] bArr, int i, int i2) throws IOException;
}
