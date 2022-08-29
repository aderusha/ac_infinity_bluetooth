package com.zhihu.matisse.internal.p012ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.p012ui.widget.CheckView;
import com.zhihu.matisse.internal.p012ui.widget.MediaGrid;

/* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter */
public class AlbumMediaAdapter extends RecyclerViewCursorAdapter<RecyclerView.ViewHolder> implements MediaGrid.OnMediaGridClickListener {
    private static final int VIEW_TYPE_CAPTURE = 1;
    private static final int VIEW_TYPE_MEDIA = 2;
    private CheckStateListener mCheckStateListener;
    private int mImageResize;
    private OnMediaClickListener mOnMediaClickListener;
    private final Drawable mPlaceholder;
    private RecyclerView mRecyclerView;
    private final SelectedItemCollection mSelectedCollection;
    private SelectionSpec mSelectionSpec = SelectionSpec.getInstance();

    /* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter$CheckStateListener */
    public interface CheckStateListener {
        void onUpdate();
    }

    /* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter$OnMediaClickListener */
    public interface OnMediaClickListener {
        void onMediaClick(Album album, Item item, int i);
    }

    /* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter$OnPhotoCapture */
    public interface OnPhotoCapture {
        void capture();
    }

    public AlbumMediaAdapter(Context context, SelectedItemCollection selectedItemCollection, RecyclerView recyclerView) {
        super((Cursor) null);
        this.mSelectedCollection = selectedItemCollection;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{C3757R.attr.item_placeholder});
        this.mPlaceholder = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        this.mRecyclerView = recyclerView;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            CaptureViewHolder captureViewHolder = new CaptureViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C3757R.layout.photo_capture_item, viewGroup, false));
            captureViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (view.getContext() instanceof OnPhotoCapture) {
                        ((OnPhotoCapture) view.getContext()).capture();
                    }
                }
            });
            return captureViewHolder;
        } else if (i == 2) {
            return new MediaViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C3757R.layout.media_grid_item, viewGroup, false));
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        Drawable.ConstantState constantState;
        if (viewHolder instanceof CaptureViewHolder) {
            CaptureViewHolder captureViewHolder = (CaptureViewHolder) viewHolder;
            Drawable[] compoundDrawables = captureViewHolder.mHint.getCompoundDrawables();
            TypedArray obtainStyledAttributes = viewHolder.itemView.getContext().getTheme().obtainStyledAttributes(new int[]{C3757R.attr.capture_textColor});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            for (int i = 0; i < compoundDrawables.length; i++) {
                Drawable drawable = compoundDrawables[i];
                if (!(drawable == null || (constantState = drawable.getConstantState()) == null)) {
                    Drawable mutate = constantState.newDrawable().mutate();
                    mutate.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    mutate.setBounds(drawable.getBounds());
                    compoundDrawables[i] = mutate;
                }
            }
            captureViewHolder.mHint.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        } else if (viewHolder instanceof MediaViewHolder) {
            MediaViewHolder mediaViewHolder = (MediaViewHolder) viewHolder;
            Item valueOf = Item.valueOf(cursor);
            mediaViewHolder.mMediaGrid.preBindMedia(new MediaGrid.PreBindInfo(getImageResize(mediaViewHolder.mMediaGrid.getContext()), this.mPlaceholder, this.mSelectionSpec.countable, viewHolder));
            mediaViewHolder.mMediaGrid.bindMedia(valueOf);
            mediaViewHolder.mMediaGrid.setOnMediaGridClickListener(this);
            setCheckStatus(valueOf, mediaViewHolder.mMediaGrid);
        }
    }

    private void setCheckStatus(Item item, MediaGrid mediaGrid) {
        if (this.mSelectionSpec.countable) {
            int checkedNumOf = this.mSelectedCollection.checkedNumOf(item);
            if (checkedNumOf > 0) {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(checkedNumOf);
            } else if (this.mSelectedCollection.maxSelectableReached()) {
                mediaGrid.setCheckEnabled(false);
                mediaGrid.setCheckedNum(Integer.MIN_VALUE);
            } else {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(checkedNumOf);
            }
        } else if (this.mSelectedCollection.isSelected(item)) {
            mediaGrid.setCheckEnabled(true);
            mediaGrid.setChecked(true);
        } else if (this.mSelectedCollection.maxSelectableReached()) {
            mediaGrid.setCheckEnabled(false);
            mediaGrid.setChecked(false);
        } else {
            mediaGrid.setCheckEnabled(true);
            mediaGrid.setChecked(false);
        }
    }

    public void onThumbnailClicked(ImageView imageView, Item item, RecyclerView.ViewHolder viewHolder) {
        if (this.mSelectionSpec.showPreview) {
            OnMediaClickListener onMediaClickListener = this.mOnMediaClickListener;
            if (onMediaClickListener != null) {
                onMediaClickListener.onMediaClick((Album) null, item, viewHolder.getAdapterPosition());
                return;
            }
            return;
        }
        updateSelectedItem(item, viewHolder);
    }

    public void onCheckViewClicked(CheckView checkView, Item item, RecyclerView.ViewHolder viewHolder) {
        updateSelectedItem(item, viewHolder);
    }

    private void updateSelectedItem(Item item, RecyclerView.ViewHolder viewHolder) {
        if (this.mSelectionSpec.countable) {
            if (this.mSelectedCollection.checkedNumOf(item) != Integer.MIN_VALUE) {
                this.mSelectedCollection.remove(item);
                notifyCheckStateChanged();
            } else if (assertAddSelection(viewHolder.itemView.getContext(), item)) {
                this.mSelectedCollection.add(item);
                notifyCheckStateChanged();
            }
        } else if (this.mSelectedCollection.isSelected(item)) {
            this.mSelectedCollection.remove(item);
            notifyCheckStateChanged();
        } else if (assertAddSelection(viewHolder.itemView.getContext(), item)) {
            this.mSelectedCollection.add(item);
            notifyCheckStateChanged();
        }
    }

    private void notifyCheckStateChanged() {
        notifyDataSetChanged();
        CheckStateListener checkStateListener = this.mCheckStateListener;
        if (checkStateListener != null) {
            checkStateListener.onUpdate();
        }
    }

    public int getItemViewType(int i, Cursor cursor) {
        return Item.valueOf(cursor).isCapture() ? 1 : 2;
    }

    private boolean assertAddSelection(Context context, Item item) {
        IncapableCause isAcceptable = this.mSelectedCollection.isAcceptable(item);
        IncapableCause.handleCause(context, isAcceptable);
        return isAcceptable == null;
    }

    public void registerCheckStateListener(CheckStateListener checkStateListener) {
        this.mCheckStateListener = checkStateListener;
    }

    public void unregisterCheckStateListener() {
        this.mCheckStateListener = null;
    }

    public void registerOnMediaClickListener(OnMediaClickListener onMediaClickListener) {
        this.mOnMediaClickListener = onMediaClickListener;
    }

    public void unregisterOnMediaClickListener() {
        this.mOnMediaClickListener = null;
    }

    public void refreshSelection() {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) this.mRecyclerView.getLayoutManager();
        int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition != -1 && findLastVisibleItemPosition != -1) {
            Cursor cursor = getCursor();
            for (int i = findFirstVisibleItemPosition; i <= findLastVisibleItemPosition; i++) {
                RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                if ((findViewHolderForAdapterPosition instanceof MediaViewHolder) && cursor.moveToPosition(i)) {
                    setCheckStatus(Item.valueOf(cursor), ((MediaViewHolder) findViewHolderForAdapterPosition).mMediaGrid);
                }
            }
        }
    }

    private int getImageResize(Context context) {
        if (this.mImageResize == 0) {
            int spanCount = ((GridLayoutManager) this.mRecyclerView.getLayoutManager()).getSpanCount();
            int dimensionPixelSize = (context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(C3757R.dimen.media_grid_spacing) * (spanCount - 1))) / spanCount;
            this.mImageResize = dimensionPixelSize;
            this.mImageResize = (int) (((float) dimensionPixelSize) * this.mSelectionSpec.thumbnailScale);
        }
        return this.mImageResize;
    }

    /* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter$MediaViewHolder */
    private static class MediaViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public MediaGrid mMediaGrid;

        MediaViewHolder(View view) {
            super(view);
            this.mMediaGrid = (MediaGrid) view;
        }
    }

    /* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter$CaptureViewHolder */
    private static class CaptureViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView mHint;

        CaptureViewHolder(View view) {
            super(view);
            this.mHint = (TextView) view.findViewById(C3757R.C3760id.hint);
        }
    }
}
