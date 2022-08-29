package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1", mo28222f = "SimpleChannelFlow.kt", mo28223i = {}, mo28224l = {46}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: SimpleChannelFlow.kt */
final class SimpleChannelFlowKt$simpleChannelFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $block;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SimpleChannelFlowKt$simpleChannelFlow$1(Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        SimpleChannelFlowKt$simpleChannelFlow$1 simpleChannelFlowKt$simpleChannelFlow$1 = new SimpleChannelFlowKt$simpleChannelFlow$1(this.$block, continuation);
        simpleChannelFlowKt$simpleChannelFlow$1.L$0 = obj;
        return simpleChannelFlowKt$simpleChannelFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SimpleChannelFlowKt$simpleChannelFlow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1", mo28222f = "SimpleChannelFlow.kt", mo28223i = {0, 1}, mo28224l = {64, 65}, mo28225m = "invokeSuspend", mo28226n = {"producer", "producer"}, mo28227s = {"L$0", "L$0"})
    /* renamed from: androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1 */
    /* compiled from: SimpleChannelFlow.kt */
    static final class C05671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SimpleChannelFlowKt$simpleChannelFlow$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            C05671 r0 = new C05671(this.this$0, flowCollector, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C05671) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x006f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                r2 = 2
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L_0x0032
                if (r1 == r3) goto L_0x0025
                if (r1 != r2) goto L_0x001d
                java.lang.Object r1 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r12.L$0
                kotlinx.coroutines.Job r5 = (kotlinx.coroutines.Job) r5
                kotlin.ResultKt.throwOnFailure(r13)
                r13 = r1
                r1 = r5
                goto L_0x0054
            L_0x001d:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L_0x0025:
                java.lang.Object r1 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r12.L$0
                kotlinx.coroutines.Job r5 = (kotlinx.coroutines.Job) r5
                kotlin.ResultKt.throwOnFailure(r13)
                r6 = r12
                goto L_0x0067
            L_0x0032:
                kotlin.ResultKt.throwOnFailure(r13)
                java.lang.Object r13 = r12.L$0
                r5 = r13
                kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
                r13 = 0
                r1 = 6
                kotlinx.coroutines.channels.Channel r13 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r13, r4, r4, r1, r4)
                r6 = 0
                r7 = 0
                androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1 r1 = new androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1
                r1.<init>(r12, r13, r4)
                r8 = r1
                kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
                r9 = 3
                r10 = 0
                kotlinx.coroutines.Job r1 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
                kotlinx.coroutines.channels.ChannelIterator r13 = r13.iterator()
            L_0x0054:
                r5 = r12
            L_0x0055:
                r5.L$0 = r1
                r5.L$1 = r13
                r5.label = r3
                java.lang.Object r6 = r13.hasNext(r5)
                if (r6 != r0) goto L_0x0062
                return r0
            L_0x0062:
                r11 = r1
                r1 = r13
                r13 = r6
                r6 = r5
                r5 = r11
            L_0x0067:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto L_0x0086
                java.lang.Object r13 = r1.next()
                kotlinx.coroutines.flow.FlowCollector r7 = r5
                r6.L$0 = r5
                r6.L$1 = r1
                r6.label = r2
                java.lang.Object r13 = r7.emit(r13, r6)
                if (r13 != r0) goto L_0x0082
                return r0
            L_0x0082:
                r13 = r1
                r1 = r5
                r5 = r6
                goto L_0x0055
            L_0x0086:
                kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r5, (java.util.concurrent.CancellationException) r4, (int) r3, (java.lang.Object) r4)
                kotlin.Unit r13 = kotlin.Unit.INSTANCE
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1.C05671.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final FlowCollector flowCollector = (FlowCollector) this.L$0;
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new C05671(this, (Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
