package com.eternal.framework.component;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.eternal.framework.C2171R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import java.lang.ref.WeakReference;

public class ContainerActivity extends RxAppCompatActivity {
    public static final String BUNDLE = "bundle";
    public static final String FRAGMENT = "fragment";
    private static final String FRAGMENT_TAG = "content_fragment_tag";
    protected WeakReference<Fragment> mFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        getWindow().setSoftInputMode(32);
        super.onCreate(bundle);
        setContentView(C2171R.layout.activity_container);
        Fragment fragment = bundle != null ? getSupportFragmentManager().getFragment(bundle, FRAGMENT_TAG) : null;
        if (fragment == null) {
            fragment = initFromIntent(getIntent());
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(C2171R.C2174id.content, fragment);
        beginTransaction.commitAllowingStateLoss();
        this.mFragment = new WeakReference<>(fragment);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getSupportFragmentManager().putFragment(bundle, FRAGMENT_TAG, (Fragment) this.mFragment.get());
    }

    /* access modifiers changed from: protected */
    public Fragment initFromIntent(Intent intent) {
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra(FRAGMENT);
                if (stringExtra == null || "".equals(stringExtra)) {
                    throw new IllegalArgumentException("can not find page fragmentName");
                }
                Fragment fragment = (Fragment) Class.forName(stringExtra).newInstance();
                Bundle bundleExtra = intent.getBundleExtra(BUNDLE);
                if (bundleExtra != null) {
                    fragment.setArguments(bundleExtra);
                }
                return fragment;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("fragment initialization failed!");
            } catch (InstantiationException e2) {
                e2.printStackTrace();
                throw new RuntimeException("fragment initialization failed!");
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
                throw new RuntimeException("fragment initialization failed!");
            }
        } else {
            throw new RuntimeException("you must provide a page info to display");
        }
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(C2171R.C2174id.content);
        if (!(findFragmentById instanceof BaseRxFragment)) {
            super.onBackPressed();
        } else if (!((BaseRxFragment) findFragmentById).isBackPressed()) {
            super.onBackPressed();
        }
    }
}
