package com.ariel.java.io.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * Buffer详解
 * <a href='project/_20230605135544/src/test/java/com/ariel/io/_20230625133030.java' style='color:green;font-weight:bold;'>Buffer详解</a>
 */
public class BufferTest {

    @Test
    public void testAllocate() {
        // 创建堆缓冲区（间接缓冲区），间接缓冲区的创建和销毁效率要高于直接缓冲区
        ByteBuffer heepByteBuffer = ByteBuffer.allocate(10);
//        ByteBufferTest heepByteBuffer = ByteBufferTest.wrap(new byte[10]);
        System.out.println("heepByteBuffer.array() = " + Arrays.toString(heepByteBuffer.array()));
    }

    @Test
    public void testAllocateDirect() {
        // 创建堆外内存缓冲区（直接缓冲区），直接缓冲区的工作效率要高于间接缓冲区
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(10);
        System.out.println("directByteBuffer.array() = " + Arrays.toString(directByteBuffer.array()));
    }

    @Test
    public void testPosition() {
        // position本质是一次操作的起点，limit是终点，这样从起点执行到终点的一组操作，可读也可写，就非常方便
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            intBuffer.put(i);
            System.out.printf("After put, position = %s%n", intBuffer.position());
        }

        // 将写模式切换成读模式
        intBuffer.flip();
        System.out.printf("After flip, position = %s%n", intBuffer.position());

        while (intBuffer.hasRemaining()) {
            System.out.println("intBuffer.get() = " + intBuffer.get());
            System.out.printf("After get, position = %s%n", intBuffer.position());
        }
    }

    @Test
    public void testLimit() {
        // limit为一组操作的终点
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        while (intBuffer.hasRemaining()) {
            System.out.println("intBuffer.get() = " + intBuffer.get());
        }

        intBuffer.position(0);
        intBuffer.limit(5);
        System.out.println("After set limit = 5, intBuffer.limit() = " + intBuffer.limit());
        while (intBuffer.hasRemaining()) {
            System.out.println("intBuffer.get() = " + intBuffer.get());
        }
    }

    @Test
    public void testMark() {
        // mark变量用于保存一个position标记位，并且这个值不对外暴露
        // 实际执行 mark = position;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(1);
        intBuffer.mark();
    }

    @Test
    public void testReset() {
        // reset()方法重置position为上一次保存的mark标记位
        // 实际执行 position = mark;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(1);
        intBuffer.mark();

        intBuffer.put(2);
        System.out.println("After put, intBuffer.position() = " + intBuffer.position());

        intBuffer.reset();
        System.out.println("After reset, intBuffer.position() = " + intBuffer.position());
    }

    @Test
    public void testClear() {
        // 将所有标记复位，但不会清除数据
        // 实际执行 position = 0; limit = capacity; mark = -1;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(1);
        intBuffer.mark();

        intBuffer.put(2);
        System.out.println("After put, intBuffer.position() = " + intBuffer.position());

        intBuffer.reset();
        System.out.println("After reset, intBuffer.position() = " + intBuffer.position());

        intBuffer.clear();
        System.out.println("After clear, intBuffer.position() = " + intBuffer.position());
        System.out.println("After clear, intBuffer.limit() = " + intBuffer.limit());

        for (int i = 0; i < 2; i++) {
            System.out.println("intBuffer.get() = " + intBuffer.get());
        }
    }

    @Test
    public void testFlip() {
        // 将position复位，在反转读写操作时使用
        // 实际执行 limit = position; position = 0; mark = -1;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        intBuffer.flip();
        System.out.println("After flip, intBuffer.position() = " + intBuffer.position());
        System.out.println("After flip, intBuffer.limit() = " + intBuffer.limit());
    }

    @Test
    public void testPut() {
        // intBuffer.put(i); 在当前位置插入元素，并且position++
        // intBuffer.put(8, 8); 在指定位置插入元素，不会改变position
        IntBuffer intBuffer = IntBuffer.allocate(10);

        System.out.println("intBuffer.position() = " + intBuffer.position());
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        System.out.println("After put, intBuffer.position() = " + intBuffer.position());

        intBuffer.put(8, 8);
        System.out.println("After put(8, 8), intBuffer.position() = " + intBuffer.position());
    }

    @Test
    public void testGet() {
        // intBuffer.get(); 获取当前位置的元素，并且position++
        // intBuffer.get(3); 获取指定位置的元素，不会改变position
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.get(5) = " + intBuffer.get(3));
        System.out.println("After get(5), intBuffer.position() = " + intBuffer.position());
    }

    @Test
    public void testRewind() {
        // 重绕此缓冲区
        // 实际执行 position = 0; mark = -1;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        System.out.println("intBuffer.position() = " + intBuffer.position());
        intBuffer.rewind();
        System.out.println("After rewind, intBuffer.position() = " + intBuffer.position());
    }

    @Test
    public void testRemaining() {
        // 返回position到limit位置的个数
        // 实际执行 limit - position;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 4; i++) {
            intBuffer.put(i);
        }

        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.remaining() = " + intBuffer.remaining());
    }

    @Test
    public void testHasRemaining() {
        // 返回从position到limit的元素个数，用于作为遍历操作的出口条件
        // 实际执行 position < limit;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 4; i++) {
            intBuffer.put(i);
        }

        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.hasRemaining() = " + intBuffer.hasRemaining());
    }

    @Test
    public void testIsReadOnly() {
        // 返回此缓冲区是否只读
        // 实际执行 return isReadOnly;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("intBuffer.isReadOnly() = " + intBuffer.isReadOnly());
    }

    @Test
    public void testHasArray() {
        // 返回此缓冲区是否具有可访问的底层实现数组
        // 实际执行 return (hb != null) && !isReadOnly
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("intBuffer.hasArray() = " + intBuffer.hasArray());
    }

    @Test
    public void testArray() {
        // 返回此缓冲区的底层实现数组，首先数组必须是可读的
        // 实际执行 return hb;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("intBuffer.array() = " + Arrays.toString(intBuffer.array()));
    }

    @Test
    public void testArrayOffset() {
        // 返回此缓冲区的底层实现数组的第一个元素的偏移量，首先数组必须是可读的
        // 实际执行 return offset;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("intBuffer.arrayOffset() = " + intBuffer.arrayOffset());

        intBuffer.put(1);
        System.out.println("intBuffer.arrayOffset() = " + intBuffer.arrayOffset());
    }

    @Test
    public void testIsDirect() {
        // 返回此缓冲区是否是直接缓冲区（使用了堆内存）
        // 实际执行 return true; 或者 return false;
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("intBuffer.isDirect() = " + intBuffer.isDirect());

        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(10);
        System.out.println("directByteBuffer.isDirect() = " + directByteBuffer.isDirect());

        IntBuffer intBufferByWrap = IntBuffer.wrap(new int[10]);
        System.out.println("intBufferByWrap.isDirect() = " + intBufferByWrap.isDirect());
    }

    @Test
    public void testAsReadOnlyBuffer() {
        // 复制一个只读副本
        // 实际执行 return new HeapByteBufferR(hb, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);

        for (byte i = 0; i < 10; i++) {
            byteBuffer.put(i);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println("readOnlyBuffer.isReadOnly() = " + readOnlyBuffer.isReadOnly());
    }

}
