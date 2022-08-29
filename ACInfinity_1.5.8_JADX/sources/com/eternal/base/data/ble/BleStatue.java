package com.eternal.base.data.ble;

import com.clj.fastble.data.BleDevice;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.exception.BleException;
import com.eternal.base.protocol.BaseProtocol;
import com.eternal.base.utils.ByteUtils;
import com.eternal.framework.utils.KLog;
import java.util.concurrent.LinkedBlockingQueue;
import p014io.reactivex.Flowable;
import p014io.reactivex.processors.PublishProcessor;

public class BleStatue {
    private int cSequence = -1;
    private final BleDevice device;
    public String firmwareVersion;
    public String hardwareVersion;
    private final int portCount;
    private final LinkedBlockingQueue<Message> queue;
    private int sequence = -1;
    public String softwareVersion;
    private final PublishProcessor<DeviceModel> subject;
    private final int type;
    private final byte version;

    public BleStatue(BleDevice bleDevice) {
        this.device = bleDevice;
        this.type = BaseProtocol.parseScanRecordType(bleDevice);
        this.version = BaseProtocol.parseScanRecordVersion(bleDevice);
        this.portCount = BaseProtocol.parseScanRecordPortCount(bleDevice);
        this.queue = new LinkedBlockingQueue<>();
        this.subject = PublishProcessor.create();
    }

    /* access modifiers changed from: package-private */
    public boolean offer(Message message) {
        try {
            this.queue.put(message);
            if (this.queue.peek() == message) {
                return true;
            }
            return false;
        } catch (InterruptedException unused) {
            KLog.m65e("queue not offer");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onError(Throwable th) {
        if (this.queue.isEmpty()) {
            return false;
        }
        KLog.m65e(th);
        Message poll = this.queue.poll();
        KLog.m65e(th.toString());
        KLog.m65e(ByteUtils.bytes2HexString(poll.getPacket()));
        poll.onErr(th);
        return !this.queue.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isObservableMessage() {
        return this.queue.peek() instanceof ObservableMessage;
    }

    /* access modifiers changed from: package-private */
    public boolean onNext(byte[] bArr) {
        Message peek = this.queue.peek();
        if (peek == null) {
            KLog.m65e("null byte:" + ByteUtils.bytes2HexString(bArr));
            return false;
        }
        peek.onNext(bArr);
        if (!(peek instanceof SingleMessage) && !(peek instanceof CustomMessage) && !(peek instanceof NoneAckMessage)) {
            return false;
        }
        this.queue.poll();
        return !this.queue.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isRetry() {
        Message peek = this.queue.peek();
        if (peek != null && peek.getRetryCount() <= 3) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void addRetryCount() {
        Message peek = this.queue.peek();
        if (peek != null) {
            peek.addRetryCount();
        }
    }

    /* access modifiers changed from: package-private */
    public Message getMessage() {
        return this.queue.peek();
    }

    /* access modifiers changed from: package-private */
    public boolean onComplement() {
        Message poll = this.queue.poll();
        if (poll instanceof ObservableMessage) {
            ((ObservableMessage) poll).onComplete();
        }
        return !this.queue.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public byte[] getPacket() {
        Message peek = this.queue.peek();
        return peek != null ? peek.getPacket() : new byte[0];
    }

    /* access modifiers changed from: package-private */
    public boolean isConfigNet() {
        Message peek = this.queue.peek();
        if (peek != null) {
            return peek.isConfigNet();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getSequence() {
        byte[] packet = getPacket();
        if (packet.length > 6) {
            return ByteUtils.getInt(packet, 4);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getLastSequence() {
        int i = this.sequence;
        if (i == 65535) {
            this.sequence = 1;
        } else {
            this.sequence = i + 1;
        }
        return this.sequence;
    }

    /* access modifiers changed from: package-private */
    public int getLastCSequence() {
        int i = this.cSequence;
        if (i == 65535) {
            this.cSequence = 1;
        } else {
            this.cSequence = i + 1;
        }
        return this.cSequence;
    }

    public void clear() {
        KLog.m65e("queue size:" + this.queue.size());
        do {
        } while (onError(new BleException("disconnect")));
        this.queue.clear();
    }

    public BleDevice getDevice() {
        return this.device;
    }

    public String getMac() {
        return this.device.getMac();
    }

    public int getSize() {
        return this.queue.size();
    }

    public boolean remove(Message message) {
        this.queue.remove(message);
        KLog.m65e(" remove size: " + this.queue.size());
        return !this.queue.isEmpty();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof BleDevice) {
            return ((BleDevice) obj).getMac().equals(this.device.getMac());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onNext(DeviceModel deviceModel) {
        this.subject.onNext(deviceModel);
    }

    public int getType() {
        return this.type;
    }

    public byte getVersion() {
        return this.version;
    }

    public int getPortCount() {
        return this.portCount;
    }

    /* access modifiers changed from: package-private */
    public Flowable<DeviceModel> getBroadcast() {
        return this.subject.onBackpressureLatest();
    }
}
