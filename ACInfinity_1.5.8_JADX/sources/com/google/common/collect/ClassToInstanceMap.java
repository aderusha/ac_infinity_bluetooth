package com.google.common.collect;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public interface ClassToInstanceMap<B> extends Map<Class<? extends B>, B> {
    <T extends B> T getInstance(Class<T> cls);

    <T extends B> T putInstance(Class<T> cls, @NullableDecl T t);
}
