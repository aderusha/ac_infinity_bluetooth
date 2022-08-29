package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(mo27511d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001aJ\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\bø\u0001\u0000\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\bø\u0001\u0000\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\b\u001aJ\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\bø\u0001\u0000\u001a[\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a]\u0010\u0014\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\\\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\bø\u0001\u0000\u001aa\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\bø\u0001\u0000¢\u0006\u0002\b\u0017\u001au\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aw\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001c\u001aJ\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010 \u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0\u0006H\bø\u0001\u0000\u001aV\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000\u001a\\\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\bø\u0001\u0000\u001au\u0010#\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010$\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ah\u0010%\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000\u001ah\u0010(\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000\u001a_\u0010)\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aJ\u0010)\u001a\u00020+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\bø\u0001\u0000\u001aJ\u0010)\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\bø\u0001\u0000\u001aa\u0010-\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aQ\u0010-\u001a\u0004\u0018\u00010+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010.\u001aQ\u0010-\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aq\u00100\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u00104\u001as\u00105\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u00104\u001ai\u00106\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\b\u001ai\u00107\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\b\u001ah\u00108\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000\u001ah\u00109\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000\u001a_\u0010:\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aJ\u0010:\u001a\u00020+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\bø\u0001\u0000\u001aJ\u0010:\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\bø\u0001\u0000\u001aa\u0010;\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aQ\u0010;\u001a\u0004\u0018\u00010+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010.\u001aQ\u0010;\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aq\u0010<\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u00104\u001as\u0010=\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\bø\u0001\u0000¢\u0006\u0002\u00104\u001ah\u0010>\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\u0007\u001ai\u0010?\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\b\u001a$\u0010@\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010@\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\bø\u0001\u0000\u001aY\u0010A\u001a\u0002HB\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010B*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HB2\u001e\u0010 \u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010C\u001an\u0010D\u001a\u0002HB\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010B*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HB23\u0010 \u001a/\u0012\u0013\u0012\u00110\u000e¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(H\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0EH\bø\u0001\u0000¢\u0006\u0002\u0010I\u001a6\u0010J\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030K0\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006L"}, mo27512d2 = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "firstNotNullOf", "R", "", "transform", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullOfOrNull", "flatMap", "", "flatMapSequence", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "forEach", "", "action", "map", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxByOrNull", "maxOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "", "", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrNull", "minBy", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 1, mo27517xs = "kotlin/collections/MapsKt")
/* compiled from: _Maps.kt */
class MapsKt___MapsKt extends MapsKt__MapsKt {
    private static final <K, V, R> R firstNotNullOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        R r;
        Iterator<Map.Entry<? extends K, ? extends V>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                r = null;
                break;
            }
            r = function1.invoke(it.next());
            if (r != null) {
                break;
            }
        }
        if (r != null) {
            return r;
        }
        throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
    }

    private static final <K, V, R> R firstNotNullOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            R invoke2 = function1.invoke(invoke);
            if (invoke2 != null) {
                return invoke2;
            }
        }
        return null;
    }

    public static final <K, V> List<Pair<K, V>> toList(Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "$this$toList");
        if (map.size() == 0) {
            return CollectionsKt.emptyList();
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return CollectionsKt.emptyList();
        }
        Map.Entry next = it.next();
        if (!it.hasNext()) {
            return CollectionsKt.listOf(new Pair(next.getKey(), next.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new Pair(next.getKey(), next.getValue()));
        do {
            Map.Entry next2 = it.next();
            arrayList.add(new Pair(next2.getKey(), next2.getValue()));
        } while (it.hasNext());
        return arrayList;
    }

    public static final <K, V, R> List<R> flatMap(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$flatMap");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Collection arrayList = new ArrayList();
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            CollectionsKt.addAll(arrayList, (Iterable) function1.invoke(invoke));
        }
        return (List) arrayList;
    }

    public static final <K, V, R> List<R> flatMapSequence(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$flatMap");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Collection arrayList = new ArrayList();
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            CollectionsKt.addAll(arrayList, (Sequence) function1.invoke(invoke));
        }
        return (List) arrayList;
    }

    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(c, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            CollectionsKt.addAll(c, (Iterable) function1.invoke(invoke));
        }
        return c;
    }

    public static final <K, V, R, C extends Collection<? super R>> C flatMapSequenceTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(c, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            CollectionsKt.addAll(c, (Sequence) function1.invoke(invoke));
        }
        return c;
    }

    public static final <K, V, R> List<R> map(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Collection arrayList = new ArrayList(map.size());
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            arrayList.add(function1.invoke(invoke));
        }
        return (List) arrayList;
    }

    public static final <K, V, R> List<R> mapNotNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$mapNotNull");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Collection arrayList = new ArrayList();
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            Object invoke2 = function1.invoke(invoke);
            if (invoke2 != null) {
                arrayList.add(invoke2);
            }
        }
        return (List) arrayList;
    }

    public static final <K, V, R, C extends Collection<? super R>> C mapTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$mapTo");
        Intrinsics.checkNotNullParameter(c, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            c.add(function1.invoke(invoke));
        }
        return c;
    }

    public static final <K, V> boolean all(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$all");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            if (!function1.invoke(invoke).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "$this$any");
        return !map.isEmpty();
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$any");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        if (map.isEmpty()) {
            return false;
        }
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            if (function1.invoke(invoke).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private static final <K, V> int count(Map<? extends K, ? extends V> map) {
        return map.size();
    }

    public static final <K, V> int count(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$count");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        int i = 0;
        if (map.isEmpty()) {
            return 0;
        }
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            if (function1.invoke(invoke).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public static final <K, V> void forEach(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$forEach");
        Intrinsics.checkNotNullParameter(function1, "action");
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            function1.invoke(invoke);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX WARNING: Multi-variable type inference failed */
    @kotlin.Deprecated(message = "Use maxByOrNull instead.", replaceWith = @kotlin.ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @kotlin.DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <K, V, R extends java.lang.Comparable<? super R>> java.util.Map.Entry<K, V> maxBy(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            java.util.Set r5 = r5.entrySet()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            boolean r0 = r5.hasNext()
            if (r0 != 0) goto L_0x0012
            r5 = 0
            goto L_0x003d
        L_0x0012:
            java.lang.Object r0 = r5.next()
            boolean r1 = r5.hasNext()
            if (r1 != 0) goto L_0x001e
        L_0x001c:
            r5 = r0
            goto L_0x003d
        L_0x001e:
            java.lang.Object r1 = r6.invoke(r0)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
        L_0x0024:
            java.lang.Object r2 = r5.next()
            java.lang.Object r3 = r6.invoke(r2)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r1.compareTo(r3)
            if (r4 >= 0) goto L_0x0036
            r0 = r2
            r1 = r3
        L_0x0036:
            boolean r2 = r5.hasNext()
            if (r2 != 0) goto L_0x0024
            goto L_0x001c
        L_0x003d:
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.maxBy(java.util.Map, kotlin.jvm.functions.Function1):java.util.Map$Entry");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <K, V, R extends java.lang.Comparable<? super R>> java.util.Map.Entry<K, V> maxByOrNull(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            java.util.Set r5 = r5.entrySet()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            boolean r0 = r5.hasNext()
            if (r0 != 0) goto L_0x0012
            r5 = 0
            goto L_0x003d
        L_0x0012:
            java.lang.Object r0 = r5.next()
            boolean r1 = r5.hasNext()
            if (r1 != 0) goto L_0x001e
        L_0x001c:
            r5 = r0
            goto L_0x003d
        L_0x001e:
            java.lang.Object r1 = r6.invoke(r0)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
        L_0x0024:
            java.lang.Object r2 = r5.next()
            java.lang.Object r3 = r6.invoke(r2)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r1.compareTo(r3)
            if (r4 >= 0) goto L_0x0036
            r0 = r2
            r1 = r3
        L_0x0036:
            boolean r2 = r5.hasNext()
            if (r2 != 0) goto L_0x0024
            goto L_0x001c
        L_0x003d:
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.maxByOrNull(java.util.Map, kotlin.jvm.functions.Function1):java.util.Map$Entry");
    }

    private static final <K, V> double maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            double doubleValue = function1.invoke(it.next()).doubleValue();
            while (it.hasNext()) {
                doubleValue = Math.max(doubleValue, function1.invoke(it.next()).doubleValue());
            }
            return doubleValue;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: maxOf  reason: collision with other method in class */
    private static final <K, V> float m1462maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            float floatValue = function1.invoke(it.next()).floatValue();
            while (it.hasNext()) {
                floatValue = Math.max(floatValue, function1.invoke(it.next()).floatValue());
            }
            return floatValue;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: maxOf  reason: collision with other method in class */
    private static final <K, V, R extends Comparable<? super R>> R m1463maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            R r = (Comparable) function1.invoke(it.next());
            while (it.hasNext()) {
                R r2 = (Comparable) function1.invoke(it.next());
                if (r.compareTo(r2) < 0) {
                    r = r2;
                }
            }
            return r;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: maxOfOrNull  reason: collision with other method in class */
    private static final <K, V> Double m1464maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        double doubleValue = function1.invoke(it.next()).doubleValue();
        while (it.hasNext()) {
            doubleValue = Math.max(doubleValue, function1.invoke(it.next()).doubleValue());
        }
        return Double.valueOf(doubleValue);
    }

    /* renamed from: maxOfOrNull  reason: collision with other method in class */
    private static final <K, V> Float m1465maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        float floatValue = function1.invoke(it.next()).floatValue();
        while (it.hasNext()) {
            floatValue = Math.max(floatValue, function1.invoke(it.next()).floatValue());
        }
        return Float.valueOf(floatValue);
    }

    private static final <K, V, R extends Comparable<? super R>> R maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        R r = (Comparable) function1.invoke(it.next());
        while (it.hasNext()) {
            R r2 = (Comparable) function1.invoke(it.next());
            if (r.compareTo(r2) < 0) {
                r = r2;
            }
        }
        return r;
    }

    private static final <K, V, R> R maxOfWith(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            R invoke = function1.invoke(it.next());
            while (it.hasNext()) {
                R invoke2 = function1.invoke(it.next());
                if (comparator.compare(invoke, invoke2) < 0) {
                    invoke = invoke2;
                }
            }
            return invoke;
        }
        throw new NoSuchElementException();
    }

    private static final <K, V, R> R maxOfWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        R invoke = function1.invoke(it.next());
        while (it.hasNext()) {
            R invoke2 = function1.invoke(it.next());
            if (comparator.compare(invoke, invoke2) < 0) {
                invoke = invoke2;
            }
        }
        return invoke;
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    private static final <K, V> Map.Entry<K, V> maxWith(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) CollectionsKt.maxWithOrNull(map.entrySet(), comparator);
    }

    private static final <K, V> Map.Entry<K, V> maxWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) CollectionsKt.maxWithOrNull(map.entrySet(), comparator);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX WARNING: Multi-variable type inference failed */
    @kotlin.Deprecated(message = "Use minByOrNull instead.", replaceWith = @kotlin.ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @kotlin.DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <K, V, R extends java.lang.Comparable<? super R>> java.util.Map.Entry<K, V> minBy(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            java.lang.String r0 = "$this$minBy"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "selector"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.util.Set r5 = r5.entrySet()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            boolean r0 = r5.hasNext()
            if (r0 != 0) goto L_0x001c
            r5 = 0
            goto L_0x0047
        L_0x001c:
            java.lang.Object r0 = r5.next()
            boolean r1 = r5.hasNext()
            if (r1 != 0) goto L_0x0028
        L_0x0026:
            r5 = r0
            goto L_0x0047
        L_0x0028:
            java.lang.Object r1 = r6.invoke(r0)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
        L_0x002e:
            java.lang.Object r2 = r5.next()
            java.lang.Object r3 = r6.invoke(r2)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r1.compareTo(r3)
            if (r4 <= 0) goto L_0x0040
            r0 = r2
            r1 = r3
        L_0x0040:
            boolean r2 = r5.hasNext()
            if (r2 != 0) goto L_0x002e
            goto L_0x0026
        L_0x0047:
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.minBy(java.util.Map, kotlin.jvm.functions.Function1):java.util.Map$Entry");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.util.Map$Entry<K, V>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <K, V, R extends java.lang.Comparable<? super R>> java.util.Map.Entry<K, V> minByOrNull(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            java.util.Set r5 = r5.entrySet()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            boolean r0 = r5.hasNext()
            if (r0 != 0) goto L_0x0012
            r5 = 0
            goto L_0x003d
        L_0x0012:
            java.lang.Object r0 = r5.next()
            boolean r1 = r5.hasNext()
            if (r1 != 0) goto L_0x001e
        L_0x001c:
            r5 = r0
            goto L_0x003d
        L_0x001e:
            java.lang.Object r1 = r6.invoke(r0)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
        L_0x0024:
            java.lang.Object r2 = r5.next()
            java.lang.Object r3 = r6.invoke(r2)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r1.compareTo(r3)
            if (r4 <= 0) goto L_0x0036
            r0 = r2
            r1 = r3
        L_0x0036:
            boolean r2 = r5.hasNext()
            if (r2 != 0) goto L_0x0024
            goto L_0x001c
        L_0x003d:
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.minByOrNull(java.util.Map, kotlin.jvm.functions.Function1):java.util.Map$Entry");
    }

    private static final <K, V> double minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            double doubleValue = function1.invoke(it.next()).doubleValue();
            while (it.hasNext()) {
                doubleValue = Math.min(doubleValue, function1.invoke(it.next()).doubleValue());
            }
            return doubleValue;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: minOf  reason: collision with other method in class */
    private static final <K, V> float m1466minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            float floatValue = function1.invoke(it.next()).floatValue();
            while (it.hasNext()) {
                floatValue = Math.min(floatValue, function1.invoke(it.next()).floatValue());
            }
            return floatValue;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: minOf  reason: collision with other method in class */
    private static final <K, V, R extends Comparable<? super R>> R m1467minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            R r = (Comparable) function1.invoke(it.next());
            while (it.hasNext()) {
                R r2 = (Comparable) function1.invoke(it.next());
                if (r.compareTo(r2) > 0) {
                    r = r2;
                }
            }
            return r;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: minOfOrNull  reason: collision with other method in class */
    private static final <K, V> Double m1468minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        double doubleValue = function1.invoke(it.next()).doubleValue();
        while (it.hasNext()) {
            doubleValue = Math.min(doubleValue, function1.invoke(it.next()).doubleValue());
        }
        return Double.valueOf(doubleValue);
    }

    /* renamed from: minOfOrNull  reason: collision with other method in class */
    private static final <K, V> Float m1469minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        float floatValue = function1.invoke(it.next()).floatValue();
        while (it.hasNext()) {
            floatValue = Math.min(floatValue, function1.invoke(it.next()).floatValue());
        }
        return Float.valueOf(floatValue);
    }

    private static final <K, V, R extends Comparable<? super R>> R minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        R r = (Comparable) function1.invoke(it.next());
        while (it.hasNext()) {
            R r2 = (Comparable) function1.invoke(it.next());
            if (r.compareTo(r2) > 0) {
                r = r2;
            }
        }
        return r;
    }

    private static final <K, V, R> R minOfWith(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            R invoke = function1.invoke(it.next());
            while (it.hasNext()) {
                R invoke2 = function1.invoke(it.next());
                if (comparator.compare(invoke, invoke2) > 0) {
                    invoke = invoke2;
                }
            }
            return invoke;
        }
        throw new NoSuchElementException();
    }

    private static final <K, V, R> R minOfWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        R invoke = function1.invoke(it.next());
        while (it.hasNext()) {
            R invoke2 = function1.invoke(it.next());
            if (comparator.compare(invoke, invoke2) > 0) {
                invoke = invoke2;
            }
        }
        return invoke;
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    public static final <K, V> Map.Entry<K, V> minWith(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        Intrinsics.checkNotNullParameter(map, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return (Map.Entry) CollectionsKt.minWithOrNull(map.entrySet(), comparator);
    }

    private static final <K, V> Map.Entry<K, V> minWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) CollectionsKt.minWithOrNull(map.entrySet(), comparator);
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "$this$none");
        return map.isEmpty();
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$none");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        if (map.isEmpty()) {
            return true;
        }
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            if (function1.invoke(invoke).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(M m, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> function1) {
        Intrinsics.checkNotNullParameter(m, "$this$onEach");
        Intrinsics.checkNotNullParameter(function1, "action");
        for (Map.Entry invoke : m.entrySet()) {
            function1.invoke(invoke);
        }
        return m;
    }

    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEachIndexed(M m, Function2<? super Integer, ? super Map.Entry<? extends K, ? extends V>, Unit> function2) {
        Intrinsics.checkNotNullParameter(m, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(function2, "action");
        int i = 0;
        for (Object next : m.entrySet()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            function2.invoke(Integer.valueOf(i), next);
            i = i2;
        }
        return m;
    }

    private static final <K, V> Iterable<Map.Entry<K, V>> asIterable(Map<? extends K, ? extends V> map) {
        return map.entrySet();
    }

    public static final <K, V> Sequence<Map.Entry<K, V>> asSequence(Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "$this$asSequence");
        return CollectionsKt.asSequence(map.entrySet());
    }

    public static final <K, V, R, C extends Collection<? super R>> C mapNotNullTo(Map<? extends K, ? extends V> map, C c, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(map, "$this$mapNotNullTo");
        Intrinsics.checkNotNullParameter(c, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        for (Map.Entry<? extends K, ? extends V> invoke : map.entrySet()) {
            Object invoke2 = function1.invoke(invoke);
            if (invoke2 != null) {
                c.add(invoke2);
            }
        }
        return c;
    }
}
