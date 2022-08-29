package com.eternal.log.adapter;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.base.database.entity.Log;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.log.C2303R;
import com.google.common.base.Ascii;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogHolder extends RecyclerView.ViewHolder {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("h:mm aa", Locale.ENGLISH);
    private TextView content;
    private TextView time;

    public LogHolder(ViewGroup viewGroup) {
        this(viewGroup, C2303R.layout.log_item_content);
    }

    public LogHolder(ViewGroup viewGroup, int i) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        this.time = (TextView) this.itemView.findViewById(C2303R.C2306id.tv_log_time);
        this.content = (TextView) this.itemView.findViewById(C2303R.C2306id.tv_log_info);
    }

    public Date init(Log log, long j, SparseArray<String> sparseArray, boolean z, boolean z2) {
        String str;
        if (log.time > j) {
            this.time.setTextColor(-15353089);
            this.content.setTextColor(-15353089);
        } else {
            this.time.setTextColor(-1);
            this.content.setTextColor(-1);
        }
        Date date = new Date(log.time);
        this.time.setText(FORMAT.format(date));
        if (log.logType > 0) {
            str = ProtocolTransformer.getLogString(log, sparseArray.get(log.notifyId + (log.logType * 1000) + (log.port * Ascii.DLE)), z, z2);
        } else {
            str = ProtocolTransformer.getLogString(log, (String) null, z, z2);
        }
        String str2 = sparseArray.get(log.port);
        if (!TextUtils.isEmpty(str2)) {
            str = str2 + str;
        }
        this.content.setText(str);
        return date;
    }
}
