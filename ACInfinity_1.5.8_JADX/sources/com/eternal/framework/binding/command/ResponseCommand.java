package com.eternal.framework.binding.command;

import p014io.reactivex.functions.Function;

public class ResponseCommand<T, R> {
    private BindingFunction<Boolean> canExecute;
    private BindingFunction<R> execute;
    private Function<T, R> function;

    public ResponseCommand(BindingFunction<R> bindingFunction) {
        this.execute = bindingFunction;
    }

    public ResponseCommand(Function<T, R> function2) {
        this.function = function2;
    }

    public ResponseCommand(BindingFunction<R> bindingFunction, BindingFunction<Boolean> bindingFunction2) {
        this.execute = bindingFunction;
        this.canExecute = bindingFunction2;
    }

    public ResponseCommand(Function<T, R> function2, BindingFunction<Boolean> bindingFunction) {
        this.function = function2;
        this.canExecute = bindingFunction;
    }

    public R execute() {
        if (this.execute == null || !canExecute()) {
            return null;
        }
        return this.execute.call();
    }

    private boolean canExecute() {
        BindingFunction<Boolean> bindingFunction = this.canExecute;
        if (bindingFunction == null) {
            return true;
        }
        return bindingFunction.call().booleanValue();
    }

    public R execute(T t) throws Exception {
        if (this.function == null || !canExecute()) {
            return null;
        }
        return this.function.apply(t);
    }
}
