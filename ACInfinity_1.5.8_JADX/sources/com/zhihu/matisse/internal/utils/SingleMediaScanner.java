package com.zhihu.matisse.internal.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
    private ScanListener mListener;
    private MediaScannerConnection mMsc;
    private String mPath;

    public interface ScanListener {
        void onScanFinish();
    }

    public SingleMediaScanner(Context context, String str, ScanListener scanListener) {
        this.mPath = str;
        this.mListener = scanListener;
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, this);
        this.mMsc = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    public void onMediaScannerConnected() {
        this.mMsc.scanFile(this.mPath, (String) null);
    }

    public void onScanCompleted(String str, Uri uri) {
        this.mMsc.disconnect();
        ScanListener scanListener = this.mListener;
        if (scanListener != null) {
            scanListener.onScanFinish();
        }
    }
}
