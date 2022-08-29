package com.eternal.framework.binding.viewadapter.webview;

import android.text.TextUtils;
import android.webkit.WebView;
import com.bumptech.glide.load.Key;

public class ViewAdapter {
    public static void loadHtml(WebView webView, String str) {
        if (!TextUtils.isEmpty(str)) {
            webView.loadDataWithBaseURL((String) null, str, "text/html", Key.STRING_CHARSET_NAME, (String) null);
        }
    }
}
