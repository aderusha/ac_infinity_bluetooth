package com.eternal.framework.binding.command;

public class BindingCommand<T> {
    private BindingFunction<Boolean> canExecute0;
    private BindingConsumer<T> consumer;
    private BindingAction execute;

    public BindingCommand(BindingAction bindingAction) {
        this.execute = bindingAction;
    }

    public BindingCommand(BindingConsumer<T> bindingConsumer) {
        this.consumer = bindingConsumer;
    }

    public BindingCommand(BindingAction bindingAction, BindingFunction<Boolean> bindingFunction) {
        this.execute = bindingAction;
        this.canExecute0 = bindingFunction;
    }

    public BindingCommand(BindingConsumer<T> bindingConsumer, BindingFunction<Boolean> bindingFunction) {
        this.consumer = bindingConsumer;
        this.canExecute0 = bindingFunction;
    }

    public BindingCommand() {
    }

    public void execute() {
        if (this.execute != null && canExecute0()) {
            this.execute.call();
        }
    }

    public void execute(T t) {
        if (this.consumer != null && canExecute0()) {
            this.consumer.call(t);
        }
    }

    private boolean canExecute0() {
        BindingFunction<Boolean> bindingFunction = this.canExecute0;
        if (bindingFunction == null) {
            return true;
        }
        return bindingFunction.call().booleanValue();
    }
}
