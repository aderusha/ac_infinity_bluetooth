package com.eternal.widget.guqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.C2779R;
import java.util.Locale;

public class AddSubView extends ConstraintLayout {
    /* access modifiers changed from: private */
    public int distance;
    private ImageView imgAdd;
    private ImageView imgMinus;
    /* access modifiers changed from: private */
    public OnChangeListener listener;
    private byte mCurrentType;
    /* access modifiers changed from: private */
    public int min;
    /* access modifiers changed from: private */
    public int minDisplace;
    /* access modifiers changed from: private */
    public int now;
    private TextView tvShow;
    private View vLine;

    public interface OnChangeListener {
        void onChange(View view, int i);

        void onExceeded(View view, boolean z);
    }

    public static class Type {
        public static final byte CALIBRATION_HUMIDITY = 5;
        public static final byte CALIBRATION_TEMPERATURE_C = 4;
        public static final byte CALIBRATION_TEMPERATURE_F = 3;
        public static final byte CYCLE_OFF = 7;
        public static final byte LEAF_TEMPERATURE_C = 10;
        public static final byte LEAF_TEMPERATURE_F = 9;
        public static final byte LEVEL = 6;
        public static final byte OFF = 9;

        /* renamed from: ON */
        public static final byte f243ON = 8;
        public static final byte TRANSITION_HUMIDITY = 2;
        public static final byte TRANSITION_TEMPERATURE_C = 1;
        public static final byte TRANSITION_TEMPERATURE_F = 0;
        public static final byte TRANSITION_VPD = 11;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0041 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int convertValue(byte r10, int r11) {
        /*
            r9 = this;
            r0 = 4
            r1 = 2
            r2 = 8
            r3 = 20
            r4 = -10
            r5 = 40
            r6 = -20
            r7 = 0
            r8 = 1
            if (r10 == 0) goto L_0x002b
            if (r10 == r8) goto L_0x0027
            r0 = 9
            if (r10 == r0) goto L_0x0020
            r0 = 40
            r1 = 1
            r2 = 20
            r4 = -20
            r7 = -10
            goto L_0x002c
        L_0x0020:
            r0 = 20
            r2 = 40
            r7 = -20
            goto L_0x002c
        L_0x0027:
            r0 = 8
            r1 = 1
            r2 = 4
        L_0x002b:
            r4 = 0
        L_0x002c:
            int r10 = r11 - r4
            float r10 = (float) r10
            float r0 = (float) r0
            float r10 = r10 / r0
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0041
            float r11 = (float) r2
            float r10 = r10 * r11
            float r11 = (float) r1
            float r10 = r10 / r11
            float r10 = r10 * r11
            int r10 = (int) r10
            int r10 = r10 + r7
            return r10
        L_0x0041:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.AddSubView.convertValue(byte, int):int");
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.listener = onChangeListener;
    }

    public AddSubView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AddSubView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AddSubView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, C2779R.layout.layout_add_sub_view, this);
        this.tvShow = (TextView) inflate.findViewById(C2779R.C2782id.tv_show);
        ImageView imageView = (ImageView) inflate.findViewById(C2779R.C2782id.img_add);
        this.imgAdd = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddSubView addSubView = AddSubView.this;
                int unused = addSubView.now = addSubView.now + AddSubView.this.minDisplace;
                if (AddSubView.this.now > AddSubView.this.min + AddSubView.this.distance) {
                    AddSubView addSubView2 = AddSubView.this;
                    int unused2 = addSubView2.now = addSubView2.min + AddSubView.this.distance;
                    if (AddSubView.this.listener != null) {
                        AddSubView.this.listener.onExceeded(AddSubView.this, true);
                    }
                }
                AddSubView addSubView3 = AddSubView.this;
                addSubView3.setShowText(addSubView3.now);
                if (AddSubView.this.listener != null) {
                    OnChangeListener access$400 = AddSubView.this.listener;
                    AddSubView addSubView4 = AddSubView.this;
                    access$400.onChange(addSubView4, addSubView4.now);
                }
            }
        });
        ImageView imageView2 = (ImageView) inflate.findViewById(C2779R.C2782id.img_minus);
        this.imgMinus = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddSubView addSubView = AddSubView.this;
                int unused = addSubView.now = addSubView.now - AddSubView.this.minDisplace;
                if (AddSubView.this.now < AddSubView.this.min) {
                    AddSubView addSubView2 = AddSubView.this;
                    int unused2 = addSubView2.now = addSubView2.min;
                    if (AddSubView.this.listener != null) {
                        AddSubView.this.listener.onExceeded(AddSubView.this, false);
                    }
                }
                AddSubView addSubView3 = AddSubView.this;
                addSubView3.setShowText(addSubView3.now);
                if (AddSubView.this.listener != null) {
                    OnChangeListener access$400 = AddSubView.this.listener;
                    AddSubView addSubView4 = AddSubView.this;
                    access$400.onChange(addSubView4, addSubView4.now);
                }
            }
        });
        this.vLine = inflate.findViewById(C2779R.C2782id.v_line);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.AddSubView);
        setModeType((byte) obtainStyledAttributes.getInt(C2779R.styleable.AddSubView_modeType, 0), (byte) 0, true);
        obtainStyledAttributes.recycle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r2 = r4.min;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setMinAndMax(int r5, int r6, int r7) {
        /*
            r4 = this;
            int r0 = r4.distance
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 == 0) goto L_0x0017
            int r2 = r4.min
            if (r2 != r5) goto L_0x0010
            if (r0 != r6) goto L_0x0010
            int r3 = r4.minDisplace
            if (r3 == r7) goto L_0x0017
        L_0x0010:
            int r3 = r4.now
            int r3 = r3 - r2
            float r2 = (float) r3
            float r0 = (float) r0
            float r2 = r2 / r0
            goto L_0x0019
        L_0x0017:
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x0019:
            r4.min = r5
            r4.minDisplace = r7
            r4.distance = r6
            int r0 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0031
            if (r7 == 0) goto L_0x0031
            float r6 = (float) r6
            float r2 = r2 * r6
            float r6 = (float) r7
            float r2 = r2 / r6
            float r2 = r2 * r6
            int r6 = (int) r2
            int r6 = r6 + r5
            r4.setShowText(r6)
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.guqiang.AddSubView.setMinAndMax(int, int, int):void");
    }

    private void setModeTypeAB(byte b, byte b2) {
        this.mCurrentType = b;
        if (b == 0) {
            setMinAndMax(0, 8, 2);
        } else if (b == 1) {
            setMinAndMax(0, 4, 1);
        } else if (b == 2) {
            setMinAndMax(0, 8, 2);
        } else if (b != 3) {
            if (b != 4) {
                if (b != 5) {
                    if (b == 9) {
                        setMinAndMax(-20, 40, 2);
                    } else if (b != 10) {
                        setMinAndMax(0, 10, 1);
                    } else {
                        setMinAndMax(-10, 20, 1);
                    }
                } else if (b2 != 0) {
                    setMinAndMax(-10, 20, 1);
                } else {
                    setMinAndMax(-8, 16, 1);
                }
            } else if (b2 != 0) {
                setMinAndMax(-10, 20, 1);
            } else {
                setMinAndMax(-4, 8, 1);
            }
        } else if (b2 != 0) {
            setMinAndMax(-20, 40, 2);
        } else {
            setMinAndMax(-8, 16, 2);
        }
    }

    public void setModeType(byte b, byte b2, boolean z) {
        if (z) {
            setModeTypeAB(b, b2);
            return;
        }
        this.mCurrentType = b;
        if (b == 0) {
            setMinAndMax(0, 20, 1);
        } else if (b == 1) {
            setMinAndMax(0, 10, 1);
        } else if (b == 2) {
            setMinAndMax(0, 10, 1);
        } else if (b == 3) {
            setMinAndMax(-20, 40, 1);
        } else if (b == 4) {
            setMinAndMax(-10, 20, 1);
        } else if (b == 5) {
            setMinAndMax(-10, 20, 1);
        } else if (b == 9) {
            setMinAndMax(-20, 40, 1);
        } else if (b != 10) {
            setMinAndMax(0, 10, 1);
        } else {
            setMinAndMax(-10, 20, 1);
        }
    }

    public void disEditable() {
        this.imgAdd.setVisibility(4);
        this.imgAdd.setClickable(false);
        this.imgMinus.setVisibility(4);
        this.imgMinus.setClickable(false);
        this.vLine.setVisibility(4);
    }

    public void setShowText(int i) {
        int i2 = this.min;
        int i3 = this.distance;
        if (i > i2 + i3) {
            i = i2 + i3;
        }
        if (i >= i2) {
            i2 = i;
        }
        if (this.now != i2) {
            this.now = i2;
            OnChangeListener onChangeListener = this.listener;
            if (onChangeListener != null) {
                onChangeListener.onChange(this, i2);
            }
        }
        switch (this.mCurrentType) {
            case 0:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_fah_num, getPlusNum(this.now)));
                break;
            case 1:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_degree_num, getPlusNum(this.now)));
                break;
            case 2:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_percent_num, getPlusNum(this.now)));
                break;
            case 3:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_fah_num, getPlusNum(this.now)));
                break;
            case 4:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_degree_num, getPlusNum(this.now)));
                break;
            case 5:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_percent_num, getPlusNum(this.now)));
                break;
            case 9:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_fah_num, getPlusNum(this.now)));
                break;
            case 10:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_degree_num, getPlusNum(this.now)));
                break;
            case 11:
                this.tvShow.setText(Utils.getString(C2779R.string.tip_kpa, String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) this.now) / 10.0f)})));
                break;
            default:
                this.tvShow.setText(String.valueOf(this.now));
                break;
        }
        this.tvShow.setTextColor(getResources().getColor(this.now == 0 ? C2779R.C2780color.white : C2779R.C2780color.color_15BAFF));
    }

    private String getPlusNum(int i) {
        return String.valueOf(i);
    }
}
