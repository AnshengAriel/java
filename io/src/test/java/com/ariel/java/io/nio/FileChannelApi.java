package com.ariel.java.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * [FileChannel API](jetbrains://idea/navigate/reference?project=java-io&fqn=com.ariel.io.nio_channel.FileChannelApi)
 */
public class FileChannelApi {

    private final static Path PATH = Path.of("src/test/resources/nio_channel/_20230625164736.txt");
    private final static Path PATH_A = Path.of("src/test/resources/nio_channel/_20230625164736a.txt");

    @Test
    public void testWrite() throws IOException {
        FileChannel fileChannel = FileChannel.open(PATH, StandardOpenOption.WRITE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello file channel".getBytes());
        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileChannel.close();
    }

    @Test
    public void testRead() throws IOException {
        FileChannel fileChannel = FileChannel.open(PATH, StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int read = fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, read));

        fileChannel.close();
    }

    @Test
    public void testCopy() throws IOException {
        FileChannel readFileChannel = FileChannel.open(PATH, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(((int) readFileChannel.size()));

        readFileChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array()));

        FileChannel writeFileChannel = FileChannel.open(PATH_A, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        writeFileChannel.write(byteBuffer);

        readFileChannel.close();
        writeFileChannel.close();
    }

    @Test
    public void testTransferFrom() throws IOException {
        FileChannel readFileChannel = FileChannel.open(PATH, StandardOpenOption.READ);
        FileChannel writeFileChannel = FileChannel.open(PATH_A, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(((int) readFileChannel.size()));

        writeFileChannel.transferFrom(readFileChannel, 0, byteBuffer.limit());

        readFileChannel.close();
        writeFileChannel.close();
    }

    @Test
    public void testTransferTo() throws IOException {
        FileChannel readFileChannel = FileChannel.open(PATH, StandardOpenOption.READ);
        FileChannel writeFileChannel = FileChannel.open(PATH_A, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(((int) readFileChannel.size()));

        readFileChannel.transferTo(0, byteBuffer.limit(), writeFileChannel);

        readFileChannel.close();
        writeFileChannel.close();
    }
}
