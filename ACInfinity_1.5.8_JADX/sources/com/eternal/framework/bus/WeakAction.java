package com.eternal.framework.bus;

import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import java.lang.ref.WeakReference;

public class WeakAction<T> {
    private BindingAction action;
    private BindingConsumer<T> consumer;
    private boolean isLive;
    private WeakReference reference;
    private Object target;

    public WeakAction(Object obj, BindingAction bindingAction) {
        this.reference = new WeakReference(obj);
        this.action = bindingAction;
    }

    public WeakAction(Object obj, BindingConsumer<T> bindingConsumer) {
        this.reference = new WeakReference(obj);
        this.consumer = bindingConsumer;
    }

    public void execute() {
        if (this.action != null && isLive()) {
            this.action.call();
        }
    }

    public void execute(T t) {
        if (this.consumer != null && isLive()) {
            this.consumer.call(t);
        }
    }

    public void markForDeletion() {
        this.reference.clear();
        this.reference = null;
        this.action = null;
        this.consumer = null;
    }

    public BindingAction getBindingAction() {
        return this.action;
    }

    public BindingConsumer getBindingConsumer() {
        return this.consumer;
    }

    public boolean isLive() {
        WeakReference weakReference = this.reference;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        return true;
    }

    public Object getTarget() {
        WeakReference weakReference = this.reference;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
