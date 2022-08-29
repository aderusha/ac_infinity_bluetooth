package com.eternal.data.p007ui;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.data.C1835R;
import com.eternal.framework.utils.Utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/* renamed from: com.eternal.data.ui.GraphTmpBarView */
public class GraphTmpBarView extends GraphBarView {
    private DecimalFormat format;
    private boolean isDataDegree;
    private boolean isDegree;

    /* access modifiers changed from: package-private */
    public int getDistance() {
        return 100;
    }

    public GraphTmpBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GraphTmpBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GraphTmpBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isDegree = true;
        this.isDataDegree = true;
        DecimalFormat decimalFormat = new DecimalFormat("0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    public void setDegree(boolean z) {
        this.isDegree = z;
    }

    public void setDataDegree(boolean z) {
        this.isDataDegree = z;
    }

    /* access modifiers changed from: package-private */
    public TimeValue getItem(int i) {
        TmpHum item = this.adapter.getItem(i);
        if (item == null) {
            return new TimeValue(0, 0);
        }
        if (this.isDataDegree) {
            if (this.isDegree) {
                return new TimeValue(item.time, item.getTmpValue());
            }
            return new TimeValue(item.time, Math.round(ProtocolTransformer.getFah(((float) item.getTmpValue()) / 100.0f) * 100.0f));
        } else if (this.isDegree) {
            return new TimeValue(item.time, Math.round(ProtocolTransformer.getDegree(((float) item.getTmpValue()) / 100.0f) * 100.0f));
        } else {
            return new TimeValue(item.time, item.getTmpValue());
        }
    }

    /* access modifiers changed from: package-private */
    public int getMax() {
        if (this.isDataDegree) {
            if (this.isDegree || this.adapter.maxTmp == 0) {
                return this.adapter.maxTmp;
            }
            return Math.round(ProtocolTransformer.getFah(((float) this.adapter.maxTmp) / 100.0f) * 100.0f);
        } else if (this.isDegree || this.adapter.maxTmp == 0) {
            return Math.round(ProtocolTransformer.getDegree(((float) this.adapter.maxTmp) / 100.0f) * 100.0f);
        } else {
            return this.adapter.maxTmp;
        }
    }

    /* access modifiers changed from: package-private */
    public int getMin() {
        if (this.isDataDegree) {
            if (this.isDegree || this.adapter.minTmp == 0) {
                return this.adapter.minTmp;
            }
            return Math.round(ProtocolTransformer.getFah(((float) this.adapter.minTmp) / 100.0f) * 100.0f);
        } else if (this.isDegree || this.adapter.minTmp == 0) {
            return Math.round(ProtocolTransformer.getDegree(((float) this.adapter.minTmp) / 100.0f) * 100.0f);
        } else {
            return this.adapter.minTmp;
        }
    }

    /* access modifiers changed from: package-private */
    public String formatValue(int i) {
        if (this.isDegree) {
            return this.format.format((double) (((float) i) / 100.0f)) + ProtocolTransformer.DEGREE;
        }
        return this.format.format((double) (((float) i) / 100.0f)) + ProtocolTransformer.FAH;
    }

    /* access modifiers changed from: package-private */
    public int getDefaultColor() {
        return ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_graph_tmp);
    }

    /* access modifiers changed from: package-private */
    public String formatMaxMin(int i) {
        if (this.isDegree) {
            if (i == -32768) {
                return "--℃";
            }
            return this.format.format((double) (((float) i) / 100.0f)) + ProtocolTransformer.DEGREE;
        } else if (i == -32768) {
            return "--℉";
        } else {
            return this.format.format((double) (((float) i) / 100.0f)) + ProtocolTransformer.FAH;
        }
    }
}
