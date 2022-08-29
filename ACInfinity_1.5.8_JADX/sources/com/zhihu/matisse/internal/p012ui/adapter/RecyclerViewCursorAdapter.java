package com.zhihu.matisse.internal.p012ui.adapter;

import android.database.Cursor;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/* renamed from: com.zhihu.matisse.internal.ui.adapter.RecyclerViewCursorAdapter */
public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private Cursor mCursor;
    private int mRowIDColumn;

    /* access modifiers changed from: protected */
    public abstract int getItemViewType(int i, Cursor cursor);

    /* access modifiers changed from: protected */
    public abstract void onBindViewHolder(VH vh, Cursor cursor);

    RecyclerViewCursorAdapter(Cursor cursor) {
        setHasStableIds(true);
        swapCursor(cursor);
    }

    public void onBindViewHolder(VH vh, int i) {
        if (!isDataValid(this.mCursor)) {
            throw new IllegalStateException("Cannot bind view holder when cursor is in invalid state.");
        } else if (this.mCursor.moveToPosition(i)) {
            onBindViewHolder(vh, this.mCursor);
        } else {
            throw new IllegalStateException("Could not move cursor to position " + i + " when trying to bind view holder");
        }
    }

    public int getItemViewType(int i) {
        if (this.mCursor.moveToPosition(i)) {
            return getItemViewType(i, this.mCursor);
        }
        throw new IllegalStateException("Could not move cursor to position " + i + " when trying to get item view type.");
    }

    public int getItemCount() {
        if (isDataValid(this.mCursor)) {
            return this.mCursor.getCount();
        }
        return 0;
    }

    public long getItemId(int i) {
        if (!isDataValid(this.mCursor)) {
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        } else if (this.mCursor.moveToPosition(i)) {
            return this.mCursor.getLong(this.mRowIDColumn);
        } else {
            throw new IllegalStateException("Could not move cursor to position " + i + " when trying to get an item id");
        }
    }

    public void swapCursor(Cursor cursor) {
        if (cursor != this.mCursor) {
            if (cursor != null) {
                this.mCursor = cursor;
                this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
                notifyDataSetChanged();
                return;
            }
            notifyItemRangeRemoved(0, getItemCount());
            this.mCursor = null;
            this.mRowIDColumn = -1;
        }
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    private boolean isDataValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }
}
