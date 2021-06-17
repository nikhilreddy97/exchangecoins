package com.exchange.coins.exchangecoins.service;

import java.util.List;

import com.exchange.coins.exchangecoins.entity.Exchange;

public interface BillExchangeService {

	List<Exchange> getCoinsForAmount(double amount);

}
