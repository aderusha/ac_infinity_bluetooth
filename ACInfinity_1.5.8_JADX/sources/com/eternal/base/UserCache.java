package com.eternal.base;

import android.text.TextUtils;
import com.eternal.framework.utils.SPUtils;

public class UserCache {
    private static UserCache instance = new UserCache();
    private final String KEY_BINDING_DEVICE = "kBindingD";
    private final String KEY_EMAIL = "kEmail";
    private final String KEY_TOKEN = "kToken";
    private int bindingD;
    private String email;
    private String token;

    public static UserCache getInstance() {
        return instance;
    }

    public String getToken() {
        if (TextUtils.isEmpty(this.token)) {
            this.token = SPUtils.getInstance().getString("kToken", "");
        }
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
        SPUtils.getInstance().put("kToken", str);
    }

    public String getEmail() {
        if (TextUtils.isEmpty(this.email)) {
            this.email = SPUtils.getInstance().getString("kEmail", "");
        }
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
        SPUtils.getInstance().put("kEmail", str);
    }

    public void setBingD(int i) {
        this.bindingD = i;
        SPUtils.getInstance().put("kBindingD", i);
    }

    public int getBindingD() {
        int i = SPUtils.getInstance().getInt("kBindingD", 0);
        this.bindingD = i;
        return i;
    }
}
