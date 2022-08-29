package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.IEffectBar;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;

public class SingleAddView extends ConstraintLayout implements IEffectBar {
    private Disposable addSubs;
    private CompoundButton.OnCheckedChangeListener checkedChangeListener;
    /* access modifiers changed from: private */
    public int distance;
    private IEffectBar.Factory factory;
    /* access modifiers changed from: private */
    public ImageView imgAdd;
    /* access modifiers changed from: private */
    public ImageView imgMinus;
    private int interval;
    private boolean isMax;
    /* access modifiers changed from: private */
    public IEffectBar.Listener listener;
    /* access modifiers changed from: private */
    public int min;
    private Disposable minusSubs;
    /* access modifiers changed from: private */
    public int now;
    private TextView progress;

    /* renamed from: s */
    private Switch f249s;
    private TextView subtitle;
    private TextView title;

    public float getTx() {
        return 0.0f;
    }

    public void setFillWhenEqual(boolean z) {
    }

    public void setProgress(float f, boolean z) {
    }

    public void setUnit(String str) {
    }

    static /* synthetic */ int access$108(SingleAddView singleAddView) {
        int i = singleAddView.now;
        singleAddView.now = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(SingleAddView singleAddView) {
        int i = singleAddView.now;
        singleAddView.now = i - 1;
        return i;
    }

    public SingleAddView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SingleAddView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleAddView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_single_add_view, this);
        this.title = (TextView) inflate.findViewById(C2779R.C2782id.txt_title);
        this.subtitle = (TextView) inflate.findViewById(C2779R.C2782id.txt_subtitle);
        this.progress = (TextView) inflate.findViewById(C2779R.C2782id.tv_progress);
        this.imgMinus = (ImageView) inflate.findViewById(C2779R.C2782id.img_minus);
        this.imgAdd = (ImageView) inflate.findViewById(C2779R.C2782id.img_add);
        this.f249s = (Switch) inflate.findViewById(C2779R.C2782id.sw_close);
        C28121 r6 = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SingleAddView.this.updateText();
                if (SingleAddView.this.listener != null) {
                    SingleAddView.this.listener.onChecked(z);
                }
            }
        };
        this.checkedChangeListener = r6;
        this.f249s.setOnCheckedChangeListener(r6);
        this.addSubs = Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                SingleAddView.this.imgAdd.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SingleAddView.access$108(SingleAddView.this);
                        if (SingleAddView.this.now > SingleAddView.this.min + SingleAddView.this.distance) {
                            int unused = SingleAddView.this.now = SingleAddView.this.min + SingleAddView.this.distance;
                        }
                        SingleAddView.this.updateText();
                        if (SingleAddView.this.listener != null) {
                            SingleAddView.this.listener.onDown(true);
                            SingleAddView.this.listener.onChange(SingleAddView.this.now);
                        }
                        observableEmitter.onNext(view);
                        if (!SingleAddView.this.isChecked()) {
                            SingleAddView.this.setChecked(true);
                        }
                    }
                });
            }
        }).debounce(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            public void accept(Object obj) {
                if (SingleAddView.this.listener != null) {
                    SingleAddView.this.listener.onUp(true, SingleAddView.this.now);
                }
            }
        });
        this.minusSubs = Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                SingleAddView.this.imgMinus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SingleAddView.access$110(SingleAddView.this);
                        if (SingleAddView.this.now < SingleAddView.this.min) {
                            int unused = SingleAddView.this.now = SingleAddView.this.min;
                        }
                        SingleAddView.this.updateText();
                        if (SingleAddView.this.listener != null) {
                            SingleAddView.this.listener.onDown(true);
                            SingleAddView.this.listener.onChange(SingleAddView.this.now);
                        }
                        observableEmitter.onNext(this);
                        if (!SingleAddView.this.isChecked()) {
                            SingleAddView.this.setChecked(true);
                        }
                    }
                });
            }
        }).debounce(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            public void accept(Object obj) {
                SingleAddView.this.listener.onUp(true, SingleAddView.this.now);
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.SingleCloseAddProgressBar);
        this.distance = obtainStyledAttributes.getInt(C2779R.styleable.SingleCloseAddProgressBar_distance, 0);
        this.min = obtainStyledAttributes.getInt(C2779R.styleable.SingleCloseAddProgressBar_minNum, 0);
        this.interval = obtainStyledAttributes.getInt(C2779R.styleable.SingleCloseAddProgressBar_interval, 1);
        boolean z = obtainStyledAttributes.getBoolean(C2779R.styleable.SingleCloseAddProgressBar_isMax, false);
        this.isMax = z;
        this.subtitle.setText(z ? "HIGH" : "LOW");
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: package-private */
    public void updateText() {
        if (isChecked()) {
            this.progress.setTextColor(ContextCompat.getColor(Utils.getContext(), this.isMax ? C2779R.C2780color.color_FF6A6A : C2779R.C2780color.color_15BAFF));
            this.progress.setText(this.factory.getText(this.now));
            this.imgMinus.setVisibility(0);
            this.imgAdd.setVisibility(0);
            this.imgMinus.setClickable(true);
            this.imgAdd.setClickable(true);
            return;
        }
        this.progress.setTextColor(ContextCompat.getColor(Utils.getContext(), C2779R.C2780color.disable));
        this.progress.setText("OFF");
        this.imgMinus.setVisibility(4);
        this.imgAdd.setVisibility(4);
        this.imgMinus.setClickable(false);
        this.imgAdd.setClickable(false);
    }

    public void setListener(IEffectBar.Listener listener2) {
        this.listener = listener2;
    }

    public void setType(String str, int i, int i2) {
        this.title.setText(str);
        setValue(i, i2);
    }

    public void setMin(int i) {
        this.min = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setValue(int i, int i2) {
        setMin(i);
        setDistance(i2);
    }

    public void setFactory(IEffectBar.Factory factory2) {
        this.factory = factory2;
        this.progress.setText(factory2.getText(this.now));
    }

    public void setProgress(int i) {
        int i2 = this.min;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.distance;
        if (i > i2 + i3) {
            i = i2 + i3;
        }
        this.now = i;
        updateText();
        IEffectBar.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(this.now);
        }
    }

    public int getProgress() {
        return this.now;
    }

    public void setChecked(boolean z) {
        this.f249s.setChecked(z);
    }

    public void setCheckedSilently(boolean z) {
        this.f249s.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.f249s.setChecked(z);
        this.f249s.setOnCheckedChangeListener(this.checkedChangeListener);
        updateText();
    }

    public boolean isChecked() {
        return this.f249s.isChecked();
    }
}
