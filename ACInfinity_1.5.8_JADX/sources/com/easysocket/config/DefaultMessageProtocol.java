package com.easysocket.config;

import com.easysocket.interfaces.config.IMessageProtocol;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DefaultMessageProtocol implements IMessageProtocol {
    public int getHeaderLength() {
        return 4;
    }

    public int getBodyLength(byte[] bArr, ByteOrder byteOrder) {
        if (bArr == null || bArr.length < getHeaderLength()) {
            return 0;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(byteOrder);
        return wrap.getInt();
    }
}
