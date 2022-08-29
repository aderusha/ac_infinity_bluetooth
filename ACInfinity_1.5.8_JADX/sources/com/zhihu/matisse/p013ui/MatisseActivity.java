package com.zhihu.matisse.p013ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.AlbumCollection;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.p012ui.AlbumPreviewActivity;
import com.zhihu.matisse.internal.p012ui.BasePreviewActivity;
import com.zhihu.matisse.internal.p012ui.MediaSelectionFragment;
import com.zhihu.matisse.internal.p012ui.SelectedPreviewActivity;
import com.zhihu.matisse.internal.p012ui.adapter.AlbumMediaAdapter;
import com.zhihu.matisse.internal.p012ui.adapter.AlbumsAdapter;
import com.zhihu.matisse.internal.p012ui.widget.AlbumsSpinner;
import com.zhihu.matisse.internal.p012ui.widget.CheckRadioView;
import com.zhihu.matisse.internal.p012ui.widget.IncapableDialog;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;
import com.zhihu.matisse.internal.utils.PathUtils;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import com.zhihu.matisse.internal.utils.SingleMediaScanner;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.zhihu.matisse.ui.MatisseActivity */
public class MatisseActivity extends AppCompatActivity implements AlbumCollection.AlbumCallbacks, AdapterView.OnItemSelectedListener, MediaSelectionFragment.SelectionProvider, View.OnClickListener, AlbumMediaAdapter.CheckStateListener, AlbumMediaAdapter.OnMediaClickListener, AlbumMediaAdapter.OnPhotoCapture {
    public static final String CHECK_STATE = "checkState";
    public static final String EXTRA_RESULT_ORIGINAL_ENABLE = "extra_result_original_enable";
    public static final String EXTRA_RESULT_SELECTION = "extra_result_selection";
    public static final String EXTRA_RESULT_SELECTION_PATH = "extra_result_selection_path";
    private static final int REQUEST_CODE_CAPTURE = 24;
    private static final int REQUEST_CODE_PREVIEW = 23;
    /* access modifiers changed from: private */
    public final AlbumCollection mAlbumCollection = new AlbumCollection();
    private AlbumsAdapter mAlbumsAdapter;
    /* access modifiers changed from: private */
    public AlbumsSpinner mAlbumsSpinner;
    private TextView mButtonApply;
    private TextView mButtonPreview;
    private View mContainer;
    private View mEmptyView;
    private MediaStoreCompat mMediaStoreCompat;
    private CheckRadioView mOriginal;
    private boolean mOriginalEnable;
    private LinearLayout mOriginalLayout;
    private SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    private SelectionSpec mSpec;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        SelectionSpec instance = SelectionSpec.getInstance();
        this.mSpec = instance;
        setTheme(instance.themeId);
        super.onCreate(bundle);
        if (!this.mSpec.hasInited) {
            setResult(0);
            finish();
            return;
        }
        setContentView(C3757R.layout.activity_matisse);
        if (this.mSpec.needOrientationRestriction()) {
            setRequestedOrientation(this.mSpec.orientation);
        }
        if (this.mSpec.capture) {
            this.mMediaStoreCompat = new MediaStoreCompat(this);
            if (this.mSpec.captureStrategy != null) {
                this.mMediaStoreCompat.setCaptureStrategy(this.mSpec.captureStrategy);
            } else {
                throw new RuntimeException("Don't forget to set CaptureStrategy.");
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(C3757R.C3760id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        Drawable navigationIcon = toolbar.getNavigationIcon();
        TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{C3757R.attr.album_element_color});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        navigationIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        this.mButtonPreview = (TextView) findViewById(C3757R.C3760id.button_preview);
        this.mButtonApply = (TextView) findViewById(C3757R.C3760id.button_apply);
        this.mButtonPreview.setOnClickListener(this);
        this.mButtonApply.setOnClickListener(this);
        this.mContainer = findViewById(C3757R.C3760id.container);
        this.mEmptyView = findViewById(C3757R.C3760id.empty_view);
        this.mOriginalLayout = (LinearLayout) findViewById(C3757R.C3760id.originalLayout);
        this.mOriginal = (CheckRadioView) findViewById(C3757R.C3760id.original);
        this.mOriginalLayout.setOnClickListener(this);
        this.mSelectedCollection.onCreate(bundle);
        if (bundle != null) {
            this.mOriginalEnable = bundle.getBoolean("checkState");
        }
        updateBottomToolbar();
        this.mAlbumsAdapter = new AlbumsAdapter((Context) this, (Cursor) null, false);
        AlbumsSpinner albumsSpinner = new AlbumsSpinner(this);
        this.mAlbumsSpinner = albumsSpinner;
        albumsSpinner.setOnItemSelectedListener(this);
        this.mAlbumsSpinner.setSelectedTextView((TextView) findViewById(C3757R.C3760id.selected_album));
        this.mAlbumsSpinner.setPopupAnchorView(findViewById(C3757R.C3760id.toolbar));
        this.mAlbumsSpinner.setAdapter(this.mAlbumsAdapter);
        this.mAlbumCollection.onCreate(this, this);
        this.mAlbumCollection.onRestoreInstanceState(bundle);
        this.mAlbumCollection.loadAlbums();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mSelectedCollection.onSaveInstanceState(bundle);
        this.mAlbumCollection.onSaveInstanceState(bundle);
        bundle.putBoolean("checkState", this.mOriginalEnable);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mAlbumCollection.onDestroy();
        this.mSpec.onCheckedListener = null;
        this.mSpec.onSelectedListener = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 23) {
                Bundle bundleExtra = intent.getBundleExtra(BasePreviewActivity.EXTRA_RESULT_BUNDLE);
                ArrayList parcelableArrayList = bundleExtra.getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
                this.mOriginalEnable = intent.getBooleanExtra("extra_result_original_enable", false);
                int i3 = bundleExtra.getInt(SelectedItemCollection.STATE_COLLECTION_TYPE, 0);
                if (intent.getBooleanExtra(BasePreviewActivity.EXTRA_RESULT_APPLY, false)) {
                    Intent intent2 = new Intent();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    if (parcelableArrayList != null) {
                        Iterator it = parcelableArrayList.iterator();
                        while (it.hasNext()) {
                            Item item = (Item) it.next();
                            arrayList.add(item.getContentUri());
                            arrayList2.add(PathUtils.getPath(this, item.getContentUri()));
                        }
                    }
                    intent2.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList);
                    intent2.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, arrayList2);
                    intent2.putExtra("extra_result_original_enable", this.mOriginalEnable);
                    setResult(-1, intent2);
                    finish();
                    return;
                }
                this.mSelectedCollection.overwrite(parcelableArrayList, i3);
                Class<MediaSelectionFragment> cls = MediaSelectionFragment.class;
                Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("MediaSelectionFragment");
                if (findFragmentByTag instanceof MediaSelectionFragment) {
                    ((MediaSelectionFragment) findFragmentByTag).refreshMediaGrid();
                }
                updateBottomToolbar();
            } else if (i == 24) {
                Uri currentPhotoUri = this.mMediaStoreCompat.getCurrentPhotoUri();
                String currentPhotoPath = this.mMediaStoreCompat.getCurrentPhotoPath();
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(currentPhotoUri);
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(currentPhotoPath);
                Intent intent3 = new Intent();
                intent3.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList3);
                intent3.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, arrayList4);
                setResult(-1, intent3);
                if (Build.VERSION.SDK_INT < 21) {
                    revokeUriPermission(currentPhotoUri, 3);
                }
                new SingleMediaScanner(getApplicationContext(), currentPhotoPath, new SingleMediaScanner.ScanListener() {
                    public void onScanFinish() {
                        Log.i("SingleMediaScanner", "scan finish!");
                    }
                });
                finish();
            }
        }
    }

    private void updateBottomToolbar() {
        int count = this.mSelectedCollection.count();
        if (count == 0) {
            this.mButtonPreview.setEnabled(false);
            this.mButtonApply.setEnabled(false);
            this.mButtonApply.setText(getString(C3757R.string.button_apply_default));
        } else if (count != 1 || !this.mSpec.singleSelectionModeEnabled()) {
            this.mButtonPreview.setEnabled(true);
            this.mButtonApply.setEnabled(true);
            this.mButtonApply.setText(getString(C3757R.string.button_apply, new Object[]{Integer.valueOf(count)}));
        } else {
            this.mButtonPreview.setEnabled(true);
            this.mButtonApply.setText(C3757R.string.button_apply_default);
            this.mButtonApply.setEnabled(true);
        }
        if (this.mSpec.originalable) {
            this.mOriginalLayout.setVisibility(0);
            updateOriginalState();
            return;
        }
        this.mOriginalLayout.setVisibility(4);
    }

    private void updateOriginalState() {
        this.mOriginal.setChecked(this.mOriginalEnable);
        if (countOverMaxSize() > 0 && this.mOriginalEnable) {
            IncapableDialog.newInstance("", getString(C3757R.string.error_over_original_size, new Object[]{Integer.valueOf(this.mSpec.originalMaxSize)})).show(getSupportFragmentManager(), IncapableDialog.class.getName());
            this.mOriginal.setChecked(false);
            this.mOriginalEnable = false;
        }
    }

    private int countOverMaxSize() {
        int count = this.mSelectedCollection.count();
        int i = 0;
        for (int i2 = 0; i2 < count; i2++) {
            Item item = this.mSelectedCollection.asList().get(i2);
            if (item.isImage() && PhotoMetadataUtils.getSizeInMB(item.size) > ((float) this.mSpec.originalMaxSize)) {
                i++;
            }
        }
        return i;
    }

    public void onClick(View view) {
        if (view.getId() == C3757R.C3760id.button_preview) {
            Intent intent = new Intent(this, SelectedPreviewActivity.class);
            intent.putExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
            intent.putExtra("extra_result_original_enable", this.mOriginalEnable);
            startActivityForResult(intent, 23);
        } else if (view.getId() == C3757R.C3760id.button_apply) {
            Intent intent2 = new Intent();
            intent2.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, (ArrayList) this.mSelectedCollection.asListOfUri());
            intent2.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, (ArrayList) this.mSelectedCollection.asListOfString());
            intent2.putExtra("extra_result_original_enable", this.mOriginalEnable);
            setResult(-1, intent2);
            finish();
        } else if (view.getId() == C3757R.C3760id.originalLayout) {
            int countOverMaxSize = countOverMaxSize();
            if (countOverMaxSize > 0) {
                IncapableDialog.newInstance("", getString(C3757R.string.error_over_original_count, new Object[]{Integer.valueOf(countOverMaxSize), Integer.valueOf(this.mSpec.originalMaxSize)})).show(getSupportFragmentManager(), IncapableDialog.class.getName());
                return;
            }
            boolean z = !this.mOriginalEnable;
            this.mOriginalEnable = z;
            this.mOriginal.setChecked(z);
            if (this.mSpec.onCheckedListener != null) {
                this.mSpec.onCheckedListener.onCheck(this.mOriginalEnable);
            }
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.mAlbumCollection.setStateCurrentSelection(i);
        this.mAlbumsAdapter.getCursor().moveToPosition(i);
        Album valueOf = Album.valueOf(this.mAlbumsAdapter.getCursor());
        if (valueOf.isAll() && SelectionSpec.getInstance().capture) {
            valueOf.addCaptureCount();
        }
        onAlbumSelected(valueOf);
    }

    public void onAlbumLoad(final Cursor cursor) {
        this.mAlbumsAdapter.swapCursor(cursor);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                cursor.moveToPosition(MatisseActivity.this.mAlbumCollection.getCurrentSelection());
                AlbumsSpinner access$100 = MatisseActivity.this.mAlbumsSpinner;
                MatisseActivity matisseActivity = MatisseActivity.this;
                access$100.setSelection(matisseActivity, matisseActivity.mAlbumCollection.getCurrentSelection());
                Album valueOf = Album.valueOf(cursor);
                if (valueOf.isAll() && SelectionSpec.getInstance().capture) {
                    valueOf.addCaptureCount();
                }
                MatisseActivity.this.onAlbumSelected(valueOf);
            }
        });
    }

    public void onAlbumReset() {
        this.mAlbumsAdapter.swapCursor((Cursor) null);
    }

    /* access modifiers changed from: private */
    public void onAlbumSelected(Album album) {
        if (!album.isAll() || !album.isEmpty()) {
            this.mContainer.setVisibility(0);
            this.mEmptyView.setVisibility(8);
            Class<MediaSelectionFragment> cls = MediaSelectionFragment.class;
            getSupportFragmentManager().beginTransaction().replace(C3757R.C3760id.container, (Fragment) MediaSelectionFragment.newInstance(album), "MediaSelectionFragment").commitAllowingStateLoss();
            return;
        }
        this.mContainer.setVisibility(8);
        this.mEmptyView.setVisibility(0);
    }

    public void onUpdate() {
        updateBottomToolbar();
        if (this.mSpec.onSelectedListener != null) {
            this.mSpec.onSelectedListener.onSelected(this.mSelectedCollection.asListOfUri(), this.mSelectedCollection.asListOfString());
        }
    }

    public void onMediaClick(Album album, Item item, int i) {
        Intent intent = new Intent(this, AlbumPreviewActivity.class);
        intent.putExtra("extra_album", album);
        intent.putExtra(AlbumPreviewActivity.EXTRA_ITEM, item);
        intent.putExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
        intent.putExtra("extra_result_original_enable", this.mOriginalEnable);
        startActivityForResult(intent, 23);
    }

    public SelectedItemCollection provideSelectedItemCollection() {
        return this.mSelectedCollection;
    }

    public void capture() {
        MediaStoreCompat mediaStoreCompat = this.mMediaStoreCompat;
        if (mediaStoreCompat != null) {
            mediaStoreCompat.dispatchCaptureIntent(this, 24);
        }
    }
}
