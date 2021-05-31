package cn.az.code.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.stream.Stream;

import com.nimbusds.jose.util.StandardCharset;

/**
 * Read
 * 
 * @author az
 */
public class NioRead {

    private static final int BUFFER_SIZE = 10 * 1024;

    public void readSend(File logFile) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(logFile, "r"); FileChannel channel = raf.getChannel();) {

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            ByteBuffer stringBuffer = ByteBuffer.allocate(20);
            while (channel.read(buffer) != -1) {
                // change mode: from write to read
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    // \r or \n
                    if (b == 10 || b == 13) {
                        stringBuffer.flip();
                        String line = StandardCharset.UTF_8.decode(stringBuffer).toString();
                        System.out.println(line);
                        stringBuffer.clear();
                    } else {
                        // check stringBuffer spacity
                        if (!stringBuffer.hasRemaining()) {
                            stringBuffer = reAllocate(stringBuffer);
                        }
                        stringBuffer.put(b);
                    }
                }
                buffer.clear();
            }
        }
    }

    public void streamRead(File f) throws IOException {
        try (Stream<String> stream = Files.lines(f.toPath())) {
            stream.forEach(s -> System.out.println(s));
        }
    }

    /**
     * scale up
     *
     * @param stringBuffer buffer
     * @return 扩容后的buffer
     */
    private static ByteBuffer reAllocate(ByteBuffer stringBuffer) {
        final int capacity = stringBuffer.capacity();
        byte[] newBuffer = new byte[capacity * 2];
        System.arraycopy(stringBuffer.array(), 0, newBuffer, 0, capacity);
        return (ByteBuffer) ByteBuffer.wrap(newBuffer).position(capacity);
    }
}
