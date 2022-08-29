package kotlinx.coroutines.channels;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, mo27512d2 = {"<anonymous>", "", "cause", ""}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$consumesAll$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel<?>[] $channels;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$consumesAll$1(ReceiveChannel<?>[] receiveChannelArr) {
        super(1);
        this.$channels = receiveChannelArr;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        ReceiveChannel<?>[] receiveChannelArr = this.$channels;
        int length = receiveChannelArr.length;
        Throwable th2 = null;
        int i = 0;
        while (i < length) {
            ReceiveChannel<?> receiveChannel = receiveChannelArr[i];
            i++;
            try {
                ChannelsKt.cancelConsumed(receiveChannel, th);
            } catch (Throwable th3) {
                if (th2 == null) {
                    th2 = th3;
                } else {
                    ExceptionsKt.addSuppressed(th2, th3);
                }
            }
        }
        if (th2 != null) {
            throw th2;
        }
    }
}
