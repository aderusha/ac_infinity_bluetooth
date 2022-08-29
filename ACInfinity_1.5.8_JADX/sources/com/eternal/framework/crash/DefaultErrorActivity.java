package com.eternal.framework.crash;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.eternal.framework.C2171R;

public final class DefaultErrorActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedArray obtainStyledAttributes = obtainStyledAttributes(C2171R.styleable.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(C2171R.styleable.AppCompatTheme_windowActionBar)) {
            setTheme(C2171R.style.Theme_AppCompat_Light_DarkActionBar);
        }
        obtainStyledAttributes.recycle();
        setContentView(C2171R.layout.customactivityoncrash_default_error_activity);
        Button button = (Button) findViewById(C2171R.C2174id.customactivityoncrash_error_activity_restart_button);
        final CaocConfig configFromIntent = CustomActivityOnCrash.getConfigFromIntent(getIntent());
        if (!configFromIntent.isShowRestartButton() || configFromIntent.getRestartActivityClass() == null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CustomActivityOnCrash.closeApplication(DefaultErrorActivity.this, configFromIntent);
                }
            });
        } else {
            button.setText(C2171R.string.customactivityoncrash_error_activity_restart_app);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CustomActivityOnCrash.restartApplication(DefaultErrorActivity.this, configFromIntent);
                }
            });
        }
        Button button2 = (Button) findViewById(C2171R.C2174id.customactivityoncrash_error_activity_more_info_button);
        if (configFromIntent.isShowErrorDetails()) {
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlertDialog.Builder title = new AlertDialog.Builder(DefaultErrorActivity.this).setTitle(C2171R.string.customactivityoncrash_error_activity_error_details_title);
                    DefaultErrorActivity defaultErrorActivity = DefaultErrorActivity.this;
                    ((TextView) title.setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(defaultErrorActivity, defaultErrorActivity.getIntent())).setPositiveButton(C2171R.string.customactivityoncrash_error_activity_error_details_close, (DialogInterface.OnClickListener) null).setNeutralButton(C2171R.string.customactivityoncrash_error_activity_error_details_copy, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DefaultErrorActivity.this.copyErrorToClipboard();
                            Toast.makeText(DefaultErrorActivity.this, C2171R.string.customactivityoncrash_error_activity_error_details_copied, 0).show();
                        }
                    }).show().findViewById(16908299)).setTextSize(0, DefaultErrorActivity.this.getResources().getDimension(C2171R.dimen.customactivityoncrash_error_activity_error_details_text_size));
                }
            });
        } else {
            button2.setVisibility(8);
        }
        Integer errorDrawable = configFromIntent.getErrorDrawable();
        ImageView imageView = (ImageView) findViewById(C2171R.C2174id.customactivityoncrash_error_activity_image);
        if (errorDrawable != null) {
            imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), errorDrawable.intValue(), getTheme()));
        }
    }

    /* access modifiers changed from: private */
    public void copyErrorToClipboard() {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(getString(C2171R.string.f182x949651f5), CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent())));
    }
}
