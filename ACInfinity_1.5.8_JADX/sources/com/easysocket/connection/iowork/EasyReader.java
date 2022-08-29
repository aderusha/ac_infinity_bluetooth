package com.easysocket.connection.iowork;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.action.IOAction;
import com.easysocket.entity.OriginReadData;
import com.easysocket.exception.ReadRecoverableExeption;
import com.easysocket.exception.ReadUnrecoverableException;
import com.easysocket.interfaces.config.IMessageProtocol;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.p006io.IReader;
import com.easysocket.utils.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class EasyReader implements IReader<EasySocketOptions> {
    private ISocketActionDispatch actionDispatch;
    /* access modifiers changed from: private */
    public IConnectionManager connectionManager;
    private InputStream inputStream;
    private ByteBuffer originBuf;
    private Runnable readerTask = new Runnable() {
        public void run() {
            while (!EasyReader.this.stopThread) {
                try {
                    EasyReader.this.read();
                } catch (ReadUnrecoverableException e) {
                    e.printStackTrace();
                    boolean unused = EasyReader.this.stopThread = true;
                    EasyReader.this.release();
                    return;
                } catch (ReadRecoverableExeption e2) {
                    e2.printStackTrace();
                    EasyReader.this.connectionManager.disconnect(true);
                    return;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    EasyReader.this.connectionManager.disconnect(true);
                    return;
                }
            }
        }
    };
    private Thread readerThread;
    private ByteBuffer remainingBuf;
    private EasySocketOptions socketOptions;
    /* access modifiers changed from: private */
    public boolean stopThread;

    public EasyReader(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.actionDispatch = iSocketActionDispatch;
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
    }

    public void read() throws IOException, ReadRecoverableExeption, ReadUnrecoverableException {
        OriginReadData originReadData = new OriginReadData();
        IMessageProtocol messageProtocol = this.socketOptions.getMessageProtocol();
        if (messageProtocol == null) {
            readOriginDataFromSteam(originReadData);
            return;
        }
        int headerLength = messageProtocol.getHeaderLength();
        ByteBuffer allocate = ByteBuffer.allocate(headerLength);
        allocate.order(this.socketOptions.getReadOrder());
        ByteBuffer byteBuffer = this.remainingBuf;
        if (byteBuffer != null) {
            byteBuffer.flip();
            int min = Math.min(this.remainingBuf.remaining(), headerLength);
            allocate.put(this.remainingBuf.array(), 0, min);
            if (min < headerLength) {
                this.remainingBuf = null;
                readHeaderFromSteam(allocate, headerLength - min);
            } else {
                this.remainingBuf.position(headerLength);
            }
        } else {
            readHeaderFromSteam(allocate, allocate.capacity());
        }
        originReadData.setHeaderData(allocate.array());
        int bodyLength = messageProtocol.getBodyLength(originReadData.getHeaderData(), this.socketOptions.getReadOrder());
        if (bodyLength > 0) {
            if (bodyLength <= this.socketOptions.getMaxResponseDataMb() * 1024 * 1024) {
                ByteBuffer allocate2 = ByteBuffer.allocate(bodyLength);
                allocate2.order(this.socketOptions.getReadOrder());
                ByteBuffer byteBuffer2 = this.remainingBuf;
                if (byteBuffer2 != null) {
                    int position = byteBuffer2.position();
                    int min2 = Math.min(this.remainingBuf.remaining(), bodyLength);
                    allocate2.put(this.remainingBuf.array(), position, min2);
                    this.remainingBuf.position(position + min2);
                    if (min2 == bodyLength) {
                        if (this.remainingBuf.remaining() > 0) {
                            ByteBuffer allocate3 = ByteBuffer.allocate(this.remainingBuf.remaining());
                            allocate3.order(this.socketOptions.getReadOrder());
                            allocate3.put(this.remainingBuf.array(), this.remainingBuf.position(), this.remainingBuf.remaining());
                            this.remainingBuf = allocate3;
                        } else {
                            this.remainingBuf = null;
                        }
                        originReadData.setBodyData(allocate2.array());
                        LogUtil.m46d("Socket收到数据-->" + originReadData.getBodyString());
                        this.actionDispatch.dispatchAction(IOAction.ACTION_READ_COMPLETE, originReadData);
                        return;
                    }
                    this.remainingBuf = null;
                }
                readBodyFromStream(allocate2);
                originReadData.setBodyData(allocate2.array());
            } else {
                throw new ReadUnrecoverableException("服务器返回的单次数据超过了规定的最大值，可能你的Socket消息协议不对，一般消息格式为：Header+Body，其中Header保存消息长度和类型等，Body保存消息内容，请规范好你的协议");
            }
        } else if (bodyLength == 0) {
            originReadData.setBodyData(new byte[0]);
            ByteBuffer byteBuffer3 = this.remainingBuf;
            if (byteBuffer3 != null) {
                if (byteBuffer3.hasRemaining()) {
                    ByteBuffer allocate4 = ByteBuffer.allocate(this.remainingBuf.remaining());
                    allocate4.order(this.socketOptions.getReadOrder());
                    allocate4.put(this.remainingBuf.array(), this.remainingBuf.position(), this.remainingBuf.remaining());
                    this.remainingBuf = allocate4;
                } else {
                    this.remainingBuf = null;
                }
            }
        } else if (bodyLength < 0) {
            throw new ReadUnrecoverableException("数据body的长度不能小于0");
        }
        LogUtil.m46d("Socket收到数据-->" + originReadData.getBodyString());
        this.actionDispatch.dispatchAction(IOAction.ACTION_READ_COMPLETE, originReadData);
    }

    private void readHeaderFromSteam(ByteBuffer byteBuffer, int i) throws ReadRecoverableExeption, IOException {
        int i2 = 0;
        while (i2 < i) {
            byte[] bArr = new byte[1];
            if (this.inputStream.read(bArr) != -1) {
                byteBuffer.put(bArr);
                i2++;
            } else {
                throw new ReadRecoverableExeption("读数据失败，可能是因为socket跟服务器断开了连接");
            }
        }
    }

    private void readOriginDataFromSteam(OriginReadData originReadData) throws ReadRecoverableExeption, IOException {
        int read = this.inputStream.read(this.originBuf.array());
        if (read != -1) {
            byte[] bArr = new byte[read];
            this.originBuf.get(bArr, 0, read);
            originReadData.setBodyData(bArr);
            LogUtil.m46d("Socket收到数据-->" + originReadData.getBodyString());
            this.actionDispatch.dispatchAction(IOAction.ACTION_READ_COMPLETE, originReadData);
            this.originBuf.clear();
            return;
        }
        throw new ReadRecoverableExeption("读数据失败，可能因为socket跟服务器断开了连接");
    }

    private void readBodyFromStream(ByteBuffer byteBuffer) throws ReadRecoverableExeption, IOException {
        while (byteBuffer.hasRemaining()) {
            byte[] bArr = new byte[this.socketOptions.getMaxReadBytes()];
            int read = this.inputStream.read(bArr);
            if (read != -1) {
                int remaining = byteBuffer.remaining();
                if (read > remaining) {
                    byteBuffer.put(bArr, 0, remaining);
                    int i = read - remaining;
                    ByteBuffer allocate = ByteBuffer.allocate(i);
                    this.remainingBuf = allocate;
                    allocate.order(this.socketOptions.getReadOrder());
                    this.remainingBuf.put(bArr, remaining, i);
                } else {
                    byteBuffer.put(bArr, 0, read);
                }
            } else {
                throw new ReadRecoverableExeption("读数据失败，可能是因为socket跟服务器断开了连接");
            }
        }
    }

    public void openReader() {
        init();
        Thread thread = this.readerThread;
        if (thread == null || !thread.isAlive()) {
            Thread thread2 = new Thread(this.readerTask, "reader thread");
            this.readerThread = thread2;
            this.stopThread = false;
            thread2.start();
        }
    }

    public void closeReader() {
        try {
            shutDownThread();
            release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void release() {
        if (this.originBuf != null) {
            this.originBuf = null;
        }
        if (this.remainingBuf != null) {
            this.remainingBuf = null;
        }
        Thread thread = this.readerThread;
        if (thread != null && !thread.isAlive()) {
            this.readerThread = null;
        }
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.inputStream = null;
            throw th;
        }
        this.inputStream = null;
    }

    private void init() {
        this.inputStream = this.connectionManager.getInputStream();
        if (this.socketOptions.getMessageProtocol() == null) {
            this.originBuf = ByteBuffer.allocate(4096);
        }
    }

    public void setOption(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
    }

    private void shutDownThread() throws InterruptedException {
        Thread thread = this.readerThread;
        if (thread != null && thread.isAlive() && !thread.isInterrupted()) {
            this.stopThread = true;
            thread.interrupt();
            thread.join();
        }
    }
}
