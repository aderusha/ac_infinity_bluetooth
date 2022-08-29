package okhttp3.internal.p021ws;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* renamed from: okhttp3.internal.ws.WebSocketWriter */
final class WebSocketWriter {
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    final Buffer sinkBuffer;
    boolean writerClosed;

    WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random2) {
        Objects.requireNonNull(bufferedSink, "sink == null");
        Objects.requireNonNull(random2, "random == null");
        this.isClient = z;
        this.sink = bufferedSink;
        this.sinkBuffer = bufferedSink.buffer();
        this.random = random2;
        Buffer.UnsafeCursor unsafeCursor = null;
        this.maskKey = z ? new byte[4] : null;
        this.maskCursor = z ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    /* access modifiers changed from: package-private */
    public void writePing(ByteString byteString) throws IOException {
        writeControlFrame(9, byteString);
    }

    /* access modifiers changed from: package-private */
    public void writePong(ByteString byteString) throws IOException {
        writeControlFrame(10, byteString);
    }

    /* access modifiers changed from: package-private */
    public void writeClose(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            Buffer buffer2 = new Buffer();
            buffer2.writeShort(i);
            if (byteString != null) {
                buffer2.write(byteString);
            }
            byteString2 = buffer2.readByteString();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int i, ByteString byteString) throws IOException {
        if (!this.writerClosed) {
            int size = byteString.size();
            if (((long) size) <= 125) {
                this.sinkBuffer.writeByte(i | 128);
                if (this.isClient) {
                    this.sinkBuffer.writeByte(size | 128);
                    this.random.nextBytes(this.maskKey);
                    this.sinkBuffer.write(this.maskKey);
                    if (size > 0) {
                        long size2 = this.sinkBuffer.size();
                        this.sinkBuffer.write(byteString);
                        this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                        this.maskCursor.seek(size2);
                        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.writeByte(size);
                    this.sinkBuffer.write(byteString);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException("closed");
    }

    /* access modifiers changed from: package-private */
    public Sink newMessageSink(int i, long j) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            this.frameSink.formatOpcode = i;
            this.frameSink.contentLength = j;
            this.frameSink.isFirstFrame = true;
            this.frameSink.closed = false;
            return this.frameSink;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    /* access modifiers changed from: package-private */
    public void writeMessageFrame(int i, long j, boolean z, boolean z2) throws IOException {
        if (!this.writerClosed) {
            int i2 = 0;
            if (!z) {
                i = 0;
            }
            if (z2) {
                i |= 128;
            }
            this.sinkBuffer.writeByte(i);
            if (this.isClient) {
                i2 = 128;
            }
            if (j <= 125) {
                this.sinkBuffer.writeByte(((int) j) | i2);
            } else if (j <= 65535) {
                this.sinkBuffer.writeByte(i2 | 126);
                this.sinkBuffer.writeShort((int) j);
            } else {
                this.sinkBuffer.writeByte(i2 | WorkQueueKt.MASK);
                this.sinkBuffer.writeLong(j);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sinkBuffer.write(this.maskKey);
                if (j > 0) {
                    long size = this.sinkBuffer.size();
                    this.sinkBuffer.write(this.buffer, j);
                    this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(size);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.write(this.buffer, j);
            }
            this.sink.emit();
            return;
        }
        throw new IOException("closed");
    }

    /* renamed from: okhttp3.internal.ws.WebSocketWriter$FrameSink */
    final class FrameSink implements Sink {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (!this.closed) {
                WebSocketWriter.this.buffer.write(buffer, j);
                boolean z = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.size() > this.contentLength - PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                long completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
                if (completeSegmentByteCount > 0 && !z) {
                    WebSocketWriter.this.writeMessageFrame(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                    this.isFirstFrame = false;
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        public void flush() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }

        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        public void close() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, true);
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
                return;
            }
            throw new IOException("closed");
        }
    }
}
