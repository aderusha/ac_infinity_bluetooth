package com.eternal.account.bleota.message;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, mo27512d2 = {"Lcom/eternal/account/bleota/message/CommandAckMessage;", "Lcom/eternal/account/bleota/message/BleOTAMessage;", "status", "", "(I)V", "getStatus", "()I", "Companion", "module-account_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: CommandAckMessage.kt */
public abstract class CommandAckMessage extends BleOTAMessage {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int STATUS_ACCEPT = 0;
    public static final int STATUS_REFUSE = 1;
    private final int status;

    public final int getStatus() {
        return this.status;
    }

    @Metadata(mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo27512d2 = {"Lcom/eternal/account/bleota/message/CommandAckMessage$Companion;", "", "()V", "STATUS_ACCEPT", "", "STATUS_REFUSE", "module-account_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: CommandAckMessage.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public CommandAckMessage(int i) {
        this.status = i;
    }
}
