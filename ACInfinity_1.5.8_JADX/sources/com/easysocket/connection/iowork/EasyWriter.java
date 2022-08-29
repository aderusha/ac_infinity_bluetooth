package com.easysocket.connection.iowork;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.p006io.IWriter;
import com.easysocket.utils.LogUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingDeque;

public class EasyWriter implements IWriter<EasySocketOptions> {
    private ISocketActionDispatch actionDispatch;
    private IConnectionManager connectionManager;
    /* access modifiers changed from: private */
    public boolean isStop;
    private OutputStream outputStream;
    /* access modifiers changed from: private */
    public LinkedBlockingDeque<byte[]> packetsToSend = new LinkedBlockingDeque<>();
    private EasySocketOptions socketOptions;
    private Runnable writerTask = new Runnable() {
        public void run() {
            while (!EasyWriter.this.isStop) {
                try {
                    EasyWriter.this.write((byte[]) EasyWriter.this.packetsToSend.take());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Thread writerThread;

    public EasyWriter(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
        this.actionDispatch = iSocketActionDispatch;
    }

    public void openWriter() {
        this.outputStream = this.connectionManager.getOutStream();
        if (this.writerThread == null) {
            this.isStop = false;
            Thread thread = new Thread(this.writerTask, "writer thread");
            this.writerThread = thread;
            thread.start();
        }
    }

    public void setOption(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
    }

    public void write(byte[] bArr) throws IOException {
        if (bArr != null) {
            int i = 0;
            LogUtil.m46d("Socket发送数据String-->" + new String(bArr, Charset.forName("utf-8")));
            LogUtil.m46d("Socket发送数据byte[]-->" + Arrays.toString(bArr));
            int maxWriteBytes = this.socketOptions.getMaxWriteBytes();
            int length = bArr.length;
            ByteBuffer allocate = ByteBuffer.allocate(maxWriteBytes);
            allocate.order(this.socketOptions.getReadOrder());
            while (length > 0) {
                int min = Math.min(maxWriteBytes, length);
                allocate.clear();
                allocate.rewind();
                allocate.put(bArr, i, min);
                allocate.flip();
                byte[] bArr2 = new byte[min];
                allocate.get(bArr2);
                this.outputStream.write(bArr2);
                this.outputStream.flush();
                i += min;
                length -= min;
            }
        }
    }

    public void offer(byte[] bArr) {
        if (!this.isStop) {
            this.packetsToSend.offer(bArr);
        }
    }

    public void closeWriter() {
        try {
            OutputStream outputStream2 = this.outputStream;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            shutDownThread();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.outputStream = null;
            throw th;
        }
        this.outputStream = null;
    }

    private void shutDownThread() throws InterruptedException {
        Thread thread = this.writerThread;
        if (thread != null && thread.isAlive() && !thread.isInterrupted()) {
            this.isStop = true;
            thread.interrupt();
            thread.join();
            this.writerThread = null;
        }
    }
}
