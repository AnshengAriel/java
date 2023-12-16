package com.ariel.java.io.bio;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 单线程BIO
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230623201651.java' style='color:green;font-weight:bold;'>BIO一请求一响应</a>
 */
public class SingleThread {

    @Test
    public void testServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                System.out.println("服务端程序已启动，accept方法阻塞中，等待客户端连接。。。");
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];

                while (true) {
                    System.out.println("客户端已连接，read方法阻塞中，等待数据传输。。。");
                    int read = inputStream.read(bytes);
                    if (read != -1) {
                        System.out.println(new String(bytes, 0, read));
                    }else {
                        System.out.println("传输数据结束，连接正在断开。。。");
                        socket.close();
                        inputStream.close();
                        break;
                    }
                }
            }
        }

    }

    @Test
    public void testClient() throws IOException {
        try (
            Socket socket = new Socket("127.0.0.1", 8080);
        ) {
            socket.getOutputStream().write("hello".getBytes(StandardCharsets.UTF_8));
        }
    }

}
