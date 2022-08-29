package com.zhihu.matisse.internal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import androidx.core.p003os.EnvironmentCompat;
import androidx.fragment.app.Fragment;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MediaStoreCompat {
    private CaptureStrategy mCaptureStrategy;
    private final WeakReference<Activity> mContext;
    private String mCurrentPhotoPath;
    private Uri mCurrentPhotoUri;
    private final WeakReference<Fragment> mFragment;

    public MediaStoreCompat(Activity activity) {
        this.mContext = new WeakReference<>(activity);
        this.mFragment = null;
    }

    public MediaStoreCompat(Activity activity, Fragment fragment) {
        this.mContext = new WeakReference<>(activity);
        this.mFragment = new WeakReference<>(fragment);
    }

    public static boolean hasCameraFeature(Context context) {
        return context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public void setCaptureStrategy(CaptureStrategy captureStrategy) {
        this.mCaptureStrategy = captureStrategy;
    }

    public void dispatchCaptureIntent(Context context, int i) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            File file = null;
            try {
                file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file != null) {
                this.mCurrentPhotoPath = file.getAbsolutePath();
                Uri uriForFile = FileProvider.getUriForFile((Context) this.mContext.get(), this.mCaptureStrategy.authority, file);
                this.mCurrentPhotoUri = uriForFile;
                intent.putExtra("output", uriForFile);
                intent.addFlags(2);
                if (Build.VERSION.SDK_INT < 21) {
                    for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
                        context.grantUriPermission(resolveInfo.activityInfo.packageName, this.mCurrentPhotoUri, 3);
                    }
                }
                WeakReference<Fragment> weakReference = this.mFragment;
                if (weakReference != null) {
                    ((Fragment) weakReference.get()).startActivityForResult(intent, i);
                } else {
                    ((Activity) this.mContext.get()).startActivityForResult(intent, i);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        File file;
        String format = String.format("JPEG_%s.jpg", new Object[]{new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date())});
        if (this.mCaptureStrategy.isPublic) {
            file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            file = ((Activity) this.mContext.get()).getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        if (this.mCaptureStrategy.directory != null) {
            File file2 = new File(file, this.mCaptureStrategy.directory);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            file = file2;
        }
        File file3 = new File(file, format);
        if (!"mounted".equals(EnvironmentCompat.getStorageState(file3))) {
            return null;
        }
        return file3;
    }

    public Uri getCurrentPhotoUri() {
        return this.mCurrentPhotoUri;
    }

    public String getCurrentPhotoPath() {
        return this.mCurrentPhotoPath;
    }
}
