package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.LogTime;
import java.util.Collections;
import java.util.List;

class SourceGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object>, DataFetcherGenerator.FetcherReadyCallback {
    private static final String TAG = "SourceGenerator";

    /* renamed from: cb */
    private final DataFetcherGenerator.FetcherReadyCallback f88cb;
    private Object dataToCache;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int loadDataListIndex;
    private DataCacheKey originalKey;
    private DataCacheGenerator sourceCacheGenerator;

    SourceGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.f88cb = fetcherReadyCallback;
    }

    public boolean startNext() {
        Object obj = this.dataToCache;
        if (obj != null) {
            this.dataToCache = null;
            cacheData(obj);
        }
        DataCacheGenerator dataCacheGenerator = this.sourceCacheGenerator;
        if (dataCacheGenerator != null && dataCacheGenerator.startNext()) {
            return true;
        }
        this.sourceCacheGenerator = null;
        this.loadData = null;
        boolean z = false;
        while (!z && hasNextModelLoader()) {
            List<ModelLoader.LoadData<?>> loadData2 = this.helper.getLoadData();
            int i = this.loadDataListIndex;
            this.loadDataListIndex = i + 1;
            this.loadData = loadData2.get(i);
            if (this.loadData != null && (this.helper.getDiskCacheStrategy().isDataCacheable(this.loadData.fetcher.getDataSource()) || this.helper.hasLoadPath(this.loadData.fetcher.getDataClass()))) {
                this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                z = true;
            }
        }
        return z;
    }

    private boolean hasNextModelLoader() {
        return this.loadDataListIndex < this.helper.getLoadData().size();
    }

    /* JADX INFO: finally extract failed */
    private void cacheData(Object obj) {
        long logTime = LogTime.getLogTime();
        try {
            Encoder<X> sourceEncoder = this.helper.getSourceEncoder(obj);
            DataCacheWriter dataCacheWriter = new DataCacheWriter(sourceEncoder, obj, this.helper.getOptions());
            this.originalKey = new DataCacheKey(this.loadData.sourceKey, this.helper.getSignature());
            this.helper.getDiskCache().put(this.originalKey, dataCacheWriter);
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Finished encoding source to cache, key: " + this.originalKey + ", data: " + obj + ", encoder: " + sourceEncoder + ", duration: " + LogTime.getElapsedMillis(logTime));
            }
            this.loadData.fetcher.cleanup();
            this.sourceCacheGenerator = new DataCacheGenerator(Collections.singletonList(this.loadData.sourceKey), this.helper, this);
        } catch (Throwable th) {
            this.loadData.fetcher.cleanup();
            throw th;
        }
    }

    public void cancel() {
        ModelLoader.LoadData<?> loadData2 = this.loadData;
        if (loadData2 != null) {
            loadData2.fetcher.cancel();
        }
    }

    public void onDataReady(Object obj) {
        DiskCacheStrategy diskCacheStrategy = this.helper.getDiskCacheStrategy();
        if (obj == null || !diskCacheStrategy.isDataCacheable(this.loadData.fetcher.getDataSource())) {
            this.f88cb.onDataFetcherReady(this.loadData.sourceKey, obj, this.loadData.fetcher, this.loadData.fetcher.getDataSource(), this.originalKey);
            return;
        }
        this.dataToCache = obj;
        this.f88cb.reschedule();
    }

    public void onLoadFailed(Exception exc) {
        this.f88cb.onDataFetcherFailed(this.originalKey, exc, this.loadData.fetcher, this.loadData.fetcher.getDataSource());
    }

    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.f88cb.onDataFetcherReady(key, obj, dataFetcher, this.loadData.fetcher.getDataSource(), key);
    }

    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.f88cb.onDataFetcherFailed(key, exc, dataFetcher, this.loadData.fetcher.getDataSource());
    }
}
