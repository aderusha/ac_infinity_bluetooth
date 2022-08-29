package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.IEffectBar;
import com.zyyoona7.wheel.WheelView;
import java.util.ArrayList;

public class TimeWheel extends ConstraintLayout implements IEffectBar {
    /* access modifiers changed from: private */
    public ArrayList<String> cycleHours;
    /* access modifiers changed from: private */
    public ArrayList<String> cycleMinus;
    /* access modifiers changed from: private */
    public ArrayList<String> cycleMinus24;
    private IEffectBar.Factory factory;
    /* access modifiers changed from: private */
    public boolean isFromUserHour;
    /* access modifiers changed from: private */
    public boolean isFromUserMin;
    /* access modifiers changed from: private */
    public IEffectBar.Listener listener;
    /* access modifiers changed from: private */
    public int now;
    int selectedTextColor;
    private TextView tvHours;
    private TextView tvMin;
    /* access modifiers changed from: private */
    public WheelView wvHour;
    /* access modifiers changed from: private */
    public WheelView wvMin;

    public float getTx() {
        return 0.0f;
    }

    public boolean isChecked() {
        return true;
    }

    public void setFillWhenEqual(boolean z) {
    }

    public void setProgress(float f, boolean z) {
    }

    public TimeWheel(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimeWheel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeWheel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_time_wheel, this);
        this.wvHour = (WheelView) inflate.findViewById(C2779R.C2782id.wv_hour);
        this.wvMin = (WheelView) inflate.findViewById(C2779R.C2782id.wv_min);
        this.tvHours = (TextView) inflate.findViewById(C2779R.C2782id.tv_hours);
        this.tvMin = (TextView) inflate.findViewById(C2779R.C2782id.tv_min);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.TimeWheel);
        int color = obtainStyledAttributes.getColor(C2779R.styleable.TimeWheel_selectedTextColor, getResources().getColor(C2779R.C2780color.color_15BAFF));
        this.selectedTextColor = color;
        this.tvHours.setTextColor(color);
        this.tvMin.setTextColor(this.selectedTextColor);
        obtainStyledAttributes.recycle();
        this.cycleHours = initTime(0, 24, true);
        this.cycleMinus = initTime(0, 59, true);
        ArrayList<String> arrayList = new ArrayList<>();
        this.cycleMinus24 = arrayList;
        arrayList.add("00");
        initTime(this.cycleHours, this.cycleMinus);
    }

    private void initTime(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this.wvHour.setCyclic(true);
        this.wvHour.setData(arrayList);
        this.wvHour.setVisibleItems(3);
        this.wvHour.setResetSelectedPosition(true);
        this.wvHour.setCurved(false);
        this.wvHour.setLineSpacing(getResources().getDimension(C2779R.dimen.dp_23));
        this.wvHour.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2779R.font.avenir_medium));
        this.wvHour.setSelectedItemTextColor(this.selectedTextColor);
        this.wvHour.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        this.wvHour.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        this.wvHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelScroll(int i) {
            }

            public void onWheelItemChanged(int i, int i2) {
                if (i2 == TimeWheel.this.cycleHours.size() - 1) {
                    if (TimeWheel.this.wvMin.getData().size() != 1) {
                        TimeWheel.this.wvMin.setCyclic(false);
                        TimeWheel.this.wvMin.setData(TimeWheel.this.cycleMinus24);
                        TimeWheel.this.wvMin.setSelectedItemPosition(0, false);
                        int unused = TimeWheel.this.now = i2 * 60;
                        if (TimeWheel.this.listener != null) {
                            TimeWheel.this.listener.onChange(TimeWheel.this.now);
                            return;
                        }
                        return;
                    }
                } else if (TimeWheel.this.wvMin.getData().size() == 1) {
                    TimeWheel.this.wvMin.setCyclic(true);
                    TimeWheel.this.wvMin.setData(TimeWheel.this.cycleMinus);
                    TimeWheel.this.wvMin.setSelectedItemPosition(0, false);
                    int unused2 = TimeWheel.this.now = i2 * 60;
                    if (TimeWheel.this.listener != null) {
                        TimeWheel.this.listener.onChange(TimeWheel.this.now);
                        return;
                    }
                    return;
                }
                TimeWheel timeWheel = TimeWheel.this;
                int unused3 = timeWheel.now = (i2 * 60) + timeWheel.wvMin.getSelectedItemPosition();
                if (TimeWheel.this.listener != null) {
                    TimeWheel.this.listener.onChange(TimeWheel.this.now);
                }
            }

            public void onWheelSelected(int i) {
                if (i == TimeWheel.this.cycleHours.size() - 1) {
                    if (TimeWheel.this.wvMin.getData().size() != 1) {
                        TimeWheel.this.wvMin.setCyclic(false);
                        TimeWheel.this.wvMin.setData(TimeWheel.this.cycleMinus24);
                        int unused = TimeWheel.this.now = i * 60;
                        return;
                    }
                } else if (TimeWheel.this.wvMin.getData().size() == 1) {
                    TimeWheel.this.wvMin.setCyclic(true);
                    TimeWheel.this.wvMin.setData(TimeWheel.this.cycleMinus);
                    int unused2 = TimeWheel.this.now = i * 60;
                    return;
                }
                TimeWheel timeWheel = TimeWheel.this;
                int unused3 = timeWheel.now = (i * 60) + timeWheel.wvMin.getSelectedItemPosition();
            }

            public void onWheelScrollStateChanged(int i) {
                if (i == 1) {
                    boolean unused = TimeWheel.this.isFromUserHour = true;
                    if (TimeWheel.this.listener != null) {
                        TimeWheel.this.listener.onDown(true);
                    }
                } else if (i == 0 && TimeWheel.this.listener != null && TimeWheel.this.isFromUserHour) {
                    boolean unused2 = TimeWheel.this.isFromUserHour = false;
                    if (!TimeWheel.this.isFromUserHour && !TimeWheel.this.isFromUserMin) {
                        TimeWheel.this.listener.onUp(true, TimeWheel.this.now);
                    }
                }
            }
        });
        this.wvMin.setCyclic(true);
        this.wvMin.setData(arrayList2);
        this.wvMin.setVisibleItems(3);
        this.wvMin.setResetSelectedPosition(true);
        this.wvMin.setCurved(false);
        this.wvMin.setLineSpacing(getResources().getDimension(C2779R.dimen.dp_23));
        this.wvMin.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2779R.font.avenir_medium));
        this.wvMin.setSelectedItemTextColor(this.selectedTextColor);
        this.wvMin.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        this.wvMin.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        this.wvMin.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelScroll(int i) {
            }

            public void onWheelItemChanged(int i, int i2) {
                TimeWheel timeWheel = TimeWheel.this;
                int unused = timeWheel.now = (timeWheel.wvHour.getSelectedItemPosition() * 60) + i2;
                if (TimeWheel.this.listener != null) {
                    TimeWheel.this.listener.onChange(TimeWheel.this.now);
                }
            }

            public void onWheelSelected(int i) {
                TimeWheel timeWheel = TimeWheel.this;
                int unused = timeWheel.now = (timeWheel.wvHour.getSelectedItemPosition() * 60) + i;
            }

            public void onWheelScrollStateChanged(int i) {
                if (i == 1) {
                    boolean unused = TimeWheel.this.isFromUserMin = true;
                    if (TimeWheel.this.listener != null) {
                        TimeWheel.this.listener.onDown(true);
                    }
                } else if (i == 0 && TimeWheel.this.listener != null && TimeWheel.this.isFromUserMin) {
                    boolean unused2 = TimeWheel.this.isFromUserMin = false;
                    if (!TimeWheel.this.isFromUserHour && !TimeWheel.this.isFromUserMin) {
                        TimeWheel.this.listener.onUp(true, TimeWheel.this.now);
                    }
                }
            }
        });
    }

    private ArrayList<String> initTime(int i, int i2, boolean z) {
        ArrayList<String> arrayList = new ArrayList<>((i2 - i) + 1);
        if (z) {
            if (i < 10) {
                int min = Math.min(i2, 10);
                while (i < min) {
                    arrayList.add("0" + i);
                    i++;
                }
            }
            while (i <= i2) {
                arrayList.add(String.valueOf(i));
                i++;
            }
        } else {
            while (i <= i2) {
                arrayList.add(String.valueOf(i));
                i++;
            }
        }
        return arrayList;
    }

    public void setType(boolean z) {
        this.wvHour.setSelectedItemTextColor(getResources().getColor(z ? C2779R.C2780color.color_15BAFF : C2779R.C2780color.color_FF6A6A));
        this.wvMin.setSelectedItemTextColor(getResources().getColor(z ? C2779R.C2780color.color_15BAFF : C2779R.C2780color.color_FF6A6A));
        this.tvHours.setTextColor(getResources().getColor(z ? C2779R.C2780color.color_15BAFF : C2779R.C2780color.color_FF6A6A));
        this.tvMin.setTextColor(getResources().getColor(z ? C2779R.C2780color.color_15BAFF : C2779R.C2780color.color_FF6A6A));
    }

    public void setListener(IEffectBar.Listener listener2) {
        this.listener = listener2;
    }

    public void setFactory(IEffectBar.Factory factory2) {
        this.factory = factory2;
        this.wvHour.setSelectedItemPosition(this.now / 60, false);
        this.wvMin.setSelectedItemPosition(this.now % 60, false);
    }

    public void setProgress(int i) {
        this.now = i;
        int i2 = i / 60;
        if (i2 != this.wvHour.getSelectedItemPosition()) {
            this.wvHour.setSelectedItemPosition(i2);
        }
        int i3 = i % 60;
        if (i3 != this.wvMin.getSelectedItemPosition()) {
            this.wvMin.setSelectedItemPosition(i3);
        }
        IEffectBar.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(i);
        }
    }

    public int getProgress() {
        return this.now;
    }
}
