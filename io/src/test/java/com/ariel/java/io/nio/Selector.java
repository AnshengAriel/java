package com.ariel.java.io.nio;

import com.ariel.io.util.OU;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * NIO选择器
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230624114250.java' style='color:green;font-weight:bold;'>NIO选择器</a>
 */
public class Selector {

    @Test
    public void testServer() throws IOException {
        try (
                java.nio.channels.Selector selector = java.nio.channels.Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            OU.obs(selector);
            OU.apd("Selector#open");

            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            OU.apd("ServerSocketChannel#register");

            while (true) {
                // 获取接收到新事件的通道列表
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    // 处理事件
                    consumeEvent(iterator.next(), selector);
                    // 一个事件只处理一次，处理完成后移除待处理列表
                    iterator.remove();
                }
            }

        }
    }

    void consumeEvent(SelectionKey selectionKey, java.nio.channels.Selector selector) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            serverSocketChannel.close();
        }else if (selectionKey.isReadable()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(7);
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            int read = socketChannel.read(byteBuffer);
            if (read > 0) {
                System.out.printf("%s%n", new String(byteBuffer.array(), 0, read));
            }else {
                socketChannel.close();
            }
        }
    }

    @Test
    public void testClient() throws IOException, InterruptedException {
        try (
            Socket socket = new Socket("127.0.0.1", 8080);
        ) {
            for (int i = 0; i < 10; i++) {
                String msg = "hello " + i;
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
