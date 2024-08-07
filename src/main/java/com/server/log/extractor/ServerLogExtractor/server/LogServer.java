package com.server.log.extractor.ServerLogExtractor.server;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;

@Component
public class LogServer implements Runnable {
    private final int port = 12345;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Simulate log messages
                for (int i = 0; i < 10; i++) {
                    writer.println("Log message " + (i + 1));
                    Thread.sleep(1000); // Wait for 1 second
                }

                socket.close();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}