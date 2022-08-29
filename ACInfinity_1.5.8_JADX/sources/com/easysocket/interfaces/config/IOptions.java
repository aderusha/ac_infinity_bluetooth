package com.easysocket.interfaces.config;

import com.easysocket.config.EasySocketOptions;

public interface IOptions<T> {
    EasySocketOptions getOptions();

    T setOptions(EasySocketOptions easySocketOptions);
}
