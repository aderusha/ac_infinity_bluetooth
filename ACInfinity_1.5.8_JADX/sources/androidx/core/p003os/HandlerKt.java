package androidx.core.p003os;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\bø\u0001\u0000\u001a4\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\f"}, mo27512d2 = {"postAtTime", "Ljava/lang/Runnable;", "Landroid/os/Handler;", "uptimeMillis", "", "token", "", "action", "Lkotlin/Function0;", "", "postDelayed", "delayInMillis", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* renamed from: androidx.core.os.HandlerKt */
/* compiled from: Handler.kt */
public final class HandlerKt {
    public static /* synthetic */ Runnable postDelayed$default(Handler handler, long j, Object obj, Function0 function0, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        Intrinsics.checkNotNullParameter(handler, "<this>");
        Intrinsics.checkNotNullParameter(function0, "action");
        Runnable handlerKt$postDelayed$runnable$1 = new HandlerKt$postDelayed$runnable$1(function0);
        if (obj == null) {
            handler.postDelayed(handlerKt$postDelayed$runnable$1, j);
        } else {
            HandlerCompat.postDelayed(handler, handlerKt$postDelayed$runnable$1, obj, j);
        }
        return handlerKt$postDelayed$runnable$1;
    }

    public static final Runnable postDelayed(Handler handler, long j, Object obj, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(handler, "<this>");
        Intrinsics.checkNotNullParameter(function0, "action");
        Runnable handlerKt$postDelayed$runnable$1 = new HandlerKt$postDelayed$runnable$1(function0);
        if (obj == null) {
            handler.postDelayed(handlerKt$postDelayed$runnable$1, j);
        } else {
            HandlerCompat.postDelayed(handler, handlerKt$postDelayed$runnable$1, obj, j);
        }
        return handlerKt$postDelayed$runnable$1;
    }

    public static /* synthetic */ Runnable postAtTime$default(Handler handler, long j, Object obj, Function0 function0, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        Intrinsics.checkNotNullParameter(handler, "<this>");
        Intrinsics.checkNotNullParameter(function0, "action");
        Runnable handlerKt$postAtTime$runnable$1 = new HandlerKt$postAtTime$runnable$1(function0);
        handler.postAtTime(handlerKt$postAtTime$runnable$1, obj, j);
        return handlerKt$postAtTime$runnable$1;
    }

    public static final Runnable postAtTime(Handler handler, long j, Object obj, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(handler, "<this>");
        Intrinsics.checkNotNullParameter(function0, "action");
        Runnable handlerKt$postAtTime$runnable$1 = new HandlerKt$postAtTime$runnable$1(function0);
        handler.postAtTime(handlerKt$postAtTime$runnable$1, obj, j);
        return handlerKt$postAtTime$runnable$1;
    }
}
