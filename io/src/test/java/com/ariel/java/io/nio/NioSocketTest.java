package com.ariel.java.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * NIO（非阻塞）
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230624110600.java' style='color:green;font-weight:bold;'>NIO（非阻塞）</a>
 */
public class NioSocketTest {

    @Test
    public void testServer() throws IOException {
        try (
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);

            List<SocketChannel> socketChannels = new ArrayList<>();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                // accept()被设置为非阻塞方法，有连接则返回实例，无连接则返回null
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    socketChannel.configureBlocking(false);
                    socketChannels.add(socketChannel);
                }
                socketChannels.forEach(channel -> {
                    try {
                        // read()被设置为非阻塞方法，有内容返回正整数，无内容返回0或-1
                        int read = channel.read(byteBuffer);
                        if (read > 0) {
                            System.out.println(new String(byteBuffer.array(), 0, read));
                            byteBuffer.flip();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
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
