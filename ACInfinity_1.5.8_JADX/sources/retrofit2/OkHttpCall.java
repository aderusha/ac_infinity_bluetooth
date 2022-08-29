package retrofit2;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

final class OkHttpCall<T> implements Call<T> {
    @Nullable
    private final Object[] args;
    private volatile boolean canceled;
    @Nullable
    private Throwable creationFailure;
    private boolean executed;
    @Nullable
    private Call rawCall;
    private final ServiceMethod<T, ?> serviceMethod;

    OkHttpCall(ServiceMethod<T, ?> serviceMethod2, @Nullable Object[] objArr) {
        this.serviceMethod = serviceMethod2;
        this.args = objArr;
    }

    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.serviceMethod, this.args);
    }

    public synchronized Request request() {
        Call call = this.rawCall;
        if (call != null) {
            return call.request();
        }
        Throwable th = this.creationFailure;
        if (th == null) {
            try {
                Call createRawCall = createRawCall();
                this.rawCall = createRawCall;
                return createRawCall.request();
            } catch (RuntimeException e) {
                e = e;
                Utils.throwIfFatal(e);
                this.creationFailure = e;
                throw e;
            } catch (Error e2) {
                e = e2;
                Utils.throwIfFatal(e);
                this.creationFailure = e;
                throw e;
            } catch (IOException e3) {
                this.creationFailure = e3;
                throw new RuntimeException("Unable to create request.", e3);
            }
        } else if (th instanceof IOException) {
            throw new RuntimeException("Unable to create request.", this.creationFailure);
        } else if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else {
            throw ((Error) th);
        }
    }

    public void enqueue(final Callback<T> callback) {
        Call call;
        Throwable th;
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                call = this.rawCall;
                th = this.creationFailure;
                if (call == null && th == null) {
                    try {
                        Call createRawCall = createRawCall();
                        this.rawCall = createRawCall;
                        call = createRawCall;
                    } catch (Throwable th2) {
                        th = th2;
                        Utils.throwIfFatal(th);
                        this.creationFailure = th;
                    }
                }
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            call.cancel();
        }
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                try {
                    try {
                        callback.onResponse(OkHttpCall.this, OkHttpCall.this.parseResponse(response));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                } catch (Throwable th2) {
                    callFailure(th2);
                }
            }

            public void onFailure(Call call, IOException iOException) {
                callFailure(iOException);
            }

            private void callFailure(Throwable th) {
                try {
                    callback.onFailure(OkHttpCall.this, th);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        });
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public Response<T> execute() throws IOException {
        Call call;
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Throwable th = this.creationFailure;
                if (th == null) {
                    call = this.rawCall;
                    if (call == null) {
                        try {
                            call = createRawCall();
                            this.rawCall = call;
                        } catch (IOException | Error | RuntimeException e) {
                            Utils.throwIfFatal(e);
                            this.creationFailure = e;
                            throw e;
                        }
                    }
                } else if (th instanceof IOException) {
                    throw ((IOException) th);
                } else if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                } else {
                    throw ((Error) th);
                }
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (this.canceled) {
            call.cancel();
        }
        return parseResponse(call.execute());
    }

    private Call createRawCall() throws IOException {
        Call call = this.serviceMethod.toCall(this.args);
        Objects.requireNonNull(call, "Call.Factory returned null.");
        return call;
    }

    /* access modifiers changed from: package-private */
    public Response<T> parseResponse(Response response) throws IOException {
        ResponseBody body = response.body();
        Response build = response.newBuilder().body(new NoContentResponseBody(body.contentType(), body.contentLength())).build();
        int code = build.code();
        if (code < 200 || code >= 300) {
            try {
                return Response.error(Utils.buffer(body), build);
            } finally {
                body.close();
            }
        } else if (code == 204 || code == 205) {
            body.close();
            return Response.success(null, build);
        } else {
            ExceptionCatchingRequestBody exceptionCatchingRequestBody = new ExceptionCatchingRequestBody(body);
            try {
                return Response.success(this.serviceMethod.toResponse(exceptionCatchingRequestBody), build);
            } catch (RuntimeException e) {
                exceptionCatchingRequestBody.throwIfCaught();
                throw e;
            }
        }
    }

    public void cancel() {
        Call call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            Call call = this.rawCall;
            if (call == null || !call.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;
        private final MediaType contentType;

        NoContentResponseBody(MediaType mediaType, long j) {
            this.contentType = mediaType;
            this.contentLength = j;
        }

        public MediaType contentType() {
            return this.contentType;
        }

        public long contentLength() {
            return this.contentLength;
        }

        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    static final class ExceptionCatchingRequestBody extends ResponseBody {
        private final ResponseBody delegate;
        IOException thrownException;

        ExceptionCatchingRequestBody(ResponseBody responseBody) {
            this.delegate = responseBody;
        }

        public MediaType contentType() {
            return this.delegate.contentType();
        }

        public long contentLength() {
            return this.delegate.contentLength();
        }

        public BufferedSource source() {
            return Okio.buffer((Source) new ForwardingSource(this.delegate.source()) {
                public long read(Buffer buffer, long j) throws IOException {
                    try {
                        return super.read(buffer, j);
                    } catch (IOException e) {
                        ExceptionCatchingRequestBody.this.thrownException = e;
                        throw e;
                    }
                }
            });
        }

        public void close() {
            this.delegate.close();
        }

        /* access modifiers changed from: package-private */
        public void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }
}
