package com.eternal.account.bleota;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27511d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, mo27512d2 = {"<anonymous>", ""}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: BleOTAClient.kt */
final class BleOTAClient$postBinData$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ BleOTAClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BleOTAClient$postBinData$1(BleOTAClient bleOTAClient) {
        super(0);
        this.this$0 = bleOTAClient;
    }

    public final void invoke() {
        this.this$0.initPackets();
        this.this$0.postNextPacket();
    }
}
