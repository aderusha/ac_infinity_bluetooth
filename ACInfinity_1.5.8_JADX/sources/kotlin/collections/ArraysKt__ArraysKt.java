package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(mo27511d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a;\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!\u0002\u0007\n\u0005\b20\u0001¨\u0006\""}, mo27512d2 = {"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 1, mo27517xs = "kotlin/collections/ArraysKt")
/* compiled from: Arrays.kt */
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    public static final <T> List<T> flatten(T[][] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "$this$flatten");
        int i = 0;
        for (Object obj : (Object[]) tArr) {
            i += ((Object[]) obj).length;
        }
        ArrayList arrayList = new ArrayList(i);
        for (T[] addAll : tArr) {
            CollectionsKt.addAll(arrayList, addAll);
        }
        return arrayList;
    }

    public static final <T, R> Pair<List<T>, List<R>> unzip(Pair<? extends T, ? extends R>[] pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "$this$unzip");
        ArrayList arrayList = new ArrayList(pairArr.length);
        ArrayList arrayList2 = new ArrayList(pairArr.length);
        for (Pair<? extends T, ? extends R> pair : pairArr) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.m993to(arrayList, arrayList2);
    }

    private static final boolean isNullOrEmpty(Object[] objArr) {
        if (objArr != null) {
            return objArr.length == 0;
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [R, C] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <C extends java.lang.Object & R, R> R ifEmpty(C r1, kotlin.jvm.functions.Function0<? extends R> r2) {
        /*
            int r0 = r1.length
            if (r0 != 0) goto L_0x0005
            r0 = 1
            goto L_0x0006
        L_0x0005:
            r0 = 0
        L_0x0006:
            if (r0 == 0) goto L_0x000c
            java.lang.Object r1 = r2.invoke()
        L_0x000c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.ArraysKt__ArraysKt.ifEmpty(java.lang.Object[], kotlin.jvm.functions.Function0):java.lang.Object");
    }

    public static final <T> boolean contentDeepEquals(T[] tArr, T[] tArr2) {
        if (tArr == tArr2) {
            return true;
        }
        if (tArr == null || tArr2 == null || tArr.length != tArr2.length) {
            return false;
        }
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            T t = tArr[i];
            T t2 = tArr2[i];
            if (t != t2) {
                if (t == null || t2 == null) {
                    return false;
                }
                if (!(t instanceof Object[]) || !(t2 instanceof Object[])) {
                    if (!(t instanceof byte[]) || !(t2 instanceof byte[])) {
                        if (!(t instanceof short[]) || !(t2 instanceof short[])) {
                            if (!(t instanceof int[]) || !(t2 instanceof int[])) {
                                if (!(t instanceof long[]) || !(t2 instanceof long[])) {
                                    if (!(t instanceof float[]) || !(t2 instanceof float[])) {
                                        if (!(t instanceof double[]) || !(t2 instanceof double[])) {
                                            if (!(t instanceof char[]) || !(t2 instanceof char[])) {
                                                if (!(t instanceof boolean[]) || !(t2 instanceof boolean[])) {
                                                    if (!(t instanceof UByteArray) || !(t2 instanceof UByteArray)) {
                                                        if (!(t instanceof UShortArray) || !(t2 instanceof UShortArray)) {
                                                            if (!(t instanceof UIntArray) || !(t2 instanceof UIntArray)) {
                                                                if (!(t instanceof ULongArray) || !(t2 instanceof ULongArray)) {
                                                                    if (!Intrinsics.areEqual((Object) t, (Object) t2)) {
                                                                        return false;
                                                                    }
                                                                } else if (!UArraysKt.m1571contentEqualslec5QzE(((ULongArray) t).m1263unboximpl(), ((ULongArray) t2).m1263unboximpl())) {
                                                                    return false;
                                                                }
                                                            } else if (!UArraysKt.m1567contentEqualsKJPZfPQ(((UIntArray) t).m1185unboximpl(), ((UIntArray) t2).m1185unboximpl())) {
                                                                return false;
                                                            }
                                                        } else if (!UArraysKt.m1566contentEqualsFGO6Aew(((UShortArray) t).m1367unboximpl(), ((UShortArray) t2).m1367unboximpl())) {
                                                            return false;
                                                        }
                                                    } else if (!UArraysKt.m1569contentEqualskV0jMPg(((UByteArray) t).m1107unboximpl(), ((UByteArray) t2).m1107unboximpl())) {
                                                        return false;
                                                    }
                                                } else if (!Arrays.equals((boolean[]) t, (boolean[]) t2)) {
                                                    return false;
                                                }
                                            } else if (!Arrays.equals((char[]) t, (char[]) t2)) {
                                                return false;
                                            }
                                        } else if (!Arrays.equals((double[]) t, (double[]) t2)) {
                                            return false;
                                        }
                                    } else if (!Arrays.equals((float[]) t, (float[]) t2)) {
                                        return false;
                                    }
                                } else if (!Arrays.equals((long[]) t, (long[]) t2)) {
                                    return false;
                                }
                            } else if (!Arrays.equals((int[]) t, (int[]) t2)) {
                                return false;
                            }
                        } else if (!Arrays.equals((short[]) t, (short[]) t2)) {
                            return false;
                        }
                    } else if (!Arrays.equals((byte[]) t, (byte[]) t2)) {
                        return false;
                    }
                } else if (!ArraysKt.contentDeepEquals((Object[]) t, (Object[]) t2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final <T> String contentDeepToString(T[] tArr) {
        if (tArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder((RangesKt.coerceAtMost(tArr.length, 429496729) * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(tArr, sb, new ArrayList());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: long[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(T[] r5, java.lang.StringBuilder r6, java.util.List<java.lang.Object[]> r7) {
        /*
            boolean r0 = r7.contains(r5)
            if (r0 == 0) goto L_0x000c
            java.lang.String r5 = "[...]"
            r6.append(r5)
            return
        L_0x000c:
            r7.add(r5)
            r0 = 91
            r6.append(r0)
            r0 = 0
            int r1 = r5.length
        L_0x0016:
            if (r0 >= r1) goto L_0x0121
            if (r0 == 0) goto L_0x001f
            java.lang.String r2 = ", "
            r6.append(r2)
        L_0x001f:
            r2 = r5[r0]
            if (r2 != 0) goto L_0x002a
            java.lang.String r2 = "null"
            r6.append(r2)
            goto L_0x011d
        L_0x002a:
            boolean r3 = r2 instanceof java.lang.Object[]
            if (r3 == 0) goto L_0x0035
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            contentDeepToStringInternal$ArraysKt__ArraysKt(r2, r6, r7)
            goto L_0x011d
        L_0x0035:
            boolean r3 = r2 instanceof byte[]
            java.lang.String r4 = "java.util.Arrays.toString(this)"
            if (r3 == 0) goto L_0x0049
            byte[] r2 = (byte[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x0049:
            boolean r3 = r2 instanceof short[]
            if (r3 == 0) goto L_0x005b
            short[] r2 = (short[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x005b:
            boolean r3 = r2 instanceof int[]
            if (r3 == 0) goto L_0x006d
            int[] r2 = (int[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x006d:
            boolean r3 = r2 instanceof long[]
            if (r3 == 0) goto L_0x007f
            long[] r2 = (long[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x007f:
            boolean r3 = r2 instanceof float[]
            if (r3 == 0) goto L_0x0091
            float[] r2 = (float[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x0091:
            boolean r3 = r2 instanceof double[]
            if (r3 == 0) goto L_0x00a3
            double[] r2 = (double[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x00a3:
            boolean r3 = r2 instanceof char[]
            if (r3 == 0) goto L_0x00b4
            char[] r2 = (char[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x00b4:
            boolean r3 = r2 instanceof boolean[]
            if (r3 == 0) goto L_0x00c5
            boolean[] r2 = (boolean[]) r2
            java.lang.String r2 = java.util.Arrays.toString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r6.append(r2)
            goto L_0x011d
        L_0x00c5:
            boolean r3 = r2 instanceof kotlin.UByteArray
            r4 = 0
            if (r3 == 0) goto L_0x00da
            kotlin.UByteArray r2 = (kotlin.UByteArray) r2
            if (r2 == 0) goto L_0x00d2
            byte[] r4 = r2.m1107unboximpl()
        L_0x00d2:
            java.lang.String r2 = kotlin.collections.unsigned.UArraysKt.m1583contentToString2csIQuQ(r4)
            r6.append(r2)
            goto L_0x011d
        L_0x00da:
            boolean r3 = r2 instanceof kotlin.UShortArray
            if (r3 == 0) goto L_0x00ee
            kotlin.UShortArray r2 = (kotlin.UShortArray) r2
            if (r2 == 0) goto L_0x00e6
            short[] r4 = r2.m1367unboximpl()
        L_0x00e6:
            java.lang.String r2 = kotlin.collections.unsigned.UArraysKt.m1587contentToStringd6D3K8(r4)
            r6.append(r2)
            goto L_0x011d
        L_0x00ee:
            boolean r3 = r2 instanceof kotlin.UIntArray
            if (r3 == 0) goto L_0x0102
            kotlin.UIntArray r2 = (kotlin.UIntArray) r2
            if (r2 == 0) goto L_0x00fa
            int[] r4 = r2.m1185unboximpl()
        L_0x00fa:
            java.lang.String r2 = kotlin.collections.unsigned.UArraysKt.m1586contentToStringXUkPCBk(r4)
            r6.append(r2)
            goto L_0x011d
        L_0x0102:
            boolean r3 = r2 instanceof kotlin.ULongArray
            if (r3 == 0) goto L_0x0116
            kotlin.ULongArray r2 = (kotlin.ULongArray) r2
            if (r2 == 0) goto L_0x010e
            long[] r4 = r2.m1263unboximpl()
        L_0x010e:
            java.lang.String r2 = kotlin.collections.unsigned.UArraysKt.m1589contentToStringuLth9ew(r4)
            r6.append(r2)
            goto L_0x011d
        L_0x0116:
            java.lang.String r2 = r2.toString()
            r6.append(r2)
        L_0x011d:
            int r0 = r0 + 1
            goto L_0x0016
        L_0x0121:
            r5 = 93
            r6.append(r5)
            int r5 = kotlin.collections.CollectionsKt.getLastIndex(r7)
            r7.remove(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.ArraysKt__ArraysKt.contentDeepToStringInternal$ArraysKt__ArraysKt(java.lang.Object[], java.lang.StringBuilder, java.util.List):void");
    }
}
