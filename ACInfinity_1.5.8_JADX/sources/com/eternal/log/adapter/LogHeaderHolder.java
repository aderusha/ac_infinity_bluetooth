package com.eternal.log.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;
import com.eternal.base.database.entity.Log;
import com.eternal.log.C2303R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogHeaderHolder extends LogHolder {
    private TextView month;

    public LogHeaderHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
        this.month = (TextView) this.itemView.findViewById(C2303R.C2306id.tv_month);
    }

    public LogHeaderHolder(ViewGroup viewGroup) {
        this(viewGroup, C2303R.layout.log_item_header);
    }

    public Date init(Log log, long j, SparseArray<String> sparseArray, boolean z, boolean z2) {
        Date init = super.init(log, j, sparseArray, z, z2);
        this.month.setText(new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).format(init).toUpperCase());
        return init;
    }
}
