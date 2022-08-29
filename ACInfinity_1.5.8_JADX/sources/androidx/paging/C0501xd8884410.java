package androidx.paging;

import androidx.paging.PageFetcherSnapshot$pageEventFlow$1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0501xd8884410 implements FlowCollector<Unit> {
    final /* synthetic */ CoroutineScope $this_launch$inlined;
    final /* synthetic */ PageFetcherSnapshot$pageEventFlow$1.C05084 this$0;

    public C0501xd8884410(PageFetcherSnapshot$pageEventFlow$1.C05084 r1, CoroutineScope coroutineScope) {
        this.this$0 = r1;
        this.$this_launch$inlined = coroutineScope;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0349, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x034a, code lost:
        r10 = r5;
        r5 = r2;
        r2 = r6;
        r6 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r5 = r5.state;
        r7 = r6.this$0.this$0.this$0;
        r0.L$0 = r6;
        r0.L$1 = r13;
        r0.L$2 = r2;
        r0.L$3 = r12;
        r0.L$4 = null;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x036a, code lost:
        if (r7.setLoading(r5, r13, r0) != r1) goto L_0x036d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x036c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x036d, code lost:
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x036e, code lost:
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0370, code lost:
        r2.unlock((java.lang.Object) null);
        r2 = r5;
        r9 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x037a, code lost:
        r2 = r13;
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x037c, code lost:
        r5 = r12;
        r12 = r9.this$0.this$0.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x038b, code lost:
        if (androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0[r2.ordinal()] == 1) goto L_0x03cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x038d, code lost:
        r7 = r9.this$0.this$0.this$0.stateHolder;
        r6 = r7.lock;
        r0.L$0 = r9;
        r0.L$1 = r2;
        r0.L$2 = r7;
        r0.L$3 = r6;
        r0.L$4 = r5;
        r0.L$5 = r2;
        r0.L$6 = r12;
        r0.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x03b1, code lost:
        if (r6.lock((java.lang.Object) null, r0) != r1) goto L_0x03b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x03b3, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03b4, code lost:
        r8 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r13 = r7.state.getFailedHintsByLoadType$paging_common().get(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x03c3, code lost:
        r6.unlock((java.lang.Object) null);
        r6 = r5;
        r5 = r2;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03ca, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x03cb, code lost:
        r6.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x03ce, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x03cf, code lost:
        r13 = null;
        r6 = r5;
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03d2, code lost:
        r0.L$0 = r9;
        r0.L$1 = r2;
        r0.L$2 = r6;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.L$5 = null;
        r0.L$6 = null;
        r0.label = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x03e8, code lost:
        if (r12.retryLoadError(r5, r13, r0) != r1) goto L_0x03eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x03ea, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x03eb, code lost:
        r12 = r6;
        r6 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x03ef, code lost:
        if (r2 != androidx.paging.LoadType.REFRESH) goto L_0x0438;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x03f1, code lost:
        r5 = r6.this$0.this$0.this$0.stateHolder;
        r2 = r5.lock;
        r0.L$0 = r6;
        r0.L$1 = r5;
        r0.L$2 = r2;
        r0.L$3 = r12;
        r0.label = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x040f, code lost:
        if (r2.lock((java.lang.Object) null, r0) != r1) goto L_0x0412;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0411, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:?, code lost:
        r13 = r5.state.getSourceLoadStates$paging_common().get$paging_common(androidx.paging.LoadType.REFRESH);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0420, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0425, code lost:
        if ((r13 instanceof androidx.paging.LoadState.Error) != false) goto L_0x0438;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0427, code lost:
        r6.this$0.this$0.this$0.startConsumingHints(r6.$this_launch$inlined);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0433, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0434, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0437, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0438, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0439, code lost:
        r13 = androidx.paging.LoadType.APPEND;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0441, code lost:
        if ((r12.getAppend() instanceof androidx.paging.LoadState.Error) != false) goto L_0x0445;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0447, code lost:
        if (r13 == androidx.paging.LoadType.REFRESH) goto L_0x0492;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0449, code lost:
        r2 = r5.this$0.this$0.this$0.stateHolder;
        r12 = r2.lock;
        r0.L$0 = r5;
        r0.L$1 = r13;
        r0.L$2 = r2;
        r0.L$3 = r12;
        r0.label = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0467, code lost:
        if (r12.lock((java.lang.Object) null, r0) != r1) goto L_0x046a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0469, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:?, code lost:
        r2 = r2.state;
        r6 = r5.this$0.this$0.this$0;
        r0.L$0 = r5;
        r0.L$1 = r13;
        r0.L$2 = r12;
        r0.L$3 = null;
        r0.label = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0484, code lost:
        if (r6.setLoading(r2, r13, r0) != r1) goto L_0x0487;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0486, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0487, code lost:
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0488, code lost:
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x048a, code lost:
        r12.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0492, code lost:
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0493, code lost:
        r7 = r5;
        r12 = r7.this$0.this$0.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x04a2, code lost:
        if (androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0[r2.ordinal()] == 1) goto L_0x04e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x04a4, code lost:
        r5 = r7.this$0.this$0.this$0.stateHolder;
        r3 = r5.lock;
        r0.L$0 = r7;
        r0.L$1 = r2;
        r0.L$2 = r5;
        r0.L$3 = r3;
        r0.L$4 = r2;
        r0.L$5 = r12;
        r0.label = 14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x04c6, code lost:
        if (r3.lock((java.lang.Object) null, r0) != r1) goto L_0x04c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x04c8, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x04c9, code lost:
        r6 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:?, code lost:
        r13 = r5.state.getFailedHintsByLoadType$paging_common().get(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x04d8, code lost:
        r3.unlock((java.lang.Object) null);
        r3 = r2;
        r2 = r13;
        r13 = r12;
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x04e0, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04e1, code lost:
        r3.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x04e4, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x04e5, code lost:
        r13 = r12;
        r12 = r2;
        r3 = r12;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x04e9, code lost:
        r0.L$0 = r7;
        r0.L$1 = r12;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.L$5 = null;
        r0.label = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x04fd, code lost:
        if (r13.retryLoadError(r3, r2, r0) != r1) goto L_0x0500;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x04ff, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0500, code lost:
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0503, code lost:
        if (r12 != androidx.paging.LoadType.REFRESH) goto L_0x054d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0505, code lost:
        r12 = r2.this$0.this$0.this$0.stateHolder;
        r13 = r12.lock;
        r0.L$0 = r2;
        r0.L$1 = r12;
        r0.L$2 = r13;
        r0.label = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0521, code lost:
        if (r13.lock((java.lang.Object) null, r0) != r1) goto L_0x0524;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0523, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0524, code lost:
        r1 = r12;
        r12 = r13;
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:?, code lost:
        r13 = r1.state.getSourceLoadStates$paging_common().get$paging_common(androidx.paging.LoadType.REFRESH);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0535, code lost:
        r12.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x053a, code lost:
        if ((r13 instanceof androidx.paging.LoadState.Error) != false) goto L_0x054d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x053c, code lost:
        r0.this$0.this$0.this$0.startConsumingHints(r0.$this_launch$inlined);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0548, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0549, code lost:
        r12.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x054c, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x054f, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0550, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0551, code lost:
        r12.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0554, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r13 = r2.state;
        r13 = kotlin.TuplesKt.m993to(r13.getSourceLoadStates$paging_common(), r13.currentPagingState$paging_common(r5.this$0.this$0.this$0.lastHint));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01da, code lost:
        r12.unlock((java.lang.Object) null);
        r12 = (androidx.paging.LoadStates) r13.component1();
        r13 = (androidx.paging.PagingState) r13.component2();
        r2 = r5.this$0.this$0.this$0.getRemoteMediatorConnection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01f3, code lost:
        if (r2 == null) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01f5, code lost:
        r2.retryFailed(r13);
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01fa, code lost:
        r13 = androidx.paging.LoadType.REFRESH;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0202, code lost:
        if ((r12.getRefresh() instanceof androidx.paging.LoadState.Error) != false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0208, code lost:
        if (r13 == androidx.paging.LoadType.REFRESH) goto L_0x025c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x020a, code lost:
        r2 = r5.this$0.this$0.this$0.stateHolder;
        r6 = r2.lock;
        r0.L$0 = r5;
        r0.L$1 = r13;
        r0.L$2 = r2;
        r0.L$3 = r6;
        r0.L$4 = r12;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0229, code lost:
        if (r6.lock((java.lang.Object) null, r0) != r1) goto L_0x022c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x022b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x022c, code lost:
        r10 = r5;
        r5 = r2;
        r2 = r6;
        r6 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r5 = r5.state;
        r7 = r6.this$0.this$0.this$0;
        r0.L$0 = r6;
        r0.L$1 = r13;
        r0.L$2 = r2;
        r0.L$3 = r12;
        r0.L$4 = null;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x024b, code lost:
        if (r7.setLoading(r5, r13, r0) != r1) goto L_0x024e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x024d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x024e, code lost:
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x024f, code lost:
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0251, code lost:
        r2.unlock((java.lang.Object) null);
        r2 = r5;
        r9 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0256, code lost:
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x025c, code lost:
        r2 = r13;
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x025f, code lost:
        r12 = r9.this$0.this$0.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x026d, code lost:
        if (androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0[r2.ordinal()] == 1) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x026f, code lost:
        r7 = r9.this$0.this$0.this$0.stateHolder;
        r6 = r7.lock;
        r0.L$0 = r9;
        r0.L$1 = r2;
        r0.L$2 = r7;
        r0.L$3 = r6;
        r0.L$4 = r5;
        r0.L$5 = r2;
        r0.L$6 = r12;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0292, code lost:
        if (r6.lock((java.lang.Object) null, r0) != r1) goto L_0x0295;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0294, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0295, code lost:
        r8 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r13 = r7.state.getFailedHintsByLoadType$paging_common().get(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x02a4, code lost:
        r6.unlock((java.lang.Object) null);
        r6 = r5;
        r5 = r2;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x02ab, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x02ac, code lost:
        r6.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x02af, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x02b0, code lost:
        r13 = null;
        r6 = r5;
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x02b3, code lost:
        r0.L$0 = r9;
        r0.L$1 = r2;
        r0.L$2 = r6;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.L$5 = null;
        r0.L$6 = null;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x02c8, code lost:
        if (r12.retryLoadError(r5, r13, r0) != r1) goto L_0x02cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02ca, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02cb, code lost:
        r12 = r6;
        r6 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02cf, code lost:
        if (r2 != androidx.paging.LoadType.REFRESH) goto L_0x0317;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02d1, code lost:
        r5 = r6.this$0.this$0.this$0.stateHolder;
        r2 = r5.lock;
        r0.L$0 = r6;
        r0.L$1 = r5;
        r0.L$2 = r2;
        r0.L$3 = r12;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02ee, code lost:
        if (r2.lock((java.lang.Object) null, r0) != r1) goto L_0x02f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x02f0, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r13 = r5.state.getSourceLoadStates$paging_common().get$paging_common(androidx.paging.LoadType.REFRESH);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x02ff, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0304, code lost:
        if ((r13 instanceof androidx.paging.LoadState.Error) != false) goto L_0x0317;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0306, code lost:
        r6.this$0.this$0.this$0.startConsumingHints(r6.$this_launch$inlined);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0312, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0313, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0316, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0317, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0318, code lost:
        r13 = androidx.paging.LoadType.PREPEND;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0320, code lost:
        if ((r12.getPrepend() instanceof androidx.paging.LoadState.Error) != false) goto L_0x0324;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0326, code lost:
        if (r13 == androidx.paging.LoadType.REFRESH) goto L_0x037a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0328, code lost:
        r2 = r5.this$0.this$0.this$0.stateHolder;
        r6 = r2.lock;
        r0.L$0 = r5;
        r0.L$1 = r13;
        r0.L$2 = r2;
        r0.L$3 = r6;
        r0.L$4 = r12;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0347, code lost:
        if (r6.lock((java.lang.Object) null, r0) != r1) goto L_0x034a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r12, kotlin.coroutines.Continuation r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof androidx.paging.C0501xd8884410.C05021
            if (r0 == 0) goto L_0x0014
            r0 = r13
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1$1 r0 = (androidx.paging.C0501xd8884410.C05021) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1$1 r0 = new androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L_0x019a;
                case 1: goto L_0x018a;
                case 2: goto L_0x016f;
                case 3: goto L_0x0157;
                case 4: goto L_0x0136;
                case 5: goto L_0x0124;
                case 6: goto L_0x010f;
                case 7: goto L_0x00f4;
                case 8: goto L_0x00dc;
                case 9: goto L_0x00bb;
                case 10: goto L_0x00a9;
                case 11: goto L_0x0094;
                case 12: goto L_0x007d;
                case 13: goto L_0x0069;
                case 14: goto L_0x004c;
                case 15: goto L_0x003f;
                case 16: goto L_0x002e;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x002e:
            java.lang.Object r12 = r0.L$2
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r1 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r1 = (androidx.paging.PageFetcherSnapshotState.Holder) r1
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r0 = (androidx.paging.C0501xd8884410) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0527
        L_0x003f:
            java.lang.Object r12 = r0.L$1
            androidx.paging.LoadType r12 = (androidx.paging.LoadType) r12
            java.lang.Object r2 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r2 = (androidx.paging.C0501xd8884410) r2
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0501
        L_0x004c:
            java.lang.Object r12 = r0.L$5
            androidx.paging.PageFetcherSnapshot r12 = (androidx.paging.PageFetcherSnapshot) r12
            java.lang.Object r2 = r0.L$4
            androidx.paging.LoadType r2 = (androidx.paging.LoadType) r2
            java.lang.Object r3 = r0.L$3
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            java.lang.Object r5 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.LoadType r6 = (androidx.paging.LoadType) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r7 = (androidx.paging.C0501xd8884410) r7
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x04ca
        L_0x0069:
            java.lang.Object r12 = r0.L$2
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r2 = r0.L$1
            androidx.paging.LoadType r2 = (androidx.paging.LoadType) r2
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r5 = (androidx.paging.C0501xd8884410) r5
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x007a }
            goto L_0x0488
        L_0x007a:
            r13 = move-exception
            goto L_0x048e
        L_0x007d:
            java.lang.Object r12 = r0.L$3
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r2 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r5 = r0.L$1
            androidx.paging.LoadType r5 = (androidx.paging.LoadType) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r6 = (androidx.paging.C0501xd8884410) r6
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r5
            r5 = r6
            goto L_0x046a
        L_0x0094:
            java.lang.Object r12 = r0.L$3
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r6 = (androidx.paging.C0501xd8884410) r6
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0412
        L_0x00a9:
            java.lang.Object r12 = r0.L$2
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$1
            androidx.paging.LoadType r2 = (androidx.paging.LoadType) r2
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r5 = (androidx.paging.C0501xd8884410) r5
            kotlin.ResultKt.throwOnFailure(r13)
            r6 = r5
            goto L_0x03ed
        L_0x00bb:
            java.lang.Object r12 = r0.L$6
            androidx.paging.PageFetcherSnapshot r12 = (androidx.paging.PageFetcherSnapshot) r12
            java.lang.Object r2 = r0.L$5
            androidx.paging.LoadType r2 = (androidx.paging.LoadType) r2
            java.lang.Object r5 = r0.L$4
            androidx.paging.LoadStates r5 = (androidx.paging.LoadStates) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r7 = (androidx.paging.PageFetcherSnapshotState.Holder) r7
            java.lang.Object r8 = r0.L$1
            androidx.paging.LoadType r8 = (androidx.paging.LoadType) r8
            java.lang.Object r9 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r9 = (androidx.paging.C0501xd8884410) r9
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x03b5
        L_0x00dc:
            java.lang.Object r12 = r0.L$3
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$1
            androidx.paging.LoadType r5 = (androidx.paging.LoadType) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r6 = (androidx.paging.C0501xd8884410) r6
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x00f1 }
            goto L_0x036e
        L_0x00f1:
            r12 = move-exception
            goto L_0x0376
        L_0x00f4:
            java.lang.Object r12 = r0.L$4
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.LoadType r6 = (androidx.paging.LoadType) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r7 = (androidx.paging.C0501xd8884410) r7
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r6
            r6 = r7
            goto L_0x034e
        L_0x010f:
            java.lang.Object r12 = r0.L$3
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r6 = (androidx.paging.C0501xd8884410) r6
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x02f1
        L_0x0124:
            java.lang.Object r12 = r0.L$2
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$1
            androidx.paging.LoadType r2 = (androidx.paging.LoadType) r2
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r5 = (androidx.paging.C0501xd8884410) r5
            kotlin.ResultKt.throwOnFailure(r13)
            r6 = r5
            goto L_0x02cd
        L_0x0136:
            java.lang.Object r12 = r0.L$6
            androidx.paging.PageFetcherSnapshot r12 = (androidx.paging.PageFetcherSnapshot) r12
            java.lang.Object r2 = r0.L$5
            androidx.paging.LoadType r2 = (androidx.paging.LoadType) r2
            java.lang.Object r5 = r0.L$4
            androidx.paging.LoadStates r5 = (androidx.paging.LoadStates) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r7 = (androidx.paging.PageFetcherSnapshotState.Holder) r7
            java.lang.Object r8 = r0.L$1
            androidx.paging.LoadType r8 = (androidx.paging.LoadType) r8
            java.lang.Object r9 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r9 = (androidx.paging.C0501xd8884410) r9
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0296
        L_0x0157:
            java.lang.Object r12 = r0.L$3
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$1
            androidx.paging.LoadType r5 = (androidx.paging.LoadType) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r6 = (androidx.paging.C0501xd8884410) r6
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x016c }
            goto L_0x024f
        L_0x016c:
            r12 = move-exception
            goto L_0x0258
        L_0x016f:
            java.lang.Object r12 = r0.L$4
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.LoadType r6 = (androidx.paging.LoadType) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r7 = (androidx.paging.C0501xd8884410) r7
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r6
            r6 = r7
            goto L_0x0230
        L_0x018a:
            java.lang.Object r12 = r0.L$2
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r2 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 r5 = (androidx.paging.C0501xd8884410) r5
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x01c0
        L_0x019a:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            kotlin.Unit r12 = (kotlin.Unit) r12
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r11.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r2 = r12.stateHolder
            kotlinx.coroutines.sync.Mutex r12 = r2.lock
            r0.L$0 = r11
            r0.L$1 = r2
            r0.L$2 = r12
            r0.label = r3
            java.lang.Object r13 = r12.lock(r4, r0)
            if (r13 != r1) goto L_0x01bf
            return r1
        L_0x01bf:
            r5 = r11
        L_0x01c0:
            androidx.paging.PageFetcherSnapshotState r13 = r2.state     // Catch:{ all -> 0x0550 }
            androidx.paging.LoadStates r2 = r13.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0550 }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r6 = r5.this$0     // Catch:{ all -> 0x0550 }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r6 = r6.this$0     // Catch:{ all -> 0x0550 }
            androidx.paging.PageFetcherSnapshot r6 = r6.this$0     // Catch:{ all -> 0x0550 }
            androidx.paging.ViewportHint$Access r6 = r6.lastHint     // Catch:{ all -> 0x0550 }
            androidx.paging.PagingState r13 = r13.currentPagingState$paging_common(r6)     // Catch:{ all -> 0x0550 }
            kotlin.Pair r13 = kotlin.TuplesKt.m993to(r2, r13)     // Catch:{ all -> 0x0550 }
            r12.unlock(r4)
            java.lang.Object r12 = r13.component1()
            androidx.paging.LoadStates r12 = (androidx.paging.LoadStates) r12
            java.lang.Object r13 = r13.component2()
            androidx.paging.PagingState r13 = (androidx.paging.PagingState) r13
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r2 = r5.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r2 = r2.this$0
            androidx.paging.PageFetcherSnapshot r2 = r2.this$0
            androidx.paging.RemoteMediatorConnection r2 = r2.getRemoteMediatorConnection()
            if (r2 == 0) goto L_0x01fa
            r2.retryFailed(r13)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
        L_0x01fa:
            androidx.paging.LoadType r13 = androidx.paging.LoadType.REFRESH
            androidx.paging.LoadState r2 = r12.getRefresh()
            boolean r2 = r2 instanceof androidx.paging.LoadState.Error
            if (r2 != 0) goto L_0x0206
            goto L_0x0318
        L_0x0206:
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            if (r13 == r2) goto L_0x025c
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r2 = r5.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r2 = r2.this$0
            androidx.paging.PageFetcherSnapshot r2 = r2.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r2 = r2.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r2.lock
            r0.L$0 = r5
            r0.L$1 = r13
            r0.L$2 = r2
            r0.L$3 = r6
            r0.L$4 = r12
            r7 = 2
            r0.label = r7
            java.lang.Object r7 = r6.lock(r4, r0)
            if (r7 != r1) goto L_0x022c
            return r1
        L_0x022c:
            r10 = r5
            r5 = r2
            r2 = r6
            r6 = r10
        L_0x0230:
            androidx.paging.PageFetcherSnapshotState r5 = r5.state     // Catch:{ all -> 0x016c }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r7 = r6.this$0     // Catch:{ all -> 0x016c }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r7 = r7.this$0     // Catch:{ all -> 0x016c }
            androidx.paging.PageFetcherSnapshot r7 = r7.this$0     // Catch:{ all -> 0x016c }
            r0.L$0 = r6     // Catch:{ all -> 0x016c }
            r0.L$1 = r13     // Catch:{ all -> 0x016c }
            r0.L$2 = r2     // Catch:{ all -> 0x016c }
            r0.L$3 = r12     // Catch:{ all -> 0x016c }
            r0.L$4 = r4     // Catch:{ all -> 0x016c }
            r8 = 3
            r0.label = r8     // Catch:{ all -> 0x016c }
            java.lang.Object r5 = r7.setLoading(r5, r13, r0)     // Catch:{ all -> 0x016c }
            if (r5 != r1) goto L_0x024e
            return r1
        L_0x024e:
            r5 = r13
        L_0x024f:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x016c }
            r2.unlock(r4)
            r2 = r5
            r9 = r6
        L_0x0256:
            r5 = r12
            goto L_0x025f
        L_0x0258:
            r2.unlock(r4)
            throw r12
        L_0x025c:
            r2 = r13
            r9 = r5
            goto L_0x0256
        L_0x025f:
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r9.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            int[] r13 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0
            int r6 = r2.ordinal()
            r13 = r13[r6]
            if (r13 == r3) goto L_0x02b0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r9.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r7 = r13.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r7.lock
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r7
            r0.L$3 = r6
            r0.L$4 = r5
            r0.L$5 = r2
            r0.L$6 = r12
            r13 = 4
            r0.label = r13
            java.lang.Object r13 = r6.lock(r4, r0)
            if (r13 != r1) goto L_0x0295
            return r1
        L_0x0295:
            r8 = r2
        L_0x0296:
            androidx.paging.PageFetcherSnapshotState r13 = r7.state     // Catch:{ all -> 0x02ab }
            java.util.Map r13 = r13.getFailedHintsByLoadType$paging_common()     // Catch:{ all -> 0x02ab }
            java.lang.Object r13 = r13.get(r8)     // Catch:{ all -> 0x02ab }
            androidx.paging.ViewportHint r13 = (androidx.paging.ViewportHint) r13     // Catch:{ all -> 0x02ab }
            r6.unlock(r4)
            r6 = r5
            r5 = r2
            r2 = r8
            goto L_0x02b3
        L_0x02ab:
            r12 = move-exception
            r6.unlock(r4)
            throw r12
        L_0x02b0:
            r13 = r4
            r6 = r5
            r5 = r2
        L_0x02b3:
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r6
            r0.L$3 = r4
            r0.L$4 = r4
            r0.L$5 = r4
            r0.L$6 = r4
            r7 = 5
            r0.label = r7
            java.lang.Object r12 = r12.retryLoadError(r5, r13, r0)
            if (r12 != r1) goto L_0x02cb
            return r1
        L_0x02cb:
            r12 = r6
            r6 = r9
        L_0x02cd:
            androidx.paging.LoadType r13 = androidx.paging.LoadType.REFRESH
            if (r2 != r13) goto L_0x0317
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r6.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r5 = r13.stateHolder
            kotlinx.coroutines.sync.Mutex r2 = r5.lock
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r12
            r13 = 6
            r0.label = r13
            java.lang.Object r13 = r2.lock(r4, r0)
            if (r13 != r1) goto L_0x02f1
            return r1
        L_0x02f1:
            androidx.paging.PageFetcherSnapshotState r13 = r5.state     // Catch:{ all -> 0x0312 }
            androidx.paging.LoadStates r13 = r13.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0312 }
            androidx.paging.LoadType r5 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0312 }
            androidx.paging.LoadState r13 = r13.get$paging_common(r5)     // Catch:{ all -> 0x0312 }
            r2.unlock(r4)
            boolean r13 = r13 instanceof androidx.paging.LoadState.Error
            if (r13 != 0) goto L_0x0317
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r6.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            kotlinx.coroutines.CoroutineScope r2 = r6.$this_launch$inlined
            r13.startConsumingHints(r2)
            goto L_0x0317
        L_0x0312:
            r12 = move-exception
            r2.unlock(r4)
            throw r12
        L_0x0317:
            r5 = r6
        L_0x0318:
            androidx.paging.LoadType r13 = androidx.paging.LoadType.PREPEND
            androidx.paging.LoadState r2 = r12.getPrepend()
            boolean r2 = r2 instanceof androidx.paging.LoadState.Error
            if (r2 != 0) goto L_0x0324
            goto L_0x0439
        L_0x0324:
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            if (r13 == r2) goto L_0x037a
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r2 = r5.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r2 = r2.this$0
            androidx.paging.PageFetcherSnapshot r2 = r2.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r2 = r2.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r2.lock
            r0.L$0 = r5
            r0.L$1 = r13
            r0.L$2 = r2
            r0.L$3 = r6
            r0.L$4 = r12
            r7 = 7
            r0.label = r7
            java.lang.Object r7 = r6.lock(r4, r0)
            if (r7 != r1) goto L_0x034a
            return r1
        L_0x034a:
            r10 = r5
            r5 = r2
            r2 = r6
            r6 = r10
        L_0x034e:
            androidx.paging.PageFetcherSnapshotState r5 = r5.state     // Catch:{ all -> 0x00f1 }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r7 = r6.this$0     // Catch:{ all -> 0x00f1 }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r7 = r7.this$0     // Catch:{ all -> 0x00f1 }
            androidx.paging.PageFetcherSnapshot r7 = r7.this$0     // Catch:{ all -> 0x00f1 }
            r0.L$0 = r6     // Catch:{ all -> 0x00f1 }
            r0.L$1 = r13     // Catch:{ all -> 0x00f1 }
            r0.L$2 = r2     // Catch:{ all -> 0x00f1 }
            r0.L$3 = r12     // Catch:{ all -> 0x00f1 }
            r0.L$4 = r4     // Catch:{ all -> 0x00f1 }
            r8 = 8
            r0.label = r8     // Catch:{ all -> 0x00f1 }
            java.lang.Object r5 = r7.setLoading(r5, r13, r0)     // Catch:{ all -> 0x00f1 }
            if (r5 != r1) goto L_0x036d
            return r1
        L_0x036d:
            r5 = r13
        L_0x036e:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f1 }
            r2.unlock(r4)
            r2 = r5
            r9 = r6
            goto L_0x037c
        L_0x0376:
            r2.unlock(r4)
            throw r12
        L_0x037a:
            r2 = r13
            r9 = r5
        L_0x037c:
            r5 = r12
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r9.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            int[] r13 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0
            int r6 = r2.ordinal()
            r13 = r13[r6]
            if (r13 == r3) goto L_0x03cf
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r9.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r7 = r13.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r7.lock
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r7
            r0.L$3 = r6
            r0.L$4 = r5
            r0.L$5 = r2
            r0.L$6 = r12
            r13 = 9
            r0.label = r13
            java.lang.Object r13 = r6.lock(r4, r0)
            if (r13 != r1) goto L_0x03b4
            return r1
        L_0x03b4:
            r8 = r2
        L_0x03b5:
            androidx.paging.PageFetcherSnapshotState r13 = r7.state     // Catch:{ all -> 0x03ca }
            java.util.Map r13 = r13.getFailedHintsByLoadType$paging_common()     // Catch:{ all -> 0x03ca }
            java.lang.Object r13 = r13.get(r8)     // Catch:{ all -> 0x03ca }
            androidx.paging.ViewportHint r13 = (androidx.paging.ViewportHint) r13     // Catch:{ all -> 0x03ca }
            r6.unlock(r4)
            r6 = r5
            r5 = r2
            r2 = r8
            goto L_0x03d2
        L_0x03ca:
            r12 = move-exception
            r6.unlock(r4)
            throw r12
        L_0x03cf:
            r13 = r4
            r6 = r5
            r5 = r2
        L_0x03d2:
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r6
            r0.L$3 = r4
            r0.L$4 = r4
            r0.L$5 = r4
            r0.L$6 = r4
            r7 = 10
            r0.label = r7
            java.lang.Object r12 = r12.retryLoadError(r5, r13, r0)
            if (r12 != r1) goto L_0x03eb
            return r1
        L_0x03eb:
            r12 = r6
            r6 = r9
        L_0x03ed:
            androidx.paging.LoadType r13 = androidx.paging.LoadType.REFRESH
            if (r2 != r13) goto L_0x0438
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r6.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r5 = r13.stateHolder
            kotlinx.coroutines.sync.Mutex r2 = r5.lock
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r12
            r13 = 11
            r0.label = r13
            java.lang.Object r13 = r2.lock(r4, r0)
            if (r13 != r1) goto L_0x0412
            return r1
        L_0x0412:
            androidx.paging.PageFetcherSnapshotState r13 = r5.state     // Catch:{ all -> 0x0433 }
            androidx.paging.LoadStates r13 = r13.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0433 }
            androidx.paging.LoadType r5 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0433 }
            androidx.paging.LoadState r13 = r13.get$paging_common(r5)     // Catch:{ all -> 0x0433 }
            r2.unlock(r4)
            boolean r13 = r13 instanceof androidx.paging.LoadState.Error
            if (r13 != 0) goto L_0x0438
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r6.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            kotlinx.coroutines.CoroutineScope r2 = r6.$this_launch$inlined
            r13.startConsumingHints(r2)
            goto L_0x0438
        L_0x0433:
            r12 = move-exception
            r2.unlock(r4)
            throw r12
        L_0x0438:
            r5 = r6
        L_0x0439:
            androidx.paging.LoadType r13 = androidx.paging.LoadType.APPEND
            androidx.paging.LoadState r12 = r12.getAppend()
            boolean r12 = r12 instanceof androidx.paging.LoadState.Error
            if (r12 != 0) goto L_0x0445
            goto L_0x054d
        L_0x0445:
            androidx.paging.LoadType r12 = androidx.paging.LoadType.REFRESH
            if (r13 == r12) goto L_0x0492
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r5.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r2 = r12.stateHolder
            kotlinx.coroutines.sync.Mutex r12 = r2.lock
            r0.L$0 = r5
            r0.L$1 = r13
            r0.L$2 = r2
            r0.L$3 = r12
            r6 = 12
            r0.label = r6
            java.lang.Object r6 = r12.lock(r4, r0)
            if (r6 != r1) goto L_0x046a
            return r1
        L_0x046a:
            androidx.paging.PageFetcherSnapshotState r2 = r2.state     // Catch:{ all -> 0x007a }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r6 = r5.this$0     // Catch:{ all -> 0x007a }
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r6 = r6.this$0     // Catch:{ all -> 0x007a }
            androidx.paging.PageFetcherSnapshot r6 = r6.this$0     // Catch:{ all -> 0x007a }
            r0.L$0 = r5     // Catch:{ all -> 0x007a }
            r0.L$1 = r13     // Catch:{ all -> 0x007a }
            r0.L$2 = r12     // Catch:{ all -> 0x007a }
            r0.L$3 = r4     // Catch:{ all -> 0x007a }
            r7 = 13
            r0.label = r7     // Catch:{ all -> 0x007a }
            java.lang.Object r2 = r6.setLoading(r2, r13, r0)     // Catch:{ all -> 0x007a }
            if (r2 != r1) goto L_0x0487
            return r1
        L_0x0487:
            r2 = r13
        L_0x0488:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007a }
            r12.unlock(r4)
            goto L_0x0493
        L_0x048e:
            r12.unlock(r4)
            throw r13
        L_0x0492:
            r2 = r13
        L_0x0493:
            r7 = r5
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r7.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            int[] r13 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0
            int r5 = r2.ordinal()
            r13 = r13[r5]
            if (r13 == r3) goto L_0x04e5
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r13 = r7.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r13 = r13.this$0
            androidx.paging.PageFetcherSnapshot r13 = r13.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r5 = r13.stateHolder
            kotlinx.coroutines.sync.Mutex r3 = r5.lock
            r0.L$0 = r7
            r0.L$1 = r2
            r0.L$2 = r5
            r0.L$3 = r3
            r0.L$4 = r2
            r0.L$5 = r12
            r13 = 14
            r0.label = r13
            java.lang.Object r13 = r3.lock(r4, r0)
            if (r13 != r1) goto L_0x04c9
            return r1
        L_0x04c9:
            r6 = r2
        L_0x04ca:
            androidx.paging.PageFetcherSnapshotState r13 = r5.state     // Catch:{ all -> 0x04e0 }
            java.util.Map r13 = r13.getFailedHintsByLoadType$paging_common()     // Catch:{ all -> 0x04e0 }
            java.lang.Object r13 = r13.get(r6)     // Catch:{ all -> 0x04e0 }
            androidx.paging.ViewportHint r13 = (androidx.paging.ViewportHint) r13     // Catch:{ all -> 0x04e0 }
            r3.unlock(r4)
            r3 = r2
            r2 = r13
            r13 = r12
            r12 = r6
            goto L_0x04e9
        L_0x04e0:
            r12 = move-exception
            r3.unlock(r4)
            throw r12
        L_0x04e5:
            r13 = r12
            r12 = r2
            r3 = r12
            r2 = r4
        L_0x04e9:
            r0.L$0 = r7
            r0.L$1 = r12
            r0.L$2 = r4
            r0.L$3 = r4
            r0.L$4 = r4
            r0.L$5 = r4
            r5 = 15
            r0.label = r5
            java.lang.Object r13 = r13.retryLoadError(r3, r2, r0)
            if (r13 != r1) goto L_0x0500
            return r1
        L_0x0500:
            r2 = r7
        L_0x0501:
            androidx.paging.LoadType r13 = androidx.paging.LoadType.REFRESH
            if (r12 != r13) goto L_0x054d
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r2.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r12 = r12.stateHolder
            kotlinx.coroutines.sync.Mutex r13 = r12.lock
            r0.L$0 = r2
            r0.L$1 = r12
            r0.L$2 = r13
            r3 = 16
            r0.label = r3
            java.lang.Object r0 = r13.lock(r4, r0)
            if (r0 != r1) goto L_0x0524
            return r1
        L_0x0524:
            r1 = r12
            r12 = r13
            r0 = r2
        L_0x0527:
            androidx.paging.PageFetcherSnapshotState r13 = r1.state     // Catch:{ all -> 0x0548 }
            androidx.paging.LoadStates r13 = r13.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0548 }
            androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0548 }
            androidx.paging.LoadState r13 = r13.get$paging_common(r1)     // Catch:{ all -> 0x0548 }
            r12.unlock(r4)
            boolean r12 = r13 instanceof androidx.paging.LoadState.Error
            if (r12 != 0) goto L_0x054d
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4 r12 = r0.this$0
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1 r12 = r12.this$0
            androidx.paging.PageFetcherSnapshot r12 = r12.this$0
            kotlinx.coroutines.CoroutineScope r13 = r0.$this_launch$inlined
            r12.startConsumingHints(r13)
            goto L_0x054d
        L_0x0548:
            r13 = move-exception
            r12.unlock(r4)
            throw r13
        L_0x054d:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0550:
            r13 = move-exception
            r12.unlock(r4)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0501xd8884410.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
