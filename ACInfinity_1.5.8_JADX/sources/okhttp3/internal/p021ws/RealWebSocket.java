package okhttp3.internal.p021ws;

import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.p021ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

/* renamed from: okhttp3.internal.ws.RealWebSocket */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random2, long j) {
        if ("GET".equals(request.method())) {
            this.originalRequest = request;
            this.listener = webSocketListener;
            this.random = random2;
            this.pingIntervalMillis = j;
            byte[] bArr = new byte[16];
            random2.nextBytes(bArr);
            this.key = ByteString.m1014of(bArr).base64();
            this.writerRunnable = new Runnable() {
                public void run() {
                    do {
                        try {
                        } catch (IOException e) {
                            RealWebSocket.this.failWebSocket(e, (Response) null);
                            return;
                        }
                    } while (RealWebSocket.this.writeOneFrame());
                }
            };
            return;
        }
        throw new IllegalArgumentException("Request must be GET: " + request.method());
    }

    public Request request() {
        return this.originalRequest;
    }

    public synchronized long queueSize() {
        return this.queueSize;
    }

    public void cancel() {
        this.call.cancel();
    }

    public void connect(OkHttpClient okHttpClient) {
        OkHttpClient build = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request build2 = this.originalRequest.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header(HttpHeaders.SEC_WEBSOCKET_KEY, this.key).header(HttpHeaders.SEC_WEBSOCKET_VERSION, "13").build();
        Call newWebSocketCall = Internal.instance.newWebSocketCall(build, build2);
        this.call = newWebSocketCall;
        newWebSocketCall.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams newWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + build2.url().redact(), newWebSocketStreams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, (Response) null);
                    }
                } catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly((Closeable) response);
                }
            }

            public void onFailure(Call call, IOException iOException) {
                RealWebSocket.this.failWebSocket(iOException, (Response) null);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() == 101) {
            String header = response.header(HttpHeaders.CONNECTION);
            if (HttpHeaders.UPGRADE.equalsIgnoreCase(header)) {
                String header2 = response.header(HttpHeaders.UPGRADE);
                if ("websocket".equalsIgnoreCase(header2)) {
                    String header3 = response.header(HttpHeaders.SEC_WEBSOCKET_ACCEPT);
                    String base64 = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
                    if (!base64.equals(header3)) {
                        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header3 + "'");
                    }
                    return;
                }
                throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header2 + "'");
            }
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header + "'");
        }
        throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
    }

    public void initReaderAndWriter(String str, Streams streams2) throws IOException {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            this.executor = scheduledThreadPoolExecutor;
            if (this.pingIntervalMillis != 0) {
                PingRunnable pingRunnable = new PingRunnable();
                long j = this.pingIntervalMillis;
                scheduledThreadPoolExecutor.scheduleAtFixedRate(pingRunnable, j, j, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams2.client, streams2.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, (Response) null);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void awaitTermination(int i, TimeUnit timeUnit) throws InterruptedException {
        this.executor.awaitTermination((long) i, timeUnit);
    }

    /* access modifiers changed from: package-private */
    public void tearDown() throws InterruptedException {
        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    public synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    /* access modifiers changed from: package-private */
    public synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    /* access modifiers changed from: package-private */
    public synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    public void onReadMessage(String str) throws IOException {
        this.listener.onMessage((WebSocket) this, str);
    }

    public void onReadMessage(ByteString byteString) throws IOException {
        this.listener.onMessage((WebSocket) this, byteString);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadPing(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.failed     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0022
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0012
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0024 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0012
            goto L_0x0022
        L_0x0012:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0024 }
            r0.add(r2)     // Catch:{ all -> 0x0024 }
            r1.runWriter()     // Catch:{ all -> 0x0024 }
            int r2 = r1.receivedPingCount     // Catch:{ all -> 0x0024 }
            int r2 = r2 + 1
            r1.receivedPingCount = r2     // Catch:{ all -> 0x0024 }
            monitor-exit(r1)
            return
        L_0x0022:
            monitor-exit(r1)
            return
        L_0x0024:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p021ws.RealWebSocket.onReadPing(okio.ByteString):void");
    }

    public synchronized void onReadPong(ByteString byteString) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public void onReadClose(int i, String str) {
        Streams streams2;
        if (i != -1) {
            synchronized (this) {
                if (this.receivedCloseCode == -1) {
                    this.receivedCloseCode = i;
                    this.receivedCloseReason = str;
                    streams2 = null;
                    if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                        Streams streams3 = this.streams;
                        this.streams = streams2;
                        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
                        if (scheduledFuture != null) {
                            scheduledFuture.cancel(false);
                        }
                        this.executor.shutdown();
                        streams2 = streams3;
                    }
                } else {
                    throw new IllegalStateException("already closed");
                }
            }
            try {
                this.listener.onClosing(this, i, str);
                if (streams2 != null) {
                    this.listener.onClosed(this, i, str);
                }
            } finally {
                Util.closeQuietly((Closeable) streams2);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean send(String str) {
        Objects.requireNonNull(str, "text == null");
        return send(ByteString.encodeUtf8(str), 1);
    }

    public boolean send(ByteString byteString) {
        Objects.requireNonNull(byteString, "bytes == null");
        return send(byteString, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean send(okio.ByteString r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.failed     // Catch:{ all -> 0x003e }
            r1 = 0
            if (r0 != 0) goto L_0x003c
            boolean r0 = r6.enqueuedClose     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000b
            goto L_0x003c
        L_0x000b:
            long r2 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r0 = r7.size()     // Catch:{ all -> 0x003e }
            long r4 = (long) r0     // Catch:{ all -> 0x003e }
            long r2 = r2 + r4
            r4 = 16777216(0x1000000, double:8.289046E-317)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0022
            r7 = 1001(0x3e9, float:1.403E-42)
            r8 = 0
            r6.close(r7, r8)     // Catch:{ all -> 0x003e }
            monitor-exit(r6)
            return r1
        L_0x0022:
            long r0 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r2 = r7.size()     // Catch:{ all -> 0x003e }
            long r2 = (long) r2     // Catch:{ all -> 0x003e }
            long r0 = r0 + r2
            r6.queueSize = r0     // Catch:{ all -> 0x003e }
            java.util.ArrayDeque<java.lang.Object> r0 = r6.messageAndCloseQueue     // Catch:{ all -> 0x003e }
            okhttp3.internal.ws.RealWebSocket$Message r1 = new okhttp3.internal.ws.RealWebSocket$Message     // Catch:{ all -> 0x003e }
            r1.<init>(r8, r7)     // Catch:{ all -> 0x003e }
            r0.add(r1)     // Catch:{ all -> 0x003e }
            r6.runWriter()     // Catch:{ all -> 0x003e }
            r7 = 1
            monitor-exit(r6)
            return r7
        L_0x003c:
            monitor-exit(r6)
            return r1
        L_0x003e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p021ws.RealWebSocket.send(okio.ByteString, int):boolean");
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean pong(ByteString byteString) {
        if (!this.failed) {
            if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                this.pongQueue.add(byteString);
                runWriter();
                return true;
            }
        }
        return false;
    }

    public boolean close(int i, String str) {
        return close(i, str, CANCEL_AFTER_CLOSE_MILLIS);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean close(int i, String str, long j) {
        WebSocketProtocol.validateCloseCode(i);
        ByteString byteString = null;
        if (str != null) {
            byteString = ByteString.encodeUtf8(str);
            if (((long) byteString.size()) > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + str);
            }
        }
        if (!this.failed) {
            if (!this.enqueuedClose) {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(i, byteString, j));
                runWriter();
                return true;
            }
        }
        return false;
    }

    private void runWriter() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(this.writerRunnable);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        if (r2 == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r0.writePong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
        if ((r4 instanceof okhttp3.internal.p021ws.RealWebSocket.Message) == false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0060, code lost:
        r2 = ((okhttp3.internal.p021ws.RealWebSocket.Message) r4).data;
        r0 = okio.Okio.buffer(r0.newMessageSink(((okhttp3.internal.p021ws.RealWebSocket.Message) r4).formatOpcode, (long) r2.size()));
        r0.write(r2);
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007c, code lost:
        monitor-enter(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r12.queueSize -= (long) r2.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0087, code lost:
        monitor-exit(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        if ((r4 instanceof okhttp3.internal.p021ws.RealWebSocket.Close) == false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0090, code lost:
        r4 = (okhttp3.internal.p021ws.RealWebSocket.Close) r4;
        r0.writeClose(r4.code, r4.reason);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        if (r1 == null) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        r12.listener.onClosed(r12, r3, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a0, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a4, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00aa, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ab, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ac, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00af, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeOneFrame() throws java.io.IOException {
        /*
            r12 = this;
            monitor-enter(r12)
            boolean r0 = r12.failed     // Catch:{ all -> 0x00b0 }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r12)     // Catch:{ all -> 0x00b0 }
            return r1
        L_0x0008:
            okhttp3.internal.ws.WebSocketWriter r0 = r12.writer     // Catch:{ all -> 0x00b0 }
            java.util.ArrayDeque<okio.ByteString> r2 = r12.pongQueue     // Catch:{ all -> 0x00b0 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x00b0 }
            okio.ByteString r2 = (okio.ByteString) r2     // Catch:{ all -> 0x00b0 }
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L_0x0053
            java.util.ArrayDeque<java.lang.Object> r5 = r12.messageAndCloseQueue     // Catch:{ all -> 0x00b0 }
            java.lang.Object r5 = r5.poll()     // Catch:{ all -> 0x00b0 }
            boolean r6 = r5 instanceof okhttp3.internal.p021ws.RealWebSocket.Close     // Catch:{ all -> 0x00b0 }
            if (r6 == 0) goto L_0x004b
            int r1 = r12.receivedCloseCode     // Catch:{ all -> 0x00b0 }
            java.lang.String r6 = r12.receivedCloseReason     // Catch:{ all -> 0x00b0 }
            if (r1 == r3) goto L_0x0034
            okhttp3.internal.ws.RealWebSocket$Streams r3 = r12.streams     // Catch:{ all -> 0x00b0 }
            r12.streams = r4     // Catch:{ all -> 0x00b0 }
            java.util.concurrent.ScheduledExecutorService r4 = r12.executor     // Catch:{ all -> 0x00b0 }
            r4.shutdown()     // Catch:{ all -> 0x00b0 }
            r4 = r5
            r11 = r3
            r3 = r1
            r1 = r11
            goto L_0x0055
        L_0x0034:
            java.util.concurrent.ScheduledExecutorService r3 = r12.executor     // Catch:{ all -> 0x00b0 }
            okhttp3.internal.ws.RealWebSocket$CancelRunnable r7 = new okhttp3.internal.ws.RealWebSocket$CancelRunnable     // Catch:{ all -> 0x00b0 }
            r7.<init>()     // Catch:{ all -> 0x00b0 }
            r8 = r5
            okhttp3.internal.ws.RealWebSocket$Close r8 = (okhttp3.internal.p021ws.RealWebSocket.Close) r8     // Catch:{ all -> 0x00b0 }
            long r8 = r8.cancelAfterCloseMillis     // Catch:{ all -> 0x00b0 }
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00b0 }
            java.util.concurrent.ScheduledFuture r3 = r3.schedule(r7, r8, r10)     // Catch:{ all -> 0x00b0 }
            r12.cancelFuture = r3     // Catch:{ all -> 0x00b0 }
            r3 = r1
            r1 = r4
            goto L_0x0051
        L_0x004b:
            if (r5 != 0) goto L_0x004f
            monitor-exit(r12)     // Catch:{ all -> 0x00b0 }
            return r1
        L_0x004f:
            r1 = r4
            r6 = r1
        L_0x0051:
            r4 = r5
            goto L_0x0055
        L_0x0053:
            r1 = r4
            r6 = r1
        L_0x0055:
            monitor-exit(r12)     // Catch:{ all -> 0x00b0 }
            if (r2 == 0) goto L_0x005c
            r0.writePong(r2)     // Catch:{ all -> 0x00ab }
            goto L_0x00a0
        L_0x005c:
            boolean r2 = r4 instanceof okhttp3.internal.p021ws.RealWebSocket.Message     // Catch:{ all -> 0x00ab }
            if (r2 == 0) goto L_0x008c
            r2 = r4
            okhttp3.internal.ws.RealWebSocket$Message r2 = (okhttp3.internal.p021ws.RealWebSocket.Message) r2     // Catch:{ all -> 0x00ab }
            okio.ByteString r2 = r2.data     // Catch:{ all -> 0x00ab }
            okhttp3.internal.ws.RealWebSocket$Message r4 = (okhttp3.internal.p021ws.RealWebSocket.Message) r4     // Catch:{ all -> 0x00ab }
            int r3 = r4.formatOpcode     // Catch:{ all -> 0x00ab }
            int r4 = r2.size()     // Catch:{ all -> 0x00ab }
            long r4 = (long) r4     // Catch:{ all -> 0x00ab }
            okio.Sink r0 = r0.newMessageSink(r3, r4)     // Catch:{ all -> 0x00ab }
            okio.BufferedSink r0 = okio.Okio.buffer((okio.Sink) r0)     // Catch:{ all -> 0x00ab }
            r0.write((okio.ByteString) r2)     // Catch:{ all -> 0x00ab }
            r0.close()     // Catch:{ all -> 0x00ab }
            monitor-enter(r12)     // Catch:{ all -> 0x00ab }
            long r3 = r12.queueSize     // Catch:{ all -> 0x0089 }
            int r0 = r2.size()     // Catch:{ all -> 0x0089 }
            long r5 = (long) r0     // Catch:{ all -> 0x0089 }
            long r3 = r3 - r5
            r12.queueSize = r3     // Catch:{ all -> 0x0089 }
            monitor-exit(r12)     // Catch:{ all -> 0x0089 }
            goto L_0x00a0
        L_0x0089:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0089 }
            throw r0     // Catch:{ all -> 0x00ab }
        L_0x008c:
            boolean r2 = r4 instanceof okhttp3.internal.p021ws.RealWebSocket.Close     // Catch:{ all -> 0x00ab }
            if (r2 == 0) goto L_0x00a5
            okhttp3.internal.ws.RealWebSocket$Close r4 = (okhttp3.internal.p021ws.RealWebSocket.Close) r4     // Catch:{ all -> 0x00ab }
            int r2 = r4.code     // Catch:{ all -> 0x00ab }
            okio.ByteString r4 = r4.reason     // Catch:{ all -> 0x00ab }
            r0.writeClose(r2, r4)     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x00a0
            okhttp3.WebSocketListener r0 = r12.listener     // Catch:{ all -> 0x00ab }
            r0.onClosed(r12, r3, r6)     // Catch:{ all -> 0x00ab }
        L_0x00a0:
            r0 = 1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
            return r0
        L_0x00a5:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x00ab }
            r0.<init>()     // Catch:{ all -> 0x00ab }
            throw r0     // Catch:{ all -> 0x00ab }
        L_0x00ab:
            r0 = move-exception
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
            throw r0
        L_0x00b0:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x00b0 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p021ws.RealWebSocket.writeOneFrame():boolean");
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$PingRunnable */
    private final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r1 == -1) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        failWebSocket(new java.net.SocketTimeoutException("sent ping but didn't receive pong within " + r7.pingIntervalMillis + "ms (after " + (r1 - 1) + " successful ping/pongs)"), (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        failWebSocket(r0, (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writePingFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.failed     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            return
        L_0x0007:
            okhttp3.internal.ws.WebSocketWriter r0 = r7.writer     // Catch:{ all -> 0x0053 }
            boolean r1 = r7.awaitingPong     // Catch:{ all -> 0x0053 }
            r2 = -1
            if (r1 == 0) goto L_0x0011
            int r1 = r7.sentPingCount     // Catch:{ all -> 0x0053 }
            goto L_0x0012
        L_0x0011:
            r1 = -1
        L_0x0012:
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x0053 }
            r4 = 1
            int r3 = r3 + r4
            r7.sentPingCount = r3     // Catch:{ all -> 0x0053 }
            r7.awaitingPong = r4     // Catch:{ all -> 0x0053 }
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            r3 = 0
            if (r1 == r2) goto L_0x0048
            java.net.SocketTimeoutException r0 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            r2.append(r5)
            long r5 = r7.pingIntervalMillis
            r2.append(r5)
            java.lang.String r5 = "ms (after "
            r2.append(r5)
            int r1 = r1 - r4
            r2.append(r1)
            java.lang.String r1 = " successful ping/pongs)"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            r7.failWebSocket(r0, r3)
            return
        L_0x0048:
            okio.ByteString r1 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x004e }
            r0.writePing(r1)     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r7.failWebSocket(r0, r3)
        L_0x0052:
            return
        L_0x0053:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p021ws.RealWebSocket.writePingFrame():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.listener.onFailure(r3, r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void failWebSocket(java.lang.Exception r4, @javax.annotation.Nullable okhttp3.Response r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.failed     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x002d }
            return
        L_0x0007:
            r0 = 1
            r3.failed = r0     // Catch:{ all -> 0x002d }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = r3.streams     // Catch:{ all -> 0x002d }
            r1 = 0
            r3.streams = r1     // Catch:{ all -> 0x002d }
            java.util.concurrent.ScheduledFuture<?> r1 = r3.cancelFuture     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x0017
            r2 = 0
            r1.cancel(r2)     // Catch:{ all -> 0x002d }
        L_0x0017:
            java.util.concurrent.ScheduledExecutorService r1 = r3.executor     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x001e
            r1.shutdown()     // Catch:{ all -> 0x002d }
        L_0x001e:
            monitor-exit(r3)     // Catch:{ all -> 0x002d }
            okhttp3.WebSocketListener r1 = r3.listener     // Catch:{ all -> 0x0028 }
            r1.onFailure(r3, r4, r5)     // Catch:{ all -> 0x0028 }
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
            return
        L_0x0028:
            r4 = move-exception
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
            throw r4
        L_0x002d:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.p021ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$Message */
    static final class Message {
        final ByteString data;
        final int formatOpcode;

        Message(int i, ByteString byteString) {
            this.formatOpcode = i;
            this.data = byteString;
        }
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$Close */
    static final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final ByteString reason;

        Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$Streams */
    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$CancelRunnable */
    final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        public void run() {
            RealWebSocket.this.cancel();
        }
    }
}
