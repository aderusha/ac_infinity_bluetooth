package com.eternal.account.model;

import androidx.databinding.ObservableField;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;

public class PhotoItemModel {
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(PhotoItemModel.this, "delete item");
        }
    });
    public ObservableField<String> thumbUrl = new ObservableField<>();

    public PhotoItemModel(String str) {
        this.thumbUrl.set(str);
    }
}
