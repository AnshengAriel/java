package com.ariel.java.io.nio;

import org.junit.Test;

/**
 * ByteBuffer多类型存取
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230628161158.java' style='color:green;font-weight:bold;'>ByteBuffer多类型存取</a>
 */
public class ByteBufferTest {

    @Test
    public void test() {
        // 如果空间不足，就会在put时抛出异常BufferOverflowException
        java.nio.ByteBuffer byteBuffer = java.nio.ByteBuffer.allocate(100);

        // short char 占用2个字节
        byteBuffer.putShort(((short) 8));
        System.out.println("byteBuffer.position() = " + byteBuffer.position());
        byteBuffer.putChar('a');
        System.out.println("byteBuffer.position() = " + byteBuffer.position());

        // int float 占用4个字节
        byteBuffer.putInt(10);
        System.out.println("byteBuffer.position() = " + byteBuffer.position());
        byteBuffer.putFloat(1.6f);
        System.out.println("byteBuffer.position() = " + byteBuffer.position());

        // long double 占用8个字节
        byteBuffer.putLong(8L);
        System.out.println("byteBuffer.position() = " + byteBuffer.position());
        byteBuffer.putDouble(6.5);
        System.out.println("byteBuffer.position() = " + byteBuffer.position());

        byteBuffer.flip();

        // 如果类型剩余空间不足会抛出异常BufferUnderflowException
        System.out.println("byteBuffer.getShort() = " + byteBuffer.getShort());
        System.out.println("byteBuffer.getChar() = " + byteBuffer.getChar());
        System.out.println("byteBuffer.getInt() = " + byteBuffer.getInt());
        System.out.println("byteBuffer.getFloat() = " + byteBuffer.getFloat());
        System.out.println("byteBuffer.getLong() = " + byteBuffer.getLong());
        System.out.println("byteBuffer.getDouble() = " + byteBuffer.getDouble());
    }
}
