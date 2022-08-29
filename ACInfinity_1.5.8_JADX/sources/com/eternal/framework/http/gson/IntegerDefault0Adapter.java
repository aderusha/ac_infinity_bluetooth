package com.eternal.framework.http.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|(2:5|6)|7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer deserialize(com.google.gson.JsonElement r1, java.lang.reflect.Type r2, com.google.gson.JsonDeserializationContext r3) throws com.google.gson.JsonParseException {
        /*
            r0 = this;
            java.lang.String r2 = r1.getAsString()     // Catch:{ Exception -> 0x001e }
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x001e }
            if (r2 != 0) goto L_0x0018
            java.lang.String r2 = r1.getAsString()     // Catch:{ Exception -> 0x001e }
            java.lang.String r3 = "null"
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x001e }
            if (r2 == 0) goto L_0x001e
        L_0x0018:
            r2 = 0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x001e }
            return r1
        L_0x001e:
            int r1 = r1.getAsInt()     // Catch:{ NumberFormatException -> 0x0027 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x0027 }
            return r1
        L_0x0027:
            r1 = move-exception
            com.google.gson.JsonSyntaxException r2 = new com.google.gson.JsonSyntaxException
            r2.<init>((java.lang.Throwable) r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.http.gson.IntegerDefault0Adapter.deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext):java.lang.Integer");
    }

    public JsonElement serialize(Integer num, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive((Number) num);
    }
}
