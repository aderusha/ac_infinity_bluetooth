package p014io.reactivex.internal.subscribers;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: io.reactivex.internal.subscribers.QueueDrainSubscriberPad3 */
/* compiled from: QueueDrainSubscriber */
class QueueDrainSubscriberPad3 extends QueueDrainSubscriberPad2 {
    final AtomicLong requested = new AtomicLong();

    QueueDrainSubscriberPad3() {
    }
}
