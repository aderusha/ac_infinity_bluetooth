package com.easysocket.interfaces.callback;

import java.lang.reflect.Type;

public interface IType<T> {
    Class<?> getGenericityClazz();

    Type getType();
}
