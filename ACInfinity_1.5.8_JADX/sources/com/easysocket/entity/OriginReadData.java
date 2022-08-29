package com.easysocket.entity;

import com.easysocket.EasySocket;
import com.easysocket.utils.Utils;
import java.io.Serializable;
import java.nio.charset.Charset;

public class OriginReadData implements Serializable {
    private byte[] bodyData;
    private byte[] headerData;

    public byte[] getHeaderData() {
        return this.headerData;
    }

    public void setHeaderData(byte[] bArr) {
        this.headerData = bArr;
    }

    public byte[] getBodyBytes() {
        return this.bodyData;
    }

    public void setBodyData(byte[] bArr) {
        this.bodyData = bArr;
    }

    public String getBodyString() {
        return new String(getBodyBytes(), Charset.forName(EasySocket.getInstance().getDefOptions().getCharsetName()));
    }

    public byte[] getOriginDataBytes() {
        return Utils.concatBytes(getHeaderData(), getBodyBytes());
    }
}
