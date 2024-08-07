package com.server.log.extractor.ServerLogExtractor.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class ClientService {
    private String hostname = "localhost";
    private int port = 12345;

    @Autowired
    private SimpMessagingTemplate template;

    public void connect() {
        new Thread(() -> {
            try (Socket socket = new Socket(hostname, port)) {
                System.out.println("Connected to the log server");

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String logMessage;
                while ((logMessage = reader.readLine()) != null) {
                    System.out.println("Received: " + logMessage);
                    template.convertAndSend("/topic/logs", logMessage);
                }
            } catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("I/O error: " + ex.getMessage());
            }
        }).start();
    }
}
