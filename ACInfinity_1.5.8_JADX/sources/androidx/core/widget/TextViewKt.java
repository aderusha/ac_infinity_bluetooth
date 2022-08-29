package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022d\b\u0006\u0010\u0003\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00042d\b\u0006\u0010\u000e\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\r0\u00042%\b\u0006\u0010\u0010\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\r0\u0011H\bø\u0001\u0000\u001a7\u0010\u0013\u001a\u00020\u0001*\u00020\u00022%\b\u0004\u0010\u0014\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\r0\u0011H\bø\u0001\u0000\u001av\u0010\u0015\u001a\u00020\u0001*\u00020\u00022d\b\u0004\u0010\u0014\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0004H\bø\u0001\u0000\u001av\u0010\u0016\u001a\u00020\u0001*\u00020\u00022d\b\u0004\u0010\u0014\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\r0\u0004H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0017"}, mo27512d2 = {"addTextChangedListener", "Landroid/text/TextWatcher;", "Landroid/widget/TextView;", "beforeTextChanged", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "text", "", "start", "count", "after", "", "onTextChanged", "before", "afterTextChanged", "Lkotlin/Function1;", "Landroid/text/Editable;", "doAfterTextChanged", "action", "doBeforeTextChanged", "doOnTextChanged", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: TextView.kt */
public final class TextViewKt {
    public static /* synthetic */ TextWatcher addTextChangedListener$default(TextView textView, Function4 function4, Function4 function42, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function4 = TextViewKt$addTextChangedListener$1.INSTANCE;
        }
        if ((i & 2) != 0) {
            function42 = TextViewKt$addTextChangedListener$2.INSTANCE;
        }
        if ((i & 4) != 0) {
            function1 = TextViewKt$addTextChangedListener$3.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(function4, "beforeTextChanged");
        Intrinsics.checkNotNullParameter(function42, "onTextChanged");
        Intrinsics.checkNotNullParameter(function1, "afterTextChanged");
        TextWatcher textViewKt$addTextChangedListener$textWatcher$1 = new TextViewKt$addTextChangedListener$textWatcher$1(function1, function4, function42);
        textView.addTextChangedListener(textViewKt$addTextChangedListener$textWatcher$1);
        return textViewKt$addTextChangedListener$textWatcher$1;
    }

    public static final TextWatcher addTextChangedListener(TextView textView, Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function4, Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function42, Function1<? super Editable, Unit> function1) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(function4, "beforeTextChanged");
        Intrinsics.checkNotNullParameter(function42, "onTextChanged");
        Intrinsics.checkNotNullParameter(function1, "afterTextChanged");
        TextWatcher textViewKt$addTextChangedListener$textWatcher$1 = new TextViewKt$addTextChangedListener$textWatcher$1(function1, function4, function42);
        textView.addTextChangedListener(textViewKt$addTextChangedListener$textWatcher$1);
        return textViewKt$addTextChangedListener$textWatcher$1;
    }

    public static final TextWatcher doBeforeTextChanged(TextView textView, Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function4) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(function4, "action");
        TextWatcher textViewKt$doBeforeTextChanged$$inlined$addTextChangedListener$default$1 = new C0243x44bcd5b1(function4);
        textView.addTextChangedListener(textViewKt$doBeforeTextChanged$$inlined$addTextChangedListener$default$1);
        return textViewKt$doBeforeTextChanged$$inlined$addTextChangedListener$default$1;
    }

    public static final TextWatcher doOnTextChanged(TextView textView, Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function4) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(function4, "action");
        TextWatcher textViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1 = new C0244x3fe43951(function4);
        textView.addTextChangedListener(textViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1);
        return textViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1;
    }

    public static final TextWatcher doAfterTextChanged(TextView textView, Function1<? super Editable, Unit> function1) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        TextWatcher textViewKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1 = new C0242xbb2cb19c(function1);
        textView.addTextChangedListener(textViewKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1);
        return textViewKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1;
    }
}
