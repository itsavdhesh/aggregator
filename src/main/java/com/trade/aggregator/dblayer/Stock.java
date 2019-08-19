package com.trade.aggregator.dblayer;

import javax.persistence.Entity;

@Entity
public class Stock {

	long id;
	String stock;
	double lastPrice;

	public Stock() {
		super();
	}

	public Stock(int id, String stock, double lastPrice) {
		super();
		this.id = id;
		this.stock = stock;
		this.lastPrice = lastPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

}
