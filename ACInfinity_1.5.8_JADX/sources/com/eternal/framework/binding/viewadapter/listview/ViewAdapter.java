package com.eternal.framework.binding.viewadapter.listview;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.eternal.framework.binding.command.BindingCommand;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.subjects.PublishSubject;

public final class ViewAdapter {
    public static void onScrollChangeCommand(ListView listView, final BindingCommand<ListViewScrollDataWrapper> bindingCommand, final BindingCommand<Integer> bindingCommand2) {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            public void onScrollStateChanged(AbsListView absListView, int i) {
                this.scrollState = i;
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(Integer.valueOf(i));
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                BindingCommand bindingCommand = bindingCommand;
                if (bindingCommand != null) {
                    bindingCommand.execute(new ListViewScrollDataWrapper(this.scrollState, i, i2, i3));
                }
            }
        });
    }

    public static void onItemClickCommand(ListView listView, final BindingCommand<Integer> bindingCommand) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute(Integer.valueOf(i));
                }
            }
        });
    }

    public static void onLoadMoreCommand(ListView listView, BindingCommand<Integer> bindingCommand) {
        listView.setOnScrollListener(new OnScrollListener(listView, bindingCommand));
    }

    public static class OnScrollListener implements AbsListView.OnScrollListener {
        private ListView listView;
        private PublishSubject<Integer> methodInvoke;
        private BindingCommand<Integer> onLoadMoreCommand;

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public OnScrollListener(ListView listView2, final BindingCommand<Integer> bindingCommand) {
            PublishSubject<Integer> create = PublishSubject.create();
            this.methodInvoke = create;
            this.onLoadMoreCommand = bindingCommand;
            this.listView = listView2;
            create.throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
                public void accept(Integer num) throws Exception {
                    bindingCommand.execute(num);
                }
            });
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i + i2 >= i3 && i3 != 0 && i3 != this.listView.getHeaderViewsCount() + this.listView.getFooterViewsCount() && this.onLoadMoreCommand != null) {
                this.methodInvoke.onNext(Integer.valueOf(i3));
            }
        }
    }

    public static class ListViewScrollDataWrapper {
        public int firstVisibleItem;
        public int scrollState;
        public int totalItemCount;
        public int visibleItemCount;

        public ListViewScrollDataWrapper(int i, int i2, int i3, int i4) {
            this.firstVisibleItem = i2;
            this.visibleItemCount = i3;
            this.totalItemCount = i4;
            this.scrollState = i;
        }
    }
}
