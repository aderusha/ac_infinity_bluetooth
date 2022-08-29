package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f321c;

    /* renamed from: d */
    private final int f322d;

    /* renamed from: k0 */
    private final long f323k0;

    /* renamed from: k1 */
    private final long f324k1;

    public int bits() {
        return 64;
    }

    SipHashFunction(int i, int i2, long j, long j2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        Preconditions.checkArgument(i2 <= 0 ? false : z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f321c = i;
        this.f322d = i2;
        this.f323k0 = j;
        this.f324k1 = j2;
    }

    public Hasher newHasher() {
        return new SipHasher(this.f321c, this.f322d, this.f323k0, this.f324k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f321c + "" + this.f322d + "(" + this.f323k0 + ", " + this.f324k1 + ")";
    }

    public boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        if (this.f321c == sipHashFunction.f321c && this.f322d == sipHashFunction.f322d && this.f323k0 == sipHashFunction.f323k0 && this.f324k1 == sipHashFunction.f324k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f321c) ^ this.f322d)) ^ this.f323k0) ^ this.f324k1);
    }

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f325b = 0;

        /* renamed from: c */
        private final int f326c;

        /* renamed from: d */
        private final int f327d;
        private long finalM = 0;

        /* renamed from: v0 */
        private long f328v0 = 8317987319222330741L;

        /* renamed from: v1 */
        private long f329v1 = 7237128888997146477L;

        /* renamed from: v2 */
        private long f330v2 = 7816392313619706465L;

        /* renamed from: v3 */
        private long f331v3 = 8387220255154660723L;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f326c = i;
            this.f327d = i2;
            this.f328v0 = 8317987319222330741L ^ j;
            this.f329v1 = 7237128888997146477L ^ j2;
            this.f330v2 = 7816392313619706465L ^ j;
            this.f331v3 = 8387220255154660723L ^ j2;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer byteBuffer) {
            this.f325b += 8;
            processM(byteBuffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer byteBuffer) {
            this.f325b += (long) byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (((long) byteBuffer.get()) & 255) << i;
                i += 8;
            }
        }

        public HashCode makeHash() {
            long j = this.finalM ^ (this.f325b << 56);
            this.finalM = j;
            processM(j);
            this.f330v2 ^= 255;
            sipRound(this.f327d);
            return HashCode.fromLong(((this.f328v0 ^ this.f329v1) ^ this.f330v2) ^ this.f331v3);
        }

        private void processM(long j) {
            this.f331v3 ^= j;
            sipRound(this.f326c);
            this.f328v0 = j ^ this.f328v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.f328v0;
                long j2 = this.f329v1;
                this.f328v0 = j + j2;
                this.f330v2 += this.f331v3;
                this.f329v1 = Long.rotateLeft(j2, 13);
                long rotateLeft = Long.rotateLeft(this.f331v3, 16);
                this.f331v3 = rotateLeft;
                long j3 = this.f329v1;
                long j4 = this.f328v0;
                this.f329v1 = j3 ^ j4;
                this.f331v3 = rotateLeft ^ this.f330v2;
                long rotateLeft2 = Long.rotateLeft(j4, 32);
                this.f328v0 = rotateLeft2;
                long j5 = this.f330v2;
                long j6 = this.f329v1;
                this.f330v2 = j5 + j6;
                this.f328v0 = rotateLeft2 + this.f331v3;
                this.f329v1 = Long.rotateLeft(j6, 17);
                long rotateLeft3 = Long.rotateLeft(this.f331v3, 21);
                this.f331v3 = rotateLeft3;
                long j7 = this.f329v1;
                long j8 = this.f330v2;
                this.f329v1 = j7 ^ j8;
                this.f331v3 = rotateLeft3 ^ this.f328v0;
                this.f330v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
