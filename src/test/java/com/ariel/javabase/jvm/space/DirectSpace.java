package com.ariel.javabase.jvm.space;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class DirectSpace {

    public static void main(String[] args) {
        // 申请直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.gc();

        scanner.next();

    }

}
