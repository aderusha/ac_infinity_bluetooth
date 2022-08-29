package com.eternal.framework.http.bean;

public class BaseData<T> {
    public static final String LOGOUT = "logout";
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String toString() {
        return "BaseData{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }
}
