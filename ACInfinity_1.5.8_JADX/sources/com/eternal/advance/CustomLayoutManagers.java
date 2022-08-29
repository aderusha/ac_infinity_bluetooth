package com.eternal.advance;

import androidx.recyclerview.widget.RecyclerView;
import p018me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class CustomLayoutManagers implements LayoutManagers.LayoutManagerFactory {
    public static CustomLayoutManagers linear() {
        return new CustomLayoutManagers() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new CustomLinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
        return new CustomLinearLayoutManager(recyclerView.getContext());
    }
}
