package com.zhihu.matisse.internal.p012ui;

import android.database.Cursor;
import android.os.Bundle;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.AlbumMediaCollection;
import com.zhihu.matisse.internal.p012ui.adapter.PreviewPagerAdapter;
import java.util.ArrayList;

/* renamed from: com.zhihu.matisse.internal.ui.AlbumPreviewActivity */
public class AlbumPreviewActivity extends BasePreviewActivity implements AlbumMediaCollection.AlbumMediaCallbacks {
    public static final String EXTRA_ALBUM = "extra_album";
    public static final String EXTRA_ITEM = "extra_item";
    private AlbumMediaCollection mCollection = new AlbumMediaCollection();
    private boolean mIsAlreadySetPosition;

    public void onAlbumMediaReset() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!SelectionSpec.getInstance().hasInited) {
            setResult(0);
            finish();
            return;
        }
        this.mCollection.onCreate(this, this);
        this.mCollection.load((Album) getIntent().getParcelableExtra("extra_album"));
        Item item = (Item) getIntent().getParcelableExtra(EXTRA_ITEM);
        if (this.mSpec.countable) {
            this.mCheckView.setCheckedNum(this.mSelectedCollection.checkedNumOf(item));
        } else {
            this.mCheckView.setChecked(this.mSelectedCollection.isSelected(item));
        }
        updateSize(item);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mCollection.onDestroy();
    }

    public void onAlbumMediaLoad(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(Item.valueOf(cursor));
        }
        if (!arrayList.isEmpty()) {
            PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.mPager.getAdapter();
            previewPagerAdapter.addAll(arrayList);
            previewPagerAdapter.notifyDataSetChanged();
            if (!this.mIsAlreadySetPosition) {
                this.mIsAlreadySetPosition = true;
                int indexOf = arrayList.indexOf((Item) getIntent().getParcelableExtra(EXTRA_ITEM));
                this.mPager.setCurrentItem(indexOf, false);
                this.mPreviousPos = indexOf;
            }
        }
    }
}
