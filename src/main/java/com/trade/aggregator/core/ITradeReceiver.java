package com.trade.aggregator.core;

import org.springframework.stereotype.Component;

import com.trade.aggregator.entities.Trade;

@Component
public interface ITradeReceiver {
	void onMessage(Trade trade);
}