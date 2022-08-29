package com.eternal.framework.http.interceptor;

import android.util.Log;
import com.eternal.export.CSVUtil;
import com.eternal.framework.http.utils.JsonUtil;
import okhttp3.logging.HttpLoggingInterceptor;

public class RxHttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuffer mMessage = new StringBuffer();

    public void log(String str) {
        if (str.startsWith("--> POST")) {
            this.mMessage.setLength(0);
            this.mMessage.append(" ");
            this.mMessage.append(CSVUtil.ROW_SEPARATOR);
        }
        if (str.startsWith("--> GET")) {
            this.mMessage.setLength(0);
            this.mMessage.append(" ");
            this.mMessage.append(CSVUtil.ROW_SEPARATOR);
        }
        if ((str.startsWith("{") && str.endsWith("}")) || (str.startsWith("[") && str.endsWith("]"))) {
            str = JsonUtil.formatJson(str);
        }
        this.mMessage.append(str.concat("\n"));
        if (str.startsWith("<-- END HTTP")) {
            Log.e("RxHttpUtils", this.mMessage.toString());
        }
    }
}
