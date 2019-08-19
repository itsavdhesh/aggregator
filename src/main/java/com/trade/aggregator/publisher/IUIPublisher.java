package com.trade.aggregator.publisher;

import org.springframework.stereotype.Component;

import com.trade.aggregator.entities.Trade;

@Component
public interface IUIPublisher {
	public void publishTradeToUI (Trade trade);
	public void publishBlockToUI(Trade trade);
}
