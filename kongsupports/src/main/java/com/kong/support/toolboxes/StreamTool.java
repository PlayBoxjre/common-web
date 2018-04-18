package com.kong.support.toolboxes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/11 0:38 星期三
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class StreamTool {

    public static byte[] readByteFromInputStreamForProcess(InputStream inputStream, Consumer<Integer> consumer) {
        Objects.requireNonNull(inputStream, "StreamTool (readByteFromInputStream) paramster is not null  ");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024 * 8 * 8];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
                if (consumer!=null)
                    consumer.accept(baos.size());
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readByteFromInputStream(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "StreamTool (readByteFromInputStream) paramster is not null  ");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024 * 8 * 8];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
