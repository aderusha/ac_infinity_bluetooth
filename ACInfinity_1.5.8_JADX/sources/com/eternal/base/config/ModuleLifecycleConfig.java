package com.eternal.base.config;

import android.app.Application;
import com.eternal.base.IModuleInit;

public class ModuleLifecycleConfig {
    public static void initAhead(Application application) {
        for (String cls : ModuleLifecycleReflexes.MODULES_INIT) {
            try {
                ((IModuleInit) Class.forName(cls).newInstance()).initAhead(application);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initLow(Application application) {
        for (String cls : ModuleLifecycleReflexes.MODULES_INIT) {
            try {
                ((IModuleInit) Class.forName(cls).newInstance()).initLow(application);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
