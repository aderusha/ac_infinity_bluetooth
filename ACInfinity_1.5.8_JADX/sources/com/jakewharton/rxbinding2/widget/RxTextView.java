package com.jakewharton.rxbinding2.widget;

import android.widget.TextView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Functions;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Predicate;

public final class RxTextView {
    public static Observable<Integer> editorActions(TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return editorActions(textView, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<Integer> editorActions(TextView textView, Predicate<? super Integer> predicate) {
        Preconditions.checkNotNull(textView, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new TextViewEditorActionObservable(textView, predicate);
    }

    public static Observable<TextViewEditorActionEvent> editorActionEvents(TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return editorActionEvents(textView, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<TextViewEditorActionEvent> editorActionEvents(TextView textView, Predicate<? super TextViewEditorActionEvent> predicate) {
        Preconditions.checkNotNull(textView, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new TextViewEditorActionEventObservable(textView, predicate);
    }

    public static InitialValueObservable<CharSequence> textChanges(TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new TextViewTextObservable(textView);
    }

    public static InitialValueObservable<TextViewTextChangeEvent> textChangeEvents(TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new TextViewTextChangeEventObservable(textView);
    }

    public static InitialValueObservable<TextViewBeforeTextChangeEvent> beforeTextChangeEvents(TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new TextViewBeforeTextChangeEventObservable(textView);
    }

    public static InitialValueObservable<TextViewAfterTextChangeEvent> afterTextChangeEvents(TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new TextViewAfterTextChangeEventObservable(textView);
    }

    public static Consumer<? super CharSequence> text(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                textView.setText(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> textRes(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                textView.setText(num.intValue());
            }
        };
    }

    public static Consumer<? super CharSequence> error(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                textView.setError(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> errorRes(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                TextView textView = textView;
                textView.setError(textView.getContext().getResources().getText(num.intValue()));
            }
        };
    }

    public static Consumer<? super CharSequence> hint(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                textView.setHint(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> hintRes(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                textView.setHint(num.intValue());
            }
        };
    }

    public static Consumer<? super Integer> color(final TextView textView) {
        Preconditions.checkNotNull(textView, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) throws Exception {
                textView.setTextColor(num.intValue());
            }
        };
    }

    private RxTextView() {
        throw new AssertionError("No instances.");
    }
}
