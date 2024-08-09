package com.server.log.extractor.ServerLogExtractor.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String customLogin() {
        return "login-dark";
    }

    @GetMapping("/logout")
    public String customLogout() {
        return "login-dark";
    }

    @GetMapping("/statistics")
    public String getStatistics() {
        return "statistics";
    }
}
