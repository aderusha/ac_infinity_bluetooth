package com.eternal.export;

import com.google.common.p009io.FileWriteMode;
import com.google.common.p009io.Files;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class CSVUtil {
    public static final String COLUMN_SEPARATOR = ",";
    public static final String ROW_SEPARATOR = "\r\n";
    private StringBuffer buffer = new StringBuffer();
    private Writer writer;

    private CSVUtil(String str) throws IOException {
        this.writer = Files.asCharSink(new File(str), StandardCharsets.UTF_8, new FileWriteMode[0]).openBufferedStream();
    }

    public static CSVUtil build(String str) throws IOException {
        return new CSVUtil(str);
    }

    public CSVUtil writeLine(String str) throws IOException {
        this.writer.append(str).append(ROW_SEPARATOR);
        return this;
    }

    public CSVUtil writeElements(String... strArr) throws IOException {
        this.buffer.setLength(0);
        for (String append : strArr) {
            StringBuffer stringBuffer = this.buffer;
            stringBuffer.append(append);
            stringBuffer.append(COLUMN_SEPARATOR);
        }
        writeLine(this.buffer.toString());
        return this;
    }

    public void sync() throws IOException {
        this.writer.flush();
        this.writer.close();
    }
}
