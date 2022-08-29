package p014io.reactivex;

/* renamed from: io.reactivex.FlowableOnSubscribe */
public interface FlowableOnSubscribe<T> {
    void subscribe(FlowableEmitter<T> flowableEmitter) throws Exception;
}
