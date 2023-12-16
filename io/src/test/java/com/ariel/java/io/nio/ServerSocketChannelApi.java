package com.ariel.java.io.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class ServerSocketChannelApi {

    public static ServerSocketChannel newInstance() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8088));
        channel.configureBlocking(false);
        return channel;
    }

}
