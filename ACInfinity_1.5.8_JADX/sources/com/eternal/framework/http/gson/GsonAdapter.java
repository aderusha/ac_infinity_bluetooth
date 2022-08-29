package com.eternal.framework.http.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonAdapter {
    public static Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter()).registerTypeAdapter(Integer.TYPE, new IntegerDefault0Adapter()).registerTypeAdapter(Double.class, new DoubleDefault0Adapter()).registerTypeAdapter(Double.TYPE, new DoubleDefault0Adapter()).registerTypeAdapter(Long.class, new LongDefault0Adapter()).registerTypeAdapter(Long.TYPE, new LongDefault0Adapter()).create();
    }
}
