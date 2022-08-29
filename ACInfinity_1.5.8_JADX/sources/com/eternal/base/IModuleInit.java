package com.eternal.base;

import android.app.Application;

public interface IModuleInit {
    boolean initAhead(Application application);

    boolean initLow(Application application);
}
