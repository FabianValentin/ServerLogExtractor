package com.server.log.extractor.ServerLogExtractor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerLogsController {

    @GetMapping("/server-logs")
    public String showServerLogs() {
        return "server-logs";
    }
}
