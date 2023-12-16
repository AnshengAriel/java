package com.ariel.java.io.bio;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * BIO多线程增强型
 */
public class MultiThread {

    @Test
    public void testServer() throws IOException {
        try (
            ServerSocket serverSocket = new ServerSocket(8080);
        ) {
            while (true) {
                // accept()为阻塞方法，直到有客户端（消费者）连接上来
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try (InputStream inputStream = socket.getInputStream()) {
                        // readAllBytes()也是阻塞方法，直到此连接有新的内容传输过来
                        String msg = new String(inputStream.readAllBytes());
                        System.out.println(msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
    }

    @Test
    public void testClient() throws IOException, InterruptedException {
        try (
            Socket socket = new Socket("127.0.0.1", 8080);
        ) {
            for (int i = 0; i < 10; i++) {
                String msg = "hello " + i + "\n";
                new Thread(() -> {
                    try {
                        socket.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
            Thread.sleep(1000);
        }
    }

}
