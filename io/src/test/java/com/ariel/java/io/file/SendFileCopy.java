package com.ariel.java.io.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * [sendFile拷贝](jetbrains://idea/navigate/reference?project=java-io&fqn=com.ariel.io.zerocopy.SendFileCopy)
 *
 * 发送完成 大小[49686839] 耗时[1787] 接收完成 大小[49686839] 耗时[1901]
 * 发送完成 大小[12947411] 耗时[394] 接收完成 大小[12947411] 耗时[680]
 */
public class SendFileCopy {

    @Test
    public void testReceive() throws IOException {
        File file = new File("..\\assets\\b.mp3");

        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                FileChannel fileChannel = randomAccessFile.getChannel();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
                        .bind(new InetSocketAddress("127.0.0.1", 8081));
                SocketChannel socketChannel = serverSocketChannel.accept();
        ) {
            // 每次只写入1M文件
            long l = System.currentTimeMillis(), length = 1 << 20, position = 0;
            while ((length = fileChannel.transferFrom(socketChannel, position, length)) != 0) {
                position += length;
            }
            System.out.printf("接收完成 大小[%s] 耗时[%s]%n", position, System.currentTimeMillis() - l);
        }
    }

    @Test
    public void testSend() throws IOException {
        // 指定启动参数，否则只会发送最多为8M的内容
        System.setProperty("jdk.nio.enableFastFileTransfer", "true");
        File file = new File("..\\assets\\a.mp3");
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                FileChannel fileChannel = randomAccessFile.getChannel();
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8081));
             ) {
            long l = System.currentTimeMillis();
            // 只需要两次DMA拷贝
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            System.out.printf("发送完成 大小[%s] 耗时[%s]%n", fileChannel.size(), System.currentTimeMillis() - l);
        }
    }

}
