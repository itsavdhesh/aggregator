package com.trade.aggregator.core;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.trade.aggregator.entities.Trade;
import com.trade.aggregator.publisher.IUIPublisher;
import com.trade.aggregator.publisher.UIPublisher;

@Component("tradeAggregator")
public class TradeAggregator {

	@Autowired
	private ExecutorService executor;

	@Autowired
	private ApplicationContext context;

	public void aggregateTrade(Trade trade) {
		AggregatorTask task = (AggregatorTask) context.getBean(AggregatorTask.class, trade);
		executor.execute(task);
	}

}

@Component
@Scope("prototype")
class AggregatorTask implements Runnable {

	Trade trade = null;

	@Autowired
	private ApplicationContext context;

	public AggregatorTask(Trade trade) {
		this.trade = trade;
	}

	@Override
	public void run() {
		try {
			aggregateTrade(trade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void aggregateTrade(Trade trade) throws IOException {

		Trade block = null;
		String key = trade.getClientCode() + "_" + trade.getStock() + "_" + trade.getSide();

		MainCache mainCache = (MainCache) context.getBean(MainCache.class);
		IUIPublisher uiPublisher = (IUIPublisher) context.getBean(UIPublisher.class);
		block = mainCache.getTradeCache().get(key);

		if (block == null) {
			trade.setBlockId(trade.getTradeId());
			mainCache.getTradeCache().put(key, trade);
		} else {
			double avgPrice = (block.getExecutedQuantity() * block.getExecutedPrice()
					+ trade.getExecutedQuantity() * trade.getExecutedPrice())
					/ (block.getExecutedQuantity() + trade.getExecutedQuantity());
			block.setExecutedQuantity(block.getExecutedQuantity() + trade.getExecutedQuantity());
			block.setExecutedPrice(avgPrice);
			mainCache.getTradeCache().put(key, block);
			uiPublisher.publishBlockToUI(block);
		}
	}
}
