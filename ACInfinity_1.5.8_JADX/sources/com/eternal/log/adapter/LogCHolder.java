package com.eternal.log.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.base.database.entity.Log;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.log.C2303R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogCHolder extends RecyclerView.ViewHolder {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("h:mm aa", Locale.ENGLISH);
    private TextView content;
    private TextView time;

    public LogCHolder(ViewGroup viewGroup) {
        this(viewGroup, C2303R.layout.log_item_content);
    }

    public LogCHolder(ViewGroup viewGroup, int i) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        this.time = (TextView) this.itemView.findViewById(C2303R.C2306id.tv_log_time);
        this.content = (TextView) this.itemView.findViewById(C2303R.C2306id.tv_log_info);
    }

    public Date init(Log log, long j, boolean z) {
        if (log.time > j) {
            this.time.setTextColor(-15353089);
            this.content.setTextColor(-15353089);
        } else {
            this.time.setTextColor(-1);
            this.content.setTextColor(-1);
        }
        Date date = new Date(log.time);
        this.time.setText(FORMAT.format(date));
        this.content.setText(ProtocolTransformer.getLogCString(log, z));
        return date;
    }
}
