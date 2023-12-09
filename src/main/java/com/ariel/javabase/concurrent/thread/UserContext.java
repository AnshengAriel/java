package com.ariel.javabase.concurrent.thread;

public class UserContext implements AutoCloseable {

    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public UserContext(String name) {
        threadLocal.set(name);
    }

    public static String currentUser() {
        return threadLocal.get();
    }

    @Override
    public void close() throws Exception {
        threadLocal.remove();
    }
}
