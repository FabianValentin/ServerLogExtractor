package com.server.log.extractor.ServerLogExtractor.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ServerController {

    @GetMapping("/servers")
    public String listServers(Model model, Authentication authentication) {
        List<String> servers;

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            servers = Arrays.asList("Server1", "Server2", "Server3");
        } else {
            servers = Arrays.asList("Server1");
        }

        model.addAttribute("servers", servers);
        return "servers";
    }
}
