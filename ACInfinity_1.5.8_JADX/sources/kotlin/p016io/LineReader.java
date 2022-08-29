package kotlin.p016io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, mo27512d2 = {"Lkotlin/io/LineReader;", "", "()V", "BUFFER_SIZE", "", "byteBuf", "Ljava/nio/ByteBuffer;", "bytes", "", "charBuf", "Ljava/nio/CharBuffer;", "chars", "", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "compactBytes", "decode", "endOfInput", "decodeEndOfInput", "nBytes", "nChars", "readLine", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "resetAll", "", "trimStringBuilder", "updateCharset", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 5, 1})
/* renamed from: kotlin.io.LineReader */
/* compiled from: Console.kt */
public final class LineReader {
    private static final int BUFFER_SIZE = 32;
    public static final LineReader INSTANCE = new LineReader();
    private static final ByteBuffer byteBuf;
    private static final byte[] bytes;
    private static final CharBuffer charBuf;
    private static final char[] chars;
    /* access modifiers changed from: private */
    public static CharsetDecoder decoder;
    private static boolean directEOL;

    /* renamed from: sb */
    private static final StringBuilder f1191sb = new StringBuilder();

    static {
        byte[] bArr = new byte[32];
        bytes = bArr;
        char[] cArr = new char[32];
        chars = cArr;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(wrap, "ByteBuffer.wrap(bytes)");
        byteBuf = wrap;
        CharBuffer wrap2 = CharBuffer.wrap(cArr);
        Intrinsics.checkNotNullExpressionValue(wrap2, "CharBuffer.wrap(chars)");
        charBuf = wrap2;
    }

    private LineReader() {
    }

    public static final /* synthetic */ CharsetDecoder access$getDecoder$p(LineReader lineReader) {
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        return charsetDecoder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        if (f1191sb.length() != 0) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r10 == false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r0 != 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r2 != 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r10 = decodeEndOfInput(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if ((!kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0.charset(), (java.lang.Object) r11)) != false) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String readLine(java.io.InputStream r10, java.nio.charset.Charset r11) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "inputStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)     // Catch:{ all -> 0x00ce }
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)     // Catch:{ all -> 0x00ce }
            java.nio.charset.CharsetDecoder r0 = decoder     // Catch:{ all -> 0x00ce }
            r1 = 1
            if (r0 == 0) goto L_0x0022
            if (r0 != 0) goto L_0x0017
            java.lang.String r2 = "decoder"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)     // Catch:{ all -> 0x00ce }
        L_0x0017:
            java.nio.charset.Charset r0 = r0.charset()     // Catch:{ all -> 0x00ce }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r11)     // Catch:{ all -> 0x00ce }
            r0 = r0 ^ r1
            if (r0 == 0) goto L_0x0025
        L_0x0022:
            r9.updateCharset(r11)     // Catch:{ all -> 0x00ce }
        L_0x0025:
            r11 = 0
            r0 = 0
            r2 = 0
        L_0x0028:
            int r3 = r10.read()     // Catch:{ all -> 0x00ce }
            r4 = 32
            r5 = 10
            r6 = -1
            if (r3 != r6) goto L_0x004e
            java.lang.StringBuilder r10 = f1191sb     // Catch:{ all -> 0x00ce }
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x00ce }
            int r10 = r10.length()     // Catch:{ all -> 0x00ce }
            if (r10 != 0) goto L_0x003f
            r10 = 1
            goto L_0x0040
        L_0x003f:
            r10 = 0
        L_0x0040:
            if (r10 == 0) goto L_0x0049
            if (r0 != 0) goto L_0x0049
            if (r2 != 0) goto L_0x0049
            r10 = 0
            monitor-exit(r9)
            return r10
        L_0x0049:
            int r10 = r9.decodeEndOfInput(r0, r2)     // Catch:{ all -> 0x00ce }
            goto L_0x007c
        L_0x004e:
            byte[] r6 = bytes     // Catch:{ all -> 0x00ce }
            int r7 = r0 + 1
            byte r8 = (byte) r3     // Catch:{ all -> 0x00ce }
            r6[r0] = r8     // Catch:{ all -> 0x00ce }
            if (r3 == r5) goto L_0x0060
            if (r7 == r4) goto L_0x0060
            boolean r0 = directEOL     // Catch:{ all -> 0x00ce }
            if (r0 != 0) goto L_0x005e
            goto L_0x0060
        L_0x005e:
            r0 = r7
            goto L_0x0028
        L_0x0060:
            java.nio.ByteBuffer r0 = byteBuf     // Catch:{ all -> 0x00ce }
            r0.limit(r7)     // Catch:{ all -> 0x00ce }
            java.nio.CharBuffer r3 = charBuf     // Catch:{ all -> 0x00ce }
            r3.position(r2)     // Catch:{ all -> 0x00ce }
            int r2 = r9.decode(r11)     // Catch:{ all -> 0x00ce }
            if (r2 <= 0) goto L_0x00c8
            char[] r3 = chars     // Catch:{ all -> 0x00ce }
            int r6 = r2 + -1
            char r3 = r3[r6]     // Catch:{ all -> 0x00ce }
            if (r3 != r5) goto L_0x00c8
            r0.position(r11)     // Catch:{ all -> 0x00ce }
            r10 = r2
        L_0x007c:
            if (r10 <= 0) goto L_0x0094
            char[] r0 = chars     // Catch:{ all -> 0x00ce }
            int r2 = r10 + -1
            char r2 = r0[r2]     // Catch:{ all -> 0x00ce }
            if (r2 != r5) goto L_0x0094
            int r10 = r10 + -1
            if (r10 <= 0) goto L_0x0094
            int r2 = r10 + -1
            char r0 = r0[r2]     // Catch:{ all -> 0x00ce }
            r2 = 13
            if (r0 != r2) goto L_0x0094
            int r10 = r10 + -1
        L_0x0094:
            java.lang.StringBuilder r0 = f1191sb     // Catch:{ all -> 0x00ce }
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x00ce }
            int r2 = r2.length()     // Catch:{ all -> 0x00ce }
            if (r2 != 0) goto L_0x00a0
            goto L_0x00a1
        L_0x00a0:
            r1 = 0
        L_0x00a1:
            if (r1 == 0) goto L_0x00ac
            char[] r0 = chars     // Catch:{ all -> 0x00ce }
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x00ce }
            r1.<init>(r0, r11, r10)     // Catch:{ all -> 0x00ce }
            monitor-exit(r9)
            return r1
        L_0x00ac:
            char[] r1 = chars     // Catch:{ all -> 0x00ce }
            r0.append(r1, r11, r10)     // Catch:{ all -> 0x00ce }
            java.lang.String r10 = r0.toString()     // Catch:{ all -> 0x00ce }
            java.lang.String r1 = "sb.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r1)     // Catch:{ all -> 0x00ce }
            int r1 = r0.length()     // Catch:{ all -> 0x00ce }
            if (r1 <= r4) goto L_0x00c3
            r9.trimStringBuilder()     // Catch:{ all -> 0x00ce }
        L_0x00c3:
            r0.setLength(r11)     // Catch:{ all -> 0x00ce }
            monitor-exit(r9)
            return r10
        L_0x00c8:
            int r0 = r9.compactBytes()     // Catch:{ all -> 0x00ce }
            goto L_0x0028
        L_0x00ce:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.LineReader.readLine(java.io.InputStream, java.nio.charset.Charset):java.lang.String");
    }

    private final int decode(boolean z) {
        while (true) {
            CharsetDecoder charsetDecoder = decoder;
            if (charsetDecoder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
            }
            ByteBuffer byteBuffer = byteBuf;
            CharBuffer charBuffer = charBuf;
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, z);
            Intrinsics.checkNotNullExpressionValue(decode, "decoder.decode(byteBuf, charBuf, endOfInput)");
            if (decode.isError()) {
                resetAll();
                decode.throwException();
            }
            int position = charBuffer.position();
            if (!decode.isOverflow()) {
                return position;
            }
            StringBuilder sb = f1191sb;
            char[] cArr = chars;
            int i = position - 1;
            sb.append(cArr, 0, i);
            charBuffer.position(0);
            charBuffer.limit(32);
            charBuffer.put(cArr[i]);
        }
    }

    private final int compactBytes() {
        ByteBuffer byteBuffer = byteBuf;
        byteBuffer.compact();
        int position = byteBuffer.position();
        byteBuffer.position(0);
        return position;
    }

    private final int decodeEndOfInput(int i, int i2) {
        ByteBuffer byteBuffer = byteBuf;
        byteBuffer.limit(i);
        charBuf.position(i2);
        int decode = decode(true);
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder.reset();
        byteBuffer.position(0);
        return decode;
    }

    private final void updateCharset(Charset charset) {
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        decoder = newDecoder;
        ByteBuffer byteBuffer = byteBuf;
        byteBuffer.clear();
        CharBuffer charBuffer = charBuf;
        charBuffer.clear();
        byteBuffer.put((byte) 10);
        byteBuffer.flip();
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        boolean z = false;
        charsetDecoder.decode(byteBuffer, charBuffer, false);
        if (charBuffer.position() == 1 && charBuffer.get(0) == 10) {
            z = true;
        }
        directEOL = z;
        resetAll();
    }

    private final void resetAll() {
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder.reset();
        byteBuf.position(0);
        f1191sb.setLength(0);
    }

    private final void trimStringBuilder() {
        StringBuilder sb = f1191sb;
        sb.setLength(32);
        sb.trimToSize();
    }
}
