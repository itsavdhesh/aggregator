package com.trade.aggregator.publisher;

import java.io.File;
import java.io.FileWriter;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.trade.aggregator.core.MainCache;

@Component
@Scope("prototype")
public class DownStreamPublisherTask implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Scanner scanner = new Scanner(System.in);

	@Autowired
	private ApplicationContext context;

	@Override
	public void run() {

		while (true) {
			scanner.nextLine();
			publishBlocks();
			LOGGER.info("All Blocks published to downstream!");
		}
	}

	private synchronized void publishBlocks() {

		MainCache mainCache = (MainCache) context.getBean(MainCache.class);
		try {
			new File("C:\\out").mkdir();
			FileWriter fw = new FileWriter("C:\\out\\tradeoutput.txt");

			for (String key : mainCache.getTradeCache().keySet()) {
				fw.write(mainCache.getTradeCache().get(key).toString());
				fw.write("\r\n");
				mainCache.getTradeCache().remove(key);
				
			}
			LOGGER.info("Published the data in file C:\\\\tradeoutput.txt ");

			fw.close();

		} catch (Exception e) {
			LOGGER.error("Error in writing the output in file.." , e);
		}

	}
}
