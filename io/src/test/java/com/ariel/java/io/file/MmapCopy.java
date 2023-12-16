package com.ariel.java.io.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * [Mmap零拷贝技术](jetbrains://idea/navigate/reference?project=java-io&fqn=com.ariel.io.zerocopy.MmapCopy)
 *
 * <p>
 *     FileChannel.MapMode参数说明
 *     <ol>
 *         <li><b>READ_ONLY</b> 请求的映射模式受到Filechannel对象的访问权限限制，如果在一个没有读权限的文件上启用 READ_ONLY，将抛出 NonReadableChannelException。</li>
 *         <li><b>PRIVATE</b> 表示写时拷贝的映射，意味着通过 put() 方法所做的任何修改都会导致产生一个私有的数据拷贝并且该拷贝中的数据只有 MappedByteBuffer 实例可以看到。
 *         该过程不会对底层文件做任何修改，而且一旦缓冲区被施以垃圾收集动作，那些修改都会丢失。</li>
 *     </ol>
 * </p>
 * <p>
 *     FileChannel#map方法源码：
 *     <ol>
 *         <li>通过Native方法获取内存映射的地址，如果失败，手动 GC 再次映射。</li>
 *         <li>通过内存映射的地址实例化出MappedByteBuffer，MappedByteBuffer本身是一个抽象类，其实这里真正实例化出来的是DirectByteBuffer。
 *         DirectByteBuffer继承于MappedByteBuffer，开辟一段直接内存，并不会占用JVM内存空间。</li>
 *         <li>通过 Filechannel 映射出的 MappedByteBuffer 其实际也是 DirectByteBuffer，也可以手动开辟一段空间：
 *         <pre>ByteBufferTest directByteBuffer = ByteBufferTest.allocateDirect(100); // 开辟100字节的直接内存空间</pre></li>
 *     </ol>
 * </p>
 * 发送完成 大小[49686839] 耗时[889] 接收完成 大小[49686839] 耗时[155]
 * 发送完成 大小[12947411] 耗时[31] 接收完成 大小[12947411] 耗时[49]
 */
public class MmapCopy {

    @Test
    public void testReceive() throws IOException, InterruptedException {
        System.setProperty("jdk.nio.enableFastFileTransfer", "true");
        File file = new File("..\\assets\\b.mp3");
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                FileChannel fileChannel = randomAccessFile.getChannel();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
                        .bind(new InetSocketAddress("127.0.0.1", 8081));
        ) {
            // 申请64M的空间，权限是可读可写
            long len = 1 << 26;
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, len);

            // 将数据直接写入缓冲区
            SocketChannel socketChannel = serverSocketChannel.accept();
            long l = System.currentTimeMillis();
            int read, count = 0;
            while ((read = socketChannel.read(mappedByteBuffer)) != -1) {
                count += read;
            }
            socketChannel.close();

            System.out.printf("接收完成 大小[%s] 耗时[%s]%n", count, System.currentTimeMillis() - l);
        }
    }

    @Test
    public void testSend() throws IOException {
        System.setProperty("jdk.nio.enableFastFileTransfer", "true");
        File file = new File("..\\assets\\a.mp3");
        long len = file.length();

        // 先建立虚拟内存
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                FileChannel fileChannel = randomAccessFile.getChannel();
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8081));
                ) {
            // FileChannel#map将在一个打开的文件和MappedByteBuffer之间建立一个虚拟内存映射，并没有真的读取文件
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, len);

            long l = System.currentTimeMillis();
            int write = socketChannel.write(mappedByteBuffer);
            System.out.printf("发送完成 大小[%s] 耗时[%s]%n", write, System.currentTimeMillis() - l);
        }

    }

}
