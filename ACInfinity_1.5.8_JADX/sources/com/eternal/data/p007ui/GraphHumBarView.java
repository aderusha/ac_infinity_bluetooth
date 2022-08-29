package com.eternal.data.p007ui;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.data.C1835R;
import com.eternal.framework.utils.Utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/* renamed from: com.eternal.data.ui.GraphHumBarView */
public class GraphHumBarView extends GraphBarView {
    private DecimalFormat format;

    /* access modifiers changed from: package-private */
    public int getDistance() {
        return 100;
    }

    public GraphHumBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GraphHumBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GraphHumBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        DecimalFormat decimalFormat = new DecimalFormat("0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    /* access modifiers changed from: package-private */
    public String formatMaxMin(int i) {
        if (i == -32768) {
            return "--%";
        }
        return this.format.format((double) (((float) i) / 100.0f)) + "%";
    }

    /* access modifiers changed from: package-private */
    public int getDefaultColor() {
        return ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_graph_hum);
    }

    /* access modifiers changed from: package-private */
    public TimeValue getItem(int i) {
        TmpHum item = this.adapter.getItem(i);
        if (item != null) {
            return new TimeValue(item.time, item.hum);
        }
        return new TimeValue(0, 0);
    }

    /* access modifiers changed from: package-private */
    public int getMax() {
        return this.adapter.maxHum;
    }

    /* access modifiers changed from: package-private */
    public int getMin() {
        return this.adapter.minHum;
    }

    /* access modifiers changed from: package-private */
    public String formatValue(int i) {
        return this.format.format((double) (((float) i) / 100.0f)) + "%";
    }
}
