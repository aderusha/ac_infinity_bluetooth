package p018me.tatarka.bindingcollectionadapter2.itembindings;

import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.OnItemBind;
import p018me.tatarka.bindingcollectionadapter2.itembindings.ItemBindingModel;

/* renamed from: me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindModel */
public class OnItemBindModel<T extends ItemBindingModel> implements OnItemBind<T> {
    public void onItemBind(ItemBinding itemBinding, int i, T t) {
        t.onItemBind(itemBinding);
    }
}
