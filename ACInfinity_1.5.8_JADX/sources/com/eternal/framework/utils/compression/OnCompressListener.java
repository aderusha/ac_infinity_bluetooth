package com.eternal.framework.utils.compression;

import java.io.File;

public interface OnCompressListener {
    void onError(Throwable th);

    void onStart();

    void onSuccess(File file);
}
