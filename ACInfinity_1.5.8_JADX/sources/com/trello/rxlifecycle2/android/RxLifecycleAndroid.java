package com.trello.rxlifecycle2.android;

import android.view.View;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.OutsideLifecycleException;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Function;

public class RxLifecycleAndroid {
    private static final Function<ActivityEvent, ActivityEvent> ACTIVITY_LIFECYCLE = new Function<ActivityEvent, ActivityEvent>() {
        public ActivityEvent apply(ActivityEvent activityEvent) throws Exception {
            switch (C18753.$SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent[activityEvent.ordinal()]) {
                case 1:
                    return ActivityEvent.DESTROY;
                case 2:
                    return ActivityEvent.STOP;
                case 3:
                    return ActivityEvent.PAUSE;
                case 4:
                    return ActivityEvent.STOP;
                case 5:
                    return ActivityEvent.DESTROY;
                case 6:
                    throw new OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + activityEvent + " not yet implemented");
            }
        }
    };
    private static final Function<FragmentEvent, FragmentEvent> FRAGMENT_LIFECYCLE = new Function<FragmentEvent, FragmentEvent>() {
        public FragmentEvent apply(FragmentEvent fragmentEvent) throws Exception {
            switch (C18753.$SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent[fragmentEvent.ordinal()]) {
                case 1:
                    return FragmentEvent.DETACH;
                case 2:
                    return FragmentEvent.DESTROY;
                case 3:
                    return FragmentEvent.DESTROY_VIEW;
                case 4:
                    return FragmentEvent.STOP;
                case 5:
                    return FragmentEvent.PAUSE;
                case 6:
                    return FragmentEvent.STOP;
                case 7:
                    return FragmentEvent.DESTROY_VIEW;
                case 8:
                    return FragmentEvent.DESTROY;
                case 9:
                    return FragmentEvent.DETACH;
                case 10:
                    throw new OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + fragmentEvent + " not yet implemented");
            }
        }
    };

    private RxLifecycleAndroid() {
        throw new AssertionError("No instances");
    }

    public static <T> LifecycleTransformer<T> bindActivity(Observable<ActivityEvent> observable) {
        return RxLifecycle.bind(observable, ACTIVITY_LIFECYCLE);
    }

    public static <T> LifecycleTransformer<T> bindFragment(Observable<FragmentEvent> observable) {
        return RxLifecycle.bind(observable, FRAGMENT_LIFECYCLE);
    }

    public static <T> LifecycleTransformer<T> bindView(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return RxLifecycle.bind(Observable.create(new ViewDetachesOnSubscribe(view)));
    }

    /* renamed from: com.trello.rxlifecycle2.android.RxLifecycleAndroid$3 */
    static /* synthetic */ class C18753 {
        static final /* synthetic */ int[] $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent;
        static final /* synthetic */ int[] $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent;

        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(35:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|1|2|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0089 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0093 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x009d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00a7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b1 */
        static {
            /*
                com.trello.rxlifecycle2.android.FragmentEvent[] r0 = com.trello.rxlifecycle2.android.FragmentEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent = r0
                r1 = 1
                com.trello.rxlifecycle2.android.FragmentEvent r2 = com.trello.rxlifecycle2.android.FragmentEvent.ATTACH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x001d }
                com.trello.rxlifecycle2.android.FragmentEvent r3 = com.trello.rxlifecycle2.android.FragmentEvent.CREATE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.trello.rxlifecycle2.android.FragmentEvent r4 = com.trello.rxlifecycle2.android.FragmentEvent.CREATE_VIEW     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.trello.rxlifecycle2.android.FragmentEvent r5 = com.trello.rxlifecycle2.android.FragmentEvent.START     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x003e }
                com.trello.rxlifecycle2.android.FragmentEvent r6 = com.trello.rxlifecycle2.android.FragmentEvent.RESUME     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.PAUSE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r6 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.STOP     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r8 = 7
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r6 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.DESTROY_VIEW     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8 = 8
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r6 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x006c }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.DESTROY     // Catch:{ NoSuchFieldError -> 0x006c }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r8 = 9
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r6 = $SwitchMap$com$trello$rxlifecycle2$android$FragmentEvent     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.DETACH     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r8 = 10
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                com.trello.rxlifecycle2.android.ActivityEvent[] r6 = com.trello.rxlifecycle2.android.ActivityEvent.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent = r6
                com.trello.rxlifecycle2.android.ActivityEvent r7 = com.trello.rxlifecycle2.android.ActivityEvent.CREATE     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                int[] r1 = $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.trello.rxlifecycle2.android.ActivityEvent r6 = com.trello.rxlifecycle2.android.ActivityEvent.START     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r1[r6] = r0     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                int[] r0 = $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent     // Catch:{ NoSuchFieldError -> 0x009d }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.RESUME     // Catch:{ NoSuchFieldError -> 0x009d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009d }
            L_0x009d:
                int[] r0 = $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent     // Catch:{ NoSuchFieldError -> 0x00a7 }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.PAUSE     // Catch:{ NoSuchFieldError -> 0x00a7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a7 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00a7 }
            L_0x00a7:
                int[] r0 = $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent     // Catch:{ NoSuchFieldError -> 0x00b1 }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.STOP     // Catch:{ NoSuchFieldError -> 0x00b1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b1 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00b1 }
            L_0x00b1:
                int[] r0 = $SwitchMap$com$trello$rxlifecycle2$android$ActivityEvent     // Catch:{ NoSuchFieldError -> 0x00bb }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.DESTROY     // Catch:{ NoSuchFieldError -> 0x00bb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bb }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00bb }
            L_0x00bb:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.trello.rxlifecycle2.android.RxLifecycleAndroid.C18753.<clinit>():void");
        }
    }
}
