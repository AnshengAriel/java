package com.ariel.java.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Executors;

/**
 * [实现群聊系统](project/_20230605135544/src/test/java/com/ariel/io/_20230709221303.java)
 */
public class GroupChat {

    /**
     * 服务器端口
     */
    public static final InetSocketAddress INET_SOCKET_ADDRESS;

    static {
        try {
            INET_SOCKET_ADDRESS = new InetSocketAddress(InetAddress.getByName("localhost"), 8088);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testServer() throws IOException {
        doServer();
    }

    @Test
    public void testClientA() throws IOException {
        doClient();
    }

    @Test
    public void testClientB() throws IOException {
        doClient();
    }

    public void doServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(INET_SOCKET_ADDRESS);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int i = selector.select(2000);
            if (i > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel channel = serverSocketChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.printf("某某上线了！%n");
                    }else if (selectionKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        int read = socketChannel.read(byteBuffer);
                        InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                        int port = remoteAddress.getPort();

                        if (read == -1) {
                            System.out.printf("端口[%s]离线了%n", port);
                        }else if (read == 0) {
                            System.out.printf("端口[%s]无话可说%n", port);
                        }else {
                            System.out.printf("端口[%s]发送消息[%s]%n", port, new String(byteBuffer.array(), 0, read));

                            ByteBuffer wrapMsg = ByteBuffer.wrap(("端口[" + port + "]发送消息：").getBytes());
                            selector.keys().forEach(key -> {
                                if (!key.equals(selectionKey) && key.channel() instanceof SocketChannel) {
                                    SocketChannel channel = (SocketChannel) key.channel();
                                    try {
                                        wrapMsg.flip();
                                        byteBuffer.flip();
                                        channel.write(new ByteBuffer[]{wrapMsg, byteBuffer});
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        }

                    }
                    iterator.remove();
                }
            }
        }
    }

    public void doClient() throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open(INET_SOCKET_ADDRESS);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_WRITE + SelectionKey.OP_READ);
        InetSocketAddress localAddress = (InetSocketAddress) socketChannel.getLocalAddress();

        Executors.newSingleThreadExecutor().execute(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                byte[] bytes = scanner.next().getBytes(StandardCharsets.UTF_8);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.put(bytes);

                byteBuffer.flip();
                int write = 0;
                try {
                    write = socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (write == -1) {
                    System.out.printf("端口[%s]离线了%n", localAddress.getPort());
                }else if (write == 0) {
                    System.out.printf("端口[%s]无话可说%n", localAddress.getPort());
                }else {
                    System.out.printf("端口[%s]发送消息[%s]%n", localAddress.getPort(), new String(byteBuffer.array(), 0, write));
                }
            }
        });

        while (true) {
            int i = selector.select(2000);
            if (i > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = socketChannel.read(byteBuffer);

                        if (read == -1) {
                            System.out.printf("端口离线了%n");
                        }else if (read == 0) {
                            System.out.printf("端口无话可说%n");
                        }else {
                            System.out.printf("%s%n", new String(byteBuffer.array(), 0, read));
                        }
                    }
                    iterator.remove();
                }
            }
        }
    }
}
