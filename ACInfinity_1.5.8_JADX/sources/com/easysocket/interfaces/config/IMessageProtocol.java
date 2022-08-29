package com.easysocket.interfaces.config;

import java.nio.ByteOrder;

public interface IMessageProtocol {
    int getBodyLength(byte[] bArr, ByteOrder byteOrder);

    int getHeaderLength();
}
