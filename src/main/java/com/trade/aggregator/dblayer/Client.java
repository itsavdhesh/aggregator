package com.trade.aggregator.dblayer;

import javax.persistence.Entity;

@Entity
public class Client {

	long id;
	String clientCode;
	String clientName;

	public Client() {
		super();
	}
	public Client(long id, String clientCode, String clientName) {
		super();
		this.id = id;
		this.clientCode = clientCode;
		this.clientName = clientName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientCode=" + clientCode + ", clientName=" + clientName + "]";
	}

}
