package com.exchange.coins.exchangecoins.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Exchange implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private double cents;
	private int maxCoins;

	public Exchange(double cents, int maxCoins) {
		this.cents = cents;
		this.maxCoins = maxCoins;
	}

	public Exchange() {
	}

	public double getCents() {
		return cents;
	}

	public void setCents(double cents) {
		this.cents = cents;
	}

	public int getMaxCoins() {
		return maxCoins;
	}

	public void setMaxCoins(int maxCoins) {
		this.maxCoins = maxCoins;
	}

	@Override
	public String toString() {
		return "Exchange [cents=" + cents + ", maxCoins=" + maxCoins + "]";
	}

}
