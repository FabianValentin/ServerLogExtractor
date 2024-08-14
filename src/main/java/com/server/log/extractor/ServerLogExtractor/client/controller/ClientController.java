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
        return Flux.fromStream(new Random()
                                       .ints(10000)
                                       .mapToObj(value -> {
                                           Random random = new Random();
                                           int randomNumber = random.nextInt(9);
                                           if (randomNumber == 0) {
                                               return new Date() + " INFO id=CRRRS01902E Message=An exception occurred while the OAuth cookies for IBM HTTP Server were being checked" + "\n";
                                           } else if (randomNumber == 1) {
                                               return new Date() + " INFO id=CRRRS0600E Message=Server(s) have been renamed." + "\n";
                                           } else if (randomNumber == 2) {
                                               return new Date() + " INFO id=CRRRS08723E Message=A link was not created between the copied and original artifacts" + "\n";
                                           } else if (randomNumber == 3) {
                                               return new Date() + " DEBUG id=CRRRS08704W Message=The server is too busy to handle the requested query" + "\n";
                                           } else if (randomNumber == 4) {
                                               return new Date() + " DEBUG id=CRRRS1008E Message=The request could not be run" + "\n";
                                           } else if (randomNumber == 5) {
                                               return new Date() + " ERROR id=CRRRS1006E Message=The resource URL cannot be found or has invalid parameters" + "\n";
                                           } else if (randomNumber == 6) {
                                               return new Date() + " ERROR id=CRMVC0007E Message=The projects could not be loaded" + "\n";
                                           } else if (randomNumber == 7) {
                                               return new Date() + " INFO id=CRMVC0009E Message=There was a problem communicating with the server" + "\n";
                                           } else {
                                               return new Date() + " INFO id=CRRGW0143I Message=Set authentication server URL" + "\n";
                                           }
                                       }))
                   .delayElements(Duration.ofMillis(300));
    }

    @GetMapping(value = "/logs/database", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> showDatabaseLogs() {
        return Flux.fromStream(new Random()
                                       .ints(10000)
                                       .mapToObj(value -> {
                                           Random random = new Random();
                                           int randomNumber = random.nextInt(9);
                                           if (randomNumber == 0) {
                                               return new Date() + " DEBUG id=CRRTC3543E Message=The workspace with the following UUID was not found in the repository" + "\n";
                                           } else if (randomNumber == 1) {
                                               return new Date() + " DEBUG id=CRRTC4644E Message=The connection to CQ database failed" + "\n";
                                           } else if (randomNumber == 2){
                                               return new Date() + " INFO id=CRJAZ1850E Message=Unable to save the consumer key and secret into the application's database" + "\n";
                                           } else if (randomNumber == 3) {
                                               return new Date() + " ERROR id=CRRRS2048E Message=An error occurred while upgrading the RM application to use components" + "\n";
                                           } else if (randomNumber == 4) {
                                               return new Date() + " ERROR id=CRRRS9603W Message=The database is not configured properly. Run the setup utility" + "\n";
                                           } else if (randomNumber == 5) {
                                               return new Date() + " DEBUG id=CRRRW7551E Message=The requested artifact cannot be found" + "\n";
                                           } else if (randomNumber == 7) {
                                               return new Date() + " INFO id=CRRTC1009W Message=The plan cannot be migrated because the plan name is empty" + "\n";
                                           }  else if (randomNumber == 8) {
                                               return new Date() + " INFO id=CRRTC5099E Message=The database could not be reached." + "\n";
                                           } else {
                                               return new Date() + " INFO id=CRRGW0062I Message=Shutting down the model database" + "\n";
                                           }
                                       }))
                   .delayElements(Duration.ofMillis(300));
    }




}
