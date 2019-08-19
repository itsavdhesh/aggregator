package com.trade.aggregator.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.trade.aggregator.entities.Trade;
import com.trade.aggregator.publisher.IUIPublisher;
import com.trade.aggregator.publisher.UIPublisher;

@Component("tradeReceiver")
public class TradeReceiver implements ITradeReceiver {

	@Autowired
	private ApplicationContext context;

	@Override
	public void onMessage(Trade trade) {

		if (trade == null)
			return;

		IUIPublisher uiPublisher = (IUIPublisher) context.getBean(UIPublisher.class);
		uiPublisher.publishTradeToUI(trade);
		TradeAggregator tradeAggregator = (TradeAggregator) context.getBean(TradeAggregator.class);
		tradeAggregator.aggregateTrade(trade);
	}
}
