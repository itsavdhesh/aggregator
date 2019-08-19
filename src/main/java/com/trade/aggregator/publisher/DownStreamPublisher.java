package com.trade.aggregator.publisher;

import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DownStreamPublisher {

	@Autowired
	private ExecutorService releaseBlockExecutor;

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void startThread() {
		DownStreamPublisherTask task = (DownStreamPublisherTask) context.getBean(DownStreamPublisherTask.class);
		releaseBlockExecutor.submit(task);
	}
}
