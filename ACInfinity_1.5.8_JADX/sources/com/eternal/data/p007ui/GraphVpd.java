package com.eternal.data.p007ui;

import android.graphics.Path;
import androidx.core.content.ContextCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.data.C1835R;
import com.eternal.data.p007ui.GraphView;
import com.eternal.framework.utils.Utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

/* renamed from: com.eternal.data.ui.GraphVpd */
public class GraphVpd implements GraphView.IGraphDelegate {
    private GraphAdapter adapter;
    private DecimalFormat format = new DecimalFormat("0.00");
    private DecimalFormat format2 = new DecimalFormat("0.0");
    private Path graphPath;
    private Path missPath;

    public GraphVpd(GraphAdapter graphAdapter) {
        this.adapter = graphAdapter;
        this.format.setRoundingMode(RoundingMode.HALF_UP);
        this.format2.setRoundingMode(RoundingMode.HALF_UP);
        this.graphPath = new Path();
        this.missPath = new Path();
    }

    public TimeValue getItem(int i) {
        TmpHum item = this.adapter.getItem(i);
        if (item != null) {
            return new TimeValue(item.time, item.vpd);
        }
        return new TimeValue(0, 0);
    }

    public float getAvg() {
        return this.adapter.avgVpd;
    }

    public int getMaxTarget() {
        return this.adapter.getMaxTargetVpd();
    }

    public int getMinTarget() {
        return this.adapter.getMinTargetVpd();
    }

    public int getAMax() {
        return this.adapter.aMaxVpd;
    }

    public int getAMin() {
        return this.adapter.aMinVpd;
    }

    public int getMax() {
        return this.adapter.maxVpd;
    }

    public int getMin() {
        return this.adapter.minVpd;
    }

    public String formatValue(float f) {
        if (f == -32768.0f) {
            return "--kPa";
        }
        return String.format(Locale.ENGLISH, "%.2fkPa", new Object[]{Float.valueOf(f / 100.0f)});
    }

    public String formatValue2(float f, float f2) {
        if (f == -32768.0f) {
            return "--kPa";
        }
        return String.format(Locale.ENGLISH, "%.1fkPa", new Object[]{Float.valueOf((((float) Math.round(f)) * f2) / 100.0f)});
    }

    public Path getGraphPath() {
        return this.graphPath;
    }

    public Path getMissPath() {
        return this.missPath;
    }

    public int getDefaultColor() {
        return ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_graph_vpd);
    }
}
