package com.eternal.framework.binding.viewadapter.recyclerview;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.recyclerview.LineManagers;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.subjects.PublishSubject;

public class ViewAdapter {
    public static void setLineManager(RecyclerView recyclerView, LineManagers.LineManagerFactory lineManagerFactory) {
        recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView));
    }

    public static void onScrollChangeCommand(RecyclerView recyclerView, final BindingCommand<ScrollDataWrapper> bindingCommand, final BindingCommand<Integer> bindingCommand2) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state;

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(new ScrollDataWrapper((float) i, (float) i2, this.state));
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                this.state = i;
                BindingCommand bindingCommand = bindingCommand2;
                if (bindingCommand != null) {
                    bindingCommand.execute(Integer.valueOf(i));
                }
            }
        });
    }

    public static void onLoadMoreCommand(RecyclerView recyclerView, BindingCommand<Integer> bindingCommand) {
        recyclerView.addOnScrollListener(new OnScrollListener(bindingCommand));
    }

    public static void setItemAnimator(RecyclerView recyclerView, RecyclerView.ItemAnimator itemAnimator) {
        recyclerView.setItemAnimator(itemAnimator);
    }

    public static void setItemTouchHelper(RecyclerView recyclerView, ItemTouchHelper itemTouchHelper) {
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public static class OnScrollListener extends RecyclerView.OnScrollListener {
        private PublishSubject<Integer> methodInvoke;
        private BindingCommand<Integer> onLoadMoreCommand;

        public OnScrollListener(final BindingCommand<Integer> bindingCommand) {
            PublishSubject<Integer> create = PublishSubject.create();
            this.methodInvoke = create;
            this.onLoadMoreCommand = bindingCommand;
            create.throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
                public void accept(Integer num) throws Exception {
                    bindingCommand.execute(num);
                }
            });
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int childCount = linearLayoutManager.getChildCount();
            if (childCount + linearLayoutManager.findFirstVisibleItemPosition() >= linearLayoutManager.getItemCount() && this.onLoadMoreCommand != null) {
                this.methodInvoke.onNext(Integer.valueOf(recyclerView.getAdapter().getItemCount()));
            }
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }
    }

    public static class ScrollDataWrapper {
        public float scrollX;
        public float scrollY;
        public int state;

        public ScrollDataWrapper(float f, float f2, int i) {
            this.scrollX = f;
            this.scrollY = f2;
            this.state = i;
        }
    }
}
