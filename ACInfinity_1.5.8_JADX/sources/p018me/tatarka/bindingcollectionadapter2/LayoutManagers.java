package p018me.tatarka.bindingcollectionadapter2;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: me.tatarka.bindingcollectionadapter2.LayoutManagers */
public class LayoutManagers {

    /* renamed from: me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory */
    public interface LayoutManagerFactory {
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: me.tatarka.bindingcollectionadapter2.LayoutManagers$Orientation */
    public @interface Orientation {
    }

    protected LayoutManagers() {
    }

    public static LayoutManagerFactory linear() {
        return new LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    public static LayoutManagerFactory linear(final int i, final boolean z) {
        return new LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext(), i, z);
            }
        };
    }

    public static LayoutManagerFactory grid(final int i) {
        return new LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), i);
            }
        };
    }

    public static LayoutManagerFactory grid(final int i, final int i2, final boolean z) {
        return new LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), i, i2, z);
            }
        };
    }

    public static LayoutManagerFactory staggeredGrid(final int i, final int i2) {
        return new LayoutManagerFactory() {
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new StaggeredGridLayoutManager(i, i2);
            }
        };
    }
}
