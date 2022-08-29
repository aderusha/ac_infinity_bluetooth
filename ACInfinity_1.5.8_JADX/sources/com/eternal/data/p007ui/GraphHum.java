package com.eternal.data.p007ui;

import android.graphics.Path;
import androidx.core.content.ContextCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.data.C1835R;
import com.eternal.data.p007ui.GraphView;
import com.eternal.framework.utils.Utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/* renamed from: com.eternal.data.ui.GraphHum */
public class GraphHum implements GraphView.IGraphDelegate {
    private GraphAdapter adapter;
    private DecimalFormat format = new DecimalFormat("0.0");
    private DecimalFormat format2 = new DecimalFormat("0");
    private Path graphPath;
    private Path missPath;

    public GraphHum(GraphAdapter graphAdapter) {
        this.adapter = graphAdapter;
        this.format.setRoundingMode(RoundingMode.HALF_UP);
        this.format2.setRoundingMode(RoundingMode.HALF_UP);
        this.graphPath = new Path();
        this.missPath = new Path();
    }

    public TimeValue getItem(int i) {
        TmpHum item = this.adapter.getItem(i);
        if (item != null) {
            return new TimeValue(item.time, item.hum);
        }
        return new TimeValue(0, 0);
    }

    public float getAvg() {
        return this.adapter.avgHum;
    }

    public int getMaxTarget() {
        return this.adapter.getMaxTargetHum();
    }

    public int getMinTarget() {
        return this.adapter.getMinTargetHum();
    }

    public int getAMax() {
        return this.adapter.aMaxHum;
    }

    public int getAMin() {
        return this.adapter.aMinHum;
    }

    public int getMax() {
        return this.adapter.maxHum;
    }

    public int getMin() {
        return this.adapter.minHum;
    }

    public String formatValue(float f) {
        if (f == -32768.0f) {
            return "--%";
        }
        return this.format.format((double) (f / 100.0f)) + "%";
    }

    public String formatValue2(float f, float f2) {
        if (f == -32768.0f) {
            return "--%";
        }
        return this.format2.format((double) ((((float) Math.round(f / 10.0f)) * f2) / 10.0f)) + "%";
    }

    public Path getGraphPath() {
        return this.graphPath;
    }

    public Path getMissPath() {
        return this.missPath;
    }

    public int getDefaultColor() {
        return ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_graph_hum);
    }
}
