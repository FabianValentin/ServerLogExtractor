package com.server.log.extractor.ServerLogExtractor;

import com.server.log.extractor.ServerLogExtractor.server.LogServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerLogExtractorApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ServerLogExtractorApplication.class, args);
		LogServer logServer = context.getBean(LogServer.class);
		new Thread(logServer).start();
	}

}
