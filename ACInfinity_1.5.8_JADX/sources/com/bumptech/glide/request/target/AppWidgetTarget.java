package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final ComponentName componentName;
    private final Context context;
    private final RemoteViews remoteViews;
    private final int viewId;
    private final int[] widgetIds;

    public AppWidgetTarget(Context context2, int i, int i2, int i3, RemoteViews remoteViews2, int... iArr) {
        super(i, i2);
        if (iArr.length != 0) {
            this.context = (Context) Preconditions.checkNotNull(context2, "Context can not be null!");
            this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
            this.widgetIds = (int[]) Preconditions.checkNotNull(iArr, "WidgetIds can not be null!");
            this.viewId = i3;
            this.componentName = null;
            return;
        }
        throw new IllegalArgumentException("WidgetIds must have length > 0");
    }

    public AppWidgetTarget(Context context2, int i, RemoteViews remoteViews2, int... iArr) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews2, iArr);
    }

    public AppWidgetTarget(Context context2, int i, int i2, int i3, RemoteViews remoteViews2, ComponentName componentName2) {
        super(i, i2);
        this.context = (Context) Preconditions.checkNotNull(context2, "Context can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
        this.componentName = (ComponentName) Preconditions.checkNotNull(componentName2, "ComponentName can not be null!");
        this.viewId = i3;
        this.widgetIds = null;
    }

    public AppWidgetTarget(Context context2, int i, RemoteViews remoteViews2, ComponentName componentName2) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews2, componentName2);
    }

    private void update() {
        AppWidgetManager instance = AppWidgetManager.getInstance(this.context);
        ComponentName componentName2 = this.componentName;
        if (componentName2 != null) {
            instance.updateAppWidget(componentName2, this.remoteViews);
        } else {
            instance.updateAppWidget(this.widgetIds, this.remoteViews);
        }
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
