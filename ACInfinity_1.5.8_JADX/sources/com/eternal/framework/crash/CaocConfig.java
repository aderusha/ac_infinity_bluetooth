package com.eternal.framework.crash;

import android.app.Activity;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.eternal.framework.crash.CustomActivityOnCrash;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Modifier;

public class CaocConfig implements Serializable {
    public static final int BACKGROUND_MODE_CRASH = 2;
    public static final int BACKGROUND_MODE_SHOW_CUSTOM = 1;
    public static final int BACKGROUND_MODE_SILENT = 0;
    /* access modifiers changed from: private */
    public int backgroundMode = 1;
    /* access modifiers changed from: private */
    public boolean enabled = true;
    /* access modifiers changed from: private */
    public Class<? extends Activity> errorActivityClass = null;
    /* access modifiers changed from: private */
    public Integer errorDrawable = null;
    /* access modifiers changed from: private */
    public CustomActivityOnCrash.EventListener eventListener = null;
    /* access modifiers changed from: private */
    public int minTimeBetweenCrashesMs = PathInterpolatorCompat.MAX_NUM_POINTS;
    /* access modifiers changed from: private */
    public Class<? extends Activity> restartActivityClass = null;
    /* access modifiers changed from: private */
    public boolean showErrorDetails = true;
    /* access modifiers changed from: private */
    public boolean showRestartButton = true;
    /* access modifiers changed from: private */
    public boolean trackActivities = false;

    @Retention(RetentionPolicy.SOURCE)
    private @interface BackgroundMode {
    }

    public int getBackgroundMode() {
        return this.backgroundMode;
    }

    public void setBackgroundMode(int i) {
        this.backgroundMode = i;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public boolean isShowErrorDetails() {
        return this.showErrorDetails;
    }

    public void setShowErrorDetails(boolean z) {
        this.showErrorDetails = z;
    }

    public boolean isShowRestartButton() {
        return this.showRestartButton;
    }

    public void setShowRestartButton(boolean z) {
        this.showRestartButton = z;
    }

    public boolean isTrackActivities() {
        return this.trackActivities;
    }

    public void setTrackActivities(boolean z) {
        this.trackActivities = z;
    }

    public int getMinTimeBetweenCrashesMs() {
        return this.minTimeBetweenCrashesMs;
    }

    public void setMinTimeBetweenCrashesMs(int i) {
        this.minTimeBetweenCrashesMs = i;
    }

    public Integer getErrorDrawable() {
        return this.errorDrawable;
    }

    public void setErrorDrawable(Integer num) {
        this.errorDrawable = num;
    }

    public Class<? extends Activity> getErrorActivityClass() {
        return this.errorActivityClass;
    }

    public void setErrorActivityClass(Class<? extends Activity> cls) {
        this.errorActivityClass = cls;
    }

    public Class<? extends Activity> getRestartActivityClass() {
        return this.restartActivityClass;
    }

    public void setRestartActivityClass(Class<? extends Activity> cls) {
        this.restartActivityClass = cls;
    }

    public CustomActivityOnCrash.EventListener getEventListener() {
        return this.eventListener;
    }

    public void setEventListener(CustomActivityOnCrash.EventListener eventListener2) {
        this.eventListener = eventListener2;
    }

    public static class Builder {
        private CaocConfig config;

        public static Builder create() {
            Builder builder = new Builder();
            CaocConfig config2 = CustomActivityOnCrash.getConfig();
            CaocConfig caocConfig = new CaocConfig();
            int unused = caocConfig.backgroundMode = config2.backgroundMode;
            boolean unused2 = caocConfig.enabled = config2.enabled;
            boolean unused3 = caocConfig.showErrorDetails = config2.showErrorDetails;
            boolean unused4 = caocConfig.showRestartButton = config2.showRestartButton;
            boolean unused5 = caocConfig.trackActivities = config2.trackActivities;
            int unused6 = caocConfig.minTimeBetweenCrashesMs = config2.minTimeBetweenCrashesMs;
            Integer unused7 = caocConfig.errorDrawable = config2.errorDrawable;
            Class unused8 = caocConfig.errorActivityClass = config2.errorActivityClass;
            Class unused9 = caocConfig.restartActivityClass = config2.restartActivityClass;
            CustomActivityOnCrash.EventListener unused10 = caocConfig.eventListener = config2.eventListener;
            builder.config = caocConfig;
            return builder;
        }

        public Builder backgroundMode(int i) {
            int unused = this.config.backgroundMode = i;
            return this;
        }

        public Builder enabled(boolean z) {
            boolean unused = this.config.enabled = z;
            return this;
        }

        public Builder showErrorDetails(boolean z) {
            boolean unused = this.config.showErrorDetails = z;
            return this;
        }

        public Builder showRestartButton(boolean z) {
            boolean unused = this.config.showRestartButton = z;
            return this;
        }

        public Builder trackActivities(boolean z) {
            boolean unused = this.config.trackActivities = z;
            return this;
        }

        public Builder minTimeBetweenCrashesMs(int i) {
            int unused = this.config.minTimeBetweenCrashesMs = i;
            return this;
        }

        public Builder errorDrawable(Integer num) {
            Integer unused = this.config.errorDrawable = num;
            return this;
        }

        public Builder errorActivity(Class<? extends Activity> cls) {
            Class unused = this.config.errorActivityClass = cls;
            return this;
        }

        public Builder restartActivity(Class<? extends Activity> cls) {
            Class unused = this.config.restartActivityClass = cls;
            return this;
        }

        public Builder eventListener(CustomActivityOnCrash.EventListener eventListener) {
            if (eventListener == null || eventListener.getClass().getEnclosingClass() == null || Modifier.isStatic(eventListener.getClass().getModifiers())) {
                CustomActivityOnCrash.EventListener unused = this.config.eventListener = eventListener;
                return this;
            }
            throw new IllegalArgumentException("The event listener cannot be an inner or anonymous class, because it will need to be serialized. Change it to a class of its own, or make it a static inner class.");
        }

        public CaocConfig get() {
            return this.config;
        }

        public void apply() {
            CustomActivityOnCrash.setConfig(this.config);
        }
    }
}
