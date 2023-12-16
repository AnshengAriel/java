package com.ariel.java.base.jvm.space;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.ObjectInputStream;

/**
 * jdk1.8:
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class MetaSpace {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        for (int i = 0; i < 10000; i++) {
            ClassWriter writer = new ClassWriter(0);
            writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            writer.visitEnd();
            byte[] bytes = writer.toByteArray();
            try {
                myClassLoader.myDefineClass("Class" + i, bytes, 0, bytes.length);
            } catch (OutOfMemoryError e) {
                System.out.println(i);
                throw new RuntimeException(e);
            }
        }
    }

    public static class MyClassLoader extends ClassLoader {
        public void myDefineClass(String name, byte[] b, int off, int len) {
            super.defineClass(name, b, off, len);
        }
    }

}
