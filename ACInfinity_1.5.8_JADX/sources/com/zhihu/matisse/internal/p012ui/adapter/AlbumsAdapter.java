package com.zhihu.matisse.internal.p012ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.SelectionSpec;

/* renamed from: com.zhihu.matisse.internal.ui.adapter.AlbumsAdapter */
public class AlbumsAdapter extends CursorAdapter {
    private final Drawable mPlaceholder;

    public AlbumsAdapter(Context context, Cursor cursor, boolean z) {
        super(context, cursor, z);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{C3757R.attr.album_thumbnail_placeholder});
        this.mPlaceholder = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public AlbumsAdapter(Context context, Cursor cursor, int i) {
        super(context, cursor, i);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{C3757R.attr.album_thumbnail_placeholder});
        this.mPlaceholder = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(C3757R.layout.album_list_item, viewGroup, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        Album valueOf = Album.valueOf(cursor);
        ((TextView) view.findViewById(C3757R.C3760id.album_name)).setText(valueOf.getDisplayName(context));
        ((TextView) view.findViewById(C3757R.C3760id.album_media_count)).setText(String.valueOf(valueOf.getCount()));
        SelectionSpec.getInstance().imageEngine.loadThumbnail(context, context.getResources().getDimensionPixelSize(C3757R.dimen.media_grid_size), this.mPlaceholder, (ImageView) view.findViewById(C3757R.C3760id.album_cover), valueOf.getCoverUri());
    }
}
