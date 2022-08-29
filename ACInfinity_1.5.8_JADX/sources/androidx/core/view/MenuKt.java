package androidx.core.view;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(mo27511d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\u0002\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0002\u001a3\u0010\r\u001a\u00020\u000e*\u00020\u00032!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0010H\bø\u0001\u0000\u001aH\u0010\u0013\u001a\u00020\u000e*\u00020\u000326\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0014H\bø\u0001\u0000\u001a\u0015\u0010\u0016\u001a\u00020\u0002*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H\n\u001a\r\u0010\u0017\u001a\u00020\u000b*\u00020\u0003H\b\u001a\r\u0010\u0018\u001a\u00020\u000b*\u00020\u0003H\b\u001a\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a*\u00020\u0003H\u0002\u001a\u0015\u0010\u001b\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\n\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001c"}, mo27512d2 = {"children", "Lkotlin/sequences/Sequence;", "Landroid/view/MenuItem;", "Landroid/view/Menu;", "getChildren", "(Landroid/view/Menu;)Lkotlin/sequences/Sequence;", "size", "", "getSize", "(Landroid/view/Menu;)I", "contains", "", "item", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "isEmpty", "isNotEmpty", "iterator", "", "minusAssign", "core-ktx_release"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Menu.kt */
public final class MenuKt {
    public static final MenuItem get(Menu menu, int i) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        MenuItem item = menu.getItem(i);
        Intrinsics.checkNotNullExpressionValue(item, "getItem(index)");
        return item;
    }

    public static final boolean contains(Menu menu, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int size = menu.size();
        if (size > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (Intrinsics.areEqual((Object) menu.getItem(i), (Object) menuItem)) {
                    return true;
                }
                if (i2 >= size) {
                    break;
                }
                i = i2;
            }
        }
        return false;
    }

    public static final void minusAssign(Menu menu, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        Intrinsics.checkNotNullParameter(menuItem, "item");
        menu.removeItem(menuItem.getItemId());
    }

    public static final int getSize(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        return menu.size();
    }

    public static final boolean isEmpty(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        return menu.size() == 0;
    }

    public static final boolean isNotEmpty(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        return menu.size() != 0;
    }

    public static final void forEach(Menu menu, Function1<? super MenuItem, Unit> function1) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        int size = menu.size();
        if (size > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                MenuItem item = menu.getItem(i);
                Intrinsics.checkNotNullExpressionValue(item, "getItem(index)");
                function1.invoke(item);
                if (i2 < size) {
                    i = i2;
                } else {
                    return;
                }
            }
        }
    }

    public static final void forEachIndexed(Menu menu, Function2<? super Integer, ? super MenuItem, Unit> function2) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        Intrinsics.checkNotNullParameter(function2, "action");
        int size = menu.size();
        if (size > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                Integer valueOf = Integer.valueOf(i);
                MenuItem item = menu.getItem(i);
                Intrinsics.checkNotNullExpressionValue(item, "getItem(index)");
                function2.invoke(valueOf, item);
                if (i2 < size) {
                    i = i2;
                } else {
                    return;
                }
            }
        }
    }

    public static final Iterator<MenuItem> iterator(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        return new MenuKt$iterator$1(menu);
    }

    public static final Sequence<MenuItem> getChildren(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "<this>");
        return new MenuKt$children$1(menu);
    }
}
