package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.C2779R;
import com.eternal.widget.guqiang.IEffectBar;
import com.zyyoona7.wheel.WheelView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeAmPmWheel extends ConstraintLayout implements IEffectBar {
    private final String DASH;
    private List<String> aps;
    private IEffectBar.Factory factory;
    private List<String> hours;
    private boolean isChecked;
    /* access modifiers changed from: private */
    public boolean isFromUserAmOrPm;
    /* access modifiers changed from: private */
    public boolean isFromUserHour;
    /* access modifiers changed from: private */
    public boolean isFromUserMin;
    /* access modifiers changed from: private */
    public IEffectBar.Listener listener;
    private List<String> minus;
    /* access modifiers changed from: private */
    public int now;
    int selectedTextColor;
    /* access modifiers changed from: private */
    public List<String> singleAps;
    /* access modifiers changed from: private */
    public List<String> singleMins;
    /* access modifiers changed from: private */
    public TextView tvSign;
    /* access modifiers changed from: private */
    public WheelView wvAmOrPm;
    /* access modifiers changed from: private */
    public WheelView wvHour;
    /* access modifiers changed from: private */
    public WheelView wvMin;

    public float getTx() {
        return 0.0f;
    }

    public void setFillWhenEqual(boolean z) {
    }

    public void setProgress(float f, boolean z) {
    }

    public TimeAmPmWheel(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimeAmPmWheel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeAmPmWheel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DASH = "- -";
        View inflate = View.inflate(context, C2779R.layout.layout_time_am_pm_wheel, this);
        this.wvHour = (WheelView) inflate.findViewById(C2779R.C2782id.wv_hour);
        this.wvMin = (WheelView) inflate.findViewById(C2779R.C2782id.wv_min);
        this.wvAmOrPm = (WheelView) inflate.findViewById(C2779R.C2782id.wv_am_or_pm);
        this.tvSign = (TextView) inflate.findViewById(C2779R.C2782id.tv_sign);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.TimeWheel);
        int color = obtainStyledAttributes.getColor(C2779R.styleable.TimeWheel_selectedTextColor, getResources().getColor(C2779R.C2780color.color_15BAFF));
        this.selectedTextColor = color;
        this.tvSign.setTextColor(color);
        obtainStyledAttributes.recycle();
        this.singleMins = Collections.singletonList("- -");
        this.singleAps = Collections.singletonList("");
        this.hours = getTimeList(1, 12, true);
        this.minus = getTimeList(0, 59, true);
        ArrayList arrayList = new ArrayList();
        this.aps = arrayList;
        arrayList.add("AM");
        this.aps.add("PM");
        initTime(this.hours, this.minus, this.aps);
    }

    public void showDash() {
        this.hours.add("- -");
        this.wvHour.setData(this.hours);
    }

    public static ArrayList<String> getTimeList(int i, int i2, boolean z) {
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

    private void initTime(final List<String> list, final List<String> list2, final List<String> list3) {
        this.wvHour.setCyclic(true);
        this.wvHour.setData(list);
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
                if ("- -".equals(list.get(i2))) {
                    TimeAmPmWheel.this.wvHour.setSelectedItemTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    TimeAmPmWheel.this.tvSign.setTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    if (TimeAmPmWheel.this.wvMin.getData().size() != 1) {
                        TimeAmPmWheel.this.wvMin.setCyclic(false);
                        TimeAmPmWheel.this.wvMin.setData(TimeAmPmWheel.this.singleMins);
                        TimeAmPmWheel.this.wvMin.setSelectedItemPosition(0, false);
                        TimeAmPmWheel.this.wvMin.setSelectedItemTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    }
                    if (TimeAmPmWheel.this.wvAmOrPm.getData().size() != 1) {
                        TimeAmPmWheel.this.wvAmOrPm.setData(TimeAmPmWheel.this.singleAps);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemPosition(0, false);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    }
                    int unused = TimeAmPmWheel.this.now = 65535;
                } else {
                    TimeAmPmWheel.this.wvHour.setSelectedItemTextColor(TimeAmPmWheel.this.selectedTextColor);
                    TimeAmPmWheel.this.tvSign.setTextColor(TimeAmPmWheel.this.selectedTextColor);
                    if (TimeAmPmWheel.this.wvMin.getData().size() == 1) {
                        TimeAmPmWheel.this.wvMin.setCyclic(true);
                        TimeAmPmWheel.this.wvMin.setData(list2);
                        TimeAmPmWheel.this.wvMin.setSelectedItemTextColor(TimeAmPmWheel.this.selectedTextColor);
                        TimeAmPmWheel.this.wvMin.setSelectedItemPosition(0, false);
                    }
                    if (TimeAmPmWheel.this.wvAmOrPm.getData().size() == 1) {
                        TimeAmPmWheel.this.wvAmOrPm.setData(list3);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemTextColor(TimeAmPmWheel.this.selectedTextColor);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemPosition(0, false);
                    }
                    int i3 = i2 + 1;
                    if (i3 >= 12) {
                        i3 -= 12;
                    }
                    TimeAmPmWheel timeAmPmWheel = TimeAmPmWheel.this;
                    int unused2 = timeAmPmWheel.now = (i3 * 60) + timeAmPmWheel.wvMin.getSelectedItemPosition() + (TimeAmPmWheel.this.wvAmOrPm.getSelectedItemPosition() * 720);
                }
                if (TimeAmPmWheel.this.listener != null) {
                    TimeAmPmWheel.this.listener.onChange(TimeAmPmWheel.this.now);
                }
            }

            public void onWheelSelected(int i) {
                if ("- -".equals(list.get(i))) {
                    TimeAmPmWheel.this.wvHour.setSelectedItemTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    TimeAmPmWheel.this.tvSign.setTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    if (TimeAmPmWheel.this.wvMin.getData().size() != 1) {
                        TimeAmPmWheel.this.wvMin.setCyclic(false);
                        TimeAmPmWheel.this.wvMin.setData(TimeAmPmWheel.this.singleMins);
                        TimeAmPmWheel.this.wvMin.setSelectedItemPosition(0, false);
                        TimeAmPmWheel.this.wvMin.setSelectedItemTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    }
                    if (TimeAmPmWheel.this.wvAmOrPm.getData().size() != 1) {
                        TimeAmPmWheel.this.wvAmOrPm.setData(TimeAmPmWheel.this.singleAps);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemPosition(0, false);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemTextColor(TimeAmPmWheel.this.getResources().getColor(C2779R.C2780color.color_707070));
                    }
                    int unused = TimeAmPmWheel.this.now = 65535;
                } else {
                    TimeAmPmWheel.this.wvHour.setSelectedItemTextColor(TimeAmPmWheel.this.selectedTextColor);
                    TimeAmPmWheel.this.tvSign.setTextColor(TimeAmPmWheel.this.selectedTextColor);
                    if (TimeAmPmWheel.this.wvMin.getData().size() == 1) {
                        TimeAmPmWheel.this.wvMin.setCyclic(true);
                        TimeAmPmWheel.this.wvMin.setData(list2);
                        TimeAmPmWheel.this.wvMin.setSelectedItemTextColor(TimeAmPmWheel.this.selectedTextColor);
                        TimeAmPmWheel.this.wvMin.setSelectedItemPosition(0, false);
                    }
                    if (TimeAmPmWheel.this.wvAmOrPm.getData().size() == 1) {
                        TimeAmPmWheel.this.wvAmOrPm.setData(list3);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemTextColor(TimeAmPmWheel.this.selectedTextColor);
                        TimeAmPmWheel.this.wvAmOrPm.setSelectedItemPosition(0, false);
                    }
                    int i2 = i + 1;
                    if (i2 >= 12) {
                        i2 -= 12;
                    }
                    TimeAmPmWheel timeAmPmWheel = TimeAmPmWheel.this;
                    int unused2 = timeAmPmWheel.now = (i2 * 60) + timeAmPmWheel.wvMin.getSelectedItemPosition() + (TimeAmPmWheel.this.wvAmOrPm.getSelectedItemPosition() * 720);
                }
                if (TimeAmPmWheel.this.listener != null) {
                    TimeAmPmWheel.this.listener.onChange(TimeAmPmWheel.this.now);
                }
            }

            public void onWheelScrollStateChanged(int i) {
                if (i == 1) {
                    boolean unused = TimeAmPmWheel.this.isFromUserHour = true;
                    if (TimeAmPmWheel.this.listener != null) {
                        TimeAmPmWheel.this.listener.onDown(true);
                    }
                } else if (i == 0 && TimeAmPmWheel.this.listener != null && TimeAmPmWheel.this.isFromUserHour) {
                    boolean unused2 = TimeAmPmWheel.this.isFromUserHour = false;
                    if (!TimeAmPmWheel.this.isFromUserHour && !TimeAmPmWheel.this.isFromUserMin && !TimeAmPmWheel.this.isFromUserAmOrPm) {
                        TimeAmPmWheel.this.listener.onUp(true, TimeAmPmWheel.this.now);
                    }
                }
            }
        });
        this.wvMin.setCyclic(true);
        this.wvMin.setData(list2);
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
                if (TimeAmPmWheel.this.now != 65535) {
                    int selectedItemPosition = TimeAmPmWheel.this.wvHour.getSelectedItemPosition() + 1;
                    if (selectedItemPosition >= 12) {
                        selectedItemPosition -= 12;
                    }
                    TimeAmPmWheel timeAmPmWheel = TimeAmPmWheel.this;
                    int unused = timeAmPmWheel.now = (selectedItemPosition * 60) + i2 + (timeAmPmWheel.wvAmOrPm.getSelectedItemPosition() * 720);
                }
                if (TimeAmPmWheel.this.listener != null) {
                    TimeAmPmWheel.this.listener.onChange(TimeAmPmWheel.this.now);
                }
            }

            public void onWheelSelected(int i) {
                if (TimeAmPmWheel.this.now != 65535) {
                    int selectedItemPosition = TimeAmPmWheel.this.wvHour.getSelectedItemPosition() + 1;
                    if (selectedItemPosition >= 12) {
                        selectedItemPosition -= 12;
                    }
                    TimeAmPmWheel timeAmPmWheel = TimeAmPmWheel.this;
                    int unused = timeAmPmWheel.now = (selectedItemPosition * 60) + i + (timeAmPmWheel.wvAmOrPm.getSelectedItemPosition() * 720);
                }
            }

            public void onWheelScrollStateChanged(int i) {
                if (i == 1) {
                    boolean unused = TimeAmPmWheel.this.isFromUserMin = true;
                    if (TimeAmPmWheel.this.listener != null) {
                        TimeAmPmWheel.this.listener.onDown(true);
                    }
                } else if (i == 0 && TimeAmPmWheel.this.listener != null && TimeAmPmWheel.this.isFromUserMin) {
                    boolean unused2 = TimeAmPmWheel.this.isFromUserMin = false;
                    StringBuilder sb = new StringBuilder();
                    sb.append("滚轮 Hour:");
                    sb.append(!TimeAmPmWheel.this.isFromUserHour);
                    sb.append(" ,Min:");
                    sb.append(!TimeAmPmWheel.this.isFromUserMin);
                    sb.append(",AP：");
                    sb.append(!TimeAmPmWheel.this.isFromUserAmOrPm);
                    KLog.m62d(sb.toString());
                    if (!TimeAmPmWheel.this.isFromUserHour && !TimeAmPmWheel.this.isFromUserMin && !TimeAmPmWheel.this.isFromUserAmOrPm) {
                        TimeAmPmWheel.this.listener.onUp(true, TimeAmPmWheel.this.now);
                    }
                }
            }
        });
        this.wvAmOrPm.setCyclic(false);
        this.wvAmOrPm.setData(list3);
        this.wvAmOrPm.setVisibleItems(2);
        this.wvAmOrPm.setResetSelectedPosition(true);
        this.wvAmOrPm.setCurved(false);
        this.wvAmOrPm.setLineSpacing(getResources().getDimension(C2779R.dimen.dp_23));
        this.wvAmOrPm.setTypeface(ResourcesCompat.getFont(Utils.getContext(), C2779R.font.avenir_medium));
        this.wvAmOrPm.setSelectedItemTextColor(this.selectedTextColor);
        this.wvAmOrPm.setNormalItemTextColor(getResources().getColor(C2779R.C2780color.color_707070));
        this.wvAmOrPm.setTextSize(getResources().getDimension(C2779R.dimen.dp_18));
        this.wvAmOrPm.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            public void onWheelScroll(int i) {
            }

            public void onWheelItemChanged(int i, int i2) {
                if (TimeAmPmWheel.this.now != 65535) {
                    int selectedItemPosition = TimeAmPmWheel.this.wvHour.getSelectedItemPosition() + 1;
                    if (selectedItemPosition >= 12) {
                        selectedItemPosition -= 12;
                    }
                    TimeAmPmWheel timeAmPmWheel = TimeAmPmWheel.this;
                    int unused = timeAmPmWheel.now = (selectedItemPosition * 60) + timeAmPmWheel.wvMin.getSelectedItemPosition() + (i2 * 720);
                }
                if (TimeAmPmWheel.this.listener != null) {
                    TimeAmPmWheel.this.listener.onChange(TimeAmPmWheel.this.now);
                }
            }

            public void onWheelSelected(int i) {
                if (TimeAmPmWheel.this.now != 65535) {
                    int selectedItemPosition = TimeAmPmWheel.this.wvHour.getSelectedItemPosition() + 1;
                    if (selectedItemPosition >= 12) {
                        selectedItemPosition -= 12;
                    }
                    TimeAmPmWheel timeAmPmWheel = TimeAmPmWheel.this;
                    int unused = timeAmPmWheel.now = (selectedItemPosition * 60) + timeAmPmWheel.wvMin.getSelectedItemPosition() + (i * 720);
                }
            }

            public void onWheelScrollStateChanged(int i) {
                if (i == 1) {
                    boolean unused = TimeAmPmWheel.this.isFromUserAmOrPm = true;
                    if (TimeAmPmWheel.this.listener != null) {
                        TimeAmPmWheel.this.listener.onDown(true);
                    }
                } else if (i == 0 && TimeAmPmWheel.this.listener != null && TimeAmPmWheel.this.isFromUserAmOrPm) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("滚轮 Hour:");
                    sb.append(!TimeAmPmWheel.this.isFromUserHour);
                    sb.append(" ,Min:");
                    sb.append(!TimeAmPmWheel.this.isFromUserMin);
                    sb.append(",AP：");
                    sb.append(!TimeAmPmWheel.this.isFromUserAmOrPm);
                    KLog.m62d(sb.toString());
                    boolean unused2 = TimeAmPmWheel.this.isFromUserAmOrPm = false;
                    if (!TimeAmPmWheel.this.isFromUserHour && !TimeAmPmWheel.this.isFromUserMin && !TimeAmPmWheel.this.isFromUserAmOrPm) {
                        TimeAmPmWheel.this.listener.onUp(true, TimeAmPmWheel.this.now);
                    }
                }
            }
        });
    }

    public void setType(boolean z) {
        int color = getResources().getColor(z ? C2779R.C2780color.color_15BAFF : C2779R.C2780color.color_FF6A6A);
        this.selectedTextColor = color;
        this.wvHour.setSelectedItemTextColor(color);
        this.wvMin.setSelectedItemTextColor(this.selectedTextColor);
        this.wvAmOrPm.setSelectedItemTextColor(this.selectedTextColor);
        this.tvSign.setTextColor(this.selectedTextColor);
    }

    public void setListener(IEffectBar.Listener listener2) {
        this.listener = listener2;
    }

    public void setFactory(IEffectBar.Factory factory2) {
        this.factory = factory2;
        updateView(this.now);
    }

    public void setProgress(int i) {
        this.now = i;
        updateView(i);
        IEffectBar.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onChange(i);
        }
    }

    private void updateView(int i) {
        int i2;
        int i3;
        int i4 = 0;
        if (i == 65535) {
            i2 = this.hours.size() - 1;
            i3 = 0;
        } else {
            int i5 = i / 60;
            int i6 = i % 60;
            int i7 = 12;
            if (i5 >= 12) {
                i5 -= 12;
                i4 = 1;
            }
            if (i5 > 0 && i5 <= 12) {
                i7 = i5;
            }
            int i8 = i4;
            i4 = i6;
            i2 = i7 - 1;
            i3 = i8;
        }
        if (i2 != this.wvHour.getSelectedItemPosition()) {
            this.wvHour.setSelectedItemPosition(i2);
        }
        if (i4 != this.wvMin.getSelectedItemPosition()) {
            this.wvMin.setSelectedItemPosition(i4);
        }
        if (i3 != this.wvAmOrPm.getSelectedItemPosition()) {
            this.wvAmOrPm.setSelectedItemPosition(i3);
        }
    }

    public int getProgress() {
        return this.now;
    }

    public boolean isChecked() {
        return this.isChecked;
    }
}
