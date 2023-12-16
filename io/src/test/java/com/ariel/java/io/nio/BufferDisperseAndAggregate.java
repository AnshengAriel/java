package com.ariel.java.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer的分散和聚合
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230704155841.java' style='color:green;font-weight:bold;'>Buffer的分散和聚合</a>
 */
public class BufferDisperseAndAggregate {

    @Test
    public void test() throws IOException {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(10);
        ByteBuffer[] byteBuffers = {buffer1, buffer2};

        RandomAccessFile randomAccessFile = new RandomAccessFile("src/test/resources/nio_buffer/a.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        // 依次写入到多个buffer
        long read = fileChannel.read(byteBuffers);

        System.out.println("read = " + read);
        System.out.println("buffer1.position() = " + buffer1.position());
        System.out.println("buffer1.limit() = " + buffer1.limit());
        System.out.println("buffer2.position() = " + buffer2.position());
        System.out.println("buffer2.limit() = " + buffer2.limit());

        for (ByteBuffer byteBuffer : byteBuffers) {
            byteBuffer.flip();
        }

        // 从多个buffer中依次读取
        long write = fileChannel.write(byteBuffers);

        System.out.println("write = " + write);
        System.out.println("buffer1.position() = " + buffer1.position());
        System.out.println("buffer1.limit() = " + buffer1.limit());
        System.out.println("buffer2.position() = " + buffer2.position());
        System.out.println("buffer2.limit() = " + buffer2.limit());

        fileChannel.close();
        randomAccessFile.close();
    }
}
