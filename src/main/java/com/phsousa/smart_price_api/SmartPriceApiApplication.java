package com.phsousa.smart_price_api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class SmartPriceApiApplication {

	public static void main(String[] args) {

		SpringApplication app =
				new SpringApplication(SmartPriceApiApplication.class);

		Environment env = app.run(args).getEnvironment();

		logApplicationStartup(env);
	}

	private static void logApplicationStartup(
			Environment env
	) {

		String protocol =
				env.getProperty("server.ssl.key-store") != null
						? "https"
						: "http";

		String applicationName =
				env.getProperty(
						"spring.application.name",
						"SEM NOME A APLICACAO"
				);

		String serverPort =
				env.getProperty("server.port", "8080");

		String contextPath =
				env.getProperty(
						"server.servlet.context-path",
						""
				);

		String activeProfiles =
				String.join(", ", env.getActiveProfiles());

		String appVersion = env.getProperty("app.version", "SEM VERSAO");

		String swaggerUrl =
				protocol + "://localhost:" + serverPort + contextPath + "/swagger-ui/index.html";

		String hostAddress = "localhost";

		try {

			hostAddress =
					InetAddress.getLocalHost().getHostAddress();

		} catch (Exception ex) {

			log.warn(
					"Não foi possível obter o IP da aplicação"
			);
		}

		String currentDateTime = LocalDateTime.now()
				.format(
						DateTimeFormatter.ofPattern(
								"HH:mm:ss dd/MM/yyyy"
						)
				);

		log.info("""
				
				==================================================
				🚀 SMART PRICE API INICIADA
				
				Aplicação  : {}
				Version    : {}
				Local      : {}://localhost:{}{}
				Externo    : {}://{}:{}{}
				Swagger    : {}
				Profiles   : {}
				Java       : {}
				Spring     : {}
				Spring Boot: {}
				Data/Hora  : {}
				Região     : {}
				==================================================
				""",

				applicationName,
				appVersion,

				protocol,
				serverPort,
				contextPath,

				protocol,
				hostAddress,
				serverPort,
				contextPath,

				swaggerUrl,

				activeProfiles.isBlank()
						? "default"
						: activeProfiles,

				System.getProperty("java.version"),

				org.springframework.core.SpringVersion.getVersion(),
				org.springframework.boot.SpringBootVersion.getVersion(),
				currentDateTime,
				TimeZone.getDefault().getID() + " (" + TimeZone.getDefault().getDisplayName() + ")"

		);
	}
}