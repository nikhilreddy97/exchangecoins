package com.exchange.coins.exchangecoins.service;

import java.util.List;

import com.exchange.coins.exchangecoins.entity.Exchange;
import com.exchange.coins.exchangecoins.entity.ExchangeResponse;

public interface BillExchangeService {

	List<ExchangeResponse> getCoinsForAmount(double amount);

}
