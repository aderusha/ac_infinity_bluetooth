package androidx.paging;

import androidx.paging.LoadState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0003\u000f\u0010\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J;\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\tJK\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0000\"\b\b\u0001\u0010\u000b*\u00020\u00022(\u0010\f\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\r0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\tJE\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0000\"\b\b\u0001\u0010\u000b*\u00020\u00022\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0001\u0003\u0012\u0013\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo27512d2 = {"Landroidx/paging/PageEvent;", "T", "", "()V", "filter", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flatMap", "R", "transform", "", "map", "Drop", "Insert", "LoadStateUpdate", "Landroidx/paging/PageEvent$Insert;", "Landroidx/paging/PageEvent$Drop;", "Landroidx/paging/PageEvent$LoadStateUpdate;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PageEvent.kt */
public abstract class PageEvent<T> {
    static /* synthetic */ Object filter$suspendImpl(PageEvent pageEvent, Function2 function2, Continuation continuation) {
        return pageEvent;
    }

    public Object filter(Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super PageEvent<T>> continuation) {
        return filter$suspendImpl(this, function2, continuation);
    }

    public <R> Object flatMap(Function2<? super T, ? super Continuation<? super Iterable<? extends R>>, ? extends Object> function2, Continuation<? super PageEvent<R>> continuation) {
        return Objects.requireNonNull(this, "null cannot be cast to non-null type androidx.paging.PageEvent<R>");
    }

    public <R> Object map(Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super PageEvent<R>> continuation) {
        return Objects.requireNonNull(this, "null cannot be cast to non-null type androidx.paging.PageEvent<R>");
    }

    private PageEvent() {
    }

    public /* synthetic */ PageEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\b\u0018\u0000 2*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u00012B;\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003J\t\u0010\u001c\u001a\u00020\rHÆ\u0003JM\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0002HÖ\u0003J;\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0$\u0012\u0006\u0012\u0004\u0018\u00010\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010%JK\u0010&\u001a\b\u0012\u0004\u0012\u0002H'0\u0003\"\b\b\u0002\u0010'*\u00020\u00022(\u0010(\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0)0$\u0012\u0006\u0012\u0004\u0018\u00010\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010%J\t\u0010*\u001a\u00020\nHÖ\u0001JE\u0010+\u001a\b\u0012\u0004\u0012\u0002H'0\u0003\"\b\b\u0002\u0010'*\u00020\u00022\"\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0$\u0012\u0006\u0012\u0004\u0018\u00010\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010%J9\u0010,\u001a\b\u0012\u0004\u0012\u0002H'0\u0000\"\b\b\u0002\u0010'*\u00020\u00022\u001e\u0010(\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0\b0-H\bJ\t\u0010.\u001a\u00020/HÖ\u0001JM\u00100\u001a\b\u0012\u0004\u0012\u0002H'0\u0000\"\b\b\u0002\u0010'*\u00020\u00022*\u0010(\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0\b0\u00070-H\bø\u0001\u0001¢\u0006\u0002\b1R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016\u0002\u000b\n\u0002\b\u0019\n\u0005\b20\u0001¨\u00063"}, mo27512d2 = {"Landroidx/paging/PageEvent$Insert;", "T", "", "Landroidx/paging/PageEvent;", "loadType", "Landroidx/paging/LoadType;", "pages", "", "Landroidx/paging/TransformablePage;", "placeholdersBefore", "", "placeholdersAfter", "combinedLoadStates", "Landroidx/paging/CombinedLoadStates;", "(Landroidx/paging/LoadType;Ljava/util/List;IILandroidx/paging/CombinedLoadStates;)V", "getCombinedLoadStates", "()Landroidx/paging/CombinedLoadStates;", "getLoadType", "()Landroidx/paging/LoadType;", "getPages", "()Ljava/util/List;", "getPlaceholdersAfter", "()I", "getPlaceholdersBefore", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "filter", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flatMap", "R", "transform", "", "hashCode", "map", "mapPages", "Lkotlin/Function1;", "toString", "", "transformPages", "transformPages$paging_common", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PageEvent.kt */
    public static final class Insert<T> extends PageEvent<T> {
        public static final Companion Companion;
        /* access modifiers changed from: private */
        public static final Insert<Object> EMPTY_REFRESH_LOCAL;
        private final CombinedLoadStates combinedLoadStates;
        private final LoadType loadType;
        private final List<TransformablePage<T>> pages;
        private final int placeholdersAfter;
        private final int placeholdersBefore;

        public static /* synthetic */ Insert copy$default(Insert insert, LoadType loadType2, List<TransformablePage<T>> list, int i, int i2, CombinedLoadStates combinedLoadStates2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                loadType2 = insert.loadType;
            }
            if ((i3 & 2) != 0) {
                list = insert.pages;
            }
            List<TransformablePage<T>> list2 = list;
            if ((i3 & 4) != 0) {
                i = insert.placeholdersBefore;
            }
            int i4 = i;
            if ((i3 & 8) != 0) {
                i2 = insert.placeholdersAfter;
            }
            int i5 = i2;
            if ((i3 & 16) != 0) {
                combinedLoadStates2 = insert.combinedLoadStates;
            }
            return insert.copy(loadType2, list2, i4, i5, combinedLoadStates2);
        }

        public final LoadType component1() {
            return this.loadType;
        }

        public final List<TransformablePage<T>> component2() {
            return this.pages;
        }

        public final int component3() {
            return this.placeholdersBefore;
        }

        public final int component4() {
            return this.placeholdersAfter;
        }

        public final CombinedLoadStates component5() {
            return this.combinedLoadStates;
        }

        public final Insert<T> copy(LoadType loadType2, List<TransformablePage<T>> list, int i, int i2, CombinedLoadStates combinedLoadStates2) {
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            Intrinsics.checkNotNullParameter(list, "pages");
            Intrinsics.checkNotNullParameter(combinedLoadStates2, "combinedLoadStates");
            return new Insert(loadType2, list, i, i2, combinedLoadStates2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Insert)) {
                return false;
            }
            Insert insert = (Insert) obj;
            return Intrinsics.areEqual((Object) this.loadType, (Object) insert.loadType) && Intrinsics.areEqual((Object) this.pages, (Object) insert.pages) && this.placeholdersBefore == insert.placeholdersBefore && this.placeholdersAfter == insert.placeholdersAfter && Intrinsics.areEqual((Object) this.combinedLoadStates, (Object) insert.combinedLoadStates);
        }

        public int hashCode() {
            LoadType loadType2 = this.loadType;
            int i = 0;
            int hashCode = (loadType2 != null ? loadType2.hashCode() : 0) * 31;
            List<TransformablePage<T>> list = this.pages;
            int hashCode2 = (((((hashCode + (list != null ? list.hashCode() : 0)) * 31) + this.placeholdersBefore) * 31) + this.placeholdersAfter) * 31;
            CombinedLoadStates combinedLoadStates2 = this.combinedLoadStates;
            if (combinedLoadStates2 != null) {
                i = combinedLoadStates2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            return "Insert(loadType=" + this.loadType + ", pages=" + this.pages + ", placeholdersBefore=" + this.placeholdersBefore + ", placeholdersAfter=" + this.placeholdersAfter + ", combinedLoadStates=" + this.combinedLoadStates + ")";
        }

        public /* synthetic */ Insert(LoadType loadType2, List list, int i, int i2, CombinedLoadStates combinedLoadStates2, DefaultConstructorMarker defaultConstructorMarker) {
            this(loadType2, list, i, i2, combinedLoadStates2);
        }

        public final LoadType getLoadType() {
            return this.loadType;
        }

        public final List<TransformablePage<T>> getPages() {
            return this.pages;
        }

        public final int getPlaceholdersBefore() {
            return this.placeholdersBefore;
        }

        public final int getPlaceholdersAfter() {
            return this.placeholdersAfter;
        }

        public final CombinedLoadStates getCombinedLoadStates() {
            return this.combinedLoadStates;
        }

        private Insert(LoadType loadType2, List<TransformablePage<T>> list, int i, int i2, CombinedLoadStates combinedLoadStates2) {
            super((DefaultConstructorMarker) null);
            this.loadType = loadType2;
            this.pages = list;
            this.placeholdersBefore = i;
            this.placeholdersAfter = i2;
            this.combinedLoadStates = combinedLoadStates2;
            boolean z = false;
            if (loadType2 == LoadType.APPEND || i >= 0) {
                if (loadType2 == LoadType.PREPEND || i2 >= 0) {
                    if (!((loadType2 != LoadType.REFRESH || (list.isEmpty() ^ true)) ? true : z)) {
                        throw new IllegalArgumentException("Cannot create a REFRESH Insert event with no TransformablePages as this could permanently stall pagination. Note that this check does not prevent empty LoadResults and is instead usually an indication of an internal error in Paging itself.".toString());
                    }
                    return;
                }
                throw new IllegalArgumentException(("Append insert defining placeholdersAfter must be > 0, but was" + ' ' + i2).toString());
            }
            throw new IllegalArgumentException(("Prepend insert defining placeholdersBefore must be > 0, but was" + ' ' + i).toString());
        }

        public final <R> Insert<R> transformPages$paging_common(Function1<? super List<TransformablePage<T>>, ? extends List<TransformablePage<R>>> function1) {
            Intrinsics.checkNotNullParameter(function1, "transform");
            return new Insert(getLoadType(), (List) function1.invoke(getPages()), getPlaceholdersBefore(), getPlaceholdersAfter(), getCombinedLoadStates(), (DefaultConstructorMarker) null);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x006e  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0094  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00bf  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x00ed  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> java.lang.Object map(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r18, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r19) {
            /*
                r17 = this;
                r0 = r19
                boolean r1 = r0 instanceof androidx.paging.PageEvent$Insert$map$1
                if (r1 == 0) goto L_0x0018
                r1 = r0
                androidx.paging.PageEvent$Insert$map$1 r1 = (androidx.paging.PageEvent$Insert$map$1) r1
                int r2 = r1.label
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r2 & r3
                if (r2 == 0) goto L_0x0018
                int r0 = r1.label
                int r0 = r0 - r3
                r1.label = r0
                r2 = r17
                goto L_0x001f
            L_0x0018:
                androidx.paging.PageEvent$Insert$map$1 r1 = new androidx.paging.PageEvent$Insert$map$1
                r2 = r17
                r1.<init>(r2, r0)
            L_0x001f:
                java.lang.Object r0 = r1.result
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r4 = r1.label
                r5 = 10
                r6 = 1
                if (r4 == 0) goto L_0x006e
                if (r4 != r6) goto L_0x0066
                java.lang.Object r4 = r1.L$10
                java.util.Collection r4 = (java.util.Collection) r4
                java.lang.Object r7 = r1.L$9
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r1.L$8
                java.util.Iterator r8 = (java.util.Iterator) r8
                java.lang.Object r9 = r1.L$7
                java.util.Collection r9 = (java.util.Collection) r9
                java.lang.Object r10 = r1.L$6
                int[] r10 = (int[]) r10
                java.lang.Object r11 = r1.L$5
                androidx.paging.TransformablePage r11 = (androidx.paging.TransformablePage) r11
                java.lang.Object r12 = r1.L$4
                java.util.Iterator r12 = (java.util.Iterator) r12
                java.lang.Object r13 = r1.L$3
                java.util.Collection r13 = (java.util.Collection) r13
                java.lang.Object r14 = r1.L$2
                androidx.paging.LoadType r14 = (androidx.paging.LoadType) r14
                java.lang.Object r15 = r1.L$1
                androidx.paging.PageEvent$Insert r15 = (androidx.paging.PageEvent.Insert) r15
                java.lang.Object r6 = r1.L$0
                kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                kotlin.ResultKt.throwOnFailure(r0)
                r16 = r11
                r11 = r8
                r8 = r14
                r14 = r9
                r9 = r15
                r15 = 1
                goto L_0x00f5
            L_0x0066:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x006e:
                kotlin.ResultKt.throwOnFailure(r0)
                androidx.paging.LoadType r0 = r17.getLoadType()
                java.util.List r4 = r17.getPages()
                java.lang.Iterable r4 = (java.lang.Iterable) r4
                java.util.ArrayList r6 = new java.util.ArrayList
                int r7 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r5)
                r6.<init>(r7)
                java.util.Collection r6 = (java.util.Collection) r6
                java.util.Iterator r4 = r4.iterator()
                r7 = r0
                r8 = r2
                r0 = r18
            L_0x008e:
                boolean r9 = r4.hasNext()
                if (r9 == 0) goto L_0x0118
                java.lang.Object r9 = r4.next()
                androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
                int[] r10 = r9.getOriginalPageOffsets()
                java.util.List r11 = r9.getData()
                java.lang.Iterable r11 = (java.lang.Iterable) r11
                java.util.ArrayList r12 = new java.util.ArrayList
                int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r5)
                r12.<init>(r13)
                java.util.Collection r12 = (java.util.Collection) r12
                java.util.Iterator r11 = r11.iterator()
                r13 = r9
                r9 = r8
                r8 = r7
                r7 = r6
                r6 = r4
                r4 = r7
            L_0x00b9:
                boolean r14 = r11.hasNext()
                if (r14 == 0) goto L_0x00ff
                java.lang.Object r14 = r11.next()
                r1.L$0 = r0
                r1.L$1 = r9
                r1.L$2 = r8
                r1.L$3 = r7
                r1.L$4 = r6
                r1.L$5 = r13
                r1.L$6 = r10
                r1.L$7 = r12
                r1.L$8 = r11
                r1.L$9 = r12
                r1.L$10 = r4
                r15 = 1
                r1.label = r15
                r16 = 6
                kotlin.jvm.internal.InlineMarker.mark((int) r16)
                java.lang.Object r14 = r0.invoke(r14, r1)
                r16 = 7
                kotlin.jvm.internal.InlineMarker.mark((int) r16)
                if (r14 != r3) goto L_0x00ed
                return r3
            L_0x00ed:
                r16 = r13
                r13 = r7
                r7 = r12
                r12 = r6
                r6 = r0
                r0 = r14
                r14 = r7
            L_0x00f5:
                r7.add(r0)
                r0 = r6
                r6 = r12
                r7 = r13
                r12 = r14
                r13 = r16
                goto L_0x00b9
            L_0x00ff:
                r15 = 1
                java.util.List r12 = (java.util.List) r12
                int r11 = r13.getHintOriginalPageOffset()
                java.util.List r13 = r13.getHintOriginalIndices()
                androidx.paging.TransformablePage r14 = new androidx.paging.TransformablePage
                r14.<init>(r10, r12, r11, r13)
                r4.add(r14)
                r4 = r6
                r6 = r7
                r7 = r8
                r8 = r9
                goto L_0x008e
            L_0x0118:
                r0 = r6
                java.util.List r0 = (java.util.List) r0
                int r9 = r8.getPlaceholdersBefore()
                int r10 = r8.getPlaceholdersAfter()
                androidx.paging.CombinedLoadStates r11 = r8.getCombinedLoadStates()
                r12 = 0
                androidx.paging.PageEvent$Insert r1 = new androidx.paging.PageEvent$Insert
                r6 = r1
                r8 = r0
                r6.<init>(r7, r8, r9, r10, r11, r12)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.map(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x009e  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00c9  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00d1  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0141 A[LOOP:0: B:30:0x0137->B:32:0x0141, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> java.lang.Object flatMap(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Iterable<? extends R>>, ? extends java.lang.Object> r18, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r19) {
            /*
                r17 = this;
                r0 = r19
                boolean r1 = r0 instanceof androidx.paging.PageEvent$Insert$flatMap$1
                if (r1 == 0) goto L_0x0018
                r1 = r0
                androidx.paging.PageEvent$Insert$flatMap$1 r1 = (androidx.paging.PageEvent$Insert$flatMap$1) r1
                int r2 = r1.label
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r2 & r3
                if (r2 == 0) goto L_0x0018
                int r0 = r1.label
                int r0 = r0 - r3
                r1.label = r0
                r2 = r17
                goto L_0x001f
            L_0x0018:
                androidx.paging.PageEvent$Insert$flatMap$1 r1 = new androidx.paging.PageEvent$Insert$flatMap$1
                r2 = r17
                r1.<init>(r2, r0)
            L_0x001f:
                java.lang.Object r0 = r1.result
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r4 = r1.label
                r5 = 1
                if (r4 == 0) goto L_0x0072
                if (r4 != r5) goto L_0x006a
                int r4 = r1.I$1
                int r6 = r1.I$0
                java.lang.Object r7 = r1.L$10
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r1.L$9
                java.util.Collection r8 = (java.util.Collection) r8
                java.lang.Object r9 = r1.L$8
                java.util.Iterator r9 = (java.util.Iterator) r9
                java.lang.Object r10 = r1.L$7
                java.util.List r10 = (java.util.List) r10
                java.lang.Object r11 = r1.L$6
                java.util.List r11 = (java.util.List) r11
                java.lang.Object r12 = r1.L$5
                androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
                java.lang.Object r13 = r1.L$4
                java.util.Iterator r13 = (java.util.Iterator) r13
                java.lang.Object r14 = r1.L$3
                java.util.Collection r14 = (java.util.Collection) r14
                java.lang.Object r15 = r1.L$2
                androidx.paging.LoadType r15 = (androidx.paging.LoadType) r15
                java.lang.Object r5 = r1.L$1
                androidx.paging.PageEvent$Insert r5 = (androidx.paging.PageEvent.Insert) r5
                java.lang.Object r2 = r1.L$0
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                kotlin.ResultKt.throwOnFailure(r0)
                r16 = r12
                r12 = r11
                r11 = r10
                r10 = r9
                r9 = r6
                r6 = r5
                r5 = r3
                r3 = 1
                goto L_0x0120
            L_0x006a:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x0072:
                kotlin.ResultKt.throwOnFailure(r0)
                androidx.paging.LoadType r0 = r17.getLoadType()
                java.util.List r2 = r17.getPages()
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r4 = new java.util.ArrayList
                r5 = 10
                int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r5)
                r4.<init>(r5)
                java.util.Collection r4 = (java.util.Collection) r4
                java.util.Iterator r2 = r2.iterator()
                r6 = r17
                r5 = r4
                r4 = r3
                r3 = r2
                r2 = r0
                r0 = r18
            L_0x0098:
                boolean r7 = r3.hasNext()
                if (r7 == 0) goto L_0x016d
                java.lang.Object r7 = r3.next()
                androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.List r8 = (java.util.List) r8
                java.util.ArrayList r9 = new java.util.ArrayList
                r9.<init>()
                java.util.List r9 = (java.util.List) r9
                java.util.List r10 = r7.getData()
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                r11 = 0
                java.util.Iterator r10 = r10.iterator()
                r12 = r7
                r11 = r9
                r9 = r10
                r7 = r5
                r10 = r8
                r8 = 0
            L_0x00c3:
                boolean r13 = r9.hasNext()
                if (r13 == 0) goto L_0x0156
                java.lang.Object r13 = r9.next()
                int r14 = r8 + 1
                if (r8 >= 0) goto L_0x00d4
                kotlin.collections.CollectionsKt.throwIndexOverflow()
            L_0x00d4:
                java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
                java.lang.Number r8 = (java.lang.Number) r8
                int r8 = r8.intValue()
                r15 = r10
                java.util.Collection r15 = (java.util.Collection) r15
                r1.L$0 = r0
                r1.L$1 = r6
                r1.L$2 = r2
                r1.L$3 = r5
                r1.L$4 = r3
                r1.L$5 = r12
                r1.L$6 = r11
                r1.L$7 = r10
                r1.L$8 = r9
                r1.L$9 = r15
                r1.L$10 = r7
                r1.I$0 = r14
                r1.I$1 = r8
                r18 = r3
                r3 = 1
                r1.label = r3
                r16 = 6
                kotlin.jvm.internal.InlineMarker.mark((int) r16)
                java.lang.Object r13 = r0.invoke(r13, r1)
                r16 = 7
                kotlin.jvm.internal.InlineMarker.mark((int) r16)
                if (r13 != r4) goto L_0x0111
                return r4
            L_0x0111:
                r16 = r12
                r12 = r11
                r11 = r10
                r10 = r9
                r9 = r14
                r14 = r5
                r5 = r4
                r4 = r8
                r8 = r15
                r15 = r2
                r2 = r0
                r0 = r13
                r13 = r18
            L_0x0120:
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                kotlin.collections.CollectionsKt.addAll(r8, r0)
                java.util.List r0 = r16.getHintOriginalIndices()
                if (r0 == 0) goto L_0x0137
                java.lang.Object r0 = r0.get(r4)
                java.lang.Integer r0 = (java.lang.Integer) r0
                if (r0 == 0) goto L_0x0137
                int r4 = r0.intValue()
            L_0x0137:
                int r0 = r12.size()
                int r8 = r11.size()
                if (r0 >= r8) goto L_0x0149
                java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
                r12.add(r0)
                goto L_0x0137
            L_0x0149:
                r0 = r2
                r4 = r5
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                r3 = r13
                r5 = r14
                r2 = r15
                r12 = r16
                goto L_0x00c3
            L_0x0156:
                r18 = r3
                r3 = 1
                androidx.paging.TransformablePage r8 = new androidx.paging.TransformablePage
                int[] r9 = r12.getOriginalPageOffsets()
                int r12 = r12.getHintOriginalPageOffset()
                r8.<init>(r9, r10, r12, r11)
                r7.add(r8)
                r3 = r18
                goto L_0x0098
            L_0x016d:
                r3 = r5
                java.util.List r3 = (java.util.List) r3
                int r4 = r6.getPlaceholdersBefore()
                int r5 = r6.getPlaceholdersAfter()
                androidx.paging.CombinedLoadStates r6 = r6.getCombinedLoadStates()
                r7 = 0
                androidx.paging.PageEvent$Insert r0 = new androidx.paging.PageEvent$Insert
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.flatMap(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x009e  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00c9  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00d1  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x010c  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0126  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object filter(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r20, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<T>> r21) {
            /*
                r19 = this;
                r0 = r21
                boolean r1 = r0 instanceof androidx.paging.PageEvent$Insert$filter$1
                if (r1 == 0) goto L_0x0018
                r1 = r0
                androidx.paging.PageEvent$Insert$filter$1 r1 = (androidx.paging.PageEvent$Insert$filter$1) r1
                int r2 = r1.label
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r2 & r3
                if (r2 == 0) goto L_0x0018
                int r0 = r1.label
                int r0 = r0 - r3
                r1.label = r0
                r2 = r19
                goto L_0x001f
            L_0x0018:
                androidx.paging.PageEvent$Insert$filter$1 r1 = new androidx.paging.PageEvent$Insert$filter$1
                r2 = r19
                r1.<init>(r2, r0)
            L_0x001f:
                java.lang.Object r0 = r1.result
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r4 = r1.label
                r5 = 1
                if (r4 == 0) goto L_0x0072
                if (r4 != r5) goto L_0x006a
                int r4 = r1.I$1
                int r6 = r1.I$0
                java.lang.Object r7 = r1.L$10
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r1.L$9
                java.lang.Object r9 = r1.L$8
                java.util.Iterator r9 = (java.util.Iterator) r9
                java.lang.Object r10 = r1.L$7
                java.util.List r10 = (java.util.List) r10
                java.lang.Object r11 = r1.L$6
                java.util.List r11 = (java.util.List) r11
                java.lang.Object r12 = r1.L$5
                androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
                java.lang.Object r13 = r1.L$4
                java.util.Iterator r13 = (java.util.Iterator) r13
                java.lang.Object r14 = r1.L$3
                java.util.Collection r14 = (java.util.Collection) r14
                java.lang.Object r15 = r1.L$2
                androidx.paging.LoadType r15 = (androidx.paging.LoadType) r15
                java.lang.Object r5 = r1.L$1
                androidx.paging.PageEvent$Insert r5 = (androidx.paging.PageEvent.Insert) r5
                java.lang.Object r2 = r1.L$0
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                kotlin.ResultKt.throwOnFailure(r0)
                r17 = r5
                r5 = r3
                r3 = r13
                r13 = r12
                r12 = r11
                r11 = r10
                r10 = r9
                r9 = r6
                r6 = r17
                goto L_0x011e
            L_0x006a:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x0072:
                kotlin.ResultKt.throwOnFailure(r0)
                androidx.paging.LoadType r0 = r19.getLoadType()
                java.util.List r2 = r19.getPages()
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r4 = new java.util.ArrayList
                r5 = 10
                int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r5)
                r4.<init>(r5)
                java.util.Collection r4 = (java.util.Collection) r4
                java.util.Iterator r2 = r2.iterator()
                r6 = r19
                r5 = r4
                r4 = r3
                r3 = r2
                r2 = r0
                r0 = r20
            L_0x0098:
                boolean r7 = r3.hasNext()
                if (r7 == 0) goto L_0x015f
                java.lang.Object r7 = r3.next()
                androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.List r8 = (java.util.List) r8
                java.util.ArrayList r9 = new java.util.ArrayList
                r9.<init>()
                java.util.List r9 = (java.util.List) r9
                java.util.List r10 = r7.getData()
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                r11 = 0
                java.util.Iterator r10 = r10.iterator()
                r12 = r7
                r11 = r9
                r9 = r10
                r7 = r5
                r10 = r8
                r8 = 0
            L_0x00c3:
                boolean r13 = r9.hasNext()
                if (r13 == 0) goto L_0x014d
                java.lang.Object r13 = r9.next()
                int r14 = r8 + 1
                if (r8 >= 0) goto L_0x00d4
                kotlin.collections.CollectionsKt.throwIndexOverflow()
            L_0x00d4:
                java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
                java.lang.Number r8 = (java.lang.Number) r8
                int r8 = r8.intValue()
                r1.L$0 = r0
                r1.L$1 = r6
                r1.L$2 = r2
                r1.L$3 = r5
                r1.L$4 = r3
                r1.L$5 = r12
                r1.L$6 = r11
                r1.L$7 = r10
                r1.L$8 = r9
                r1.L$9 = r13
                r1.L$10 = r7
                r1.I$0 = r14
                r1.I$1 = r8
                r15 = 1
                r1.label = r15
                r16 = 6
                kotlin.jvm.internal.InlineMarker.mark((int) r16)
                java.lang.Object r15 = r0.invoke(r13, r1)
                r16 = 7
                kotlin.jvm.internal.InlineMarker.mark((int) r16)
                if (r15 != r4) goto L_0x010c
                return r4
            L_0x010c:
                r17 = r2
                r2 = r0
                r0 = r15
                r15 = r17
                r18 = r5
                r5 = r4
                r4 = r8
                r8 = r13
                r13 = r12
                r12 = r11
                r11 = r10
                r10 = r9
                r9 = r14
                r14 = r18
            L_0x011e:
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto L_0x0142
                r11.add(r8)
                java.util.List r0 = r13.getHintOriginalIndices()
                if (r0 == 0) goto L_0x013b
                java.lang.Object r0 = r0.get(r4)
                java.lang.Integer r0 = (java.lang.Integer) r0
                if (r0 == 0) goto L_0x013b
                int r4 = r0.intValue()
            L_0x013b:
                java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
                r12.add(r0)
            L_0x0142:
                r0 = r2
                r4 = r5
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                r12 = r13
                r5 = r14
                r2 = r15
                goto L_0x00c3
            L_0x014d:
                androidx.paging.TransformablePage r8 = new androidx.paging.TransformablePage
                int[] r9 = r12.getOriginalPageOffsets()
                int r12 = r12.getHintOriginalPageOffset()
                r8.<init>(r9, r10, r12, r11)
                r7.add(r8)
                goto L_0x0098
            L_0x015f:
                r3 = r5
                java.util.List r3 = (java.util.List) r3
                int r4 = r6.getPlaceholdersBefore()
                int r5 = r6.getPlaceholdersAfter()
                androidx.paging.CombinedLoadStates r6 = r6.getCombinedLoadStates()
                r7 = 0
                androidx.paging.PageEvent$Insert r0 = new androidx.paging.PageEvent$Insert
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.filter(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\b\b\u0002\u0010\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ:\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\b\b\u0002\u0010\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000b0\n2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJB\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\b\b\u0002\u0010\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000b0\n2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, mo27512d2 = {"Landroidx/paging/PageEvent$Insert$Companion;", "", "()V", "EMPTY_REFRESH_LOCAL", "Landroidx/paging/PageEvent$Insert;", "getEMPTY_REFRESH_LOCAL", "()Landroidx/paging/PageEvent$Insert;", "Append", "T", "pages", "", "Landroidx/paging/TransformablePage;", "placeholdersAfter", "", "combinedLoadStates", "Landroidx/paging/CombinedLoadStates;", "Prepend", "placeholdersBefore", "Refresh", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PageEvent.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final <T> Insert<T> Refresh(List<TransformablePage<T>> list, int i, int i2, CombinedLoadStates combinedLoadStates) {
                Intrinsics.checkNotNullParameter(list, "pages");
                Intrinsics.checkNotNullParameter(combinedLoadStates, "combinedLoadStates");
                return new Insert(LoadType.REFRESH, list, i, i2, combinedLoadStates, (DefaultConstructorMarker) null);
            }

            public final <T> Insert<T> Prepend(List<TransformablePage<T>> list, int i, CombinedLoadStates combinedLoadStates) {
                Intrinsics.checkNotNullParameter(list, "pages");
                Intrinsics.checkNotNullParameter(combinedLoadStates, "combinedLoadStates");
                return new Insert(LoadType.PREPEND, list, i, -1, combinedLoadStates, (DefaultConstructorMarker) null);
            }

            public final <T> Insert<T> Append(List<TransformablePage<T>> list, int i, CombinedLoadStates combinedLoadStates) {
                Intrinsics.checkNotNullParameter(list, "pages");
                Intrinsics.checkNotNullParameter(combinedLoadStates, "combinedLoadStates");
                return new Insert(LoadType.APPEND, list, -1, i, combinedLoadStates, (DefaultConstructorMarker) null);
            }

            public final Insert<Object> getEMPTY_REFRESH_LOCAL() {
                return Insert.EMPTY_REFRESH_LOCAL;
            }
        }

        static {
            Companion companion = new Companion((DefaultConstructorMarker) null);
            Companion = companion;
            EMPTY_REFRESH_LOCAL = companion.Refresh(CollectionsKt.listOf(TransformablePage.Companion.getEMPTY_INITIAL_PAGE()), 0, 0, new CombinedLoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), new LoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common()), (LoadStates) null, 16, (DefaultConstructorMarker) null));
        }

        private final <R> Insert<R> mapPages(Function1<? super TransformablePage<T>, TransformablePage<R>> function1) {
            LoadType loadType2 = getLoadType();
            Iterable<Object> pages2 = getPages();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(pages2, 10));
            for (Object invoke : pages2) {
                arrayList.add(function1.invoke(invoke));
            }
            return new Insert(loadType2, (List) arrayList, getPlaceholdersBefore(), getPlaceholdersAfter(), getCombinedLoadStates(), (DefaultConstructorMarker) null);
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J7\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, mo27512d2 = {"Landroidx/paging/PageEvent$Drop;", "T", "", "Landroidx/paging/PageEvent;", "loadType", "Landroidx/paging/LoadType;", "minPageOffset", "", "maxPageOffset", "placeholdersRemaining", "(Landroidx/paging/LoadType;III)V", "getLoadType", "()Landroidx/paging/LoadType;", "getMaxPageOffset", "()I", "getMinPageOffset", "pageCount", "getPageCount", "getPlaceholdersRemaining", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PageEvent.kt */
    public static final class Drop<T> extends PageEvent<T> {
        private final LoadType loadType;
        private final int maxPageOffset;
        private final int minPageOffset;
        private final int placeholdersRemaining;

        public static /* synthetic */ Drop copy$default(Drop drop, LoadType loadType2, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                loadType2 = drop.loadType;
            }
            if ((i4 & 2) != 0) {
                i = drop.minPageOffset;
            }
            if ((i4 & 4) != 0) {
                i2 = drop.maxPageOffset;
            }
            if ((i4 & 8) != 0) {
                i3 = drop.placeholdersRemaining;
            }
            return drop.copy(loadType2, i, i2, i3);
        }

        public final LoadType component1() {
            return this.loadType;
        }

        public final int component2() {
            return this.minPageOffset;
        }

        public final int component3() {
            return this.maxPageOffset;
        }

        public final int component4() {
            return this.placeholdersRemaining;
        }

        public final Drop<T> copy(LoadType loadType2, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            return new Drop<>(loadType2, i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Drop)) {
                return false;
            }
            Drop drop = (Drop) obj;
            return Intrinsics.areEqual((Object) this.loadType, (Object) drop.loadType) && this.minPageOffset == drop.minPageOffset && this.maxPageOffset == drop.maxPageOffset && this.placeholdersRemaining == drop.placeholdersRemaining;
        }

        public int hashCode() {
            LoadType loadType2 = this.loadType;
            return ((((((loadType2 != null ? loadType2.hashCode() : 0) * 31) + this.minPageOffset) * 31) + this.maxPageOffset) * 31) + this.placeholdersRemaining;
        }

        public String toString() {
            return "Drop(loadType=" + this.loadType + ", minPageOffset=" + this.minPageOffset + ", maxPageOffset=" + this.maxPageOffset + ", placeholdersRemaining=" + this.placeholdersRemaining + ")";
        }

        public final LoadType getLoadType() {
            return this.loadType;
        }

        public final int getMinPageOffset() {
            return this.minPageOffset;
        }

        public final int getMaxPageOffset() {
            return this.maxPageOffset;
        }

        public final int getPlaceholdersRemaining() {
            return this.placeholdersRemaining;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Drop(LoadType loadType2, int i, int i2, int i3) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            this.loadType = loadType2;
            this.minPageOffset = i;
            this.maxPageOffset = i2;
            this.placeholdersRemaining = i3;
            boolean z = true;
            if (loadType2 != LoadType.REFRESH) {
                if (getPageCount() > 0) {
                    if (!(i3 < 0 ? false : z)) {
                        throw new IllegalArgumentException(("Invalid placeholdersRemaining " + i3).toString());
                    }
                    return;
                }
                throw new IllegalArgumentException(("Drop count must be > 0, but was " + getPageCount()).toString());
            }
            throw new IllegalArgumentException("Drop load type must be PREPEND or APPEND".toString());
        }

        public final int getPageCount() {
            return (this.maxPageOffset - this.minPageOffset) + 1;
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001b*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001bB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0013\u001a\u00020\tHÆ\u0003J-\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, mo27512d2 = {"Landroidx/paging/PageEvent$LoadStateUpdate;", "T", "", "Landroidx/paging/PageEvent;", "loadType", "Landroidx/paging/LoadType;", "fromMediator", "", "loadState", "Landroidx/paging/LoadState;", "(Landroidx/paging/LoadType;ZLandroidx/paging/LoadState;)V", "getFromMediator", "()Z", "getLoadState", "()Landroidx/paging/LoadState;", "getLoadType", "()Landroidx/paging/LoadType;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PageEvent.kt */
    public static final class LoadStateUpdate<T> extends PageEvent<T> {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final boolean fromMediator;
        private final LoadState loadState;
        private final LoadType loadType;

        public static /* synthetic */ LoadStateUpdate copy$default(LoadStateUpdate loadStateUpdate, LoadType loadType2, boolean z, LoadState loadState2, int i, Object obj) {
            if ((i & 1) != 0) {
                loadType2 = loadStateUpdate.loadType;
            }
            if ((i & 2) != 0) {
                z = loadStateUpdate.fromMediator;
            }
            if ((i & 4) != 0) {
                loadState2 = loadStateUpdate.loadState;
            }
            return loadStateUpdate.copy(loadType2, z, loadState2);
        }

        public final LoadType component1() {
            return this.loadType;
        }

        public final boolean component2() {
            return this.fromMediator;
        }

        public final LoadState component3() {
            return this.loadState;
        }

        public final LoadStateUpdate<T> copy(LoadType loadType2, boolean z, LoadState loadState2) {
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            Intrinsics.checkNotNullParameter(loadState2, "loadState");
            return new LoadStateUpdate<>(loadType2, z, loadState2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LoadStateUpdate)) {
                return false;
            }
            LoadStateUpdate loadStateUpdate = (LoadStateUpdate) obj;
            return Intrinsics.areEqual((Object) this.loadType, (Object) loadStateUpdate.loadType) && this.fromMediator == loadStateUpdate.fromMediator && Intrinsics.areEqual((Object) this.loadState, (Object) loadStateUpdate.loadState);
        }

        public int hashCode() {
            LoadType loadType2 = this.loadType;
            int i = 0;
            int hashCode = (loadType2 != null ? loadType2.hashCode() : 0) * 31;
            boolean z = this.fromMediator;
            if (z) {
                z = true;
            }
            int i2 = (hashCode + (z ? 1 : 0)) * 31;
            LoadState loadState2 = this.loadState;
            if (loadState2 != null) {
                i = loadState2.hashCode();
            }
            return i2 + i;
        }

        public String toString() {
            return "LoadStateUpdate(loadType=" + this.loadType + ", fromMediator=" + this.fromMediator + ", loadState=" + this.loadState + ")";
        }

        public final LoadType getLoadType() {
            return this.loadType;
        }

        public final boolean getFromMediator() {
            return this.fromMediator;
        }

        public final LoadState getLoadState() {
            return this.loadState;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LoadStateUpdate(LoadType loadType2, boolean z, LoadState loadState2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            Intrinsics.checkNotNullParameter(loadState2, "loadState");
            this.loadType = loadType2;
            this.fromMediator = z;
            this.loadState = loadState2;
            if (!(loadType2 != LoadType.REFRESH || z || !(loadState2 instanceof LoadState.NotLoading) || !loadState2.getEndOfPaginationReached())) {
                throw new IllegalArgumentException("LoadStateUpdate for local REFRESH may not set endOfPaginationReached = true".toString());
            } else if (!Companion.canDispatchWithoutInsert$paging_common(loadState2, z)) {
                throw new IllegalArgumentException("LoadStateUpdates cannot be used to dispatch NotLoading unless it is from remote mediator and remote mediator reached end of pagination.".toString());
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\b¨\u0006\t"}, mo27512d2 = {"Landroidx/paging/PageEvent$LoadStateUpdate$Companion;", "", "()V", "canDispatchWithoutInsert", "", "loadState", "Landroidx/paging/LoadState;", "fromMediator", "canDispatchWithoutInsert$paging_common", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PageEvent.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final boolean canDispatchWithoutInsert$paging_common(LoadState loadState, boolean z) {
                Intrinsics.checkNotNullParameter(loadState, "loadState");
                return (loadState instanceof LoadState.Loading) || (loadState instanceof LoadState.Error) || z;
            }
        }
    }
}
