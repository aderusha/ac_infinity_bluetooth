package com.zhihu.matisse.internal.p012ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.AlbumMediaCollection;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.p012ui.adapter.AlbumMediaAdapter;
import com.zhihu.matisse.internal.p012ui.widget.MediaGridInset;
import com.zhihu.matisse.internal.utils.UIUtils;

/* renamed from: com.zhihu.matisse.internal.ui.MediaSelectionFragment */
public class MediaSelectionFragment extends Fragment implements AlbumMediaCollection.AlbumMediaCallbacks, AlbumMediaAdapter.CheckStateListener, AlbumMediaAdapter.OnMediaClickListener {
    public static final String EXTRA_ALBUM = "extra_album";
    private AlbumMediaAdapter mAdapter;
    private final AlbumMediaCollection mAlbumMediaCollection = new AlbumMediaCollection();
    private AlbumMediaAdapter.CheckStateListener mCheckStateListener;
    private AlbumMediaAdapter.OnMediaClickListener mOnMediaClickListener;
    private RecyclerView mRecyclerView;
    private SelectionProvider mSelectionProvider;

    /* renamed from: com.zhihu.matisse.internal.ui.MediaSelectionFragment$SelectionProvider */
    public interface SelectionProvider {
        SelectedItemCollection provideSelectedItemCollection();
    }

    public static MediaSelectionFragment newInstance(Album album) {
        MediaSelectionFragment mediaSelectionFragment = new MediaSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_album", album);
        mediaSelectionFragment.setArguments(bundle);
        return mediaSelectionFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectionProvider) {
            this.mSelectionProvider = (SelectionProvider) context;
            if (context instanceof AlbumMediaAdapter.CheckStateListener) {
                this.mCheckStateListener = (AlbumMediaAdapter.CheckStateListener) context;
            }
            if (context instanceof AlbumMediaAdapter.OnMediaClickListener) {
                this.mOnMediaClickListener = (AlbumMediaAdapter.OnMediaClickListener) context;
                return;
            }
            return;
        }
        throw new IllegalStateException("Context must implement SelectionProvider.");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C3757R.layout.fragment_media_selection, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (RecyclerView) view.findViewById(C3757R.C3760id.recyclerview);
    }

    public void onActivityCreated(Bundle bundle) {
        int i;
        super.onActivityCreated(bundle);
        Album album = (Album) getArguments().getParcelable("extra_album");
        AlbumMediaAdapter albumMediaAdapter = new AlbumMediaAdapter(getContext(), this.mSelectionProvider.provideSelectedItemCollection(), this.mRecyclerView);
        this.mAdapter = albumMediaAdapter;
        albumMediaAdapter.registerCheckStateListener(this);
        this.mAdapter.registerOnMediaClickListener(this);
        this.mRecyclerView.setHasFixedSize(true);
        SelectionSpec instance = SelectionSpec.getInstance();
        if (instance.gridExpectedSize > 0) {
            i = UIUtils.spanCount(getContext(), instance.gridExpectedSize);
        } else {
            i = instance.spanCount;
        }
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), i));
        this.mRecyclerView.addItemDecoration(new MediaGridInset(i, getResources().getDimensionPixelSize(C3757R.dimen.media_grid_spacing), false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAlbumMediaCollection.onCreate(getActivity(), this);
        this.mAlbumMediaCollection.load(album, instance.capture);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mAlbumMediaCollection.onDestroy();
    }

    public void refreshMediaGrid() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void refreshSelection() {
        this.mAdapter.refreshSelection();
    }

    public void onAlbumMediaLoad(Cursor cursor) {
        this.mAdapter.swapCursor(cursor);
    }

    public void onAlbumMediaReset() {
        this.mAdapter.swapCursor((Cursor) null);
    }

    public void onUpdate() {
        AlbumMediaAdapter.CheckStateListener checkStateListener = this.mCheckStateListener;
        if (checkStateListener != null) {
            checkStateListener.onUpdate();
        }
    }

    public void onMediaClick(Album album, Item item, int i) {
        AlbumMediaAdapter.OnMediaClickListener onMediaClickListener = this.mOnMediaClickListener;
        if (onMediaClickListener != null) {
            onMediaClickListener.onMediaClick((Album) getArguments().getParcelable("extra_album"), item, i);
        }
    }
}
