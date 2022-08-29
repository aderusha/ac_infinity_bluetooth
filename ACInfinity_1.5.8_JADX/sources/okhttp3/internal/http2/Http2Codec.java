package okhttp3.internal.http2;

import com.eternal.framework.http.cookie.SerializableCookie;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Http2Codec implements HttpCodec {
    private static final ByteString CONNECTION;
    private static final ByteString ENCODING;
    private static final ByteString HOST;
    private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS;
    private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS;
    private static final ByteString KEEP_ALIVE;
    private static final ByteString PROXY_CONNECTION;

    /* renamed from: TE */
    private static final ByteString f1216TE;
    private static final ByteString TRANSFER_ENCODING;
    private static final ByteString UPGRADE;
    private final Interceptor.Chain chain;
    private final OkHttpClient client;
    private final Http2Connection connection;
    private Http2Stream stream;
    final StreamAllocation streamAllocation;

    static {
        ByteString encodeUtf8 = ByteString.encodeUtf8("connection");
        CONNECTION = encodeUtf8;
        ByteString encodeUtf82 = ByteString.encodeUtf8(SerializableCookie.HOST);
        HOST = encodeUtf82;
        ByteString encodeUtf83 = ByteString.encodeUtf8("keep-alive");
        KEEP_ALIVE = encodeUtf83;
        ByteString encodeUtf84 = ByteString.encodeUtf8("proxy-connection");
        PROXY_CONNECTION = encodeUtf84;
        ByteString encodeUtf85 = ByteString.encodeUtf8("transfer-encoding");
        TRANSFER_ENCODING = encodeUtf85;
        ByteString encodeUtf86 = ByteString.encodeUtf8("te");
        f1216TE = encodeUtf86;
        ByteString encodeUtf87 = ByteString.encodeUtf8("encoding");
        ENCODING = encodeUtf87;
        ByteString encodeUtf88 = ByteString.encodeUtf8("upgrade");
        UPGRADE = encodeUtf88;
        HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList((T[]) new ByteString[]{encodeUtf8, encodeUtf82, encodeUtf83, encodeUtf84, encodeUtf86, encodeUtf85, encodeUtf87, encodeUtf88, C3987Header.TARGET_METHOD, C3987Header.TARGET_PATH, C3987Header.TARGET_SCHEME, C3987Header.TARGET_AUTHORITY});
        HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList((T[]) new ByteString[]{encodeUtf8, encodeUtf82, encodeUtf83, encodeUtf84, encodeUtf86, encodeUtf85, encodeUtf87, encodeUtf88});
    }

    public Http2Codec(OkHttpClient okHttpClient, Interceptor.Chain chain2, StreamAllocation streamAllocation2, Http2Connection http2Connection) {
        this.client = okHttpClient;
        this.chain = chain2;
        this.streamAllocation = streamAllocation2;
        this.connection = http2Connection;
    }

    public Sink createRequestBody(Request request, long j) {
        return this.stream.getSink();
    }

    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream == null) {
            Http2Stream newStream = this.connection.newStream(http2HeadersList(request), request.body() != null);
            this.stream = newStream;
            newStream.readTimeout().timeout((long) this.chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
            this.stream.writeTimeout().timeout((long) this.chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public void flushRequest() throws IOException {
        this.connection.flush();
    }

    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    public Response.Builder readResponseHeaders(boolean z) throws IOException {
        Response.Builder readHttp2HeadersList = readHttp2HeadersList(this.stream.takeResponseHeaders());
        if (!z || Internal.instance.code(readHttp2HeadersList) != 100) {
            return readHttp2HeadersList;
        }
        return null;
    }

    public static List<C3987Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new C3987Header(C3987Header.TARGET_METHOD, request.method()));
        arrayList.add(new C3987Header(C3987Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String header = request.header(HttpHeaders.HOST);
        if (header != null) {
            arrayList.add(new C3987Header(C3987Header.TARGET_AUTHORITY, header));
        }
        arrayList.add(new C3987Header(C3987Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                arrayList.add(new C3987Header(encodeUtf8, headers.value(i)));
            }
        }
        return arrayList;
    }

    public static Response.Builder readHttp2HeadersList(List<C3987Header> list) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int size = list.size();
        StatusLine statusLine = null;
        for (int i = 0; i < size; i++) {
            C3987Header header = list.get(i);
            if (header != null) {
                ByteString byteString = header.name;
                String utf8 = header.value.utf8();
                if (byteString.equals(C3987Header.RESPONSE_STATUS)) {
                    statusLine = StatusLine.parse("HTTP/1.1 " + utf8);
                } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(byteString)) {
                    Internal.instance.addLenient(builder, byteString.utf8(), utf8);
                }
            } else if (statusLine != null && statusLine.code == 100) {
                builder = new Headers.Builder();
                statusLine = null;
            }
        }
        if (statusLine != null) {
            return new Response.Builder().protocol(Protocol.HTTP_2).code(statusLine.code).message(statusLine.message).headers(builder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        return new RealResponseBody(response.header(HttpHeaders.CONTENT_TYPE), okhttp3.internal.http.HttpHeaders.contentLength(response), Okio.buffer((Source) new StreamFinishingSource(this.stream.getSource())));
    }

    public void cancel() {
        Http2Stream http2Stream = this.stream;
        if (http2Stream != null) {
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }

    class StreamFinishingSource extends ForwardingSource {
        long bytesRead = 0;
        boolean completed = false;

        StreamFinishingSource(Source source) {
            super(source);
        }

        public long read(Buffer buffer, long j) throws IOException {
            try {
                long read = delegate().read(buffer, j);
                if (read > 0) {
                    this.bytesRead += read;
                }
                return read;
            } catch (IOException e) {
                endOfInput(e);
                throw e;
            }
        }

        public void close() throws IOException {
            super.close();
            endOfInput((IOException) null);
        }

        private void endOfInput(IOException iOException) {
            if (!this.completed) {
                this.completed = true;
                Http2Codec.this.streamAllocation.streamFinished(false, Http2Codec.this, this.bytesRead, iOException);
            }
        }
    }
}
