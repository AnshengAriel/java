package com.ariel.javabase.web;

import org.junit.Test;

public class HttpServerTest {

    @Test
    public void handle() {
        HttpServer server = new HttpServer(8080);
        server.accept();
    }

}
