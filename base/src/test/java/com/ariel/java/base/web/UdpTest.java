package com.ariel.java.base.web;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UdpTest {

    @Test
    public void server() throws IOException {
        DatagramSocket ds = new DatagramSocket(6666); // 监听指定端口
        for (;;) { // 无限循环
            // 数据缓冲区:
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet); // 收取一个UDP数据包
            // 收取到的数据存储在buffer中，由packet.getOffset(), packet.getLength()指定起始位置和长度
            // 将其按UTF-8编码转换为String:
            String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
            System.out.println(s);
            // 发送数据:
            byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            ds.send(packet);
        }
    }

    @Test
    public void client() throws IOException {
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(1000);
        ds.connect(InetAddress.getByName("localhost"), 6666); // 连接指定服务器和端口
        // 发送:
        byte[] data = "Hello".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        ds.send(packet);
        // 接收:
        byte[] buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        ds.receive(packet);
        String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println(resp);
        ds.disconnect();
        // 关闭:
        ds.close();
    }

    @Test
    public void client1() throws IOException {
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(1000);
        // 发送到localhost:6666:
        byte[] data1 = "Hello".getBytes();
        DatagramPacket packet1 = new DatagramPacket(data1, data1.length, InetAddress.getByName("localhost"), 6666);
        ds.send(packet1);
        // 发送到localhost:8888:
        byte[] data2 = "Hi".getBytes();
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, InetAddress.getByName("localhost"), 8888);
        ds.send(packet2);
        // 关闭:
        ds.close();
    }

}
