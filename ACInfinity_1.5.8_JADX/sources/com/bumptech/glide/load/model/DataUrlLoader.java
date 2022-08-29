package com.bumptech.glide.load.model;

import android.util.Base64;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class DataUrlLoader<Model, Data> implements ModelLoader<Model, Data> {
    private static final String BASE64_TAG = ";base64";
    private static final String DATA_SCHEME_IMAGE = "data:image";
    private final DataDecoder<Data> dataDecoder;

    public interface DataDecoder<Data> {
        void close(Data data) throws IOException;

        Data decode(String str) throws IllegalArgumentException;

        Class<Data> getDataClass();
    }

    public DataUrlLoader(DataDecoder<Data> dataDecoder2) {
        this.dataDecoder = dataDecoder2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(Model model, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new DataUriFetcher(model.toString(), this.dataDecoder));
    }

    public boolean handles(Model model) {
        return model.toString().startsWith(DATA_SCHEME_IMAGE);
    }

    private static final class DataUriFetcher<Data> implements DataFetcher<Data> {
        private Data data;
        private final String dataUri;
        private final DataDecoder<Data> reader;

        public void cancel() {
        }

        DataUriFetcher(String str, DataDecoder<Data> dataDecoder) {
            this.dataUri = str;
            this.reader = dataDecoder;
        }

        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                Data decode = this.reader.decode(this.dataUri);
                this.data = decode;
                dataCallback.onDataReady(decode);
            } catch (IllegalArgumentException e) {
                dataCallback.onLoadFailed(e);
            }
        }

        public void cleanup() {
            try {
                this.reader.close(this.data);
            } catch (IOException unused) {
            }
        }

        public Class<Data> getDataClass() {
            return this.reader.getDataClass();
        }

        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static final class StreamFactory<Model> implements ModelLoaderFactory<Model, InputStream> {
        private final DataDecoder<InputStream> opener = new DataDecoder<InputStream>() {
            public InputStream decode(String str) {
                if (str.startsWith(DataUrlLoader.DATA_SCHEME_IMAGE)) {
                    int indexOf = str.indexOf(44);
                    if (indexOf == -1) {
                        throw new IllegalArgumentException("Missing comma in data URL.");
                    } else if (str.substring(0, indexOf).endsWith(DataUrlLoader.BASE64_TAG)) {
                        return new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 1), 0));
                    } else {
                        throw new IllegalArgumentException("Not a base64 image data URL.");
                    }
                } else {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
            }

            public void close(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            public Class<InputStream> getDataClass() {
                return InputStream.class;
            }
        };

        public void teardown() {
        }

        public ModelLoader<Model, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DataUrlLoader(this.opener);
        }
    }
}
