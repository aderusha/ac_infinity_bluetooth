package com.eternal.log.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.eternal.base.concat.LogExtra;
import com.eternal.base.database.entity.Log;
import com.eternal.log.C2303R;
import java.util.Calendar;

public class LogAdapter extends PagedListAdapter<Log, LogHolder> {
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
    private boolean hideAutoTime;
    private boolean isDegree;
    private Calendar last = Calendar.getInstance();
    private SparseArray<String> notifyCache;
    private long start;

    public long getItemId(int i) {
        return (long) i;
    }

    public LogAdapter(LogExtra logExtra) {
        super(diff);
        this.hideAutoTime = logExtra.hideAutoTime;
        setExtra(logExtra);
    }

    public void setExtra(LogExtra logExtra) {
        if (this.isDegree != logExtra.isDegree) {
            this.isDegree = logExtra.isDegree;
            notifyItemRangeChanged(0, getItemCount());
        }
        if (!(this.notifyCache == null || logExtra.notifyName == null || this.notifyCache.toString().equals(logExtra.notifyName.toString()))) {
            notifyItemRangeChanged(0, getItemCount());
        }
        this.notifyCache = logExtra.notifyName;
        this.start = logExtra.time;
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
            this.end = getItem(i).time;
            return 1;
        }
        this.first.setTimeInMillis(getItem(i).time);
        this.last.setTimeInMillis(getItem(i - 1).time);
        if (this.first.get(5) != this.last.get(5) || this.first.get(2) != this.last.get(2) || this.first.get(1) != this.last.get(1)) {
            return 3;
        }
        if (i == getItemCount() - 1) {
            return 2;
        }
        return 0;
    }

    public LogHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new LogHeaderHolder(viewGroup);
        }
        if (i == 2) {
            return new LogHolder(viewGroup, C2303R.layout.log_item_foobar);
        }
        if (i != 3) {
            return new LogHolder(viewGroup);
        }
        return new LogHeaderHolder(viewGroup, C2303R.layout.log_item_foobar_header);
    }

    public void onBindViewHolder(LogHolder logHolder, int i) {
        logHolder.init(getItem(i), this.start, this.notifyCache, this.isDegree, this.hideAutoTime);
    }

    public boolean isClear() {
        return this.start >= this.end;
    }

    public void clear(Long l) {
        this.start = l.longValue();
        notifyItemRangeChanged(0, getItemCount());
    }

    public Log getItem(int i) {
        return (Log) super.getItem(i);
    }
}
