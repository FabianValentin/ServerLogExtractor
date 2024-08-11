package com.server.log.extractor.ServerLogExtractor.client.controller;

import com.server.log.extractor.ServerLogExtractor.client.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @GetMapping(value = "/logs", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> showLogs() {
        /*// Connect to the log server
        clientService.connect();
        return "logs";*/
        return Flux.fromStream(new Random()
                                       .ints(10000)
                                       .mapToObj(value -> {
                                           final int randomPath = (int) (Math.random() * 100);
                                           if (randomPath % 3 == 0) {
                                               return new Date() + " DEBUG " + " Transaction " + value + " started" + "\n";
                                           } else if (randomPath % 3 == 1) {
                                               return new Date() + " DEBUG " + " Transaction " + value + " completed" + "\n";
                                           } else {
                                               return new Date() + " ERROR " + " Transaction " + value + " failed" + "\n";
                                           }
                                       }))
                   .delayElements(Duration.ofMillis(300));
    }

}
