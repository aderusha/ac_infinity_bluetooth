package com.eternal.framework.binding.viewadapter.recyclerview;

import androidx.recyclerview.widget.RecyclerView;
import com.eternal.framework.binding.viewadapter.recyclerview.DividerLine;

public class LineManagers {

    public interface LineManagerFactory {
        RecyclerView.ItemDecoration create(RecyclerView recyclerView);
    }

    protected LineManagers() {
    }

    public static LineManagerFactory both() {
        return new LineManagerFactory() {
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.BOTH);
            }
        };
    }

    public static LineManagerFactory horizontal() {
        return new LineManagerFactory() {
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.HORIZONTAL);
            }
        };
    }

    public static LineManagerFactory vertical() {
        return new LineManagerFactory() {
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.VERTICAL);
            }
        };
    }
}
