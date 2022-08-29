package com.eternal.base.data.ble;

import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;

public abstract class BaseParse<T> implements ObservableOnSubscribe<T> {

    /* renamed from: i */
    protected int f141i;
    protected byte[] last;
    protected byte[] now;
    protected int total = -1;

    /* access modifiers changed from: package-private */
    public abstract boolean checkLength();

    /* access modifiers changed from: package-private */
    public abstract void init();

    /* access modifiers changed from: package-private */
    public abstract T parse();

    public void setNow(byte[] bArr) {
        this.now = bArr;
    }

    public boolean isEnd() {
        return this.total == -1;
    }

    public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
        byte[] bArr = this.last;
        if (bArr != null) {
            byte[] bArr2 = new byte[(this.now.length + bArr.length)];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            byte[] bArr3 = this.now;
            System.arraycopy(bArr3, 0, bArr2, this.last.length, bArr3.length);
            this.now = bArr2;
            this.last = null;
        }
        this.f141i = 0;
        if (this.total == -1) {
            init();
        }
        while (checkLength()) {
            observableEmitter.onNext(parse());
        }
        if (this.total == 0) {
            this.total = -1;
        } else {
            int i = this.f141i;
            byte[] bArr4 = this.now;
            if (i < bArr4.length) {
                byte[] bArr5 = new byte[(bArr4.length - i)];
                this.last = bArr5;
                System.arraycopy(bArr4, i, bArr5, 0, bArr5.length);
            }
        }
        observableEmitter.onComplete();
    }
}
