package kotlinx.coroutines.channels;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(mo27511d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aJ\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00072\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\nH\u0001¢\u0006\u0002\u0010\u000b\u001a!\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a1\u0010\u0010\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u0006\u0012\u0002\b\u00030\nH\u0001\u001a!\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0007\u001aZ\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a0\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010!\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a+\u0010$\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010#\u001aT\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ai\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u0010 \u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001aT\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a$\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0001\u001aA\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H@ø\u0001\u0000¢\u0006\u0002\u00101\u001a!\u00102\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u00103\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a`\u00104\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172(\u00106\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\n0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u00107\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH@ø\u0001\u0000¢\u0006\u0002\u00109\u001a!\u0010:\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a)\u0010;\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH@ø\u0001\u0000¢\u0006\u0002\u00109\u001a#\u0010<\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aZ\u0010=\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u00106\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010>\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u00106\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0001ø\u0001\u0000¢\u0006\u0002\u0010(\u001au\u0010?\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001729\u00106\u001a5\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001a`\u0010@\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172$\u00106\u001a \b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a?\u0010A\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a?\u0010F\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a!\u0010G\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a$\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0007\u001a!\u0010I\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u0010J\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a0\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a9\u0010M\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H@ø\u0001\u0000¢\u0006\u0002\u00101\u001a;\u0010N\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010O\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0P\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aU\u0010O\u001a\u0002HS\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q\"\u0018\b\u0002\u0010S*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0015\u0012\u0006\b\u0000\u0012\u0002HQ0T*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\n2\u0006\u0010.\u001a\u0002HSH@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a'\u0010V\u001a\b\u0012\u0004\u0012\u0002H\u000e0W\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010X\u001a\b\u0012\u0004\u0012\u0002H\u000e0Y\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010Z\u001a\b\u0012\u0004\u0012\u0002H\u000e0[\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a.\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0]0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a?\u0010^\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H50R0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\nH\u0004\u001az\u0010^\u001a\b\u0012\u0004\u0012\u0002HQ0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105\"\u0004\b\u0002\u0010Q*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001726\u00106\u001a2\u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011H5¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(a\u0012\u0004\u0012\u0002HQ0\u0019H\u0001\u0002\u0004\n\u0002\b\u0019¨\u0006b"}, mo27512d2 = {"consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "any", "", "E", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumes", "count", "", "distinct", "distinctBy", "K", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "predicate", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterNot", "filterNotNull", "filterNotNullTo", "C", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "first", "firstOrNull", "flatMap", "R", "transform", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapNotNull", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minWith", "none", "requireNoNulls", "single", "singleOrNull", "take", "takeWhile", "toChannel", "toCollection", "toMap", "", "V", "Lkotlin/Pair;", "M", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "", "toMutableSet", "", "toSet", "", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 48, mo27517xs = "kotlinx/coroutines/channels/ChannelsKt")
/* compiled from: Deprecated.kt */
final /* synthetic */ class ChannelsKt__DeprecatedKt {
    public static final Function1<Throwable, Unit> consumesAll(ReceiveChannel<?>... receiveChannelArr) {
        return new ChannelsKt__DeprecatedKt$consumesAll$1(receiveChannelArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006d A[Catch:{ all -> 0x003a }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object elementAt(kotlinx.coroutines.channels.ReceiveChannel r10, int r11, kotlin.coroutines.Continuation r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 46
            java.lang.String r4 = "ReceiveChannel doesn't contain element at index "
            r5 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r5) goto L_0x003d
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003a }
            goto L_0x0064
        L_0x003a:
            r10 = move-exception
            goto L_0x00b0
        L_0x003d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r12)
            if (r11 < 0) goto L_0x0098
            r12 = 0
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ all -> 0x0094 }
        L_0x004f:
            r0.L$0 = r10     // Catch:{ all -> 0x0094 }
            r0.L$1 = r2     // Catch:{ all -> 0x0094 }
            r0.I$0 = r11     // Catch:{ all -> 0x0094 }
            r0.I$1 = r12     // Catch:{ all -> 0x0094 }
            r0.label = r5     // Catch:{ all -> 0x0094 }
            java.lang.Object r6 = r2.hasNext(r0)     // Catch:{ all -> 0x0094 }
            if (r6 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r9 = r6
            r6 = r10
            r10 = r12
            r12 = r9
        L_0x0064:
            r7 = 0
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x003a }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x003a }
            if (r12 == 0) goto L_0x007c
            java.lang.Object r12 = r2.next()     // Catch:{ all -> 0x003a }
            int r8 = r10 + 1
            if (r11 != r10) goto L_0x0079
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            return r12
        L_0x0079:
            r10 = r6
            r12 = r8
            goto L_0x004f
        L_0x007c:
            java.lang.IndexOutOfBoundsException r10 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x003a }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r12.<init>()     // Catch:{ all -> 0x003a }
            r12.append(r4)     // Catch:{ all -> 0x003a }
            r12.append(r11)     // Catch:{ all -> 0x003a }
            r12.append(r3)     // Catch:{ all -> 0x003a }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x003a }
            r10.<init>(r11)     // Catch:{ all -> 0x003a }
            throw r10     // Catch:{ all -> 0x003a }
        L_0x0094:
            r11 = move-exception
            r6 = r10
            r10 = r11
            goto L_0x00b0
        L_0x0098:
            java.lang.IndexOutOfBoundsException r12 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r0.<init>()     // Catch:{ all -> 0x0094 }
            r0.append(r4)     // Catch:{ all -> 0x0094 }
            r0.append(r11)     // Catch:{ all -> 0x0094 }
            r0.append(r3)     // Catch:{ all -> 0x0094 }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x0094 }
            r12.<init>(r11)     // Catch:{ all -> 0x0094 }
            throw r12     // Catch:{ all -> 0x0094 }
        L_0x00b0:
            throw r10     // Catch:{ all -> 0x00b1 }
        L_0x00b1:
            r11 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAt(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0063 A[Catch:{ all -> 0x007e }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006c A[Catch:{ all -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel r8, int r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x003b }
            r7 = r10
            r10 = r8
            r8 = r5
            r5 = r7
            goto L_0x0064
        L_0x003b:
            r8 = move-exception
            goto L_0x0081
        L_0x003d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r10)
            if (r9 >= 0) goto L_0x004e
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r4)
            return r4
        L_0x004e:
            r10 = 0
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x007e }
        L_0x0053:
            r0.L$0 = r8     // Catch:{ all -> 0x007e }
            r0.L$1 = r2     // Catch:{ all -> 0x007e }
            r0.I$0 = r9     // Catch:{ all -> 0x007e }
            r0.I$1 = r10     // Catch:{ all -> 0x007e }
            r0.label = r3     // Catch:{ all -> 0x007e }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ all -> 0x007e }
            if (r5 != r1) goto L_0x0064
            return r1
        L_0x0064:
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x007e }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x007a
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x007e }
            int r6 = r10 + 1
            if (r9 != r10) goto L_0x0078
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r4)
            return r5
        L_0x0078:
            r10 = r6
            goto L_0x0053
        L_0x007a:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r4)
            return r4
        L_0x007e:
            r9 = move-exception
            r5 = r8
            r8 = r9
        L_0x0081:
            throw r8     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d A[Catch:{ all -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0065 A[SYNTHETIC, Splitter:B:28:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object first(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0032 }
            goto L_0x0054
        L_0x0032:
            r5 = move-exception
            goto L_0x0070
        L_0x0034:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.ChannelIterator r6 = r5.iterator()     // Catch:{ all -> 0x006d }
            r0.L$0 = r5     // Catch:{ all -> 0x006d }
            r0.L$1 = r6     // Catch:{ all -> 0x006d }
            r0.label = r3     // Catch:{ all -> 0x006d }
            java.lang.Object r0 = r6.hasNext(r0)     // Catch:{ all -> 0x006d }
            if (r0 != r1) goto L_0x0050
            return r1
        L_0x0050:
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L_0x0054:
            r1 = 0
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0032 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0032 }
            if (r6 == 0) goto L_0x0065
            java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x0032 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r5
        L_0x0065:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException     // Catch:{ all -> 0x0032 }
            java.lang.String r6 = "ReceiveChannel is empty."
            r5.<init>(r6)     // Catch:{ all -> 0x0032 }
            throw r5     // Catch:{ all -> 0x0032 }
        L_0x006d:
            r6 = move-exception
            r0 = r5
            r5 = r6
        L_0x0070:
            throw r5     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0061 A[SYNTHETIC, Splitter:B:27:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object firstOrNull(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0032 }
            goto L_0x0054
        L_0x0032:
            r5 = move-exception
            goto L_0x006c
        L_0x0034:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.ChannelIterator r6 = r5.iterator()     // Catch:{ all -> 0x0069 }
            r0.L$0 = r5     // Catch:{ all -> 0x0069 }
            r0.L$1 = r6     // Catch:{ all -> 0x0069 }
            r0.label = r3     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r6.hasNext(r0)     // Catch:{ all -> 0x0069 }
            if (r0 != r1) goto L_0x0050
            return r1
        L_0x0050:
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L_0x0054:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0032 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0032 }
            r1 = 0
            if (r6 != 0) goto L_0x0061
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r1
        L_0x0061:
            java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x0032 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r5
        L_0x0069:
            r6 = move-exception
            r0 = r5
            r5 = r6
        L_0x006c:
            throw r5     // Catch:{ all -> 0x006d }
        L_0x006d:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0065 A[Catch:{ all -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071 A[Catch:{ all -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008c A[Catch:{ all -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object indexOf(kotlinx.coroutines.channels.ReceiveChannel r7, java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 != r3) goto L_0x003b
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r4 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0038 }
            goto L_0x0068
        L_0x0038:
            r7 = move-exception
            goto L_0x009b
        L_0x003b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$IntRef r9 = new kotlin.jvm.internal.Ref$IntRef
            r9.<init>()
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x0097 }
            r6 = r8
            r8 = r7
            r7 = r2
            r2 = r9
            r9 = r6
        L_0x0054:
            r0.L$0 = r9     // Catch:{ all -> 0x0038 }
            r0.L$1 = r2     // Catch:{ all -> 0x0038 }
            r0.L$2 = r8     // Catch:{ all -> 0x0038 }
            r0.L$3 = r7     // Catch:{ all -> 0x0038 }
            r0.label = r3     // Catch:{ all -> 0x0038 }
            java.lang.Object r4 = r7.hasNext(r0)     // Catch:{ all -> 0x0038 }
            if (r4 != r1) goto L_0x0065
            return r1
        L_0x0065:
            r6 = r4
            r4 = r9
            r9 = r6
        L_0x0068:
            r5 = 0
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0038 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0038 }
            if (r9 == 0) goto L_0x008c
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x0038 }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r9)     // Catch:{ all -> 0x0038 }
            if (r9 == 0) goto L_0x0085
            int r7 = r2.element     // Catch:{ all -> 0x0038 }
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)     // Catch:{ all -> 0x0038 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            return r7
        L_0x0085:
            int r9 = r2.element     // Catch:{ all -> 0x0038 }
            int r9 = r9 + r3
            r2.element = r9     // Catch:{ all -> 0x0038 }
            r9 = r4
            goto L_0x0054
        L_0x008c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0038 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            r7 = -1
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        L_0x0097:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x009b:
            throw r7     // Catch:{ all -> 0x009c }
        L_0x009c:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.indexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0071 A[Catch:{ all -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0094 A[Catch:{ all -> 0x0037 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009e A[SYNTHETIC, Splitter:B:45:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object last(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r4) goto L_0x0043
            if (r2 != r3) goto L_0x003b
            java.lang.Object r7 = r0.L$2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0037 }
            goto L_0x008b
        L_0x0037:
            r7 = move-exception
            r2 = r4
            goto L_0x00a9
        L_0x003b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0043:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x004f }
            goto L_0x0069
        L_0x004f:
            r7 = move-exception
            goto L_0x00a9
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch:{ all -> 0x00a6 }
            r0.L$0 = r7     // Catch:{ all -> 0x00a6 }
            r0.L$1 = r8     // Catch:{ all -> 0x00a6 }
            r0.label = r4     // Catch:{ all -> 0x00a6 }
            java.lang.Object r2 = r8.hasNext(r0)     // Catch:{ all -> 0x00a6 }
            if (r2 != r1) goto L_0x0065
            return r1
        L_0x0065:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L_0x0069:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x004f }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x004f }
            if (r8 == 0) goto L_0x009e
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x004f }
            r6 = r2
            r2 = r7
            r7 = r6
        L_0x0078:
            r0.L$0 = r7     // Catch:{ all -> 0x00a6 }
            r0.L$1 = r2     // Catch:{ all -> 0x00a6 }
            r0.L$2 = r8     // Catch:{ all -> 0x00a6 }
            r0.label = r3     // Catch:{ all -> 0x00a6 }
            java.lang.Object r4 = r2.hasNext(r0)     // Catch:{ all -> 0x00a6 }
            if (r4 != r1) goto L_0x0087
            return r1
        L_0x0087:
            r6 = r4
            r4 = r7
            r7 = r8
            r8 = r6
        L_0x008b:
            r5 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0037 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0037 }
            if (r8 == 0) goto L_0x009a
            java.lang.Object r8 = r2.next()     // Catch:{ all -> 0x0037 }
            r7 = r4
            goto L_0x0078
        L_0x009a:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            return r7
        L_0x009e:
            java.util.NoSuchElementException r7 = new java.util.NoSuchElementException     // Catch:{ all -> 0x004f }
            java.lang.String r8 = "ReceiveChannel is empty."
            r7.<init>(r8)     // Catch:{ all -> 0x004f }
            throw r7     // Catch:{ all -> 0x004f }
        L_0x00a6:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L_0x00a9:
            throw r7     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073 A[Catch:{ all -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007f A[Catch:{ all -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0094 A[Catch:{ all -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel r8, java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r8 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            java.lang.Object r5 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x003c }
            goto L_0x0076
        L_0x003c:
            r8 = move-exception
            goto L_0x00a4
        L_0x003f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.jvm.internal.Ref$IntRef r10 = new kotlin.jvm.internal.Ref$IntRef
            r10.<init>()
            r2 = -1
            r10.element = r2
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            kotlinx.coroutines.channels.ChannelIterator r4 = r8.iterator()     // Catch:{ all -> 0x00a0 }
            r7 = r9
            r9 = r8
            r8 = r4
            r4 = r10
            r10 = r7
        L_0x0060:
            r0.L$0 = r10     // Catch:{ all -> 0x003c }
            r0.L$1 = r4     // Catch:{ all -> 0x003c }
            r0.L$2 = r2     // Catch:{ all -> 0x003c }
            r0.L$3 = r9     // Catch:{ all -> 0x003c }
            r0.L$4 = r8     // Catch:{ all -> 0x003c }
            r0.label = r3     // Catch:{ all -> 0x003c }
            java.lang.Object r5 = r8.hasNext(r0)     // Catch:{ all -> 0x003c }
            if (r5 != r1) goto L_0x0073
            return r1
        L_0x0073:
            r7 = r5
            r5 = r10
            r10 = r7
        L_0x0076:
            r6 = 0
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x003c }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x003c }
            if (r10 == 0) goto L_0x0094
            java.lang.Object r10 = r8.next()     // Catch:{ all -> 0x003c }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r10)     // Catch:{ all -> 0x003c }
            if (r10 == 0) goto L_0x008d
            int r10 = r2.element     // Catch:{ all -> 0x003c }
            r4.element = r10     // Catch:{ all -> 0x003c }
        L_0x008d:
            int r10 = r2.element     // Catch:{ all -> 0x003c }
            int r10 = r10 + r3
            r2.element = r10     // Catch:{ all -> 0x003c }
            r10 = r5
            goto L_0x0060
        L_0x0094:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003c }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r6)
            int r8 = r4.element
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            return r8
        L_0x00a0:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x00a4:
            throw r8     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r8)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0076 A[SYNTHETIC, Splitter:B:32:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0098 A[Catch:{ all -> 0x0038 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object lastOrNull(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0052
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r7 = r0.L$2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0038 }
            goto L_0x0090
        L_0x0038:
            r7 = move-exception
            r2 = r4
            goto L_0x00a5
        L_0x003c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0044:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0050 }
            goto L_0x006a
        L_0x0050:
            r7 = move-exception
            goto L_0x00a5
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch:{ all -> 0x00a2 }
            r0.L$0 = r7     // Catch:{ all -> 0x00a2 }
            r0.L$1 = r8     // Catch:{ all -> 0x00a2 }
            r0.label = r4     // Catch:{ all -> 0x00a2 }
            java.lang.Object r2 = r8.hasNext(r0)     // Catch:{ all -> 0x00a2 }
            if (r2 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L_0x006a:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0050 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r8 != 0) goto L_0x0076
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            return r5
        L_0x0076:
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x0050 }
            r6 = r2
            r2 = r7
            r7 = r6
        L_0x007d:
            r0.L$0 = r7     // Catch:{ all -> 0x00a2 }
            r0.L$1 = r2     // Catch:{ all -> 0x00a2 }
            r0.L$2 = r8     // Catch:{ all -> 0x00a2 }
            r0.label = r3     // Catch:{ all -> 0x00a2 }
            java.lang.Object r4 = r2.hasNext(r0)     // Catch:{ all -> 0x00a2 }
            if (r4 != r1) goto L_0x008c
            return r1
        L_0x008c:
            r6 = r4
            r4 = r7
            r7 = r8
            r8 = r6
        L_0x0090:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0038 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0038 }
            if (r8 == 0) goto L_0x009e
            java.lang.Object r8 = r2.next()     // Catch:{ all -> 0x0038 }
            r7 = r4
            goto L_0x007d
        L_0x009e:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            return r7
        L_0x00a2:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L_0x00a5:
            throw r7     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006d A[Catch:{ all -> 0x004b }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008f A[SYNTHETIC, Splitter:B:40:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0097 A[SYNTHETIC, Splitter:B:43:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object single(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 == r4) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r6 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0033 }
            goto L_0x0082
        L_0x0033:
            r6 = move-exception
            r2 = r0
            goto L_0x00a2
        L_0x0037:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003f:
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x004b }
            goto L_0x0065
        L_0x004b:
            r6 = move-exception
            goto L_0x00a2
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r6.iterator()     // Catch:{ all -> 0x009f }
            r0.L$0 = r6     // Catch:{ all -> 0x009f }
            r0.L$1 = r7     // Catch:{ all -> 0x009f }
            r0.label = r4     // Catch:{ all -> 0x009f }
            java.lang.Object r2 = r7.hasNext(r0)     // Catch:{ all -> 0x009f }
            if (r2 != r1) goto L_0x0061
            return r1
        L_0x0061:
            r5 = r2
            r2 = r6
            r6 = r7
            r7 = r5
        L_0x0065:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x004b }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x004b }
            if (r7 == 0) goto L_0x0097
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x004b }
            r0.L$0 = r2     // Catch:{ all -> 0x004b }
            r0.L$1 = r7     // Catch:{ all -> 0x004b }
            r0.label = r3     // Catch:{ all -> 0x004b }
            java.lang.Object r6 = r6.hasNext(r0)     // Catch:{ all -> 0x004b }
            if (r6 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r0 = r2
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x0082:
            r1 = 0
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x0033 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r7 != 0) goto L_0x008f
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r6
        L_0x008f:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0033 }
            java.lang.String r7 = "ReceiveChannel has more than one element."
            r6.<init>(r7)     // Catch:{ all -> 0x0033 }
            throw r6     // Catch:{ all -> 0x0033 }
        L_0x0097:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException     // Catch:{ all -> 0x004b }
            java.lang.String r7 = "ReceiveChannel is empty."
            r6.<init>(r7)     // Catch:{ all -> 0x004b }
            throw r6     // Catch:{ all -> 0x004b }
        L_0x009f:
            r7 = move-exception
            r2 = r6
            r6 = r7
        L_0x00a2:
            throw r6     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0072 A[SYNTHETIC, Splitter:B:32:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object singleOrNull(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x004e
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r7 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0034 }
            goto L_0x0087
        L_0x0034:
            r7 = move-exception
            r2 = r0
            goto L_0x009a
        L_0x0038:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0040:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x004c }
            goto L_0x0066
        L_0x004c:
            r7 = move-exception
            goto L_0x009a
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch:{ all -> 0x0097 }
            r0.L$0 = r7     // Catch:{ all -> 0x0097 }
            r0.L$1 = r8     // Catch:{ all -> 0x0097 }
            r0.label = r4     // Catch:{ all -> 0x0097 }
            java.lang.Object r2 = r8.hasNext(r0)     // Catch:{ all -> 0x0097 }
            if (r2 != r1) goto L_0x0062
            return r1
        L_0x0062:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L_0x0066:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x004c }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x004c }
            if (r8 != 0) goto L_0x0072
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            return r5
        L_0x0072:
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x004c }
            r0.L$0 = r2     // Catch:{ all -> 0x004c }
            r0.L$1 = r8     // Catch:{ all -> 0x004c }
            r0.label = r3     // Catch:{ all -> 0x004c }
            java.lang.Object r7 = r7.hasNext(r0)     // Catch:{ all -> 0x004c }
            if (r7 != r1) goto L_0x0083
            return r1
        L_0x0083:
            r0 = r2
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x0087:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0034 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0034 }
            if (r8 == 0) goto L_0x0093
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            return r5
        L_0x0093:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            return r7
        L_0x0097:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L_0x009a:
            throw r7     // Catch:{ all -> 0x009b }
        L_0x009b:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$drop$1(i, receiveChannel, (Continuation<? super ChannelsKt__DeprecatedKt$drop$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$dropWhile$1(receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$dropWhile$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> filter(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$filter$1(receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$filter$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$filterIndexed$1(receiveChannel, function3, (Continuation<? super ChannelsKt__DeprecatedKt$filterIndexed$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, new ChannelsKt__DeprecatedKt$filterNot$1(function2, (Continuation<? super ChannelsKt__DeprecatedKt$filterNot$1>) null));
    }

    public static final <E> ReceiveChannel<E> filterNotNull(ReceiveChannel<? extends E> receiveChannel) {
        return filter$default(receiveChannel, (CoroutineContext) null, new ChannelsKt__DeprecatedKt$filterNotNull$1((Continuation<? super ChannelsKt__DeprecatedKt$filterNotNull$1>) null), 1, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005a A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0071 A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel r6, java.util.Collection r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Collection r2 = (java.util.Collection) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0036 }
            goto L_0x005d
        L_0x0036:
            r6 = move-exception
            goto L_0x007b
        L_0x0038:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x0077 }
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L_0x004b:
            r0.L$0 = r8     // Catch:{ all -> 0x0036 }
            r0.L$1 = r7     // Catch:{ all -> 0x0036 }
            r0.L$2 = r6     // Catch:{ all -> 0x0036 }
            r0.label = r3     // Catch:{ all -> 0x0036 }
            java.lang.Object r2 = r6.hasNext(r0)     // Catch:{ all -> 0x0036 }
            if (r2 != r1) goto L_0x005a
            return r1
        L_0x005a:
            r5 = r2
            r2 = r8
            r8 = r5
        L_0x005d:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0036 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r8 == 0) goto L_0x0071
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x0036 }
            if (r8 == 0) goto L_0x006f
            r2.add(r8)     // Catch:{ all -> 0x0036 }
        L_0x006f:
            r8 = r2
            goto L_0x004b
        L_0x0071:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0036 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r4)
            return r2
        L_0x0077:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x007b:
            throw r6     // Catch:{ all -> 0x007c }
        L_0x007c:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071 A[Catch:{ all -> 0x009e }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072 A[Catch:{ all -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007f A[Catch:{ all -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0098 A[Catch:{ all -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel r7, kotlinx.coroutines.channels.SendChannel r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x005c
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x005a }
            r9 = r7
            r7 = r8
            r8 = r2
            goto L_0x0063
        L_0x003c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0044:
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x005a }
            r6 = r0
            r0 = r7
            r7 = r8
            r8 = r2
        L_0x0057:
            r2 = r1
            r1 = r6
            goto L_0x0076
        L_0x005a:
            r7 = move-exception
            goto L_0x00a2
        L_0x005c:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ChannelIterator r9 = r7.iterator()     // Catch:{ all -> 0x009e }
        L_0x0063:
            r0.L$0 = r8     // Catch:{ all -> 0x009e }
            r0.L$1 = r7     // Catch:{ all -> 0x009e }
            r0.L$2 = r9     // Catch:{ all -> 0x009e }
            r0.label = r4     // Catch:{ all -> 0x009e }
            java.lang.Object r2 = r9.hasNext(r0)     // Catch:{ all -> 0x009e }
            if (r2 != r1) goto L_0x0072
            return r1
        L_0x0072:
            r6 = r0
            r0 = r9
            r9 = r2
            goto L_0x0057
        L_0x0076:
            r5 = 0
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x009e }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x0098
            java.lang.Object r9 = r0.next()     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x0094
            r1.L$0 = r8     // Catch:{ all -> 0x009e }
            r1.L$1 = r7     // Catch:{ all -> 0x009e }
            r1.L$2 = r0     // Catch:{ all -> 0x009e }
            r1.label = r3     // Catch:{ all -> 0x009e }
            java.lang.Object r9 = r8.send(r9, r1)     // Catch:{ all -> 0x009e }
            if (r9 != r2) goto L_0x0094
            return r2
        L_0x0094:
            r9 = r0
            r0 = r1
            r1 = r2
            goto L_0x0063
        L_0x0098:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x009e }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r5)
            return r8
        L_0x009e:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x00a2:
            throw r7     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$take$1(i, receiveChannel, (Continuation<? super ChannelsKt__DeprecatedKt$take$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$takeWhile$1(receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$takeWhile$1>) null), 6, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007a A[Catch:{ all -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object toChannel(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r7, C r8, kotlin.coroutines.Continuation<? super C> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0056
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0054 }
        L_0x0038:
            r9 = r7
            r7 = r8
            r8 = r2
            goto L_0x005d
        L_0x003c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0044:
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0054 }
            goto L_0x0071
        L_0x0054:
            r7 = move-exception
            goto L_0x0097
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ChannelIterator r9 = r7.iterator()     // Catch:{ all -> 0x0093 }
        L_0x005d:
            r0.L$0 = r8     // Catch:{ all -> 0x0093 }
            r0.L$1 = r7     // Catch:{ all -> 0x0093 }
            r0.L$2 = r9     // Catch:{ all -> 0x0093 }
            r0.label = r4     // Catch:{ all -> 0x0093 }
            java.lang.Object r2 = r9.hasNext(r0)     // Catch:{ all -> 0x0093 }
            if (r2 != r1) goto L_0x006c
            return r1
        L_0x006c:
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r2
            r2 = r6
        L_0x0071:
            r5 = 0
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0054 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0054 }
            if (r9 == 0) goto L_0x008d
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x0054 }
            r0.L$0 = r2     // Catch:{ all -> 0x0054 }
            r0.L$1 = r8     // Catch:{ all -> 0x0054 }
            r0.L$2 = r7     // Catch:{ all -> 0x0054 }
            r0.label = r3     // Catch:{ all -> 0x0054 }
            java.lang.Object r9 = r2.send(r9, r0)     // Catch:{ all -> 0x0054 }
            if (r9 != r1) goto L_0x0038
            return r1
        L_0x008d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0054 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            return r2
        L_0x0093:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x0097:
            throw r7     // Catch:{ all -> 0x0098 }
        L_0x0098:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005a A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object toCollection(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r6, C r7, kotlin.coroutines.Continuation<? super C> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Collection r2 = (java.util.Collection) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0036 }
            goto L_0x005d
        L_0x0036:
            r6 = move-exception
            goto L_0x0079
        L_0x0038:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x0075 }
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L_0x004b:
            r0.L$0 = r8     // Catch:{ all -> 0x0036 }
            r0.L$1 = r7     // Catch:{ all -> 0x0036 }
            r0.L$2 = r6     // Catch:{ all -> 0x0036 }
            r0.label = r3     // Catch:{ all -> 0x0036 }
            java.lang.Object r2 = r6.hasNext(r0)     // Catch:{ all -> 0x0036 }
            if (r2 != r1) goto L_0x005a
            return r1
        L_0x005a:
            r5 = r2
            r2 = r8
            r8 = r5
        L_0x005d:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0036 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r8 == 0) goto L_0x006f
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x0036 }
            r2.add(r8)     // Catch:{ all -> 0x0036 }
            r8 = r2
            goto L_0x004b
        L_0x006f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0036 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r4)
            return r2
        L_0x0075:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x0079:
            throw r6     // Catch:{ all -> 0x007a }
        L_0x007a:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toCollection(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005a A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object toMap(kotlinx.coroutines.channels.ReceiveChannel<? extends kotlin.Pair<? extends K, ? extends V>> r6, M r7, kotlin.coroutines.Continuation<? super M> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Map r2 = (java.util.Map) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0036 }
            goto L_0x005d
        L_0x0036:
            r6 = move-exception
            goto L_0x0083
        L_0x0038:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x007f }
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L_0x004b:
            r0.L$0 = r8     // Catch:{ all -> 0x0036 }
            r0.L$1 = r7     // Catch:{ all -> 0x0036 }
            r0.L$2 = r6     // Catch:{ all -> 0x0036 }
            r0.label = r3     // Catch:{ all -> 0x0036 }
            java.lang.Object r2 = r6.hasNext(r0)     // Catch:{ all -> 0x0036 }
            if (r2 != r1) goto L_0x005a
            return r1
        L_0x005a:
            r5 = r2
            r2 = r8
            r8 = r5
        L_0x005d:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0036 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r8 == 0) goto L_0x0079
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x0036 }
            kotlin.Pair r8 = (kotlin.Pair) r8     // Catch:{ all -> 0x0036 }
            java.lang.Object r4 = r8.getFirst()     // Catch:{ all -> 0x0036 }
            java.lang.Object r8 = r8.getSecond()     // Catch:{ all -> 0x0036 }
            r2.put(r4, r8)     // Catch:{ all -> 0x0036 }
            r8 = r2
            goto L_0x004b
        L_0x0079:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0036 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r4)
            return r2
        L_0x007f:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L_0x0083:
            throw r6     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toMap(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$flatMap$1(receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$flatMap$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    public static final <E, R> ReceiveChannel<R> map(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$map$1(receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$map$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    public static final <E, R> ReceiveChannel<R> mapIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$mapIndexed$1(receiveChannel, function3, (Continuation<? super ChannelsKt__DeprecatedKt$mapIndexed$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$withIndex$1(receiveChannel, (Continuation<? super ChannelsKt__DeprecatedKt$withIndex$1>) null), 6, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    public static final <E, K> ReceiveChannel<E> distinctBy(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$distinctBy$1(receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$distinctBy$1>) null), 6, (Object) null);
    }

    public static final <E> Object toMutableSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0050, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object any(kotlinx.coroutines.channels.ReceiveChannel r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1
            r0.<init>(r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x004d }
            goto L_0x0048
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ChannelIterator r5 = r4.iterator()     // Catch:{ all -> 0x004d }
            r0.L$0 = r4     // Catch:{ all -> 0x004d }
            r0.label = r3     // Catch:{ all -> 0x004d }
            java.lang.Object r5 = r5.hasNext(r0)     // Catch:{ all -> 0x004d }
            if (r5 != r1) goto L_0x0048
            return r1
        L_0x0048:
            r0 = 0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            return r5
        L_0x004d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object count(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0036 }
            goto L_0x0061
        L_0x0036:
            r7 = move-exception
            goto L_0x0086
        L_0x0038:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$IntRef r8 = new kotlin.jvm.internal.Ref$IntRef
            r8.<init>()
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x0083 }
            r4 = r8
            r8 = r7
            r7 = r2
        L_0x004f:
            r0.L$0 = r4     // Catch:{ all -> 0x0080 }
            r0.L$1 = r8     // Catch:{ all -> 0x0080 }
            r0.L$2 = r7     // Catch:{ all -> 0x0080 }
            r0.label = r3     // Catch:{ all -> 0x0080 }
            java.lang.Object r2 = r7.hasNext(r0)     // Catch:{ all -> 0x0080 }
            if (r2 != r1) goto L_0x005e
            return r1
        L_0x005e:
            r6 = r2
            r2 = r8
            r8 = r6
        L_0x0061:
            r5 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0036 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r8 == 0) goto L_0x0074
            r7.next()     // Catch:{ all -> 0x0036 }
            int r8 = r4.element     // Catch:{ all -> 0x0036 }
            int r8 = r8 + r3
            r4.element = r8     // Catch:{ all -> 0x0036 }
            r8 = r2
            goto L_0x004f
        L_0x0074:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0036 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            int r7 = r4.element
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        L_0x0080:
            r7 = move-exception
            r2 = r8
            goto L_0x0086
        L_0x0083:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L_0x0086:
            throw r7     // Catch:{ all -> 0x0087 }
        L_0x0087:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0088 A[SYNTHETIC, Splitter:B:34:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a1 A[Catch:{ all -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad A[Catch:{ all -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object maxWith(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Comparator r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0061
            if (r2 == r4) goto L_0x004e
            if (r2 != r3) goto L_0x0046
            java.lang.Object r8 = r0.L$3
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            java.util.Comparator r4 = (java.util.Comparator) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0042 }
            r7 = r0
            r0 = r8
            r8 = r2
        L_0x003e:
            r2 = r1
            r1 = r7
            goto L_0x00a5
        L_0x0042:
            r8 = move-exception
            r9 = r2
            goto L_0x00c4
        L_0x0046:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x004e:
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$0
            java.util.Comparator r2 = (java.util.Comparator) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x005e }
            goto L_0x007c
        L_0x005e:
            r8 = move-exception
            goto L_0x00c4
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ChannelIterator r10 = r8.iterator()     // Catch:{ all -> 0x00c0 }
            r0.L$0 = r9     // Catch:{ all -> 0x00c0 }
            r0.L$1 = r8     // Catch:{ all -> 0x00c0 }
            r0.L$2 = r10     // Catch:{ all -> 0x00c0 }
            r0.label = r4     // Catch:{ all -> 0x00c0 }
            java.lang.Object r2 = r10.hasNext(r0)     // Catch:{ all -> 0x00c0 }
            if (r2 != r1) goto L_0x0077
            return r1
        L_0x0077:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r2
            r2 = r7
        L_0x007c:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x005e }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x005e }
            if (r10 != 0) goto L_0x0088
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r5)
            return r5
        L_0x0088:
            java.lang.Object r10 = r8.next()     // Catch:{ all -> 0x005e }
            r4 = r2
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0090:
            r0.L$0 = r4     // Catch:{ all -> 0x00c0 }
            r0.L$1 = r8     // Catch:{ all -> 0x00c0 }
            r0.L$2 = r9     // Catch:{ all -> 0x00c0 }
            r0.L$3 = r10     // Catch:{ all -> 0x00c0 }
            r0.label = r3     // Catch:{ all -> 0x00c0 }
            java.lang.Object r2 = r9.hasNext(r0)     // Catch:{ all -> 0x00c0 }
            if (r2 != r1) goto L_0x00a1
            return r1
        L_0x00a1:
            r7 = r0
            r0 = r10
            r10 = r2
            goto L_0x003e
        L_0x00a5:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x00c0 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x00c0 }
            if (r10 == 0) goto L_0x00bc
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x00c0 }
            int r6 = r4.compare(r0, r10)     // Catch:{ all -> 0x00c0 }
            if (r6 >= 0) goto L_0x00b8
            goto L_0x00b9
        L_0x00b8:
            r10 = r0
        L_0x00b9:
            r0 = r1
            r1 = r2
            goto L_0x0090
        L_0x00bc:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            return r0
        L_0x00c0:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x00c4:
            throw r8     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r8)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0088 A[SYNTHETIC, Splitter:B:34:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a1 A[Catch:{ all -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad A[Catch:{ all -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object minWith(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Comparator r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0061
            if (r2 == r4) goto L_0x004e
            if (r2 != r3) goto L_0x0046
            java.lang.Object r8 = r0.L$3
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            java.util.Comparator r4 = (java.util.Comparator) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0042 }
            r7 = r0
            r0 = r8
            r8 = r2
        L_0x003e:
            r2 = r1
            r1 = r7
            goto L_0x00a5
        L_0x0042:
            r8 = move-exception
            r9 = r2
            goto L_0x00c4
        L_0x0046:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x004e:
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$0
            java.util.Comparator r2 = (java.util.Comparator) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x005e }
            goto L_0x007c
        L_0x005e:
            r8 = move-exception
            goto L_0x00c4
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ChannelIterator r10 = r8.iterator()     // Catch:{ all -> 0x00c0 }
            r0.L$0 = r9     // Catch:{ all -> 0x00c0 }
            r0.L$1 = r8     // Catch:{ all -> 0x00c0 }
            r0.L$2 = r10     // Catch:{ all -> 0x00c0 }
            r0.label = r4     // Catch:{ all -> 0x00c0 }
            java.lang.Object r2 = r10.hasNext(r0)     // Catch:{ all -> 0x00c0 }
            if (r2 != r1) goto L_0x0077
            return r1
        L_0x0077:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r2
            r2 = r7
        L_0x007c:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x005e }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x005e }
            if (r10 != 0) goto L_0x0088
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r5)
            return r5
        L_0x0088:
            java.lang.Object r10 = r8.next()     // Catch:{ all -> 0x005e }
            r4 = r2
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0090:
            r0.L$0 = r4     // Catch:{ all -> 0x00c0 }
            r0.L$1 = r8     // Catch:{ all -> 0x00c0 }
            r0.L$2 = r9     // Catch:{ all -> 0x00c0 }
            r0.L$3 = r10     // Catch:{ all -> 0x00c0 }
            r0.label = r3     // Catch:{ all -> 0x00c0 }
            java.lang.Object r2 = r9.hasNext(r0)     // Catch:{ all -> 0x00c0 }
            if (r2 != r1) goto L_0x00a1
            return r1
        L_0x00a1:
            r7 = r0
            r0 = r10
            r10 = r2
            goto L_0x003e
        L_0x00a5:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x00c0 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x00c0 }
            if (r10 == 0) goto L_0x00bc
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x00c0 }
            int r6 = r4.compare(r0, r10)     // Catch:{ all -> 0x00c0 }
            if (r6 <= 0) goto L_0x00b8
            goto L_0x00b9
        L_0x00b8:
            r10 = r0
        L_0x00b9:
            r0 = r1
            r1 = r2
            goto L_0x0090
        L_0x00bc:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            return r0
        L_0x00c0:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x00c4:
            throw r8     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r8)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051 A[Catch:{ all -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0052 A[Catch:{ all -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.lang.Object none(kotlinx.coroutines.channels.ReceiveChannel r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1
            r0.<init>(r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x005b }
            goto L_0x0048
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ChannelIterator r5 = r4.iterator()     // Catch:{ all -> 0x005b }
            r0.L$0 = r4     // Catch:{ all -> 0x005b }
            r0.label = r3     // Catch:{ all -> 0x005b }
            java.lang.Object r5 = r5.hasNext(r0)     // Catch:{ all -> 0x005b }
            if (r5 != r1) goto L_0x0048
            return r1
        L_0x0048:
            r0 = 0
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x005b }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x005b }
            if (r5 != 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r3 = 0
        L_0x0053:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ all -> 0x005b }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            return r5
        L_0x005b:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x005d }
        L_0x005d:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    public static final <E, R, V> ReceiveChannel<V> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2, CoroutineContext coroutineContext, Function2<? super E, ? super R, ? extends V> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, (CoroutineStart) null, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new ChannelsKt__DeprecatedKt$zip$2(receiveChannel2, receiveChannel, function2, (Continuation<? super ChannelsKt__DeprecatedKt$zip$2>) null), 6, (Object) null);
    }

    public static final Function1<Throwable, Unit> consumes(ReceiveChannel<?> receiveChannel) {
        return new ChannelsKt__DeprecatedKt$consumes$1(receiveChannel);
    }
}
