package com.eternal.framework.http.interceptor.CacheInterceptor.Catch;

import com.alibaba.android.arouter.utils.Consts;
import com.bumptech.glide.load.Key;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class DiskLruCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    private static final int IO_BUFFER_SIZE = 8192;
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_TMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    /* access modifiers changed from: private */
    public static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Callable<Void> cleanupCallable = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r4 = this;
                com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r0 = com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.this
                monitor-enter(r0)
                com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r1 = com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                java.io.Writer r1 = r1.journalWriter     // Catch:{ all -> 0x0028 }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x000e:
                com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r1 = com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1.trimToSize()     // Catch:{ all -> 0x0028 }
                com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r1 = com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                boolean r1 = r1.journalRebuildRequired()     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0026
                com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r1 = com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1.rebuildJournal()     // Catch:{ all -> 0x0028 }
                com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r1 = com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r3 = 0
                int unused = r1.redundantOpCount = r3     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x0028:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.C22171.call():java.lang.Void");
        }
    };
    /* access modifiers changed from: private */
    public final File directory;
    private final ExecutorService executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File journalFile;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private final long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    private static <T> T[] copyOfRange(T[] tArr, int i, int i2) {
        int length = tArr.length;
        if (i > i2) {
            throw new IllegalArgumentException();
        } else if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            T[] tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3);
            System.arraycopy(tArr, i, tArr2, 0, min);
            return tArr2;
        }
    }

    public static String readFully(Reader reader) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            while (true) {
                int read = reader.read(cArr);
                if (read == -1) {
                    return stringWriter.toString();
                }
                stringWriter.write(cArr, 0, read);
            }
        } finally {
            reader.close();
        }
    }

    public static String readAsciiLine(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder(80);
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                throw new EOFException();
            } else if (read == 10) {
                int length = sb.length();
                if (length > 0) {
                    int i = length - 1;
                    if (sb.charAt(i) == 13) {
                        sb.setLength(i);
                    }
                }
                return sb.toString();
            } else {
                sb.append((char) read);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static void deleteContents(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    deleteContents(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    throw new IOException("failed to delete file: " + file2);
                }
            }
            return;
        }
        throw new IllegalArgumentException("not a directory: " + file);
    }

    private DiskLruCache(File file, int i, int i2, long j) {
        File file2 = file;
        this.directory = file2;
        this.appVersion = i;
        this.journalFile = new File(file2, JOURNAL_FILE);
        this.journalFileTmp = new File(file2, JOURNAL_FILE_TMP);
        this.valueCount = i2;
        this.maxSize = j;
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
            if (diskLruCache.journalFile.exists()) {
                try {
                    diskLruCache.readJournal();
                    diskLruCache.processJournal();
                    diskLruCache.journalWriter = new BufferedWriter(new FileWriter(diskLruCache.journalFile, true), 8192);
                    return diskLruCache;
                } catch (IOException unused) {
                    diskLruCache.delete();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i, i2, j);
            diskLruCache2.rebuildJournal();
            return diskLruCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private void readJournal() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.journalFile), 8192);
        try {
            String readAsciiLine = readAsciiLine(bufferedInputStream);
            String readAsciiLine2 = readAsciiLine(bufferedInputStream);
            String readAsciiLine3 = readAsciiLine(bufferedInputStream);
            String readAsciiLine4 = readAsciiLine(bufferedInputStream);
            String readAsciiLine5 = readAsciiLine(bufferedInputStream);
            if (!MAGIC.equals(readAsciiLine) || !VERSION_1.equals(readAsciiLine2) || !Integer.toString(this.appVersion).equals(readAsciiLine3) || !Integer.toString(this.valueCount).equals(readAsciiLine4) || !"".equals(readAsciiLine5)) {
                throw new IOException("unexpected journal header: [" + readAsciiLine + ", " + readAsciiLine2 + ", " + readAsciiLine4 + ", " + readAsciiLine5 + "]");
            }
            while (true) {
                try {
                    readJournalLine(readAsciiLine(bufferedInputStream));
                } catch (EOFException unused) {
                    return;
                }
            }
        } finally {
            closeQuietly(bufferedInputStream);
        }
    }

    private void readJournalLine(String str) throws IOException {
        String[] split = str.split(" ");
        if (split.length >= 2) {
            String str2 = split[1];
            if (!split[0].equals(REMOVE) || split.length != 2) {
                Entry entry = this.lruEntries.get(str2);
                if (entry == null) {
                    entry = new Entry(str2);
                    this.lruEntries.put(str2, entry);
                }
                if (split[0].equals(CLEAN) && split.length == this.valueCount + 2) {
                    boolean unused = entry.readable = true;
                    Editor unused2 = entry.currentEditor = null;
                    entry.setLengths((String[]) copyOfRange(split, 2, split.length));
                } else if (split[0].equals(DIRTY) && split.length == 2) {
                    Editor unused3 = entry.currentEditor = new Editor(entry);
                } else if (!split[0].equals(READ) || split.length != 2) {
                    throw new IOException("unexpected journal line: " + str);
                }
            } else {
                this.lruEntries.remove(str2);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            } else {
                Editor unused = next.currentEditor = null;
                while (i < this.valueCount) {
                    deleteIfExists(next.getCleanFile(i));
                    deleteIfExists(next.getDirtyFile(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        Writer writer = this.journalWriter;
        if (writer != null) {
            writer.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.journalFileTmp), 8192);
        bufferedWriter.write(MAGIC);
        bufferedWriter.write("\n");
        bufferedWriter.write(VERSION_1);
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.appVersion));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.valueCount));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (Entry next : this.lruEntries.values()) {
            if (next.currentEditor != null) {
                bufferedWriter.write("DIRTY " + next.key + 10);
            } else {
                bufferedWriter.write("CLEAN " + next.key + next.getLengths() + 10);
            }
        }
        bufferedWriter.close();
        this.journalFileTmp.renameTo(this.journalFile);
        this.journalWriter = new BufferedWriter(new FileWriter(this.journalFile, true), 8192);
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public synchronized Snapshot get(String str) throws IOException {
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return null;
        }
        if (!entry.readable) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.valueCount];
        int i = 0;
        while (i < this.valueCount) {
            try {
                inputStreamArr[i] = new FileInputStream(entry.getCleanFile(i));
                i++;
            } catch (FileNotFoundException unused) {
                return null;
            }
        }
        this.redundantOpCount++;
        this.journalWriter.append("READ " + str + 10);
        if (journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
        return new Snapshot(str, entry.sequenceNumber, inputStreamArr);
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.checkNotClosed()     // Catch:{ all -> 0x0061 }
            r5.validateKey(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x0061 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0061 }
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry r0 = (com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0061 }
            r1 = -1
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x0022
            if (r0 == 0) goto L_0x0020
            long r1 = r0.sequenceNumber     // Catch:{ all -> 0x0061 }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0022
        L_0x0020:
            monitor-exit(r5)
            return r3
        L_0x0022:
            if (r0 != 0) goto L_0x002f
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry r0 = new com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry     // Catch:{ all -> 0x0061 }
            r0.<init>(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x0061 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0061 }
            goto L_0x0037
        L_0x002f:
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r3
        L_0x0037:
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Editor r7 = new com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Editor     // Catch:{ all -> 0x0061 }
            r7.<init>(r0)     // Catch:{ all -> 0x0061 }
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.Editor unused = r0.currentEditor = r7     // Catch:{ all -> 0x0061 }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r0.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x0061 }
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0061 }
            r8.write(r6)     // Catch:{ all -> 0x0061 }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x0061 }
            r6.flush()     // Catch:{ all -> 0x0061 }
            monitor-exit(r5)
            return r7
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.edit(java.lang.String, long):com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Editor");
    }

    public File getDirectory() {
        return this.directory;
    }

    public long maxSize() {
        return this.maxSize;
    }

    public synchronized long size() {
        return this.size;
    }

    /* access modifiers changed from: private */
    public synchronized void completeEdit(Editor editor, boolean z) throws IOException {
        Entry access$1400 = editor.entry;
        if (access$1400.currentEditor == editor) {
            if (z && !access$1400.readable) {
                int i = 0;
                while (i < this.valueCount) {
                    if (access$1400.getDirtyFile(i).exists()) {
                        i++;
                    } else {
                        editor.abort();
                        throw new IllegalStateException("edit didn't create file " + i);
                    }
                }
            }
            for (int i2 = 0; i2 < this.valueCount; i2++) {
                File dirtyFile = access$1400.getDirtyFile(i2);
                if (!z) {
                    deleteIfExists(dirtyFile);
                } else if (dirtyFile.exists()) {
                    File cleanFile = access$1400.getCleanFile(i2);
                    dirtyFile.renameTo(cleanFile);
                    long j = access$1400.lengths[i2];
                    long length = cleanFile.length();
                    access$1400.lengths[i2] = length;
                    this.size = (this.size - j) + length;
                }
            }
            this.redundantOpCount++;
            Editor unused = access$1400.currentEditor = null;
            if (access$1400.readable || z) {
                boolean unused2 = access$1400.readable = true;
                this.journalWriter.write("CLEAN " + access$1400.key + access$1400.getLengths() + 10);
                if (z) {
                    long j2 = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1 + j2;
                    long unused3 = access$1400.sequenceNumber = j2;
                }
            } else {
                this.lruEntries.remove(access$1400.key);
                this.journalWriter.write("REMOVE " + access$1400.key + 10);
            }
            if (this.size > this.maxSize || journalRebuildRequired()) {
                this.executorService.submit(this.cleanupCallable);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0088, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008a, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.checkNotClosed()     // Catch:{ all -> 0x008b }
            r7.validateKey(r8)     // Catch:{ all -> 0x008b }
            java.util.LinkedHashMap<java.lang.String, com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry> r0 = r7.lruEntries     // Catch:{ all -> 0x008b }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x008b }
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry r0 = (com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.Entry) r0     // Catch:{ all -> 0x008b }
            r1 = 0
            if (r0 == 0) goto L_0x0089
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Editor r2 = r0.currentEditor     // Catch:{ all -> 0x008b }
            if (r2 == 0) goto L_0x0019
            goto L_0x0089
        L_0x0019:
            int r2 = r7.valueCount     // Catch:{ all -> 0x008b }
            if (r1 >= r2) goto L_0x0054
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x008b }
            boolean r3 = r2.delete()     // Catch:{ all -> 0x008b }
            if (r3 == 0) goto L_0x003d
            long r2 = r7.size     // Catch:{ all -> 0x008b }
            long[] r4 = r0.lengths     // Catch:{ all -> 0x008b }
            r5 = r4[r1]     // Catch:{ all -> 0x008b }
            long r2 = r2 - r5
            r7.size = r2     // Catch:{ all -> 0x008b }
            long[] r2 = r0.lengths     // Catch:{ all -> 0x008b }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x008b }
            int r1 = r1 + 1
            goto L_0x0019
        L_0x003d:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x008b }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r0.<init>()     // Catch:{ all -> 0x008b }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x008b }
            r0.append(r2)     // Catch:{ all -> 0x008b }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008b }
            r8.<init>(r0)     // Catch:{ all -> 0x008b }
            throw r8     // Catch:{ all -> 0x008b }
        L_0x0054:
            int r0 = r7.redundantOpCount     // Catch:{ all -> 0x008b }
            r1 = 1
            int r0 = r0 + r1
            r7.redundantOpCount = r0     // Catch:{ all -> 0x008b }
            java.io.Writer r0 = r7.journalWriter     // Catch:{ all -> 0x008b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r2.<init>()     // Catch:{ all -> 0x008b }
            java.lang.String r3 = "REMOVE "
            r2.append(r3)     // Catch:{ all -> 0x008b }
            r2.append(r8)     // Catch:{ all -> 0x008b }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x008b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008b }
            r0.append(r2)     // Catch:{ all -> 0x008b }
            java.util.LinkedHashMap<java.lang.String, com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Entry> r0 = r7.lruEntries     // Catch:{ all -> 0x008b }
            r0.remove(r8)     // Catch:{ all -> 0x008b }
            boolean r8 = r7.journalRebuildRequired()     // Catch:{ all -> 0x008b }
            if (r8 == 0) goto L_0x0087
            java.util.concurrent.ExecutorService r8 = r7.executorService     // Catch:{ all -> 0x008b }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.cleanupCallable     // Catch:{ all -> 0x008b }
            r8.submit(r0)     // Catch:{ all -> 0x008b }
        L_0x0087:
            monitor-exit(r7)
            return r1
        L_0x0089:
            monitor-exit(r7)
            return r1
        L_0x008b:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache.remove(java.lang.String):boolean");
    }

    public boolean isClosed() {
        return this.journalWriter == null;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        checkNotClosed();
        trimToSize();
        this.journalWriter.flush();
    }

    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            Iterator it = new ArrayList(this.lruEntries.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove((String) this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    public void delete() throws IOException {
        close();
        deleteContents(this.directory);
    }

    private void validateKey(String str) {
        if (str.contains(" ") || str.contains("\n") || str.contains("\r")) {
            throw new IllegalArgumentException("keys must not contain spaces or newlines: \"" + str + "\"");
        }
    }

    /* access modifiers changed from: private */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        return readFully(new InputStreamReader(inputStream, UTF_8));
    }

    public final class Snapshot implements Closeable {
        private final InputStream[] ins;
        private final String key;
        private final long sequenceNumber;

        private Snapshot(String str, long j, InputStream[] inputStreamArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.ins = inputStreamArr;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public InputStream getInputStream(int i) {
            return this.ins[i];
        }

        public String getString(int i) throws IOException {
            return DiskLruCache.inputStreamToString(getInputStream(i));
        }

        public void close() {
            for (InputStream closeQuietly : this.ins) {
                DiskLruCache.closeQuietly(closeQuietly);
            }
        }
    }

    public final class Editor {
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public boolean hasErrors;

        private Editor(Entry entry2) {
            this.entry = entry2;
        }

        public InputStream newInputStream(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                } else if (!this.entry.readable) {
                    return null;
                } else {
                    FileInputStream fileInputStream = new FileInputStream(this.entry.getCleanFile(i));
                    return fileInputStream;
                }
            }
        }

        public String getString(int i) throws IOException {
            InputStream newInputStream = newInputStream(i);
            if (newInputStream != null) {
                return DiskLruCache.inputStreamToString(newInputStream);
            }
            return null;
        }

        public OutputStream newOutputStream(int i) throws IOException {
            FaultHidingOutputStream faultHidingOutputStream;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor == this) {
                    faultHidingOutputStream = new FaultHidingOutputStream(new FileOutputStream(this.entry.getDirtyFile(i)));
                } else {
                    throw new IllegalStateException();
                }
            }
            return faultHidingOutputStream;
        }

        public void set(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(newOutputStream(i), DiskLruCache.UTF_8);
                try {
                    outputStreamWriter2.write(str);
                    DiskLruCache.closeQuietly(outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    DiskLruCache.closeQuietly(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                DiskLruCache.closeQuietly(outputStreamWriter);
                throw th;
            }
        }

        public void commit() throws IOException {
            if (this.hasErrors) {
                DiskLruCache.this.completeEdit(this, false);
                DiskLruCache.this.remove(this.entry.key);
                return;
            }
            DiskLruCache.this.completeEdit(this, true);
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        private class FaultHidingOutputStream extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.hasErrors = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.hasErrors = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.hasErrors = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    boolean unused2 = Editor.this.hasErrors = true;
                }
            }
        }
    }

    private final class Entry {
        /* access modifiers changed from: private */
        public Editor currentEditor;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;

        private Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
        }

        public String getLengths() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.lengths) {
                sb.append(' ');
                sb.append(append);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.valueCount) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.lengths[i] = Long.parseLong(strArr[i]);
                        i++;
                    } catch (NumberFormatException unused) {
                        throw invalidLengths(strArr);
                    }
                }
                return;
            }
            throw invalidLengths(strArr);
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File getCleanFile(int i) {
            File access$2200 = DiskLruCache.this.directory;
            return new File(access$2200, this.key + Consts.DOT + i);
        }

        public File getDirtyFile(int i) {
            File access$2200 = DiskLruCache.this.directory;
            return new File(access$2200, this.key + Consts.DOT + i + ".tmp");
        }
    }
}
