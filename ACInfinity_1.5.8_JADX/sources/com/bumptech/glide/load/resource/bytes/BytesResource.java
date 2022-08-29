package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    public void recycle() {
    }

    public BytesResource(byte[] bArr) {
        this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
    }

    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    public byte[] get() {
        return this.bytes;
    }

    public int getSize() {
        return this.bytes.length;
    }
}
