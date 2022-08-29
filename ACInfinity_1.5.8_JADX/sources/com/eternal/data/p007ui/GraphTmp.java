package com.eternal.data.p007ui;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.content.ContextCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.data.C1835R;
import com.eternal.data.p007ui.GraphView;
import com.eternal.framework.utils.Utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/* renamed from: com.eternal.data.ui.GraphTmp */
public class GraphTmp implements GraphView.IGraphDelegate {
    private GraphAdapter adapter;
    private DecimalFormat format;
    private DecimalFormat format2;
    private Path graphPath;
    private boolean isDegree = true;
    private Path missPath;
    public int scrollerTmp;
    public RectF scrollerTmpLabelBounds;
    public Rect scrollerTmpLabelTextBounds;
    public float scrollerTmpY;
    public String tmpLabelText;

    public GraphTmp(GraphAdapter graphAdapter) {
        this.adapter = graphAdapter;
        this.format = new DecimalFormat("0.0");
        this.format2 = new DecimalFormat("0");
        this.format.setRoundingMode(RoundingMode.HALF_UP);
        this.format2.setRoundingMode(RoundingMode.HALF_UP);
        this.graphPath = new Path();
        this.missPath = new Path();
        this.scrollerTmpLabelTextBounds = new Rect();
        this.scrollerTmpLabelBounds = new RectF();
        this.tmpLabelText = "";
    }

    public void setDegree(boolean z) {
        this.isDegree = z;
    }

    public TimeValue getItem(int i) {
        TmpHum item = this.adapter.getItem(i);
        return item == null ? new TimeValue(0, 0) : new TimeValue(item.time, item.getTmpValue());
    }

    public float getAvg() {
        return this.adapter.avgTmp;
    }

    public int getAMax() {
        return this.adapter.aMaxTmp;
    }

    public int getAMin() {
        return this.adapter.aMinTmp;
    }

    public int getMax() {
        return this.adapter.maxTmp;
    }

    public int getMin() {
        return this.adapter.minTmp;
    }

    public int getMaxTarget() {
        return this.adapter.getMaxTargetTmp();
    }

    public int getMinTarget() {
        return this.adapter.getMinTargetTmp();
    }

    public String formatValue(float f) {
        if (this.adapter.isDataDegree()) {
            if (this.isDegree) {
                if (f == -32768.0f) {
                    return "--℃";
                }
                return this.format.format((double) (f / 100.0f)) + ProtocolTransformer.DEGREE;
            } else if (f == -32768.0f) {
                return "--℉";
            } else {
                return this.format.format((double) ProtocolTransformer.getFah(f / 100.0f)) + ProtocolTransformer.FAH;
            }
        } else if (this.isDegree) {
            if (f == -32768.0f) {
                return "--℃";
            }
            return this.format.format((double) ProtocolTransformer.getDegree(f / 100.0f)) + ProtocolTransformer.DEGREE;
        } else if (f == -32768.0f) {
            return "--℉";
        } else {
            return this.format.format((double) (f / 100.0f)) + ProtocolTransformer.FAH;
        }
    }

    public String formatValue2(float f, float f2) {
        float f3 = f2 / 10.0f;
        if (this.adapter.isDataDegree()) {
            if (this.isDegree) {
                if (f == -32768.0f) {
                    return "--℃";
                }
                return this.format2.format((double) (((float) Math.round(f / 10.0f)) * f3)) + ProtocolTransformer.DEGREE;
            } else if (f == -32768.0f) {
                return "--℉";
            } else {
                return this.format2.format((double) (((float) Math.round(ProtocolTransformer.getFah(f / 100.0f) * 10.0f)) * f3)) + ProtocolTransformer.FAH;
            }
        } else if (this.isDegree) {
            if (f == -32768.0f) {
                return "--℃";
            }
            return this.format2.format((double) (((float) Math.round(ProtocolTransformer.getDegree(f / 100.0f) * 10.0f)) * f3)) + ProtocolTransformer.DEGREE;
        } else if (f == -32768.0f) {
            return "--℉";
        } else {
            return this.format2.format((double) (((float) Math.round(f / 10.0f)) * f3)) + ProtocolTransformer.FAH;
        }
    }

    public Path getGraphPath() {
        return this.graphPath;
    }

    public Path getMissPath() {
        return this.missPath;
    }

    public int getDefaultColor() {
        return ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_graph_tmp);
    }
}
