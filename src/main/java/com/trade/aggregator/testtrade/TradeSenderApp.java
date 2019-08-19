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
import com.trade.aggregator.dblayer.Client;
import com.trade.aggregator.dblayer.ClientJdbcRepository;
import com.trade.aggregator.dblayer.Stock;
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

	List<Client> clientList = new ArrayList<Client>();
	List<Stock> stockList = new ArrayList<Stock>();
	Map<String, Double> stockClosingPrice = new HashMap<String, Double>();

	private static long tradeId = 1000000;

	@Scheduled(fixedDelay = 100)
	public void publishTrade() {
		Trade trade = getTrade();
		long delay = random.nextInt(400);
		try {
			Thread.sleep(100 + delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TradeReceiver tr = (TradeReceiver) context.getBean(TradeReceiver.class);
		tr.onMessage(trade);
	}

	@PostConstruct
	public void intiStaticData() {

		LOGGER.info("Stocks -> {}", stockRepository.findAll());
		LOGGER.info("Clients -> {}", clientRepository.findAll());

		clientList.addAll(clientRepository.findAll());
		stockList.addAll(stockRepository.findAll());
	}

	private Trade getTrade() {

		int clientIndex = random.nextInt(clientList.size());
		int stockIndex = random.nextInt(stockList.size());
		int quantity = random.nextInt(5000);
		int sideIndex = random.nextInt(2);
		String stock = stockList.get(stockIndex).getStock();
		String side = null;

		if (sideIndex == 0)
			side = "B";
		else
			side = "S";
		Trade trade = new Trade(getPrice(stockIndex), quantity, String.valueOf(tradeId++), side, stock,
				clientList.get(clientIndex).getClientCode(), 20190805, null);
		return trade;
	}

	private double getPrice(int stockIndex) {
		double price = 0.0;
		double closingPrice = stockList.get(stockIndex).getLastPrice();
		double onePercent = closingPrice / 100;
		price = random.nextDouble() * ((closingPrice + onePercent) - (closingPrice - onePercent))
				+ (closingPrice - onePercent);
		return price;
	}
}
