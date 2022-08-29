package com.google.common.reflect;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public interface TypeToInstanceMap<B> extends Map<TypeToken<? extends B>, B> {
    @NullableDecl
    <T extends B> T getInstance(TypeToken<T> typeToken);

    @NullableDecl
    <T extends B> T getInstance(Class<T> cls);

    @NullableDecl
    <T extends B> T putInstance(TypeToken<T> typeToken, @NullableDecl T t);

    @NullableDecl
    <T extends B> T putInstance(Class<T> cls, @NullableDecl T t);
}
