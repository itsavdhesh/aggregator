package com.trade.aggregator.main;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = { "com.trade.aggregator.core", "com.trade.aggregator.entities", "com.trade.aggregator.testtrade",
		"com.trade.aggregator.publisher", "com.trade.aggregator.dblayer" })
@PropertySource(value = "classpath:application.properties")
@EnableScheduling

public class AppLauncher {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		LOGGER.debug("Starting the Aggregator..");
		SpringApplication.run(AppLauncher.class, args);
	}
}
