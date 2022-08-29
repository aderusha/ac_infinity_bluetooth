package com.bumptech.glide.manager;

class ApplicationLifecycle implements Lifecycle {
    public void removeListener(LifecycleListener lifecycleListener) {
    }

    ApplicationLifecycle() {
    }

    public void addListener(LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
