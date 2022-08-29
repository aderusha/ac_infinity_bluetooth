package com.easysocket.interfaces.p006io;

import java.io.IOException;

/* renamed from: com.easysocket.interfaces.io.IWriter */
public interface IWriter<T> {
    void closeWriter();

    void offer(byte[] bArr);

    void openWriter();

    void setOption(T t);

    void write(byte[] bArr) throws IOException;
}
