package com.eternal.base.protocol;

import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;

public class StateMachine {
    /* access modifiers changed from: private */
    public ByteBuffer buffer;
    /* access modifiers changed from: private */
    public final ByteBuffer cache = ByteBuffer.allocate(1024);
    /* access modifiers changed from: private */
    public final Callback callback;
    /* access modifiers changed from: private */
    public State now;
    /* access modifiers changed from: private */
    public final State sHead;
    /* access modifiers changed from: private */
    public final State sLength = new State() {
        public boolean onNext() {
            if (StateMachine.this.buffer.remaining() <= StateMachine.this.cache.remaining()) {
                int limit = StateMachine.this.cache.limit();
                StateMachine.this.cache.limit(StateMachine.this.cache.position() + StateMachine.this.buffer.remaining());
                StateMachine.this.buffer.put(StateMachine.this.cache);
                StateMachine.this.cache.limit(limit);
                if (StateMachine.this.callback != null) {
                    StateMachine.this.callback.onPacket(StateMachine.this.buffer.array());
                }
                StateMachine stateMachine = StateMachine.this;
                State unused = stateMachine.now = stateMachine.sHead;
                return false;
            }
            StateMachine.this.buffer.put(StateMachine.this.cache);
            return true;
        }
    };
    /* access modifiers changed from: private */
    public byte type;

    public interface Callback {
        void onPacket(byte[] bArr);
    }

    public StateMachine(Callback callback2, byte b) {
        C16671 r0 = new State() {
            public boolean onNext() {
                if (StateMachine.this.cache.remaining() < 5) {
                    return true;
                }
                short s = StateMachine.this.cache.getShort();
                if (s >= -23296 && s <= -23268) {
                    short s2 = StateMachine.this.cache.getShort();
                    if (s2 < 0) {
                        return false;
                    }
                    ByteBuffer unused = StateMachine.this.buffer = ByteBuffer.allocate(s2 + 10 + 2);
                    StateMachine.this.buffer.putShort(s);
                    StateMachine.this.buffer.putShort(s2);
                    StateMachine stateMachine = StateMachine.this;
                    State unused2 = stateMachine.now = stateMachine.sLength;
                } else if (s == 7935) {
                    if (ProtocolTransformer.isDeviceC(StateMachine.this.type)) {
                        ByteBuffer unused3 = StateMachine.this.buffer = ByteBuffer.allocate(31);
                        StateMachine.this.buffer.putShort(s);
                    } else {
                        short s3 = StateMachine.this.cache.getShort();
                        byte b = StateMachine.this.cache.get();
                        byte b2 = StateMachine.this.cache.get();
                        ByteBuffer unused4 = StateMachine.this.buffer = ByteBuffer.allocate(UnsignedBytes.toInt(b2) + 6);
                        StateMachine.this.buffer.putShort(s);
                        StateMachine.this.buffer.putShort(s3);
                        StateMachine.this.buffer.put(b);
                        StateMachine.this.buffer.put(b2);
                    }
                    StateMachine stateMachine2 = StateMachine.this;
                    State unused5 = stateMachine2.now = stateMachine2.sLength;
                }
                return false;
            }
        };
        this.sHead = r0;
        this.callback = callback2;
        this.now = r0;
        this.type = b;
    }

    public void onNext(byte[] bArr) {
        this.cache.put(bArr);
        this.cache.flip();
        do {
        } while (!this.now.onNext());
        this.cache.compact();
    }
}
