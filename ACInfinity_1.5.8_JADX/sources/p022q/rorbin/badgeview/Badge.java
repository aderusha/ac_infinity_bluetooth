package p022q.rorbin.badgeview;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* renamed from: q.rorbin.badgeview.Badge */
public interface Badge {

    /* renamed from: q.rorbin.badgeview.Badge$OnDragStateChangedListener */
    public interface OnDragStateChangedListener {
        public static final int STATE_CANCELED = 4;
        public static final int STATE_DRAGGING = 2;
        public static final int STATE_DRAGGING_OUT_OF_RANGE = 3;
        public static final int STATE_START = 1;
        public static final int STATE_SUCCEED = 5;

        void onDragStateChanged(int i, Badge badge, View view);
    }

    Badge bindTarget(View view);

    Drawable getBadgeBackground();

    int getBadgeBackgroundColor();

    int getBadgeGravity();

    int getBadgeNumber();

    float getBadgePadding(boolean z);

    String getBadgeText();

    int getBadgeTextColor();

    float getBadgeTextSize(boolean z);

    PointF getDragCenter();

    float getGravityOffsetX(boolean z);

    float getGravityOffsetY(boolean z);

    View getTargetView();

    void hide(boolean z);

    boolean isDraggable();

    boolean isExactMode();

    boolean isShowShadow();

    Badge setBadgeBackground(Drawable drawable);

    Badge setBadgeBackground(Drawable drawable, boolean z);

    Badge setBadgeBackgroundColor(int i);

    Badge setBadgeGravity(int i);

    Badge setBadgeNumber(int i);

    Badge setBadgePadding(float f, boolean z);

    Badge setBadgeText(String str);

    Badge setBadgeTextColor(int i);

    Badge setBadgeTextSize(float f, boolean z);

    Badge setExactMode(boolean z);

    Badge setGravityOffset(float f, float f2, boolean z);

    Badge setGravityOffset(float f, boolean z);

    Badge setOnDragStateChangedListener(OnDragStateChangedListener onDragStateChangedListener);

    Badge setShowShadow(boolean z);

    Badge stroke(int i, float f, boolean z);
}
