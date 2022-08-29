package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.TimeZone;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class SearchModel extends BaseViewModel implements Filterable {
    public static final String ItemClick = "item click";
    private String devId;
    /* access modifiers changed from: private */
    public String devTimeZone;
    public final ItemBinding<SearchItemModel> itemBinding = ItemBinding.m1008of(C0977BR.itemSearch, C0997R.layout.item_search);
    public ObservableList<SearchItemModel> items = new ObservableArrayList();
    private ArrayFilter mFilter;
    public ObservableList<SearchItemModel> mOriginalItems = new ObservableArrayList();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SearchModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public MutableLiveData<Boolean> showEmpty = new MutableLiveData<>();
    public BindingCommand<String> textChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            SearchModel.this.getFilter().filter(str);
        }
    });

    public void deleteShare(String str) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
    }

    public SearchModel(Application application) {
        super(application);
    }

    public void init(String str, String str2) {
        this.devId = str;
        this.devTimeZone = str2;
        initMessage();
        fetchTimeZone();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) ItemClick, SearchItemModel.class, new BindingConsumer<SearchItemModel>() {
            public void call(SearchItemModel searchItemModel) {
                SearchModel.this.putTimeZone(searchItemModel.timeZone.timeZone);
            }
        });
    }

    private void fetchTimeZone() {
        ApiHelper.getDeviceApi().getDevTimeZone().subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<List<TimeZone>>() {
            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public String setTag() {
                Class<SearchModel> cls = SearchModel.class;
                return "SearchModel";
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                SearchModel.this.showFailDialog(str);
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
            }

            /* access modifiers changed from: protected */
            public void onSuccess(List<TimeZone> list) {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                if (list == null || list.size() == 0) {
                    list = new ArrayList<>();
                }
                ArrayList arrayList = new ArrayList();
                for (TimeZone next : list) {
                    if (!TextUtils.isEmpty(SearchModel.this.devTimeZone)) {
                        next.isSelect = SearchModel.this.devTimeZone.equalsIgnoreCase(next.timeZone);
                    } else {
                        next.isSelect = false;
                    }
                    arrayList.add(new SearchItemModel(next));
                }
                SearchModel.this.mOriginalItems.clear();
                SearchModel.this.mOriginalItems.addAll(arrayList);
                SearchModel.this.items.clear();
                SearchModel.this.items.addAll(arrayList);
            }
        });
    }

    /* access modifiers changed from: private */
    public void putTimeZone(String str) {
        ApiHelper.getDeviceApi().putDevTimeZone(this.devId, str).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public String setTag() {
                Class<SearchModel> cls = SearchModel.class;
                return "SearchModel";
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                SearchModel.this.showFailDialog(str);
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                SearchModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Messenger.getDefault().unregister(this);
    }

    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter();
        }
        return this.mFilter;
    }

    private class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = SearchModel.this.mOriginalItems;
                filterResults.count = SearchModel.this.mOriginalItems.size();
            } else {
                String lowerCase = charSequence.toString().toLowerCase();
                int size = SearchModel.this.mOriginalItems.size();
                ObservableArrayList observableArrayList = new ObservableArrayList();
                for (int i = 0; i < size; i++) {
                    SearchItemModel searchItemModel = (SearchItemModel) SearchModel.this.mOriginalItems.get(i);
                    String lowerCase2 = searchItemModel.timeZone.timeZone.toLowerCase();
                    if (lowerCase2.contains(lowerCase)) {
                        observableArrayList.add(searchItemModel);
                    } else {
                        String[] split = lowerCase2.split(" ");
                        int length = split.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            } else if (split[i2].contains(lowerCase)) {
                                observableArrayList.add(searchItemModel);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                }
                filterResults.values = observableArrayList;
                filterResults.count = observableArrayList.size();
            }
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            SearchModel.this.items.clear();
            SearchModel.this.items.addAll((ObservableArrayList) filterResults.values);
        }
    }
}
