package com.zhihu.matisse.internal.p012ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import com.zhihu.matisse.listener.OnFragmentInteractionListener;
import p015it.sephiroth.android.library.imagezoom.ImageViewTouch;
import p015it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/* renamed from: com.zhihu.matisse.internal.ui.PreviewItemFragment */
public class PreviewItemFragment extends Fragment {
    private static final String ARGS_ITEM = "args_item";
    /* access modifiers changed from: private */
    public OnFragmentInteractionListener mListener;

    public static PreviewItemFragment newInstance(Item item) {
        PreviewItemFragment previewItemFragment = new PreviewItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ITEM, item);
        previewItemFragment.setArguments(bundle);
        return previewItemFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C3757R.layout.fragment_preview_item, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final Item item = (Item) getArguments().getParcelable(ARGS_ITEM);
        if (item != null) {
            View findViewById = view.findViewById(C3757R.C3760id.video_play_button);
            if (item.isVideo()) {
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setDataAndType(item.uri, "video/*");
                        try {
                            PreviewItemFragment.this.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            Toast.makeText(PreviewItemFragment.this.getContext(), C3757R.string.error_no_video_activity, 0).show();
                        }
                    }
                });
            } else {
                findViewById.setVisibility(8);
            }
            ImageViewTouch imageViewTouch = (ImageViewTouch) view.findViewById(C3757R.C3760id.image_view);
            imageViewTouch.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
            imageViewTouch.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener() {
                public void onSingleTapConfirmed() {
                    if (PreviewItemFragment.this.mListener != null) {
                        PreviewItemFragment.this.mListener.onClick();
                    }
                }
            });
            Point bitmapSize = PhotoMetadataUtils.getBitmapSize(item.getContentUri(), getActivity());
            if (item.isGif()) {
                SelectionSpec.getInstance().imageEngine.loadGifImage(getContext(), bitmapSize.x, bitmapSize.y, imageViewTouch, item.getContentUri());
            } else {
                SelectionSpec.getInstance().imageEngine.loadImage(getContext(), bitmapSize.x, bitmapSize.y, imageViewTouch, item.getContentUri());
            }
        }
    }

    public void resetView() {
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(C3757R.C3760id.image_view)).resetMatrix();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
