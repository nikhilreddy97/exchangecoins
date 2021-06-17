package com.exchange.coins.exchangecoins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.coins.exchangecoins.entity.Exchange;
import com.exchange.coins.exchangecoins.service.BillExchangeService;

@RestController
@RequestMapping("/api")
public class BillExchangeController {

	@Autowired
	BillExchangeService billExchangeService;

	@GetMapping("/get/change/{amount}")
	public ResponseEntity<List<Exchange>> getExchange(@PathVariable("amount") double amount) {
		List<Exchange> response = billExchangeService.getCoinsForAmount(amount);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
