package com.eternal.account.model;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import com.eternal.base.concat.TimeZone;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;

public class SearchItemModel {
    public MutableLiveData<String> desc = new MutableLiveData<>();
    public ObservableBoolean isSelect = new ObservableBoolean();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<Void> onItem = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(SearchItemModel.this, SearchModel.ItemClick);
        }
    });
    public TimeZone timeZone;

    SearchItemModel(TimeZone timeZone2) {
        this.timeZone = timeZone2;
        this.name.setValue(timeZone2.timeZone);
        this.desc.setValue(timeZone2.timeGmt);
        this.isSelect.set(timeZone2.isSelect);
    }
}
