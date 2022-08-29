package com.eternal.data.p007ui;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.content.ContextCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.data.C1835R;
import com.eternal.data.p007ui.GraphView;
import com.eternal.framework.utils.Utils;
import java.util.Locale;

/* renamed from: com.eternal.data.ui.GraphLevel */
public class GraphLevel implements GraphView.IGraphDelegate {
    private GraphAdapter adapter;
    public Bitmap bitmap;
    public RectF bitmapBounds = new RectF();
    private Path graphPath = new Path();
    public String labelText;
    public String labelText2;
    public boolean merge;
    public boolean mergeShow;
    private Path missPath = new Path();
    public int padding;
    public byte port;
    public RectF scrollerBounds = new RectF();
    public Rect scrollerTextBounds1 = new Rect();
    public Rect scrollerTextBounds2 = new Rect();
    public float scrollerY;
    public boolean showIcon;
    public int value;

    public int getAMax() {
        return 10;
    }

    public int getAMin() {
        return 0;
    }

    public float getAvg() {
        return 0.0f;
    }

    public int getMax() {
        return 10;
    }

    public int getMaxTarget() {
        return 0;
    }

    public int getMin() {
        return 0;
    }

    public int getMinTarget() {
        return 0;
    }

    public GraphLevel(GraphAdapter graphAdapter, byte b, boolean z) {
        this.adapter = graphAdapter;
        this.port = b;
        this.showIcon = z;
    }

    public int getLevel(TmpHum tmpHum) {
        if (this.port == 0) {
            return tmpHum.fan;
        }
        return (int) ((tmpHum.portFan >> ((this.port - 1) * 4)) & 15);
    }

    public boolean getOff(TmpHum tmpHum) {
        if (this.port == 0) {
            return tmpHum.off;
        }
        return ((tmpHum.portState >> (this.port - 1)) & 1) == 0;
    }

    public void setLabelText(TmpHum tmpHum, boolean z, String str) {
        if (!z || getLevel(tmpHum) == 15) {
            String str2 = "OFF";
            if (this.showIcon) {
                this.labelText = str;
                if (!getOff(tmpHum)) {
                    str2 = "ON";
                }
                this.labelText2 = str2;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (!getOff(tmpHum)) {
                str2 = "ON";
            }
            sb.append(str2);
            this.labelText = sb.toString();
        } else if (this.showIcon) {
            this.labelText = str;
            this.labelText2 = String.format(Locale.ENGLISH, "%s", new Object[]{formatValue((float) getLevel(tmpHum))});
        } else {
            this.labelText = str + String.format(Locale.ENGLISH, "%s", new Object[]{formatValue((float) getLevel(tmpHum))});
        }
    }

    public void setScrollerLevel(TmpHum tmpHum, boolean z) {
        this.value = (!z || getLevel(tmpHum) == 15) ? getOff(tmpHum) ? 0 : 10 : getLevel(tmpHum);
    }

    public void setScrollerY(float f) {
        this.scrollerY = f;
    }

    public void setTextBounds(Paint paint, int i, int i2) {
        this.padding = i2;
        String str = this.labelText;
        if (str != null) {
            paint.getTextBounds(str, 0, str.length(), this.scrollerTextBounds1);
            this.scrollerTextBounds1.top = 0;
            this.scrollerTextBounds1.bottom = i;
        }
        String str2 = this.labelText2;
        if (str2 != null) {
            paint.getTextBounds(str2, 0, str2.length(), this.scrollerTextBounds2);
            this.scrollerTextBounds2.top = 0;
            this.scrollerTextBounds2.bottom = i;
        }
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 != null) {
            float f = (float) i;
            this.bitmapBounds.set(0.0f, 0.0f, ((1.0f * f) / ((float) bitmap2.getHeight())) * ((float) this.bitmap.getWidth()), f);
            return;
        }
        this.bitmapBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public float getTextWidth() {
        if (this.showIcon) {
            return ((float) (this.scrollerTextBounds1.width() + this.scrollerTextBounds2.width())) + this.bitmapBounds.width() + ((float) (this.padding * 3));
        }
        return (float) this.scrollerTextBounds1.width();
    }

    public void setIcon(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public TimeValue getItem(int i) {
        TmpHum item = this.adapter.getItem(i);
        if (item != null) {
            return new TimeValue(item.time, getLevel(item));
        }
        return new TimeValue(0, 0);
    }

    public String formatValue(float f) {
        if (f == -32768.0f) {
            return "--";
        }
        return String.format(Locale.ENGLISH, "%.0f", new Object[]{Float.valueOf(f)});
    }

    public String formatValue2(float f, float f2) {
        return formatValue(f);
    }

    public Path getGraphPath() {
        return this.graphPath;
    }

    public Path getMissPath() {
        return this.missPath;
    }

    public int getDefaultColor() {
        int i;
        byte b = this.port;
        if (b == 1) {
            i = C1835R.C1836color.color_graph_level_1;
        } else if (b == 2) {
            i = C1835R.C1836color.color_graph_level_2;
        } else if (b == 3) {
            i = C1835R.C1836color.color_graph_level_3;
        } else if (b != 4) {
            i = C1835R.C1836color.color_graph_level_0;
        } else {
            i = C1835R.C1836color.color_graph_level_4;
        }
        return ContextCompat.getColor(Utils.getContext(), i);
    }
}
