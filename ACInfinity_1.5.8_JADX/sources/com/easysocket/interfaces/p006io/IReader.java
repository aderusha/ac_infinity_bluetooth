package com.easysocket.interfaces.p006io;

/* renamed from: com.easysocket.interfaces.io.IReader */
public interface IReader<T> {
    void closeReader();

    void openReader();

    void read() throws Exception;

    void setOption(T t);
}
