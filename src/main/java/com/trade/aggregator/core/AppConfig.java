package com.trade.aggregator.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.trade.aggregator.publisher.DownStreamPublisherTask;

@Configuration
public class AppConfig {

	@Bean(name = "executor")
	public ExecutorService threadPoolTaskExecutor() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		return executor;
	}

	@Bean(name = "downStreamPublisherTask")
	public DownStreamPublisherTask geDownStreamPublisherTask() {
		return new DownStreamPublisherTask();
	}

	@Bean(name = "releaseBlockExecutor")
	@DependsOn("downStreamPublisherTask")
	public ExecutorService threadPoolTaskReleaseBlockExecutor() {
		ExecutorService releaseBlockExecutor = Executors.newFixedThreadPool(10);
		return releaseBlockExecutor;
	}
}
