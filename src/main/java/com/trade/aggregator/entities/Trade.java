package com.trade.aggregator.entities;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")

public class Trade implements Serializable {

	static final long serialVersionUID = -6912533836521317050L;
	private String tradeId = null;
	private String blockId = null;
	private String clientCode = null;
	private String side = null;
	private String stock = null;
	private long executedQuantity = -1;
	private double executedPrice = -1.0;
	private int tradeDate = 19000101;

	public Trade(double executedPrice, long executedQuantity, String tradeId,
			String side, String stock, String clientCode, int tradeDate,
			String blockOrderID) {
		super();
		this.executedPrice = executedPrice;
		this.executedQuantity = executedQuantity;
		this.tradeId = tradeId;
		this.side = side;
		this.stock = stock;
		this.clientCode = clientCode;
		this.tradeDate = tradeDate;
		this.blockId = blockOrderID;
	}

	public double getExecutedPrice() {
		return executedPrice;
	}

	public void setExecutedPrice(double executedPrice) {
		this.executedPrice = executedPrice;
	}

	public long getExecutedQuantity() {
		return executedQuantity;
	}

	public void setExecutedQuantity(long executedQuantity) {
		this.executedQuantity = executedQuantity;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public int getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(int tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", blockId=" + blockId + ", clientCode=" + clientCode + ", side=" + side
				+ ", stock=" + stock + ", executedQuantity=" + executedQuantity + ", executedPrice=" + executedPrice
				+ ", tradeDate=" + tradeDate + "]";
	}


	
	
	
}
