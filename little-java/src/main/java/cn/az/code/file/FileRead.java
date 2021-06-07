package cn.az.code.file;

import cn.hutool.core.date.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 文件读取
 *
 * @author az
 * @since 5/27/2021 22:34
 */
public class FileRead {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileRead.class);

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    private static final int LF = 10;

    private static final int CR = 13;

    public static void main(String[] args) {
        String file = "C:\\Users\\ycpang\\Desktop\\20210528\\log.log";
        StopWatch stopWatch = StopWatch.create("1");

        run(() -> bufferedRead(file), stopWatch);
        run(() -> streamRead(file), stopWatch);
        run(() -> scannerRead(file), stopWatch);
        run(() -> readLine(file), stopWatch);

        LOGGER.info(stopWatch.prettyPrint());
    }

    /**
     * BufferReader按行读取
     */
    public static void bufferedRead(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.readLine() != null) {
                // do something
            }
        } catch (IOException e) {
            LOGGER.error("错误", e);
        }
    }

    /**
     * Stream按行读取
     */
    public static void streamRead(String file) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
        } catch (IOException e) {
            LOGGER.error("错误", e);
        }
    }

    /**
     * Scanner按行读取
     */
    public static void scannerRead(String file) {
        try (Scanner scan = new Scanner(new File(file))) {
            while (scan.hasNextLine()) {
                scan.nextLine();
            }
        } catch (IOException e) {
            LOGGER.error("错误", e);
        }
    }

    /**
     * NIO按行读取
     */
    public static void readLine(String fileName) {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
             FileChannel channel = randomAccessFile.getChannel()) {
            // 每次读取的buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            // 期望每行占用的buffer
            ByteBuffer stringBuffer = ByteBuffer.allocate(20);
            // 从文件中读数据到buffer中
            while (channel.read(buffer) != -1) {

                // 切换模式，写->读
                buffer.flip();

                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    // 换行或回车
                    if (b == LF || b == CR) {
                        stringBuffer.flip();
                        // 解码已经读到的一行所对应的字节
                        UTF8.decode(stringBuffer).toString();
                        // System.out.println(line + "----------");
                        stringBuffer.clear();
                    } else {
                        // 空间不够扩容
                        if (!stringBuffer.hasRemaining()) {
                            stringBuffer = reAllocate(stringBuffer);
                        }
                        stringBuffer.put(b);
                    }
                }
                // 清空,position位置为0，limit=capacity
                buffer.clear();
            }
        } catch (Exception e) {
            LOGGER.error("错误", e);
        }
    }

    private static ByteBuffer reAllocate(ByteBuffer stringBuffer) {
        final int capacity = stringBuffer.capacity();
        byte[] newBuffer = new byte[capacity * 2];
        System.arraycopy(stringBuffer.array(), 0, newBuffer, 0, capacity);
        return (ByteBuffer) ByteBuffer.wrap(newBuffer).position(capacity);
    }

    private static void run(Runnable r, StopWatch sw) {
        sw.start();
        r.run();
        sw.stop();
    }
}
