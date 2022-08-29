package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    final Http2Connection connection;
    ErrorCode errorCode = null;
    private boolean hasResponseHeaders;

    /* renamed from: id */
    final int f1217id;
    final StreamTimeout readTimeout = new StreamTimeout();
    private final List<C3987Header> requestHeaders;
    private List<C3987Header> responseHeaders;
    final FramingSink sink;
    private final FramingSource source;
    long unacknowledgedBytesRead = 0;
    final StreamTimeout writeTimeout = new StreamTimeout();

    Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, List<C3987Header> list) {
        Objects.requireNonNull(http2Connection, "connection == null");
        Objects.requireNonNull(list, "requestHeaders == null");
        this.f1217id = i;
        this.connection = http2Connection;
        this.bytesLeftInWriteWindow = (long) http2Connection.peerSettings.getInitialWindowSize();
        FramingSource framingSource = new FramingSource((long) http2Connection.okHttpSettings.getInitialWindowSize());
        this.source = framingSource;
        FramingSink framingSink = new FramingSink();
        this.sink = framingSink;
        framingSource.finished = z2;
        framingSink.finished = z;
        this.requestHeaders = list;
    }

    public int getId() {
        return this.f1217id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && ((this.sink.finished || this.sink.closed) && this.hasResponseHeaders)) {
            return false;
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        if (this.connection.client == ((this.f1217id & 1) == 1)) {
            return true;
        }
        return false;
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public List<C3987Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002f, code lost:
        r2.readTimeout.exitAndThrowIfTimedOut();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<okhttp3.internal.http2.C3987Header> takeResponseHeaders() throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isLocallyInitiated()     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0035
            okhttp3.internal.http2.Http2Stream$StreamTimeout r0 = r2.readTimeout     // Catch:{ all -> 0x003d }
            r0.enter()     // Catch:{ all -> 0x003d }
        L_0x000c:
            java.util.List<okhttp3.internal.http2.Header> r0 = r2.responseHeaders     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x0018
            okhttp3.internal.http2.ErrorCode r0 = r2.errorCode     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x0018
            r2.waitForIo()     // Catch:{ all -> 0x002e }
            goto L_0x000c
        L_0x0018:
            okhttp3.internal.http2.Http2Stream$StreamTimeout r0 = r2.readTimeout     // Catch:{ all -> 0x003d }
            r0.exitAndThrowIfTimedOut()     // Catch:{ all -> 0x003d }
            java.util.List<okhttp3.internal.http2.Header> r0 = r2.responseHeaders     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0026
            r1 = 0
            r2.responseHeaders = r1     // Catch:{ all -> 0x003d }
            monitor-exit(r2)
            return r0
        L_0x0026:
            okhttp3.internal.http2.StreamResetException r0 = new okhttp3.internal.http2.StreamResetException     // Catch:{ all -> 0x003d }
            okhttp3.internal.http2.ErrorCode r1 = r2.errorCode     // Catch:{ all -> 0x003d }
            r0.<init>(r1)     // Catch:{ all -> 0x003d }
            throw r0     // Catch:{ all -> 0x003d }
        L_0x002e:
            r0 = move-exception
            okhttp3.internal.http2.Http2Stream$StreamTimeout r1 = r2.readTimeout     // Catch:{ all -> 0x003d }
            r1.exitAndThrowIfTimedOut()     // Catch:{ all -> 0x003d }
            throw r0     // Catch:{ all -> 0x003d }
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003d }
            java.lang.String r1 = "servers cannot read response headers"
            r0.<init>(r1)     // Catch:{ all -> 0x003d }
            throw r0     // Catch:{ all -> 0x003d }
        L_0x003d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.takeResponseHeaders():java.util.List");
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void sendResponseHeaders(List<C3987Header> list, boolean z) throws IOException {
        Objects.requireNonNull(list, "responseHeaders == null");
        boolean z2 = false;
        synchronized (this) {
            this.hasResponseHeaders = true;
            if (!z) {
                this.sink.finished = true;
                z2 = true;
            }
        }
        this.connection.writeSynReply(this.f1217id, z2, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders) {
                if (!isLocallyInitiated()) {
                    throw new IllegalStateException("reply before requesting the sink");
                }
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode2) throws IOException {
        if (closeInternal(errorCode2)) {
            this.connection.writeSynReset(this.f1217id, errorCode2);
        }
    }

    public void closeLater(ErrorCode errorCode2) {
        if (closeInternal(errorCode2)) {
            this.connection.writeSynResetLater(this.f1217id, errorCode2);
        }
    }

    private boolean closeInternal(ErrorCode errorCode2) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode2;
            notifyAll();
            this.connection.removeStream(this.f1217id);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void receiveHeaders(List<C3987Header> list) {
        boolean z;
        synchronized (this) {
            z = true;
            this.hasResponseHeaders = true;
            if (this.responseHeaders == null) {
                this.responseHeaders = list;
                z = isOpen();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.responseHeaders);
                arrayList.add((Object) null);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (!z) {
            this.connection.removeStream(this.f1217id);
        }
    }

    /* access modifiers changed from: package-private */
    public void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, (long) i);
    }

    /* access modifiers changed from: package-private */
    public void receiveFin() {
        boolean isOpen;
        synchronized (this) {
            this.source.finished = true;
            isOpen = isOpen();
            notifyAll();
        }
        if (!isOpen) {
            this.connection.removeStream(this.f1217id);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void receiveRstStream(ErrorCode errorCode2) {
        if (this.errorCode == null) {
            this.errorCode = errorCode2;
            notifyAll();
        }
    }

    private final class FramingSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean closed;
        boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer = new Buffer();
        private final Buffer receiveBuffer = new Buffer();

        static {
            Class<Http2Stream> cls = Http2Stream.class;
        }

        FramingSource(long j) {
            this.maxByteCount = j;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x005b, code lost:
            r10 = r7.this$0.connection;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x005f, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r7.this$0.connection.unacknowledgedBytesRead += r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
            if (r7.this$0.connection.unacknowledgedBytesRead < ((long) (r7.this$0.connection.okHttpSettings.getInitialWindowSize() / 2))) goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0080, code lost:
            r7.this$0.connection.writeWindowUpdateLater(0, r7.this$0.connection.unacknowledgedBytesRead);
            r7.this$0.connection.unacknowledgedBytesRead = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0094, code lost:
            monitor-exit(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0095, code lost:
            return r8;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r8, long r9) throws java.io.IOException {
            /*
                r7 = this;
                r0 = 0
                int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r2 < 0) goto L_0x009c
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r2)
                r7.waitUntilReadable()     // Catch:{ all -> 0x0099 }
                r7.checkNotClosed()     // Catch:{ all -> 0x0099 }
                okio.Buffer r3 = r7.readBuffer     // Catch:{ all -> 0x0099 }
                long r3 = r3.size()     // Catch:{ all -> 0x0099 }
                int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r5 != 0) goto L_0x001d
                r8 = -1
                monitor-exit(r2)     // Catch:{ all -> 0x0099 }
                return r8
            L_0x001d:
                okio.Buffer r3 = r7.readBuffer     // Catch:{ all -> 0x0099 }
                long r4 = r3.size()     // Catch:{ all -> 0x0099 }
                long r9 = java.lang.Math.min(r9, r4)     // Catch:{ all -> 0x0099 }
                long r8 = r3.read(r8, r9)     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                long r3 = r10.unacknowledgedBytesRead     // Catch:{ all -> 0x0099 }
                long r3 = r3 + r8
                r10.unacknowledgedBytesRead = r3     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                long r3 = r10.unacknowledgedBytesRead     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Connection r10 = r10.connection     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Settings r10 = r10.okHttpSettings     // Catch:{ all -> 0x0099 }
                int r10 = r10.getInitialWindowSize()     // Catch:{ all -> 0x0099 }
                int r10 = r10 / 2
                long r5 = (long) r10     // Catch:{ all -> 0x0099 }
                int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r10 < 0) goto L_0x005a
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Connection r10 = r10.connection     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r3 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                int r3 = r3.f1217id     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r4 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                long r4 = r4.unacknowledgedBytesRead     // Catch:{ all -> 0x0099 }
                r10.writeWindowUpdateLater(r3, r4)     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0099 }
                r10.unacknowledgedBytesRead = r0     // Catch:{ all -> 0x0099 }
            L_0x005a:
                monitor-exit(r2)     // Catch:{ all -> 0x0099 }
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r10 = r10.connection
                monitor-enter(r10)
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Connection r2 = r2.connection     // Catch:{ all -> 0x0096 }
                long r3 = r2.unacknowledgedBytesRead     // Catch:{ all -> 0x0096 }
                long r3 = r3 + r8
                r2.unacknowledgedBytesRead = r3     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Connection r2 = r2.connection     // Catch:{ all -> 0x0096 }
                long r2 = r2.unacknowledgedBytesRead     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Stream r4 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Connection r4 = r4.connection     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Settings r4 = r4.okHttpSettings     // Catch:{ all -> 0x0096 }
                int r4 = r4.getInitialWindowSize()     // Catch:{ all -> 0x0096 }
                int r4 = r4 / 2
                long r4 = (long) r4     // Catch:{ all -> 0x0096 }
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 < 0) goto L_0x0094
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Connection r2 = r2.connection     // Catch:{ all -> 0x0096 }
                r3 = 0
                okhttp3.internal.http2.Http2Stream r4 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Connection r4 = r4.connection     // Catch:{ all -> 0x0096 }
                long r4 = r4.unacknowledgedBytesRead     // Catch:{ all -> 0x0096 }
                r2.writeWindowUpdateLater(r3, r4)     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0096 }
                okhttp3.internal.http2.Http2Connection r2 = r2.connection     // Catch:{ all -> 0x0096 }
                r2.unacknowledgedBytesRead = r0     // Catch:{ all -> 0x0096 }
            L_0x0094:
                monitor-exit(r10)     // Catch:{ all -> 0x0096 }
                return r8
            L_0x0096:
                r8 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x0096 }
                throw r8
            L_0x0099:
                r8 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0099 }
                throw r8
            L_0x009c:
                java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "byteCount < 0: "
                r0.append(r1)
                r0.append(r9)
                java.lang.String r9 = r0.toString()
                r8.<init>(r9)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        private void waitUntilReadable() throws IOException {
            Http2Stream.this.readTimeout.enter();
            while (this.readBuffer.size() == 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                try {
                    Http2Stream.this.waitForIo();
                } finally {
                    Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void receive(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (Http2Stream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.size() + j > this.maxByteCount;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    bufferedSource.skip(j);
                    return;
                } else {
                    long read = bufferedSource.read(this.receiveBuffer, j);
                    if (read != -1) {
                        j -= read;
                        synchronized (Http2Stream.this) {
                            if (this.readBuffer.size() != 0) {
                                z2 = false;
                            }
                            this.readBuffer.writeAll(this.receiveBuffer);
                            if (z2) {
                                Http2Stream.this.notifyAll();
                            }
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }

        public void close() throws IOException {
            synchronized (Http2Stream.this) {
                this.closed = true;
                this.readBuffer.clear();
                Http2Stream.this.notifyAll();
            }
            Http2Stream.this.cancelStreamIfNecessary();
        }

        private void checkNotClosed() throws IOException {
            if (this.closed) {
                throw new IOException("stream closed");
            } else if (Http2Stream.this.errorCode != null) {
                throw new StreamResetException(Http2Stream.this.errorCode);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean isOpen;
        synchronized (this) {
            z = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            isOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else if (!isOpen) {
            this.connection.removeStream(this.f1217id);
        }
    }

    final class FramingSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        boolean closed;
        boolean finished;
        private final Buffer sendBuffer = new Buffer();

        static {
            Class<Http2Stream> cls = Http2Stream.class;
        }

        FramingSink() {
        }

        public void write(Buffer buffer, long j) throws IOException {
            this.sendBuffer.write(buffer, j);
            while (this.sendBuffer.size() >= 16384) {
                emitFrame(false);
            }
        }

        /* JADX INFO: finally extract failed */
        private void emitFrame(boolean z) throws IOException {
            long min;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (Http2Stream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                    try {
                        Http2Stream.this.waitForIo();
                    } catch (Throwable th) {
                        Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed();
                min = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
                Http2Stream.this.bytesLeftInWriteWindow -= min;
            }
            Http2Stream.this.writeTimeout.enter();
            try {
                Http2Stream.this.connection.writeData(Http2Stream.this.f1217id, z && min == this.sendBuffer.size(), this.sendBuffer, min);
            } finally {
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        public void flush() throws IOException {
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
            if (r8.sendBuffer.size() <= 0) goto L_0x002d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
            if (r8.sendBuffer.size() <= 0) goto L_0x003c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            emitFrame(true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
            r8.this$0.connection.writeData(r8.this$0.f1217id, true, (okio.Buffer) null, 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
            r2 = r8.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r8.closed = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
            r8.this$0.connection.flush();
            r8.this$0.cancelStreamIfNecessary();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
            if (r8.this$0.sink.finished != false) goto L_0x003c;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r8 = this;
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r0)
                boolean r1 = r8.closed     // Catch:{ all -> 0x0052 }
                if (r1 == 0) goto L_0x0009
                monitor-exit(r0)     // Catch:{ all -> 0x0052 }
                return
            L_0x0009:
                monitor-exit(r0)     // Catch:{ all -> 0x0052 }
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Stream$FramingSink r0 = r0.sink
                boolean r0 = r0.finished
                r1 = 1
                if (r0 != 0) goto L_0x003c
                okio.Buffer r0 = r8.sendBuffer
                long r2 = r0.size()
                r4 = 0
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x002d
            L_0x001f:
                okio.Buffer r0 = r8.sendBuffer
                long r2 = r0.size()
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x003c
                r8.emitFrame(r1)
                goto L_0x001f
            L_0x002d:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r2 = r0.connection
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                int r3 = r0.f1217id
                r4 = 1
                r5 = 0
                r6 = 0
                r2.writeData(r3, r4, r5, r6)
            L_0x003c:
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r2)
                r8.closed = r1     // Catch:{ all -> 0x004f }
                monitor-exit(r2)     // Catch:{ all -> 0x004f }
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r0 = r0.connection
                r0.flush()
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                r0.cancelStreamIfNecessary()
                return
            L_0x004f:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x004f }
                throw r0
            L_0x0052:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0052 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.close():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        } else if (this.sink.finished) {
            throw new IOException("stream finished");
        } else if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
    }

    /* access modifiers changed from: package-private */
    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        /* access modifiers changed from: protected */
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }

        /* access modifiers changed from: protected */
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException((IOException) null);
            }
        }
    }
}
