package com.eternal.log.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.eternal.base.concat.LogExtra;
import com.eternal.base.database.entity.Log;
import com.eternal.log.C2303R;
import java.util.Calendar;

public class LogCAdapter extends PagedListAdapter<Log, LogCHolder> {
    private static final int CONTENT = 0;
    private static final int FOOBAR = 2;
    private static final int FOOBAR_HEADER = 3;
    private static final int HEADER = 1;
    private static DiffUtil.ItemCallback<Log> diff = new DiffUtil.ItemCallback<Log>() {
        public boolean areItemsTheSame(Log log, Log log2) {
            return log.f142id == log2.f142id && log.time == log2.time && log.model == log2.model;
        }

        public boolean areContentsTheSame(Log log, Log log2) {
            return log.f142id == log2.f142id && log.time == log2.time && log.model == log2.model;
        }
    };
    private long end;
    private Calendar first = Calendar.getInstance();
    private boolean isDegree;
    private Calendar last = Calendar.getInstance();
    private SparseArray<String> notifyCache;
    private long start;

    public long getItemId(int i) {
        return (long) i;
    }

    public LogCAdapter(LogExtra logExtra) {
        super(diff);
        setExtra(logExtra);
    }

    public void setExtra(LogExtra logExtra) {
        if (this.isDegree != logExtra.isDegree) {
            this.isDegree = logExtra.isDegree;
            notifyItemRangeChanged(0, getItemCount());
        }
        this.notifyCache = logExtra.notifyName;
        if (logExtra.time == 0) {
            this.start = System.currentTimeMillis();
        } else {
            this.start = logExtra.time;
        }
    }

    public void setDegree(boolean z) {
        if (this.isDegree != z) {
            this.isDegree = z;
            notifyItemRangeChanged(0, getItemCount());
        }
    }

    public long getStart() {
        return this.start;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            this.end = ((Log) getItem(i)).time;
            return 1;
        }
        this.first.setTimeInMillis(((Log) getItem(i)).time);
        this.last.setTimeInMillis(((Log) getItem(i - 1)).time);
        if (this.first.get(5) != this.last.get(5) || this.first.get(2) != this.last.get(2) || this.first.get(1) != this.last.get(1)) {
            return 3;
        }
        if (i == getItemCount() - 1) {
            return 2;
        }
        return 0;
    }

    public LogCHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new LogCHeaderHolder(viewGroup);
        }
        if (i == 2) {
            return new LogCHolder(viewGroup, C2303R.layout.log_item_foobar);
        }
        if (i != 3) {
            return new LogCHolder(viewGroup);
        }
        return new LogCHeaderHolder(viewGroup, C2303R.layout.log_item_foobar_header);
    }

    public void onBindViewHolder(LogCHolder logCHolder, int i) {
        logCHolder.init((Log) getItem(i), this.start, this.isDegree);
    }

    public boolean isClear() {
        return this.start >= this.end;
    }

    public void clear(Long l) {
        if (l.longValue() == 0) {
            this.start = System.currentTimeMillis();
        } else {
            this.start = l.longValue();
        }
        notifyItemRangeChanged(0, getItemCount());
    }
}
