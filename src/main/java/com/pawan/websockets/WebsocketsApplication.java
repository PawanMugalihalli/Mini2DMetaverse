package com.pawan.websockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class WebsocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketsApplication.class, args);
	}

}
