package com.exchange.coins.exchangecoins.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND, reason = "No Details Found")
public class CoinMachineException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CoinMachineException(String message) {
		super(message);
	}

}