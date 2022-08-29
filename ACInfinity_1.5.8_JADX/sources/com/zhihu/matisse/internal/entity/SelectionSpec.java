package com.zhihu.matisse.internal.entity;

import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.ImageEngine;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;
import java.util.List;
import java.util.Set;

public final class SelectionSpec {
    public boolean autoHideToobar;
    public boolean capture;
    public CaptureStrategy captureStrategy;
    public boolean countable;
    public List<Filter> filters;
    public int gridExpectedSize;
    public boolean hasInited;
    public ImageEngine imageEngine;
    public int maxImageSelectable;
    public int maxSelectable;
    public int maxVideoSelectable;
    public boolean mediaTypeExclusive;
    public Set<MimeType> mimeTypeSet;
    public OnCheckedListener onCheckedListener;
    public OnSelectedListener onSelectedListener;
    public int orientation;
    public int originalMaxSize;
    public boolean originalable;
    public boolean showPreview;
    public boolean showSingleMediaType;
    public int spanCount;
    public int themeId;
    public float thumbnailScale;

    private SelectionSpec() {
    }

    public static SelectionSpec getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static SelectionSpec getCleanInstance() {
        SelectionSpec instance = getInstance();
        instance.reset();
        return instance;
    }

    private void reset() {
        this.mimeTypeSet = null;
        this.mediaTypeExclusive = true;
        this.showSingleMediaType = false;
        this.themeId = C3757R.style.Matisse_Zhihu;
        this.orientation = 0;
        this.countable = false;
        this.maxSelectable = 1;
        this.maxImageSelectable = 0;
        this.maxVideoSelectable = 0;
        this.filters = null;
        this.capture = false;
        this.captureStrategy = null;
        this.spanCount = 3;
        this.gridExpectedSize = 0;
        this.thumbnailScale = 0.5f;
        this.imageEngine = new GlideEngine();
        this.hasInited = true;
        this.originalable = false;
        this.autoHideToobar = false;
        this.originalMaxSize = Integer.MAX_VALUE;
        this.showPreview = true;
    }

    public boolean singleSelectionModeEnabled() {
        if (!this.countable) {
            if (this.maxSelectable != 1) {
                return this.maxImageSelectable == 1 && this.maxVideoSelectable == 1;
            }
            return true;
        }
    }

    public boolean needOrientationRestriction() {
        return this.orientation != -1;
    }

    public boolean onlyShowImages() {
        return this.showSingleMediaType && MimeType.ofImage().containsAll(this.mimeTypeSet);
    }

    public boolean onlyShowVideos() {
        return this.showSingleMediaType && MimeType.ofVideo().containsAll(this.mimeTypeSet);
    }

    public boolean onlyShowGif() {
        return this.showSingleMediaType && MimeType.ofGif().equals(this.mimeTypeSet);
    }

    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final SelectionSpec INSTANCE = new SelectionSpec();

        private InstanceHolder() {
        }
    }
}
