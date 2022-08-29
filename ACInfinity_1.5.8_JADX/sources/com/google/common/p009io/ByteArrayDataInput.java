package com.google.common.p009io;

import java.io.DataInput;

/* renamed from: com.google.common.io.ByteArrayDataInput */
public interface ByteArrayDataInput extends DataInput {
    boolean readBoolean();

    byte readByte();

    char readChar();

    double readDouble();

    float readFloat();

    void readFully(byte[] bArr);

    void readFully(byte[] bArr, int i, int i2);

    int readInt();

    String readLine();

    long readLong();

    short readShort();

    String readUTF();

    int readUnsignedByte();

    int readUnsignedShort();

    int skipBytes(int i);
}
