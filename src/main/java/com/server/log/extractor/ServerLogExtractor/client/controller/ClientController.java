package com.server.log.extractor.ServerLogExtractor.client.controller;

import com.server.log.extractor.ServerLogExtractor.client.service.ClientService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService logClientService) {
        this.clientService = logClientService;
    }

    private String hostname = "localhost";
    private int port = 12345;

    @GetMapping("/servers")
    public String listServers(Model model, Authentication authentication) {
        List<String> servers;

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            servers = Arrays.asList("Server1", "Server2", "Server3");
        } else {
            servers = Arrays.asList("Server1");
        }

        model.addAttribute("servers", servers);

        // Connect to the log server
        clientService.connect();

        return "servers";
    }

        @GetMapping("/logs")
    public String showLogs() {
        // Connect to the log server
        clientService.connect();
        return "logs";
    }

}
