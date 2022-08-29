package com.eternal.base;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import java.util.concurrent.Executor;

public class LivePagedBuilder<Key, Value> {
    private PagedList.BoundaryCallback mBoundaryCallback;
    private PagedList.C0510Config mConfig;
    private DataSource.Factory<Key, Value> mDataSourceFactory;
    private Executor mFetchExecutor;
    private Key mInitialLoadKey;

    public LivePagedBuilder(DataSource.Factory<Key, Value> factory, PagedList.C0510Config config) {
        this.mFetchExecutor = ArchTaskExecutor.getIOThreadExecutor();
        if (config == null) {
            throw new IllegalArgumentException("PagedList.Config must be provided");
        } else if (factory != null) {
            this.mDataSourceFactory = factory;
            this.mConfig = config;
        } else {
            throw new IllegalArgumentException("DataSource.Factory must be provided");
        }
    }

    public LivePagedBuilder(DataSource.Factory<Key, Value> factory, int i) {
        this(factory, new PagedList.C0510Config.Builder().setPageSize(i).build());
    }

    public LivePagedBuilder<Key, Value> setInitialLoadKey(Key key) {
        this.mInitialLoadKey = key;
        return this;
    }

    public LivePagedBuilder<Key, Value> setBoundaryCallback(PagedList.BoundaryCallback<Value> boundaryCallback) {
        this.mBoundaryCallback = boundaryCallback;
        return this;
    }

    public LivePagedBuilder<Key, Value> setFetchExecutor(Executor executor) {
        this.mFetchExecutor = executor;
        return this;
    }

    public LiveData<PagedList<Value>> build() {
        return create(this.mInitialLoadKey, this.mConfig, this.mBoundaryCallback, this.mDataSourceFactory, ArchTaskExecutor.getMainThreadExecutor(), this.mFetchExecutor);
    }

    private static <Key, Value> LiveData<PagedList<Value>> create(Key key, PagedList.C0510Config config, PagedList.BoundaryCallback boundaryCallback, DataSource.Factory<Key, Value> factory, Executor executor, Executor executor2) {
        final Key key2 = key;
        final DataSource.Factory<Key, Value> factory2 = factory;
        final PagedList.C0510Config config2 = config;
        final Executor executor3 = executor;
        final Executor executor4 = executor2;
        final PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        return new ComputableLiveData<PagedList<Value>>(executor2) {
            private final DataSource.InvalidatedCallback mCallback = new DataSource.InvalidatedCallback() {
                public void onInvalidated() {
                    C13131.this.invalidate();
                }
            };
            private DataSource<Key, Value> mDataSource;
            private PagedList<Value> mList;

            /* access modifiers changed from: protected */
            public PagedList<Value> compute() {
                PagedList<Value> build;
                Object obj = key2;
                do {
                    DataSource<Key, Value> dataSource = this.mDataSource;
                    if (dataSource != null) {
                        dataSource.removeInvalidatedCallback(this.mCallback);
                    }
                    DataSource<Key, Value> create = factory2.create();
                    this.mDataSource = create;
                    create.addInvalidatedCallback(this.mCallback);
                    build = new PagedList.Builder(this.mDataSource, config2).setNotifyExecutor(executor3).setFetchExecutor(executor4).setBoundaryCallback(boundaryCallback2).setInitialKey(obj).build();
                    this.mList = build;
                } while (build.isDetached());
                return this.mList;
            }
        }.getLiveData();
    }
}
