package com.eternal.framework.http.exception;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import java.io.IOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import retrofit2.HttpException;

public class ApiException extends Exception {
    private final int code;
    private String message;

    public static class ERROR {
        public static final int CAST_ERROR = 1004;
        public static final int ILLEGAL_STATE_ERROR = 1006;
        public static final int NULL_POINTER_EXCEPTION = 1002;
        public static final int PARSE_ERROR = 1005;
        public static final int SSL_ERROR = 1003;
        public static final int TIMEOUT_ERROR = 1001;
        public static final int UNKNOWN = 1000;
    }

    public ApiException(Throwable th, int i) {
        super(th);
        this.code = i;
        this.message = th.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static ApiException handleException(Throwable th) {
        if (th instanceof HttpException) {
            HttpException httpException = (HttpException) th;
            ApiException apiException = new ApiException(httpException, httpException.code());
            apiException.message = httpException.getMessage();
            return apiException;
        } else if (th instanceof SocketTimeoutException) {
            ApiException apiException2 = new ApiException(th, 1001);
            apiException2.message = "Check your WiFi settings and try again.";
            return apiException2;
        } else if (th instanceof IOException) {
            ApiException apiException3 = new ApiException(th, 1001);
            apiException3.message = "Check your WiFi settings and try again.";
            return apiException3;
        } else if (th instanceof ConnectException) {
            ApiException apiException4 = new ApiException(th, 1001);
            apiException4.message = "Check your WiFi settings and try again.";
            return apiException4;
        } else if (th instanceof ConnectTimeoutException) {
            ApiException apiException5 = new ApiException(th, 1001);
            apiException5.message = "Check your WiFi settings and try again.";
            return apiException5;
        } else if (th instanceof UnknownHostException) {
            ApiException apiException6 = new ApiException(th, 1001);
            apiException6.message = "Check your WiFi settings and try again.";
            return apiException6;
        } else if (th instanceof NullPointerException) {
            ApiException apiException7 = new ApiException(th, 1002);
            apiException7.message = "空指针异常";
            return apiException7;
        } else if (th instanceof SSLHandshakeException) {
            ApiException apiException8 = new ApiException(th, 1003);
            apiException8.message = "证书验证失败";
            return apiException8;
        } else if (th instanceof ClassCastException) {
            ApiException apiException9 = new ApiException(th, 1004);
            apiException9.message = "类型转换错误";
            return apiException9;
        } else if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof JsonSerializer) || (th instanceof NotSerializableException) || (th instanceof ParseException)) {
            ApiException apiException10 = new ApiException(th, ERROR.PARSE_ERROR);
            apiException10.message = "解析错误";
            return apiException10;
        } else if (!(th instanceof IllegalStateException)) {
            return new ApiException(th, 1000);
        } else {
            ApiException apiException11 = new ApiException(th, 1006);
            apiException11.message = th.getMessage();
            return apiException11;
        }
    }
}
