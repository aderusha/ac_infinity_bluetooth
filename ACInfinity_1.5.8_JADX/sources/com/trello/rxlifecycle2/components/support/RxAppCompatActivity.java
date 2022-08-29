package com.trello.rxlifecycle2.components.support;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import p014io.reactivex.Observable;
import p014io.reactivex.subjects.BehaviorSubject;

public abstract class RxAppCompatActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    public final Observable<ActivityEvent> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    public final <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent activityEvent) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, activityEvent);
    }

    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(this.lifecycleSubject);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.lifecycleSubject.onNext(ActivityEvent.START);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}
