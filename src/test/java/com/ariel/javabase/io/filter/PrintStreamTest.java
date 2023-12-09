package com.ariel.javabase.io.filter;

import org.junit.Test;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {

    @Test
    public void println() {
        PrintStream printStream = new PrintStream(new FileOutputStream(FileDescriptor.out), true);
        printStream.println("Hello world");
    }

}
