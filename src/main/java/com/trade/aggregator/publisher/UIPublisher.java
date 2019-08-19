package com.trade.aggregator.publisher;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.trade.aggregator.entities.Trade;

@Component
public class UIPublisher implements IUIPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void publishTradeToUI (Trade trade) {
		LOGGER.info("Recieved Trade : " + trade.toString());
	}

	public void publishBlockToUI(Trade trade) {
		LOGGER.info("Aggregated BLOCK : " + trade);
	}
}
