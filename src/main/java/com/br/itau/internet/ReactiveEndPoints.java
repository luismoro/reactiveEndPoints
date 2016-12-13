package com.br.itau.internet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.DelegatingWebReactiveConfiguration;

import reactor.ipc.netty.http.server.HttpServer;

@SpringBootApplication
public class ReactiveEndPoints {
	private static final Logger log = LoggerFactory.getLogger(ReactiveEndPoints.class);

	@Bean
	public HttpServer getHttpServer(){
		ApplicationContext context = new AnnotationConfigApplicationContext(DelegatingWebReactiveConfiguration.class);
		HttpHandler handler = DispatcherHandler.toHttpHandler(context);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
		HttpServer server = HttpServer.create("localhost", 8080);
		server.newHandler(adapter);
		return server;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveEndPoints.class, args);
	}
}