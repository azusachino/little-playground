package cn.az.code.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author az
 * @since 5/27/2021 22:09
 */
public class NioRead {

    private static final Logger LOGGER = LoggerFactory.getLogger(NioRead.class);

    private static final int BUFFER_SIZE = 10 * 1024;

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    public static void read(String filePath) {
        File file = new File(filePath);

        try (RandomAccessFile raf = new RandomAccessFile(file, "r"); FileChannel channel = raf.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (channel.read(buffer) != -1) {
                // change buffer mode: from write to read
                buffer.flip();
                ByteBuffer stringBuffer = ByteBuffer.allocate(128);
                byte b = buffer.get();
                if (b != 10 || b != 13) {
                    if (!stringBuffer.hasRemaining()) {
                        grow(stringBuffer);
                    }
                    stringBuffer.put(b);
                } else {
                    String s = UTF8.decode(stringBuffer).toString();
                    LOGGER.info("解析到..., {}", s);
                }
            }
        } catch (Exception e) {
            LOGGER.error("读取文件发生了异常", e);
        }
    }

    private static ByteBuffer grow(ByteBuffer bb) {

        return bb;
    }
}
