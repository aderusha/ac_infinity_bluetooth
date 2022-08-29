package com.google.gson;

public enum LongSerializationPolicy {
    DEFAULT {
        public JsonElement serialize(Long l) {
            if (l == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive((Number) l);
        }
    },
    STRING {
        public JsonElement serialize(Long l) {
            if (l == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(l.toString());
        }
    };

    public abstract JsonElement serialize(Long l);
}
