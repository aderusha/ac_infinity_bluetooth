package com.eternal.log.adapter;

import android.view.ViewGroup;
import android.widget.TextView;
import com.eternal.base.database.entity.Log;
import com.eternal.log.C2303R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogCHeaderHolder extends LogCHolder {
    private TextView month;

    public LogCHeaderHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
        this.month = (TextView) this.itemView.findViewById(C2303R.C2306id.tv_month);
    }

    public LogCHeaderHolder(ViewGroup viewGroup) {
        this(viewGroup, C2303R.layout.log_item_header);
    }

    public Date init(Log log, long j, boolean z) {
        Date init = super.init(log, j, z);
        this.month.setText(new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).format(init).toUpperCase());
        return init;
    }
}
