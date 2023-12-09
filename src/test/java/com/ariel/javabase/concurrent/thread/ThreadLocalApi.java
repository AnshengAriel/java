package com.ariel.javabase.concurrent.thread;

import org.junit.Test;

public class ThreadLocalApi {

    @Test
    public void test() throws InterruptedException {
        try (UserContext user = new UserContext("zs")) {
            currentUser();
            System.out.println("UserContext.currentUser() = " + UserContext.currentUser());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("UserContext.currentUser() = " + UserContext.currentUser());
    }

    public void currentUser() {
        System.out.println("UserContext.currentUser() = " + UserContext.currentUser());
        new UserContext("ls");
    }

}
