package com.ariel.java.base.jvm.init;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class MyClassLoader extends ClassLoader {

    private String className;

    public MyClassLoader(String className) {
        this.className = className;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = getClassBytes();
        return copy().defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] getClassBytes() {
        String path = className.replace('.', '/').concat(".class");
        try (InputStream in = getStream(path)) {
            byte[] result = new byte[in.available()];
            in.read(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MyClassLoader copy() {
        return new MyClassLoader(className);
    }

    private InputStream getStream(String path) throws IOException {
        File file = new File("src/main/java/" + path);
        if (file.exists()) {
            return Files.newInputStream(file.toPath());
        }
        return ClassLoader.getSystemResourceAsStream(path);
    }
}
