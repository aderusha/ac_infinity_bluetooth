package androidx.core.view;

import android.os.Build;
import android.view.WindowInsetsAnimationController;
import androidx.core.graphics.Insets;

public final class WindowInsetsAnimationControllerCompat {
    private final Impl mImpl;

    WindowInsetsAnimationControllerCompat() {
        if (Build.VERSION.SDK_INT < 30) {
            this.mImpl = new Impl();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("On API 30+, the constructor taking a ");
        Class<WindowInsetsAnimationController> cls = WindowInsetsAnimationController.class;
        sb.append("WindowInsetsAnimationController");
        sb.append(" as parameter");
        throw new UnsupportedOperationException(sb.toString());
    }

    WindowInsetsAnimationControllerCompat(WindowInsetsAnimationController windowInsetsAnimationController) {
        this.mImpl = new Impl30(windowInsetsAnimationController);
    }

    public Insets getHiddenStateInsets() {
        return this.mImpl.getHiddenStateInsets();
    }

    public Insets getShownStateInsets() {
        return this.mImpl.getShownStateInsets();
    }

    public Insets getCurrentInsets() {
        return this.mImpl.getCurrentInsets();
    }

    public float getCurrentFraction() {
        return this.mImpl.getCurrentFraction();
    }

    public float getCurrentAlpha() {
        return this.mImpl.getCurrentAlpha();
    }

    public int getTypes() {
        return this.mImpl.getTypes();
    }

    public void setInsetsAndAlpha(Insets insets, float f, float f2) {
        this.mImpl.setInsetsAndAlpha(insets, f, f2);
    }

    public void finish(boolean z) {
        this.mImpl.finish(z);
    }

    public boolean isReady() {
        return !isFinished() && !isCancelled();
    }

    public boolean isFinished() {
        return this.mImpl.isFinished();
    }

    public boolean isCancelled() {
        return this.mImpl.isCancelled();
    }

    private static class Impl {
        /* access modifiers changed from: package-private */
        public void finish(boolean z) {
        }

        public float getCurrentAlpha() {
            return 0.0f;
        }

        public float getCurrentFraction() {
            return 0.0f;
        }

        public int getTypes() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isCancelled() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean isFinished() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public void setInsetsAndAlpha(Insets insets, float f, float f2) {
        }

        Impl() {
        }

        public Insets getHiddenStateInsets() {
            return Insets.NONE;
        }

        public Insets getShownStateInsets() {
            return Insets.NONE;
        }

        public Insets getCurrentInsets() {
            return Insets.NONE;
        }
    }

    private static class Impl30 extends Impl {
        private final WindowInsetsAnimationController mController;

        Impl30(WindowInsetsAnimationController windowInsetsAnimationController) {
            this.mController = windowInsetsAnimationController;
        }

        public Insets getHiddenStateInsets() {
            return Insets.toCompatInsets(this.mController.getHiddenStateInsets());
        }

        public Insets getShownStateInsets() {
            return Insets.toCompatInsets(this.mController.getShownStateInsets());
        }

        public Insets getCurrentInsets() {
            return Insets.toCompatInsets(this.mController.getCurrentInsets());
        }

        public float getCurrentFraction() {
            return this.mController.getCurrentFraction();
        }

        public float getCurrentAlpha() {
            return this.mController.getCurrentAlpha();
        }

        public int getTypes() {
            return this.mController.getTypes();
        }

        public void setInsetsAndAlpha(Insets insets, float f, float f2) {
            this.mController.setInsetsAndAlpha(insets == null ? null : insets.toPlatformInsets(), f, f2);
        }

        /* access modifiers changed from: package-private */
        public void finish(boolean z) {
            this.mController.finish(z);
        }

        public boolean isReady() {
            return this.mController.isReady();
        }

        /* access modifiers changed from: package-private */
        public boolean isFinished() {
            return this.mController.isFinished();
        }

        /* access modifiers changed from: package-private */
        public boolean isCancelled() {
            return this.mController.isCancelled();
        }
    }
}
