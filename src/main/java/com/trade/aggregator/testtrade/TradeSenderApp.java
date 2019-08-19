package com.trade.aggregator.testtrade;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.trade.aggregator.core.TradeReceiver;
import com.trade.aggregator.dblayer.ClientJdbcRepository;
import com.trade.aggregator.dblayer.StockJdbcRepository;
import com.trade.aggregator.entities.Trade;

@Component
public class TradeSenderApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ApplicationContext context;

	@Autowired
	ClientJdbcRepository clientRepository;

	@Autowired
	StockJdbcRepository stockRepository;

	static Random random = new Random();

	List<String> clientList = new ArrayList<String>();
	List<String> stockList = new ArrayList<String>();
	Map<String, Double> stockClosingPrice = new HashMap<String, Double>();

	private static long tradeId = 1000000;

	public TradeSenderApp() {
		clientList.add("BLACKR");
		clientList.add("MILL");
		clientList.add("TRS");
		clientList.add("MPLMT");
		clientList.add("UBS");
		clientList.add("CITI");
		clientList.add("JP");

		stockClosingPrice.put("IBM", 120.45);
		stockClosingPrice.put("GOOG", 230.15);
		stockClosingPrice.put("PTS", 34.55);
		stockClosingPrice.put("APPL", 27.25);
		stockClosingPrice.put("INFY", 78.40);
		stockClosingPrice.put("TESLA", 56.95);
		stockClosingPrice.put("TAMP", 66.65);

		stockList.add("IBM");
		stockList.add("GOOG");
		stockList.add("PTS");
		stockList.add("APPL");
		stockList.add("INFY");
		stockList.add("TESLA");
		stockList.add("TAMP");

	}

	@Scheduled(fixedRate = 1000)
	public void publishTrade() {

		Trade trade = getTrade();
		System.out.println("Sending Trades : " + trade.toString());
		TradeReceiver tr = (TradeReceiver) context.getBean(TradeReceiver.class);
		tr.onMessage(trade);

	}

	@PostConstruct
	public void dislayDBData() {

		LOGGER.info("Stocks -> {}", stockRepository.findAll());
		LOGGER.info("Clients -> {}", clientRepository.findAll());

	}

	private Trade getTrade() {

		int clientIndex = random.nextInt(clientList.size());
		int stockIndex = random.nextInt(stockList.size());
		int quantity = random.nextInt(5000);
		int sideIndex = random.nextInt(2);
		String stock = stockList.get(stockIndex);
		String side = null;

		if (sideIndex == 0)
			side = "B";
		else
			side = "S";
		Trade trade = new Trade(getPrice(stock), quantity, String.valueOf(tradeId++), side, stock,
				clientList.get(clientIndex), 20190805, null);
		return trade;
	}

	private double getPrice(String stock) {
		double price = 0.0;
		double closingPrice = stockClosingPrice.get(stock);
		double onePercent = closingPrice / 100;
		price = random.nextDouble() * ((closingPrice + onePercent) - (closingPrice - onePercent))
				+ (closingPrice - onePercent);
		return price;
	}
}
