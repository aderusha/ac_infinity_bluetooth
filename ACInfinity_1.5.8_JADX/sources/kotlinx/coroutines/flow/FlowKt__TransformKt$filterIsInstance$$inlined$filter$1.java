package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 implements Flow<Object> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public FlowKt__TransformKt$filterIsInstance$$inlined$filter$1(Flow flow) {
        this.$this_unsafeTransform$inlined = flow;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        Intrinsics.needClassReification();
        Object collect = flow.collect(new FlowCollector<Object>() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                /*
                    r5 = this;
                    boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.C38642.C38651
                    if (r0 == 0) goto L_0x0014
                    r0 = r7
                    kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.C38642.C38651) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r7 = r0.label
                    int r7 = r7 - r2
                    r0.label = r7
                    goto L_0x0019
                L_0x0014:
                    kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1
                    r0.<init>(r5, r7)
                L_0x0019:
                    java.lang.Object r7 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0032
                    if (r2 != r3) goto L_0x002a
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L_0x004d
                L_0x002a:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L_0x0032:
                    kotlin.ResultKt.throwOnFailure(r7)
                    kotlinx.coroutines.flow.FlowCollector r7 = r3
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    r2 = 3
                    java.lang.String r4 = "R"
                    kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r2, r4)
                    boolean r2 = r6 instanceof java.lang.Object
                    if (r2 == 0) goto L_0x004d
                    r0.label = r3
                    java.lang.Object r6 = r7.emit(r6, r0)
                    if (r6 != r1) goto L_0x004d
                    return r1
                L_0x004d:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.C38642.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }

            public Object emit$$forInline(Object obj, Continuation continuation) {
                InlineMarker.mark(4);
                new ContinuationImpl(this, continuation) {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;
                    final /* synthetic */ C38642 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit((Object) null, this);
                    }
                };
                InlineMarker.mark(5);
                FlowCollector flowCollector = r3;
                Continuation continuation2 = continuation;
                Intrinsics.reifiedOperationMarker(3, "R");
                if (obj instanceof Object) {
                    InlineMarker.mark(0);
                    flowCollector.emit(obj, continuation);
                    InlineMarker.mark(1);
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        if (collect == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return collect;
        }
        return Unit.INSTANCE;
    }

    public Object collect$$forInline(final FlowCollector flowCollector, Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(this, continuation) {
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 this$0;

            {
                this.this$0 = r1;
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.collect((FlowCollector) null, this);
            }
        };
        InlineMarker.mark(5);
        Flow flow = this.$this_unsafeTransform$inlined;
        Intrinsics.needClassReification();
        InlineMarker.mark(0);
        flow.collect(new FlowCollector<Object>() {
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                /*
                    r5 = this;
                    boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.C38642.C38651
                    if (r0 == 0) goto L_0x0014
                    r0 = r7
                    kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.C38642.C38651) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r7 = r0.label
                    int r7 = r7 - r2
                    r0.label = r7
                    goto L_0x0019
                L_0x0014:
                    kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1
                    r0.<init>(r5, r7)
                L_0x0019:
                    java.lang.Object r7 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0032
                    if (r2 != r3) goto L_0x002a
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L_0x004d
                L_0x002a:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L_0x0032:
                    kotlin.ResultKt.throwOnFailure(r7)
                    kotlinx.coroutines.flow.FlowCollector r7 = r3
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    r2 = 3
                    java.lang.String r4 = "R"
                    kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r2, r4)
                    boolean r2 = r6 instanceof java.lang.Object
                    if (r2 == 0) goto L_0x004d
                    r0.label = r3
                    java.lang.Object r6 = r7.emit(r6, r0)
                    if (r6 != r1) goto L_0x004d
                    return r1
                L_0x004d:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.C38642.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }

            public Object emit$$forInline(Object obj, Continuation continuation) {
                InlineMarker.mark(4);
                new ContinuationImpl(this, continuation) {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;
                    final /* synthetic */ C38642 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit((Object) null, this);
                    }
                };
                InlineMarker.mark(5);
                FlowCollector flowCollector = flowCollector;
                Continuation continuation2 = continuation;
                Intrinsics.reifiedOperationMarker(3, "R");
                if (obj instanceof Object) {
                    InlineMarker.mark(0);
                    flowCollector.emit(obj, continuation);
                    InlineMarker.mark(1);
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
