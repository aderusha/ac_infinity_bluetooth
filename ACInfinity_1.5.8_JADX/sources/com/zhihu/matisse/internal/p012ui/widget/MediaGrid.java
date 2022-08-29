package com.zhihu.matisse.internal.p012ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;

/* renamed from: com.zhihu.matisse.internal.ui.widget.MediaGrid */
public class MediaGrid extends SquareFrameLayout implements View.OnClickListener {
    private CheckView mCheckView;
    private ImageView mGifTag;
    private OnMediaGridClickListener mListener;
    private Item mMedia;
    private PreBindInfo mPreBindInfo;
    private ImageView mThumbnail;
    private TextView mVideoDuration;

    /* renamed from: com.zhihu.matisse.internal.ui.widget.MediaGrid$OnMediaGridClickListener */
    public interface OnMediaGridClickListener {
        void onCheckViewClicked(CheckView checkView, Item item, RecyclerView.ViewHolder viewHolder);

        void onThumbnailClicked(ImageView imageView, Item item, RecyclerView.ViewHolder viewHolder);
    }

    public MediaGrid(Context context) {
        super(context);
        init(context);
    }

    public MediaGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(C3757R.layout.media_grid_content, this, true);
        this.mThumbnail = (ImageView) findViewById(C3757R.C3760id.media_thumbnail);
        this.mCheckView = (CheckView) findViewById(C3757R.C3760id.check_view);
        this.mGifTag = (ImageView) findViewById(C3757R.C3760id.gif);
        this.mVideoDuration = (TextView) findViewById(C3757R.C3760id.video_duration);
        this.mThumbnail.setOnClickListener(this);
        this.mCheckView.setOnClickListener(this);
    }

    public void onClick(View view) {
        OnMediaGridClickListener onMediaGridClickListener = this.mListener;
        if (onMediaGridClickListener != null) {
            ImageView imageView = this.mThumbnail;
            if (view == imageView) {
                onMediaGridClickListener.onThumbnailClicked(imageView, this.mMedia, this.mPreBindInfo.mViewHolder);
                return;
            }
            CheckView checkView = this.mCheckView;
            if (view == checkView) {
                onMediaGridClickListener.onCheckViewClicked(checkView, this.mMedia, this.mPreBindInfo.mViewHolder);
            }
        }
    }

    public void preBindMedia(PreBindInfo preBindInfo) {
        this.mPreBindInfo = preBindInfo;
    }

    public void bindMedia(Item item) {
        this.mMedia = item;
        setGifTag();
        initCheckView();
        setImage();
        setVideoDuration();
    }

    public Item getMedia() {
        return this.mMedia;
    }

    private void setGifTag() {
        this.mGifTag.setVisibility(this.mMedia.isGif() ? 0 : 8);
    }

    private void initCheckView() {
        this.mCheckView.setCountable(this.mPreBindInfo.mCheckViewCountable);
    }

    public void setCheckEnabled(boolean z) {
        this.mCheckView.setEnabled(z);
    }

    public void setCheckedNum(int i) {
        this.mCheckView.setCheckedNum(i);
    }

    public void setChecked(boolean z) {
        this.mCheckView.setChecked(z);
    }

    private void setImage() {
        if (this.mMedia.isGif()) {
            SelectionSpec.getInstance().imageEngine.loadGifThumbnail(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, this.mMedia.getContentUri());
        } else {
            SelectionSpec.getInstance().imageEngine.loadThumbnail(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, this.mMedia.getContentUri());
        }
    }

    private void setVideoDuration() {
        if (this.mMedia.isVideo()) {
            this.mVideoDuration.setVisibility(0);
            this.mVideoDuration.setText(DateUtils.formatElapsedTime(this.mMedia.duration / 1000));
            return;
        }
        this.mVideoDuration.setVisibility(8);
    }

    public void setOnMediaGridClickListener(OnMediaGridClickListener onMediaGridClickListener) {
        this.mListener = onMediaGridClickListener;
    }

    public void removeOnMediaGridClickListener() {
        this.mListener = null;
    }

    /* renamed from: com.zhihu.matisse.internal.ui.widget.MediaGrid$PreBindInfo */
    public static class PreBindInfo {
        boolean mCheckViewCountable;
        Drawable mPlaceholder;
        int mResize;
        RecyclerView.ViewHolder mViewHolder;

        public PreBindInfo(int i, Drawable drawable, boolean z, RecyclerView.ViewHolder viewHolder) {
            this.mResize = i;
            this.mPlaceholder = drawable;
            this.mCheckViewCountable = z;
            this.mViewHolder = viewHolder;
        }
    }
}
