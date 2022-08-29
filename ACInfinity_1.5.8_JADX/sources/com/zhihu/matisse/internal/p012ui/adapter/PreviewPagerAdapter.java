package com.zhihu.matisse.internal.p012ui.adapter;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.p012ui.PreviewItemFragment;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.zhihu.matisse.internal.ui.adapter.PreviewPagerAdapter */
public class PreviewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Item> mItems = new ArrayList<>();
    private OnPrimaryItemSetListener mListener;

    /* renamed from: com.zhihu.matisse.internal.ui.adapter.PreviewPagerAdapter$OnPrimaryItemSetListener */
    interface OnPrimaryItemSetListener {
        void onPrimaryItemSet(int i);
    }

    public PreviewPagerAdapter(FragmentManager fragmentManager, OnPrimaryItemSetListener onPrimaryItemSetListener) {
        super(fragmentManager);
        this.mListener = onPrimaryItemSetListener;
    }

    public Fragment getItem(int i) {
        return PreviewItemFragment.newInstance(this.mItems.get(i));
    }

    public int getCount() {
        return this.mItems.size();
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        OnPrimaryItemSetListener onPrimaryItemSetListener = this.mListener;
        if (onPrimaryItemSetListener != null) {
            onPrimaryItemSetListener.onPrimaryItemSet(i);
        }
    }

    public Item getMediaItem(int i) {
        return this.mItems.get(i);
    }

    public void addAll(List<Item> list) {
        this.mItems.addAll(list);
    }
}
