package com.ariel.java.io.file;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 零拷贝技术
 */
public class ZeroCopyTest {

    @Test
    public void testReceive() throws IOException {
        File file = new File("..\\assets\\b.mp3");
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                ServerSocket serverSocket = new ServerSocket(8081);
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream()
        ) {

            long l = System.currentTimeMillis();

            byte[] bytes = new byte[1 << 20];
            int length, count = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, length);
                count += length;
            }
            System.out.printf("接收完成 大小[%s] 耗时[%s]%n", count, System.currentTimeMillis() - l);
        }
    }

    @Test
    public void testSend() throws IOException {
        File file = new File("..\\assets\\a.mp3");
        try (
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                Socket socket = new Socket("127.0.0.1", 8081);
                OutputStream outputStream = socket.getOutputStream();
        ) {

            long l = System.currentTimeMillis();
            // 读取磁盘文件，发生两次拷贝，一次DMA拷贝到内核读写缓冲区，一次CPU拷贝用户应用缓冲区
            byte[] arr = new byte[(int)file.length()];
            raf.read(arr);
            // 发送文件，也发生两次拷贝，一次CPU拷贝到socket缓冲区，一次DMA拷贝到网络设备缓冲区
            outputStream.write(arr);
            System.out.printf("发送完成 大小[%s] 耗时[%s]%n", arr.length, System.currentTimeMillis() - l);
        }
    }

}
