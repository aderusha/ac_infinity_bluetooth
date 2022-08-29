package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.data.DataRewinder;
import java.nio.ByteBuffer;

public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {
    private final ByteBuffer buffer;

    public void cleanup() {
    }

    public ByteBufferRewinder(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    public ByteBuffer rewindAndGet() {
        this.buffer.position(0);
        return this.buffer;
    }

    public static class Factory implements DataRewinder.Factory<ByteBuffer> {
        public DataRewinder<ByteBuffer> build(ByteBuffer byteBuffer) {
            return new ByteBufferRewinder(byteBuffer);
        }

        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }
    }
}
