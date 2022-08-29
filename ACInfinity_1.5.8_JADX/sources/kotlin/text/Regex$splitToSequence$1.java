package kotlin.text;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

@Metadata(mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 5, 1})
@DebugMetadata(mo28221c = "kotlin.text.Regex$splitToSequence$1", mo28222f = "Regex.kt", mo28223i = {}, mo28224l = {243, 251, 255}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Regex.kt */
final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Regex$splitToSequence$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x009e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 == r5) goto L_0x002e
            if (r1 == r4) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x009f
        L_0x0017:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x001f:
            int r1 = r10.I$0
            java.lang.Object r2 = r10.L$1
            java.util.regex.Matcher r2 = (java.util.regex.Matcher) r2
            java.lang.Object r6 = r10.L$0
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r10
            goto L_0x0073
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00b1
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r11
            kotlin.text.Regex r1 = r10.this$0
            java.util.regex.Pattern r1 = r1.nativePattern
            java.lang.CharSequence r6 = r10.$input
            java.util.regex.Matcher r1 = r1.matcher(r6)
            int r6 = r10.$limit
            if (r6 == r5) goto L_0x00a2
            boolean r6 = r1.find()
            if (r6 != 0) goto L_0x0051
            goto L_0x00a2
        L_0x0051:
            r7 = r10
            r6 = r11
            r2 = r1
            r11 = 0
            r1 = 0
        L_0x0056:
            java.lang.CharSequence r8 = r7.$input
            int r9 = r2.start()
            java.lang.CharSequence r11 = r8.subSequence(r11, r9)
            java.lang.String r11 = r11.toString()
            r7.L$0 = r6
            r7.L$1 = r2
            r7.I$0 = r1
            r7.label = r4
            java.lang.Object r11 = r6.yield(r11, r7)
            if (r11 != r0) goto L_0x0073
            return r0
        L_0x0073:
            int r11 = r2.end()
            int r1 = r1 + r5
            int r8 = r7.$limit
            int r8 = r8 - r5
            if (r1 == r8) goto L_0x0083
            boolean r8 = r2.find()
            if (r8 != 0) goto L_0x0056
        L_0x0083:
            java.lang.CharSequence r1 = r7.$input
            int r2 = r1.length()
            java.lang.CharSequence r11 = r1.subSequence(r11, r2)
            java.lang.String r11 = r11.toString()
            r1 = 0
            r7.L$0 = r1
            r7.L$1 = r1
            r7.label = r3
            java.lang.Object r11 = r6.yield(r11, r7)
            if (r11 != r0) goto L_0x009f
            return r0
        L_0x009f:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00a2:
            java.lang.CharSequence r1 = r10.$input
            java.lang.String r1 = r1.toString()
            r10.label = r5
            java.lang.Object r11 = r11.yield(r1, r10)
            if (r11 != r0) goto L_0x00b1
            return r0
        L_0x00b1:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex$splitToSequence$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
