package com.ariel.java.base.web;

import lombok.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Data
public class HttpServer {

    private final ServerSocket serverSocket;

    public HttpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public HttpServer(Integer port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void accept() {
        try (Socket socket = serverSocket.accept()) {
            try (InputStream in = socket.getInputStream()) {
                try (OutputStream out = socket.getOutputStream()) {
                    handle(in, out);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));

        System.out.println("-------------------------" + "request" + "-------------------------");
        StringBuilder builder = new StringBuilder();
        String first = reader.readLine();
        builder.append(first).append("\n");

        boolean requestOk = true;
        if (first.startsWith("GET")) {
            requestOk = false;
        }
        for (String readed = reader.readLine(); !readed.isEmpty(); readed = reader.readLine()) {
            builder.append(readed).append("\n");
        }
        System.out.println(builder);

        System.out.println("-------------------------" + "response" + "-------------------------");
        // 读取HTTP请求:
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            // 发送错误响应:
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // 发送成功响应:
            String data = "<html><body><h1>Hello, world!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data);
            writer.flush();
        }
    }

}
