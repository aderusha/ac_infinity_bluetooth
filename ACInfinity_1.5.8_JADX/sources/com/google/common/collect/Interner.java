package com.google.common.collect;

public interface Interner<E> {
    E intern(E e);
}
