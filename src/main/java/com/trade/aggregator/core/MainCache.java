package com.trade.aggregator.core;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.trade.aggregator.entities.Trade;

import net.openhft.chronicle.map.ChronicleMapBuilder;

@Component("mainCache")
public class MainCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, Trade> tradeCache = null;

	public MainCache() {
		try {

			initializeCache();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeCache() throws IOException {
		setTradeCache(loadTradeData());
		LOGGER.info("Trade recovery completed.");
	}

	public Map<String, Trade> getTradeCache() {
		return tradeCache;
	}

	public void setTradeCache(Map<String, Trade> tradeCache) {
		this.tradeCache = tradeCache;
	}

	private Map<String, Trade> loadTradeData() throws IOException {
		File file = new File("target//trades.dat");
		LOGGER.info("Trade recovery started.");
		ChronicleMapBuilder<String, Trade> trades = ChronicleMapBuilder.of(String.class, Trade.class);
		return trades.create(file);
	}
}
