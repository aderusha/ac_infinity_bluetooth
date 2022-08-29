package p014io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: io.reactivex.internal.observers.QueueDrainSubscriberWip */
/* compiled from: QueueDrainObserver */
class QueueDrainSubscriberWip extends QueueDrainSubscriberPad0 {
    final AtomicInteger wip = new AtomicInteger();

    QueueDrainSubscriberWip() {
    }
}
