package kotlin.p016io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

@Metadata(mo27511d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u001e\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a:\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010\u0015\u001a:\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010\u0018\u001a=\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00010\u001bH\bø\u0001\u0000\u001a&\u0010 \u001a\u00020!*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010\"\u001a&\u0010#\u001a\u00020$*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010%\u001a\r\u0010&\u001a\u00020\u0004*\u00020\u0002H\b\u001a\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0016\u0010)\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a0\u0010*\u001a\u00020+*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010,\u001a?\u0010-\u001a\u0002H.\"\u0004\b\u0000\u0010.*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000b\u0012\u0004\u0012\u0002H.0\u001bH\bø\u0001\u0000¢\u0006\u0002\u00100\u001a.\u00101\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u00102\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u00104\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u00105\u001a7\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007¢\u0006\u0002\u00107\u001a0\u00108\u001a\u000209*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010:\u0002\u0007\n\u0005\b20\u0001¨\u0006;"}, mo27512d2 = {"appendBytes", "", "Ljava/nio/file/Path;", "array", "", "appendLines", "lines", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/sequences/Sequence;", "appendText", "text", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "bufferedWriter", "Ljava/io/BufferedWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "forEachLine", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "useLines", "T", "block", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "writer", "Ljava/io/OutputStreamWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"}, mo27513k = 5, mo27514mv = {1, 5, 1}, mo27516xi = 1, mo27517xs = "kotlin/io/path/PathsKt")
/* renamed from: kotlin.io.path.PathsKt__PathReadWriteKt */
/* compiled from: PathReadWrite.kt */
class PathsKt__PathReadWriteKt {
    static /* synthetic */ InputStreamReader reader$default(Path path, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
    }

    private static final InputStreamReader reader(Path path, Charset charset, OpenOption... openOptionArr) throws IOException {
        return new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(Path path, Charset charset, int i, OpenOption[] openOptionArr, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset), i);
    }

    private static final BufferedReader bufferedReader(Path path, Charset charset, int i, OpenOption... openOptionArr) throws IOException {
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset), i);
    }

    static /* synthetic */ OutputStreamWriter writer$default(Path path, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
    }

    private static final OutputStreamWriter writer(Path path, Charset charset, OpenOption... openOptionArr) throws IOException {
        return new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(Path path, Charset charset, int i, OpenOption[] openOptionArr, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset), i);
    }

    private static final BufferedWriter bufferedWriter(Path path, Charset charset, int i, OpenOption... openOptionArr) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset), i);
    }

    private static final byte[] readBytes(Path path) throws IOException {
        byte[] readAllBytes = Files.readAllBytes(path);
        Intrinsics.checkNotNullExpressionValue(readAllBytes, "Files.readAllBytes(this)");
        return readAllBytes;
    }

    private static final void writeBytes(Path path, byte[] bArr, OpenOption... openOptionArr) throws IOException {
        Files.write(path, bArr, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
    }

    private static final void appendBytes(Path path, byte[] bArr) throws IOException {
        Files.write(path, bArr, new OpenOption[]{StandardOpenOption.APPEND});
    }

    public static /* synthetic */ String readText$default(Path path, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return PathsKt.readText(path, charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String readText(java.nio.file.Path r3, java.nio.charset.Charset r4) throws java.io.IOException {
        /*
            java.lang.String r0 = "$this$readText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r0]
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r1, r0)
            java.nio.file.OpenOption[] r0 = (java.nio.file.OpenOption[]) r0
            java.io.InputStream r3 = java.nio.file.Files.newInputStream(r3, r0)
            r2.<init>(r3, r4)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r4 = r2
            java.io.InputStreamReader r4 = (java.io.InputStreamReader) r4     // Catch:{ all -> 0x002e }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ all -> 0x002e }
            java.lang.String r4 = kotlin.p016io.TextStreamsKt.readText(r4)     // Catch:{ all -> 0x002e }
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            return r4
        L_0x002e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r4 = move-exception
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.readText(java.nio.file.Path, java.nio.charset.Charset):java.lang.String");
    }

    public static /* synthetic */ void writeText$default(Path path, CharSequence charSequence, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.writeText(path, charSequence, charset, openOptionArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003e, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r4, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeText(java.nio.file.Path r1, java.lang.CharSequence r2, java.nio.charset.Charset r3, java.nio.file.OpenOption... r4) throws java.io.IOException {
        /*
            java.lang.String r0 = "$this$writeText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            int r0 = r4.length
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r4, r0)
            java.nio.file.OpenOption[] r4 = (java.nio.file.OpenOption[]) r4
            java.io.OutputStream r1 = java.nio.file.Files.newOutputStream(r1, r4)
            java.lang.String r4 = "Files.newOutputStream(this, *options)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter
            r4.<init>(r1, r3)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r3 = r4
            java.io.OutputStreamWriter r3 = (java.io.OutputStreamWriter) r3     // Catch:{ all -> 0x0038 }
            r3.append(r2)     // Catch:{ all -> 0x0038 }
            kotlin.p016io.CloseableKt.closeFinally(r4, r1)
            return
        L_0x0038:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x003a }
        L_0x003a:
            r2 = move-exception
            kotlin.p016io.CloseableKt.closeFinally(r4, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.writeText(java.nio.file.Path, java.lang.CharSequence, java.nio.charset.Charset, java.nio.file.OpenOption[]):void");
    }

    public static /* synthetic */ void appendText$default(Path path, CharSequence charSequence, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.appendText(path, charSequence, charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003c, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r0, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void appendText(java.nio.file.Path r3, java.lang.CharSequence r4, java.nio.charset.Charset r5) throws java.io.IOException {
        /*
            java.lang.String r0 = "$this$appendText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 1
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r1 = java.nio.file.StandardOpenOption.APPEND
            java.nio.file.OpenOption r1 = (java.nio.file.OpenOption) r1
            r2 = 0
            r0[r2] = r1
            java.io.OutputStream r3 = java.nio.file.Files.newOutputStream(r3, r0)
            java.lang.String r0 = "Files.newOutputStream(th…tandardOpenOption.APPEND)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter
            r0.<init>(r3, r5)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r5 = r0
            java.io.OutputStreamWriter r5 = (java.io.OutputStreamWriter) r5     // Catch:{ all -> 0x0036 }
            r5.append(r4)     // Catch:{ all -> 0x0036 }
            kotlin.p016io.CloseableKt.closeFinally(r0, r3)
            return
        L_0x0036:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r4 = move-exception
            kotlin.p016io.CloseableKt.closeFinally(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.appendText(java.nio.file.Path, java.lang.CharSequence, java.nio.charset.Charset):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0065, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void forEachLine$default(java.nio.file.Path r2, java.nio.charset.Charset r3, kotlin.jvm.functions.Function1 r4, int r5, java.lang.Object r6) throws java.io.IOException {
        /*
            r6 = 1
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0006
            java.nio.charset.Charset r3 = kotlin.text.Charsets.UTF_8
        L_0x0006:
            java.io.BufferedReader r2 = java.nio.file.Files.newBufferedReader(r2, r3)
            java.lang.String r3 = "Files.newBufferedReader(this, charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.io.Reader r2 = (java.io.Reader) r2
            r3 = 8192(0x2000, float:1.14794E-41)
            boolean r5 = r2 instanceof java.io.BufferedReader
            if (r5 == 0) goto L_0x001a
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
            goto L_0x0020
        L_0x001a:
            java.io.BufferedReader r5 = new java.io.BufferedReader
            r5.<init>(r2, r3)
            r2 = r5
        L_0x0020:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r5 = 0
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ all -> 0x0055 }
            kotlin.sequences.Sequence r0 = kotlin.p016io.TextStreamsKt.lineSequence(r0)     // Catch:{ all -> 0x0055 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0055 }
        L_0x0031:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x003f
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0055 }
            r4.invoke(r1)     // Catch:{ all -> 0x0055 }
            goto L_0x0031
        L_0x003f:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r4 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r4 == 0) goto L_0x004e
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            goto L_0x0051
        L_0x004e:
            r2.close()
        L_0x0051:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return
        L_0x0055:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 != 0) goto L_0x0065
            r2.close()     // Catch:{ all -> 0x0068 }
            goto L_0x0068
        L_0x0065:
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
        L_0x0068:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.forEachLine$default(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void forEachLine(java.nio.file.Path r4, java.nio.charset.Charset r5, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r6) throws java.io.IOException {
        /*
            java.io.BufferedReader r4 = java.nio.file.Files.newBufferedReader(r4, r5)
            java.lang.String r5 = "Files.newBufferedReader(this, charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            java.io.Reader r4 = (java.io.Reader) r4
            boolean r5 = r4 instanceof java.io.BufferedReader
            if (r5 == 0) goto L_0x0012
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4
            goto L_0x001a
        L_0x0012:
            java.io.BufferedReader r5 = new java.io.BufferedReader
            r0 = 8192(0x2000, float:1.14794E-41)
            r5.<init>(r4, r0)
            r4 = r5
        L_0x001a:
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r0 = 0
            r1 = 1
            r2 = r4
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch:{ all -> 0x0050 }
            kotlin.sequences.Sequence r2 = kotlin.p016io.TextStreamsKt.lineSequence(r2)     // Catch:{ all -> 0x0050 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0050 }
        L_0x002c:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0050 }
            if (r3 == 0) goto L_0x003a
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0050 }
            r6.invoke(r3)     // Catch:{ all -> 0x0050 }
            goto L_0x002c
        L_0x003a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r6 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r6 == 0) goto L_0x0049
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
            goto L_0x004c
        L_0x0049:
            r4.close()
        L_0x004c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return
        L_0x0050:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r0 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r0 != 0) goto L_0x0060
            r4.close()     // Catch:{ all -> 0x0063 }
            goto L_0x0063
        L_0x0060:
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
        L_0x0063:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.forEachLine(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1):void");
    }

    private static final InputStream inputStream(Path path, OpenOption... openOptionArr) throws IOException {
        InputStream newInputStream = Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(newInputStream, "Files.newInputStream(this, *options)");
        return newInputStream;
    }

    private static final OutputStream outputStream(Path path, OpenOption... openOptionArr) throws IOException {
        OutputStream newOutputStream = Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(newOutputStream, "Files.newOutputStream(this, *options)");
        return newOutputStream;
    }

    static /* synthetic */ List readLines$default(Path path, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        List<String> readAllLines = Files.readAllLines(path, charset);
        Intrinsics.checkNotNullExpressionValue(readAllLines, "Files.readAllLines(this, charset)");
        return readAllLines;
    }

    private static final List<String> readLines(Path path, Charset charset) throws IOException {
        List<String> readAllLines = Files.readAllLines(path, charset);
        Intrinsics.checkNotNullExpressionValue(readAllLines, "Files.readAllLines(this, charset)");
        return readAllLines;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r2 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004f, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object useLines$default(java.nio.file.Path r2, java.nio.charset.Charset r3, kotlin.jvm.functions.Function1 r4, int r5, java.lang.Object r6) throws java.io.IOException {
        /*
            r6 = 1
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0006
            java.nio.charset.Charset r3 = kotlin.text.Charsets.UTF_8
        L_0x0006:
            java.io.BufferedReader r2 = java.nio.file.Files.newBufferedReader(r2, r3)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r5 = 0
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ all -> 0x0037 }
            kotlin.sequences.Sequence r0 = kotlin.p016io.TextStreamsKt.lineSequence(r0)     // Catch:{ all -> 0x0037 }
            java.lang.Object r4 = r4.invoke(r0)     // Catch:{ all -> 0x0037 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 == 0) goto L_0x002d
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            goto L_0x0033
        L_0x002d:
            if (r2 != 0) goto L_0x0030
            goto L_0x0033
        L_0x0030:
            r2.close()
        L_0x0033:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r4
        L_0x0037:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 != 0) goto L_0x0049
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch:{ all -> 0x004c }
            goto L_0x004c
        L_0x0049:
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
        L_0x004c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.useLines$default(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r4 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <T> T useLines(java.nio.file.Path r4, java.nio.charset.Charset r5, kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r6) throws java.io.IOException {
        /*
            java.io.BufferedReader r4 = java.nio.file.Files.newBufferedReader(r4, r5)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r0 = 0
            r1 = 1
            r2 = r4
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch:{ all -> 0x0032 }
            java.lang.String r3 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0032 }
            kotlin.sequences.Sequence r2 = kotlin.p016io.TextStreamsKt.lineSequence(r2)     // Catch:{ all -> 0x0032 }
            java.lang.Object r6 = r6.invoke(r2)     // Catch:{ all -> 0x0032 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r0 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r0 == 0) goto L_0x0028
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
            goto L_0x002e
        L_0x0028:
            if (r4 != 0) goto L_0x002b
            goto L_0x002e
        L_0x002b:
            r4.close()
        L_0x002e:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0032:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            boolean r0 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r1, r1, r0)
            if (r0 != 0) goto L_0x0044
            if (r4 == 0) goto L_0x0047
            r4.close()     // Catch:{ all -> 0x0047 }
            goto L_0x0047
        L_0x0044:
            kotlin.p016io.CloseableKt.closeFinally(r4, r5)
        L_0x0047:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.p016io.path.PathsKt__PathReadWriteKt.useLines(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    static /* synthetic */ Path writeLines$default(Path path, Iterable iterable, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path write = Files.write(path, iterable, charset, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines, charset, *options)");
        return write;
    }

    private static final Path writeLines(Path path, Iterable<? extends CharSequence> iterable, Charset charset, OpenOption... openOptionArr) throws IOException {
        Path write = Files.write(path, iterable, charset, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines, charset, *options)");
        return write;
    }

    static /* synthetic */ Path writeLines$default(Path path, Sequence sequence, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path write = Files.write(path, SequencesKt.asIterable(sequence), charset, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines.…ble(), charset, *options)");
        return write;
    }

    private static final Path writeLines(Path path, Sequence<? extends CharSequence> sequence, Charset charset, OpenOption... openOptionArr) throws IOException {
        Path write = Files.write(path, SequencesKt.asIterable(sequence), charset, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines.…ble(), charset, *options)");
        return write;
    }

    static /* synthetic */ Path appendLines$default(Path path, Iterable iterable, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path write = Files.write(path, iterable, charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines,…tandardOpenOption.APPEND)");
        return write;
    }

    private static final Path appendLines(Path path, Iterable<? extends CharSequence> iterable, Charset charset) throws IOException {
        Path write = Files.write(path, iterable, charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines,…tandardOpenOption.APPEND)");
        return write;
    }

    static /* synthetic */ Path appendLines$default(Path path, Sequence sequence, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Path write = Files.write(path, SequencesKt.asIterable(sequence), charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines.…tandardOpenOption.APPEND)");
        return write;
    }

    private static final Path appendLines(Path path, Sequence<? extends CharSequence> sequence, Charset charset) throws IOException {
        Path write = Files.write(path, SequencesKt.asIterable(sequence), charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "Files.write(this, lines.…tandardOpenOption.APPEND)");
        return write;
    }
}
