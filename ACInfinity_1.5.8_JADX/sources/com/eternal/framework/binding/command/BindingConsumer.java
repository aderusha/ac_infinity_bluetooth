package com.eternal.framework.binding.command;

public interface BindingConsumer<T> {
    void call(T t);
}
