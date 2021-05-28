package cn.az.code.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferRead {

    private static void byteBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/zhengwei/lanqiao/ConditionOperator.java",
                "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            // System.out.println("读取字节数："+bytesRead);
            // 之前是写buffer，现在要读buffer
            buffer.flip();// 切换模式，写->读
            System.out.print(Charset.forName("utf-8").decode(buffer)); // 这样读取，如果10字节恰好分割了一个字符将出现乱码
            buffer.clear();// 清空,position位置为0，limit=capacity
            // 继续往buffer中写
            bytesRead = channel.read(buffer);
        }
        randomAccessFile.close();
    }

    private static void readLine() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/zhengwei/lanqiao/ConditionOperator.java",
                "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        int bytesRead = channel.read(buffer);
        ByteBuffer stringBuffer = ByteBuffer.allocate(20);
        while (bytesRead != -1) {
            System.out.println("读取字节数：" + bytesRead);
            // 之前是写buffer，现在要读buffer
            buffer.flip();// 切换模式，写->读
            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                if (b == 10 || b == 13) { // 换行或回车
                    stringBuffer.flip();
                    // 这里就是一个行
                    final String line = Charset.forName("utf-8").decode(stringBuffer).toString();
                    System.out.println(line + "----------");// 解码已经读到的一行所对应的字节
                    stringBuffer.clear();
                } else {
                    if (stringBuffer.hasRemaining())
                        stringBuffer.put(b);
                    else { // 空间不够扩容
                        stringBuffer = reAllocate(stringBuffer);
                        stringBuffer.put(b);
                    }
                }
            }
            buffer.clear();// 清空,position位置为0，limit=capacity
            // 继续往buffer中写
            bytesRead = channel.read(buffer);
        }
        randomAccessFile.close();
    }

    private static ByteBuffer reAllocate(ByteBuffer stringBuffer) {
        final int capacity = stringBuffer.capacity();
        byte[] newBuffer = new byte[capacity * 2];
        System.arraycopy(stringBuffer.array(), 0, newBuffer, 0, capacity);
        return (ByteBuffer) ByteBuffer.wrap(newBuffer).position(capacity);
    }
}
