package androidx.databinding;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class MergedDataBinderMapper extends DataBinderMapper {
    private static final String TAG = "MergedDataBinderMapper";
    private Set<Class<? extends DataBinderMapper>> mExistingMappers = new HashSet();
    private List<String> mFeatureBindingMappers = new CopyOnWriteArrayList();
    private List<DataBinderMapper> mMappers = new CopyOnWriteArrayList();

    public void addMapper(DataBinderMapper dataBinderMapper) {
        if (this.mExistingMappers.add(dataBinderMapper.getClass())) {
            this.mMappers.add(dataBinderMapper);
            for (DataBinderMapper addMapper : dataBinderMapper.collectDependencies()) {
                addMapper(addMapper);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addMapper(String str) {
        List<String> list = this.mFeatureBindingMappers;
        list.add(str + ".DataBinderMapperImpl");
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        for (DataBinderMapper dataBinder : this.mMappers) {
            ViewDataBinding dataBinder2 = dataBinder.getDataBinder(dataBindingComponent, view, i);
            if (dataBinder2 != null) {
                return dataBinder2;
            }
        }
        if (loadFeatures()) {
            return getDataBinder(dataBindingComponent, view, i);
        }
        return null;
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        for (DataBinderMapper dataBinder : this.mMappers) {
            ViewDataBinding dataBinder2 = dataBinder.getDataBinder(dataBindingComponent, viewArr, i);
            if (dataBinder2 != null) {
                return dataBinder2;
            }
        }
        if (loadFeatures()) {
            return getDataBinder(dataBindingComponent, viewArr, i);
        }
        return null;
    }

    public int getLayoutId(String str) {
        for (DataBinderMapper layoutId : this.mMappers) {
            int layoutId2 = layoutId.getLayoutId(str);
            if (layoutId2 != 0) {
                return layoutId2;
            }
        }
        if (loadFeatures()) {
            return getLayoutId(str);
        }
        return 0;
    }

    public String convertBrIdToString(int i) {
        for (DataBinderMapper convertBrIdToString : this.mMappers) {
            String convertBrIdToString2 = convertBrIdToString.convertBrIdToString(i);
            if (convertBrIdToString2 != null) {
                return convertBrIdToString2;
            }
        }
        if (loadFeatures()) {
            return convertBrIdToString(i);
        }
        return null;
    }

    private boolean loadFeatures() {
        boolean z = false;
        for (String next : this.mFeatureBindingMappers) {
            try {
                Class<?> cls = Class.forName(next);
                if (DataBinderMapper.class.isAssignableFrom(cls)) {
                    addMapper((DataBinderMapper) cls.newInstance());
                    this.mFeatureBindingMappers.remove(next);
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            } catch (IllegalAccessException e) {
                Log.e(TAG, "unable to add feature mapper for " + next, e);
            } catch (InstantiationException e2) {
                Log.e(TAG, "unable to add feature mapper for " + next, e2);
            }
        }
        return z;
    }
}
