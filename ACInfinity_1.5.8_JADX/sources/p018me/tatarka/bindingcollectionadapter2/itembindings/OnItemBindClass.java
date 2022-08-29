package p018me.tatarka.bindingcollectionadapter2.itembindings;

import java.util.ArrayList;
import java.util.List;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.OnItemBind;

/* renamed from: me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass */
public class OnItemBindClass<T> implements OnItemBind<T> {
    private final List<Class<? extends T>> itemBindingClassList = new ArrayList(2);
    private final List<OnItemBind<? extends T>> itemBindingList = new ArrayList(2);

    public OnItemBindClass<T> map(Class<? extends T> cls, int i, int i2) {
        int indexOf = this.itemBindingClassList.indexOf(cls);
        if (indexOf >= 0) {
            this.itemBindingList.set(indexOf, itemBind(i, i2));
        } else {
            this.itemBindingClassList.add(cls);
            this.itemBindingList.add(itemBind(i, i2));
        }
        return this;
    }

    public <E extends T> OnItemBindClass<T> map(Class<E> cls, OnItemBind<E> onItemBind) {
        int indexOf = this.itemBindingClassList.indexOf(cls);
        if (indexOf >= 0) {
            this.itemBindingList.set(indexOf, onItemBind);
        } else {
            this.itemBindingClassList.add(cls);
            this.itemBindingList.add(onItemBind);
        }
        return this;
    }

    public int itemTypeCount() {
        return this.itemBindingClassList.size();
    }

    public void onItemBind(ItemBinding itemBinding, int i, T t) {
        for (int i2 = 0; i2 < this.itemBindingClassList.size(); i2++) {
            if (this.itemBindingClassList.get(i2).isInstance(t)) {
                this.itemBindingList.get(i2).onItemBind(itemBinding, i, t);
                return;
            }
        }
        throw new IllegalArgumentException("Missing class for item " + t);
    }

    private OnItemBind<T> itemBind(final int i, final int i2) {
        return new OnItemBind<T>() {
            public void onItemBind(ItemBinding itemBinding, int i, T t) {
                itemBinding.set(i, i2);
            }
        };
    }
}
